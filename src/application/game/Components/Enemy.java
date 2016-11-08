package application.game.Components;

import javafx.scene.Node;
import javafx.scene.control.Label;


public class Enemy extends Sprite {

    public Enemy(Layer layer, Vector2D location, Vector2D velocity, Vector2D acceleration) {
        super(layer, location, velocity, acceleration, 12.5, 25);
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
