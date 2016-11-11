package application.game.Components.BulletType;

import application.game.Components.Layer;
import application.game.Components.Sprite;
import application.game.Components.Vector2D;
import javafx.scene.Node;
import javafx.scene.shape.Circle;


/**
 * Created by Griet Coysman on 10/11/2016.
 */
public  class Bullet extends Sprite {

    private String name;
    private Vector2D destination;
    private int damage ;

    public String getName(){
        return  name;
    }
    public void setName(String name){
        this.name=name;
    }
    public Vector2D getDestination() {
        return this.destination;
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
        Circle circle = new Circle();
        circle.setCenterX(10);
        circle.setCenterY(10);
        circle.setRadius(5.0);
        return circle;


    }

    public Bullet(Layer layer, Vector2D location,Vector2D mouseLoc) {
        super(layer, location, 25, 12.5);
        setDestination(location,mouseLoc);
        System.out.println("ik ben hierin geweest");

    }








}
