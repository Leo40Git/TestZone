package adudecalledleo.testzone.holder;

@FunctionalInterface
public interface IntHolder extends LongHolder, FloatHolder {
    static IntHolder create(int value) {
        return new IntHolder() {
            private final int _value = value;
            @Override
            public int getInt() {
                return _value;
            }
        };
    }

    int getInt();

    @Override
    default long getLong() {
        return getInt();
    }

    @Override
    default double getDouble() {
        return getInt();
    }

    @Override
    default float getFloat() {
        return getInt();
    }
}
