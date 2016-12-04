package application.Engine;

import application.Client;
import application.FXML.GameField;
import application.Models.AttractorType.Attractor;
import application.Models.BossType.Boss;
import application.Models.BulletType.*;
import application.Models.EnemyType.Enemy;
import application.Models.FollowerType.Follower;
import application.Models.PowerUpType.PowerUp;
import application.Models.Vector2D;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class Game {

    private FakeDataBase fakeDataBase = new FakeDataBase();
    private Pane playField;
    private Attractor mainCharacter;
    private Follower follower;

    private List<Enemy> allEnemys = new LinkedList<>();
    private List<Bullet> allBullets = new LinkedList<>();
    private List<Bullet> BulletFromBoss = new LinkedList<>();
    private List<PowerUp> powerups = new LinkedList<>();
    private List<Boss> bosses = new LinkedList<>();
    private GameField gameField = new GameField();
    private Engine instance = Engine.getInstance();
    private Random random = new Random();

    private Vector2D mouseLocation,location = new Vector2D(0, 0);
    private long time, shootersTime,bossSpeed = System.currentTimeMillis();
    private Scene scene;
    private AnimationTimer loop;
    private boolean up, down, left, right, shooting;
    private int highScore, enemysKilled,angle = 0;
    private int enemyToKill = 5;

    private double shooterSpeed = 1;

    public Game(Scene scene, BorderPane mainLayout) {
        this.scene = scene;
        playField = gameField.getScreen();
        mainLayout.setCenter(playField);
    }

    private void startGame() {
        loop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                mainCharacter.display();
                moveChar();
                handleBullets();
                moveEnemysTowardsMainCharacter();
                updateWaves();
                doSpecialAbility();
                shoot();
                checkColOnPowerUps();
                handleBoss();

            }
        };
        loop.start();
    }

    public void initGame() {
        addListeners();
        prepareGame();
        startGame();
    }

    private void prepareGame() {
        //for (int i = 0; i < 5; i++) {addEnemy();}
        mainCharacter = new Attractor(playField);
        follower = instance.makeFollower(playField);
        makeBoss();

    }

    private void addEnemy() {
        allEnemys.add(new Enemy(playField));
    }

    private void handleBoss(){
        for (int i = 0; i < bosses.size(); i++) {
            Boss boss = bosses.get(i);
            shootWithBoss(boss);
            boss.changeLocation();
        }
    }

    private void shootWithBoss(Boss boss){
        if(bossSpeed + 5000 < System.currentTimeMillis()){
            location = new Vector2D(mainCharacter.getLocation().getX(), mainCharacter.getLocation().getY());
            Vector2D bosslocation = new Vector2D(boss.getLocation().getX(),boss.getLocation().getY());
            Bullet bullet = instance.makeBullet(playField, bosslocation, location);
            BulletFromBoss.add(bullet);
            bossSpeed = System.currentTimeMillis();
        }
    }

    private void makeBoss() {
        Boss boss = instance.makeBoss(playField,new Vector2D(300,200));
        bosses.add(boss);
    }

    private void checkCollisionBoss(Bullet b){
        for (int i = 0; i < bosses.size(); i++) {
            Boss boss = bosses.get(i);
            if(boss.coll(boss,b)){
                if(boss.getHealth() >1){
                    boss.setHealth(boss.getHealth() -1);
                    b.setVisible(false);
                    allBullets.remove(b);
                }
                else {
                    boss.setVisible(false);
                    bosses.remove(boss);
                }
            }
        }
    }

    private void addBullet(Vector2D loc) {
        location = new Vector2D(mainCharacter.getLocation().getX(), mainCharacter.getLocation().getY());
        Bullet bullet = instance.makeBullet(playField, location, loc);
        allBullets.add(bullet);
    }

    private void spawnPowerUp() {

        location = new Vector2D(random.nextDouble() * 800, random.nextDouble() * 600);
        PowerUp pu = new PowerUp(playField, location);
        pu.display();
        powerups.add(pu);
    }

    private void updateWaves() {
        if (enemysKilled == enemyToKill) {
            enemyToKill += instance.getIncrease();
            enemysKilled = 0;
            for (int i = 0; i < enemyToKill + 1; i++) {addEnemy();}
            spawnPowerUp();
        }
    }

    private void multiHighScore() {
        highScore = highScore + 1;
    }

    private void trippleArrow() {
        addBullet(new Vector2D(mouseLocation.getX(), mouseLocation.getY() + 100));
        addBullet(mouseLocation);
        addBullet(new Vector2D(mouseLocation.getX(), mouseLocation.getY() - 100));
    }

    private void checkColOnPowerUps(){
        for (int i = 0; i< powerups.size();i++) {
            if (powerups.get(i).coll(mainCharacter, powerups.get(i))) {
                powerups.get(i).setVisible(false);
                powerups.remove(powerups.get(i));
                handlePowerUps();
            }
        }
    }

    private void handlePowerUps(){
        Random r = new Random();
        int number = r.nextInt(5);
        switch (number){
            case 1:
                trippleArrow();
                break;
            case 2:
                shooterSpeed =0.5;
                break;
            case 3:
                shooterSpeed =1.5;
                break;
            case 4:
                multiHighScore();

        }
    }

    private void doSpecialAbility() {
        switch (instance.getFollowerType()) {
            case "Donkey":
                specialAbilityDonkey();
                break;
            case "Horse":
                specialAbilityHorse();
                break;
            case "Unicorn":
                specialAbilityUnicorn();
                break;
            default:
        }
    }

    private void specialAbilityHorse(){

        follower.movement(follower.getDestionation(),false);

        if (Math.round(follower.getLocation().getX()) == Math.round(follower.getDestionation().getX()) && Math.round(follower.getLocation().getY()) == Math.round(follower.getDestionation().getY())) {
            angle = angle + 1;
            double x =mainCharacter.getLocation().getX() + Math.cos(angle) * 75;
            double y = mainCharacter.getLocation().getY() + Math.sin(angle) * 75;
            follower.setDestionation(new Vector2D(x,y));
        }

        if(time + 500 < System.currentTimeMillis()){
            for (int i = 0; i < allEnemys.size(); i++) {
                if(allEnemys.get(i).coll(allEnemys.get(i),follower)){
                    allEnemys.get(i).setVisible(false);
                    allEnemys.remove(allEnemys.get(i));
                    time = System.currentTimeMillis();
                }
            }
        }
    }

    private void specialAbilityUnicorn(){
        follower.movement(mainCharacter.getLocation(), false);
        if(time + 5000 < System.currentTimeMillis()){
            location = new Vector2D(follower.getLocation().getX(), follower.getLocation().getY());
            Bullet bullet = new UnicornHorn(playField,location,mouseLocation);
            allBullets.add(bullet);
            time = System.currentTimeMillis();
        }
    }

    private void specialAbilityDonkey() {
        if (time + 10000 < System.currentTimeMillis()) {
            for (int i = 0; i < powerups.size(); i++) {
                follower.movement(powerups.get(0).getLocation(), false);
                if (follower.coll(follower, powerups.get(0))) {
                    handlePowerUps();
                    powerups.get(0).setVisible(false);
                    powerups.remove(powerups.get(0));
                    time = System.currentTimeMillis();
                }
            }
        }
        else {
            follower.movement(mainCharacter.getLocation(), false);
        }
    }

    private void checkCollisionBullet(Bullet b) {
        for (int i = 0; i < allEnemys.size(); i++) {
            Enemy e = allEnemys.get(i);
            if (e.coll(b, e)) {
                b.setVisible(false);
                allBullets.remove(b);
                killEnemy(e);
            }
        }
    }

    private void moveEnemysTowardsMainCharacter() {
        for (int i = 0;i < allEnemys.size();i++) {
            Enemy enemy = allEnemys.get(i);
            enemy.movement(mainCharacter.getLocation(), true);
            checkCollisionEnemy(mainCharacter, enemy);
        }
    }

    private void handleBullets() {

        for (int i = 0; i < BulletFromBoss.size(); i++) {
            Bullet bullet = BulletFromBoss.get(i);
            if (bullet.outOfDestination()) BulletFromBoss.remove(bullet);
            bullet.movement(bullet.getDestination(), true);
        }

        for (int i = 0; i < allBullets.size(); i++) {
            Bullet bullet = allBullets.get(i);
            if (bullet.outOfDestination()) allBullets.remove(bullet);
            bullet.movement(bullet.getDestination(), true);
            checkCollisionBullet(bullet);
            checkCollisionBoss(bullet);
        }
    }

    private void checkCollisionEnemy(Attractor a, Enemy e) {
        if (e.coll(a, e)) {
            killEnemy(e);
            mainCharacter.setlives(mainCharacter.getLives() - 1);
            if (a.getLives() <= 0) {stopGame();}
        }
    }

    private void killEnemy(Enemy e) {
        e.setVisible(false);
        allEnemys.remove(e);
        updateHighscore();
        enemysKilled++;
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

    private void shoot() {
        if (shooting && (shootersTime + fakeDataBase.getTimeFromWeapon(instance.getWeaponType()) * shooterSpeed) < System.currentTimeMillis()) {
            addBullet(mouseLocation);
            shootersTime = System.currentTimeMillis();
        }
    }

    private void moveMouseLoc(MouseEvent e ){
        mouseLocation = new Vector2D(e.getX(), e.getY());
    }

    private void addListeners() {
        scene.setOnMouseDragged(this::moveMouseLoc);
        scene.setOnMouseMoved(this::moveMouseLoc);
        scene.setOnMousePressed(e -> shooting = true);
        scene.setOnMouseReleased(e -> shooting = false);
        scene.setOnKeyPressed(e -> keyAction(e, true));
        scene.setOnKeyReleased(e -> keyAction(e, false));
        gameField.getStop().setOnAction(e -> stopGame());
        gameField.getPauze().setOnAction(e -> loop.stop());
        gameField.getResume().setOnAction(e -> loop.start());
    }

    private void stopGame() {
        loop.stop();
        updateHighscoreToDataBase();
        Client.loadScreen("endGame");
    }

    private void updateHighscore() {
        highScore += 10;
    }

    private void updateHighscoreToDataBase() {
        if (instance.getCurrentUser().getHighscore() < highScore) {
            String query = "update users set highScore=" + highScore + " where username like '" + instance.getCurrentUser().getUsername() + "'";
            instance.getDb().updateTable(query);
        }
    }
}
