package net.etfbl.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.etfbl.project.dto.Notification;

public class NotificationDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_INSERT_NOTIFICATION = "INSERT INTO notification (date,user_id,avatar,name) VALUES(NOW(),?,?,?)";
	private static final String SQL_GET_NOTIFICATION = "SELECT * FROM ipbaza.notification n\r\n"
			+ "right join ipbaza.video \r\n" + "on n.id=ipbaza.video.Notification_id\r\n" + "union \r\n"
			+ "SELECT * FROM ipbaza.notification n\r\n" + "right join  ipbaza.link l\r\n"
			+ "on n.id=l.Notification_id\r\n" + "union \r\n" + "\r\n" + "SELECT * FROM ipbaza.notification n\r\n"
			+ "right join  ipbaza.text t\r\n" + "on n.id=t.Notification_id\r\n" + "ORDER BY date DESC\r\n" + "limit 30";

	public static boolean insertNotoification(Notification notification, int id, String avatar, String name) {
		Connection connection = null;
		ResultSet rs = null;
		boolean result = false;
		Object values[] = { id, avatar, name };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT_NOTIFICATION, true, values);
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if (pstmt.getUpdateCount() > 0) {
				result = true;
			}
			if (rs.next()) {
				notification.setId(rs.getInt(1));
				System.out.println("auto inkrement je " + rs.getInt(1));
			}
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}

	public static List<Notification> getNotification() {
		Notification notification = null;
		List<Notification> notifications = new ArrayList<Notification>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_GET_NOTIFICATION, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// user = new User(rs.getInt("id"),
				// rs.getString("username"),rs.getString("email"),rs.getString("firstName"),
				// rs.getString("lastName"),rs.getString("region"),rs.getString("city"),rs.getString("alpha2Code"),rs.getString("picture"));
				notification = new Notification(rs.getInt("id"), rs.getString("link"), rs.getString("date"),
						rs.getString("avatar"));
				notification.setName(rs.getString("name"));
				notifications.add(notification);
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return notifications;
	}
}
