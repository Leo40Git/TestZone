package adudecalledleo.testzone.expression;

import java.math.BigDecimal;

public interface Expression {
    BigDecimal evaluate();
    String represent();

    static String parenString(String s) {
        if (s.startsWith("+") || s.startsWith("-"))
            return "(" + s + ")";
        return s;
    }
}
