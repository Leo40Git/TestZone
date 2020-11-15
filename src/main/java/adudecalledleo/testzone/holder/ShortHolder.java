package adudecalledleo.testzone.holder;

@FunctionalInterface
public interface ShortHolder extends IntHolder {
    static ShortHolder create(short value) {
        return new ShortHolder() {
            private final short _value = value;
            @Override
            public short getShort() {
                return _value;
            }
        };
    }

    short getShort();

    @Override
    default int getInt() {
        return getShort();
    }

    @Override
    default long getLong() {
        return getShort();
    }

    @Override
    default double getDouble() {
        return getShort();
    }

    @Override
    default float getFloat() {
        return getShort();
    }
}
