package application.game.Components;

import javafx.scene.Node;

public class Enemy extends Sprite {

    public Enemy(Layer layer, Vector2D location, Vector2D velocity, Vector2D acceleration, double width, double height) {
        super(layer, location, velocity, acceleration, width, height);
    }

    @Override
    public Node createView() {
        return Utils.createArrowImageView((int) width);
    }

    public boolean bots(Attractor a, Enemy e) {
        boolean bool = false;
        if (a.getLocation().x == e.getLocation().x && a.getLocation().y == e.getLocation().y) {
            bool = true;
        }
        return bool;
    }

}
