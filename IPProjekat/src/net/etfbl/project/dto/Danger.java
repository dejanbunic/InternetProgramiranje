package net.etfbl.project.dto;

public class Danger {
	private int id;
	private int userId;
	private String name;
	private Double lat;
	private Double lng;
	private String date;
	private String description;

	public Danger() {
		super();
	}

	public Danger(int userId, String name, Double lat, Double lng, String description) {
		super();
		this.userId = userId;
		this.name = name;
		this.lat = lat;
		this.lng = lng;
		this.description = description;
	}

	public Danger(int id, int userId, String name, Double lat, Double lng, String date, String description) {
		super();
		this.userId = userId;
		this.name = name;
		this.lat = lat;
		this.lng = lng;
		this.id = id;
		this.date = date;
		this.description = description;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
