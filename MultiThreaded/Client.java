// package MultiThreaded;

import java.net.InetAddress;
import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Client {
    

    public static void main(String[] args) {
        Client client = new Client();
        for (int i = 0; i < 100; i++) {
            try {
                Thread thread = new Thread(client.getRunnable());
                thread.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Runnable getRunnable() {
        return new Runnable(){
            @Override
            public void run() {
                try {
                    int port = 8010;
                    InetAddress addresss = InetAddress.getByName("localhost");
                    Socket socket = new Socket(addresss, port);
                    try(PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), true);
                            BufferedReader fromSocket = new BufferedReader(
                                    new InputStreamReader(socket.getInputStream()));) {
                        String response = fromSocket.readLine();
                        System.out.println("Response from the socket is : " + response);
                    }catch(IOException e) {
                        e.printStackTrace();
                    } finally {
                        socket.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
