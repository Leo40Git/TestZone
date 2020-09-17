package adudecalledleo.util;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ExecutionException;

// provides a sqrt algorithm for BigDecimal, since an integrated one was only added in Java 9
public final class BigDecimalSqrt {
    // BigDecimal.TWO doesn't exist, and BigInteger.TWO is private
    // therefore, we have our own bootleg BigDecimal.TWO
    private static final BigDecimal BD_TWO = BigDecimal.valueOf(2);

    // https://stackoverflow.com/a/19743026 (but slightly modified)
    private static BigDecimal sqrt0(BigDecimal a) {
        final int scale = a.scale();
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

    private static final LoadingCache<BigDecimal, BigDecimal> SQRT_CACHE = CacheBuilder.newBuilder()
            .maximumSize(256)
            .build(new CacheLoader<BigDecimal, BigDecimal>() {
                @Override
                public BigDecimal load(@SuppressWarnings("NullableProblems") BigDecimal key) {
                    return sqrt0(key);
                }
            });

    public static BigDecimal sqrt(BigDecimal a) {
        try {
            return SQRT_CACHE.get(a);
        } catch (ExecutionException e) {
            throw new RuntimeException("Failed to calculate sqrt of BigDecimal. This should never happen?!", e);
        }
    }
}
