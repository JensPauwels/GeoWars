package application.Controllers;

/**
 * Created by jens on 13/11/16.
 */

import application.Client;
import application.Engine.Engine;
import javafx.fxml.FXML;

public class EndGameController {

    public Engine engine = Engine.getInstance();

    @FXML
    private void initialize() {
        Client.loadScreen("gameOptions");
        System.out.println(engine.getHighscore());
        System.out.println(engine.getWave());
        System.out.println(Math.ceil(engine.getHighscore() / 100));
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
