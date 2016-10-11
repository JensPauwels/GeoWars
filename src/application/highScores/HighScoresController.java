package application.highScores;

import application.Client;
import application.DbConnection;
import application.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class HighScoresController {
    public  VBox vbox;
    private DbConnection db = new DbConnection();

    @FXML
    public void initialize() throws IOException {
        loadHighScoreResults();
    }

    @FXML
    private void loadGameOptions() throws IOException {
       Client.loadScreen("gameoptions");
    }

   private  void loadHighScoreResults() throws IOException {
       TableView<User> tableView = new TableView<>();
       tableView.setItems(generateUserData());
       tableView.setMaxHeight(500);
       tableView.getColumns().addAll(createColumn("username"), createColumn("highscore"));
       vbox.getChildren().addAll(tableView);
   }

    private TableColumn<User,String> createColumn(String columnname){
        TableColumn<User, String> column = new TableColumn<>(columnname);
        column.setMinWidth(199);
        column.setId("table");
        column.setCellValueFactory(new PropertyValueFactory<>(columnname));
        return column;
    }

    private  ObservableList<User> generateUserData(){
        //TODO: dynamisch inladen via db
        ObservableList<User> users = FXCollections.observableArrayList();
        List<User> test =   db.getHighscores("SELECT  userName,highscore FROM users order by highscore desc");
        for (int i=0; i<test.size();i++) {users.add(test.get(i));
        }
        return users;

    }

}
