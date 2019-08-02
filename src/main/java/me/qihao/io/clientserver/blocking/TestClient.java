package me.qihao.io.clientserver.blocking;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestClient {

    public static void main(String[] args) {
        Runnable client = new Runnable() {
            @Override
            public void run() {
                new TestClient().startClient();
            }
        };
        new Thread(client, "client-A").start();
        new Thread(client, "client-B").start();
    }

    public void startClient() {

        String hostName = "localhost";
        int portNumber = 4444;
        String threadName = Thread.currentThread().getName();
        String[] messages = new String[]{threadName + " > msg1", threadName + " > msg2", threadName + " > msg3", threadName + " > Done"};

        try (Socket echoSocket = new Socket(hostName, portNumber);
             PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()))){


            for (String message : messages) {
                try (BufferedReader stdIn = new BufferedReader(new StringReader(message))) {
                    String userInput;
                    while ((userInput = stdIn.readLine()) != null) {
                        out.println(userInput); // write to server
                        System.out.println("echo: " + in.readLine()); // Wait for the server to
                        // echo back
                    }
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Unknown host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName + ".." + e.toString());
            System.exit(1);
        }
    }
}
