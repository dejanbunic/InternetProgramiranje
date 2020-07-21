package net.etfbl.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import net.etfbl.project.dto.Notification;

public class LinkDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_INSERT_LINK = "INSERT INTO link (Notification_id,link) VALUES(?,?)";

	public static boolean insertLink(Notification notification, String url) {
		Connection connection = null;

		Object values[] = { notification.getId(), url };
		try {

			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT_LINK, true, values);
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
