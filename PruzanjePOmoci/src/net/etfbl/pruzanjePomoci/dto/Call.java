package net.etfbl.pruzanjePomoci.dto;

public class Call {
	private String name;
	private String time;
	private String location;
	private String description;
	private String picture;
	private int id;
	private int idCallCategory;

	public Call(String name, String time, String location, String description, String picture, int id,
			int idCallCategory, boolean blocked, boolean fake) {
		super();
		this.name = name;
		this.time = time;
		this.location = location;
		this.description = description;
		this.picture = picture;
		this.id = id;
		this.idCallCategory = idCallCategory;
		this.blocked = blocked;
		this.fake = fake;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCallCategory() {
		return idCallCategory;
	}

	public void setIdCallCategory(int idCallCategory) {
		this.idCallCategory = idCallCategory;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public boolean isFacke() {
		return fake;
	}

	public void setFacke(boolean fake) {
		this.fake = fake;
	}

	private boolean blocked;
	private boolean fake;

	public Call() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

}
