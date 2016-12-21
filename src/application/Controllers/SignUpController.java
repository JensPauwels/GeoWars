package application.Controllers;

/**
 * Created by jens on 21/12/2016.
 */

import application.Client;
import javafx.fxml.FXML;

public class SignUpController {

    @FXML
    public void loadLoginPage(){
        Client.loadScreen("LoginScreen");
    }

}
