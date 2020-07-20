package adudecalledleo.testzone.property.indexed;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface IndexedProperty<T> {
    int size();
    T get(int index);

    @FunctionalInterface
    interface Visitor<T> {
        void visit(int index, Supplier<T> getter);
    }

    default void visit(Visitor<T> visitor) {
        for (int i = 0; i < size(); i++) {
            int finalI = i;
            visitor.visit(i, () -> get(finalI));
        }
    }

    default List<T> getAll(int from, int to) {
        return IntStream.range(from, to).mapToObj(this::get).collect(Collectors.toList());
    }
}
