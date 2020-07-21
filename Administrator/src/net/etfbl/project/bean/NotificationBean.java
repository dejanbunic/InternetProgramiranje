package net.etfbl.project.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.PrimeFaces;

import net.etfbl.project.dao.NotificationDAO;
import net.etfbl.project.dao.UserDAO;
import net.etfbl.project.dto.Notification;
import net.etfbl.project.dto.User;

@ManagedBean(name = "notificationBean")
@SessionScoped
public class NotificationBean {
	private Notification notification;
	private List<String> userNames = new ArrayList<String>();
	private List<Notification> notifications = new ArrayList<Notification>();
	public NotificationBean() {
		super();
		notifications=NotificationDAO.getNotification();
	}
	public List<Notification> getAll(){
		notifications=NotificationDAO.getNotification();
		return notifications;
	}
	public String deleteNotification(Notification notification) {
		if(NotificationDAO.deleteNotification(notification.getId())) {
			notifications=NotificationDAO.getNotification();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Notification deleted");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
			
		else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Notification not deleted");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
		 return "slavisa";
	} 
	public Notification getNotification() {
		return notification;
	}
	public void setNotification(Notification notification) {
		this.notification = notification;
	}
	public List<Notification> getNotifications() {
		return notifications;
	}
	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}
}
