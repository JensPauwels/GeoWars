package application.game.Components;

import javafx.scene.Node;
import javafx.scene.shape.Circle;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Bullet extends Sprite {
    private Vector2D destination;

    public Bullet(Layer layer, Vector2D location) {
        super(layer, location, 25, 12.5);
    }


    public Vector2D getDestination() {
        return this.destination;
    }

    public void setDestination(double destinationX , double destinationY){
        Vector2D destination = new Vector2D(destinationX,destinationY);
        this.destination = destination;
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
