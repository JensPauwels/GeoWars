package application.game.Components;

import application.game.Components.BulletType.Enemy;
import javafx.scene.Node;
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

    private Layer layer = null;






    public Sprite(Layer layer, Vector2D location, double width, double height) {

        this.layer = layer;
        this.location = location;
        this.velocity = new Vector2D(0,0);
        this.acceleration = new Vector2D(0,0);
        this.width = width;
        this.height = height;
        this.centerX = width / 2;
        this.centerY = height / 2;
        this.view = createView();


        setPrefSize(width, height);
        getChildren().add(view);
        layer.getChildren().add(this);

    }

    public void setMaxSpeed(double speed){
        this.maxSpeed = speed;
    }

    public abstract Node createView();

    public void applyForce(Vector2D force) {
        acceleration.add(force);
    }

    public boolean shoot(Bullet a, Enemy e) {
        return a.getBoundsInParent().intersects(e.getBoundsInParent());
    }

    public void move() {

        // set velocity depending on acceleration
        velocity.add(acceleration);

        // limit velocity to max speed
        velocity.limit(maxSpeed);

        // change location depending on velocity
        location.add(velocity);

        // angle: towards velocity (ie target)
        angle = velocity.heading2D();

        // clear acceleration
        acceleration.multiply(0);
    }


    public void seek(Vector2D target) {

        Vector2D desired = Vector2D.subtract(target, location);

        // The distance is the magnitude of the vector pointing from location to target.

        double d = desired.magnitude();
        desired.normalize();

        // If we are closer than 100 pixels...
        if (d < 100) {

            // ...set the magnitude according to how close we are.
            double m = Utils.map(d, 0, 100, 0, maxSpeed);
            desired.multiply(m);

        }
        // Otherwise, proceed at maximum speed.
        else {
            desired.multiply(maxSpeed);
        }

        // The usual steering = desired - velocity
        Vector2D steer = Vector2D.subtract(desired, velocity);
        steer.limit(maxForce);

        applyForce(steer);
    }

    public void display() {

        relocate(location.x - centerX, location.y - centerY);
        setRotate(Math.toDegrees(angle));
    }

    public Vector2D getLocation() {
        return location;
    }


    public void setLocation(double x, double y) {
        location.x = x;
        location.y = y;
    }


}