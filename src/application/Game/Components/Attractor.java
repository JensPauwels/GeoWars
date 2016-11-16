package application.Game.Components;

import application.Engine.Engine;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


public class Attractor extends Sprite {
    private int lives;

    public Attractor(Pane bp, Vector2D location) {
        super(bp, location, 25, 25);
        this.lives = 3;
    }

    @Override
    public Node createView() {

        Label t = new Label("                       ");
        t.setId("characterWith" + Engine.getInstance().getWeaponType());
        System.out.println(t.getId());
        return t;

    }

    public void setlives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return this.lives;
    }


}