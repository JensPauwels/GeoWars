package application.Controllers;

import application.Engine.Engine;
import application.UserInterface;
import javafx.fxml.FXML;

public class GameOptionsController {


    // is er een mogelijkheid voor de text van de button te krijgen waar je op klikt waardoor je in principe een dynamische function hebt?

    @FXML
    private void loadHighScores() {
        UserInterface.loadScreen("highScores");
    }

    @FXML
    private void loadMultiPlayer() {
        UserInterface.loadScreen("multiPlayer");
    }

    @FXML
    private void loadSinglePlayer() {
        UserInterface.loadScreen("singlePlayer");
    }

    @FXML
    private void loadSettings() {
        UserInterface.loadScreen("settings");
    }

    @FXML
    private void logOut() throws Exception {
        Engine.getInstance().saveCurrentUser();
        UserInterface.loadScreen("loginScreen");

    }


}


