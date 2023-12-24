/**
 * Implementation of the stack ADT using a fixed-length array. All operations
 * are performed in constant time. An exception is thrown if a push operation
 * is attempted when the size of the stack is equal to the length of the
 * array.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public class ArrayStack<E> implements Stack<E> {

    /** Default array capacity. */
    private static final int CAPACITY = 1000;

    /** Index to top when array empty.  */
    private static final int EMPTY = -1;

    /** Generic array used for storage of stack elements. */
    private E[] data;

    /** Index of the top element of the stack in the array. */
    private int top = EMPTY;

    /** Constructs an empty stack using the default array capacity. */
    public ArrayStack() {
        this(CAPACITY);
    }

    /**
     * Constructs and empty stack with the given array capacity.
     * 
     * @param capacity length of the underlying array
     */
    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        data = (E[]) new Object[capacity]; // safe cast
    }

    /**
     * Returns the number of elements in the stack.
     * 
     * @return number of elements in the stack
     */
    public int size() {
        return top + 1;
    }

    /**
     * Tests whether the stack is empty.
     * 
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return top == EMPTY;
    }

    /**
     * Inserts an element at the top of the stack.
     * 
     * @param element the element to be inserted
     * @throws IllegalStateException if the array storing the elements is full
     */
    public void push(E element) throws IllegalStateException {
        if (size() == data.length) {
            throw new IllegalStateException("The Stack is full and cannot add more elements.");
        }

        data[++top] = element;
    }

    /**
     * Returns, but does not remove, the element at the top of the stack.
     * 
     * @return top element in the stack (or null if empty)
     */
    public E top() {
        if (isEmpty()) {
            return null;
        }

        return data[top];
    }

    /**
     * Removes and returns the top element from the stack.
     * 
     * @return element removed (or null if empty)
     */
    public E pop() {
        if (isEmpty()) {
            return null;
        }

        E answer = data[top];
        data[top] = null; // dereference for garbage collection
        top--;

        return answer;
    }
}
