package adudecalledleo.testzone.node;

public abstract class Node<T> {
    protected T value;

    public Node(T value) {
        this.value = value;
    }

    public Node() {
        this(null);
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
