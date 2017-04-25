package me.qihao.thread.synch.client_side_locking;

public class ClientSideLockingTest {

    public static void main(String[] args) {
        ListHepler listHepler = new ListHepler();
        new Thread(new ListRunnable(listHepler, "add")).start();
        new Thread(new ListRunnable(listHepler, "putIfAbsent")).start();
    }
}
