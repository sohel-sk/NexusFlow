// package MultiThreaded;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {


    public Consumer<Socket> getConsumer() {
        return (clientSocket) ->{
            try {
                PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream());
                toClient.println("Welcome to the multi-threaded server!");
                toClient.close();
                clientSocket.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    public static void main(String[] args) {
        int port = 8010;
        Server server = new Server();
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(10000);
            System.out.println("Server is running in multi-threaded mode on Port " + port);
            while (true) {
                Socket acceptedSocket = serverSocket.accept();
                System.out.println(
                        "Accepted connection from " + acceptedSocket.getInetAddress() + ":" + acceptedSocket.getPort());
                Thread thread = new Thread(() -> 
                    server.getConsumer().accept(acceptedSocket));
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
