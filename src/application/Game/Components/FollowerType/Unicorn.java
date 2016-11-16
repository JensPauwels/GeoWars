package application.Game.Components.FollowerType;

import application.Game.Components.Layer;
import application.Game.Components.Vector2D;


public class Unicorn extends Follower{
    public Unicorn(Layer layer, Vector2D location) {
        super(layer, location);
        this.setName("Unicorn");
    }
}
