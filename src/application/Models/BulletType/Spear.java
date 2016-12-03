package application.Models.BulletType;


import application.Engine.Engine;
import application.Models.Vector2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class Spear extends Bullet {
    public Spear(Pane bp, Vector2D mainLoc, Vector2D mouseLoc) {
        super(bp, mainLoc, mouseLoc);
        this.setDamage(1);


    }

    @Override
    public Node createView() {
        Label t = new Label();
        t.setId("spear");
        return t;
    }
}
