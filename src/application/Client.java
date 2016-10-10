package application;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;

public class Client extends Application  {
    private  Stage primaryStage;
    private static BorderPane mainLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("GeoWars");

        showMainView();
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            System.out.println("ik sluit het venster");
            primaryStage.close();
        });
    }

    private void showMainView() {

        mainLayout = new BorderPane();
        mainLayout.setMinHeight(600);
        mainLayout.setMinWidth(800);
        mainLayout.setId("container");
        Scene scene = new Scene(mainLayout);
        scene.getStylesheets().add("application/application.css");
        primaryStage.setScene(scene);
        primaryStage.show();
        loadScreen("loginScreen");
    }

    private static BorderPane createBorderPane(String url) {
        try{

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Client.class.getResource(url));
            return loader.load();
        }catch (IOException e){
            System.out.println("error in createBorderPane");
            return null;
        }
    }



    public static void loadScreen(String url) {
        mainLayout.setCenter(createBorderPane(url+"/"+url+".FXML"));
    }


    public static void main(String[] args)  {
        launch(args);
    }
}


