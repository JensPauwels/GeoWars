package application.Engine;

import application.Engine.Game;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Created by jens on 03/12/2016.
 */
public class GameField {

    private Pane playfield;
    private Label highscoreLabel, livesLabel, waveLabel;
    private JFXButton stop = new JFXButton(" ");
    private JFXButton pause = new JFXButton(" ");



    public GameField(){
        this.playfield = new Pane();
        playfield.setMinWidth(800);
        playfield.setMinHeight(600);
        playfield.setId("gameScreen");
        highscoreLabel = new Label("0");
        waveLabel = new Label("1");
        livesLabel = new Label("3");
        this.playfield.getChildren().addAll(highscoreLabel, livesLabel, waveLabel,stop,pause);
        highscoreLabel.setLayoutX(100);
        highscoreLabel.setLayoutY(20);
        livesLabel.setLayoutX(300);
        livesLabel.setLayoutY(20);
        waveLabel.setLayoutX(550);
        waveLabel.setLayoutY(20);
        stop.setLayoutX(720);
        stop.setMinSize(70,50);
        pause.setLayoutX(680);
        pause.setMinSize(30,50);

    }

    public Pane getScreen(){
        return this.playfield;
    }

    public Button getStop() {
        return stop;
    }

    public Button getPause() {
        return pause;
    }
    public void updateHighscore(int highscore){
        highscoreLabel.setText(Integer.toString(highscore));
    }

    public void updateWaves(int waves){
        waveLabel.setText(Integer.toString(waves));
    }

    public void updateLives(int lives){
        livesLabel.setText(Integer.toString(lives));
    }


}
