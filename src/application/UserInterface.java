package application;


import application.Engine.Engine;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;


public class UserInterface extends Application {


    public static BorderPane mainLayout = new BorderPane();
    public static Scene scene = new Scene(mainLayout);
    private Stage primaryStage;
    private Engine instance = Engine.getInstance();


    public UserInterface() {
        instance.setUi(this);
    }

    public static BorderPane createBorderPane(String url) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(application.UserInterface.class.getResource(url));
            return loader.load();
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    public static void loadScreen(String url) {
        mainLayout.setCenter(createBorderPane(url + "/" + url + ".FXML"));
    }

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Medieval Wars");
        this.primaryStage.setResizable(false);
        showMainView();
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            close();
        });


        // dit is om direct de game te starten
       // Game newGame = new Game(UserInterface.scene, UserInterface.mainLayout);
       // newGame.initGame();
    }

    private void close() {
        try {
            instance.saveCurrentUser();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        primaryStage.close();
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


}



