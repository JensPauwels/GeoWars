package application.Controllers;

import application.Client;
import application.Engine.DbConnection;
import application.Engine.Engine;
import application.Engine.User;
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
    public VBox vbox;
    private DbConnection db = Engine.getInstance().getDb();

    @FXML
    public void initialize() throws Exception {
        loadHighScoreResults();
    }

    @FXML
    private void loadGameOptions() throws IOException {
        Client.loadScreen("gameoptions");
    }

    private void loadHighScoreResults() throws Exception {
        TableView<User> tableView = new TableView<>();
        tableView.setId("testing");
        tableView.setItems(generateUserData());
        tableView.setMaxHeight(500);
        tableView.getColumns().addAll(createColumn("username"), createColumn("highscore"));
        vbox.getChildren().addAll(tableView);
    }

    private TableColumn<User, String> createColumn(String columnname) {
        TableColumn<User, String> column = new TableColumn<>(columnname);
        column.setMinWidth(199);
        column.setId("table");
        column.setCellValueFactory(new PropertyValueFactory<>(columnname));
        return column;
    }

    private ObservableList<User> generateUserData() throws Exception {
        ObservableList<User> users = FXCollections.observableArrayList();
        String query = "SELECT  userName,highscore FROM users order by highscore desc";
        List<User> listOfUsers = db.getHighscores(query);

        int number = listOfUsers.size();
        if (number > 5) {
            number = 5;
        }
        for (int i = 0; i < number; i++) {
            users.add(listOfUsers.get(i));
        }
        return users;
    }

}
