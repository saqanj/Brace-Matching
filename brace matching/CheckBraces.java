import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

/**
 * This program reads a text file specified in a command-line argument
 * into a string named inString. It also has a code segment to echo
 * print inString.
 * 
 * To execute, assuming an input file is named filename
 * and exists in the same directory as CheckBraces.class,
 * type: 'java CheckBraces filename'
 */
public class CheckBraces {
    private static final String PUSHERS = "[({";
    private static final String POPPERS = "])}";
    private static final String UNEXPECTED_BRACE = "Invalid: Unmatched brace at character %d: Found %c expecting %c.%n";
    private static final String NO_OPENING_BRACE = "Invalid: Unmatched brace at character %d: No opening brace for %c.%n";
    private static final String ALL_MATCH = "Valid: All the braces match.";

    private String context = "";

    /**
     * Read a file from the given Path into context.
     * 
     * @param source The Path to the file to read.
     * @return The read file contents into a String
     */
    public String load(Path source) {

        // Read the source code file specified as the command-line argument
        try {
            context = Files.readString(source);
        } catch (NoSuchFileException exception) {
            System.err.format("Error: File '%s' was not found in path.%n", source);
            exception.printStackTrace();
            System.exit(2);
        } catch (IOException exception) {
            System.err.format("Error: File '%s' could not be read.%n", source);
            exception.printStackTrace();
            System.exit(3);
        }

        return context;
    }

    /**
     * A source-code checker for missing opening and closing brackets that outputs
     * an error message if there are any such issues during checking. Utilizes
     * complicated boolean logic to ensure that all error possibilities are tested
     * for. Method implemented by Saqlain Anjum.
     * 
     * @return The return message if use of brackets is valid within the
     *         given document.
     */
    public String check() {
        Stack<Character> symbols = new ArrayStack<Character>();
        String returnMessage = "";
        boolean openingError = false;
        for (int i = 0; i < context.length(); i++) {
            Character characterInQuestion = context.charAt(i);
            if (PUSHERS.contains(characterInQuestion.toString())) {
                symbols.push(characterInQuestion);
            } else if ((characterInQuestion == ']' && symbols.isEmpty() == true)
                    || (characterInQuestion == ')' && symbols.isEmpty() == true)
                    || (characterInQuestion == '}' && symbols.isEmpty() == true)) {
                System.out.format(NO_OPENING_BRACE, i, characterInQuestion);
                openingError = true;
                break;
            } else if (((characterInQuestion == ']') && (symbols.top() == '['))
                    || ((characterInQuestion == ')') && (symbols.top() == '('))
                    || ((characterInQuestion == '}') && (symbols.top() == '{'))) {
                symbols.pop();
            } else if (symbols.isEmpty() == false
                    && ((characterInQuestion == ']' && (symbols.top() == '{' || symbols.top() == '('))
                            || (characterInQuestion == ')' && (symbols.top() == '{' || symbols.top() == '['))
                            || (characterInQuestion == '}' && (symbols.top() == '(' || symbols.top() == '[')))) {
                System.out.format(UNEXPECTED_BRACE, i, characterInQuestion, symbols.top());
                break;
            }
        }
        if (symbols.isEmpty() == true && openingError == false) {
            returnMessage = ALL_MATCH;
        }
        return returnMessage;
    }

}
