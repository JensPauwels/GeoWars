package application.Controllers;


import application.Engine.Engine;
import application.Engine.Game;
import application.Client;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import java.io.IOException;

public class MultiPlayerController {

    private ObservableList<String> level = FXCollections.observableArrayList("Easy", "Medium", "Hard");
    private ObservableList<String> followerOfChoice = FXCollections.observableArrayList("Donkey", "Horse", "Unicorn");
    private Engine instance = Engine.getInstance();
    public ChoiceBox<String> followerType;
    public ChoiceBox<String> levelType;
    public String[] weapons = {"spear","bow","crossbow"};
    public Region weapon;
    private int index = 0;

    @FXML
    private void initialize() {
        followerType.setItems(followerOfChoice);
        followerType.setValue(followerOfChoice.get(0));
        levelType.setItems(level);
        levelType.setValue(level.get(0));
        weapon.setId(weapons[index]+"screen");
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
        instance.setFollowerType(followerType.getValue());
        instance.setLevelType(levelType.getValue());
        Game newGame = new Game(Client.scene, Client.mainLayout,false);
        newGame.initGame();
    }
}
