package adudecalledleo.testzone.node;

import java.util.Iterator;

public class UnaryNode<T> extends Node<T> implements Iterable<T> {
    protected UnaryNode<T> next;

    public UnaryNode(T value, UnaryNode<T> next) {
        super(value);
        this.next = next;
    }

    public UnaryNode(T value) {
        this(value, null);
    }

    public UnaryNode(UnaryNode<T> next) {
        this(null, next);
    }

    public UnaryNode() {
        this(null, null);
    }

    public boolean hasNext() {
        return next != null;
    }

    public UnaryNode<T> getNext() {
        return next;
    }

    public void setNext(UnaryNode<T> next) {
        this.next = next;
    }

    private static class UnaryNodeIterator<T> implements Iterator<T> {
        private UnaryNode<T> current;

        private UnaryNodeIterator(UnaryNode<T> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            UnaryNode<T> previous = current;
            current = current.getNext();
            return previous.value;
        }
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public Iterator<T> iterator() {
        return new UnaryNodeIterator<>(this);
    }
}
