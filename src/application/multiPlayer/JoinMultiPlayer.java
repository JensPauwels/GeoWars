package application.multiPlayer;

import application.DbConnection;
import application.Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class JoinMultiPlayer {
    private static Socket s;
    private static DataOutputStream dout;
    private String username;
    private int port;
    private DbConnection db = new DbConnection();


    public JoinMultiPlayer(String username, int port) {

        this.username = username;
        this.port = port;
        //Server.addPorts(port);
        //makeConnection(port);

        //System.out.println("dit is je poort nummer "+db.getPortNumber(username));

    }


    public void sendText(String username) throws IOException{
        dout.writeUTF( username+" has joined the lobby" );
    }

    private void makeConnection(int port) throws IOException{
        s = new Socket("127.0.0.1", port);
        dout = new DataOutputStream(s.getOutputStream());
    }

}
