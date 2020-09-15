package adudecalledleo.testzone.expression;

import java.math.BigDecimal;
import java.util.Arrays;

public interface Operator {
    int paramCount();
    Expression express(Expression... params);

    default Expression express(BigDecimal... params) {
        return express(Arrays.stream(params).map(ConstantExpression::of).toArray(Expression[]::new));
    }

    default Expression express(double... params) {
        return express(Arrays.stream(params).mapToObj(ConstantExpression::of).toArray(Expression[]::new));
    }
}
