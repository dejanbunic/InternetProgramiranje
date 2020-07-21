package net.etfbl.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.etfbl.project.dto.Comment;

public class CommentDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_INSERT_COMMENT = "INSERT INTO comment (text,picture,notification_id,user_id,avatar) VALUES(?,?,?,?,?)";
	private static final String SQL_GET_ALL_COMMENTS_BY_NOTIFICATION_ID = "SELECT * FROM comment WHERE notification_id=?";

	public static boolean insertComment(Comment comment, int userId, String avatar) {
		Connection connection = null;

		Object values[] = { comment.getText(), comment.getPicture(), comment.getNotificationId(), userId, avatar };
		try {

			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT_COMMENT, true, values);
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

	public static List<Comment> getAllCommentsByNotificationId(int id) {
		Comment comment = null;
		List<Comment> comments = new ArrayList<Comment>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_GET_ALL_COMMENTS_BY_NOTIFICATION_ID,
					false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				comment = new Comment(rs.getInt("id"), rs.getString("text"), rs.getString("picture"),
						rs.getInt("notification_id"), rs.getString("avatar"));
				comments.add(comment);
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return comments;
	}
}
