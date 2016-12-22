package application.Controllers;

import application.Client;
import application.Engine.Engine;
import javafx.fxml.FXML;

public class GameOptionsController {


    @FXML
    private void loadHighScores() {
        Client.loadScreen("highScores");
    }

    @FXML
    private void loadMultiPlayer() {
        Client.loadScreen("multiPlayer");
    }

    @FXML
    private void loadSinglePlayer() {
        Client.loadScreen("singlePlayer");
    }

    @FXML
    private void loadSettings() {
        Client.loadScreen("settings");
    }

    @FXML
    private void logOut() throws Exception {
        Client.loadScreen("loginScreen");

    }


}


