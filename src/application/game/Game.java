package application.game;

import application.UserInterface;
import application.game.Components.*;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class Game implements Runnable{


    private Layer playfield;
    private Random random = new Random();
    private Attractor mainchar;
    private List<Enemy> allEnemys = new LinkedList<>();
    private Scene scene;
    private BorderPane mainLayout;
    private AnimationTimer loop;
    private Label test;
    private int highScore = 0;
    private List<Bullet> allBullets = new LinkedList<>();
    private boolean up, down, left, right;

    public Game(Scene scene, BorderPane mainLayout) {
        this.scene = scene;
        this.mainLayout = mainLayout;
    }

    public void initGame() {
        playfield = new Layer(800, 600);

        BorderPane gameScreen = UserInterface.createBorderPane("game/game.FXML");
        gameScreen.setCenter(playfield);
        test = new Label("0");
        mainLayout.setCenter(playfield);

        prepareGame();
        startGame();
        addListeners();
    }

    private void prepareGame() {
        for (int i = 0; i < 10; i++) {addEnemy();}
        addMainCharacter();
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

                for (Bullet b: allBullets) {
                    b.seek(b.getLocation());
                    b.move();
                    b.display();
                }
                mainchar.display();
                moveChar();
            }
        };

        loop.start();
    }

    private void gotHit(Enemy e) {

        if (e.bots(mainchar, e)) {
            System.out.println("ouch");
            highScore = highScore + 10;
           // updateHighscore();


        }
    }

    private void addEnemy() {
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
        allEnemys.add(enemy);

    }

    private void addBullet(Vector2D loc) {
        Layer layer = playfield;

        // random location
        double x = mainchar.getLocation().x;
        double y = mainchar.getLocation().y;
        // dimensions
        double width = 25;
        double height = width / 2.0;
        // create enemy data
        Vector2D location = new Vector2D(x, y);
        Vector2D velocity = new Vector2D(10, 10);
        Vector2D acceleration = new Vector2D(100, 100);

        // create sprite and add to layer
        Bullet bullet = new Bullet(layer, location, velocity, acceleration, width, height);
        bullet.setLocation(loc);
        allBullets.add(bullet);

    }




    private void addMainCharacter() {

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

    private void moveChar() {
        Vector2D loc = mainchar.getLocation();
        if (up) {mainchar.setLocation(loc.x, loc.y - 5);}
        else if (down) {mainchar.setLocation(loc.x, loc.y + 5);}
        else if (left) {mainchar.setLocation(loc.x - 5, loc.y);}
        else if (right) {mainchar.setLocation(loc.x + 5, loc.y);}
    }

    private void keyAction(KeyEvent e, Boolean bool) {
        KeyCode key = e.getCode();
        if (key == KeyCode.W || key == KeyCode.UP) {up = bool;}
        else if (key == KeyCode.S || key == KeyCode.DOWN) {down = bool;}
        else if (key == KeyCode.A || key == KeyCode.LEFT) {left = bool;}
        else if (key == KeyCode.D || key == KeyCode.RIGHT) {right = bool;}
    }

    private void addListeners() {
        scene.setOnMouseClicked(e -> {if (loop != null) {addBullet(new Vector2D(e.getX(), e.getY()));}});
        scene.setOnKeyPressed(e -> keyAction(e, true));
        scene.setOnKeyReleased(e -> keyAction(e, false));
    }


    @Override
    public void run() {

    }
}



