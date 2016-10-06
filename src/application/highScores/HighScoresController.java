package application.highScores;

import application.Client;
import application.DbConnection;
import application.Engine;
import application.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class HighScoresController {

    private Engine instantie = Engine.getInstance();
    private DbConnection db = new DbConnection();
    public  VBox vbox;

    @FXML
    public void initialize() throws IOException {
        test();
        System.out.println("dit is de score van " +instantie.getUsername()+" : "+db.getHighscore(instantie.getUsername()));
    }

    @FXML
    private void loadGameOptions() throws IOException {
       Client.loadScreen("gameoptions");
    }

   private  void test() throws IOException {

       TableColumn<User, String> nameColumn = new TableColumn<>("username");
       nameColumn.setMinWidth(178);
       nameColumn.setId("table");
       nameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
       TableColumn<User, String> highScoreColumn = new TableColumn<>("highscore");
       highScoreColumn.setMinWidth(180);
       highScoreColumn.setId("table");
       highScoreColumn.setCellValueFactory(new PropertyValueFactory<>("highscore"));
       TableView<User> tableView = new TableView<>();


       vbox.setId("table");
       tableView.setItems(getProduct());
       tableView.getColumns().addAll(nameColumn, highScoreColumn);
       vbox.getChildren().addAll(tableView);
    }

    private  ObservableList<User> getProduct(){
        //TODO: dynamisch inladen via db
        ObservableList<User> users = FXCollections.observableArrayList();
        users.add(new User("joske","pauwels"));
        users.add(new User("testje","test"));
        users.add(new User("geiten","anothertest"));
        return users;

    }

}
