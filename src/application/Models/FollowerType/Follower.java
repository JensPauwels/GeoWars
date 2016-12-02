package application.Models.FollowerType;

import application.Engine.Engine;
import application.Models.Sprite;
import application.Models.Vector2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public abstract class Follower extends Sprite {

    private String name;

    public Follower(Pane bp) {
        super(bp, new Vector2D(350, 300), 10, 10);

    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void specialAbility();

    @Override
    public Node createView() {
        Label t = new Label();
        t.setId(Engine.getInstance().getFollowerType());
        System.out.println(Engine.getInstance().getFollowerType());
        return t;

    }






}
