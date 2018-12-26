package dao;

import java.util.List;

import entity.PetOwner;

public interface PetOwnerDao {
	List<PetOwner> showAll();

	int upDate(PetOwner petowner);

	int delDate();

	int addDate();

	PetOwner findOwner(String name, String password);

	int addPrice(int id ,int price);

	int delePrice(int id, int price);

}
