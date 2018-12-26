package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Pet;
import tool.BaseDao;

public class PetDaoImpl extends BaseDao implements PetDao {

	// 查找所有的宠物信息
	@Override
	public List<Pet> showAll() {
		List<Pet> list = new ArrayList<Pet>();
		String sql = "SELECT * FROM pet";
		list = selectPet(sql);
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

	/**
	 * 新增一个宠物
	 */
	@Override
	public int addDate(String name, String typeName) {
		int temp = 0;
		String sql = "INSERT INTO pet(`name`,typeName) VALUES (?,?)";
		temp = super.upDate(sql, name, typeName);
		return temp;
	}

	/**
	 * 通过ownerID进行筛选宠物
	 */
	@Override
	public List<Pet> findById(int ownerId) {
		List<Pet> list = new ArrayList<Pet>();
		String sql = "SELECT * FROM pet WHERE ownerId = ?";
		list = selectPet(sql, ownerId);
		return list;
	}

	// 清除宠物所有者信息
	@Override
	public int clearUser(int petId, int storeId, int ownerId) {
		int temp = 0;
		String sql = "UPDATE pet SET storeId = ?,ownerId = ? WHERE id = ?";
		temp = super.upDate(sql, storeId, ownerId, petId);
		return temp;
	}

	// 查询宠物价值
	@Override
	public Pet petById(int petId) {
		Pet pet = null;
		String sql = "SELECT * FROM pet WHERE id = ?";
		try {
			rs = super.selectDate(sql, petId);
			while (rs.next()) {
				int id = rs.getInt("id");
				int ownerId = rs.getInt("ownerId");
				int storeId = rs.getInt("storeId");
				String name = rs.getString("name");
				String typeName = rs.getString("typeName");
				int health = rs.getInt("health");
				int love = rs.getInt("love");
				Date birthday = rs.getDate("birthday");
				int price = rs.getInt("price");
				pet = new Pet(id, ownerId, storeId, name, typeName, health,
						love, birthday, price);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(rs, prap, conn);
		}
		return pet;
	}

	/**
	 * 查找没有主人的宠物
	 */
	@Override
	public List<Pet> findSellPet() {
		List<Pet> list = new ArrayList<Pet>();
		String sql = "SELECT * FROM pet WHERE ownerId IS NULL ";
		try {
			rs = super.selectDate(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				int ownerId = rs.getInt("ownerId");
				int storeId = rs.getInt("storeId");
				String name = rs.getString("name");
				String typeName = rs.getString("typeName");
				int health = rs.getInt("health");
				int love = rs.getInt("love");
				Date birthday = rs.getDate("birthday");
				int price = rs.getInt("price");
				Pet pet = new Pet(id, ownerId, storeId, name, typeName, health,
						love, birthday, price);
				list.add(pet);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(rs, prap, conn);
		}
		return list;
	}

	// 增加宠物主人信息
	@Override
	public int buyPet(int id, int petId) {
		int temp = 0;
		String sql = "UPDATE pet SET ownerId = ? WHERE id = ?";
		temp = super.upDate(sql, id, petId);
		return temp;
	}

	/**
	 * 增加宠物的商户信息
	 */
	@Override
	public int storeBuyPet(int id, int petId) {
		int temp = 0;
		String sql = "UPDATE pet SET storeId = ? WHERE id = ?";
		temp = super.upDate(sql, id, petId);
		return temp;
	}

	/**
	 * 通过商户ID进行宠物筛选
	 */
	@Override
	public List<Pet> findByStore(int storeId) {
		List<Pet> list = new ArrayList<Pet>();
		String sql = "SELECT * FROM pet WHERE storeId = ?";
		list = selectPet(sql, storeId);
		return list;
	}

	/**
	 * 通过ID进行筛选的宠物信息
	 * 
	 * @param sql
	 * @param id
	 * @return
	 */
	public List<Pet> selectPet(String sql, Object... obj) {
		List<Pet> list = new ArrayList<Pet>();
		try {
			rs = super.selectDate(sql, obj);
			while (rs.next()) {
				int id2 = rs.getInt("id");
				int ownerId = rs.getInt("ownerId");
				int storeId = rs.getInt("storeId");
				String name = rs.getString("name");
				String typeName = rs.getString("typeName");
				int health = rs.getInt("health");
				int love = rs.getInt("love");
				Date birthday = rs.getDate("birthday");
				int price = rs.getInt("price");
				Pet pet = new Pet(id2, ownerId, storeId, name, typeName,
						health, love, birthday, price);
				list.add(pet);
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
	 * 增加健康值
	 */
	@Override
	public int addHealth(int petId, int i) {
		int temp = 0;
		String sql = "UPDATE pet SET health = (health + ?) WHERE id = ?";
		temp = super.upDate(sql, i, petId);
		return temp;
	}

	/**
	 * 增加爱心值
	 */
	@Override
	public int addLove(int petId, int i) {
		int temp = 0;
		String sql = "UPDATE pet SET love = (love + ?) WHERE id = ?";
		temp = super.upDate(sql, i, petId);
		return temp;
	}

}
