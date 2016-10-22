package application;

import application.Engine.Engine;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;

public class UserInterface extends Application {
    private Stage primaryStage;
    private static BorderPane mainLayout = new BorderPane();
    private Scene scene = new Scene(mainLayout);

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("GeoWars");
        showMainView();

        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            Engine instance = Engine.getInstance();
            try {
                instance.saveCurrentUser();
            } catch (Exception e1) {}
            primaryStage.close();
        });
    }

    private void showMainView() {

        mainLayout.setMinHeight(600);
        mainLayout.setMinWidth(800);
        mainLayout.setId("container");
        scene.getStylesheets().add("application/Styling/application.css");
        primaryStage.setScene(scene);
        primaryStage.show();
        loadScreen("loginScreen");
    }

    private static BorderPane createBorderPane(String url) {
        BorderPane borderPane = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UserInterface.class.getResource(url));
            borderPane = loader.load();
        } catch (IOException e) {}
        return borderPane;
    }

    public static void loadScreen(String url) {
        mainLayout.setCenter(createBorderPane(url + "/" + url + ".FXML"));
    }

}


