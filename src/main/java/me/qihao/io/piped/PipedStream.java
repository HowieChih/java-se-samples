package me.qihao.io.piped;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * A Java NIO Pipe is a one-way data connection between two threads. <br/>
 * A Pipe has a source channel and a sink channel. <br/>
 * You write data to the sink channel. This data can then be read from the source channel.
 *
 */
public class PipedStream {

    public static void main(String[] args) throws IOException {
        final PipedOutputStream output = new PipedOutputStream();
        final PipedInputStream input = new PipedInputStream(output);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    output.write("Hello World".getBytes());
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int data = input.read();
                    while (data != -1) {
                        System.out.print((char) data);
                        data = input.read();
                    }
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
