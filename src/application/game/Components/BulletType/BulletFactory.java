package application.game.Components.BulletType;

import application.game.Components.*;

/**
 * Created by Griet Coysman on 10/11/2016.
 */
public class BulletFactory{

    public Bullet makeBullet(String bulletType, Layer playfield, Vector2D mainLoc, Vector2D mouseLoc){
        //Bullet newBulllet = null;

        if (bulletType.equals("Spear")) {
            return new Spear(playfield,mainLoc,mouseLoc);
        }else {if (bulletType.equals("Arrow")) {
            return new Arrow(playfield,mainLoc,mouseLoc);
        }else {
            return null;
        }

}}}
