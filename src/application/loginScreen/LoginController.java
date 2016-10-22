package application.loginScreen;

import application.UserInterface;
import application.DataBase.DbConnection;
import application.Engine.Engine;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {


    private Engine instance = Engine.getInstance();
    private DbConnection db = instance.getDb();
    public TextField userNameInput;
    public PasswordField passwordInput;
    public Label alert;
    private String query;
    private String username;
    private String password;


    @FXML
    private void initialGame() throws Exception {
        this.username = userNameInput.getText();
        this.password = passwordInput.getText();
        System.out.println(username + ": is de username");
        instance.setUsername(username);

        query = "select * from users where username like '" + username+ "' and password like '" + password + "'";

        if (db.controle(query)) {
            instance.setUsername(username);
            instance.initCurrentUser();
            UserInterface.loadScreen("gameOptions");
        } else {
            alert.setText("Enter the right creds or register to our game");
        }
    }

    @FXML
    private void Register() throws Exception {

        this.username = userNameInput.getText();
        this.password = passwordInput.getText();

        query = "select * from users where username like '" + username+ "'";
        if (db.controle(query)) {
            alert.setText(username+" is already token");

        } else {
            alert.setVisible(false);

            query = "INSERT INTO users VALUES(default,'"+ username+"', '"+ password+"',0)";
            db.updateTable(query);

            query = "INSERT INTO settings VALUES(default,'"+username+"',false,false)";
            db.updateTable(query);
            UserInterface.loadScreen("gameOptions");
        }
    }
}
