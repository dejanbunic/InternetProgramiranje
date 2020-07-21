package net.etfbl.pruzanjePomoci.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.etfbl.pruzanjePomoci.dto.CallCategory;

public class CallCategoryDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SELECT_CALL_CATEGORIES = "SELECT * FROM call_category;";
	public static List<CallCategory> getCategorys() {
		List<CallCategory> categorys = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SELECT_CALL_CATEGORIES, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				categorys.add(new CallCategory(rs.getInt("id"),rs.getString("name")));
			}
			pstmt.close();
			return categorys;
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return null;
	}
}
