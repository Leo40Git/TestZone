package adudecalledleo.testzone.property.indexed;

import java.util.function.Supplier;

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
}
