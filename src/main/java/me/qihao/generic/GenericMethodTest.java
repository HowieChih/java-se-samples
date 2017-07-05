package me.qihao.generic;

public class GenericMethodTest {

    public static void main(String[] args) {
        System.out.println(GenericMethodTest.getT(100));
        System.out.println(GenericMethodTest.<String>getT("strring value"));
        System.out.println(GenericMethodTest.getDefaultVal());
    }

    public static <T> T getT(T t) {
        return t;
    }

    public static <T> String getDefaultVal() {
        return "default";
    }
}
