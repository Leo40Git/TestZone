package adudecalledleo.testzone.tests;

import adudecalledleo.testzone.node.BinaryNode;
import adudecalledleo.testzone.node.UnaryNode;

import java.util.Iterator;
import java.util.Scanner;

public class NodeTest extends Test {
    @Override
    public String getName() {
        return "nodes";
    }

    @Override
    public void run(Scanner in) {
        UnaryNode<String> unaryHead = buildListU("a", "b", "c", "d");
        System.out.println("Unary:");
        printIterator(unaryHead.iterator());
        BinaryNode<String> binaryHead = buildListB("A1", "B2", "C3", "D4");
        System.out.println("Binary (H->T):");
        printIterator(binaryHead.iteratorNext());
        BinaryNode<String> binaryTail = binaryHead;
        while (binaryTail.hasNext())
            binaryTail = binaryTail.getNext();
        System.out.println("Binary (T->H):");
        printIterator(binaryTail.iteratorPrev());
    }

    @SafeVarargs
    private static <T> UnaryNode<T> buildListU(T... values) {
        if (values == null || values.length == 0)
            return null;
        UnaryNode<T> head = new UnaryNode<>(values[0]);
        UnaryNode<T> last = head;
        for (int i = 1; i < values.length; i++) {
            UnaryNode<T> next = new UnaryNode<>(values[i]);
            last.setNext(next);
            last = next;
        }
        return head;
    }

    @SafeVarargs
    private static <T> BinaryNode<T> buildListB(T... values) {
        if (values == null || values.length == 0)
            return null;
        BinaryNode<T> head = new BinaryNode<>(values[0]);
        BinaryNode<T> last = head;
        for (int i = 1; i < values.length; i++) {
            BinaryNode<T> next = new BinaryNode<>(last, values[i], null);
            last.setNext(next);
            last = next;
        }
        return head;
    }

    private static <T> void printIterator(Iterator<T> it) {
        while (it.hasNext())
            System.out.println(" - " + it.next());
    }
}
