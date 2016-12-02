package application.FXML;

import application.Engine.Game;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Created by jens on 03/12/2016.
 */
public class GameField {

    private Pane playfield;
    private Label highscoreLabel, livesLabel, waveLabel;
    private Button stop = new Button("stop Models");
    private Button pauze = new Button("pauze");
    private Button resume = new Button("resume");


    public GameField(){
        this.playfield = new Pane();
        playfield.setMinWidth(800);
        playfield.setMinHeight(600);
        highscoreLabel = new Label("Highscore: ");
        waveLabel = new Label("Level: ");
        livesLabel = new Label("Lives: ");
        this.playfield.getChildren().addAll(highscoreLabel, stop, livesLabel, waveLabel, pauze, resume);

        highscoreLabel.setLayoutX(120);
        highscoreLabel.setLayoutY(20);
        livesLabel.setLayoutX(350);
        livesLabel.setLayoutY(20);
        waveLabel.setLayoutX(500);
        waveLabel.setLayoutY(20);


        stop.setLayoutY(550);
        stop.setLayoutX(50);
        pauze.setLayoutY(550);
        pauze.setLayoutX(150);
        resume.setLayoutX(200);
        resume.setLayoutY(550);

    }

    public Pane getScreen(){
        return this.playfield;
    }

    public Button getStop() {
        return stop;
    }

    public Button getPauze() {
        return pauze;
    }

    public Button getResume() {
        return resume;
    }
}
