package adudecalledleo.testzone.expression;

import java.math.BigDecimal;
import java.util.function.BiFunction;

import static adudecalledleo.testzone.expression.Expression.parenString;

public final class BinaryOperator implements Operator {
    private final java.util.function.BinaryOperator<BigDecimal> evalFunction;
    private final BiFunction<Expression, Expression, String> reprFunction;

    public BinaryOperator(java.util.function.BinaryOperator<BigDecimal> evalFunction, BiFunction<Expression, Expression, String> reprFunction) {
        this.evalFunction = evalFunction;
        this.reprFunction = reprFunction;
    }

    @Override
    public int paramCount() {
        return 2;
    }

    @Override
    public Expression express(Expression... params) {
        return new CachingExpression() {
            private final Expression param1 = params[0];
            private final Expression param2 = params[1];

            @Override
            protected BigDecimal evaluate0() {
                return evalFunction.apply(param1.evaluate(), param2.evaluate());
            }

            @Override
            protected String represent0() {
                return reprFunction.apply(param1, param2);
            }
        };
    }

    public static final BinaryOperator ADD = new BinaryOperator(BigDecimal::add, (param1, param2) -> param1.represent() + " + " + parenString(param2.represent()));
    public static final BinaryOperator SUBTRACT = new BinaryOperator(BigDecimal::subtract, (param1, param2) -> param1.represent() + " - " + parenString(param2.represent()));
    public static final BinaryOperator MULTIPLY = new BinaryOperator(BigDecimal::multiply, (param1, param2) -> param1.represent() + " * " + parenString(param2.represent()));
    public static final BinaryOperator DIVIDE = new BinaryOperator(BigDecimal::divide, (param1, param2) -> param1.represent() + "/" + parenString(param2.represent()));
}
