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
	 * ��ʾָ���̻����˻����
	 * 
	 * @param id
	 */
	public void showSurplus(int id) {
		List<Account> list = accountdao.findAccountById(id);
		if (list.size() != 0) {
			System.out.println("ID\t����ID\t������ID\t����ʱ��\t������");
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
	 * ��ʾָ���̻����˻���Ϣ
	 * 
	 * @param id
	 */
	public void showAll(int id) {
		List<Account> list = accountdao.findAccountById(id);
		if (list.size() != 0) {
			System.out.println("ID\t����ID\t������ID\t��������\t�̻�ID\t���۽��\t����ʱ��");
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
	 * ����һ���û�������̨��
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
			int temp = accountdao.insertDate(petId, id, "��", storeId, price);
			if (temp > 0) {
				System.out.println("̨�˼�¼�ɹ�!");
			}
		} else if (storeId == 0) {
			storeId = pet.getStoreId();
			int temp = accountdao.insertDate(petId, id, "��", storeId, price);
			if (temp > 0) {
				System.out.println("̨�˼�¼�ɹ�!");
			}
		} else {
			int temp = accountdao.insertDate(petId, id, "��", storeId, price);
			if (temp > 0) {
				System.out.println("̨�˼�¼�ɹ�!");
			}
		}
	}
}
