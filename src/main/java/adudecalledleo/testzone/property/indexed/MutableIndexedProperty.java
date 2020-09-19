package adudecalledleo.testzone.property.indexed;

public interface MutableIndexedProperty<T> extends IndexedProperty<T> {
    void set(int i, T value);

    default T getAndSet(int i, T newValue) {
        T value = get(i);
        set(i, newValue);
        return value;
    }
}
