package net.etfbl.project.dto;

public class User {
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	//private int userGroup;
	private String picture;
	//private String country;
	private int id;
	private String region;
	private String city;
	private String alpha2Code;
	private boolean blocked;
	private boolean logged;
	private String mail_notification;
	private boolean registered;

	public String getMailNotification() {
		return mail_notification;
	}

	public void setMailNotification(String mail_notification) {
		this.mail_notification = mail_notification;
	}

	public boolean getBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	public User(int id, String username, String password, boolean blocked) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.blocked = blocked;
	}

	public User(int id, String username, String email, String firstName, String lastName, String region, String city,
			String alpha2Code, String picture) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		// this.country=country;
		this.region = region;
		this.city = city;
		this.alpha2Code = alpha2Code;
		this.picture = picture;
		
		
	}
	public User(int id, String username, String email, String firstName, String lastName, String region, String city,
			String alpha2Code, String picture,String mail_notification) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		// this.country=country;
		this.region = region;
		this.city = city;
		this.alpha2Code = alpha2Code;
		this.picture = picture;
		this.mail_notification=mail_notification;
		
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAlpha2Code() {
		return alpha2Code;
	}

	public void setAlpha2Code(String alpha2Code) {
		this.alpha2Code = alpha2Code;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/*public User(String firstName, String lastName, String username, String password, String email, int userGroup) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.userGroup = userGroup;
	}*/

	public User(String username, String password, String email, String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public User(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

 /*	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}*/

	/*(public int getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(int userGroup) {
		this.userGroup = userGroup;
	}*/

	public User() {
		super();
	}

	public User(int id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return username + "  " + password;
	}

	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}
}
