package application.Controllers;

/**
 * Created by jens on 21/12/2016.
 */

import application.Client;
import application.Engine.Database;
import application.Engine.Engine;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class SignUpController {

    public JFXPasswordField passwordInput, confirmPasswordInput;
    public JFXTextField userNameInput, email, confirmEmail;
    private Database db = Database.getInstance();

    @FXML
    public void loadLoginPage() {
        Client.loadScreen("LoginScreen");
    }

    @FXML
    public void submitUser() {
        if (!db.checkOnDuplicatedUser(userNameInput.getText()) && passwordInput.getText().equals(confirmPasswordInput.getText())) {
            db.saveUser(userNameInput.getText(), passwordInput.getText());
            Engine.getInstance().setUsername(userNameInput.getText());
            Client.loadScreen("gameOptions");
        }
    }
}
