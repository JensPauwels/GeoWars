package application.Models.EnemyType;

import application.Models.Vector2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class Boss extends Enemy {

    public Boss(Pane bp, Vector2D location) {
        super(bp, location,0);
        this.setHealth(20);
        this.setDestination(this.getLocation());
        this.setId("dragon");
    }

    @Override
    public Node createView() {
        Label img = new Label();

        return img;
    }
}
