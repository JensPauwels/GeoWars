package application.Multiplayer;

import application.Models.Vector2D;

/**
 * Created by jens on 22/12/2016.
 */
public class BulletPositions {
    private Vector2D start;
    private Vector2D end;
    private Boolean outOfDestination;
    private int randomId;

    public BulletPositions(){

    }



    public BulletPositions(Vector2D start,Vector2D end,int randomId){
        this.start = start;
        this.end = end;
        this.randomId = randomId;
    }

    public int getRandomId() {
        return randomId;
    }

    public void setRandomId(int randomId) {
        this.randomId = randomId;
    }

    public Vector2D getStart() {
        return start;
    }

    public void setStart(Vector2D start) {
        this.start = start;
    }

    public Vector2D getEnd() {
        return end;
    }

    public void setEnd(Vector2D end) {
        this.end = end;
    }

}
