package application.game.Components;

import javafx.scene.Node;
import javafx.scene.control.Label;


public class Attractor extends Sprite {
    public Attractor(Layer layer, Vector2D location) {
        super(layer, location, 25, 25);
    }

    @Override
    public Node createView() {

        Label t = new Label("                       ");
        t.setId("character");
        return t;

    }


}