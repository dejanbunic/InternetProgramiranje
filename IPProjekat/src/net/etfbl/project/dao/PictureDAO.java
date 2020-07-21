package net.etfbl.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.etfbl.project.dto.Notification;
import net.etfbl.project.dto.Picture;

public class PictureDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_INSERT_PICTURE = "INSERT INTO picture (Notification_id,link) VALUES(?,?)";
	private static final String SQL_GET_ALL_PICTURE_BY_NOTIFICATION_ID = "SELECT * FROM picture WHERE notification_id=?";

	public static List<Picture> getAllPicturesByNotificationId(int id) {
		Picture picture = null;
		List<Picture> pictures = new ArrayList<Picture>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_GET_ALL_PICTURE_BY_NOTIFICATION_ID,
					false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				picture = new Picture(rs.getInt("id"), rs.getString("link"), rs.getInt("notification_id"));
				pictures.add(picture);
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return pictures;
	}

	public static boolean insertText(Notification notification, String url) {
		Connection connection = null;

		Object values[] = { notification.getId(), url };
		try {

			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT_PICTURE, true, values);
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
