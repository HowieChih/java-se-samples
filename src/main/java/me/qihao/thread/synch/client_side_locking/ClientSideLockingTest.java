package me.qihao.thread.synch.client_side_locking;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ClientSideLockingTest {

    public static void main(String[] args) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(new Date()));

        List list = Collections.synchronizedList(new ArrayList<>());
        ListHepler listHepler = new ListHepler(list);
        new Thread(new ListRunnable(listHepler, "putIfAbsent")).start();
        new Thread(new ListRunnable(listHepler, "add")).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list.add("x");
        System.out.println(Thread.currentThread() + " add value by main thread " + formatter.format(new Date()));
    }
}
