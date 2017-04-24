package me.qihao.thread.interruption;

public class EmailService implements Runnable {

    public void run() {
        boolean interrupted = false;
        int index = 0;
        try {
            while (true) {
                System.out.println(index++);
                if (index == 20) {
                    return;
                }

                try {
                    doTask();
                } catch (InterruptedException e) {
                    interrupted = true;
                }
            }
        } finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void doTask() throws InterruptedException {
        Thread.sleep(1000);
    }

    /*public void run() {
        while (true){
            // do nothing
        }
    }*/
}
