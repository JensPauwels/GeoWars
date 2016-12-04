package application.Models.FollowerType;

import application.Engine.Engine;
import application.Models.Sprite;
import application.Models.Vector2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public abstract class Follower extends Sprite {

    private String name;
    private Vector2D destionation;

    public Follower(Pane bp) {
        super(bp, new Vector2D(350, 300), 10, 10);

    }

    public void setName(String name) {
        this.name = name;
    }

    public Vector2D getDestionation() {
        return destionation;
    }

    public void setDestionation(Vector2D destionation) {
        this.destionation = destionation;
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
