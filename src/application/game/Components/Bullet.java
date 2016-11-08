package application.game.Components;

import javafx.scene.Node;
import javafx.scene.shape.Circle;

public class Bullet extends Sprite {
    private Vector2D destination;

    public Bullet(Layer layer, Vector2D location) {
        super(layer, location, 25, 12.5);
    }

    public Vector2D getDestination() {
        return this.destination;
    }

    public void setDestination(Vector2D location) {
        this.destination = location;
    }

    @Override
    public Node createView() {
        Circle circle = new Circle();
        circle.setCenterX(10);
        circle.setCenterY(10);
        circle.setRadius(5.0);
        return circle;
    }



}
