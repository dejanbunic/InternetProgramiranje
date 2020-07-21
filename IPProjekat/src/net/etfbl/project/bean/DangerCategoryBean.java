package net.etfbl.project.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import net.etfbl.project.dao.DangerCategoryDAO;
import net.etfbl.project.dto.DangerCategory;

public class DangerCategoryBean {
	List<DangerCategory> dangerCategories = new ArrayList<DangerCategory>();
	List<DangerCategory> dangerHasCategories = new ArrayList<DangerCategory>();

	public JSONArray getAll() {
		dangerCategories = DangerCategoryDAO.getAll();
		JSONArray jsonArray = new JSONArray();

		for (DangerCategory d : dangerCategories) {
			JSONObject object = new JSONObject();
			object.put("id", d.getId());
			object.put("name", d.getName());
			jsonArray.put(object);
		}

		return jsonArray;
	}

	public List<DangerCategory> getAllHasCategories() {
		return dangerHasCategories = DangerCategoryDAO.getAllHasCategory();
	}
}
