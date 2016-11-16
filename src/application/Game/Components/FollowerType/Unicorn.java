package application.Game.Components.FollowerType;

import application.Game.Components.Vector2D;
import javafx.scene.layout.Pane;


public class Unicorn extends Follower {
    public Unicorn(Pane bp, Vector2D location) {
        super(bp, location);
        this.setName("Unicorn");
    }
}
