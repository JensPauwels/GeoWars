package application.game.Components;

import javafx.scene.Node;
import javafx.scene.control.Label;

public class Attractor extends Sprite {
    public Attractor(Layer layer, Vector2D location, Vector2D velocity, Vector2D acceleration, double width, double height) {
        super(layer, location, velocity, acceleration, width, height);
    }

    @Override
    public Node createView() {

        Label t = new Label("                       ");
        t.setId("character");
        return t;
    }


}