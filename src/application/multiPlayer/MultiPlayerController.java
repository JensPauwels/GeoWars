package application.multiPlayer;

import application.DbConnection;
import application.Engine;
import application.Client;
import application.Server;
import javafx.fxml.FXML;

import java.io.IOException;


public class MultiPlayerController {
    private Engine instantie = Engine.getInstance();
    private DbConnection db = new DbConnection();
    private String username = instantie.getUser().getUsername();
    private int port;

    @FXML
    public void initialize() throws IOException {
        this.port = db.getPortNumber(username);
        JoinMultiPlayer test = new JoinMultiPlayer(username,port);
        test.sendText(username);
    }

    @FXML
    private void loadGameOptions() throws IOException {
        Client.loadScreen("gameOptions");
    }
}
