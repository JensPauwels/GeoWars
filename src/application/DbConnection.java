package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class DbConnection {
    private Statement st;
    private ResultSet rs;
    private User currentUser;

    public DbConnection() {
        String url = "jdbc:mysql://sql8.freesqldatabase.com:3306/sql8139608";
        String username = "sql8139608";
        String password = "";

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            st = con.createStatement();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public boolean controle(String query) throws Exception {
        rs = st.executeQuery(query);
        return rs.next();
    }

    public void updateTable(String query) throws Exception {
        st.executeUpdate(query);
    }

    public User initUser(String username) throws  Exception{
        String query = "select * from users where username like'"+username +"'";

        rs = st.executeQuery(query);
        if(rs.next()){
            int highscore = rs.getInt("highscore");
            currentUser = new User(username,highscore);
        }

        query = "select * from settings where username like'"+username + "'";
        rs = st.executeQuery(query);

        if(rs.next()){
            Settings currentUserSettings = currentUser.getSettings();
            currentUserSettings.setAutoSave(rs.getBoolean("autoSave"));
            currentUserSettings.setMusic(rs.getBoolean("music"));
        }
        return currentUser;

    }

    public List<User> getHighscores(String query) throws Exception {
        List<User> userList = new LinkedList<>();
        rs = st.executeQuery(query);
        while (rs.next()) {
            String username = rs.getString("username");
            int highScore = rs.getInt("highscore");
            userList.add(new User(username, highScore));
        }
        return userList;
    }
}
