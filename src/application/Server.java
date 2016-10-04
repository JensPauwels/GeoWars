package application;

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
    public static int[] ports = new int[2];
    private static Server testServer = new Server();

    private void makeConnection() throws IOException {
        for(int poort : ports){
            ss = new ServerSocket(poort);
            s = ss.accept();
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
        }

    }

    public static void main(String args[]) throws IOException {
        System.out.println("The server is live");

        testServer.makeConnection();
        while(true){
            testServer.ontvangBerichtjes();
        }
    }
    
    private void ontvangBerichtjes() throws IOException{
        System.out.println(din.readUTF());
    }

    public static void addPorts(int portnumber) throws IOException{
        ports[ports.length] = portnumber;
        testServer.makeConnection();
    }
}
