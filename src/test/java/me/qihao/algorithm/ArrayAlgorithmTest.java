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
        Integer[] array = {80, 100, -1, 10};
        ArrayAlgorithm.findMaxDiffWithOnceLoop(array);
    }
}
