package me.qihao.io.clientserver.nonblocking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TestClient {
    public void startClient() throws IOException, InterruptedException {

        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 9093);
        SocketChannel client = SocketChannel.open(hostAddress);

        System.out.println("Client... started");

        String threadName = Thread.currentThread().getName();

        // Send messages to server
        String[] messages = new String[] { threadName + ": msg1", threadName + ": msg2", threadName + ": msg3" };

        for (String message : messages) {
            ByteBuffer buffer = ByteBuffer.allocate(74);
            buffer.put(message.getBytes());
            buffer.flip();
            client.write(buffer);
            System.out.println(message);
            buffer.clear();
            if ("client-A".equals(threadName)) {
                Thread.sleep(1000);
            } else {
                Thread.sleep(5000);
            }
        }
        client.close();
    }

    public static void main(String[] args) {
        Runnable client = new Runnable() {
            @Override
            public void run() {
                try {
                    new TestClient().startClient();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        new Thread(client, "client-A").start();
        new Thread(client, "client-B").start();
    }
}
