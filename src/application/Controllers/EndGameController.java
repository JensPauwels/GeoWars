package application.Controllers;

/**
 * Created by jens on 13/11/16.
 */

import application.Client;
import application.Engine.Database;
import application.Engine.Engine;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class EndGameController {

    public Engine engine = Engine.getInstance();
    public Label yourScore,wave,highScore,coins,ranking;
    private Database db = Database.getInstance();

    @FXML
    private void initialize() {
        int highScoreFromUser = db.getHighscoreFromUser(engine.getUsername());
        Client.loadScreen("gameOptions");
        yourScore.setText(Integer.toString(engine.getHighscore()));
        highScore.setText(Integer.toString(highScoreFromUser));
        wave.setText(Integer.toString(engine.getWave()));
        ranking.setText(Integer.toString(db.whatRankAmI(highScoreFromUser)));
        coins.setText("+"+Double.toString(Math.ceil(engine.getHighscore() / 100)));
    }

    @FXML
    public void homePage(){
        Client.loadScreen("gameOptions");
    }

    @FXML
    public void startNewGame(){
        Client.loadScreen("SinglePlayer");

    }


    @FXML
    public void shareScore(){
        Client c = new Client();
        c.open();
    }

}
