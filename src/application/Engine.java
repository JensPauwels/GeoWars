package application;


public class Engine {
    private static Engine firstInstance = null;
    private User currentUser;
    private DbConnection db = new DbConnection();
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

    public void setUsername(String username){
        this.username = username;
    }

    public void setCurrentUser(User currentUser){
        this.currentUser = currentUser;
    }

    public User getCurrentUser(){
        return this.currentUser;
    }

    public void saveCurrentUser() throws Exception{

        db.updateTable("t");

    }




    // functions die in de engine moeten horen hier thuis (op deze manier kan je engine.getinstance.(function)) => beter dan static te gebruiken volgends mij
    // over nadenken mogelijks kan deze class een init bevatten voor de currentplayer => dus als de player inlogged haalt de engine alle settings van de player op uit de online db
    // hierdoor kan de client alle settings van de speler weten zoals gebruik ik wasd of pijltjes etc
    // wanneer de speler iets aanpast zal dit in de engine bijgehouden worden
    // en ten slot als de speler zijn scherm afsluit zal dit doorgestuurd worden naar de database waardoor hij de volgende keer bij de login die gegevens uit de database krijgt
    // indien iets anders beter zou zijn let me know xoxo

}
