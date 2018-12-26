package tool;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class BaseDao {
	private static String driverName;
	private static String url;
	private static String user;
	private static String password;

	protected Connection conn;
	protected ResultSet rs;
	protected PreparedStatement prap;
	
	
	
	
	// 初始化
	static {
		Properties prop = new Properties();
		InputStream is = BaseDao.class.getClassLoader().getResourceAsStream(
				"datebase.properties");
		try {
			prop.load(is);
			driverName = prop.getProperty("driverName").trim();
			url = prop.getProperty("url").trim();
			user = prop.getProperty("user").trim();
			password = prop.getProperty("password").trim();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 连接数据库
	public Connection connection() throws SQLException, ClassNotFoundException{
		Connection conn = null;
		Class.forName(driverName);
		conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
	
	// 断开数据库
	public void closeAll(ResultSet rs,PreparedStatement prap,Connection conn){
		try {
			if(rs != null){
				rs.close();
			}
			if(prap != null){
				prap.close();
			}
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 查
	public ResultSet selectDate(String sql,Object... obj) throws ClassNotFoundException, SQLException{
		ResultSet rs = null;
		conn = connection();
		prap = conn.prepareStatement(sql);
		for(int i = 0;i < obj.length;i ++){
			prap.setObject(i+1, obj[i]);
		}
		rs = prap.executeQuery();
		return rs;
	}
	
	// 增删改
	public int upDate(String sql,Object... obj) {
		int temp = 0;
		try {
			conn = connection();
			prap = conn.prepareStatement(sql);
			for(int i = 0;i < obj.length;i ++){
				prap.setObject(i+1, obj[i]);
			}
			temp = prap.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeAll(rs, prap, conn);
		}
		return temp;		
	}
	
}
