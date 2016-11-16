package application.Game.Components.FollowerType;

import application.Game.Components.Vector2D;
import javafx.scene.layout.Pane;

/**
 * Created by jens on 11/11/16.
 */
public class Horse extends Follower {
    public Horse(Pane bp, Vector2D location) {
        super(bp, location);
        this.setName("Horse");
    }
}
