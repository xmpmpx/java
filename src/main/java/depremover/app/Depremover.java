package depremover.app;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Depremover {

    public static void main(String[] args) {
        try {
            long time = System.currentTimeMillis();
            Files.walk(Paths.get("C:\\dev\\NDCModule\\src\\main"))
                    .filter(Files::isRegularFile).filter(path -> path.toString().endsWith(".java"))
                    .map(Path::toFile)
                    .forEach(Depremover::processFile);
            System.out.println(System.currentTimeMillis() - time + " ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void processFile(File file) {
        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            while (line != null) {
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }

            Pair<Boolean, String> newContent = removeDeps(oldContent);
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
                reader.close();
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
            if (!s.startsWith("import") && !s.startsWith("package") && !s.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private static Pair<Boolean, String> removeDeps(String oldContent) {
        CompilationUnit compilationUnit = StaticJavaParser.parse(oldContent);
        List<ClassOrInterfaceDeclaration> classOrInterfaceDeclarations = compilationUnit.findAll(ClassOrInterfaceDeclaration.class);

        List<ClassOrInterfaceDeclaration> depClazz = classOrInterfaceDeclarations.stream()
                .filter(classOrInterfaceDeclaration -> classOrInterfaceDeclaration.isAnnotationPresent("Deprecated"))
                .collect(Collectors.toList());

        List<FieldDeclaration> depFields = classOrInterfaceDeclarations.stream()
                .flatMap(classOrInterfaceDeclaration -> classOrInterfaceDeclaration.getFields().stream())
                .filter(fieldDeclaration -> fieldDeclaration.isAnnotationPresent("Deprecated"))
                .collect(Collectors.toList());;

        List<MethodDeclaration> depMethods = classOrInterfaceDeclarations.stream().
                flatMap(classOrInterfaceDeclaration -> classOrInterfaceDeclaration.getMethods().stream())
                .filter(methodDeclaration -> methodDeclaration.isAnnotationPresent("Deprecated"))
                .collect(Collectors.toList());

        boolean isChanged = false;
        Consumer<ClassOrInterfaceDeclaration> removeForced = Node::removeForced;
        if (!depClazz.isEmpty() || !depFields.isEmpty() || !depMethods.isEmpty()) {
            depClazz.forEach(removeForced);
            depFields.forEach(Node::remove);
            depMethods.forEach(Node::remove);
            isChanged = true;
        }

        return Pair.of(isChanged, compilationUnit.toString());
    }
}
