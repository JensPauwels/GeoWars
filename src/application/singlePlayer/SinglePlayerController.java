package application.singlePlayer;
//import application.UserInterface;

import application.UserInterface;
import application.game.Game;
import javafx.fxml.FXML;

import java.io.IOException;

public class SinglePlayerController {

    @FXML
    private void loadGameOptions() throws IOException {
        UserInterface.loadScreen("gameOptions");
    }

    @FXML
    private void launchGame() {
        Game newGame = new Game(UserInterface.scene, UserInterface.mainLayout);
        newGame.initGame();
    }
}
