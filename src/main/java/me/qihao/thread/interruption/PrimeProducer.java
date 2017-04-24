package me.qihao.thread.interruption;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

public class PrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;

    PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (true) {
                queue.put(p = p.nextProbablePrime());
                System.out.println(p);
            }
        } catch (InterruptedException consumed) {
            consumed.printStackTrace();
        }
    }

    public void cancel() { interrupt(); }
}
