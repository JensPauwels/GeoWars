package application.loginScreen;

import application.Engine;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import application.DbConnection;
import application.Client;

public class Controller {

	private DbConnection db = new DbConnection();
	public TextField userNameInput;
	public PasswordField passwordInput;
	public Label  alert;
    private Engine instantie = Engine.getInstance();

    public Controller() throws IOException {
    }
    //dit is een test voor github

	@FXML
	private void initialGame() throws IOException {

		if (db.controle("select * from users where username like '" + userNameInput.getText() + "' and password like '" + passwordInput.getText()+ "'")) {
			Client.loadScreen("gameOptions");
            System.out.println("dit is de username input :" + userNameInput.getText());
            instantie.setUserName(userNameInput.getText());
            System.out.println("dit is een test als hij aanpast :" +instantie.getUsername());
        } else {
			alert.setText("Enter the right creds or register to our game");
		}
	}

	@FXML
	private void Register() throws IOException {
		if (db.controle("select * from users where username like '" + userNameInput.getText() + "'")) {
			alert.setText("Username is already token");
		} else {
			//alert.setVisible(false);
			//System.out.println("insert into users values('" + userNameInput.getText() + "','" + passwordInput.getText() + "',default,0,0)");
			//db.updateTable("insert into users values('" + userNameInput.getText() + "','" + passwordInput.getText() + "',default,0,0)");

			Client.loadScreen("gameOptions");
		}
	}



	


}
