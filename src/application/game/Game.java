package application.game;

import application.UserInterface;
import application.game.Components.Attractor;
import application.game.Components.Enemy;
import application.game.Components.Layer;
import application.game.Components.Vector2D;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Game {


    private Layer playfield;
    private Random random = new Random();
    private Attractor mainchar;
    private List<Enemy> allEnemys = new ArrayList<>();
    private Scene scene;
    private BorderPane mainLayout;
    private AnimationTimer loop;
    private Label test;
    private int highscore = 0;

    public Game(Scene scene, BorderPane mainLayout) {
        this.scene = scene;
        this.mainLayout = mainLayout;
    }

    public void initGame() {
        playfield = new Layer(600, 800);
        BorderPane gameScreen = UserInterface.createBorderPane("game/game.FXML");
        gameScreen.setCenter(playfield);
        test = new Label("0");
        gameScreen.setLeft(new VBox(test));
        mainLayout.setCenter(gameScreen);


        prepareGame();
        addListeners();
        startGame();
    }

    private void prepareGame() {
        for (int i = 0; i < 10; i++) {
            addVehicles();
        }
        addAttractors();
    }

    private void updateHighscore() {
        test.setText(Integer.toString(highscore));
    }

    private void startGame() {
        loop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (Enemy e : allEnemys) {
                    e.seek(mainchar.getLocation());
                    e.move();
                    e.display();
                    gotHit(e);
                }
                addVehicles(); // nog ervoor zorgen dat het om de zoveel sec begint
                mainchar.display();
            }
        };

        loop.start();

    }

    private void gotHit(Enemy e) {
        if (e.bots(mainchar, e)) {
            System.out.println("ouch");
            highscore = highscore + 10;
            updateHighscore();
        }
    }

    private void addVehicles() {
        Layer layer = playfield;

        // random location
        double x = random.nextDouble() * 800;
        double y = random.nextDouble() * 600;

        // dimensions
        double width = 25;
        double height = width / 2.0;

        // create enemy data
        Vector2D location = new Vector2D(x, y);
        Vector2D velocity = new Vector2D(0, 0);
        Vector2D acceleration = new Vector2D(0, 0);

        // create sprite and add to layer
        Enemy enemy = new Enemy(layer, location, velocity, acceleration, width, height);

        // register vehicle
        allEnemys.add(enemy);

    }

    private void addAttractors() {

        Layer layer = playfield;

        // center attractor
        double x = 400;
        double y = 300;

        // dimensions
        double width = 25;
        double height = 25;

        // create attractor data
        Vector2D location = new Vector2D(x, y);
        Vector2D velocity = new Vector2D(0, 0);
        Vector2D acceleration = new Vector2D(0, 0);

        // create attractor and add to layer
        mainchar = new Attractor(layer, location, velocity, acceleration, width, height);
    }

    private void addListeners() {
        scene.setOnMouseClicked(e -> {
            if (loop != null) {
                System.out.println("fire fire");
            }
        });

        scene.setOnKeyPressed(e -> {
            KeyCode key = e.getCode();
            Vector2D loc = mainchar.getLocation();
            if (key == KeyCode.W) {
                mainchar.setLocation(loc.x, loc.y - 25);
            } else if (key == KeyCode.S) {
                mainchar.setLocation(loc.x, loc.y + 25);
            } else if (key == KeyCode.A) {
                mainchar.setLocation(loc.x - 25, loc.y);
            } else if (key == KeyCode.D) {
                mainchar.setLocation(loc.x + 25, loc.y);
            }
        });
    }


}
