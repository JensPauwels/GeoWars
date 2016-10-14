package application;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.input.KeyCode;

/**
 * Created by jens on 10/10/16.
 */

/*

    Dit is in absoluut prototype het enige die maar werkt is de wasd functions
    nog verder uitzoeken hoe fxgl werkt => nog voor de zekerheid ook eens nagaan als we effectief wel game libaries mogen usen



 */


public class SinglePlayer extends GameApplication {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initInput() {
        Input input = getInput();

        input.addAction(new UserAction("Move Left") {
            @Override
            protected void onAction() {
                System.out.println("left");
            }
        }, KeyCode.A);

        input.addAction(new UserAction("Move Right") {
            @Override
            protected void onAction() {
                System.out.println("right");

            }
        }, KeyCode.D);

        input.addAction(new UserAction("Move Up") {
            @Override
            protected void onAction() {
                System.out.println("up");
            }
        }, KeyCode.W);

        input.addAction(new UserAction("Move Down") {
            @Override
            protected void onAction() {
                System.out.println("down");
            }
        }, KeyCode.S);


    }

    @Override
    protected void initAssets() {

    }

    @Override
    protected void initGame() {

    }

    @Override
    protected void initPhysics() {

    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void onUpdate(double v) {

    }

    @Override
    protected void initSettings(GameSettings settings) {

        settings.setWidth(1280);
        settings.setHeight(720);
        settings.setTitle("geowars");
        settings.setVersion("0.4");
        settings.setFullScreen(false);
        settings.setIntroEnabled(false);
        settings.setMenuEnabled(false);
        settings.setProfilingEnabled(true);
        settings.setCloseConfirmation(false);
        settings.setApplicationMode(ApplicationMode.DEVELOPER);


    }
}
