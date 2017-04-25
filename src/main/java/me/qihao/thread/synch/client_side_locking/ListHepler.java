package me.qihao.thread.synch.client_side_locking;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ListHepler<E> {
    private final List<E> list;
    private final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public ListHepler(List<E> list) {
        this.list = list;
    }

    public boolean putIfAbsent(E x) throws InterruptedException {
        // 关于使用 client-side locking（线程锁对象用原子操作的对象？），而不使用 synchronized 关键字修饰方法的做法，保持怀疑
        // 因为synchronized修饰方法的做法，锁对象是ListHelper，和 list add方法内部用的锁对象不一样，
        // 但client-side blocking 用list来做对象锁，同步能完成的条件是list add方法内部用的锁对象是内部锁（intrinsic），
        // 但好像 list add 方法内部所用的内部锁并不是内部锁（？）。

        // update: 上述思考过程结论不太对，使用list对象做锁，可以做到完全同步，即使其他线程有更改list的行为（即add方法也锁了）。
        // 可以将 list 改成 formatter 做锁来做测试，发现完成不了同步任务，但list就可以。
        // 那 core Java为什么不推荐client-side locking?
        synchronized (list) {
            boolean absent = !list.contains(x);
            System.out.println(Thread.currentThread() + " addIfAbsent " + absent + " " + formatter.format(new Date()));
            Thread.sleep(5000);
            if (absent) {
                list.add(x);
                System.out.println("add absent value " + formatter.format(new Date()));
            }
            return absent;
        }
    }

    public  void add(E x) throws InterruptedException {
        synchronized (list){
            Thread.sleep(2000);
            System.out.println(Thread.currentThread() + " add " + formatter.format(new Date()));
            list.add(x);
        }
    }

    public synchronized void printList() throws InterruptedException {
        synchronized (list) {
            System.out.println(Thread.currentThread() + " " + System.identityHashCode(list) + " " + list);
        }
    }
}
