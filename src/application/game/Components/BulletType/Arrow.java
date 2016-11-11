package application.game.Components.BulletType;

import application.game.Components.Layer;
import application.game.Components.Vector2D;
/**
 * Created by Griet Coysman on 11/11/2016.
 */
public class Arrow extends Bullet{

        public Arrow(Layer layer, Vector2D mainLoc, Vector2D mouseLoc){
            super(layer,mainLoc,mouseLoc);
            this.setName("Arrow");
            this.setDamage(2);

        }

}
