package application.Controllers;

import application.Client;
import application.Engine.Database;
import application.Engine.Engine;
import application.Engine.Game;
import application.Engine.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class TutorialController {
    public VBox vbox;
    private int count;
    public BorderPane tutorial;

    private String[] ids = {"tutorial1","tutorial2","tutorial3","tutorial4"};

    @FXML
    public void initialize() throws Exception {
        tutorial.setId(ids[count]);
    }

    @FXML
    private void back() {
        System.out.println(count);
        System.out.println(ids[count]);

        if(count ==0){
            Client.loadScreen("gameoptions");
        }
        else{
            count--;
            tutorial.setId(ids[count]);
        }
    }

    @FXML
    private void next() throws Exception{
        System.out.println(count);
        System.out.println(ids[count]);
        if(count == ids.length-1){
            if(Engine.getInstance().getGameChoice() == "multiplayer"){
             Game newGame = new Game(Client.scene, Client.mainLayout,true);
            }
            else{
             Game newGame = new Game(Client.scene, Client.mainLayout,false);
        }


        }
        else{
            count++;
            tutorial.setId(ids[count]);
        }


    }



}
