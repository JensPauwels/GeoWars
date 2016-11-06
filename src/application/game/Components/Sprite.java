package application.game.Components;

import javafx.scene.Node;
import javafx.scene.layout.Region;

public abstract class Sprite extends Region {


    Vector2D location;
    Vector2D velocity;
    Vector2D acceleration;

    double maxForce = 0.1;
    double maxSpeed = 2;

    Node view;

    // view dimensions
    double width;
    double height;
    double centerX;
    double centerY;


    double angle;

    Layer layer = null;

    public Sprite(Layer layer, Vector2D location, Vector2D velocity, Vector2D acceleration, double width, double height) {

        this.layer = layer;
        this.location = location;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.width = width;
        this.height = height;
        this.centerX = width / 2;
        this.centerY = height / 2;
        this.view = createView();

        setPrefSize(width, height);
        getChildren().add(view);
        layer.getChildren().add(this);

    }

    public abstract Node createView();

    public void applyForce(Vector2D force) {
        acceleration.add(force);
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