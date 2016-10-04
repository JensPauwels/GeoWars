package application.highScores;

import application.DbConnection;
import application.Engine;
import application.Client;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

import java.io.IOException;

public class HighScoresController {

    private Engine instantie = Engine.getInstance();
    private DbConnection db = new DbConnection();
    public TableColumn highscore,highscoreUserName;
    //public TableView<String> table;



    @FXML
    public void initialize() {

        System.out.println(highscore.getText()+ highscoreUserName.getText() + " ");
        System.out.println("dit is de score van " +instantie.getUsername()+" : "+db.getHighscore(instantie.getUsername()));

    }

    @FXML
    private void loadGameOptions() throws IOException {
        Client.loadScreen("gameOptions");
    }




}
