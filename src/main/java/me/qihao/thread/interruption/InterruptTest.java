package me.qihao.thread.interruption;

public class InterruptTest implements Runnable {
    private final boolean f;
    private Task task;

    public InterruptTest(boolean f, Task task) {
        this.f = f;
        this.task = task;
    }

    @Override
    public void run() {
        /*while (true){
            System.out.println(Thread.currentThread().isInterrupted());
        }*/
        if (f) {
            task.doA();
        } else {
            task.doB();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        Thread t1 = new Thread(new InterruptTest(true, task));
        Thread t2 = new Thread(new InterruptTest(false, task));
        t1.start();
        Thread.sleep(1000);
        t2.start();
        // t.interrupt();
    }

    static class Task {
        public synchronized void doA() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A");
        }

        public synchronized void doB() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B");
        }
    }
}
