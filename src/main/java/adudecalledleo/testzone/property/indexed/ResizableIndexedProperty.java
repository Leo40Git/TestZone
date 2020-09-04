package adudecalledleo.testzone.property.indexed;

import java.util.List;

public interface ResizableIndexedProperty<T> extends MutableIndexedProperty<T> {
    void resizeTo(int size);

    default void add(T value) {
        int i = size();
        resizeTo(i + 1);
        set(i, value);
    }

    default void remove(int index) {
        List<T> allValues = getAll();
        allValues.remove(index);
        resizeTo(size() - 1);
        setAll(allValues);
    }

    default void remove(T value) {
        List<T> allValues = getAll();
        allValues.remove(value);
        resizeTo(size() - 1);
        setAll(allValues);
    }

    default void clear() {
        resizeTo(0);
    }
}
