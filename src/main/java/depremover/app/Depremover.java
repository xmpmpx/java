package depremover.app;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Depremover {

    public static void main(String[] args) {
        try {
            long time = System.currentTimeMillis();
            Files.walk(Paths.get("D:\\dev\\java\\src\\main\\java\\depremover\\toremove"))
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

            String newContent = removeDeps(oldContent);
            writer = new FileWriter(file);
            if (newContent.split(System.lineSeparator()).length == 1) {
                file.deleteOnExit();
            } else {
                writer.write(newContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String removeDeps(String oldContent) {
        CompilationUnit compilationUnit = StaticJavaParser.parse(oldContent);
        List<ClassOrInterfaceDeclaration> classOrInterfaceDeclarations = compilationUnit.findAll(ClassOrInterfaceDeclaration.class);

        classOrInterfaceDeclarations.stream()
                .filter(classOrInterfaceDeclaration -> classOrInterfaceDeclaration.isAnnotationPresent("Deprecated"))
                .forEach(Node::removeForced);

        classOrInterfaceDeclarations.stream().flatMap(classOrInterfaceDeclaration -> classOrInterfaceDeclaration.getFields().stream())
                .filter(fieldDeclaration -> fieldDeclaration.isAnnotationPresent("Deprecated"))
                .forEach(Node::remove);

        classOrInterfaceDeclarations.stream().flatMap(classOrInterfaceDeclaration -> classOrInterfaceDeclaration.getMethods().stream())
                .filter(methodDeclaration -> methodDeclaration.isAnnotationPresent("Deprecated"))
                .forEach(Node::remove);

        return compilationUnit.toString();
    }
}
