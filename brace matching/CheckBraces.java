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
 * 
 * @author Saqlain Anjum
 * @version 1.0.0
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
     * an error message if there are any such issues during checking.
     * 
     * @return The return message if use of brackets is valid within the
     *         given document.
     */
    public String check() {
        Stack<Character> symbols = new ArrayStack<>();
        String returnMessage = "";
        boolean openingError = false;

        for (int i = 0; i < context.length(); i++) {
            char currentChar = context.charAt(i);

            if (isPusher(currentChar)) {
                symbols.push(currentChar);
            } else if (isClosingBracket(currentChar)) {
                if (symbols.isEmpty() || !matchesTopBracket(symbols, currentChar)) {
                    handleBracketError(i, currentChar, symbols);
                    openingError = true;
                    break;
                } else {
                    symbols.pop();
                }
            }
        }

        if (symbols.isEmpty() && !openingError) {
            returnMessage = ALL_MATCH;
        }

        return returnMessage;
    }

    /**
     * Utility method to check if a given character requires pushing from the stack.
     * 
     * @param c given character
     * @return true if push is necessary and false otherwise.
     */
    private boolean isPusher(char c) {
        return c == '[' || c == '(' || c == '{';
    }

    /**
     * Utility method to check if a given character requires a closing bracket.
     * 
     * @param c given character.
     * @return true if closing bracket is necessary and false otherwise.
     */
    private boolean isClosingBracket(char c) {
        return c == ']' || c == ')' || c == '}';
    }

    /**
     * Utility method to check if a given character matches the top bracket in the
     * symbols Array-Stack.
     * 
     * @param symbols the Array-Stack.
     * @param c       the given character.
     * @return true if matches and false otherwise.
     */
    private boolean matchesTopBracket(Stack<Character> symbols, char c) {
        char top = symbols.top();
        return (c == ']' && top == '[') || (c == ')' && top == '(') || (c == '}' && top == '{');
    }

    /**
     * Update method for handeling any bracket errors.
     * 
     * @param index   given index of Array-Stack where error is occuring.
     * @param c       given character where error is occuring.
     * @param symbols the Array-Stack.
     */
    private void handleBracketError(int index, char c, Stack<Character> symbols) {
        if (symbols.isEmpty()) {
            System.out.format(NO_OPENING_BRACE, index, c);
        } else {
            System.out.format(UNEXPECTED_BRACE, index, c, symbols.top());
        }
    }

}
