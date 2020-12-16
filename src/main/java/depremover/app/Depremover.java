package depremover.app;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Depremover {

    public static final String DEPREMOVER_TOREMOVE = "D:\\dev\\java\\src\\main\\java\\depremover\\toremove";
    public static final String DEPREMOVER_NDC = "C:\\dev\\NDCModule\\src\\main";
    public static int depCounter = 0;
    public static int fileCounter = 0;

    public static void main(String[] args) {
        try {
            long time = System.currentTimeMillis();
            Files.walk(Paths.get(DEPREMOVER_TOREMOVE))
                    .filter(Files::isRegularFile).filter(path -> path.toString().endsWith(".java"))
                    .map(Path::toFile)
                    .forEach(Depremover::processFile);
            System.out.println("Processing time: " + (System.currentTimeMillis() - time) + " ms");
            System.out.println("Affected files: " + fileCounter);
            System.out.println("Deps number: " + depCounter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void processFile(File file) {
        FileWriter writer = null;
        try {
            Pair<Boolean, String> newContent = removeDeps(file);
            if (newContent.getLeft()) {
                if (emptyClass(newContent.getRight())) {
                    file.deleteOnExit();
                } else {
                    writer = new FileWriter(file);
                    writer.write(newContent.getRight());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean emptyClass(String newContent) {
        String[] split = newContent.split(System.lineSeparator());
        for (String s : split) {
            s = s.trim();
            if (!s.startsWith("import")
                    && !s.startsWith("package")
                    && !s.isEmpty()
                    && !s.startsWith("/*")
                    && !s.startsWith("*")) {
                return false;
            }
        }
        return true;
    }

    private static Pair<Boolean, String> removeDeps(File file) throws FileNotFoundException {
        CompilationUnit compilationUnit = StaticJavaParser.parse(file);
        LexicalPreservingPrinter.setup(compilationUnit);
        List<ClassOrInterfaceDeclaration> classOrInterfaceDeclarations = compilationUnit.findAll(ClassOrInterfaceDeclaration.class);

        List<ClassOrInterfaceDeclaration> depClazz = classOrInterfaceDeclarations.stream()
                .filter(classOrInterfaceDeclaration -> classOrInterfaceDeclaration.isAnnotationPresent("Deprecated"))
                .collect(Collectors.toList());

        List<FieldDeclaration> depFields = classOrInterfaceDeclarations.stream()
                .flatMap(classOrInterfaceDeclaration -> classOrInterfaceDeclaration.getFields().stream())
                .filter(fieldDeclaration -> fieldDeclaration.isAnnotationPresent("Deprecated"))
                .collect(Collectors.toList());

        List<MethodDeclaration> depMethods = classOrInterfaceDeclarations.stream().
                flatMap(classOrInterfaceDeclaration -> classOrInterfaceDeclaration.getMethods().stream())
                .filter(methodDeclaration -> methodDeclaration.isAnnotationPresent("Deprecated"))
                .collect(Collectors.toList());

        boolean isChanged = false;
        if (!depClazz.isEmpty() || !depFields.isEmpty() || !depMethods.isEmpty()) {
            int currentFileDeps = depClazz.size() + depFields.size() + depMethods.size();
            System.out.println(file.getName() + " - " + currentFileDeps + "deps");
            fileCounter++;
            depCounter = depCounter + currentFileDeps;

            depClazz.forEach(clazz -> clazz.getComment().ifPresent(Node::removeForced));
            depFields.forEach(field -> field.getComment().ifPresent(Node::removeForced));
            depMethods.forEach(method -> method.getComment().ifPresent(Node::removeForced));
            depClazz.forEach(Node::removeForced);
            depFields.forEach(Node::removeForced);
            depMethods.forEach(Node::removeForced);
            isChanged = true;
        }
        return Pair.of(isChanged, LexicalPreservingPrinter.print(compilationUnit));
    }
}
