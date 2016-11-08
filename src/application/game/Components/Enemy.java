package application.game.Components;

import javafx.scene.Node;
import javafx.scene.control.Label;


public class Enemy extends Sprite {

    public Enemy(Layer layer, Vector2D location, Vector2D velocity, Vector2D acceleration, double width, double height) {
        super(layer, location, velocity, acceleration, width, height);
    }

    @Override
    public Node createView() {
        Label l = new Label("      ");
        l.setId("enemy");
        return l;
    }

    public boolean bots(Attractor a, Enemy e) {
        return a.getBoundsInParent().intersects(e.getBoundsInParent());
    }


}
