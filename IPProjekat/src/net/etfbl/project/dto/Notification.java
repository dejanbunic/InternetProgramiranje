package net.etfbl.project.dto;

public class Notification {
	private int id;
	private int userId;
	private String date;
	private String link;
	private String avatar;
	private String name;

	public Notification() {
		super();
	}

	public Notification(int id, int userId, String date) {
		super();
		this.id = id;
		this.userId = userId;
		this.date = date;
	}

	public Notification(int id, String link, String date, String avatar) {
		super();
		this.link = link;
		this.date = date;
		this.avatar = avatar;
		this.id = id;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Notification(String link, String date) {
		super();
		this.link = link;
		this.date = date;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
