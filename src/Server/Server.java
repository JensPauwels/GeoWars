package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ServerSocket ss;
    private static Socket s;
    private static DataInputStream din;
    private static DataOutputStream dout;

    public static void main(String args[]) throws IOException {
        System.out.println("The server is live");
        Server testServer = new Server();
        testServer.makeConnection(1201);
        while (true) {
            testServer.ontvangBerichtjes();
        }
    }

    private void makeConnection(int port) throws IOException {
        ss = new ServerSocket(port);
        s = ss.accept();
        din = new DataInputStream(s.getInputStream());
        dout = new DataOutputStream(s.getOutputStream());
    }

    private void ontvangBerichtjes() throws IOException {
        System.out.println(din.readUTF());
    }


}
