// package SingleThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public void run () throws IOException{
        try {
            int port = 8010;
            InetAddress addresss = InetAddress.getByName("localhost");
            Socket socket = new Socket(addresss, port);
            PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = fromSocket.readLine();
            System.out.println("Response from the socket is : " + response);
            toSocket.close();
            fromSocket.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
