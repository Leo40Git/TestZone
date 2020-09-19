package adudecalledleo.testzone.property.indexed;

import java.util.stream.Stream;

public interface IndexedProperty<T> {
    int size();
    T get(int index);

    default Stream<T> stream() {
        Stream.Builder<T> streamBuilder = Stream.builder();
        for (int i = 0; i < size(); i++)
            streamBuilder.accept(get(i));
        return streamBuilder.build();
    }
}
