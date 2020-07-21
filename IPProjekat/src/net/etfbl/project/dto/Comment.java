package net.etfbl.project.dto;

public class Comment {
	private int id;
	private int notificationId;
	private String text;
	private String picture;
	private String avatar;

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Comment(String text, String picture, int notificationId) {
		super();
		this.text = text;
		this.picture = picture;
		this.notificationId = notificationId;
	}

	public Comment(int id, String text, String picture, int notificationId, String avatar) {
		super();
		this.id = id;
		this.text = text;
		this.picture = picture;
		this.notificationId = notificationId;
		this.avatar = avatar;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

}
