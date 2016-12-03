package application.Models.PowerUpType;

import application.Models.Sprite;
import application.Models.Vector2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.util.Random;

public class PowerUp extends Sprite {

    public PowerUp(Pane bp, Vector2D location) {
        super(bp, location, 12.5, 12.5);
    }


    @Override
    public Node createView() {
        Circle circle = new Circle();
        circle.setCenterX(5);
        circle.setCenterY(5);
        circle.setRadius(2.5);
        return circle;

    }
}
