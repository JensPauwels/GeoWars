package application.Game.Components.BulletType;

import application.Engine.Engine;
import application.Game.Components.Layer;
import application.Game.Components.Sprite;
import application.Game.Components.Vector2D;
import javafx.scene.Node;
import javafx.scene.control.Label;


public  class Bullet extends Sprite {

    private Vector2D destination;
    private int damage ;

    public Bullet(Layer layer, Vector2D location,Vector2D mouseLoc) {
        super(layer, location, 25, 12.5);
        setDestination(location,mouseLoc);
    }

    public Vector2D getDestination() {
        return this.destination;
    }
    public boolean outOfDestination(){

        return (getLocation().y > 650 || getLocation().y <-20 || getLocation().x > 850 || getLocation().x<-20);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setDestination(Vector2D mainLoc, Vector2D mouseLoc){
        // calculation a : angle of inclination
        double a = (mouseLoc.y-mainLoc.y)/(mouseLoc.x-mainLoc.x);
        // calculation b : startfigure
        double b = mouseLoc.y-(a*mouseLoc.x);
        // y=ax+b
        double destinationX = 815;
        if(mainLoc.x > mouseLoc.x){destinationX = -15;}
        double destinationY = (a*destinationX)+b;
        this.destination = new Vector2D(destinationX,destinationY);
    }

    // gemeesnchappelijke code

    @Override

    public Node createView() {
            Label t = new Label();
            t.setId(Engine.getInstance().getWeaponType().toLowerCase());
            return t;
    }
}
