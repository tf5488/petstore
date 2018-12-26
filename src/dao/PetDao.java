package dao;

import java.util.List;

import entity.Pet;

public interface PetDao {

	List<Pet> showAll();

	int upDate();

	int delDate();

	int addDate(String name, String typeName);

	List<Pet> findById(int id);

	int clearUser(int petId, int storeId, int ownerId);

	Pet petById(int petId);

	List<Pet> findSellPet();

	int buyPet(int id, int petId);

	int storeBuyPet(int id, int petId);

	List<Pet> findByStore(int storeId);

	int addHealth(int petId, int i);

	int addLove(int petId, int i);
}
