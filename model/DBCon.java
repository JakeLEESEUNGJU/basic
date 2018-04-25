package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBCon {

	static Connection con;

	private DBCon() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@70.12.115.67:1521:orcl";
		String user = "ART";
		String pass = "art";

		con = DriverManager.getConnection(url, user, pass);

	}

	public static Connection getConnection() throws Exception {
		if (con == null) {
			new DBCon();
		}
		return con;
	}
}
