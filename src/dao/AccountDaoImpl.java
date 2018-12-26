package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entity.Account;
import tool.BaseDao;

public class AccountDaoImpl extends BaseDao implements AccountDao {

	@Override
	public List<Account> showAll() {
		// TODO Auto-generated method stub
		return null;
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
	public int addDate() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 根据ID查找商户
	 */
	@Override
	public List<Account> findAccountById(int id) {
		List<Account> list = new ArrayList<Account>();
		String sql = "SELECT * FROM account WHERE buyerId = ?";
		list = showInfo(sql, id);
		return list;
	}

	/**
	 * 显示商户信息
	 */
	public List<Account> showInfo(String sql, Object... obj) {
		List<Account> list = new ArrayList<Account>();
		try {
			rs = super.selectDate(sql, obj);
			if (rs.next()) {
				int id = rs.getInt("id");
				int petId = rs.getInt("petId");
				int sellerId = rs.getInt("sellerId");
				String dealType = rs.getString("dealType");
				int buyerId = rs.getInt("buyerId");
				int price = rs.getInt("price");
				Date dealTiem = rs.getDate("dealTiem");
				Account account = new Account(id, petId, sellerId, dealType,
						buyerId, price, dealTiem);
				list.add(account);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(rs, prap, conn);
		}
		return list;
	}

	/**
	 * 增加一条台账
	 */
	@Override
	public int insertDate(int petId, int id, String string, int storeId,
			int price) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String sql = "INSERT INTO account(perId,sellerId,dealType,buyerId,price,dealTime) VALUES (?,?,?,?,?,?)";
		int temp = super.upDate(sql, petId, id, string, storeId, price,
				timestamp);
		return temp;
	}

}
