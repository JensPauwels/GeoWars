package application.Game.Components.FollowerType;

import application.Game.Components.Layer;
import application.Game.Components.Vector2D;

/**
 * Created by jens on 11/11/16.
 */
public class Donkey extends Follower {
    public Donkey(Layer layer, Vector2D location) {
        super(layer,location);
        this.setName("Donkey");

    }
}
