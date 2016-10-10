package application;

public class User {
    private String username;
    private String password;
    private int highscore;

    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.highscore = 0;
    }

    public User(){
        this.username = "" ;
        this.password = "";
        this.highscore = 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }
}
