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
	private static final String SQL_GET_NOTIFICATION="SELECT * FROM ipbaza.notification n \r\n" + 
			"			right join ipbaza.video  \r\n" + 
			"			on n.id=ipbaza.video.Notification_id \r\n" + 
			"            left join ipbaza.user u \r\n" + 
			"			on n.user_id=u.id\r\n" + 
			"			union \r\n" + 
			"SELECT * FROM ipbaza.notification n \r\n" + 
			"			right join  ipbaza.link l \r\n" + 
			"			on n.id=l.Notification_id\r\n" + 
			"            left join ipbaza.user u \r\n" + 
			"			on n.user_id=u.id\r\n" + 
			"			union  \r\n" + 
			"SELECT * FROM ipbaza.notification n \r\n" + 
			"			right join  ipbaza.text t\r\n" + 
			"			on n.id=t.Notification_id\r\n" + 
			"			left join ipbaza.user u \r\n" + 
			"			on n.user_id=u.id\r\n" + 
			"			ORDER BY date DESC";
	private static final String SQL_DELETE_NOTIFICATION="DELETE from ipbaza.notification where id=?";
	public static boolean deleteNotification(int id) {
		Connection connection = null;
		Object values[]= {id};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,SQL_DELETE_NOTIFICATION,true,values );
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
	
	public static List<Notification> getNotification() {
		Notification notification=null;
		List<Notification>notifications= new ArrayList<Notification>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[]= {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,
					SQL_GET_NOTIFICATION, false, values);
			rs=pstmt.executeQuery();
			while (rs.next()){

				notification=new Notification(rs.getInt("id"),rs.getString("link"),rs.getString("date"),rs.getString("avatar"),rs.getString("username"));
				notifications.add(notification);
			}
			pstmt.close();
		}catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return notifications;
	}
}
