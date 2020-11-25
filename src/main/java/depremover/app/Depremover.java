package depremover.app;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Depremover {

    public static void main(String[] args) {
        try {
            Files.walk(Paths.get("D:\\dev\\java\\src\\main\\java\\depremover\\toremove"))
                    .filter(Files::isRegularFile).filter(path -> path.toString().endsWith(".java"))
                    .map(Path::toFile)
                    .forEach(file -> processFile(file));
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

            //Reading all the lines of input text file into oldContent

            String line = reader.readLine();

            while (line != null) {
                oldContent = oldContent + line + System.lineSeparator();

                line = reader.readLine();
            }

            //Replacing oldString with newString in the oldContent

            String newContent = removeDeps(oldContent);
            System.out.println(newContent);
            //Rewriting the input text file with newContent

            writer = new FileWriter(file);

            writer.write(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //Closing the resources

                reader.close();

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String removeDeps(String oldContent) {
        String[] lines = oldContent.split(System.lineSeparator());
        List<Integer> points = new ArrayList<>();
        boolean pendingRemoval = false;
        String element = "";
        int counter = 0;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.contains("@Deprecated")) {
                points.add(i);
                pendingRemoval = true;
            }
            if (pendingRemoval) {
                if (element.equals("MET")) {
                    if (line.endsWith("{")) {
                        counter++;
                    }
                    if (line.endsWith("}")) {
                        counter--;
                    }
                    if (counter == 0) {
                        points.add(i);
                        pendingRemoval = false;
                        element = "";
                    }
                } else {
                    if (line.contains("class")) {
                        points.add(lines.length - 1);
                        pendingRemoval = false;
                    } else if (line.endsWith("{") && line.contains("(") && line.contains(")")) {
                        element = "MET";
                        counter++;
                    } else if (line.endsWith(";")) {
                        points.add(i);
                        pendingRemoval = false;
                    }
                }

            }
        }

        for (int i = 0; i < points.size(); i += 2) {
            Integer start = points.get(i);
            Integer end = points.get(i + 1);

            for (int x = start; x >= 0 && lines[x] != null && lines[x].contains("@"); x--) {
                lines[x] = null;
            }

            for (int j = start; j <= end; j++) {
                lines[j] = null;
            }
        }

        return Arrays.stream(lines).filter(Objects::nonNull).collect(Collectors.joining(System.lineSeparator()));
    }
}
