package adudecalledleo.testzone.property.indexed;

import java.util.ArrayList;
import java.util.List;
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

    default List<T> getAll(int start, int end) {
        if (end < start) {
            int tmp = end;
            end = start;
            start = tmp;
        }
        start = Math.max(start, 0);
        end = Math.min(end, size());
        int finalStart = start;
        int finalEnd = end;
        List<T> list = new ArrayList<>(finalEnd - finalStart);
        visit((index, getter) -> {
            if (index < finalStart || index >= finalEnd)
                return;
            list.add(index, getter.get());
        });
        return list;
    }

    default List<T> getAll() {
        return getAll(0, size());
    }
}
