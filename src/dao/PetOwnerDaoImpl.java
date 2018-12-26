package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.PetOwner;
import tool.BaseDao;

public class PetOwnerDaoImpl extends BaseDao implements PetOwnerDao {

	@Override
	public int upDate(PetOwner petowner) {
		
		return 0;
	}

	@Override
	public int delDate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addDate() {
		// TODO Auto-generated method stub
		return 0;
	}

	// 验证用户
	@Override
	public PetOwner findOwner(String name, String password) {
		PetOwner petowner = null;
		String sql = "SELECT * FROM petowner WHERE `name` = ? AND `password` = ?";
		try {
			rs = super.selectDate(sql, name, password);
			if (rs.next()) {
				int id = rs.getInt("id");
				int money = rs.getInt("money");
				petowner = new PetOwner(id, name, password, money);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(rs, prap, conn);
		}
		return petowner;
	}

	// 查询所有信息
	@Override
	public List<PetOwner> showAll() {
		List<PetOwner> list = new ArrayList<PetOwner>();
		String sql = "SELECT * FROM petowner";
		try {
			rs = super.selectDate(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				int money = rs.getInt("money");
				PetOwner petowner = new PetOwner(id, name, password, money);
				list.add(petowner);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(rs, prap, conn);
		}
		return list;
	}

	// 金额的增加
	@Override
	public int addPrice(int id ,int price) {
		int temp = 0;
		String sql = "UPDATE petowner SET money = (money + ?) WHERE id = ?";
		temp = super.upDate(sql, price,id);
		return temp;
	}

	// 减少余额
	@Override
	public int delePrice(int id, int price) {
		int temp = 0;
		String sql = "UPDATE petowner SET money = (money - ?) WHERE id = ?";
		temp = super.upDate(sql, price,id);
		return temp;
	}

}
