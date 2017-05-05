package me.qihao.thread.synch.volatiles;

public class VolatileNotAtomic {
    private static volatile long longVal = 0;
    private static long longValSync = 0;

    private static final byte[] lock = new byte[0];

    private static class LoopVolatile implements Runnable {
        @Override
        public void run() {
            long val = 0;
            while (val < 10000000L) {
                longVal++;
                val++;
            }
        }
    }

    private static class LoopVolatile2 implements Runnable {
        @Override
        public void run() {
            long val = 0;
            synchronized (lock) {
                while (val < 10000000L) {
                    longValSync++;
                    val++;
                }
            }
        }
    }

    public static void main(String[] args) {
        // volatile 不能保证原子性测试案例
        Thread t1 = new Thread(new LoopVolatile());
        t1.start();
        Thread t2 = new Thread(new LoopVolatile());
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {

        }
        System.out.println("final longVal: " + longVal);

        // 由于使用volatile无法保证原子性，会造成线程不安全，使用同步阻塞来保证线程安全
        Thread t3 = new Thread(new LoopVolatile2());
        t3.start();
        Thread t4 = new Thread(new LoopVolatile2());
        t4.start();
        while (t3.isAlive() || t4.isAlive()) {

        }
        System.out.println("final longValSync: " + longValSync);
    }
}
