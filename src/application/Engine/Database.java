package application.Engine;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Database {

    private static Database instance;
    private final String URL = "jdbc:mysql://sql8.freemysqlhosting.net:3306/sql8150540";
    private final String UID = "sql8150540";
    private final String PWD = "8iBCWbDukA";

    private Connection connection;
    private User currentUser;

    private Database() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, UID, PWD);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }


    public void saveUser(String username, String password) {
        try {
            String sql = "INSERT INTO users(username, password, highscore, coins, wave) VALUES (?, ?, 0, 0, 0)";

            PreparedStatement prep = this.connection.prepareStatement(sql);
            prep.setString(1, username);

            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            prep.setString(2, hashedPassword);

            prep.executeUpdate();
            prep.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public User getUser(String loginUsername, String loginPassword) {
        try {
            String dbUsername;
            String dbPassword;
            String sql = "SELECT * FROM users WHERE username = ?";

            PreparedStatement prep = this.connection.prepareStatement(sql);
            prep.setString(1, loginUsername);

            ResultSet rs = prep.executeQuery();

            if (rs.next()) {
                dbUsername = rs.getString("username");
                dbPassword = rs.getString("password");

                if (BCrypt.checkpw(loginPassword, dbPassword)) {
                    System.out.println("Excisting user");
                    //currentUser = new User(dbUsername, dbPassword);
                    return currentUser;
                } else {
                    System.out.println("User not found");
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean checkUser(String loginUsername, String loginPassword) {
        try {
            String dbUsername;
            String dbPassword;
            String sql = "SELECT * FROM users WHERE username = ?";

            PreparedStatement prep = this.connection.prepareStatement(sql);
            prep.setString(1, loginUsername);

            ResultSet rs = prep.executeQuery();

            if (rs.next()) {
                dbUsername = rs.getString("username");
                dbPassword = rs.getString("password");

                if (BCrypt.checkpw(loginPassword, dbPassword)) {
                    System.out.println("Excisting user");
                    //currentUser = new User(dbUsername, dbPassword);
                    return true;
                } else {
                    System.out.println("User not found");
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }


    public void updateHighscoreFromUser(String username, int highscore) {
        try {
            String sql = "UPDATE users SET highscore = ? WHERE username = ?";

            PreparedStatement prep = this.connection.prepareStatement(sql);
            prep.setInt(1, highscore);
            prep.setString(2, username);

            prep.executeUpdate();
            prep.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void updateCurrentWaveFromUser(String username, int wave) {
        try {
            String sql = "UPDATE users SET wave = ? WHERE username = ?";

            PreparedStatement prep = this.connection.prepareStatement(sql);
            prep.setInt(1, wave);
            prep.setString(2, username);

            prep.executeUpdate();
            prep.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void updateCoinsFromUser(String username, int amountOfCoins) {
        try {
            String sql = "UPDATE users SET coins = ? WHERE username = ?";

            PreparedStatement prep = this.connection.prepareStatement(sql);
            prep.setInt(1, amountOfCoins);
            prep.setString(2, username);

            prep.executeUpdate();
            prep.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public int getHighscoreFromUser(String username) {
        try {
            String sql = "SELECT * FROM users WHERE username = ?";

            PreparedStatement prep = this.connection.prepareStatement(sql);
            prep.setString(1, username);

            ResultSet rs = prep.executeQuery();

            if (rs.next()) {
                return rs.getInt("highscore");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }


    public List<User> getHighscoreOfAllUsers() {
        List<User> userList = new LinkedList<>();
        try {
            String sql = "SELECT username, highscore FROM users";

            PreparedStatement prep = this.connection.prepareStatement(sql);

            ResultSet rs = prep.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                int highscore = rs.getInt("highscore");
                userList.add(new User(username, highscore));
            }
            return userList;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public int getRankingOfAllUsers() {
        try {
            String sql = "SELECT u.*, @curRank := @curRank + 1 AS rank FROM users u, (SELECT @curRank := 0) r ORDER BY highscore DESC";

            PreparedStatement prep = this.connection.prepareStatement(sql);

            ResultSet rs = prep.executeQuery();

            while (rs.next()) {
                int rank = rs.getInt("rank");
                String username = rs.getString("username");
                int highscore = rs.getInt("highscore");
                System.out.println(rank + " : " + username + " - " + highscore);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }


    public int getRankingOfUser(String player) {
        try {
            String sql = "SELECT u.*, @curRank := @curRank + 1 AS rank FROM users u, (SELECT @curRank := 0) r WHERE username = ? ORDER BY highscore DESC";

            PreparedStatement prep = this.connection.prepareStatement(sql);
            //prep.setString(1, player);

            ResultSet rs = prep.executeQuery();

            if (rs.next()) {
                int rank = rs.getInt("rank");
                String username = rs.getString("username");
                int highscore = rs.getInt("highscore");
                System.out.println(rank + " : " + username + " - " + highscore);
                return rank;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public long getWeaponDamage(String weaponName) {
        try {
            String sql = "SELECT damage FROM weapons where type = ?";

            PreparedStatement prep = this.connection.prepareStatement(sql);
            prep.setString(1, weaponName);

            ResultSet rs = prep.executeQuery();

            if (rs.next()) {
                long damage = rs.getLong("damage");
                return damage;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

}
