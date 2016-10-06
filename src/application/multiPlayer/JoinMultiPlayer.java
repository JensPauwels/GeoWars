package application.multiPlayer;

import application.DbConnection;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class JoinMultiPlayer {
    private static Socket s;
    private static DataOutputStream dout;



    public JoinMultiPlayer(String username, int port) throws IOException {
        makeConnection(port);
    }

    public void sendText(String username) throws IOException{
        dout.writeUTF( username+" has joined the lobby" );
    }

    private void makeConnection(int port) throws IOException{
        s = new Socket("127.0.0.1", port);
        dout = new DataOutputStream(s.getOutputStream());
    }

}
