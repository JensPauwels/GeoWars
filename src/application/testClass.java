package application;

import application.Models.EnemyType.Enemy;
import application.Multiplayer.ClientProgram;
import application.Multiplayer.PacketMessage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by jens on 13/12/2016.
 */
public class testClass extends Application {
    private long timer = System.currentTimeMillis();
    private ClientProgram cp = new ClientProgram();
    private Pane pane = new Pane();
    private PacketMessage pm = new PacketMessage();
    private   Enemy e = new Enemy(pane);

    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(pane);

        primaryStage.setScene(scene);

        scene.getStylesheets().add("application/Styling/application.css");
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.setHeight(600);
        primaryStage.setWidth(800);
        doSomething();
    }

    public void doSomething(){

        while(true){
            if(timer+500<System.currentTimeMillis()){
                System.out.println(cp.getPm().getFirstCharacter().getX() +";" + cp.getPm().getFirstCharacter().getY());
                //e.setLocation(pm.getFirstCharacter().getX(),pm.getFirstCharacter().getY());
            }
            System.out.println("hallo fgts");
        }
    }
}
