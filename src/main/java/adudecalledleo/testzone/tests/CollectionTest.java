package adudecalledleo.testzone.tests;

import adudecalledleo.testzone.TerminalInterface;
import adudecalledleo.testzone.collection.LinkedQueue;
import adudecalledleo.testzone.collection.LinkedStack;
import adudecalledleo.testzone.collection.Queue;
import adudecalledleo.testzone.collection.Stack;

public class CollectionTest extends Test {
    @Override
    public String getName() {
        return "collections";
    }

    @Override
    public void run(TerminalInterface ti) {
        Stack<String> stack = new LinkedStack<>();
        stack.push("A1");
        stack.push("B2");
        stack.push("C3");
        stack.push("D4");
        System.out.println("Stack:");
        while (!stack.isEmpty())
            System.out.println(" - " + stack.pop());
        Queue<String> queue = new LinkedQueue<>();
        queue.add("A1");
        queue.add("B2");
        queue.add("C3");
        queue.add("D4");
        System.out.println("Queue:");
        while (!queue.isEmpty())
            System.out.println(" - " + queue.remove());
    }
}
