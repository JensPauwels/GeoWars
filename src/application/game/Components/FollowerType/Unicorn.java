package application.game.Components.FollowerType;

import application.game.Components.Layer;
import application.game.Components.Vector2D;


public class Unicorn extends Follower{
    public Unicorn(Layer layer, Vector2D location) {
        super(layer, location);
        this.setName("Unicorn");
    }
}
