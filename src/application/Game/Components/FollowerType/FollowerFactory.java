package application.Game.Components.FollowerType;

import application.Engine.Engine;
import application.Game.Components.Vector2D;
import javafx.scene.layout.Pane;

/**
 * Created by jens on 11/11/16.
 */
public class FollowerFactory {

    public Follower makeFollower(Pane bp, Vector2D mainLoc) {

        Follower f = null;
        switch (Engine.getInstance().getFollowerType()) {
            case "Donkey":
                f = new Donkey(bp, mainLoc);
                break;
            case "Horse":
                f = new Horse(bp, mainLoc);
                break;
            case "Unicorn":
                f = new Unicorn(bp, mainLoc);
                break;
            default:
                break;
        }
        return f;
    }
}
