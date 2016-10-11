package application.loginScreen;

import application.Client;
import application.DbConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    private DbConnection db = new DbConnection();
    public TextField userNameInput;
    public PasswordField passwordInput;
    public Label alert;
    private String query;


    // no idea als er hier veel meer op moet => wat wel cool zou zijn is als de background hiervan dynamisch zou zijn :D


    @FXML
    private void initialGame() throws Exception {

        query = "select * from users where username like '" + userNameInput.getText() + "' and password like '" + passwordInput.getText() + "'";
        if (db.controle(query)) {
            Client.loadScreen("gameOptions");
        } else {
            alert.setText("Enter the right creds or register to our game");
        }
    }

    @FXML
    private void Register() throws Exception {
        query = "select * from users where username like '" + userNameInput.getText() + "'";

        if (db.controle(query)) {
            alert.setText("Username is already token");
        } else {
            alert.setVisible(false);
            query = "insert into users values('" + userNameInput.getText() + "','" + passwordInput.getText() + "',0)";
            db.updateTable(query);
            Client.loadScreen("gameOptions");
        }
    }
}
