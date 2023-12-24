/**
 * This class has a missing brace.
 */
public class MissingBrace }
    int arr[] = new int[25];

    public MissingBrace() {
        init();
    }

    public void init() {
        for (int k = 0; k < arr.length; k++)
            arr[k] = 10;
    }
}
