import org.json.JSONObject;

import net.etfbl.project.dao.LogDAO;
import net.etfbl.project.utility.JavaMail;

public class Main {
	public static void main (String[] args) {
		//DangerHasCategoryDAO dangerHasCategoryDAO = new DangerHasCategoryDAO();
		//UserBean userBean = new UserBean();
		//int dangerId=1;
		//String categoryId="2";
	//	Danger danger = new Danger(365,"tataratata",43.12,44.22);
	//	dangerHasCategoryDAO.insertDangerHasCategory(dangerId, Integer.parseInt(categoryId));
	//	System.out.println(DangerDAO.insertDanger(danger));
	
		//System.out.println(userBean.getRssFeed50());
		//JSONObject object = new JSONObject();
		//object.put("naziv", "ime");
		//System.out.println(object.get("naziv"));
		
	//	System.out.println(object.has("naziv"));
		//System.out.println(object.get("godine"));
		
//		System.out.println(LogDAO.getUserNumberLogs(375));
		JavaMail.sendMail("dejan_bunic@yahoo.com","naslov","tako to ide");
	}
}
