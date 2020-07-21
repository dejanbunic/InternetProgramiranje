package net.etfbl.pruzanjePomoci.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



//"UPDATE user SET picture=? WHERE username=?";

import net.etfbl.pruzanjePomoci.dto.Call;

public class CallDAO {
	//DELETE FROM products WHERE product_id=1;
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_INSERT_CALL="INSERT INTO ipbaza.call (name,time,location,description,picture,call_category_id,creationTime) VALUES(?,?,?,?,?,?,NOW())";
	private static final String SQL_SELECT_CALL="SELECT * FROM ipbaza.call";
	private static final String SQL_FACKE_CALL="UPDATE ipbaza.call SET fake=? WHERE id=?";
	private static final String SQL_BLOCK_CALL="UPDATE ipbaza.call SET block=? WHERE id=?";
	private static final String SQL_DELETE_CALL="DELETE FROM ipbaza.call WHERE id=?";
	public static boolean deleteCall(int id) {
		Connection connection = null;
		Object values[]= {id};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,SQL_DELETE_CALL,true,values );
			pstmt.executeUpdate();
			if(pstmt.getUpdateCount()>0) {
				return true;
			}
		}catch(SQLException exp) {
			exp.printStackTrace();
		}
		finally {
			connectionPool.checkIn(connection);
		}
		return false;
	}
	public static List<Call> getCalls(){
		Call call=null;
		List<Call>calls= new ArrayList<Call>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[]= {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,
					SQL_SELECT_CALL, false, values);
			rs=pstmt.executeQuery();
			//String name, String time, String location, String description, String picture, int id, int idCallCategory
			while (rs.next()){
				call=new Call(rs.getString("name"),rs.getString("time"),rs.getString("location"),rs.getString("description"),
						rs.getString("picture"),rs.getInt("id"),rs.getInt("call_category_id"),rs.getBoolean("block"),rs.getBoolean("fake"));
				
				calls.add(call);
			}
			pstmt.close();
		}catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return calls;
	}
	public static boolean insertCall(Call call) {
		Connection connection = null;
		//	ResultSet rs = null;
			Object values[]= {call.getName(),call.getTime(),call.getLocation(),call.getDescription(),call.getPicture(),call.getIdCallCategory()};
			try {
				connection = connectionPool.checkOut();
				PreparedStatement pstmt = DAOUtil.prepareStatement(connection,SQL_INSERT_CALL,true,values );
				pstmt.executeUpdate();
				if(pstmt.getUpdateCount()>0) {
					return true;
				}
			}catch(SQLException exp) {
				exp.printStackTrace();
			}
			finally {
				connectionPool.checkIn(connection);
			}
			return false;
	}
	public static boolean fakeCall(int id) {
		Connection connection = null;
		Object values[]= {true,id};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,SQL_FACKE_CALL,true,values );
			pstmt.executeUpdate();
			if(pstmt.getUpdateCount()>0) {
				return true;
			}
		}catch(SQLException exp) {
			exp.printStackTrace();
		}
		finally {
			connectionPool.checkIn(connection);
		}
		return false;
	}
	public static boolean blockCall(int id) {
		Connection connection = null;
		Object values[]= {true,id};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,SQL_BLOCK_CALL,true,values );
			pstmt.executeUpdate();
			if(pstmt.getUpdateCount()>0) {
				return true;
			}
		}catch(SQLException exp) {
			exp.printStackTrace();
		}
		finally {
			connectionPool.checkIn(connection);
		}
		return false;
	}
}
