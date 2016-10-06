package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbConnection {
	private Statement st;
	private ResultSet rs;
	//private PreparedStatement ps;



	public DbConnection() {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3309/loginsystem?autoReconnect=true&useSSL=false", "root", "");
			st = con.createStatement();
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
	}
	
	public boolean controle( String query) {
		boolean bool = false;
		try {
			rs = st.executeQuery(query);
			if (rs.next()) {
				bool = true;
			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return bool;}


	//db.updateTable("update users set highscore=" + 500 + " where username like 'test'");
	public void updateTable(String query){
		try {
			st.executeUpdate(query);
		} catch (Exception ex) {
			System.out.println("Error: " +ex);
		}
	}

	public String getHighscore(String username) {
		String highscore = "";
		try {
			String query = "select highscore from users where username like '" + username + "'";
			rs = st.executeQuery(query);
			rs.first();
			highscore = rs.getString(1);
		} catch (Exception ex) {
			System.out.println("Error: " +ex);
		}
		return highscore;
	}

	public int getPortNumber(String username) {
		int highscore = 0;
		try {
			String query = "select portnumber from users where username like '" + username + "'";
			rs = st.executeQuery(query);
			rs.first();
			highscore = rs.getInt(1);
		} catch (Exception ex) {
			System.out.println("Error: " +ex);
		}
		return highscore;
	}



}
