package application.Controllers;

import application.Engine.Engine;
import application.UserInterface;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;

public class SettingsController {
    public JFXToggleButton music, autoSave;
    private Engine instance = Engine.getInstance();


    @FXML
    public void initialize() {
        music.setSelected(instance.getCurrentUser().getSettings().isMusic());
        autoSave.setSelected(instance.getCurrentUser().getSettings().isAutoSave());
    }

    @FXML
    public void setMusic() {
        instance.getCurrentUser().getSettings().setMusic(music.isSelected());
    }

    @FXML
    public void setAutoSave() {
        instance.getCurrentUser().getSettings().setAutoSave(autoSave.isSelected());
    }


    @FXML
    public void loadGameOptions() {
        UserInterface.loadScreen("gameOptions");
    }

    // in deze class moeten we allemaal settings bedenken oa
    // - Muziek of niet

    //DIT IS PAS UITBREIDNIG
    // - linken aan social media
    // - online/ ofline display
    // - personal records bekijken?
    // - total active gameplay bekijken?
    // - GameName changen? (pas om de maand mogelijk) => no fucking idea hoe je op tijd checkt :p
}
