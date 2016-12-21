package application.Models.PowerUpType;

import application.Models.Sprite;
import application.Models.Vector2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class Bomb extends Sprite {

    public Bomb(Pane bp, Vector2D location) {
        super(bp, location, 12.5, 12.5);
    }


    @Override
    public Node createView() {

        Label t = new Label();
        t.setId("bomb");
        //System.out.println(t.getId());
        return t;

    }
}
