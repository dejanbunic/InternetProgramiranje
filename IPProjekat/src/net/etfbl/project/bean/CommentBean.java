package net.etfbl.project.bean;

import java.util.List;

import net.etfbl.project.dao.CommentDAO;
import net.etfbl.project.dto.Comment;

public class CommentBean {
	public void insertComment(Comment comment, int userId, String avatar) {
		CommentDAO.insertComment(comment, userId, avatar);
	}

	public List<Comment> getAllCommentsByNotificationId(int id) {
		return CommentDAO.getAllCommentsByNotificationId(id);
	}
}
