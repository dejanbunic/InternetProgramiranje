package net.etfbl.project.dto;

public class Log {
	private int id;
	private String login;
	private String logout;
	private int userId;

	public Log(int id, String login, String logout, int userId) {
		super();
		this.id = id;
		this.login = login;
		this.logout = logout;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogout() {
		return logout;
	}

	public void setLogout(String logout) {
		this.logout = logout;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
