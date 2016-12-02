package application.Models.BossType;

import application.Models.Vector2D;
import javafx.scene.layout.Pane;

/**
 * Created by jens on 27/11/2016.
 */
public class Dragon extends Boss {

    public Dragon(Pane bp, Vector2D location, double width, double height) {
        super(bp, location, width, height);
        this.setName("Dragon");
        this.setHealth(100);
    }
}
