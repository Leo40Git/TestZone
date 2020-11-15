package adudecalledleo.testzone.holder;

@FunctionalInterface
public interface FloatHolder extends DoubleHolder {
    static FloatHolder create(float value) {
        return new FloatHolder() {
            private final float _value = value;
            @Override
            public float getFloat() {
                return _value;
            }
        };
    }

    float getFloat();

    @Override
    default double getDouble() {
        return getFloat();
    }
}
