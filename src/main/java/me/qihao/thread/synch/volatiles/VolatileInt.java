package me.qihao.thread.synch.volatiles;

// 这个例子完全测不出缓存会导致值未及时更新的问题。
public class VolatileInt {

    private int intValue = 0;

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public static void main(String[] args) {
        VolatileInt volatileInt = new VolatileInt();
        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                new Thread(() -> volatileInt.setIntValue(1 + + 5 + 5)).start();
            }
            new Thread(() -> System.out.println("current thread: " + Thread.currentThread().getId() + " " + volatileInt.getIntValue()))
                    .start();
        }
    }
}
