package application.Models.BulletType;


import application.Models.Vector2D;
import javafx.scene.layout.Pane;

public class Spear extends Bullet {
    public Spear(Pane bp, Vector2D mainLoc, Vector2D mouseLoc) {
        super(bp, mainLoc, mouseLoc);
        this.setDamage(1);


    }
}