package application.Game.Components.BulletType;

import application.Game.Components.Vector2D;
import javafx.scene.layout.Pane;

/**
 * Created by jens on 11/11/16.
 */
public class Bolt extends Bullet {
    public Bolt(Pane bp, Vector2D location, Vector2D mouseLoc) {
        super(bp, location, mouseLoc);
        this.setDamage(3);
    }
}
