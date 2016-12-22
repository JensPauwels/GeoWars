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

    private ObservableList<String> level = FXCollections.observableArrayList("Easy", "Medium", "Hard");
    private ObservableList<String> followerOfChoice = FXCollections.observableArrayList("Donkey", "Horse", "Unicorn");
    private Engine instance = Engine.getInstance();
   // public ChoiceBox<String> followerType;
    //public ChoiceBox<String> levelType;
    private String[] weapons = {"spear","bow","crossbow"};
    private String[] speeds = {"slow","medium","fast"};
    private String[] specialAbilitys = {"10","12","14","16","18","20"};
    public String difficulty = "Easy";
    public String follower = "Donkey";
    public Region weapon,speed,specialAbility;
    private int index = 0;
 
    @FXML
    private void initialize() {
       // followerType.setItems(followerOfChoice);
       // followerType.setValue(followerOfChoice.get(0));
        //levelType.setItems(level);
        //levelType.setValue(level.get(0));
        weapon.setId(weapons[index]+"screen");
        speed.setId("speed"+speeds[0]);
        specialAbility.setId("specialAbility"+specialAbilitys[0]);
    }

    @FXML
    public void easy(){
        difficulty = "Easy";
    }

    @FXML
    public void medium(){
        difficulty  = "Medium";
    }

    @FXML
    public void hard(){
        difficulty = "Hard";
    }

    @FXML
    public void donkey(){
        follower = "Donkey";
    }

    @FXML
    public void horse(){
        follower = "Horse";
    }

    @FXML
    public void unicorn(){
        follower = "Unicorn";
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
    private void launchGame() throws Exception {
        instance.setWeaponType(weapons[index]);
        instance.setFollowerType(follower);
        instance.setLevelType(difficulty);
        Game newGame = new Game(Client.scene, Client.mainLayout,false);
        newGame.initGame();
    }
}
