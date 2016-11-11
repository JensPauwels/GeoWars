package application.game.Components.BulletType;

import application.game.Components.Layer;
import application.game.Components.Sprite;
import application.game.Components.Vector2D;
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
