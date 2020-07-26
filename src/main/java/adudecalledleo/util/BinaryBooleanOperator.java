package adudecalledleo.util;

@FunctionalInterface
public interface BinaryBooleanOperator {
    BinaryBooleanOperator FIRST = (b1, b2) -> b1;
    BinaryBooleanOperator SECOND = (b1, b2) -> b2;
    BinaryBooleanOperator AND = Boolean::logicalAnd;
    BinaryBooleanOperator OR = Boolean::logicalOr;
    BinaryBooleanOperator EXCLUSIVE_OR = Boolean::logicalXor;

    boolean apply(boolean b1, boolean b2);
}
