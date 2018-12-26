package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.PetType;
import tool.BaseDao;

public class PetTypeDaoImpl extends BaseDao implements PetTypeDao {

	/**
	 * 显示宠物类型信息
	 */
	@Override
	public List<PetType> showType() {
		List<PetType> list = new ArrayList<PetType>();
		String sql = "SELECT * FROM pettype";
		try {
			rs = super.selectDate(sql);
			if (rs.next()) {
				int id = rs.getInt("id");
				String typeName = rs.getString("typeName");
				int price = rs.getInt("price");
				PetType pettype = new PetType(id, typeName,price);
				list.add(pettype);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeAll(rs, prap, conn);
		}
		return list;
	}

	/**
	 * 通过ID查找类型
	 */
	@Override
	public String findById(int typeId) {
		String typeName = null;
		String sql = "SELECT * FROM pettype WHERE id = ?";
		try {
			rs = super.selectDate(sql, typeId);
			if(rs.next()){
				typeName = rs.getString("typeName");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(rs, prap, conn);
		}
		return typeName;
	}

}
