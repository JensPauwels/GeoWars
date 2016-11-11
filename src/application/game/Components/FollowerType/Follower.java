package application.game.Components.FollowerType;

import application.Engine.Engine;
import application.game.Components.Layer;
import application.game.Components.Sprite;
import application.game.Components.Vector2D;
import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * Created by jens on 11/11/16.
 */
public class Follower extends Sprite {

    private String name;


    public Follower(Layer layer, Vector2D location) {
        super(layer, location, 10, 10);

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
