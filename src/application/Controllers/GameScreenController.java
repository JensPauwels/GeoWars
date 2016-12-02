package application.Controllers;

import application.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameScreenController {

    public Label highscoreLabel,waveLabel,lives;



    @FXML
    public void initialize() throws Exception {
        System.out.println("load");

    }

    @FXML
    public void goBack() throws Exception {
        Client.loadScreen("gameOptions");
    }


}
