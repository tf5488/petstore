package dao;

import java.util.List;

import entity.PetType;

public interface PetTypeDao {

	List<PetType> showType();

	String findById(int typeId);

}
