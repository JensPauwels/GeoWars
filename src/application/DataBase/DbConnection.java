package application.DataBase;

import application.Engine.Settings;
import application.Engine.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DbConnection {
    private Statement st;
    private ResultSet rs;
    private User currentUser;

    public DbConnection() {




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

    public void updateTable(String query) {
        try {
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            currentUserSettings.setAutoSave(rs.getBoolean("autosave"));
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
