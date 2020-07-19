package adudecalledleo.testzone.property;

import adudecalledleo.testzone.property.indexed.IndexedProperty;
import adudecalledleo.testzone.property.indexed.MutableIndexedProperty;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class Properties {
    private Properties() {
        throw new RuntimeException("lolno");
    }

    private static class ConstantProperty<T> implements Property<T> {
        private final T value;

        private ConstantProperty(T value) {
            this.value = value;
        }

        @Override
        public T get() {
            return value;
        }
    }

    public static <T> Property<T> constant(T value) {
        return new ConstantProperty<>(value);
    }

    private static class WrappingMutableProperty<T> implements MutableProperty<T> {
        private T value;

        private WrappingMutableProperty(T value) {
            this.value = value;
        }

        @Override
        public T get() {
            return value;
        }

        @Override
        public void set(T value) {
            this.value = value;
        }
    }

    public static <T> MutableProperty<T> wrap(T value) {
        return new WrappingMutableProperty<>(value);
    }

    private static class SupplierBackedProperty<T> implements Property<T> {
        private final Supplier<T> getter;

        private SupplierBackedProperty(Supplier<T> getter) {
            this.getter = getter;
        }

        @Override
        public T get() {
            return getter.get();
        }
    }

    public static <T> Property<T> create(Supplier<T> getter) {
        return new SupplierBackedProperty<>(getter);
    }

    private static class LazySupplierBackedProperty<T> extends SupplierBackedProperty<T> {
        private T value;

        private LazySupplierBackedProperty(Supplier<T> getter) {
            super(getter);
        }

        @Override
        public T get() {
            if (value == null)
                value = super.get();
            return value;
        }
    }

    public static <T> Property<T> createLazy(Supplier<T> getter) {
        return new LazySupplierBackedProperty<>(getter);
    }

    private static class SCPairBackedMutableProperty<T> extends SupplierBackedProperty<T> implements MutableProperty<T> {
        private final Consumer<T> setter;

        private SCPairBackedMutableProperty(Supplier<T> getter, Consumer<T> setter) {
            super(getter);
            this.setter = setter;
        }

        @Override
        public void set(T value) {
            setter.accept(value);
        }
    }

    public static <T> MutableProperty<T> createMutable(Supplier<T> getter, Consumer<T> setter) {
        return new SCPairBackedMutableProperty<>(getter, setter);
    }

    private static class AtomicBackedProperty<T> implements Property<T> {
        protected final AtomicReference<T> reference;

        private AtomicBackedProperty(AtomicReference<T> reference) {
            this.reference = reference;
        }

        @Override
        public T get() {
            return reference.get();
        }
    }

    public static <T> Property<T> createAtomicBacked(AtomicReference<T> reference) {
        return new AtomicBackedProperty<>(reference);
    }

    private static class AtomicBackedMutableProperty<T> extends AtomicBackedProperty<T> implements MutableProperty<T> {
        private AtomicBackedMutableProperty(AtomicReference<T> reference) {
            super(reference);
        }

        @Override
        public void set(T value) {
            reference.set(value);
        }

        @Override
        public T getAndSet(T newValue) {
            return reference.getAndSet(newValue);
        }
    }

    public static <T> MutableProperty<T> createAtomicBackedMutable(AtomicReference<T> reference) {
        return new AtomicBackedMutableProperty<>(reference);
    }

    private static class IndexedBackedProperty<T> implements Property<T> {
        private final IndexedProperty<T> delegate;
        protected final int index;

        private IndexedBackedProperty(IndexedProperty<T> delegate, int index) {
            this.delegate = delegate;
            this.index = index;
        }

        @Override
        public T get() {
            return delegate.get(index);
        }
    }

    public static <T> Property<T> isolateFromIndexed(IndexedProperty<T> delegate, int index) {
        return new IndexedBackedProperty<>(delegate, index);
    }

    private static class IndexedBackedMutableProperty<T> extends IndexedBackedProperty<T> implements MutableProperty<T> {
        private final MutableIndexedProperty<T> delegateMutable;

        private IndexedBackedMutableProperty(MutableIndexedProperty<T> delegate, int index) {
            super(delegate, index);
            delegateMutable = delegate;
        }

        @Override
        public void set(T value) {
            delegateMutable.set(index, value);
        }
    }

    public static <T> MutableProperty<T> isolateMutableFromIndexed(MutableIndexedProperty<T> delegate, int index) {
        return new IndexedBackedMutableProperty<>(delegate, index);
    }
}
