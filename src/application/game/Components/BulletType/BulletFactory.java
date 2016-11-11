package application.game.Components.BulletType;

import application.Engine.Engine;
import application.game.Components.Layer;
import application.game.Components.Vector2D;

public class BulletFactory{

    public Bullet makeBullet( Layer playfield, Vector2D mainLoc, Vector2D mouseLoc){

        Bullet b = null;
        switch (Engine.getInstance().getWeaponType()){
            case "Spear":
                b = new Spear(playfield,mainLoc,mouseLoc);
                break;
            case "Arrow":
                b = new Arrow(playfield,mainLoc,mouseLoc);
                break;
            case "Bolt":
                b = new Bolt(playfield,mainLoc,mouseLoc);
                break;
            default:
               break;
        }
        return  b;

}}
