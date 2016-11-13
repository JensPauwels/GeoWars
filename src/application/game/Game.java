package application.game;

import application.Engine.Engine;
import application.Engine.User;
import application.UserInterface;
import application.game.Components.*;
import application.game.Components.BulletType.Bullet;
import application.game.Components.BulletType.BulletFactory;
import application.game.Components.FollowerType.Follower;
import application.game.Components.FollowerType.FollowerFactory;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class Game {

    private Layer playField;
    private Random random = new Random();
    private Attractor mainCharacter;
    private List<Enemy> allEnemys = new LinkedList<>();
    private List<Bullet> allBullets = new LinkedList<>();
    private BulletFactory bulletFactory = new BulletFactory();
    private FollowerFactory followerFactory = new FollowerFactory();
    private Scene scene;
    private BorderPane mainLayout;
    private AnimationTimer loop;
    private boolean up, down, left, right;
    private Vector2D location;
    private int highscore = 0;
    private int wave = 1;
    private int enemyToKill = 10;
    private int enemysKilled = 0;
    private Label highscoreLabel = new Label("Highscore: "+highscore);
    private Label livesLabel;
    private Label waveLabel = new Label("Level: "+wave);

    private Button stop = new Button("stop game");
    private Engine instance = Engine.getInstance();
    private User currUser = instance.getCurrentUser();
    private Follower follower;

    public Game(Scene scene, BorderPane mainLayout) {
        this.scene = scene;
        this.mainLayout = mainLayout;
    }

    public void initGame() {
        playField = new Layer(800, 600);
        mainLayout.setCenter(playField);
        addListeners();
        prepareGame();
        startGame();
        initFrameStuff();
    }


    private void initFrameStuff(){
        playField.getChildren().addAll(highscoreLabel,stop,livesLabel,waveLabel);
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
        for (int i = 0; i < enemyToKill; i++) { addEnemy(); }
        addMainCharacter();
        AddFollower();
        livesLabel = new Label("Lives: "+Integer.toString(mainCharacter.getLives()));
    }

    private void updateHighscore(){
        highscore +=10;
        highscoreLabel.setText("Highscore: "+Integer.toString(highscore));
    }


    private void updateHighscoreToDataBase(){
        if(currUser.getHighscore() < highscore){
            String query = "update users set highscore="+highscore+" where username like '"+currUser.getUsername()+"'";
            instance.getDb().updateTable(query);
        }
    }

    private void movement(Sprite s, Vector2D target,boolean angles){
        s.seek(target);
        if(angles){s.move();
        }
        else {s.moveWithoutTurn();
        }
        s.display();
    }

    private  void updateWaves(){
        if(enemysKilled ==  enemyToKill){
            enemyToKill += 10;
            wave++;
            enemysKilled = 0;
            waveLabel.setText("Level: "+Integer.toString(wave));
            for (int i = 0; i < enemyToKill+1 ; i++) {
                addEnemy();
            }
        }
    }

    private void startGame() {
        loop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                movement(follower,mainCharacter.getLocation(),false);

                for (int i = 0; i<allEnemys.size(); i++) {
                    movement(allEnemys.get(i), mainCharacter.getLocation(),true);
                    if(checkCollisionEnemy(mainCharacter,allEnemys.get(i))&& mainCharacter.getLives()<=0){
                        stopGame();
                    }
                }

                for (int j = 0; j<allBullets.size();j++) {
                    Bullet b = allBullets.get(j);
                    movement(b,b.getDestination(),true);
                    if(b.outOfDestination()){allBullets.remove(b);}
                    for (int i = 0; i< allEnemys.size();i++) {
                        checkCollisionBullet(allEnemys.get(i),b);
                    }
                 }

            mainCharacter.display();
            moveChar();
            }
        };
        loop.start();
    }

    // herhaling
    private boolean checkCollisionBullet(Enemy e,Bullet b){
        Boolean tmp = false;
        if (e.shoot(b,e)) {
            b.setVisible(false);
            e.setVisible(false);
            allEnemys.remove(e);
            allBullets.remove(b);
            updateHighscore();
            tmp  = true;
            enemysKilled++;
        }
        return tmp;
    }

    private  boolean checkCollisionEnemy (Attractor a, Enemy e){
        boolean tmp = false;
        if(e.CollisionMainChar(a,e)){
            e.setVisible(false);
            allEnemys.remove(e);
            enemysKilled++;
            updateHighscore();;
            mainCharacter.setlives(mainCharacter.getLives()-1);
            livesLabel.setText("Lives: "+Integer.toString(mainCharacter.getLives()));
            tmp = true;
        }
        return tmp;
    }

    private void addEnemy() {
        double x = random.nextDouble() * 800;
        double y = random.nextDouble() * 600;

        if(x <400){x-=500;}
        else{x+=500;}
        if(y<300){y-=300;}
        else{y+=300;}

        location = new Vector2D(x,y );
        Enemy enemy = new Enemy(playField, location);
        allEnemys.add(enemy);
    }

    private void addMainCharacter() {
        location = new Vector2D(400, 300);
        mainCharacter = new Attractor(playField, location);
    }

    private void AddFollower(){
        location = new Vector2D(350,300);
        follower = followerFactory.makeFollower(playField,location);
        System.out.println(follower.getName());
    }

    private void addBullet(Vector2D loc) {
        location= new Vector2D(mainCharacter.getLocation().x,mainCharacter.getLocation().y);
        Vector2D mouseLoc = new Vector2D(loc.x, loc.y);
        Bullet bullet = bulletFactory.makeBullet(playField,location,mouseLoc);
        allBullets.add(bullet);
    }

    private void moveChar() {
        updateWaves();
        Vector2D loc = mainCharacter.getLocation();
        if (up && loc.y>0) {mainCharacter.setLocation(loc.x, loc.y - 5);}
        else if (down && loc.y <555) {mainCharacter.setLocation(loc.x, loc.y + 5);}
        else if (left && loc.x>0) {mainCharacter.setLocation(loc.x - 5, loc.y);}
        else if (right && loc.x<775) {mainCharacter.setLocation(loc.x + 5, loc.y);}
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

        stop.setOnAction(e ->stopGame());
    }

    private void stopGame(){
        loop.stop();
        updateHighscoreToDataBase();
        UserInterface.loadScreen("endGame");
    }

}
