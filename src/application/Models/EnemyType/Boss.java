package application.Models.EnemyType;

import application.Models.EnemyType.Enemy;
import application.Models.Vector2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Created by jens on 27/11/2016.
 */
public class Boss extends Enemy {

    public Boss(Pane bp, Vector2D location) {
        super(bp, location);
        this.setHealth(25);
        this.setDestination(this.getLocation());
    }

    @Override
    public Node createView() {
        Label img = new Label();
        img.setId("dragon");
        return img;
    }
}
