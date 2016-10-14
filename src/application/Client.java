package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;

public class Client extends Application {
    private Stage primaryStage;
    private static BorderPane mainLayout = new BorderPane();
    private Scene scene = new Scene(mainLayout);

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("application.SinglePlayer");
        showMainView();
    }

    private void showMainView() {

        mainLayout.setMinHeight(600);
        mainLayout.setMinWidth(800);
        mainLayout.setId("container");
        scene.getStylesheets().add("application/application.css");
        primaryStage.setScene(scene);
        primaryStage.show();
        loadScreen("loginScreen");
    }

    private static BorderPane createBorderPane(String url) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Client.class.getResource(url));
            return loader.load();

        } catch (IOException e) {
            return null;
        }
    }

    public static void loadScreen(String url) {
        mainLayout.setCenter(createBorderPane(url + "/" + url + ".FXML"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}


