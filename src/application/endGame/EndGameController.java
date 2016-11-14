package application.endGame;

/**
 * Created by jens on 13/11/16.
 */

import application.UserInterface;
import javafx.fxml.FXML;

public class EndGameController {

    @FXML
    public void goBack() {
        UserInterface.loadScreen("gameOptions");
    }



}
