package application.Game.Components.FollowerType;

import application.Engine.Engine;
import application.Game.Components.Sprite;
import application.Game.Components.Vector2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class Follower extends Sprite {

    private String name;


    public Follower(Pane bp, Vector2D location) {
        super(bp, location, 10, 10);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Node createView() {
        Label t = new Label();
        t.setId(Engine.getInstance().getFollowerType());
        System.out.println(Engine.getInstance().getFollowerType());

        return t;

    }
}
