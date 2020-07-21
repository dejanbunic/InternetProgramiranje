package net.etfbl.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.etfbl.project.dto.Notification;

public class VideoDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_INSERT_VIDEO = "INSERT INTO video (Notification_id,link) VALUES(?,?)";

	public static boolean insertVideo(Notification notification, String url) {
		Connection connection = null;

		Object values[] = { notification.getId(), url };
		try {

			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT_VIDEO, true, values);
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
