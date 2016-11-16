package application.Game.Components.BulletType;


import application.Game.Components.Layer;
import application.Game.Components.Vector2D;

public class Spear extends Bullet {
    public Spear(Layer layer, Vector2D mainLoc,Vector2D mouseLoc){
        super(layer,mainLoc,mouseLoc);
        this.setDamage(1);
    }
}
