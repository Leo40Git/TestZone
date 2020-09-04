package adudecalledleo.testzone.property.indexed;

import java.util.List;
import java.util.function.BiFunction;
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

    default void mutateAll(int start, int end, BiFunction<Integer, T, T> mutator) {
        if (end < start) {
            int tmp = end;
            end = start;
            start = tmp;
        }
        start = Math.max(start, 0);
        end = Math.min(end, size());
        int finalStart = start;
        int finalEnd = end;
        visitMutable((index, getter, setter) -> {
            if (index < finalStart || index >= finalEnd)
                return;
            setter.accept(mutator.apply(index, getter.get()));
        });
    }

    default void mutateAll(BiFunction<Integer, T, T> mutator) {
        mutateAll(0, size(), mutator);
    }

    default void setAll(int start, int end, List<T> values) {
        mutateAll(start, end, (index, value) -> values.get(index));
    }

    default void setAll(List<T> values) {
        setAll(0, size(), values);
    }
}
