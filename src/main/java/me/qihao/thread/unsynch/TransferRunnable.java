package me.qihao.thread.unsynch;

public class TransferRunnable implements Runnable{

    private Bank bank;
    private int fromAccount;
    private double maxAmout;
    private int DEALY = 10;

    public TransferRunnable(Bank bank, int fromAccount, double maxAmout) {
        this.bank = bank;
        this.fromAccount = fromAccount;
        this.maxAmout = maxAmout;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int toAccount = (int) (bank.size() * Math.random());
                double amount = maxAmout * Math.random();
                bank.transfer(fromAccount, toAccount, amount);
                Thread.sleep((int) (DEALY * Math.random()));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
