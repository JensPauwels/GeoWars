package application.Controllers;


import application.Engine.Engine;
import application.Engine.Game;
import application.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class SinglePlayerController {

    private ObservableList<String> level = FXCollections.observableArrayList("Easy", "Medium", "Hard");
    private ObservableList<String> weaponsOfChoice = FXCollections.observableArrayList("Spear", "Arrow", "Bolt");
    private ObservableList<String> followerOfChoice = FXCollections.observableArrayList("Donkey", "Horse", "Unicorn");
    private Engine instance = Engine.getInstance();
    public ChoiceBox<String> weaponType;
    public ChoiceBox<String> followerType;
    public ChoiceBox<String> levelType;


    @FXML
    private void initialize() {
        weaponType.setItems(weaponsOfChoice);
        weaponType.setValue(weaponsOfChoice.get(0));
        followerType.setItems(followerOfChoice);
        followerType.setValue(followerOfChoice.get(0));
        levelType.setItems(level);
        levelType.setValue(level.get(0));

    }

    @FXML
    private void loadGameOptions() throws IOException {

        Client.loadScreen("gameOptions");
    }

    @FXML
    private void launchGame() {
        instance.setWeaponType(weaponType.getValue());
        instance.setFollowerType(followerType.getValue());
        instance.setLevelType(levelType.getValue());
        Game newGame = new Game(Client.scene, Client.mainLayout);
        newGame.initGame();
    }
}
