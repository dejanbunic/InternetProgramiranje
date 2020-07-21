package net.etfbl.project.bean;

import java.io.Serializable;
import java.util.List;

import net.etfbl.project.dao.LinkDAO;
import net.etfbl.project.dao.NotificationDAO;
import net.etfbl.project.dao.PictureDAO;
import net.etfbl.project.dao.TextDAO;
import net.etfbl.project.dao.VideoDAO;
import net.etfbl.project.dto.Notification;
import net.etfbl.project.dto.Picture;
import net.etfbl.project.dto.User;

public class NotificationBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2477366570492642390L;
	Notification notification = new Notification();

	public boolean addLink(User user, String url,String name) {
		if (NotificationDAO.insertNotoification(notification, user.getId(), user.getPicture(),name)) {
			if (LinkDAO.insertLink(notification, url)) {
				return true;
			}
			return false;
		}
		return false;
	}

	public void addText(User user, List<String> urls, String textContent, String name) {
		if (NotificationDAO.insertNotoification(notification, user.getId(), user.getPicture(),name)) {
			for (String url : urls)
				PictureDAO.insertText(notification, url);
			TextDAO.insertText(notification, textContent);

		}

	}

	public boolean addVideo(User user, String url,String name) {
		if (NotificationDAO.insertNotoification(notification, user.getId(), user.getPicture(),name)) {
			if (VideoDAO.insertVideo(notification, url)) {
				return true;
			}
			return false;
		}
		return false;
	}

	public List<Notification> getNotification() {
		return NotificationDAO.getNotification();
	}

	public List<Picture> getNotificationPictures(int id) {
		return PictureDAO.getAllPicturesByNotificationId(id);
	}
}
