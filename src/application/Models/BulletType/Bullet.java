package application.Models.BulletType;

import application.Engine.Engine;
import application.Models.Sprite;
import application.Models.Vector2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


public abstract class Bullet extends Sprite {

    private Vector2D destination;
    private int damage;

    public Bullet(Pane bp, Vector2D location, Vector2D mouseLoc) {
        super(bp, location, 25, 12.5);
        setDestination(location, mouseLoc);
    }

    @Override

    public abstract Node createView();

    public Vector2D getDestination() {
        return this.destination;
    }

    public boolean outOfDestination() {
        return (getLocation().getY() > 650 || getLocation().getY() < -20 || getLocation().getX() > 850 || getLocation().getX() < -20);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setDestination(Vector2D mainLoc, Vector2D mouseLoc) {
        // calculation a : angle of inclination
        double a = (mouseLoc.getY() - mainLoc.getY()) / (mouseLoc.getX() - mainLoc.getX());
        // calculation b : startfigure
        double b = mouseLoc.getY() - (a * mouseLoc.getX());
        // y=ax+b
        double destinationX = 815;
        if (mainLoc.getX() > mouseLoc.getX()) {destinationX = -15;}
        double destinationY = (a * destinationX) + b;
        this.destination = new Vector2D(destinationX, destinationY);
    }



}
