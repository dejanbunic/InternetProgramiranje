package net.etfbl.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.etfbl.project.dto.User;

public class UserDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_BY_USERNAME_AND_PASSWORD = "SELECT * FROM user WHERE username=? AND password=?";
	private static final String SQL_SELECT_BY_USERNAME = "SELECT * FROM user WHERE username=?";
	private static final String SQL_SELECT_BY_EMAIL = "SELECT * FROM user WHERE email=?";
	private static final String SQL_INSERT_USER = "INSERT INTO user (username, password, email, firstName, lastName) VALUES(?,?,?,?,?)";
	private static final String SQL_GET_USER_BY_ID = "SELECT * FROM user WHERE id=?";
	private static final String SQL_GET_USER_BY_USERNAME = "SELECT * FROM user WHERE username=?";
	private static final String SQL_UPDATE_PICTURE = "UPDATE user SET picture=? WHERE username=?";
	private static final String SQL_CHECK_PASSWORD = "SELECT password FROM user WHERE username=?";
	private static final String SQL_CHANGE_PASSWORD = "UPDATE user SET password=? WHERE username=?";
	private static final String SQL_UPDATE_USER_BY_ID = "UPDATE user SET username=?, email=?, firstName=?, lastName=?, region=?, city=?, alpha2Code=?, picture=? , mail_notification=? WHERE id=?";
	private static final String SQL_INSERT_LOGIN = "INSERT into log (login,user_id) VALUES(NOW(),?)";
	private static final String SQL_UPDATE_LOGOUT = "UPDATE log SET logout=NOW() WHERE id=?";
	private static final String SQL_SELECT_LOG = "SELECT id FROM log WHERE user_id=?";
	private static final String SQL_IS_USER_REGISTERED="SELECT registered FROM user WHERE id=?";
	private static final String SQL_SELECT_ALL_MAIL_USER="SELECT email FROM user WHERE mail_notification=?";
	public static List<String> allMailUsers(){
		Connection connection = null;
		List<String>mails= new ArrayList<String>();
		ResultSet rs = null;
		Object values[] = { "Mail" };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_ALL_MAIL_USER, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
			//	id = rs.getInt("id");
				mails.add(rs.getString("email"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return mails;
	}
	public static boolean isUserRegistered(int id) {
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_IS_USER_REGISTERED, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getBoolean("registered");
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return false;
	}

	public static int selectIdLog(int user_id) {
		int id = 0;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { user_id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_LOG, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id");
				System.out.println(id);
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return id;
	}

	public static boolean updateLog(int id) {
		Connection connection = null;
		Object values[] = { id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_UPDATE_LOGOUT, true, values);
			pstmt.executeUpdate();
			if (pstmt.getUpdateCount() > 0) {
				return true;
			}
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return false;
	}

	public static boolean insertLog(User user) {
		Connection connection = null;

		Object values[] = { user.getId() };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT_LOGIN, true, values);
			pstmt.executeUpdate();
			if (pstmt.getUpdateCount() > 0) {
				return true;
			}
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return false;
	}

	public static boolean updateUserById(User user) {
		Connection connection = null;
		Object values[] = { user.getUsername(), user.getEmail(), user.getFirstName(), user.getLastName(),
				 user.getRegion(), user.getCity(), user.getAlpha2Code(), user.getPicture(),user.getMailNotification(),
				user.getId() };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_UPDATE_USER_BY_ID, true, values);
			pstmt.executeUpdate();
			if (pstmt.getUpdateCount() > 0) {
				return true;
			}
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return false;
	}

	public static User getUserByUsername(String username) {
		User user = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { username };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_GET_USER_BY_USERNAME, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("email"),
						rs.getString("firstName"), rs.getString("lastName"), rs.getString("region"),
						rs.getString("city"), rs.getString("alpha2Code"), rs.getString("picture"));
				user.setPassword(rs.getString("password"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return user;
	}

	public static User getUserById(int id) {
		User user = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_GET_USER_BY_ID, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("email"),
						rs.getString("firstName"), rs.getString("lastName"), rs.getString("region"),
						rs.getString("city"), rs.getString("alpha2Code"), rs.getString("picture"));
				user.setMailNotification(rs.getString("mail_notification"));
				user.setRegistered(rs.getBoolean("registered"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return user;
	}

	public static User selectByUsernameAndPassword(String username, String password) {
		User user = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { username, password };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_BY_USERNAME_AND_PASSWORD, false,
					values);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getBoolean("blocked"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return user;
	}

	public static boolean isUsernameExists(String username) {
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { username };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_BY_USERNAME, false, values);
			rs = pstmt.executeQuery();
			if (!rs.first()) {
				return false;
			}

			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return true;
	}

	public static boolean isEmailExists(String email) {
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { email };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_BY_EMAIL, false, values);
			rs = pstmt.executeQuery();
			if (!rs.first()) {
				return false;
			}

			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return true;
	}

	public static boolean insertUser(User user) {
		Connection connection = null;
		// ResultSet rs = null;
		Object values[] = { user.getUsername(), user.getPassword(), user.getEmail(), user.getFirstName(),
				user.getLastName() };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT_USER, true, values);
			pstmt.executeUpdate();
			if (pstmt.getUpdateCount() > 0) {
				return true;
			}
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return false;
	}

	public static boolean updatePicture(User user) {
		Connection connection = null;
		Object values[] = { user.getPicture(), user.getUsername() };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_UPDATE_PICTURE, true, values);
			pstmt.executeUpdate();
			if (pstmt.getUpdateCount() > 0) {
				return true;
			}
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return false;
	}

	public static boolean changePassword(User user) {
		Connection connection = null;
		Object values[] = { user.getPassword(), user.getUsername() };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_CHANGE_PASSWORD, true, values);
			pstmt.executeUpdate();
			if (pstmt.getUpdateCount() > 0) {
				return true;
			}
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return false;
	}

	public static String checkPassword(User user) {
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { user.getUsername() };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_CHECK_PASSWORD, true, values);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String password = rs.getString("password");
				System.out.println(password);
				return password;

			}
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return null;
	}
}