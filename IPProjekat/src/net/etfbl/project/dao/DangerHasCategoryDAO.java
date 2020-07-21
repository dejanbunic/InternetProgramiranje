package net.etfbl.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

public class DangerHasCategoryDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_INSERT_DANGER_HAS_CATEGORY = "INSERT INTO danger_has_danger_category (danger_id,danger_category_id) VALUES(?,?)";

	public static boolean insertDangerHasCategory(int dangerId, int categoryId) {
		Connection connection = null;
		// ResultSet rs = null;
		Object values[] = { dangerId, categoryId };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT_DANGER_HAS_CATEGORY, true,
					values);
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
}
