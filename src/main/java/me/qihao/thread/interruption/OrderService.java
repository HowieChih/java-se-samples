package me.qihao.thread.interruption;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;

public class OrderService {


    public static void main(String[] args) throws InterruptedException {
        Thread emailService = new Thread(new EmailService());
        emailService.start();
        Thread.sleep(5000);
        emailService.interrupt();
        Thread.sleep(1000);
        System.out.println(emailService.getState().toString());
        System.out.println(emailService.isInterrupted());

        /*Thread thread = new PrimeProducer(new ArrayBlockingQueue<>(100));
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();*/
    }
}
