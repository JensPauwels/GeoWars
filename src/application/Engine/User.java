package application.Engine;


public class User {
    private String username;
    private int highscore;


    public User(String username, int highscore) {
        this.username = username;
        this.highscore = highscore;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }
}
