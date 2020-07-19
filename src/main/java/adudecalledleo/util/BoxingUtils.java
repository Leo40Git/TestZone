package adudecalledleo.util;

import static java.lang.Math.min;
import static java.util.stream.IntStream.range;

public final class BoxingUtils {
    private BoxingUtils() {
        throw new RuntimeException("lolno");
    }

    public static void box(byte[] in, Byte[] out) {
        range(0, min(in.length, out.length)).forEach(i -> out[i] = in[i]);
    }

    public static Byte[] box(byte[] in) {
        Byte[] out = new Byte[in.length];
        box(in, out);
        return out;
    }

    public static void box(short[] in, Short[] out) {
        range(0, min(in.length, out.length)).forEach(i -> out[i] = in[i]);
    }

    public static Short[] box(short[] in) {
        Short[] out = new Short[in.length];
        box(in, out);
        return out;
    }

    public static void box(int[] in, Integer[] out) {
        range(0, min(in.length, out.length)).forEach(i -> out[i] = in[i]);
    }

    public static Integer[] box(int[] in) {
        Integer[] out = new Integer[in.length];
        box(in, out);
        return out;
    }

    public static void box(long[] in, Long[] out) {
        range(0, min(in.length, out.length)).forEach(i -> out[i] = in[i]);
    }

    public static Long[] box(long[] in) {
        Long[] out = new Long[in.length];
        box(in, out);
        return out;
    }

    public static void box(float[] in, Float[] out) {
        range(0, min(in.length, out.length)).forEach(i -> out[i] = in[i]);
    }

    public static Float[] box(float[] in) {
        Float[] out = new Float[in.length];
        box(in, out);
        return out;
    }

    public static void box(double[] in, Double[] out) {
        range(0, min(in.length, out.length)).forEach(i -> out[i] = in[i]);
    }

    public static Double[] box(double[] in) {
        Double[] out = new Double[in.length];
        box(in, out);
        return out;
    }

    public static void box(char[] in, Character[] out) {
        range(0, min(in.length, out.length)).forEach(i -> out[i] = in[i]);
    }

    public static Character[] box(char[] in) {
        Character[] out = new Character[in.length];
        box(in, out);
        return out;
    }

    public static void unbox(Byte[] in, byte[] out) {
        range(0, min(in.length, out.length)).forEach(i -> out[i] = in[i]);
    }

    public static void unbox(Byte[] in, byte forNull, byte[] out) {
        range(0, min(in.length, out.length)).forEach(i -> out[i] = in[i] == null ? forNull : in[i]);
    }

    public static byte[] unbox(Byte[] in) {
        byte[] out = new byte[in.length];
        unbox(in, out);
        return out;
    }

    public static byte[] unbox(Byte[] in, byte forNull) {
        byte[] out = new byte[in.length];
        unbox(in, forNull, out);
        return out;
    }

    public static void unbox(Short[] in, short[] out) {
        range(0, min(in.length, out.length)).forEach(i -> out[i] = in[i]);
    }

    public static void unbox(Short[] in, short forNull, short[] out) {
        range(0, min(in.length, out.length)).forEach(i -> out[i] = in[i] == null ? forNull : in[i]);
    }

    public static short[] unbox(Short[] in) {
        short[] out = new short[in.length];
        unbox(in, out);
        return out;
    }

    public static short[] unbox(Short[] in, short forNull) {
        short[] out = new short[in.length];
        unbox(in, forNull, out);
        return out;
    }

    public static void unbox(Integer[] in, int[] out) {
        range(0, min(in.length, out.length)).forEach(i -> out[i] = in[i]);
    }

    public static void unbox(Integer[] in, int forNull, int[] out) {
        range(0, min(in.length, out.length)).forEach(i -> out[i] = in[i] == null ? forNull : in[i]);
    }

    public static int[] unbox(Integer[] in) {
        int[] out = new int[in.length];
        unbox(in, out);
        return out;
    }

    public static int[] unbox(Integer[] in, int forNull) {
        int[] out = new int[in.length];
        unbox(in, forNull, out);
        return out;
    }

    public static void unbox(Long[] in, long[] out) {
        range(0, min(in.length, out.length)).forEach(i -> out[i] = in[i]);
    }

    public static void unbox(Long[] in, long forNull, long[] out) {
        range(0, min(in.length, out.length)).forEach(i -> out[i] = in[i] == null ? forNull : in[i]);
    }

    public static long[] unbox(Long[] in) {
        long[] out = new long[in.length];
        unbox(in, out);
        return out;
    }

    public static long[] unbox(Long[] in, long forNull) {
        long[] out = new long[in.length];
        unbox(in, forNull, out);
        return out;
    }

    public static void unbox(Float[] in, float[] out) {
        range(0, min(in.length, out.length)).forEach(i -> out[i] = in[i]);
    }

    public static void unbox(Float[] in, float forNull, float[] out) {
        range(0, min(in.length, out.length)).forEach(i -> out[i] = in[i] == null ? forNull : in[i]);
    }

    public static float[] unbox(Float[] in) {
        float[] out = new float[in.length];
        unbox(in, out);
        return out;
    }

    public static float[] unbox(Float[] in, float forNull) {
        float[] out = new float[in.length];
        unbox(in, forNull, out);
        return out;
    }

    public static void unbox(Double[] in, double[] out) {
        range(0, min(in.length, out.length)).forEach(i -> out[i] = in[i]);
    }

    public static void unbox(Double[] in, double forNull, double[] out) {
        range(0, min(in.length, out.length)).forEach(i -> out[i] = in[i] == null ? forNull : in[i]);
    }

    public static double[] unbox(Double[] in) {
        double[] out = new double[in.length];
        unbox(in, out);
        return out;
    }

    public static double[] unbox(Double[] in, double forNull) {
        double[] out = new double[in.length];
        unbox(in, forNull, out);
        return out;
    }

    public static void unbox(Character[] in, char[] out) {
        range(0, min(in.length, out.length)).forEach(i -> out[i] = in[i]);
    }

    public static void unbox(Character[] in, char forNull, char[] out) {
        range(0, min(in.length, out.length)).forEach(i -> out[i] = in[i] == null ? forNull : in[i]);
    }

    public static char[] unbox(Character[] in) {
        char[] out = new char[in.length];
        unbox(in, out);
        return out;
    }

    public static char[] unbox(Character[] in, char forNull) {
        char[] out = new char[in.length];
        unbox(in, forNull, out);
        return out;
    }
}
