package me.qihao.generic.genericclass;

/**
 * @author qihao
 * @version 1.0 2017-06-15
 * @since 1.0
 */
public class GenericClassTest {
    public static void main(String[] args) {
        String[] words = {"Mary", "had", "a", "little", "lamb"};
        Pair<String> mm = ArrayAlg.minmax(words);
        System.out.printf("min: %s max: %s \n", mm.getFirst(), mm.getSecond());
        System.out.println(ArrayAlg.getT(100));
        System.out.println(ArrayAlg.getDefaultVal());
    }
}

class ArrayAlg {

    /**
     * Gets the minimum and maximum of an array of strings
     *
     * @param array an array of strings
     * @return a pari with the min and max value, or null if array is null or empty
     */
    public static Pair<String> minmax(String[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        String min = array[0], max = array[0];
        for (String str : array) {
            if (min.compareTo(str) > 0) {
                min = str;
            } else if (max.compareTo(str) < 0) {
                max = str;
            }
        }
        return new Pair<>(min, max);
    }

    public static <T> T getT(T t) {
        return t;
    }

    public static <T> String getDefaultVal() {
        return "default";
    }
}
