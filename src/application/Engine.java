package application;


public class Engine {
    private static Engine firstInstance = null;

    public static Engine getInstance() {
        if (firstInstance == null) {
            firstInstance = new Engine();
        }
        return firstInstance;
    }



    // functions die in de engine moeten horen hier thuis (op deze manier kan je engine.getinstance.(function)) => beter dan static te gebruiken volgends mij
    // over nadenken mogelijks kan deze class een init bevatten voor de currentplayer => dus als de player inlogged haalt de engine alle settings van de player op uit de online db
    // hierdoor kan de client alle settings van de speler weten zoals gebruik ik wasd of pijltjes etc
    // wanneer de speler iets aanpast zal dit in de engine bijgehouden worden
    // en ten slot als de speler zijn scherm afsluit zal dit doorgestuurd worden naar de database waardoor hij de volgende keer bij de login die gegevens uit de database krijgt
    // indien iets anders beter zou zijn let me know xoxo

}
