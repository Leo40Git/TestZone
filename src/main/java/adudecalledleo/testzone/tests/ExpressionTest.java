package adudecalledleo.testzone.tests;

import adudecalledleo.testzone.TerminalInterface;
import adudecalledleo.testzone.expression.BinaryOperator;
import adudecalledleo.testzone.expression.Expression;
import adudecalledleo.testzone.expression.UnaryOperator;

public class ExpressionTest extends Test {
    @Override
    public String getName() {
        return "expression";
    }

    @Override
    public void run(TerminalInterface ti) {
        Expression e1 = BinaryOperator.ADD.express(UnaryOperator.ABS.express(12), UnaryOperator.NEGATE.express(14));
        Expression e2 = BinaryOperator.MULTIPLY.express(UnaryOperator.SQUARE.express(3), BinaryOperator.DIVIDE.express(1, 2));
        Expression e3 = BinaryOperator.SUBTRACT.express(e1, e2);
        Expression e4 = BinaryOperator.ADD.express(e3, UnaryOperator.SQRT.express(4));
        System.out.println(e4);
    }
}
