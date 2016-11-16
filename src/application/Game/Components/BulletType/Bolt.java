package application.Game.Components.BulletType;

import application.Game.Components.Layer;
import application.Game.Components.Vector2D;

/**
 * Created by jens on 11/11/16.
 */
public class Bolt extends Bullet {
    public Bolt(Layer layer, Vector2D location, Vector2D mouseLoc) {
        super(layer, location, mouseLoc);
        this.setDamage(3);
    }
}
