package application.Models.EnemyType;

import application.Models.Sprite;
import application.Models.Vector2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class Enemy extends Sprite {
    private int health;


    public Enemy(Pane bp) {
        super(bp, new Vector2D(), 12.5, 25);

    }


    @Override
    public Node createView() {
        Label img = new Label();
        img.setId("enemy");
        return img;
    }


}
