package application.Models.EnemyType;

import application.Models.Sprite;
import application.Models.Vector2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.Random;

public class Enemy extends Sprite {
    private int health;
    private int xp;
    private Vector2D destination;
    private Random random = new Random();
    private int idn;
    private String myid = "enemy1";

    public Enemy(Pane bp) {
        super(bp, new Vector2D(), 12.5, 25);
        this.xp = 10;
        this.health = 1;
        this.setId(myid);


    }

    public Enemy(Pane bp,String myid) {
        super(bp, new Vector2D(), 12.5, 25);
        this.xp = 10;
        this.health = 2;
        this.myid = myid;
        this.setId(myid);

    }

    public Enemy(Pane bp,Vector2D location,int id ){
        super(bp, location, 12.5, 25);
        this.xp = 10;
        this.idn = id;
    }

    public int getIdn() {
        return idn;
    }

    public void setIdn(int idn) {
        this.idn = idn;
    }

    public int getXp(){
        return this.xp;
    }

    public Vector2D getDestination() {
        return destination;
    }

    public void setDestination(Vector2D destination) {
        this.destination = destination;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }



    public void changeLocation(){
        this.movement(this.getDestination(),false);
        if(Math.round(this.getLocation().getX()) == Math.round(this.getDestination().getX()) && Math.round(this.getLocation().getY()) ==                 Math.round(this.getDestination().getY())){
            Vector2D location = new Vector2D(random.nextDouble() *800,random.nextDouble()*600);
            this.setDestination(location);
        }
    }

    @Override
    public Node createView() {
        Label img = new Label();
        img.setId(this.myid);
        return img;
    }


}
