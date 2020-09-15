package adudecalledleo.testzone.expression;

import java.math.BigDecimal;

public abstract class CachingExpression implements Expression {
    private BigDecimal evalCache;
    private String reprCache;

    protected abstract BigDecimal evaluate0();
    protected abstract String represent0();

    @Override
    public final BigDecimal evaluate() {
        if (evalCache == null)
            evalCache = evaluate0();
        return evalCache;
    }

    @Override
    public final String represent() {
        if (reprCache == null)
            reprCache = represent0();
        return reprCache;
    }
}
