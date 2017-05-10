package me.qihao.thread.synch.threadlocal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {

    private static final ThreadLocal<DateFormat> dateFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    void doTask(Date date) {
        DateFormat df = dateFormat.get();
        String dateStamp = df.format(date);
        System.out.println(Thread.currentThread() + " " + System.identityHashCode(df) + " " + dateStamp);
    }
}
