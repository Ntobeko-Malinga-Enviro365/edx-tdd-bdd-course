import java.util.Arrays;

public class Stack<T> {
    private T[] items;

    public Stack() {
        items = (T[]) new Object[0];
    }

    private void increaseItemsSizeByOne() {
        items = Arrays.copyOf(items, items.length + 1);
    }

    public void push(T item) {
        increaseItemsSizeByOne();
        items[size()-1] = item;
    }

    public T pop() {
        if (size() == 0) {
            throw new EmptyStackException("Stack is empty. There are no items no remove");
        } else {
            // Items array is not empty and can continue doing operations on it
        }
        T lastItemAdded = items[size()-1];
        items = Arrays.copyOf(items, size()-1);
        return lastItemAdded;
    }

    public T peek() {
        if (size() == 0) {
            throw new EmptyStackException("Stack is empty. There are no items no peek");
        } else {
            // items array is not empty, meaning there are items that can be peeked (looked at? -\(- -)/- )
        }
        return items[size()-1];
    }

    public int size() {
        return items.length;
    }
}
