package application.game.Components.BulletType;


import application.game.Components.Layer;
import application.game.Components.Vector2D;

public class Spear extends Bullet {
    public Spear(Layer layer, Vector2D mainLoc,Vector2D mouseLoc){
        super(layer,mainLoc,mouseLoc);
        this.setDamage(1);
        this.setName("spear");
    }
}
