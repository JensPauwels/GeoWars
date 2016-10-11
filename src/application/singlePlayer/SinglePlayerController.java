package application.singlePlayer;

import application.Client;
import application.GeoWars;
import javafx.fxml.FXML;

import java.io.IOException;

public class SinglePlayerController {

    @FXML
    public void initialize()  {
    }

    @FXML
    private void loadGameOptions() throws IOException {
        Client.loadScreen("gameOptions");

        //dit is een test voor github
    }
}
