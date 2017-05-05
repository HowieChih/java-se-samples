package me.qihao.thread.synch.threadlocal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    void doTask(Date date) {
        String dateStamp = dateFormat.format(date);
        System.out.println(Thread.currentThread() + " " + System.identityHashCode(dateFormat) + " " + dateStamp);
    }
}
