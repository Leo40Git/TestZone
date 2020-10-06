package adudecalledleo.testzone.node;

import java.util.Iterator;
import java.util.function.UnaryOperator;

public class BinaryNode<T> extends Node<T> implements Iterable<T> {
    protected BinaryNode<T> prev;
    protected BinaryNode<T> next;

    public BinaryNode(BinaryNode<T> prev, T value, BinaryNode<T> next) {
        super(value);
        this.prev = prev;
        this.next = next;
    }

    public BinaryNode(BinaryNode<T> prev, BinaryNode<T> next) {
        this(prev, null, next);
    }

    public BinaryNode(T value) {
        this(null, value, null);
    }

    public BinaryNode() {
        this(null, null, null);
    }

    public boolean hasPrev() {
        return prev != null;
    }

    public BinaryNode<T> getPrev() {
        return prev;
    }

    public void setPrev(BinaryNode<T> prev) {
        this.prev = prev;
    }

    public boolean hasNext() {
        return next != null;
    }

    public BinaryNode<T> getNext() {
        return next;
    }

    public void setNext(BinaryNode<T> next) {
        this.next = next;
    }

    private static class BinaryNodeIterator<T> implements Iterator<T> {
        private final UnaryOperator<BinaryNode<T>> newFunc;
        private BinaryNode<T> current;

        private BinaryNodeIterator(UnaryOperator<BinaryNode<T>> newFunc, BinaryNode<T> current) {
            this.newFunc = newFunc;
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            BinaryNode<T> old = current;
            current = newFunc.apply(current);
            return old.value;
        }
    }

    public Iterator<T> iteratorNext() {
        return new BinaryNodeIterator<>(BinaryNode::getNext, this);
    }

    public Iterator<T> iteratorPrev() {
        return new BinaryNodeIterator<>(BinaryNode::getPrev, this);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public Iterator<T> iterator() {
        return iteratorNext();
    }
}
