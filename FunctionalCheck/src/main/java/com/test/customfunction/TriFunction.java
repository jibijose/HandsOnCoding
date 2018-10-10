package com.test.customfunction;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface TriFunction<T, U, V, X> {
  X apply(T t, U u, V v);

  default <R> TriFunction<T, U, V, R> andThen(Function<? super X, ? extends R> after) {
    Objects.requireNonNull(after);
    return (T t, U u, V v) -> after.apply(apply(t, u, v));
  }
}
