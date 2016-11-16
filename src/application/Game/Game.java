package application.Game;

import application.Engine.User;
import application.Engine.Engine;
import application.Game.Components.Attractor;
import application.Game.Components.BulletType.Bullet;
import application.Game.Components.BulletType.BulletFactory;
import application.Game.Components.Enemy;
import application.Game.Components.FollowerType.Follower;
import application.Game.Components.FollowerType.FollowerFactory;
import application.Game.Components.PowerUp;
import application.Game.Components.Vector2D;
import application.UserInterface;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Light;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class Game {

    private Pane playField;
    private Attractor mainCharacter;
    private List<Enemy> allEnemys = new LinkedList<>();
    private List<Bullet> allBullets = new LinkedList<>();
    private List<PowerUp> powerups = new LinkedList<>();
    private Scene scene;
    private AnimationTimer loop;
    private boolean up, down, left, right;
    private Vector2D location;
    private int highscore,enemysKilled = 0;
    private int wave = 1;
    private int enemyToKill = 10;
    private int xp = 10;
    private Label highscoreLabel,livesLabel, waveLabel;
    private Button stop = new Button("stop Game");
    private User currUser = Engine.getInstance().getCurrentUser();
    private Follower follower;



    public Game(Scene scene, BorderPane mainLayout) {
        this.scene = scene;
        playField = new Pane();
        playField.setMinWidth(800);
        playField.setMinHeight(600);
        mainLayout.setCenter(playField);
    }

    public void initGame() {

        addListeners();
        prepareGame();
        startGame();
        initFrameStuff();
    }


    private void initFrameStuff() {
        highscoreLabel = new Label("Highscore: " + highscore);
        waveLabel = new Label("Level: " + wave);
        playField.getChildren().addAll(highscoreLabel, stop, livesLabel, waveLabel);
        stop.setLayoutY(550);
        stop.setLayoutX(50);
        highscoreLabel.setLayoutX(120);
        highscoreLabel.setLayoutY(20);
        livesLabel.setLayoutX(350);
        livesLabel.setLayoutY(20);
        waveLabel.setLayoutX(500);
        waveLabel.setLayoutY(20);
    }



    private void prepareGame() {
        for (int i = 0; i < enemyToKill; i++) {addEnemy();}
        addMainCharacter();
        AddFollower();
        livesLabel = new Label("Lives: " + Integer.toString(mainCharacter.getLives()));
        spawnPowerUp();


    }

    private void updateHighscore() {
        highscore += xp;
        highscoreLabel.setText("Highscore: " + Integer.toString(highscore));
    }


    private void updateHighscoreToDataBase() {
        if (currUser.getHighscore() < highscore) {
            String query = "update users set highscore=" + highscore + " where username like '" + currUser.getUsername() + "'";
            Engine.getInstance().getDb().updateTable(query);
        }
    }


    private void updateWaves() {
        if (enemysKilled == enemyToKill) {
            enemyToKill += 10;
            wave++;
            enemysKilled = 0;
            waveLabel.setText("Level: " + Integer.toString(wave));
            for (int i = 0; i < enemyToKill + 1; i++) {addEnemy();}
            spawnPowerUp();
        }
    }

    private void spawnPowerUp(){

        Random random = new Random();


        location = new Vector2D(random.nextDouble() * 800, random.nextDouble()* 600);

        PowerUp pu = new PowerUp(playField,location);
        pu.setLayoutX(location.getX());
        pu.setLayoutY(location.getY());


        powerups.add(pu);

    }


    private void multiHighScore(){
       // xp = xp * 2;
        highscore = highscore +1;
        System.out.println(highscore);
    }




    private void startGame() {
        loop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (int i = 0; i < allBullets.size(); i++) {
                    Bullet bullet = allBullets.get(i);
                    checkIfBulletsIsOutOfDestination(bullet);
                    bullet.movement(bullet.getDestination(),true);
                    checkCollisionBullet(bullet);
                }

               // follower.movement(mainCharacter.getLocation(),false);
                moveEnemysTowardsMainCharacter();
                mainCharacter.display();
                moveChar();
                updateWaves();
                specialAbilityDonkey();
                //follower.movement(powerups.get(0).getLocation(),false);


            }
        };
        loop.start();
    }

    private void specialAbilityDonkey(){


        for (int i = 0; i < powerups.size() ; i++) {
            follower.movement(powerups.get(0).getLocation(),false);
            if(follower.coll(follower,powerups.get(0))){
                multiHighScore();
                powerups.get(0).setVisible(false);
                powerups.remove(powerups.get(0));
            }
        }
    }
    private void checkIfBulletsIsOutOfDestination(Bullet b){
        if(b.outOfDestination()){
            allBullets.remove(b);
        }
    }

    private void moveEnemysTowardsMainCharacter(){
        for (int i = 0; i < allEnemys.size(); i++) {
            Enemy enemy = allEnemys.get(i);
            enemy.movement(mainCharacter.getLocation(),true);
            checkCollisionEnemy(mainCharacter,enemy);
        }
    }

    private void killEnemy(Enemy e){
        e.setVisible(false);
        allEnemys.remove(e);
        updateHighscore();
        enemysKilled++;
    }


    private void checkCollisionBullet(Bullet b) {
        for (int i = 0; i <allEnemys.size() ; i++) {
            Enemy e = allEnemys.get(i);
            if (e.coll(b, e)) {
                b.setVisible(false);
                allBullets.remove(b);
                //spawnPowerUp();
                killEnemy(e);

            }
        }
    }


    private void checkCollisionEnemy(Attractor a, Enemy e) {
        if (e.coll(a, e)) {
            killEnemy(e);
            mainCharacter.setlives(mainCharacter.getLives() - 1);
            livesLabel.setText("Lives: " + Integer.toString(mainCharacter.getLives()));
            if(a.getLives() <= 0){
                stopGame();
            }
        }
    }

    private void addEnemy() {
        location = new Vector2D();
        Enemy enemy = new Enemy(playField, location);
        allEnemys.add(enemy);
    }

    private void addMainCharacter() {
        location = new Vector2D(400, 300);
        mainCharacter = new Attractor(playField, location);
    }

    private void AddFollower() {
        location = new Vector2D(350, 300);
        FollowerFactory followerFactory = new FollowerFactory();
        follower = followerFactory.makeFollower(playField, location);
    }

    private void addBullet(Vector2D loc) {
        location = new Vector2D(mainCharacter.getLocation().getX(), mainCharacter.getLocation().getY());
        BulletFactory bulletFactory = new BulletFactory();
        Bullet bullet = bulletFactory.makeBullet(playField, location, loc);
        allBullets.add(bullet);
    }

    private void moveChar() {
        double x = mainCharacter.getLocation().getX();
        double y = mainCharacter.getLocation().getY();

        if (up && y > 0) {mainCharacter.setLocation(x, y - 5);}
        else if (down && y < 555) {mainCharacter.setLocation(x, y + 5);}
        else if (left && x > 0) {mainCharacter.setLocation(x - 5, y);}
        else if (right && x < 775) {mainCharacter.setLocation(x + 5, y);}
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
        stop.setOnAction(e -> stopGame());
    }

    private void stopGame() {
        loop.stop();
        updateHighscoreToDataBase();
        UserInterface.loadScreen("endGame");
    }
}
