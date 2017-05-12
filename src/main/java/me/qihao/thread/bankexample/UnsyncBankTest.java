package me.qihao.thread.bankexample;

public class UnsyncBankTest {

    public static final int NACCOUNTS= 100;
    public static final double INITIAL_BALANCE = 1000;

    public static void main(String[] args) {
        Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
        for (int i = 0; i < NACCOUNTS; i++) {
            TransferRunnable runnable = new TransferRunnable(bank, i, INITIAL_BALANCE);
            new Thread(runnable).start();
        }
    }

}
