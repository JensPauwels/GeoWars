package application.Engine;


import application.DataBase.DbConnection;
import application.UserInterface;

public class Engine {
    private static Engine firstInstance = null;
    private User currentUser;
    private DbConnection db = new DbConnection();
    private UserInterface ui;
    private String username;



    public static Engine getInstance() {
        if (firstInstance == null) {
            firstInstance = new Engine();
        }
        return firstInstance;
    }

    public void initCurrentUser(){

        try{
            setCurrentUser(db.initUser(username));
            System.out.println(currentUser.getHighscore());
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }

    public void setUi(UserInterface ui) {
        this.ui = ui;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getCurrentUser(){
        return this.currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void saveCurrentUser() throws Exception{

        String query = "UPDATE settings SET music=" + currentUser.getSettings().isMusic() + ",autosave=" + currentUser.getSettings().isAutoSave() + " WHERE username = '" + currentUser.getUsername() + "'";
        db.updateTable(query);

    }

    public DbConnection getDb(){
        return this.db;
    }

}
