package application.game.Components;

import javafx.scene.layout.Pane;

public class Layer extends Pane {

    public Layer(double width, double height) {
        setMaxSize(width, height);
        //setPrefSize(width, height);
    }

}