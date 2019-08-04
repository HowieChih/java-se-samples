package me.qihao.io.asynchronousfilechannel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

public class AsyncFileChannel {

    public static void main(String[] args) {
        Path path = Paths.get("", "", "");
        try {
            // readViaFuture(path);
            readViaCompletionHandler(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readViaFuture(Path path) throws IOException {
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);

        ByteBuffer buffer = ByteBuffer.allocate(10);
        long position = 0;

        // 这种写法只能得到10字节的数据
        Future<Integer> oepration = fileChannel.read(buffer, position);
        while (!oepration.isDone()) ;

        buffer.flip();
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);
        System.out.println(new String(data));
        buffer.clear();
    }

    public static void readViaCompletionHandler(Path path) throws IOException {
        Thread mainThread = Thread.currentThread();

        // 会新开一个默认线程池
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);

        // 同样只能读到10个字节
        ByteBuffer buffer = ByteBuffer.allocate(10);
        long position = 0;

        fileChannel.read(buffer, position, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {

                System.out.println(Thread.currentThread().getName());

                System.out.println("result = " + result);

                attachment.flip();
                byte[] data = new byte[attachment.limit()];
                attachment.get(data);
                System.out.println(new String(data));
                attachment.clear();

                // 强制结束主线程的join
                mainThread.interrupt();
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                mainThread.interrupt();
            }
        });

        // 因为fileChannel、byteBuffer等都是在主线程中声明的，所以在fileChannel.read结束前，主线程不能结束
        try {
            // 这个地方更好的做法应该是用fileChannel用的线程池里面的线程join（waits for this thread to die）
            mainThread.join();
        } catch (InterruptedException e) {
            System.out.println("main thread terminated");
        } finally {
            fileChannel.close();
        }
    }
}
