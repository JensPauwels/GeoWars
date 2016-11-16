package application.Game.Components.BulletType;

import application.Game.Components.Layer;
import application.Game.Components.Vector2D;
/**
 * Created by Griet Coysman on 11/11/2016.
 */
public class Arrow extends Bullet{

        public Arrow(Layer layer, Vector2D mainLoc, Vector2D mouseLoc){
            super(layer,mainLoc,mouseLoc);
            this.setDamage(2);
            this.setMaxSpeed(100);

        }

}
