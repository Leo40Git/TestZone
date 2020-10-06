package adudecalledleo.testzone.expression;

import adudecalledleo.util.BigDecimalSqrt;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import static adudecalledleo.testzone.expression.Expression.parenString;

public final class UnaryOperation implements Operation {
    private final UnaryOperator<BigDecimal> evalFunction;
    private final Function<Expression, String> reprFunction;

    public UnaryOperation(UnaryOperator<BigDecimal> evalFunction, Function<Expression, String> reprFunction) {
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

    public static final UnaryOperation ABS = new UnaryOperation(BigDecimal::abs, param -> "|" + param.represent() + "|");
    public static final UnaryOperation NEGATE = new UnaryOperation(BigDecimal::negate, param -> "-" + parenString(param.represent()));
    public static final UnaryOperation SQUARE = new UnaryOperation(bigDecimal -> bigDecimal.pow(2), param -> param.represent() + "^2");
    public static final UnaryOperation SQRT = new UnaryOperation(BigDecimalSqrt::sqrt, param -> "sqrt(" + param.represent() + ")");
}
