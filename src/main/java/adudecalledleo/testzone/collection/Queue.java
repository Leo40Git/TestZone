package adudecalledleo.testzone.collection;

public interface Queue<T> {
    void add(T value);
    T head();
    T remove();
    boolean isEmpty();
    void clear();
}
