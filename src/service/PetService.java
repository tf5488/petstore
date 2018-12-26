package service;

import java.sql.Date;
import java.util.List;

import dao.PetDao;
import dao.PetDaoImpl;
import entity.Pet;

public class PetService {
	PetDao petdao = new PetDaoImpl();
	PetTypeService pettypeservice = new PetTypeService();

	// ��ʼ��
	public void showAll() {
		System.out.println("Wonderland ����,���ж����MySQL������!");
		List<Pet> list = petdao.showAll();
		System.out.println("*************************************");
		System.out.println("���\t��������");
		for (Pet pet : list) {
			int id = pet.getId();
			String name = pet.getName();
			System.out.println(id + "\t" + name);
		}
		System.out.println("*************************************");
	}

	/**
	 * ��������ID�ҳ���
	 * 
	 * @param ownerId
	 */
	public int findById(int ownerId) {
		int temp = 0;
		List<Pet> list = petdao.findById(ownerId);
		if (list.size() == 0) {
			System.out.println("�㻹û�г���!���ȹ���.");
			temp = 1;
		} else {
			petInfo(list);
		}
		return temp;
	}

	/**
	 * �����̻�ID�ҳ���
	 * 
	 * @param ownerId
	 */
	public int findByStore(int storeId) {
		int temp = 0;
		List<Pet> list = petdao.findByStore(storeId);
		if (list.size() == 0) {
			System.out.println("�㻹û�������Լ��ĳ���,���ȹ������!");
			temp = 1;
		} else {
			petInfo(list);
		}
		return temp;
	}

	/**
	 * ���������ӵ������Ϣ
	 * 
	 * @param petId
	 * @return
	 */
	public int dealById(int petId, int storeId, int ownerId) {
		int temp = petdao.clearUser(petId,storeId,ownerId);
		return temp;
	}

	/**
	 * ��ѯ�ض�����ļ۸�
	 * 
	 * @param petId
	 * @return
	 */
	public int priceById(int petId) {
		Pet pet = petdao.petById(petId);
		int price = pet.getPrice();
		return price;
	}

	/**
	 * ��ʾ�����۵ĳ�����Ϣ
	 */
	public void findSellPet() {
		List<Pet> list = petdao.findSellPet();
		System.out.println("*************************************");
		System.out.println("���\t��������\t��������\t������\t��������\t����ֵ\t����ֵ\t����");
		for (Pet pet : list) {
			int id = pet.getId();
			int ownerId = pet.getOwnerId();
			int storeId = pet.getStoreId();
			String name = pet.getName();
			String typeName = pet.getTypeName();
			int health = pet.getHealth();
			int love = pet.getLove();
			Date birthday = pet.getBirthday();
			System.out.println(id + "\t" + ownerId + "\t" + storeId + "\t"
					+ name + "\t" + typeName + "\t" + health + "\t" + love
					+ "\t" + birthday);
		}
		System.out.println("*************************************");

	}

	/**
	 * ���ĳ���ӵ������Ϣ
	 * 
	 * @param id
	 * @param petId
	 * @return
	 */
	public int buyPet(int id, int petId) {
		int temp = petdao.buyPet(id, petId);
		return temp;
	}

	/**
	 * ���ĳ�����̻���Ϣ
	 * 
	 * @param id
	 * @param petId
	 * @return
	 */
	public int storeByPet(int id, int petId) {
		int temp = petdao.storeBuyPet(id, petId);
		return temp;
	}

	/**
	 * ������Ϣ����ʾ
	 * 
	 * @param list
	 */
	public void petInfo(List<Pet> list) {
		System.out.println("*************************************");
		System.out.println("���\t��������\t��������\t������\t��������\t����ֵ\t����ֵ\t����");
		for (Pet pet : list) {
			int id = pet.getId();
			int ownerId = pet.getOwnerId();
			int storeId = pet.getStoreId();
			String name = pet.getName();
			String typeName = pet.getTypeName();
			int health = pet.getHealth();
			int love = pet.getLove();
			Date birthday = pet.getBirthday();
			System.out.println(id + "\t" + ownerId + "\t" + storeId + "\t"
					+ name + "\t" + typeName + "\t" + health + "\t" + love
					+ "\t" + birthday);
		}
		System.out.println("*************************************");
	}

	/**
	 * ����һ���³���
	 * 
	 * @param name
	 * @param typeId
	 * @return
	 */
	public int createPet(String name, int typeId) {
		int temp = 0;
		String typeName = pettypeservice.findById(typeId);
		if (typeName != null) {
			temp = petdao.addDate(name, typeName);
		} else {
			System.out.println("����ID�������!");
		}
		return temp;
	}

	/**
	 * �жϳ����ǲ������˵�
	 * 
	 * @param id
	 * @param petid
	 * @return
	 */
	public int isOwner(int id, int petid) {
		int temp = 0;
		List<Pet> list = petdao.findById(id);
		for (Pet pet : list) {
			int petId = pet.getId();
			if (petid == petId) {
				temp = 1;
			}
		}
		return temp;
	}

	/**
	 * ���ӳ���Ľ���ֵ
	 * 
	 * @param petId
	 * @param i
	 */
	public void addHealth(int petId, int i) {
		int temp = petdao.addHealth(petId, i);
		if (temp == 0) {
			System.out.println("����ֵ����ʧ��!");
		}
	}

	/**
	 * ���ӳ��ﰮ��ֵ
	 * 
	 * @param petId
	 */
	public void addLove(int petId, int i) {
		int temp = petdao.addLove(petId, i);
		if (temp == 0) {
			System.out.println("����ֵ����ʧ��!");
		}
	}

	/**
	 * �����ض��ĳ�����Ϣ
	 * 
	 * @param petId
	 * @return
	 */
	public Pet petInfoByPetId(int petId) {
		Pet pet = petdao.petById(petId);
		return pet;
	}
}
