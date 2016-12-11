package application.Models;


import java.io.Serializable;
import java.util.Random;

public class Vector2D implements Serializable{

    private Random random = new Random();
    private double x;
    private double y;

    public Vector2D(){
        double x = random.nextDouble() * 800;
        double y = random.nextDouble() * 600;

        if (x < 400) {
            x -= 500;
        } else {
            x += 500;
        }
        if (y < 300) {
            y -= 300;
        } else {
            y += 300;
        }
        this.x = x;
        this.y = y;
    }

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }


    static public Vector2D subtract(Vector2D v1, Vector2D v2) {
        return new Vector2D(v1.x - v2.x, v1.y - v2.y);
    }


    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public void add(Vector2D v) {
        x += v.x;
        y += v.y;
    }

    public void multiply(double n) {
        x *= n;
        y *= n;
    }

    public void div(double n) {
        x /= n;
        y /= n;
    }

    public void normalize() {
        double m = magnitude();
        if (m != 0 && m != 1) {
            div(m);
        }
    }

    public void limit(double max) {
        if (magnitude() > max) {
            normalize();
            multiply(max);
        }
    }

    public double heading2D() {
        return Math.atan2(y, x);
    }

}