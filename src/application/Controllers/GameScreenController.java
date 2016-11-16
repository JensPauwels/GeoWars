package application.Controllers;

import application.UserInterface;
import javafx.fxml.FXML;

public class GameScreenController {


    @FXML
    public void initialize() throws Exception {
        System.out.println("load");

    }

    @FXML
    public void goBack() throws Exception {
        UserInterface.loadScreen("gameOptions");
    }


}
