package application.gameOptions;

import application.Engine;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import application.DbConnection;
import application.Client;

public class GameOptionsController  {

    public  Label username;
    private DbConnection db = new DbConnection();
    private Engine instantie = Engine.getInstance();

    @FXML
    public void initialize() {
            System.out.println(System.identityHashCode(instantie));
            System.out.println("test in GameControllers :"+ instantie.getUsername() );
            System.out.println(username);
            instantie.getUser().setPortnumber(db.getPortNumber(instantie.getUsername()));
            System.out.println( "Dit is je port number:"+ instantie.getUser().getPortnumber());
            username.setText(instantie.getUsername());
    }

    @FXML
    private void loadHighScores() throws IOException {
        Client.loadScreen("highScores");
    }

    @FXML
    private void loadMultiPlayer() throws IOException {
        Client.loadScreen("multiPlayer");
    }

    @FXML
    private void loadSinglePlayer() throws IOException {
        Client.loadScreen("singlePlayer");
    }

    @FXML
    private void logOut() throws IOException {
        Client.loadScreen("loginScreen");
    }


}


