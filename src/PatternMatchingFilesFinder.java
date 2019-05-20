import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author: Muhammadjon Hakimov
 * created: 20.05.2019 18:49:17
 */

class PatternMatchingFilesFinder {
    public String directory;
    public String pattern;

    PatternMatchingFilesFinder(String directory, String pattern) {
        this.directory = directory;
        this.pattern = pattern;
    }

    public void printFileName(String fileName) {
        System.out.println(fileName.substring(Main.mainDirectory.length())
                .replace(System.getProperty("file.separator"), "/"));
    }

    public void printErrorMessage(String directoryName) {
        System.err.println("Some error occurred while opening directory: " + directoryName +
                " - this directory doesn't exist or access to it is denied!");
    }

    void findMatchingFiles() {
        try {
            Files.walkFileTree(Paths.get(directory), new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult preVisitDirectory(Path currentDirectory, BasicFileAttributes attributes) {
                    try (DirectoryStream<Path> stream = Files.newDirectoryStream(currentDirectory, pattern)) {
                        for (Path entry : stream) {
                            printFileName(entry.toAbsolutePath().toString());
                        }
                    } catch (IOException e) {
                        printErrorMessage(currentDirectory.getFileName().toString());
                        System.exit(1);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            printErrorMessage(directory);
            System.exit(1);
        }
    }
}
