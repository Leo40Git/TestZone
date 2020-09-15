package adudecalledleo.testzone.expression;

import adudecalledleo.util.BigDecimalSqrt;

import java.math.BigDecimal;
import java.util.function.Function;

public final class UnaryOperator implements Operator {
    private final java.util.function.UnaryOperator<BigDecimal> evalFunction;
    private final Function<Expression, String> reprFunction;

    public UnaryOperator(java.util.function.UnaryOperator<BigDecimal> evalFunction, Function<Expression, String> reprFunction) {
        this.evalFunction = evalFunction;
        this.reprFunction = reprFunction;
    }

    @Override
    public int paramCount() {
        return 1;
    }

    @Override
    public Expression express(Expression... params) {
        return new CachingExpression() {
            private final Expression param = params[0];

            @Override
            protected BigDecimal evaluate0() {
                return evalFunction.apply(param.evaluate());
            }

            @Override
            protected String represent0() {
                return reprFunction.apply(param);
            }
        };
    }

    public static final UnaryOperator ABS = new UnaryOperator(BigDecimal::abs, param -> "|" + param.represent() + "|");
    public static final UnaryOperator NEGATE = new UnaryOperator(BigDecimal::negate, param -> "-(" + param.represent() + ")");
    public static final UnaryOperator SQUARE = new UnaryOperator(bigDecimal -> bigDecimal.pow(2), param -> param.represent() + "^2");
    public static final UnaryOperator SQRT = new UnaryOperator(BigDecimalSqrt::sqrt, param -> "sqrt(" + param.represent() + ")");
}
