package application.game.Components;

import javafx.scene.Node;
import javafx.scene.shape.Circle;

public class Bullet extends Sprite {
    Vector2D test;

    public Bullet(Layer layer, Vector2D location, Vector2D velocity, Vector2D acceleration, double width, double height) {
        super(layer, location, velocity, acceleration, width, height);
    }

    public Vector2D getLocation() {
        return this.test;
    }

    public void setLocation(Vector2D g) {
        this.test = g;
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
