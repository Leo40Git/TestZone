package adudecalledleo.util;

@FunctionalInterface
public interface BinaryBooleanOperator {
    BinaryBooleanOperator FIRST = (a, b) -> a;
    BinaryBooleanOperator SECOND = (a, b) -> b;
    BinaryBooleanOperator AND = Boolean::logicalAnd;
    BinaryBooleanOperator OR = Boolean::logicalOr;
    BinaryBooleanOperator XOR = Boolean::logicalXor;

    boolean apply(boolean a, boolean b);
}
