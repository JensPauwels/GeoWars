package application.singlePlayer;

import application.Client;
import application.Engine;
import javafx.fxml.FXML;

import java.io.IOException;

public class SinglePlayerController {

    @FXML
    private void loadGameOptions() throws IOException {
        Client.loadScreen("gameOptions");
    }
}
