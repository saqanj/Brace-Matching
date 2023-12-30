import java.nio.file.Path;

/**
 * Test class for the CheckBraces method.
 * 
 * @author Jonathan Johnson & ZyBooks
 * @version 1.0.0
 */
public class TestCheckBraces {

    /**
     * Command line entry point.
     * 
     * @param commandLineArguments Expecting 1 parameter, the path to a valid file.
     */
    public static void main(String... commandLineArguments) {
        if (commandLineArguments.length != 1) {
            System.out.println("Usage: java TestCheckBraces sourcefile");
            System.exit(1); // CLI apps exit with a non zero exit code on error
        }

        CheckBraces checker = new CheckBraces();
        System.out.println("Check the contents of " + commandLineArguments[0] + ":");
        System.out.println(checker.load(Path.of(commandLineArguments[0])));
        System.out.println(checker.check());
    }
}
