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
        Path path = Paths.get("/Users", "chih", "mbox");
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
        // 会新开一个默认线程池
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);

        // 同样只能读到10个字节
        ByteBuffer buffer = ByteBuffer.allocate(1024);
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
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });

        // why?
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
