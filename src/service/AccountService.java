package service;

import java.sql.Date;
import java.util.List;

import dao.AccountDao;
import dao.AccountDaoImpl;
import entity.Account;
import entity.Pet;

public class AccountService {
	AccountDao accountdao = new AccountDaoImpl();
	PetService petservice = new PetService();

	/**
	 * 显示指定商户的账户余额
	 * 
	 * @param id
	 */
	public void showSurplus(int id) {
		List<Account> list = accountdao.findAccountById(id);
		if (list.size() != 0) {
			System.out.println("ID\t宠物ID\t购买者ID\t购买时间\t购买金额");
			for (Account account : list) {
				int price = account.getPrice();
				int petId = account.getPetId();
				int sellerId = account.getPrice();
				Date dealTiem = account.getDealTiem();
				System.out.println(id + "\t" + petId + "\t" + sellerId + "\t"
						+ dealTiem + "\t" + price);
			}
		}
	}

	/**
	 * 显示指定商户的账户信息
	 * 
	 * @param id
	 */
	public void showAll(int id) {
		List<Account> list = accountdao.findAccountById(id);
		if (list.size() != 0) {
			System.out.println("ID\t宠物ID\t购买者ID\t销售类型\t商户ID\t销售金额\t销售时间");
			for (Account account : list) {
				int price = account.getPrice();
				int petId = account.getPetId();
				int sellerId = account.getPrice();
				String dealType = account.getDealType();
				int buyerId = account.getBuyerId();
				Date dealTiem = account.getDealTiem();
				System.out.println(id + "\t" + petId + "\t" + sellerId + "\t"
						+ dealType + "\t" + buyerId + "\t" + price + "\t"
						+ dealTiem);
			}
		}
	}

	/**
	 * 增加一条用户买宠物的台账
	 * 
	 * @param petId
	 * @param id
	 * @param storeId
	 * @param price
	 */
	public void insertAccount(int petId, int id, int storeId, int price) {
		Pet pet = petservice.petInfoByPetId(petId);
		if (id == 0) {
			id = pet.getOwnerId();
			int temp = accountdao.insertDate(petId, id, "买", storeId, price);
			if (temp > 0) {
				System.out.println("台账记录成功!");
			}
		} else if (storeId == 0) {
			storeId = pet.getStoreId();
			int temp = accountdao.insertDate(petId, id, "卖", storeId, price);
			if (temp > 0) {
				System.out.println("台账记录成功!");
			}
		} else {
			int temp = accountdao.insertDate(petId, id, "买", storeId, price);
			if (temp > 0) {
				System.out.println("台账记录成功!");
			}
		}
	}
}
