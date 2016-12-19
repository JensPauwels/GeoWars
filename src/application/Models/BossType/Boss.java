package application.Models.BossType;

import application.Models.Sprite;
import application.Models.Vector2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.Random;

/**
 * Created by jens on 27/11/2016.
 */
public class Boss extends Sprite{


    private String name;
    private int health;
    private String typeOfBullets;
    private Vector2D destination;
    private int xp;
    private Random random = new Random();


    public Boss(Pane bp, Vector2D location) {
        super(bp, location, 400, 300);
        this.xp  = 50;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public Vector2D getDestination() {
        return destination;
    }

    public void setDestination(Vector2D destination) {
        this.destination = destination;
    }

    public String getTypeOfBullets() {
        return typeOfBullets;
    }

    public void setTypeOfBullets(String typeOfBullets) {
        this.typeOfBullets = typeOfBullets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void changeLocation(){
        this.movement(this.getDestination(),false);
        if(Math.round(this.getLocation().getX()) == Math.round(this.getDestination().getX()) && Math.round(this.getLocation().getY()) == Math.round(this.getDestination().getY())){
            Vector2D location = new Vector2D(random.nextDouble() *800,random.nextDouble()*600);
            this.setDestination(location);
        }
    }

    @Override
    public Node createView() {
        Label t = new Label();
        t.setId("dragon");
        return t;
    }
}
