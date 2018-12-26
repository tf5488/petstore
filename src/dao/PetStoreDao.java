package dao;

import java.util.List;

import entity.PetStore;

public interface PetStoreDao {
	List<PetStore> showAll();

	int upDate();

	int delDate();

	int addDate(String name, String password, int balance);

	PetStore findStore(String name, String password);

	int delePrice(int id, int price);
	
	int addPrice(int id, int price);
}
