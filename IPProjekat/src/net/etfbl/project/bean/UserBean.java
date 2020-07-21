package net.etfbl.project.bean;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import net.etfbl.project.dao.UserDAO;
import net.etfbl.project.dto.Comment;
import net.etfbl.project.dto.Notification;
import net.etfbl.project.dto.Picture;
import net.etfbl.project.dto.User;
import net.etfbl.project.rss.Feed;
import net.etfbl.project.rss.FeedMessage;
import net.etfbl.project.rss.RSSFeedParser;

public class UserBean {
	private User user = new User();
	int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private boolean isLoggedIn = false;

	public boolean insertLog(User user) {
		return UserDAO.insertLog(user);
	}

	public boolean updateLog(int id) {
		return UserDAO.updateLog(id);
	}

	public boolean updateUserById(User user) {
		return UserDAO.updateUserById(user);
	}

	public boolean changePassword(User user, String newPassword, String oldPassword) throws NoSuchAlgorithmException {
		User oldUser = new User();
		oldUser.setPassword(oldPassword);
		User newUser = new User();
		newUser.setPassword(newPassword);
		if (true) {  //user.getPassword().equals(this.bytesToHex(this.sha256(oldUser)))
			user.setPassword(this.bytesToHex(this.sha256(newUser)));
			UserDAO.changePassword(user);
			return true;
		}
		return false;
	}

	public boolean login(User user, HttpSession session) throws NoSuchAlgorithmException {
		user.setPassword(this.bytesToHex(this.sha256(user)));
		if ((user = UserDAO.selectByUsernameAndPassword(user.getUsername(), user.getPassword())) != null) {
			if (user.getBlocked()) {
				return false;
			}
			isLoggedIn = true;
			session.setMaxInactiveInterval(3600);
			return true;
		}
		return false;
	}

	public boolean register(User user) throws NoSuchAlgorithmException {
		user.setPassword(this.bytesToHex(this.sha256(user)));
		if (UserDAO.isUsernameExists(user.getUsername()) || UserDAO.isEmailExists(user.getEmail())) {
			return false;

		}
		if (UserDAO.insertUser(user)) {
			return true;
		}
		return false;
	}

	private byte[] sha256(User user) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] encodedhash = digest.digest(user.getPassword().getBytes(StandardCharsets.UTF_8));
		return encodedhash;
	}

	private String bytesToHex(byte[] hash) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}

	public User getUserByUsername(String username) {
		return UserDAO.getUserByUsername(username);
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void logout() {
		user = new User();
		isLoggedIn = false;
	}

	public User getUser() {
		return user;
	}
	public User getUserById(int id) {
		return UserDAO.getUserById(id);
	}
	public boolean registerUser(User user) {
		if (UserDAO.insertUser(user)) {
			return true;
		}
		return false;
	}
	public boolean isUserRegistered(int id) {
		return UserDAO.isUserRegistered(id);
	}

	public boolean updatePicture(User user) {
		return UserDAO.updatePicture(user);
	}

	public void selectIdLog(int user_id) {
		id = UserDAO.selectIdLog(user_id);
		System.out.println("id je" + id);
	}
	public List<String>getAllMailUsers(){
		return UserDAO.allMailUsers();
	}
	public JSONArray getRssFeed50() {
		RSSFeedParser parser = new RSSFeedParser(
				"https://europa.eu/newsroom/calendar.xml_en?field_nr_events_by_topic_tid=151");
		Feed feed = parser.readFeed();
		JSONArray jsonArray = new JSONArray();
		NotificationBean notificationBean = new NotificationBean();
		CommentBean commentBean = new CommentBean();
		List<Notification> notifications = new ArrayList<Notification>();
		notifications = notificationBean.getNotification();
		int i = 0;
		int j = 0;
		int bagra = 0;
		while (bagra++ < 50) {
			FeedMessage message = feed.getMessages().get(i);
			DateFormat parseFormat = new SimpleDateFormat("E, dd MMM yyyy hh:mm:ss XXX");
			Date dt = null;
			String string = message.getPubDate();
			string = string.substring(0, string.length() - 2) + ":"
					+ string.substring(string.length() - 2, string.length());
			try {
				dt = parseFormat.parse(string);
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			String tempNotification = "1900-01-01 00:00:00";
			Notification n = new Notification();
			if (j < notifications.size()) {
				n = notifications.get(j);
				tempNotification = n.getDate();
			}

			DateFormat parseFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date dt2 = null;
			try {
				dt2 = parseFormat1.parse(tempNotification);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (dt.before(dt2)) {
				JSONObject jsonObj1 = new JSONObject();
				// NotificationBean notificationBean = new NotificationBean();
				List<Picture> pictures = notificationBean.getNotificationPictures(n.getId());
				JSONArray picturesJson = new JSONArray();
				for (Picture p : pictures) {
					JSONObject pictureJson = new JSONObject();
					pictureJson.put("id", p.getId());
					pictureJson.put("link", p.getLink());
					pictureJson.put("idNotification", p.getIdNotification());
					picturesJson.put(pictureJson);
				}
				JSONArray commentsJson = new JSONArray();
				List<Comment> comments = commentBean.getAllCommentsByNotificationId(n.getId());
				for (Comment c : comments) {
					JSONObject commentJson = new JSONObject();
					commentJson.put("avatar", c.getAvatar());
					commentJson.put("text", c.getText());
					commentJson.put("picture", c.getPicture());
					commentsJson.put(commentJson);
				}
				jsonObj1.put("id", n.getId());
				jsonObj1.put("type", "notification");
				jsonObj1.put("title", n.getName());
				jsonObj1.put("link", n.getLink());
				jsonObj1.put("pubDate", n.getDate());
				jsonObj1.put("avatar", n.getAvatar());
				jsonObj1.put("pictures", picturesJson);
				jsonObj1.put("comments", commentsJson);
				j++;
				jsonArray.put(jsonObj1);
			} else {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("type", "rss");
				jsonObj.put("title", message.getTitle());
				jsonObj.put("pubDate", message.getPubDate());
				jsonObj.put("description", message.getDescription());
				jsonObj.put("link", message.getLink());
				jsonArray.put(jsonObj);
				i++;

			}
		}
		return jsonArray;
	}
}
