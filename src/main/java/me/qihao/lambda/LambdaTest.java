package me.qihao.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LambdaTest {

    public static void main(String[] args) {
        String[] array = {"list", "set", "map", "array"};
        Arrays.sort(array, (first, second) -> Integer.compare(first.length(), second.length()));
        System.out.println(Arrays.toString(array));
        Arrays.sort(array, Comparator.comparing(String::length));

        Arrays.sort(array, String::compareToIgnoreCase);

        Integer[] integers = {};
        Arrays.sort(integers, Integer::compare);

        Comparator<Integer> comparator = Integer::compareTo;

        List<String> list = new ArrayList<>();
        list.add("x");
        list.forEach(LambdaTest::printList);

        repeatMsg("run thread", 100);

        new Thread(() -> {

        });
    }

    public static <T> void printList(T obj) {
        System.out.println(obj + " specified");
    }

    public static void repeatMsg(String text, int count) {
        String x = "x";
        Runnable runnable = () -> {
            for (int i = 0; i < count; i++) {
                System.out.println(x + text);
            }
        };
        new Thread(runnable).start();
    }
}
