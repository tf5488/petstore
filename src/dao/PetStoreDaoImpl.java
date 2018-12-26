package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.PetStore;
import tool.BaseDao;

public class PetStoreDaoImpl extends BaseDao implements PetStoreDao {

	// 查看所有商户
	@Override
	public List<PetStore> showAll() {
		List<PetStore> list = new ArrayList<PetStore>();
		String sql = "SELECT * FROM petstore";
		try {
			rs = super.selectDate(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				int balance = rs.getInt("balance");
				PetStore petstore = new PetStore(id, name, password, balance);
				list.add(petstore);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(rs, prap, conn);
		}
		return list;
	}

	@Override
	public int upDate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delDate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addDate(String name, String password, int balance) {
		int temp = 0;
		String sql = "INSERT INTO petstore(`name`,`password`,balance) VALUES (?,?,?)";
		temp = super.upDate(sql, name,password,balance);
		return temp;
	}

	// 验证商户
	@Override
	public PetStore findStore(String name, String password) {
		PetStore petstore = null;
		String sql = "SELECT * FROM petstore WHERE `name` = ? AND `password` = ?";
		try {
			rs = super.selectDate(sql, name, password);
			if (rs.next()) {
				int id = rs.getInt("id");
				int balance = rs.getInt("balance");
				petstore = new PetStore(id, name, password, balance);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(rs, prap, conn);
		}
		return petstore;
	}


	/**
	 * 减少账户余额
	 */
	@Override
	public int delePrice(int id, int price) {
		int temp = 0;
		String sql = "UPDATE petstore SET balance = (balance - ?) WHERE id = ?";
		temp = super.upDate(sql, price,id);
		return temp;
	}
	
	/**
	 * 增加账户余额
	 */
	@Override
	public int addPrice(int id, int price) {
		int temp = 0;
		String sql = "UPDATE petstore SET balance = (balance + ?) WHERE id = ?";
		temp = super.upDate(sql, price,id);
		return temp;
	}

}
