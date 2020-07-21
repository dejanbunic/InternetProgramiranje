package net.etfbl.project.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONUtils {
	public static String getJSONStringFromFile(String path) {
		Scanner scanner;
		InputStream in = FileHandle.inputStreamFromFile(path);
		scanner = new Scanner(in);
		String json = scanner.useDelimiter("\\Z").next();
		scanner.close();
		try {
			in.close();
			return json;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static JSONObject getJSONObjectFromFile(String path) {
		return new JSONObject(getJSONStringFromFile(path));
	}

	public static boolean objectExists(JSONObject jsonObjects, String key) {
		Object o;
		try {
			o = jsonObjects.get(key);
		} catch (Exception e) {
			return false;
		}
		return o != null;
	}

	public static List<Integer> getCitiesID(String alpha2Code) {
		JSONArray array = new JSONArray(JSONUtils.getJSONStringFromFile("/city.list.json"));

		List<Integer> citiesId = new ArrayList<Integer>();
		for (int i = 0; i < array.length(); i++) {
			JSONObject object = array.getJSONObject(i);
			if (object.getString("country").equals(alpha2Code)) {
				// System.out.println(object.getInt("id"));
				citiesId.add(object.getInt("id"));
			}
		}
		List<Integer> cities = new ArrayList<Integer>();
		for (int i = 0; i < 3; i++) {

			Random rand = new Random();
			// System.out.println(citiesId.size());
			cities.add(citiesId.get(rand.nextInt(citiesId.size())));

		}
		return cities;
	}
}
