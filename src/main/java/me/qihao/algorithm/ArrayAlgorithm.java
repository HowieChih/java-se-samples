package me.qihao.algorithm;

import java.util.Arrays;

public class ArrayAlgorithm {

    public static void findTheTwoWithMaxDiff(Integer[] array) {
        Arrays.sort(array);
        System.out.println(array[0] + " " + array[array.length - 1]);
    }

    public static void findMaxDiff(Integer[] array) {
        int minuend = 0, subtrahend = 0, maxDiff = 0;
        int length = array.length;
        for (int i = 0; i < length; i++) {
            for (int k = i + 1; k < length; k++) {
                int diff = array[k] - array[i];
                if (diff > maxDiff) {
                    minuend = array[k];
                    subtrahend = array[i];
                    maxDiff = diff;
                }
            }
        }
        System.out.printf("minuend: %d, subtrahend: %d, maxDiff: %d \r\n", minuend, subtrahend, maxDiff);
    }

    public static void findMaxDiffWithOnceLoop(Integer[] array) {
        int length = array.length;
        if (length < 2) {
            return;
        }
        int min = Math.min(array[0], array[1]);
        int max = Math.max(array[0], array[1]);
        int diff = max - min;
        for (int k = 2; k < length; k++) {
            max = array[k] - max > 0 ? array[k] : max;
            min = array[k] - min < 0 ? array[k] : min;
            diff = max - min;
        }

        System.out.printf("minuend: %d, subtrahend: %d, maxDiff: %d \r\n", max, min, diff);
    }
}
