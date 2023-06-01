package com.cglia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.cglia.model.User;

/**
 * 
 * @author venkata.marni
 * @since 22-05-2023
 * @version 1.0
 */
public class UserDao {
	private Connection connection;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	private static final Logger logger = LogManager.getLogger(UserDao.class);

	/**
	 * 
	 * @param connection - instantiates connection
	 */
	public UserDao(Connection connection) {
		this.connection = connection;
	}

	/**
	 * 
	 * @param email-to    pass the email
	 * @param password-to pass the password
	 * @return user if found with details else null if not found
	 * @throws SQLException
	 */
	public User userLogin(String email, String password) {
		User user = null;
		try {
			query = "select * from venkat.users where email = ? and password = ?";
			pst = connection.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, password);
			rs = pst.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("No user found in the database: {}", e.getMessage(), e);
		}
		return user;
	}

	/**
	 * 
	 * @param name-required     to save in user table
	 * @param email-required    to save in user table
	 * @param password-required to save in user table
	 * @return true if user created else false if not created
	 * @throws SQLException
	 */
	public boolean createUser(String name, String email, String password) {
		boolean result = false;
		try {
			query = "insert into venkat.users (name, email, password) values (?,?,?)";
			pst = connection.prepareStatement(query);
			pst.setString(1, name);
			pst.setString(2, email);
			pst.setString(3, password);
			pst.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("insert to users table in venkat database not inserted: {}", e.getMessage(), e);
		} 
		return result;
	}

	/**
	 * 
	 * @param email-required to get email from the database.
	 * @return boolean value of true if email is found else false.
	 * @throws SQLException.
	 */
	public boolean checkEmailExists(String email) {
		try {
			query = "SELECT COUNT(*) FROM venkat.users WHERE email = ?";
			pst = connection.prepareStatement(query);
			pst.setString(1, email);
			rs = pst.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			return count > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;
	}

	/**
	 * 
	 * @param email-String       value
	 * @param newPassword-String value
	 * @return boolean value if password is updated returns true
	 * @throws SQLException
	 */
	public boolean resetPassword(String email, String newPassword) {
		try {
			query = "UPDATE venkat.users SET password = ? WHERE email = ?";
			pst = connection.prepareStatement(query);
			pst.setString(1, newPassword);
			pst.setString(2, email);
			int rowsAffected = pst.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;
	}

	/**
	 * 
	 * @param userId
	 * @return user with details already present in the database get user by Id
	 */
	public User getUserById(int userId) {
		User user = null;
		try {
			query = "SELECT * FROM users WHERE id=?";
			pst = connection.prepareStatement(query);
			pst.setInt(1, userId);
			rs = pst.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				// Set other user details here
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * 
	 * @param user
	 * @return boolean value true if user updated else false if not updated update
	 *         user details
	 */
	public boolean updateUser(User user) {
		boolean updated = false;
		try {
			query = "UPDATE users SET name=?, email=? WHERE id=?";
			pst = connection.prepareStatement(query);
			pst.setString(1, user.getName());
			pst.setString(2, user.getEmail());
			pst.setInt(3, user.getId());

			int rows = pst.executeUpdate();
			updated = (rows > 0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updated;
	}
}
