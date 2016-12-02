package application.Controllers;

/**
 * Created by jens on 13/11/16.
 */

import application.Client;
import javafx.fxml.FXML;

public class EndGameController {

    @FXML
    public void goBack() {
        Client.loadScreen("gameOptions");
    }


}
