package me.qihao.thread.blockingqueue;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {

    public static void main(String[] args){
        final int FILE_QUEUE_SIZE = 10;
        final int SEARCH_THREADS = 100;
        BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

        FileEnumerationTask enumerator = new FileEnumerationTask(
                new File("D:\\Workspace\\idea\\java-se-samples\\src\\main\\java\\me\\qihao\\thread"), queue);
        new Thread(enumerator).start();
        for (int i = 1; i <= SEARCH_THREADS; i++){
            new Thread(new SearchTask(queue, "file")).start();
        }
    }
}
