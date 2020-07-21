package net.etfbl.project.bean;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import net.etfbl.project.dao.AdminDAO;
import net.etfbl.project.dto.Admin;



@ManagedBean(name = "adminBean")
@SessionScoped
public class AdminBean implements Serializable{
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Admin admin = new Admin();
	private boolean isLoggedIn=false;
	public String login() throws NoSuchAlgorithmException{
		
		admin.setPassword(this.bytesToHex(this.sha256(admin)));
		if ((admin = AdminDAO.selectByUsernameAndPassword(admin.getUsername(),admin.getPassword()))!=null){
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("admin", admin);
			isLoggedIn = true;
			System.out.println("usao");
			//session.setMaxInactiveInterval(3600);
			return "home?faces-redirect=true";
		}
		admin = new Admin();
		isLoggedIn = false;
		return "login?faces-redirect=true";
	}
	private byte[] sha256(Admin admin) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] encodedhash = digest.digest(
		 admin.getPassword().getBytes(StandardCharsets.UTF_8));
		return encodedhash;
	}
	private  String bytesToHex(byte[] hash) {
	    StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < hash.length; i++) {
	    String hex = Integer.toHexString(0xff & hash[i]);
	    if(hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
}
