package application.game.Components.BulletType;

import application.game.Components.Layer;
import application.game.Components.Sprite;
import application.game.Components.Vector2D;
import javafx.scene.Node;
import javafx.scene.shape.Circle;

public class Bullet extends Sprite {
    private Vector2D destination;
    private int damage;

    public Bullet(Layer layer, Vector2D location) {
        super(layer, location, 25, 12.5);
    }

    public void setDamage(int dmg){
        this.damage = dmg;
    }

    public int getDamage(){
        return this.damage;
    }

    public Vector2D getDestination() {
        return this.destination;
    }

    public void setDestination(double destinationX , double destinationY){
        Vector2D destination = new Vector2D(destinationX,destinationY);
        this.destination = destination;
    }

    @Override
    public Node createView() {
        Circle circle = new Circle();
        circle.setCenterX(10);
        circle.setCenterY(10);
        circle.setRadius(5.0);
        return circle;
    }



}
