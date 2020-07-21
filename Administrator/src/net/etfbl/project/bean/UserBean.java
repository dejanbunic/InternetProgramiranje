package net.etfbl.project.bean;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import net.etfbl.project.dao.UserDAO;
import net.etfbl.project.dto.User;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4022533724751782412L;
	private List<User> users;
	private User user;
	private String message="";
	public void select(User user) {
		
		System.out.println(user.toString());
	
	}
	public String getMessage() {
		return message;
	}
	public void setPom(String message) {
		this.message = message;
	}
	public UserBean() {
		super();
		users=UserDAO.getAllUsers();
	}
	public List<User> getUsers() {
		users=UserDAO.getAllUsers();
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public String blockUser(User user) {
		if(UserDAO.blockUser(user)) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Notification deleted");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
		else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "User not blocked");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
		 return "login?faces-redirect=true";
	}
	public String registerUser(User user) {
		if(UserDAO.registerUser(user)) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Notification deleted");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
		else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "User not blocked");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
		 return "login?faces-redirect=true";
	}
	public String changeUserPassword(User user) throws NoSuchAlgorithmException {
		user.setPassword(UserBean.randomString());
		user.setPassword(UserBean.bytesToHex(UserBean.sha256(user)));
		if( UserDAO.changeUserPassword(user)) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Lozinka", "marmun");
	        
	        PrimeFaces.current().dialog().showMessageDynamic(message);
		}
		else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "User password not changed");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
		return message;
	}
	public static String randomString() {
		byte[] array = new byte[20]; // string length 20
	    new Random().nextBytes(array);
	    String generatedString = new String(array, Charset.forName("UTF-8"));
	    return generatedString;
	}
	public static byte[] sha256(User user) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] encodedhash = digest.digest(
		  user.getPassword().getBytes(StandardCharsets.UTF_8));
		return encodedhash;
	}
	public static String bytesToHex(byte[] hash) {
	    StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < hash.length; i++) {
	    String hex = Integer.toHexString(0xff & hash[i]);
	    if(hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
	public int getAllOnlineUsers() {
		return UserDAO.getAllOnlineUsers();
	}
	public int numberOfRegistredUsers() {
		return UserDAO.numberOfRegistredUsers();
	}
}
