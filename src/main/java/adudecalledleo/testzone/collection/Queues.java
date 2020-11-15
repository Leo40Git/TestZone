package adudecalledleo.testzone.collection;

import java.util.function.Consumer;

public final class Queues {
    private Queues() { }

    public static <T> void forEach(Queue<T> queue, Consumer<T> action) {
        if (queue.isEmpty())
            return;
        T head = queue.head();
        do {
            T value = queue.remove();
            action.accept(value);
            queue.add(value);
        } while (queue.head() != head);
    }

    public static <T> Queue<T> copy(Queue<T> original) {
        Queue<T> copy = new LinkedQueue<>();
        forEach(original, copy::add);
        return copy;
    }
}
