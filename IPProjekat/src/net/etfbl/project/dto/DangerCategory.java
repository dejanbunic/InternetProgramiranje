package net.etfbl.project.dto;

public class DangerCategory {
	private int id;
	private String name;
	private int dangerId;

	public DangerCategory(int id, String name) {
		super();
		this.setId(id);
		this.setName(name);
	}

	public DangerCategory(int id, int dangerId, String name) {
		super();
		this.setId(id);
		this.setName(name);
		this.setDangerId(dangerId);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDangerId() {
		return dangerId;
	}

	public void setDangerId(int dangerId) {
		this.dangerId = dangerId;
	}
}
