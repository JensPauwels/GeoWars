package application.Engine;

import application.Client;
import application.Models.AttractorType.Attractor;
import application.Models.BulletType.*;
import application.Models.EnemyType.Boss;
import application.Models.EnemyType.Enemy;
import application.Models.FollowerType.Follower;
import application.Models.PowerUpType.Bomb;
import application.Models.PowerUpType.PowerUp;
import application.Models.Vector2D;
import application.Multiplayer.ClientProgram;
import application.Multiplayer.PacketMessage;
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
    private FakeDataBase fakeDataBase;
    private Pane playField;
    private Attractor mainCharacter, secondCharacter;
    private Follower follower;
    private List<Enemy> allEnemys;
    private List<Bullet> allBullets,BulletFromBoss;
    private List<PowerUp> powerups;
    private List<Boss> bosses;
    private GameField gameField;
    private ClientProgram cp;
    private PacketMessage pm;
    private Engine instance;
    private Random random;
    private Vector2D mouseLocation,location;
    private long time, shootersTime,bossSpeed, tekstTime = System.currentTimeMillis();
    private Scene scene;
    private AnimationTimer loop;
    private boolean up, down, left, right, shooting,bossDead,multiPlayer,rapidFireActivated,shieldActivated,multiplierActivated,bombActivated;
    private int enemysKilled,angle,xp = 0;
    private int enemyToKill = 5;
    private double shooterSpeed = 1;
    private int waves = 1;


    public Game(Scene scene, BorderPane mainLayout,Boolean multiPlayer) {
        fakeDataBase = new FakeDataBase();
        allEnemys = new LinkedList<>();
        allBullets = new LinkedList<>();
        BulletFromBoss = new LinkedList<>();
        powerups = new LinkedList<>();
        bosses = new LinkedList<>();
        gameField = new GameField();
        cp = new ClientProgram();
        pm = new PacketMessage();
        instance = Engine.getInstance();
        random = new Random();
        mouseLocation = new Vector2D(0,0);
        location = new Vector2D(0,0);

        this.scene = scene;
        playField = gameField.getScreen();
        mainLayout.setCenter(playField);
        this.multiPlayer = multiPlayer;
    }

    public void initGame() throws Exception {



        addListeners();
        prepareGame();
        startGame();
        if(multiPlayer) {
            secondCharacter = new Attractor(playField);
            cp.start();
        }
    }

    private void prepareGame() {
        for (int i = 0; i < 5; i++) {addEnemy();}
        mainCharacter = new Attractor(playField);
        follower = instance.makeFollower(playField);
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
                ControlPowerUp();
                handleBoss();
                if(multiPlayer){
                    moveLocation();
                    handler();
                }
            }
        };
        loop.start();
    }


    public void moveLocation(){
        this.pm = cp.getPm() ;
        if(this.pm.getId() == 1){
            secondCharacter.setLocation(pm.getSecondCharacter());}
        else{
            secondCharacter.setLocation(pm.getFirstCharacter());}
        secondCharacter.display();
    }

    public void handler(){
        this.pm.setFirstCharacter(mainCharacter.getLocation());
        cp.setPm(this.pm);
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
        if(bossSpeed + 500 < System.currentTimeMillis()){
            location = mainCharacter.getLocation();
            Vector2D bosslocation = new Vector2D(boss.getLocation().getX()-85,boss.getLocation().getY()-50);
            Bullet bullet = instance.makeFireBall(playField, bosslocation, location);
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
                    if(multiplierActivated) {
                        xp = xp+(boss.getXp()*2);
                    }
                    else{xp = xp +  boss.getXp();}
                    gameField.updateHighscore(xp);
                    bosses.remove(boss);
                    bossDead =true;
                }
            }
        }
    }

    private void addBullet(Vector2D loc) {
        location = new Vector2D(mainCharacter.getLocation());
        Bullet bullet = instance.makeBullet(playField, location, loc);
        allBullets.add(bullet);
    }

    private void spawnPowerUp() {
        location = new Vector2D(random.nextDouble() * 770, random.nextDouble() * 540);
        PowerUp pu = new PowerUp(playField, location);
        pu.display();
        powerups.add(pu);
    }

    private void updateWaves() {
        if (enemysKilled == enemyToKill || bossDead) {
            bossDead =false;
            enemyToKill += instance.getIncrease();
            enemysKilled = 0;
            waves++;
            gameField.updateWaves(waves);
            if(waves% 5==0){makeBoss();}
            else{
                for (int i = 0; i < enemyToKill ; i++) {addEnemy();}
                spawnPowerUp();
            }
        }
    }

    private void trippleArrow() {
        addBullet(new Vector2D(mouseLocation.getX(), mouseLocation.getY() + 50));
        addBullet(mouseLocation);
        addBullet(new Vector2D(mouseLocation.getX(), mouseLocation.getY() - 50));
    }

    private void checkColOnPowerUps(){
        for (int i = 0; i< powerups.size();i++) {
            if (powerups.get(i).coll(mainCharacter, powerups.get(i))) {
                powerups.get(i).setVisible(false);
                powerups.remove(powerups.get(i));
                handlePowerUpsAndDown();
            }
        }
    }

    private void ControlPowerUp(){
        if(tekstTime + 5000 < System.currentTimeMillis() && gameField.getActivatedPowerupLabel()){
            gameField.setActivatedPowerupLabelInvisible();
            shooterSpeed = 1;
            rapidFireActivated =false;
            shieldActivated = false;
            multiplierActivated = false;
        }
    }


    private void handlePowerUpsAndDown(){
        Random r = new Random();
        int number = r.nextInt(10);
        switch (5){
            case 1:
                gameField.setActivatedPowerupLabel("Rapid fire");
                rapidFireActivated = true;
                break;
            case 2:
                shooterSpeed = 2;
                gameField.setActivatedPowerupLabel("Fire arrow");
                break;
            case 3:
                gameField.setActivatedPowerupLabel("Shield");
                shieldActivated=true;
                break;
            case 4:
                multiplierActivated = true;
                gameField.setActivatedPowerupLabel("Multiplier");
                break;
            case 5:
                gameField.setActivatedPowerupLabel("Bomb");
                bombActivated=true;
                break;
            case 6:
                gameField.setActivatedPowerupLabel("Extra life");
                mainCharacter.setlives(mainCharacter.getLives()+1);
                gameField.updateLives(mainCharacter.getLives());
                break;
            case 7:
                gameField.setActivatedPowerupLabel("Slow fire");
                shooterSpeed= 0.2;
                break;
            case 8:
                gameField.setActivatedPowerupLabel("Orcs on fire");

                break;
            case 9:
                gameField.setActivatedPowerupLabel("Orc zone");
                break;
            case 10:
                gameField.setActivatedPowerupLabel("Health down");
                mainCharacter.setlives(mainCharacter.getLives()-1);
                gameField.updateLives(mainCharacter.getLives());
                break;
        }
        tekstTime = System.currentTimeMillis();

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
            location = follower.getLocation();
            Bullet bullet = instance.makeBullet(playField,location,mouseLocation);
            System.out.println(bullet);
            time = System.currentTimeMillis();
        }
    }

    private void specialAbilityDonkey() {
        if (time + 10000 < System.currentTimeMillis() && powerups.size() > 0) {
            for (int i = 0; i < powerups.size(); i++) {
                follower.movement(powerups.get(0).getLocation(), false);
                if (follower.coll(follower, powerups.get(0))) {
                    handlePowerUpsAndDown();
                    powerups.get(0).setVisible(false);
                    powerups.remove(powerups.get(0));
                    time = System.currentTimeMillis();
                }
            }
        }
        else {
            double x = mainCharacter.getLocation().getX()-15;
            double y = mainCharacter.getLocation().getY()+50;
            Vector2D loc = new Vector2D(x,y);
            follower.movement(loc,false);
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
            if(!shieldActivated){checkCollisionEnemy(mainCharacter, enemy);}
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
            if (bullet.outOfDestination()) {allBullets.remove(bullet);}
            bullet.movement(bullet.getDestination(), true);
            checkCollisionBullet(bullet);
            checkCollisionBoss(bullet);
        }
    }

    private void checkCollisionEnemy(Attractor a, Enemy e) {
        if (e.coll(a, e)) {
            killEnemy(e);
            mainCharacter.setlives(mainCharacter.getLives() - 1);
            gameField.updateLives(mainCharacter.getLives());
            if (a.getLives() <= 0) {stopGame();}
        }
    }

    private void killEnemy(Enemy e) {
        e.setVisible(false);
        allEnemys.remove(e);
        if(multiplierActivated){ xp = xp + e.getXp()*2;}
        else{xp = xp+ e.getXp();}
        gameField.updateHighscore(xp);
        enemysKilled++;
    }

    private void moveChar() {
        double x = mainCharacter.getLocation().getX();
        double y = mainCharacter.getLocation().getY();
        if (up && y > 80) {mainCharacter.setLocation(x, y - 5);}
        else if (down && y < 540) {mainCharacter.setLocation(x, y + 5);}
        else if (left && x > 0) {mainCharacter.setLocation(x - 5, y);}
        else if (right && x < 770) {mainCharacter.setLocation(x + 5, y);}
    }

    private void keyAction(KeyEvent e, Boolean bool) {
        KeyCode key = e.getCode();
        if (key == KeyCode.W || key == KeyCode.UP) {up = bool;}
        else if (key == KeyCode.S || key == KeyCode.DOWN) {down = bool;}
        else if (key == KeyCode.A || key == KeyCode.LEFT) {left = bool;}
        else if (key == KeyCode.D || key == KeyCode.RIGHT) {right = bool;}
        else if (key == KeyCode.SPACE || bombActivated){
            System.out.println("pressed spacebar");
            location = new Vector2D(mainCharacter.getLocation().getX(),mainCharacter.getLocation().getY());
            Bomb bomb = new Bomb(playField, location);
            bomb.display();
        }
    }

    private void shoot() {

        if (shooting && (shootersTime + fakeDataBase.getTimeFromWeapon(instance.getWeaponType()) * shooterSpeed) < System.currentTimeMillis()) {
            if(rapidFireActivated){trippleArrow();}
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
        gameField.getPause().setOnAction(e -> loop.stop());

    }

    private void stopGame() {
        loop.stop();
        updateHighscoreToDataBase();
        instance.setWave(waves);
        instance.setHighscore(xp);
        Client.loadScreen("endGame");
    }

    private void updateHighscoreToDataBase() {
        if (instance.getCurrentUser().getHighscore() < xp) {
            String query = "update users set highScore=" + xp + " where username like '" + instance.getCurrentUser().getUsername() + "'";
            instance.getDb().updateTable(query);
        }
    }
}
