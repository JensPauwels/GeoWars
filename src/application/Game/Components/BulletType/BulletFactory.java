package application.Game.Components.BulletType;

import application.Engine.Engine;
import application.Game.Components.Vector2D;
import javafx.scene.layout.Pane;

public class BulletFactory {

    public Bullet makeBullet(Pane bp, Vector2D mainLoc, Vector2D mouseLoc) {

        Bullet b = null;
        switch (Engine.getInstance().getWeaponType()) {
            case "Spear":
                b = new Spear(bp, mainLoc, mouseLoc);
                break;
            case "Arrow":
                b = new Arrow(bp, mainLoc, mouseLoc);
                break;
            case "Bolt":
                b = new Bolt(bp, mainLoc, mouseLoc);
                break;
            default:
                break;
        }
        return b;

    }
}
