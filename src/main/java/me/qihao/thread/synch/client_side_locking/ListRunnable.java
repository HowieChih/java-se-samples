package me.qihao.thread.synch.client_side_locking;

public class ListRunnable implements Runnable{

    private ListHepler listHepler;
    private String mission;

    public ListRunnable(ListHepler listHepler, String mission) {
        this.listHepler = listHepler;
        this.mission = mission;
    }

    @Override
    public void run() {
        try {
            if ("putIfAbsent".equals(mission)) {
                listHepler.putIfAbsent("x");
            } else {
                listHepler.add("x");
            }
            // 此处虽然printList做了方法内部同步，但此处上面代码结束执行时，是当前线程继续执行，还是新线程执行其他方法，还是需要竞争的，
            // 这就可能导致printList显示结果可能不一致。
            listHepler.printList();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
