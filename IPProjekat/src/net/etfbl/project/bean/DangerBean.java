package net.etfbl.project.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import net.etfbl.project.dao.DangerDAO;
import net.etfbl.project.dao.DangerHasCategoryDAO;
import net.etfbl.project.dto.Danger;
import net.etfbl.project.dto.DangerCategory;

public class DangerBean {
	Danger danger = new Danger();
	List<Danger> dangers = new ArrayList<Danger>();

	public boolean insertDanger(Danger danger, String[] categories) {
		int dangerId = DangerDAO.insertDanger(danger);
		if (dangerId == -1)
			return false;
		for (String c : categories) {
			int pom = Integer.parseInt(c);
			if (!DangerHasCategoryDAO.insertDangerHasCategory(dangerId, pom))
				return false;
		}
		return true;
	}

	public JSONArray getAll() {
		DangerCategoryBean dangerCategoryBean = new DangerCategoryBean();
		List<DangerCategory> dangerCategorise = dangerCategoryBean.getAllHasCategories();
		dangers = DangerDAO.getAll();
		JSONArray jsonArray = new JSONArray();
		for (Danger d : dangers) {
			JSONObject object = new JSONObject();
			object.put("id", d.getId());
			object.put("name", d.getName());
			object.put("lat", d.getLat());
			object.put("lng", d.getLng());

			object.put("description", d.getDescription());

			SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				String reformattedStr = myFormat.format(fromUser.parse(d.getDate()));
				object.put("date", reformattedStr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONArray categories = new JSONArray();
			for (DangerCategory dc : dangerCategorise) {
				if (d.getId() == dc.getDangerId()) {
					JSONObject categoryJson = new JSONObject();
					categoryJson.put("id", dc.getId());
					categoryJson.put("name", dc.getName());
					categories.put(categoryJson);
				}
			}
			object.put("categories", categories);
			jsonArray.put(object);
		}
		return jsonArray;
	}
}