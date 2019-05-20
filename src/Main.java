import java.util.ArrayList;
import java.util.List;

public class Main {
    static String mainDirectory = System.getProperty("user.dir");

    public static void main(String[] args) {

        List<String> directoriesToSearch = new ArrayList<>();
        String pattern = "*";

        try {
            int len = args.length;
            if (len < 2) {
                throw new IncorrectNumberOfArgumentsException();
            }

            for (int i = 0; i < len - 2; i += 2) {
                if (!args[i].equals("-dir")) {
                    throw new UnknownCommandException(args[i]);
                } else {
                    directoriesToSearch.add(args[i + 1]);
                }
            }

            if (len % 2 == 0) {
                if (!args[len - 2].equals("-pattern")) {
                    throw new UnknownCommandException(args[len - 2]);
                } else {
                    pattern = args[len - 1];
                }
            } else {
                if (!args[len - 1].equals("-pattern")) {
                    throw new UnknownCommandException(args[len - 1]);
                } else {
                    System.err.println("UnknownCommandException: pattern is skipped!");
                    System.exit(1);
                }
            }
        } catch (UnknownCommandException | IncorrectNumberOfArgumentsException e) {
            e.printStackTrace();
            System.exit(1);
        }

        for (String currentDirectory : directoriesToSearch) {
            PatternMatchingFilesFinder finder = new PatternMatchingFilesFinder(mainDirectory
                    + currentDirectory.replace("/", System.getProperty("file.separator")), pattern);
            finder.findMatchingFiles();
        }
    }
}
