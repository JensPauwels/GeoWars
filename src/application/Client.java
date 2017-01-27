package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;

public class Client extends Application {
    public static BorderPane mainLayout;
    public static Scene scene;
    private Stage primaryStage;

    private static BorderPane createBorderPane(String url) {
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


    public void open(){
        getHostServices().showDocument("https://www.facebook.com/dialog/feed?app_id=87741124305&ref=site&display=page&%20name=Medieval%20Wars%20&caption=Gainz.tk%20&description=I%20just%20reached%20a%20score%20of%20points%20&picture=https://s30.postimg.org/4fui4ff1d/Screen_Shot_2016_12_23_at_22_45_27.png&link=http://google.com%20&actions=%5B%7B%20name:%20%27Your%20Footer%20Link%27,%20link:%20%27http://google.com/%27%20%7D%5D%20&properties=%5B%7B%20text:%20%27Google%27,%20href:%20%27http://google.com/%27%7D,%7B%20text:%20%27Bing%27,%20href:%20%27http://bing.com/%27%7D,%7B%20text:%20%27Yahoo%27,%20href:%20%27http://yahoo.com/%27%7D%5D%20&redirect_uri=https://www.facebook.com");

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
        Font.loadFont(getClass().getResourceAsStream("Styling/fonts/TrajanProRegular.ttf"), 14);
        scene.getStylesheets().add("application/Styling/application.css");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(false);
        primaryStage.show();
        loadScreen("loginScreen");

    }


}



