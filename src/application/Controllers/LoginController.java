package application.Controllers;

import application.Client;
import application.Engine.DbConnection;
import application.Engine.Engine;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoginController {

    public JFXTextField userNameInput;
    public JFXPasswordField passwordInput;
    public Label alert;
    private Engine instance = Engine.getInstance();
    private DbConnection db = instance.getDb();
    private String query;
    private String username;
    private String password;


    @FXML
    private void initialGame() throws Exception {
        this.username = userNameInput.getText();
        this.password = passwordInput.getText();
        instance.setUsername(username);
        query = "select * from users where username like '" + username + "' and password like '" + password + "'";
        if (db.controle(query)) {
            instance.setUsername(username);
            instance.initCurrentUser();

            Client.loadScreen("gameOptions");
        } else {
            alert.setText("Enter the right creds or register to our application.Models");
        }
    }

    @FXML
    public void register(){
        Client.loadScreen("SignUpScreen");
    }


}
