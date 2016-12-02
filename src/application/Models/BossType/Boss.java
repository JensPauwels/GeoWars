package application.Models.BossType;

import application.Models.Sprite;
import application.Models.Vector2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Created by jens on 27/11/2016.
 */
public class Boss extends Sprite{


    private String name;
    private int health;

    public Boss(Pane bp, Vector2D location, double width, double height) {
        super(bp, location, width, height);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public Node createView() {
        Label t = new Label();
        t.setId("draak");
        return t;
    }
}
