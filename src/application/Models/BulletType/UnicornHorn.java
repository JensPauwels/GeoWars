package application.Models.BulletType;

import application.Models.Vector2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Created by jens on 02/12/2016.
 */
public class UnicornHorn extends Bullet{


    public UnicornHorn(Pane bp, Vector2D location, Vector2D mouseLoc,int randomInt) {
        super(bp, location, mouseLoc,randomInt);

    }

    @Override
    public Node createView() {
        Label t = new Label();
        t.setId("UnicornHorn");
        return t;
    }


}
