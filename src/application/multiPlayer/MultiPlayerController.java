package application.multiPlayer;

import application.Client;
import application.DbConnection;
import javafx.fxml.FXML;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class MultiPlayerController {

    //hier verder over opzoeken hoe we het mogelijk maken dat 2 clients met elkaar communiceren dus niet enkel een server met een client maar 1 server
    // die while(true) nagaat als er clients bijkomen => ook gelimiteerd naar hoeveel clients er mogen => als uitbreiding mss zelf opties geven aan de multiplayer
    // zoals moeilijkheidsgraad, revive options etc

    private DbConnection db = new DbConnection();
    private String username = "jos";
    private static Socket s;
    private static DataOutputStream dout;

    @FXML
    public void initialize() {
    }

    @FXML
    private void loadGameOptions() {
        Client.loadScreen("gameOptions");
    }

    private void joinMultiPlayer() {
        makeConnection();
    }

    private void sendText(String username) {
        try {
            dout.writeUTF(username + " has joined the lobby");
        } catch (IOException e) {
            System.out.println("problem in sendText");
        }
    }

    private void makeConnection() {
        try {
            s = new Socket("127.0.0.1", 1201);
            dout = new DataOutputStream(s.getOutputStream());
        } catch (IOException e) {
            System.out.println("makeConnection");
        }
    }
}
