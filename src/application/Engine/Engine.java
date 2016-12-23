package application.Engine;


import application.Client;
import application.Models.BulletType.*;
import application.Models.EnemyType.Boss;
import application.Models.FollowerType.Donkey;
import application.Models.FollowerType.Follower;
import application.Models.FollowerType.Horse;
import application.Models.FollowerType.Unicorn;
import application.Models.Vector2D;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

public class Engine {
    private static Engine firstInstance = null;
    private String weaponType;
    private String followerType;
    private String levelType;
    private int wave;
    private int highscore;
    private String username;



    public static Engine getInstance() {
        if (firstInstance == null) {
            firstInstance = new Engine();
        }
        return firstInstance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getWave() {
        return wave;
    }

    public void setWave(int wave) {
        this.wave = wave;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public Bullet makeBullet(Pane bp, Vector2D mainLoc, Vector2D mouseLoc,int randomInt) {
        switch (getWeaponType()){
            case "spear":
                return new Spear(bp,mainLoc,mouseLoc,randomInt);
            case "bow":
                return new Arrow(bp,mainLoc,mouseLoc,randomInt);
            case "crossbow":
                return new Bolt(bp,mainLoc,mouseLoc,randomInt);
            case "Unicorn":
                return new UnicornHorn(bp,mainLoc,mouseLoc,randomInt);
            default:
                return null;
        }
    }

    public Bullet makeFireBall(Pane bp, Vector2D mainLoc, Vector2D mouseLoc,int randomInt){
        return new FireBall(bp,mainLoc,mouseLoc,randomInt);
    }

    public Boss makeBoss(Pane bp, Vector2D mainloc){
        return new Boss(bp,mainloc);
    }

    public Follower makeFollower(Pane bp) {
        switch(getFollowerType()){
            case "Donkey":
                return new Donkey(bp);
            case "Horse":
                return new Horse(bp);
            case "Unicorn":
                return new Unicorn(bp);
            default:
                return  null;
        }
    }

    public int getIncrease() {
        Map<String,Integer> levels = new HashMap<String, Integer>(){{
            put("Easy", 2);
            put("Medium", 4);
            put("Hard", 6);
        }};
        return levels.get(levelType);
    }

    public void setLevelType(String levelType) {
        this.levelType = levelType;
    }

    public String getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    public String getFollowerType() {
        return followerType;
    }

    public void setFollowerType(String followerType) {
        this.followerType = followerType;
    }

}
