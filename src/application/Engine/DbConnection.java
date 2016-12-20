package application.Engine;

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


        String url = "jdbc:mysql://localhost/geowars";
        String username = "root";
        String password = "localhost";


        try {
            Connection con = DriverManager.getConnection(url, username, password);
            st = con.createStatement();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public boolean controle(String query) {
        try {
            rs = st.executeQuery(query);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updateTable(String query) {
        try {
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User initUser(String username) {
        String query = "select * from users where username like'" + username + "'";


        try {
            rs = st.executeQuery(query);
            if (rs.next()) {
                int highscore = rs.getInt("highscore");
                currentUser = new User(username, highscore);
            }
            query = "select * from settings where username like'" + username + "'";
            rs = st.executeQuery(query);

            if (rs.next()) {
                Settings currentUserSettings = currentUser.getSettings();
                currentUserSettings.setAutoSave(rs.getBoolean("autosave"));
                currentUserSettings.setMusic(rs.getBoolean("music"));
            }
            return currentUser;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }


    public List<User> getHighscores(String query) {
        List<User> userList = new LinkedList<>();
        try {
            rs = st.executeQuery(query);
            while (rs.next()) {
                String username = rs.getString("username");
                int highScore = rs.getInt("highscore");
                userList.add(new User(username, highScore));
            }
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }
}
