package dao;

import java.util.List;

import entity.Account;

public interface AccountDao {
	List<Account> showAll();
	
	int upDate();

	int delDate();

	int addDate();

	List<Account> findAccountById(int id);

	int insertDate(int petId, int id, String string, int storeId, int price);

}
