package adudecalledleo.testzone.tests;

import adudecalledleo.testzone.collection.*;

import java.util.Scanner;

public class CollectionTest extends Test {
    @Override
    public String getName() {
        return "collections";
    }

    @Override
    public void run(Scanner in) {
        Stack<String> stack = new LinkedStack<>();
        stack.push("A1");
        stack.push("B2");
        stack.push("C3");
        stack.push("D4");
        Stack<String> stack2 = Stacks.flip(stack);
        System.out.println("Stack:");
        while (!stack.isEmpty())
            System.out.println(" - " + stack.pop());
        System.out.println("Flipped Stack:");
        while (!stack2.isEmpty())
            System.out.println(" - " + stack2.pop());
        Queue<String> queue = new LinkedQueue<>();
        queue.add("A1");
        queue.add("B2");
        queue.add("C3");
        queue.add("D4");
        Queue<String> queue2 = Queues.copy(queue);
        System.out.println("Queue:");
        while (!queue.isEmpty())
            System.out.println(" - " + queue.remove());
        System.out.println("Copied Queue:");
        while (!queue2.isEmpty())
            System.out.println(" - " + queue2.remove());
    }
}
