package me.qihao.algorithm;

import org.junit.Test;

public class ArrayAlgorithmTest {

    @Test
    public void findTheTwoWithMaxDiff() {
        Integer[] array = {1, -1, 5, 8, -9, 10, 445, 80};
        ArrayAlgorithm.findTheTwoWithMaxDiff(array);
    }

    @Test
    public void findMaxDiff() {
        Integer[] array = {445, 1, -1, 5, 8, -9, 10, 80};
        ArrayAlgorithm.findMaxDiff(array);
    }

    @Test
    public void findMaxDiffWithOnceLoop() {
        Integer[] array = {4, 9, 2, 2, -1, 8};
        ArrayAlgorithm.findMaxDiffWithOnceLoop(array);
    }

    @Test
    public void testStr() {
        String original = "jladfjjefieuhafaskdfasdhjkfb";
        char[] toMatchChars = "jjkkz".toCharArray();
        StringBuilder regex = new StringBuilder("[a-z]*");
        for (char toMatchChar : toMatchChars) {
            regex.append(toMatchChar).append("[a-z]*");
        }
        System.out.println(original.matches(regex.toString()));
    }
}
