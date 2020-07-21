package net.etfbl.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.etfbl.project.dto.DangerCategory;

public class DangerCategoryDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_All = "SELECT * FROM danger_category";
	private static final String SQL_SELECT_ALL = "SELECT * FROM ipbaza.danger_has_danger_category dhdc\r\n"
			+ "inner join  ipbaza.danger_category dc\r\n" + "on dhdc.danger_category_id=dc.id";

	public static List<DangerCategory> getAllHasCategory() {
		DangerCategory dangerCategory = null;
		List<DangerCategory> dangerCategories = new ArrayList<DangerCategory>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_ALL, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dangerCategory = new DangerCategory(rs.getInt("id"), rs.getInt("danger_id"), rs.getString("name"));
				dangerCategories.add(dangerCategory);
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return dangerCategories;
	}

	public static List<DangerCategory> getAll() {
		List<DangerCategory> dangerCategories = new ArrayList<DangerCategory>();
		DangerCategory dangerCategory = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_All, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dangerCategory = new DangerCategory(rs.getInt("id"), rs.getString("name"));
				dangerCategories.add(dangerCategory);
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return dangerCategories;
	}

}
