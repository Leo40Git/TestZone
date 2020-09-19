package adudecalledleo.util;

@FunctionalInterface
public interface IntObjConsumer<T> {
    void accept(int i, T t);
}
