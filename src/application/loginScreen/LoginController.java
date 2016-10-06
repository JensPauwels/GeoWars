package application.loginScreen;

import application.Client;
import application.DbConnection;
import application.Engine;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

	private DbConnection db = new DbConnection();
	private Engine instantie = Engine.getInstance();
	public TextField userNameInput;
	public PasswordField passwordInput;
	public Label  alert;
	private String username,password;

	@FXML
	private void initialGame() throws IOException {

		this.username = userNameInput.getText();
		this.password = passwordInput.getText();

		if (db.controle("select * from users where username like '" + username + "' and password like '" + password+ "'")) {
			Client.loadScreen("gameOptions");
            instantie.setUserName(username);
			System.out.println("dit is de username input :" + username);
        } else {
			alert.setText("Enter the right creds or register to our game");
		}
	}

	@FXML
	private void Register() throws IOException {
		if (db.controle("select * from users where username like '" + username + "'")) {
			alert.setText("Username is already token");
		} else {
			alert.setVisible(false);
			db.updateTable("insert into users values('" + username + "','" + password + "',default,0,0)");

			Client.loadScreen("gameOptions");
		}
	}



	


}
