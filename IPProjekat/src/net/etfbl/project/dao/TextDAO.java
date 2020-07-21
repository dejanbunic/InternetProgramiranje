package net.etfbl.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import net.etfbl.project.dto.Notification;

public class TextDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_INSERT_TEXT = "INSERT INTO text (Notification_id,link) VALUES(?,?)";

	public static boolean insertText(Notification notification, String url) {
		Connection connection = null;

		Object values[] = { notification.getId(), url };
		try {

			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT_TEXT, true, values);
			pstmt.executeUpdate();
			if (pstmt.getUpdateCount() > 0) {
				System.out.println("broji");
				return true;
			}
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		System.out.println("exception");
		return false;
	}
}
