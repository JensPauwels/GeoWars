package application.game;

import application.game.Components.*;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class Game{


    private Layer playfield;
    private Random random = new Random();
    private Attractor mainchar;
    private List<Enemy> allEnemys = new LinkedList<>();
    private Scene scene;
    private BorderPane mainLayout;
    private AnimationTimer loop;
    private int highScore = 0;
    private List<Bullet> allBullets = new LinkedList<>();
    private boolean up, down, left, right;
    private Vector2D location;

    public Game(Scene scene, BorderPane mainLayout) {
        this.scene = scene;
        this.mainLayout = mainLayout;
    }

    public void initGame() {
        playfield = new Layer(800, 600);
        mainLayout.setCenter(playfield);
        prepareGame();
        startGame();
        addListeners();

    }

    private void prepareGame() {
        for (int i = 0; i < 10; i++) { addEnemy(); }
        addMainCharacter();
    }

    private void movement(Sprite s,Vector2D target){
        s.seek(target);
        s.move();
        s.display();
    }

    private void startGame() {
        loop = new AnimationTimer() {
            @Override
            public void handle(long now) {

                for (Enemy e : allEnemys) {
                    movement(e,mainchar.getLocation());
                    gotHit(e);
                }

                for (Bullet b: allBullets) {
                    movement(b,b.getDestination());
                }

                mainchar.display();
                moveChar();
            }
        };
        loop.start();
    }

    private void gotHit(Enemy e) {
        if (e.bots(mainchar, e)) {
            highScore = highScore + 10;
        }
    }

    private void addEnemy() {
        location = new Vector2D(random.nextDouble() * 800, random.nextDouble() * 600);
        Enemy enemy = new Enemy(playfield, location);
        allEnemys.add(enemy);
    }

    private void addBullet(Vector2D loc) {
        location = new Vector2D(mainchar.getLocation().x,mainchar.getLocation().y);
        Bullet bullet = new Bullet(playfield, location);



        bullet.setDestination(loc);
        allBullets.add(bullet);
    }

    private void addMainCharacter() {
        location = new Vector2D(400, 300);
        mainchar = new Attractor(playfield, location);
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
        scene.setOnMouseClicked(e -> {if (loop != null) {addBullet(new Vector2D(e.getX(), e.getY()));}
            System.out.println("x "+ e.getX() + " y" + e.getY());});
        scene.setOnKeyPressed(e -> keyAction(e, true));
        scene.setOnKeyReleased(e -> keyAction(e, false));
    }

}
