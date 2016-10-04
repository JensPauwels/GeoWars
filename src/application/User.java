package application;

public class User {
    private String username;
    private String password;
    private int highscore;
    private int portnumber;

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

    public int getPortnumber() {
        return portnumber;
    }

    public void setPortnumber(int portnumber) {
        this.portnumber = portnumber;
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
