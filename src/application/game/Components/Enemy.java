package application.game.Components;

import javafx.scene.Node;
import javafx.scene.control.Label;

public class Enemy extends Sprite {

    public Enemy(Layer layer, Vector2D location) {
        super(layer, location, 12.5, 25);
    }

    @Override
    public Node createView() {
        Label img = new Label();
        img.setId("enemy");
        return img;
    }





}
