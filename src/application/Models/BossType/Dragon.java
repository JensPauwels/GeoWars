package application.Models.BossType;

import application.Models.Vector2D;
import javafx.scene.layout.Pane;

/**
 * Created by jens on 27/11/2016.
 */
public class Dragon extends Boss {

    public Dragon(Pane bp, Vector2D location) {
        super(bp, location);
        this.setName("Dragon");
        this.setHealth(25);
        this.setTypeOfBullets("vuur");
        this.setDestination(this.getLocation());
    }
}
