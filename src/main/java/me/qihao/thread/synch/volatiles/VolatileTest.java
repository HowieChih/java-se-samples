package me.qihao.thread.synch.volatiles;

import java.util.logging.Level;
import java.util.logging.Logger;

public class VolatileTest {

    private static final Object object = new Object();

    private static final Logger LOGGER = Logger.getLogger("test");

    // https://dzone.com/articles/java-volatile-keyword-0
    //
    // marvelous 终于可以找到一个例子来测试 volatile 了
    // 给 MY_INT 添加 volatile 后，可以看到 ChangeListener 里的 run 方法可以实时获取到 ChangeMaker 里 set 进去的值。
    // 去掉 volatile 后，ChangeListener 里面的 run 方法获取到的 MY_INT 就一直是 0
    // 需要注意的是 volatile 只保证了内存的可见性，不保证原子性
    // 因为锁同步既保证了可见性，又保证了原子性，所以可以通过去掉 volatile 关键字，同时给 ChangeListener local_value != MY_INT 加锁来同样保证可见性
    private static volatile int MY_INT = 0;

    public static void main(String[] args) {
        new ChangeListener().start();
        new ChangeMaker().start();
    }

    static class ChangeListener extends Thread {
        @Override
        public void run() {
            int local_value = MY_INT;
            while (local_value < 5) {
                /*synchronized (object) {
                    if (local_value != MY_INT) {
                        LOGGER.log(Level.INFO, "Got Change for MY_INT : {0}", MY_INT);
                        local_value = MY_INT;
                    }
                }*/

                if (local_value != MY_INT) {
                    LOGGER.log(Level.INFO, "Got Change for MY_INT : {0}", MY_INT);
                    local_value = MY_INT;
                }
            }
        }
    }

    static class ChangeMaker extends Thread {
        @Override
        public void run() {

            int local_value = MY_INT;
            while (MY_INT < 5) {
                LOGGER.log(Level.INFO, "Incrementing MY_INT to {0}", local_value + 1);
                MY_INT = ++local_value;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
