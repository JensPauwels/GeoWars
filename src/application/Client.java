package application;


import application.gameOptions.GameOptionsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;

import static java.awt.SystemColor.window;

public class Client extends Application  {
    private  Stage primaryStage;
    private static BorderPane mainLayout;

    @Override
    public void start(Stage primaryStage) throws IOException{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("GeoWars");
        showMainView();

        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            System.out.println("ik sluit het venster");

            primaryStage.close();
        });
    }

    private void showMainView() throws IOException{
        mainLayout = createBorderPane("main/main.FXML");
        Scene scene = new Scene(mainLayout);
        scene.getStylesheets().add("application/application.css");
        primaryStage.setScene(scene);
        primaryStage.show();
        loadScreen("loginScreen");
    }

    private static BorderPane createBorderPane(String url) throws IOException{
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(Client.class.getResource(url));
    	return loader.load();
    }

    public static void loadScreen(String url) throws IOException{
        mainLayout.setCenter(createBorderPane(url+"/"+url+".FXML"));
    }


    public static void main(String[] args) throws IOException {
        launch(args);
    }

}


