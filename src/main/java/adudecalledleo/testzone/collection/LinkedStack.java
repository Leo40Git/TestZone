package adudecalledleo.testzone.collection;

import adudecalledleo.testzone.node.UnaryNode;

public class LinkedStack<T> implements Stack<T> {
    private UnaryNode<T> head;

    @Override
    public void push(T value) {
        head = new UnaryNode<>(value, head);
    }

    @Override
    public T peek() {
        if (head == null)
            return null;
        return head.getValue();
    }

    @Override
    public T pop() {
        if (head == null)
            return null;
        T value = head.getValue();
        head = head.getNext();
        return value;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }
}
