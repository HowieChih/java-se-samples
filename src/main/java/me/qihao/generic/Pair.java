package me.qihao.generic;

/**
 * 定义简单泛型类
 *
 * @author qihao
 * @version 1.0 2017-06-15
 * @since 1.0
 */
public class Pair<T> {

    private T first;
    private T second;

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }
}
