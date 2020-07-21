package net.etfbl.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.etfbl.project.dto.Log;

public class LogDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_ALL_LOGS_24_HOUR="SELECT * FROM log WHERE DATE_SUB(NOW(), INTERVAL 25 HOUR ) < login";
	public static List<Log> allLogs24Hours() {
		List <Log> logs= new ArrayList<Log>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[]= {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,
					SQL_ALL_LOGS_24_HOUR, false, values);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Log log = new Log(rs.getInt("id"), rs.getString("login"), rs.getString("logout"),rs.getInt("user_id"));
				//id = rs.getInt("id");
				//hash_set.add(id);
				logs.add(log);
			}
			pstmt.close();
		}catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return logs;
	}
}
