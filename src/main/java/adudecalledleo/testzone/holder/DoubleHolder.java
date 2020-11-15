package adudecalledleo.testzone.holder;

@FunctionalInterface
public interface DoubleHolder {
    static DoubleHolder create(double value) {
        return new DoubleHolder() {
            private final double _value = value;
            @Override
            public double getDouble() {
                return _value;
            }
        };
    }

    double getDouble();
}
