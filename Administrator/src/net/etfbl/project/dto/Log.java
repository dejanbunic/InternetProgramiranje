package net.etfbl.project.dto;

import java.util.Date;

public class Log {
	private int id;
	private String loginDate;
	private String logoutDate;
	private int userId;
	public Log(int id, String loginDate, String logoutDate, int userId) {
		super();
		this.id=id;
		this.loginDate=loginDate;
		this.logoutDate=logoutDate;
		this.userId=userId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}
	public String getLogoutDate() {
		return logoutDate;
	}
	public void setLogoutDate(String logoutDate) {
		this.logoutDate = logoutDate;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
