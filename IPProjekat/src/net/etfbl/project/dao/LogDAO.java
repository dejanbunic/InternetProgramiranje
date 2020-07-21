package net.etfbl.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_GET_USER_NUMBER_LOGS = "SELECT COUNT(*) FROM log WHERE user_id=?";

	public static int getUserNumberLogs(int id) {
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_GET_USER_NUMBER_LOGS, false, values);
			rs = pstmt.executeQuery();
			rs.next();
			try {
				return rs.getInt("COUNT(*)");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		return 0;
	}
}
