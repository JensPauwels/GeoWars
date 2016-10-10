package application.gameOptions;

import application.Client;
import javafx.fxml.FXML;

import java.io.IOException;

public class GameOptionsController  {
    @FXML
    private void loadHighScores() {
        Client.loadScreen("highScores");
    }
    @FXML
    private void loadMultiPlayer() {
        Client.loadScreen("multiPlayer");
    }

    @FXML
    private void loadSinglePlayer()  {
        Client.loadScreen("singlePlayer");
    }

    @FXML
    private void loadSettings(){
        Client.loadScreen("settings");
    }

    @FXML
    private void logOut() {
        Client.loadScreen("loginScreen");
    }




}


