package adudecalledleo.testzone.property.indexed;

import adudecalledleo.testzone.property.MutableProperty;
import adudecalledleo.testzone.property.Property;

import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.function.IntFunction;
import java.util.function.ObjIntConsumer;

public final class IndexedProperties {
    private IndexedProperties() {
        throw new RuntimeException("lolno");
    }

    private static class FunctionBackedIndexedProperty<T> implements IndexedProperty<T> {
        private final int size;
        private final IntFunction<T> getter;

        private FunctionBackedIndexedProperty(int size, IntFunction<T> getter) {
            this.size = size;
            this.getter = getter;
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public T get(int index) {
            return getter.apply(index);
        }
    }

    public static <T> IndexedProperty<T> create(int size, IntFunction<T> getter) {
        return new FunctionBackedIndexedProperty<>(size, getter);
    }

    private static class FCBackedMutableIndexedProperty<T> extends FunctionBackedIndexedProperty<T>
            implements MutableIndexedProperty<T> {
        private final ObjIntConsumer<T> setter;

        private FCBackedMutableIndexedProperty(int size, IntFunction<T> getter, ObjIntConsumer<T> setter) {
            super(size, getter);
            this.setter = setter;
        }

        @Override
        public void set(int index, T value) {
            setter.accept(value, index);
        }
    }

    public static <T> MutableIndexedProperty<T> createMutable(int size, IntFunction<T> getter, ObjIntConsumer<T> setter) {
        return new FCBackedMutableIndexedProperty<>(size, getter, setter);
    }

    private static class ArrayBackedIndexedProperty<T> implements IndexedProperty<T> {
        protected final T[] array;

        private ArrayBackedIndexedProperty(T[] array) {
            this.array = array;
        }

        @Override
        public int size() {
            return array.length;
        }

        @Override
        public T get(int index) {
            return array[index];
        }
    }

    public static <T> IndexedProperty<T> wrapArray(T[] array) {
        return new ArrayBackedIndexedProperty<>(array);
    }

    private static class ArrayBackedMutableIndexedProperty<T> extends ArrayBackedIndexedProperty<T>
            implements MutableIndexedProperty<T> {
        private ArrayBackedMutableIndexedProperty(T[] array) {
            super(array);
        }

        @Override
        public void set(int index, T value) {
            array[index] = value;
        }
    }

    public static <T> MutableIndexedProperty<T> wrapArrayMutable(T[] array) {
        return new ArrayBackedMutableIndexedProperty<>(array);
    }

    private static class AtomicArrayBackedIndexedProperty<T> implements IndexedProperty<T> {
        protected final AtomicReferenceArray<T> array;

        private AtomicArrayBackedIndexedProperty(AtomicReferenceArray<T> array) {
            this.array = array;
        }

        @Override
        public int size() {
            return array.length();
        }

        @Override
        public T get(int index) {
            return array.get(index);
        }
    }

    public static <T> IndexedProperty<T> wrapAtomicArray(AtomicReferenceArray<T> array) {
        return new AtomicArrayBackedIndexedProperty<>(array);
    }

    private static class AtomicArrayBackedMutableIndexedProperty<T> extends AtomicArrayBackedIndexedProperty<T>
            implements MutableIndexedProperty<T> {
        private AtomicArrayBackedMutableIndexedProperty(AtomicReferenceArray<T> array) {
            super(array);
        }

        @Override
        public void set(int index, T value) {
            array.set(index, value);
        }

        @Override
        public T getAndSet(int index, T newValue) {
            return array.getAndSet(index, newValue);
        }
    }

    public static <T> MutableIndexedProperty<T> wrapAtomicArrayMutable(AtomicReferenceArray<T> array) {
        return new AtomicArrayBackedMutableIndexedProperty<>(array);
    }

    private static class CombinedIndexedProperty<T> implements IndexedProperty<T> {
        protected final Property<T>[] properties;

        private CombinedIndexedProperty(Property<T>[] properties) {
            this.properties = properties;
        }

        @Override
        public int size() {
            return properties.length;
        }

        @Override
        public T get(int index) {
            return properties[index].get();
        }
    }

    @SafeVarargs
    public static <T> IndexedProperty<T> combine(Property<T>... properties) {
        return new CombinedIndexedProperty<>(properties);
    }

    private static class CombinedMutableIndexedProperty<T> extends CombinedIndexedProperty<T> implements MutableIndexedProperty<T> {
        private CombinedMutableIndexedProperty(Property<T>[] properties) {
            super(properties);
        }

        @Override
        public void set(int index, T value) {
            Property<T> prop = properties[index];
            if (prop instanceof MutableProperty)
                ((MutableProperty<T>) prop).set(value);
            else
                throw new UnsupportedOperationException("Cannot set value at index " + index + "!");
        }
    }

    @SafeVarargs
    public static <T> MutableIndexedProperty<T> combineMutable(Property<T>... properties) {
        return new CombinedMutableIndexedProperty<>(properties);
    }
}
