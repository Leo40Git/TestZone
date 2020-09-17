package adudecalledleo.testzone.expression;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Objects;

public final class ConstantExpression implements Expression {
    private static final HashMap<BigDecimal, ConstantExpression> POOL = new HashMap<>();

    public static ConstantExpression of(BigDecimal constant) {
        Objects.requireNonNull(constant, "Constant cannot be null");
        return POOL.computeIfAbsent(constant, ConstantExpression::new);
    }

    public static ConstantExpression of(double constant) {
        return of(BigDecimal.valueOf(constant));
    }

    public static final ConstantExpression ZERO = of(BigDecimal.ZERO);
    public static final ConstantExpression ONE = of(BigDecimal.ONE);
    public static final ConstantExpression TEN = of(BigDecimal.TEN);

    private final BigDecimal constant;

    private ConstantExpression(BigDecimal constant) {
        this.constant = constant;
    }

    @Override
    public BigDecimal evaluate() {
        return constant;
    }

    @Override
    public String represent() {
        return constant.toString();
    }

    public ConstantExpression intern() {
        return POOL.computeIfAbsent(constant, bigDecimal -> this);
    }

    @Override
    public String toString() {
        return represent();
    }
}
