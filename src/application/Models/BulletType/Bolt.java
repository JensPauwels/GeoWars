package application.Models.BulletType;

import application.Models.Vector2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Created by jens on 11/11/16.
 */
public class Bolt extends Bullet {
    public Bolt(Pane bp, Vector2D location, Vector2D mouseLoc) {
        super(bp, location, mouseLoc);
        this.setMaxSpeed(99999);
        this.setDamage(3);
    }

    @Override
    public Node createView() {
        Label t = new Label();
        t.setId("bolt");
        return t;
    }


}
