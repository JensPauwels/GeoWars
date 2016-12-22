package application.Models.BulletType;

import application.Models.Vector2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class Arrow extends Bullet {

    public Arrow(Pane bp, Vector2D mainLoc, Vector2D mouseLoc,int randomInt) {
        super(bp, mainLoc, mouseLoc,randomInt);
        this.setDamage(2);
        this.setMaxSpeed(100);
    }

    public Arrow(Pane bp, Vector2D mainLoc,int randomInt) {
        super(bp, mainLoc,randomInt);
        this.setDamage(2);
        this.setMaxSpeed(100);
    }



    @Override
    public Node createView() {
        Label t = new Label();
        t.setId("arrow");
        return t;
    }

}
