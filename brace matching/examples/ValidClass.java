/**
 * This is a valid class.
 */
public class ValidClass {
    int arr[] = new int[25];

    public ValidClass() {
        init();
    }

    public void init() {
        for (int k = 0; k < arr.length; k++)
            arr[k] = 10;
    }
}
