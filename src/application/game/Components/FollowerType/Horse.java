package application.game.Components.FollowerType;

import application.game.Components.Layer;
import application.game.Components.Vector2D;

/**
 * Created by jens on 11/11/16.
 */
public class Horse extends Follower {
    public Horse(Layer layer, Vector2D location) {
        super(layer, location);
        this.setName("Horse");
    }
}
