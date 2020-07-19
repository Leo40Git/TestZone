package adudecalledleo.testzone.property.indexed;

import java.util.function.Consumer;
import java.util.function.Supplier;

public interface MutableIndexedProperty<T> extends IndexedProperty<T> {
    void set(int index, T value);

    default T getAndSet(int index, T newValue) {
        T value = get(index);
        set(index, newValue);
        return value;
    }

    @FunctionalInterface
    interface MutableVisitor<T> {
        void visit(int index, Supplier<T> getter, Consumer<T> setter);
    }

    default void visitMutable(MutableVisitor<T> mutableVisitor) {
        for (int i = 0; i < size(); i++) {
            int finalI = i;
            mutableVisitor.visit(i, () -> get(finalI), value -> set(finalI, value));
        }
    }
}