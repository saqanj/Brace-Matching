/**
 * This class has an unmatched brace.
 */
public class UnmatchedBrace {
    int arr[] = new int[25];

    public UnmatchedBrace() {
        init();
    }

    public void init() {
        for (int k = 0; k < arr.length; k++) {
            arr[k} = 10;
        }
    }
}
