package application.Models.FollowerType;

import application.Models.Vector2D;
import javafx.scene.layout.Pane;

public class Horse extends Follower {


    public Horse(Pane bp) {
        super(bp);
        this.setName("Horse");
        this.setDestionation(this.getLocation());
    }



    @Override
    public void specialAbility() {

    }
}