package me.qihao.generic;

import java.util.ArrayList;
import java.util.List;

class GenericBoundTest {

    public static void main(String[] args) {
        String[] words = {"Mary", "had", "a", "little", "lamb"};
        Pair<String> mm = ArrayAlgT.minmax(words);
        System.out.printf("min: %s max: %s \n", mm.getFirst(), mm.getSecond());
    }
}

class ArrayAlgT {

    /**
     * Gets the minimum and maximum of an array of objects of type T
     *
     * @param array an array of objects of type T
     * @return a pair with the min and max value, or null if array is null or empty
     */
    public static <T extends Comparable<T>> Pair<T> minmax(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        T min = array[0], max = array[0];
        for (T t : array) {
            if (min.compareTo(t) > 0) {
                min = t;
            } else if (max.compareTo(t) < 0) {
                max = t;
            }
        }
        return new Pair<>(min, max);
    }
}