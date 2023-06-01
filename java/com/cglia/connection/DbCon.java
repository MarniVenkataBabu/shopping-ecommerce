package com.cglia.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author venkata.marni
 * @since 22-05-2023
 * @version 1.0
 */
public class DbCon {

	private static Connection connection;

	private DbCon() {
		// Private constructor to hide the implicit public one
	}

	/**
	 * 
	 * @return An URL that is used for connecting to the database
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if (connection == null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://192.168.60.22:3306/venkat", "venkat","venkat@123456");
		}
		return connection;
	}
}