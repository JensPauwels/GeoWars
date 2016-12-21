package application.Controllers;

/**
 * Created by jens on 13/11/16.
 */

import application.Client;
import application.Engine.Engine;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class EndGameController {

    public Engine engine = Engine.getInstance();
    public Label yourScore,wave,highScore,coins,ranking;

    @FXML
    private void initialize() {
        Client.loadScreen("gameOptions");




        /*
        Font myFont = Font.loadFont(getClass().getResourceAsStream("../Styling/fonts/TrajanProRegular.ttf"), 12);
        yourScore.setFont(myFont);
        wave.setFont(myFont);
        ranking.setFont(myFont);
        highScore.setFont(myFont);*/

        yourScore.setText(Integer.toString(engine.getHighscore()));
        wave.setText(Integer.toString(engine.getWave()));
        coins.setText(Double.toString(Math.ceil(engine.getHighscore() / 100)));
    }

    @FXML
    public void homePage(){
        Client.loadScreen("gameOptions");


    }

    @FXML
    public void startNewGame(){
        Client.loadScreen("singlePlayer");
    }

}
