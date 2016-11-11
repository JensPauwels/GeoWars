package application.game.Components.BulletType;

import application.game.Components.Layer;
import application.game.Components.Vector2D;

/**
 * Created by jens on 11/11/16.
 */
public class Spear extends Bullet {



    public Spear(Layer layer, Vector2D location) {
        super(layer, location);
        this.setDamage(1);
    }



}
