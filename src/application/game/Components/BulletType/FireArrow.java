package application.game.Components.BulletType;

import application.game.Components.Layer;
import application.game.Components.Vector2D;

/**
 * Created by jens on 11/11/16.
 */
public class FireArrow extends Bullet {



    public FireArrow(Layer layer, Vector2D location) {
        super(layer, location);
        this.setDamage(2);
    }


}
