package net.etfbl.project.dto;

public class Video {
	private int id;
	private int idNotification;
	private String url;

	public Video() {
		super();
	}

	public Video(int id, int idNotification, String url) {
		super();
		this.id = id;
		this.idNotification = idNotification;
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdNotification() {
		return idNotification;
	}

	public void setIdNotification(int idNotification) {
		this.idNotification = idNotification;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
