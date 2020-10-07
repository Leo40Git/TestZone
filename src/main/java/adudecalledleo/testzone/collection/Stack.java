package adudecalledleo.testzone.collection;

public interface Stack<T> {
    void push(T value);
    T peek();
    T pop();
    boolean isEmpty();
}
