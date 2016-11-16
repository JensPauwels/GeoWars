package application.Controllers;
//import application.UserInterface;

import application.Engine.Engine;
import application.Game.Game;
import application.UserInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;

public class SinglePlayerController {

    private ObservableList<String> weaponsOfChoice = FXCollections.observableArrayList("Spear", "Arrow", "Bolt");
    private ObservableList<String> followerOfChoice = FXCollections.observableArrayList("Donkey", "Horse", "Unicorn");
    private Engine instance = Engine.getInstance();
    public ChoiceBox weaponType, followerType;


    @FXML
    private void initialize() {
        weaponType.setItems(weaponsOfChoice);
        weaponType.setValue(weaponsOfChoice.get(0));
        followerType.setItems(followerOfChoice);
        followerType.setValue(followerOfChoice.get(0));
    }

    @FXML
    private void loadGameOptions() throws IOException {
        UserInterface.loadScreen("gameOptions");
    }

    @FXML
    private void launchGame() {
        instance.setWeaponType(weaponType.getValue().toString());
        instance.setFollowerType(followerType.getValue().toString());
        System.out.println(followerType.getValue().toString());
        Game newGame = new Game(UserInterface.scene, UserInterface.mainLayout);
        newGame.initGame();
    }
}
