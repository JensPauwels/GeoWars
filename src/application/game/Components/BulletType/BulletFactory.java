package application.game.Components.BulletType;

import application.game.Components.Layer;
import application.game.Components.Vector2D;

public class BulletFactory{

    public Bullet makeBullet(String bulletType, Layer playfield, Vector2D mainLoc, Vector2D mouseLoc){

        Bullet b = null;
        switch (bulletType){
            case "Spear":
                b = new Spear(playfield,mainLoc,mouseLoc);
                break;
            case "Arrow":
                b = new Arrow(playfield,mainLoc,mouseLoc);
                break;
            default:
               break;
        }
        return  b;

}}
