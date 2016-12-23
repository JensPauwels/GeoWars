package application.Controllers;

import application.Client;
import application.Engine.Database;
import application.Engine.Engine;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class LoginController {

    public JFXTextField userNameInput;
    public JFXPasswordField passwordInput;
    private Database database = Database.getInstance();



    @FXML
    private void initialGame() throws Exception {
        if(database.checkUser(userNameInput.getText(),passwordInput.getText())){
            Engine.getInstance().setUsername(userNameInput.getText());
            Client.loadScreen("gameOptions");
        }
    }

    @FXML
    public void register(){
        Client.loadScreen("SignUpScreen");
    }


}
