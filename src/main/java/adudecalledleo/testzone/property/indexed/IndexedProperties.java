package adudecalledleo.testzone.property.indexed;

import adudecalledleo.testzone.property.MutableProperty;
import adudecalledleo.testzone.property.Property;

import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.function.BiConsumer;
import java.util.function.IntFunction;

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
        private final BiConsumer<Integer, Object> setter;

        private FCBackedMutableIndexedProperty(int size, IntFunction<T> getter, BiConsumer<Integer, Object> setter) {
            super(size, getter);
            this.setter = setter;
        }

        @Override
        public void set(int index, T value) {
            setter.accept(index, value);
        }
    }

    public static <T> MutableIndexedProperty<T> createMutable(int size, IntFunction<T> getter, BiConsumer<Integer, Object> setter) {
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

    private static class ListBackedIndexedProperty<T> implements IndexedProperty<T> {
        protected final List<T> delegate;
        protected final List<T> unmodView;

        private ListBackedIndexedProperty(List<T> delegate) {
            this.delegate = delegate;
            unmodView = Collections.unmodifiableList(delegate);
        }

        @Override
        public int size() {
            return delegate.size();
        }

        @Override
        public T get(int index) {
            return delegate.get(index);
        }

        @Override
        public List<T> getAll(int start, int end) {
            return delegate.subList(start, end);
        }

        @Override
        public List<T> getAll() {
            return unmodView;
        }
    }

    public static <T> IndexedProperty<T> wrapList(List<T> delegate) {
        return new ListBackedIndexedProperty<>(delegate);
    }

    private static class ListBackedMutableIndexedProperty<T> extends ListBackedIndexedProperty<T> implements MutableIndexedProperty<T> {
        private ListBackedMutableIndexedProperty(List<T> delegate) {
            super(delegate);
        }

        @Override
        public void set(int index, T value) {
            delegate.set(index, value);
        }
    }

    public static <T> IndexedProperty<T> wrapListMutable(List<T> delegate) {
        return new ListBackedMutableIndexedProperty<>(delegate);
    }

    private static class ListBackedResizableIndexedProperty<T> extends ListBackedMutableIndexedProperty<T> implements ResizableIndexedProperty<T> {
        private ListBackedResizableIndexedProperty(List<T> delegate) {
            super(delegate);
        }

        @Override
        public void resizeTo(int size) {
            if (size == 0) {
                delegate.clear();
            } else if (size < delegate.size()) {
                int i = delegate.size() - size;
                for (; i > 0; i--)
                    delegate.remove(delegate.size() - 1);
            } else if (size > delegate.size()) {
                int i = size - delegate.size();
                for (; i > 0; i--)
                    delegate.add(null);
            }
        }

        @Override
        public void add(T value) {
            delegate.add(value);
        }

        @Override
        public void remove(int index) {
            delegate.remove(index);
        }

        @Override
        public void remove(T value) {
            delegate.remove(value);
        }

        @Override
        public void clear() {
            delegate.clear();
        }
    }

    public static <T> ResizableIndexedProperty<T> wrapListResizable(List<T> delegate) {
        return new ListBackedResizableIndexedProperty<>(delegate);
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

    private static class BitSetIndexedProperty implements IndexedProperty<Boolean> {
        protected final BitSet bitSet;

        private BitSetIndexedProperty(BitSet bitSet) {
            this.bitSet = bitSet;
        }

        @Override
        public int size() {
            return bitSet.size();
        }

        @Override
        public Boolean get(int index) {
            return bitSet.get(index);
        }
    }

    public static IndexedProperty<Boolean> fromBitSet(BitSet bitSet) {
        return new BitSetIndexedProperty(bitSet);
    }

    private static class BitSetMutableIndexedProperty extends BitSetIndexedProperty
            implements MutableIndexedProperty<Boolean> {
        private BitSetMutableIndexedProperty(BitSet bitSet) {
            super(bitSet);
        }

        @Override
        public void set(int index, Boolean value) {
            bitSet.set(index, value);
        }
    }

    public static MutableIndexedProperty<Boolean> fromBitSetMutable(BitSet bitSet) {
        return new BitSetMutableIndexedProperty(bitSet);
    }

    private static class StringCharIndexedProperty implements IndexedProperty<Character> {
        private final char[] chars;

        private StringCharIndexedProperty(String string) {
            this.chars = string.toCharArray();
        }

        @Override
        public int size() {
            return chars.length;
        }

        @Override
        public Character get(int index) {
            return chars[index];
        }
    }

    public static IndexedProperty<Character> fromString(String string) {
        return new StringCharIndexedProperty(string);
    }
}
