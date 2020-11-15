package adudecalledleo.testzone.holder;

@FunctionalInterface
public interface LongHolder extends DoubleHolder {
    static LongHolder create(long value) {
        return new LongHolder() {
            private final long _value = value;
            @Override
            public long getLong() {
                return _value;
            }
        };
    }

    long getLong();

    @Override
    default double getDouble() {
        return getLong();
    }
}
