package application.Models.EnemyType;

import application.Models.Sprite;
import application.Models.Vector2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class Blood extends Sprite {

    public Blood(Pane bp, Vector2D location) {
        super(bp, location, 12.5, 12.5);
    }


    @Override
    public Node createView() {
        Label img = new Label();
        img.setId("blood");
        return img;
    }


}
