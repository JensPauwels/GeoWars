package application.Controllers;


import application.Client;
import application.Engine.Engine;
import application.Engine.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Region;

import java.io.IOException;

public class SinglePlayerController {


    private Engine instance = Engine.getInstance();
    private String[] weapons = {"spear","bow","crossbow"};
    private String[] speeds = {"slow","medium","fast"};
    private String[] specialAbilitys = {"10","12","14","16","18","20"};
    public String difficulty = "Easy";
    public String follower = "Donkey";
    public Region weapon,speed,specialAbility,easy,medium,hard,donkey,horse,unicorn;
    private int index = 0;
    private int speedUpdate = 0;
    private int specialAbilityUpdate = 0;


    public void removeDifficultyGlows(){

        easy.setId("easyChoice");
        hard.setId("hardChoice");
        medium.setId("mediumChoice");

    }

    public void removeFollowerGlows(){
        donkey.setId("Fdonkey");
        horse.setId("Fhorse");
        unicorn.setId("Funicorn");
    }


 
    @FXML
    private void initialize() {
        weapon.setId(weapons[index]+"screen");
        easy.setId("easyGlow");
        donkey.setId("donkeyGlow");
        speed.setId("speed"+speeds[0]);
        specialAbility.setId("specialAbility"+specialAbilitys[0]);
    }

    @FXML
    public void easy(){
        removeDifficultyGlows();
        difficulty = "Easy";
        easy.setId("easyGlow");
    }

    @FXML
    public void medium(){
        removeDifficultyGlows();
        difficulty  = "Medium";
        medium.setId("mediumGlow");
    }

    @FXML
    public void hard(){
        removeDifficultyGlows();
        difficulty = "Hard";
        hard.setId("hardGlow");
    }

    @FXML
    public void donkey(){
        removeFollowerGlows();
        follower = "Donkey";
        donkey.setId("donkeyGlow");
    }

    @FXML
    public void horse(){
        removeFollowerGlows();
        follower = "Horse";
        horse.setId("horseGlow");
    }

    @FXML
    public void unicorn(){
        removeFollowerGlows();
        follower = "Unicorn";
        unicorn.setId("unicornGlow");
    }

    @FXML
    private void loadGameOptions() throws IOException {
        Client.loadScreen("gameOptions");
    }

    @FXML
    private void previousWeapon(){
        if(index > 0){index--;}
        weapon.setId(weapons[index]+"screen");
    }

    @FXML
    private void nextWeapon(){
        if(index < weapons.length -1){index++;}
        weapon.setId(weapons[index]+"screen");
    }

    @FXML
    private void updateSpeed(){

        if(speedUpdate != speeds.length-1){
            speedUpdate++;
            speed.setId("speed"+speeds[speedUpdate]);
        }

    }

    @FXML
    private void updateSpecialAbility(){
        if(specialAbilityUpdate != specialAbilitys.length-1){
            specialAbilityUpdate++;
            specialAbility.setId("specialAbility"+specialAbilitys[specialAbilityUpdate]);
        }

    }

    @FXML
    private void launchGame() throws Exception {
        instance.setWeaponType(weapons[index]);
        instance.setFollowerType(follower);
        instance.setLevelType(difficulty);
        Game newGame = new Game(Client.scene, Client.mainLayout,false);
        newGame.initGame();
    }
}
