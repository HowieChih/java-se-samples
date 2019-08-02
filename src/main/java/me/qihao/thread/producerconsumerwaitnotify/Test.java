package me.qihao.thread.producerconsumerwaitnotify;

import java.util.LinkedList;
import java.util.Queue;

public class Test {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        int maxSize = 10;
        Thread producer = new Thread(new Producer(queue, maxSize), "PRODUCER");
        Thread consumer = new Thread(new Consumer(queue, maxSize), "CONSUMER");
        consumer.start();
        producer.start();
    }
}
