package me.qihao.lambda.collection;


import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface CustomCollection<T> extends Collection<T>{
    default void forEachIf(Consumer<T> action, Predicate<T> filter) {
        forEach(item -> {
            if (filter.test(item))
                action.accept(item);
        });
    }
}
