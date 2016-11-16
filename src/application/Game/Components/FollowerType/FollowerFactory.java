package application.Game.Components.FollowerType;

import application.Engine.Engine;
import application.Game.Components.Layer;
import application.Game.Components.Vector2D;

/**
 * Created by jens on 11/11/16.
 */
public class FollowerFactory {

    public Follower makeFollower(Layer playfield, Vector2D mainLoc){

        Follower f = null;
        switch (Engine.getInstance().getFollowerType()){
            case "Donkey":
                f = new Donkey(playfield,mainLoc);
                break;
            case "Horse":
                f = new Horse(playfield,mainLoc);
                break;
            case "Unicorn":
                f = new Unicorn(playfield,mainLoc);
                break;
            default:
                break;
        }
        return  f;
}
}
