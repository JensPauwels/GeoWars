package application.Models;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public abstract class Sprite extends Region {


    private Vector2D location;
    private Vector2D velocity;


    private Vector2D acceleration;

    private double maxForce = 0.1;
    private double maxSpeed = 2;

    private Node view;

    // view dimensions
    private double width;
    private double height;
    private double centerX;
    private double centerY;


    private double angle;




    private Pane bp  = null;

    public Sprite(Pane bp, Vector2D location, double width, double height) {



        this.bp = bp;
        this.location = location;
        this.velocity = new Vector2D(0, 0);
        this.acceleration = new Vector2D(0, 0);
        this.width = width;
        this.height = height;
        this.centerX = width / 2;
        this.centerY = height / 2;
        this.view = createView();


        setPrefSize(width, height);
        getChildren().add(view);
        bp.getChildren().add(this);
        this.display();

    }

    public Vector2D getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector2D acceleration) {
        this.acceleration = acceleration;
    }

    public void setMaxSpeed(double speed) {
        this.maxSpeed = speed;
    }

    public abstract Node createView();

    public void applyForce(Vector2D force) {
        acceleration.add(force);
    }

    public boolean coll(Sprite sprite1,Sprite sprite2){
        return  sprite1.getBoundsInParent().intersects(sprite2.getBoundsInParent());
    }

    public void movement(Vector2D target,boolean angle){
        seek(target);
        move(angle);
        display();
    }

    public void move(boolean b) {

        // set velocity depending on acceleration
        velocity.add(acceleration);

        // limit velocity to max speed
        velocity.limit(maxSpeed);

        // change location depending on velocity
        location.add(velocity);

        // angle: towards velocity (ie target)
        if(b){angle = velocity.heading2D();}

        // clear acceleration
        if(!b) acceleration.multiply(0);
    }




    public void seek(Vector2D target) {

        Vector2D desired = Vector2D.subtract(target, location);

        // The distance is the magnitude of the vector pointing from location to target.

        double d = desired.magnitude();
        desired.normalize();

        // If we are closer than 100 pixels...
        if (d < 100) {

            // ...set the magnitude according to how close we are.
            double m = 0 + (maxSpeed) * ((d) / (100));
            desired.multiply(m);

        }
        // Otherwise, proceed at maximum speed.

        desired.multiply(maxSpeed);

        // The usual steering = desired - velocity
        Vector2D steer = Vector2D.subtract(desired, velocity);
        steer.limit(maxForce);

        applyForce(steer);
    }


    public void display() {

        relocate(location.getX() - centerX, location.getY() - centerY);
        setRotate(Math.toDegrees(angle));
    }

    public Vector2D getLocation() {
        return location;
    }

    public void changeLocation(double x, double y){
        x += location.getX();
        y += location.getY();
        setLocation(x,y);
    }

    public void setLocation(double x, double y) {
        location.setX(x);
        location.setY(y);
        display();
    }

    public void setLocation(Vector2D vector2D){
        location = vector2D;
    }


}