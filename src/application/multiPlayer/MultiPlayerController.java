package application.multiPlayer;

import application.Engine;
import application.Client;
import javafx.fxml.FXML;

import java.io.IOException;


public class MultiPlayerController {
    private Engine instantie = Engine.getInstance();

    @FXML
    public void initialize() throws IOException {
        instantie.getClient().sendText(instantie.getUsername());
    }

    @FXML
    private void loadGameOptions() throws IOException {
        Client.loadScreen("gameOptions");
    }
}
