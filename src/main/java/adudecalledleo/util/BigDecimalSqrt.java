package adudecalledleo.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

// provides a sqrt algorithm for BigDecimal, since an integrated one was only added in Java 9
public final class BigDecimalSqrt {
    // BigDecimal.TWO doesn't exist, and BigInteger.TWO is private
    // therefore, we have our own bootleg BigDecimal.TWO
    private static final BigDecimal BD_TWO = BigDecimal.valueOf(2);

    // https://stackoverflow.com/a/19743026 (but slightly modified)
    public static BigDecimal sqrt(BigDecimal a, final int scale) {
        BigDecimal x0 = BigDecimal.ZERO;
        BigDecimal x1 = BigDecimal.valueOf(Math.sqrt(a.doubleValue()));
        while (!x0.equals(x1)) {
            x0 = x1;
            x1 = a.divide(x0, scale, RoundingMode.HALF_UP);
            x1 = x1.add(x0);
            x1 = x1.divide(BD_TWO, scale, RoundingMode.HALF_UP);
        }
        return x1;
    }

    public static BigDecimal sqrt(BigDecimal a) {
        return sqrt(a, a.scale());
    }
}
