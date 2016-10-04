package application;

import application.multiPlayer.JoinMultiPlayer;
import javafx.scene.layout.BorderPane;

public class Engine {

    private User user = new User();
    private JoinMultiPlayer client = new JoinMultiPlayer(getUsername(),1201);
    private static Engine firstInstance = null;
    private static boolean firstThread = true;



    public static Engine getInstance()  {
        if(firstInstance == null) {
            if(firstThread){
                firstThread = false;
                try {
                    Thread.currentThread();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            synchronized(Engine.class){
                if(firstInstance == null) {firstInstance = new Engine();}
            }
        }
        return firstInstance;
    }

    public void setUserName(String username){
        this.user.setUsername(username);
    }

    public String getUsername(){
        return this.user.getUsername();
    }
    public User getUser(){ return this.user;}
    public JoinMultiPlayer getClient() {return this.client;}

    public void changeScreen(){

    }






}
