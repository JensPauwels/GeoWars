package application.Game.Components;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class PowerUp extends Sprite{

    public PowerUp(Pane bp, Vector2D location) {
        super(bp, location, 12.5, 12.5);
    }


    @Override
    public Node createView() {
        Circle circle = new Circle();
        circle.setCenterX(25);
        circle.setCenterY(25);
        circle.setRadius(12.5);
        return circle;

    }
}
