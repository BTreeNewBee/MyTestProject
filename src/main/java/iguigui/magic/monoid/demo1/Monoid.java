package iguigui.magic.monoid.demo1;

import java.util.stream.Stream;

public interface Monoid<T> {
    T empty();

    T append(T a, T b);

    default T appends(Stream<T> x) {
        return x.reduce(empty(), this::append);
    }
}
