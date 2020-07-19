package adudecalledleo.testzone.property;

public interface MutableProperty<T> extends Property<T> {
    void set(T value);

    default T getAndSet(T newValue) {
        T value = get();
        set(newValue);
        return value;
    }
}
