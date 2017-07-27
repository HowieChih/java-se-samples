package me.qihao.extension;

public class ConstructorWithExtends {
    public static void main(String[] args) {
        A a = new B();
    }
}

class A {
    int x = 1;

    A() {
        System.out.println(this);
    }

    int getX() {
        return x;
    }

    public String toString() {
        return getClass().getSimpleName() + getX();
    }
}

class B extends A {
    int x = 2;

    B() {
        System.out.println(this);
    }

    int getX() {
        return x;
    }
}
