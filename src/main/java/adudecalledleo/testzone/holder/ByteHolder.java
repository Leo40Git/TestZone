package adudecalledleo.testzone.holder;

@FunctionalInterface
public interface ByteHolder extends ShortHolder {
    static ByteHolder create(byte value) {
        return new ByteHolder() {
            private final byte _value = value;
            @Override
            public byte getByte() {
                return _value;
            }
        };
    }

    byte getByte();

    @Override
    default short getShort() {
        return getByte();
    }

    @Override
    default int getInt() {
        return getByte();
    }

    @Override
    default long getLong() {
        return getByte();
    }

    @Override
    default double getDouble() {
        return getByte();
    }

    @Override
    default float getFloat() {
        return getByte();
    }
}
