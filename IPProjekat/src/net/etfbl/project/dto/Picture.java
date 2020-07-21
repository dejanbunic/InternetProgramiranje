package net.etfbl.project.dto;

public class Picture {
	private int id;
	private int idNotification;
	private String link;

	public Picture(int id, String link, int idNotification) {
		super();
		this.id = id;
		this.link = link;
		this.idNotification = idNotification;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
