package application.Engine;


import application.Models.BulletType.*;
import application.Models.FollowerType.Donkey;
import application.Models.FollowerType.Follower;
import application.Models.FollowerType.Horse;
import application.Models.FollowerType.Unicorn;
import application.Models.Vector2D;
import application.Client;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

public class Engine {
    private static Engine firstInstance = null;
    private User currentUser;
    private DbConnection db = new DbConnection();
    private Client ui;
    private String username;
    private String weaponType;
    private String followerType;
    private String levelType;

    public static Engine getInstance() {

        if (firstInstance == null) {
            firstInstance = new Engine();
        }

        return firstInstance;
    }

    public Bullet makeBullet(Pane bp, Vector2D mainLoc, Vector2D mouseLoc) {
        switch (getWeaponType()){
            case "Spear":
                return new Spear(bp,mainLoc,mouseLoc);
            case "Arrow":
                return new Arrow(bp,mainLoc,mouseLoc);
            case "Bolt":
                return new Bolt(bp,mainLoc,mouseLoc);
            case "Unicorn":
                return new UnicornHorn(bp,mainLoc,mouseLoc);
            default:
                return null;
        }


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

    public void initCurrentUser() {

        try {
            setCurrentUser(db.initUser(username));
            System.out.println(currentUser.getHighscore());
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void saveCurrentUser() throws Exception {

        String query = "UPDATE settings SET music=" + currentUser.getSettings().isMusic() + ",autosave=" + currentUser.getSettings().isAutoSave() + " WHERE             username = '" + currentUser.getUsername() + "'";
        db.updateTable(query);
    }

    public void setUi(Client ui) {
        this.ui = ui;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    private void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public DbConnection getDb() {
        return this.db;
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
