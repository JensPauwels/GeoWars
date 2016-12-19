package application.Models.BulletType;

import application.Models.Vector2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Created by jens on 19/12/2016.
 */
public class FireBall extends Bullet{


    public FireBall(Pane bp, Vector2D location, Vector2D mouseLoc) {
        super(bp, location, mouseLoc);

    }

    @Override
    public Node createView() {
        Label t = new Label();
        t.setId("fire");
        return t;
    }


}
