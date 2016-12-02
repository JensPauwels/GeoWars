package application.Controllers;

import application.Engine.Engine;
import application.Client;
import javafx.fxml.FXML;

public class GameOptionsController {


    // is er een mogelijkheid voor de text van de button te krijgen waar je op klikt waardoor je in principe een dynamische function hebt?

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
        Engine.getInstance().saveCurrentUser();
        Client.loadScreen("loginScreen");

    }


}


