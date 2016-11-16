package application.Game.Components;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class Enemy extends Sprite {

    public Enemy(Pane bp, Vector2D location) {
        super(bp, location, 12.5, 25);
    }

    @Override
    public Node createView() {
        Label img = new Label();
        img.setId("enemy");
        return img;
    }


}
