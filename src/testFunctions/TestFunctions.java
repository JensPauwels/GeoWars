package testFunctions;

import static org.junit.Assert.*;
import org.junit.Test;

import application.DbConnection;


public class TestFunctions {
	DbConnection db = new DbConnection();
	private String username = "test";
	private String pass = "test123";

	@Test
	public void kanari() {
		System.out.println("piep");
	}

	@Test
	public void duplicatedUsers() {
		String query =  "select * from users where username like '" + username + "'";
		if (!db.controle(query)) {
			System.err.println("duplicatedUser");
		}
	}

	@Test
	public void controlOnLogin() {
		String query = "select * from users where username like '" + username + "' and password like '" + pass+ "'";
		
		if (!db.controle(query)) {
			System.err.println("controleLogin");
		}
	}
}
