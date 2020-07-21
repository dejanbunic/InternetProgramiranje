package net.etfbl.project.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.util.Set;

import net.etfbl.project.dto.User;

public class UserDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_All_USERS = "SELECT * FROM user";
	private static final String SQL_SELECT_USER_BY_ID="SELECT * FROM user WHERE id=?";
	private static final String SQL_SET_USER_PASSWORD ="UPDATE user SET password=? WHERE username=?";
	private static final String SQL_BLOCK_USER="UPDATE user SET blocked=? WHERE username=?";
	private static final String SQL_REGISTER_USER="UPDATE user SET registered=? WHERE username=?";
	private static final String SQL_ALL_ONLINE_USERS="SELECT * FROM log WHERE logout IS NULL AND DATE_SUB(NOW(), INTERVAL 1 HOUR ) < login";
	private static final String SQL_ALL_REGISTER_USERS="SELECT *  FROM user WHERE registered=true";
	public static int numberOfRegistredUsers() {
	
		int total=0;
		Connection connection = null;
		ResultSet rs = null;
		Object values[]= {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,
					SQL_ALL_REGISTER_USERS, false, values);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				total++;
		}
			pstmt.close();
			
		}catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return total;
		
	}
	public static  User getUserById(int id) {
		User user = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[]= {id};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,
					SQL_SELECT_USER_BY_ID, false, values);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				//System.out.println(rs.getString("username"));
				user = new User(rs.getInt("id"), rs.getString("username"),rs.getString("password"),rs.getString("email"),rs.getString("firstName"),
						rs.getString("lastName"),rs.getString("region"),rs.getString("city"),rs.getString("alpha2Code"),rs.getString("picture"),rs.getBoolean("blocked"),rs.getBoolean("registered"));
			}
			pstmt.close();
		}catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return user;
	}
	public static int getAllOnlineUsers() {
		Set <Integer> hash_set= new HashSet<Integer>();
		int id=-1;
		Connection connection = null;
		ResultSet rs = null;
		Object values[]= {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,
					SQL_ALL_ONLINE_USERS, false, values);
			rs=pstmt.executeQuery();
			while(rs.next()){
				id = rs.getInt("user_id");
				hash_set.add(id);
				
			}
			pstmt.close();
		}catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return id=hash_set.size();
	}
	public static List<User> getAllUsers(){
		List<User> users = new ArrayList<User>();
		User user = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[]= {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,
					SQL_SELECT_All_USERS, false, values);
			rs=pstmt.executeQuery();
			while(rs.next()){
				//System.out.println(rs.getString("username"));
				user = new User(rs.getInt("id"), rs.getString("username"),rs.getString("password"),rs.getString("email"),rs.getString("firstName"),
						rs.getString("lastName"),rs.getString("region"),rs.getString("city"),rs.getString("alpha2Code"),rs.getString("picture"),rs.getBoolean("blocked"),rs.getBoolean("registered"));
				users.add(user);
			}
			pstmt.close();
		}catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return users;
	}
	public static boolean registerUser(User user) {
		Connection connection = null;
		boolean stanje=false;
		if(!user.getRegistered()) {
			stanje=true;
			}
		Object values[]= {stanje,user.getUsername()};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_REGISTER_USER,true,values );
			pstmt.executeUpdate();
			if(pstmt.getUpdateCount()>0) {
				return true;
			}
		}catch(SQLException exp) {
			exp.printStackTrace();
		}
		finally {
			connectionPool.checkIn(connection);
		}
		return false;
	}
	public static boolean blockUser(User user) {
		Connection connection = null;
		boolean stanje=false;
		if(!user.getBlocked()) {
			stanje=true;
			}
		Object values[]= {stanje,user.getUsername()};
		try {
			System.out.println("stanje"+stanje);
			System.out.println("getBlocked"+user.getBlocked());
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_BLOCK_USER,true,values );
			pstmt.executeUpdate();
			if(pstmt.getUpdateCount()>0) {
				return true;
			}
		}catch(SQLException exp) {
			exp.printStackTrace();
		}
		finally {
			connectionPool.checkIn(connection);
		}
		return false;
	}
	public static boolean changeUserPassword(User user) {
		Connection connection = null;
		Object values[]= {user.getPassword(),user.getUsername()};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,SQL_SET_USER_PASSWORD,true,values );
			pstmt.executeUpdate();
			if(pstmt.getUpdateCount()>0) {
				return true;
			}
		}catch(SQLException exp) {
			exp.printStackTrace();
		}
		finally {
			connectionPool.checkIn(connection);
		}
		return false;
	}
	
}
