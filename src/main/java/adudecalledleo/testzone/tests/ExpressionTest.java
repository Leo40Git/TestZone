package adudecalledleo.testzone.tests;

import adudecalledleo.testzone.TerminalInterface;
import adudecalledleo.testzone.expression.BinaryOperation;
import adudecalledleo.testzone.expression.Expression;
import adudecalledleo.testzone.expression.UnaryOperation;

public class ExpressionTest extends Test {
    @Override
    public String getName() {
        return "expression";
    }

    @Override
    public void run(TerminalInterface ti) {
        Expression e1 = BinaryOperation.ADD.express(UnaryOperation.ABS.express(12), UnaryOperation.NEGATE.express(14));
        Expression e2 = BinaryOperation.MULTIPLY.express(
                UnaryOperation.SQUARE.express(3), BinaryOperation.DIVIDE.express(1, 2));
        Expression e3 = BinaryOperation.SUBTRACT.express(e1, e2);
        Expression e4 = BinaryOperation.ADD.express(e3, UnaryOperation.SQRT.express(4));
        System.out.println(e4);
    }
}
