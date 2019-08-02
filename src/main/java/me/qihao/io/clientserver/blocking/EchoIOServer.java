package me.qihao.io.clientserver.blocking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoIOServer {

    public static void main(String[] args) {
        int portNumber = 4444;
        System.out.println("Waiting on port: " + portNumber + "...");
        boolean listening = true;

        // bind server socket to port
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (listening) {
                // Wait for the client to make a connection and when it does, create a new socket to handle the request
                Socket clientSocket = serverSocket.accept();
                System.out.println("a new request is coming...");

                // Handle each connection in a new thread to manage concurrent users
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("server thread: " + Thread.currentThread().getName());
                        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                            String request, response;
                            while ((request = in.readLine()) != null) {
                                response = processRequest(request);
                                out.println(response);
                                if ("Done".equals(request)) {
                                    break;
                                }
                            }
                            clientSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String processRequest(String request) {
        System.out.println("Server receive message from > " + request);
        return request;
    }
}
