package application.singlePlayer;

import application.Client;
import application.Engine;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * Created by jensp on 3/10/2016.
 */
public class SinglePlayerController {
    private Engine instantie = Engine.getInstance();

    @FXML
    private void loadGameOptions() throws IOException {
        Client.loadScreen("gameOptions");
    }
}
