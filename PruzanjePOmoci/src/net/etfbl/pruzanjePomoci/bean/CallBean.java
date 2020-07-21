package net.etfbl.pruzanjePomoci.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.PrimeFaces;
import net.etfbl.pruzanjePomoci.dao.CallCategoryDAO;
import net.etfbl.pruzanjePomoci.dao.CallDAO;
import net.etfbl.pruzanjePomoci.dto.Call;
import net.etfbl.pruzanjePomoci.dto.CallCategory;

@ManagedBean(name = "callBean" , eager = true)
@SessionScoped
public class CallBean {
	private CallCategory callCategory = new CallCategory();

	public CallCategory getCallCategory() {
		return callCategory;
	}

	public void setCallCategory(CallCategory callCategory) {
		this.callCategory = callCategory;
	}

	private Call call = new Call();
	private List<CallCategory> categories = new ArrayList<CallCategory>();
	private Date date;

	@PostConstruct
	public void init() {
		categories = CallCategoryDAO.getCategorys();
	}

	public List<CallCategory> vrati() {
		return CallCategoryDAO.getCategorys();
	}

	public Call getCall() {
		return call;
	}

	public void setCall(Call call) {
		this.call = call;
	}

	public List<CallCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<CallCategory> categories) {
		this.categories = categories;
	}

	public String addCall() {
		// System.out.println("heheh");
		// System.out.println(this.call.getIdCallCategory());
		// System.out.println(this.call.getTime());
		SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.call.setTime(sdf.format(date));
		if (CallDAO.insertCall(call)) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Poziv je dodan");
			PrimeFaces.current().dialog().showMessageDynamic(message);
			call = new Call();
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Poziv nije dodan");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}

		return "";
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
