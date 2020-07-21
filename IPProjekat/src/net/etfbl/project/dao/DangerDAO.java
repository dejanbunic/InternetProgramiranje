package net.etfbl.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import net.etfbl.project.dto.Danger;

public class DangerDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_INSERT_DANGER = "INSERT INTO danger (user_id,name,lat,lng,date,description) VALUES(?,?,?,?,now(),?)";
	private static final String SQL_SELECT_ALL = "SELECT * FROM danger ORDER BY id desc";

	public static int insertDanger(Danger danger) {
		Connection connection = null;
		ResultSet rs = null;
		int ret = -1;
		Object values[] = { danger.getUserId(), danger.getName(), danger.getLat(), danger.getLng(),
				danger.getDescription() };
		try {
			System.out.println("description je ovoliki" + danger.getDescription());
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT_DANGER, true, values);
			pstmt.executeUpdate();
			// pstmt = connection.prepareStatement(SQL_INSERT_DANGER,
			// Statement.RETURN_GENERATED_KEYS);

			if (pstmt.getUpdateCount() == 0) {
				return ret;
			}
			rs = pstmt.getGeneratedKeys();
			rs.next();
			ret = rs.getInt(1);
			// System.out.println("id od danger je "+ret);
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return ret;
	}

	public static List<Danger> getAll() {
		Danger danger = null;
		List<Danger> dangers = new ArrayList<Danger>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_ALL, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// user = new User(rs.getInt("id"),
				// rs.getString("username"),rs.getString("email"),rs.getString("firstName"),
				// rs.getString("lastName"),rs.getString("region"),rs.getString("city"),rs.getString("alpha2Code"),rs.getString("picture"));
				danger = new Danger(rs.getInt("id"), rs.getInt("user_id"), rs.getString("name"), rs.getDouble("lat"),
						rs.getDouble("lng"), rs.getString("date"), rs.getString("description"));
				dangers.add(danger);
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return dangers;
	}
}
