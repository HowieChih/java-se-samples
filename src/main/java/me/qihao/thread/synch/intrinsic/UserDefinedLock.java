package me.qihao.thread.synch.intrinsic;

public class UserDefinedLock {

    void doOwnTask() {
        System.out.println(Thread.currentThread() + " user defined task do own work");
    }

}
