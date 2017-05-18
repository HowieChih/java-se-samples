package me.qihao.thread.blockingqueue;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class FileEnumerationTask implements Runnable{

    public static File DUMMY = new File("");
    private BlockingQueue<File> queue;
    private File startingDirectory;

    public FileEnumerationTask(File startingDirectory, BlockingQueue<File> queue) {
        this.startingDirectory = startingDirectory;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            enumerate(startingDirectory);
            queue.put(DUMMY);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void enumerate(File directory) throws InterruptedException {
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                enumerate(file);
            } else {
                queue.put(file);
            }
        }
    }
}
