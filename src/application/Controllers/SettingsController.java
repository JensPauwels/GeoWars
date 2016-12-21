package application.Controllers;

import application.Engine.Engine;
import application.Client;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;

public class SettingsController {
    private Engine instance = Engine.getInstance();




    @FXML
    public void loadGameOptions() {
        Client.loadScreen("gameOptions");
    }

    // in deze class moeten we allemaal settings bedenken oa
    // - Muziek of niet

    //DIT IS PAS UITBREIDNIG
    // - linken aan social media
    // - online/ ofline display
    // - personal records bekijken?
    // - total active gameplay bekijken?
    // - GameName changen? (pas om de maand mogelijk) => no fucking idea hoe je op tijd checkt :p
}
