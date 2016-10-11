package application.loginScreen;

import application.Client;
import application.DbConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

	private DbConnection db = new DbConnection();
	public TextField userNameInput;
	public PasswordField passwordInput;
	public Label  alert;

	@FXML
	private void initialGame() throws IOException {


		if (db.controle("select * from users where username like '" + userNameInput.getText() + "' and password like '" + passwordInput.getText()+ "'")) {
			Client.loadScreen("gameOptions");
        } else {
			alert.setText("Enter the right creds or register to our game");
		}
	}

	@FXML
	private void Register() throws IOException {

		if (db.controle("select * from users where username like '" + userNameInput.getText() + "'")) {
			alert.setText("Username is already token");
		} else {
			alert.setVisible(false);
			db.updateTable("insert into users values('" + userNameInput.getText() + "','" + passwordInput.getText() + "',0)");
			Client.loadScreen("gameOptions");
		}
	}
}
