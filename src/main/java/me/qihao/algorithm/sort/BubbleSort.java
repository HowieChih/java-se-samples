package me.qihao.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序，将相邻两个位置的元素进行大小比较，将大的一个放在小的一个后面，重复多次。
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] array = {3, 5, 2, 1, 4, 7, 8, 9};
        // sort(array);
        // sortv2(array);
        sortv3(array);
        System.out.println(Arrays.toString(array));
    }

    // init version 0.1
    public static void sort(int[] array) {
        for (int k = array.length; k > 1; k--) { // 外层循环控制除了后面位置的最大数，前面还有多少项需要交换
            for (int i = 0; i < k - 1; i++) { // 内层循环控制每一次交换数字的坐标调整
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                }
            }
        }
    }

    // version 0.2 如果全部都排序好了，就不用执行剩下的排序操作了
    public static void sortv2(int[] array) {
        boolean isSorted;
        for (int k = array.length; k > 1; k--) {
            isSorted = false;
            for (int i = 0; i < k - 1; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    isSorted = true;
                }
            }
            if (!isSorted) {
                break;
            }
        }
    }

    // version 0.3 如果数组后面某位置后面是排好序的，那么该位置之后每次排序都不用重新排
    // 记录最后一次排序位置
    public static void sortv3(int[] array) {
        boolean isSorted;
        int lastSortPos = -1, sortBorder;
        for (int k = array.length; k > 1; k = sortBorder) {
            isSorted = false;
            for (int i = 0; i < k - 1; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    isSorted = true;
                    lastSortPos = i + 1;
                }
            }
            sortBorder = lastSortPos;
            if (!isSorted) {
                break;
            }
        }
    }
}
