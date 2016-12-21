package application.Controllers;

import application.Client;
import javafx.fxml.FXML;

public class SettingsController {

    @FXML
    public void loadGameOptions() {
        Client.loadScreen("gameOptions");
    }
}
