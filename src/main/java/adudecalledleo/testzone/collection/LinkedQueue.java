package adudecalledleo.testzone.collection;

import adudecalledleo.testzone.node.UnaryNode;

public class LinkedQueue<T> implements Queue<T> {
    private UnaryNode<T> head, tail;

    @Override
    public void add(T value) {
        UnaryNode<T> temp = new UnaryNode<>(value);
        if (tail == null) {
            head = tail = temp;
            return;
        }
        tail.setNext(temp);
        tail = temp;
    }

    @Override
    public T head() {
        if (head == null)
            return null;
        return head.getValue();
    }

    @Override
    public T remove() {
        if (head == null)
            return null;
        UnaryNode<T> temp = head;
        head = head.getNext();
        if (head == null)
            tail = null;
        return temp.getValue();
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
    }
}
