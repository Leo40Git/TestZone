package adudecalledleo.testzone.collection;

import java.util.function.Consumer;

public final class Stacks {
    private Stacks() { }

    public static <T> void forEach(Stack<T> stack, Consumer<T> action) {
        if (stack.isEmpty())
            return;
        Stack<T> temp = new LinkedStack<>();
        while (!stack.isEmpty()) {
            T value = stack.pop();
            action.accept(value);
            temp.push(value);
        }
        while (!temp.isEmpty())
            stack.push(temp.pop());
    }

    public static <T> void forEachReversed(Stack<T> stack, Consumer<T> action) {
        if (stack.isEmpty())
            return;
        Stack<T> temp = new LinkedStack<>();
        while (!stack.isEmpty())
            temp.push(stack.pop());
        while (!temp.isEmpty()) {
            T value = temp.pop();
            action.accept(value);
            stack.push(value);
        }
    }

    public static <T> Stack<T> copy(Stack<T> original) {
        Stack<T> copy = new LinkedStack<>();
        forEachReversed(original, copy::push);
        return copy;
    }

    public static <T> Stack<T> flip(Stack<T> original) {
        Stack<T> flipped = new LinkedStack<>();
        forEach(original, flipped::push);
        return flipped;
    }
}
