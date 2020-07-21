package net.etfbl.project.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import net.etfbl.project.dao.LogDAO;
import net.etfbl.project.dto.Log;
@ManagedBean(name = "logBean")
@SessionScoped
public class LogBean {
	private int pom=0;
	private JSONObject integers;
	public JSONObject getIntegers() {
		return integers;
	}
	public void setIntegers(JSONObject integers) {
		this.integers = integers;
	}
	public LogBean() {
		super();
		integers=this.logsIn24Hours();
		setPom(1);
	}
	public JSONObject logsIn24Hours() {
		try {
			Integer[] integers = new Integer[24];
			Arrays.fill(integers, 0);
			List<Log>logs=LogDAO.allLogs24Hours();
			Date now = new Date();
			System.out.println("usao");
			List<Set<Integer>> hashs = new ArrayList<Set<Integer>>();
			for (int i=0;i<24;i++) {
				Set <Integer> hash= new HashSet<Integer>();
				hashs.add(hash);
			}
			
			for(Log l:logs) {
				
				Date login=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(l.getLoginDate());
			//	long diffLogin = now.getTime() - login.getTime();
				//long hoursLogin= TimeUnit.MILLISECONDS.toHours(diffLogin);
				int hoursLogin=login.getHours();
				if(l.getLogoutDate()!=null) {
					Date logout=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(l.getLogoutDate()); 
					//long diffLogout = now.getTime() - logout.getTime();
					//long hoursLogout= TimeUnit.MILLISECONDS.toHours(diffLogout);
					int hoursLogout = logout.getHours();
					//if(hoursLogout<25) {
						hashs.get((int) hoursLogout).add(l.getUserId());
				//	}
					
					
				}
				//System.out.println("login"+login);
				//System.out.println("login hour"+login.getHours());
				//System.out.println("now hours"+now.getHours());

				//if(hoursLogin<24) {
					hashs.get((int) hoursLogin).add(l.getUserId());
					//if(hoursLogin>0)
					//	hashs.get((int) hoursLogin-1).add(l.getUserId());
				//}
				
				
			}
			for (int i=0;i<24;i++) {
				integers[i]=hashs.get(i).size();
				System.out.println(integers[i]);
			}
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObj=new JSONObject();
			for(Integer i:integers) {
				jsonArray.put(i);
			}
			jsonObj.put("result",jsonArray);
			return jsonObj;
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}
	public JSONObject logs24Hours()  {
		try {
		Integer[] integers = new Integer[24];
		Arrays.fill(integers, 0);
		List<Log>logs=LogDAO.allLogs24Hours();
		Date now = new Date();
		System.out.println("usao");
		List<Set<Integer>> hashs = new ArrayList<Set<Integer>>();
		for (int i=0;i<24;i++) {
			Set <Integer> hash= new HashSet<Integer>();
			hashs.add(hash);
		}
		
		for(Log l:logs) {
			
			Date login=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(l.getLoginDate());
			long diffLogin = now.getTime() - login.getTime();
			long hoursLogin= TimeUnit.MILLISECONDS.toHours(diffLogin);
			if(l.getLogoutDate()!=null) {
				Date logout=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(l.getLogoutDate()); 
				long diffLogout = now.getTime() - logout.getTime();
				long hoursLogout= TimeUnit.MILLISECONDS.toHours(diffLogout);
				if(hoursLogout<25) {
					hashs.get((int) hoursLogout).add(l.getUserId());
				}
				
				
			}
			//System.out.println("login"+login);
			//System.out.println("login hour"+login.getHours());
			//System.out.println("now hours"+now.getHours());

			if(hoursLogin<24) {
				hashs.get((int) hoursLogin).add(l.getUserId());
				if(hoursLogin>0)
					hashs.get((int) hoursLogin-1).add(l.getUserId());
			}
			
			
		}
		for (int i=0;i<24;i++) {
			integers[i]=hashs.get(i).size();
			System.out.println(integers[i]);
		}
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObj=new JSONObject();
		for(Integer i:integers) {
			jsonArray.put(i);
		}
		jsonObj.put("result",jsonArray);
		return jsonObj;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int getPom() {
		return pom;
	}
	public void setPom(int pom) {
		this.pom = pom;
	}
}
