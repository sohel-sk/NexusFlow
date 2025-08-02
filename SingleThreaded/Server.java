// package SingleThreaded;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void run() throws IOException {
        try{
            int port = 8010; 
            ServerSocket socket = new ServerSocket(port);
            socket.setSoTimeout(10000);
            while (true) {
                System.out.println("Server is running in single-threaded mode. on Port " + port);
                Socket acceptedSocket = socket.accept();
                System.out.println(
                        "Accepted connection from " + acceptedSocket.getInetAddress() + ":" + acceptedSocket.getPort());
                PrintWriter toClient = new PrintWriter(acceptedSocket.getOutputStream(), true);
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedSocket.getInputStream()));
                toClient.println("Welcome to the server!");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.run();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}