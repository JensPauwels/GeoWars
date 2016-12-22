package application;


import application.Engine.Engine;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;


public class Client extends Application {
    public static BorderPane mainLayout;
    public static Scene scene;
    private Stage primaryStage;


    public static BorderPane createBorderPane(String url) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Client.class.getResource(url));
            return loader.load();
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    public static void loadScreen(String url) {
        mainLayout.setCenter(createBorderPane("FXML/" + url + ".FXML"));
    }


    @Override
    public void start(Stage primaryStage) {
        mainLayout = new BorderPane();
        scene = new Scene(mainLayout);
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Medieval Wars");
        this.primaryStage.setResizable(false);
        showMainView();
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            this.primaryStage.close();
        });
    }



    private void showMainView() {
        mainLayout.setMinHeight(600);
        mainLayout.setMinWidth(800);
        mainLayout.setId("container");
        Font.loadFont(getClass().getResourceAsStream("../application/Styling/fonts/TrajanProRegular.ttf"), 14);
        scene.getStylesheets().add("application/Styling/application.css");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(false);
        primaryStage.show();
        loadScreen("loginScreen");
    }


}



