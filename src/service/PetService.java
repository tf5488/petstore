package service;

import java.sql.Date;
import java.util.List;

import dao.PetDao;
import dao.PetDaoImpl;
import entity.Pet;

public class PetService {
	PetDao petdao = new PetDaoImpl();
	PetTypeService pettypeservice = new PetTypeService();

	// 初始化
	public void showAll() {
		System.out.println("Wonderland 醒来,所有动物从MySQL中醒来!");
		List<Pet> list = petdao.showAll();
		System.out.println("*************************************");
		System.out.println("序号\t宠物名称");
		for (Pet pet : list) {
			int id = pet.getId();
			String name = pet.getName();
			System.out.println(id + "\t" + name);
		}
		System.out.println("*************************************");
	}

	/**
	 * 根据主人ID找宠物
	 * 
	 * @param ownerId
	 */
	public int findById(int ownerId) {
		int temp = 0;
		List<Pet> list = petdao.findById(ownerId);
		if (list.size() == 0) {
			System.out.println("你还没有宠物!请先购买.");
			temp = 1;
		} else {
			petInfo(list);
		}
		return temp;
	}

	/**
	 * 根据商户ID找宠物
	 * 
	 * @param ownerId
	 */
	public int findByStore(int storeId) {
		int temp = 0;
		List<Pet> list = petdao.findByStore(storeId);
		if (list.size() == 0) {
			System.out.println("你还没有属于自己的宠物,请先购买宠物!");
			temp = 1;
		} else {
			petInfo(list);
		}
		return temp;
	}

	/**
	 * 消除宠物的拥有者信息
	 * 
	 * @param petId
	 * @return
	 */
	public int dealById(int petId, int storeId, int ownerId) {
		int temp = petdao.clearUser(petId,storeId,ownerId);
		return temp;
	}

	/**
	 * 查询特定宠物的价格
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
	 * 显示待销售的宠物信息
	 */
	public void findSellPet() {
		List<Pet> list = petdao.findSellPet();
		System.out.println("*************************************");
		System.out.println("序号\t宠物主人\t所属店铺\t宠物名\t宠物类型\t健康值\t爱心值\t生日");
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
	 * 更改宠物拥有者信息
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
	 * 更改宠物的商户信息
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
	 * 宠物信息的显示
	 * 
	 * @param list
	 */
	public void petInfo(List<Pet> list) {
		System.out.println("*************************************");
		System.out.println("序号\t宠物主人\t所属店铺\t宠物名\t宠物类型\t健康值\t爱心值\t生日");
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
	 * 培育一个新宠物
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
			System.out.println("类型ID输入错误!");
		}
		return temp;
	}

	/**
	 * 判断宠物是不是主人的
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
	 * 增加宠物的健康值
	 * 
	 * @param petId
	 * @param i
	 */
	public void addHealth(int petId, int i) {
		int temp = petdao.addHealth(petId, i);
		if (temp == 0) {
			System.out.println("爱心值增加失败!");
		}
	}

	/**
	 * 增加宠物爱心值
	 * 
	 * @param petId
	 */
	public void addLove(int petId, int i) {
		int temp = petdao.addLove(petId, i);
		if (temp == 0) {
			System.out.println("爱心值增加失败!");
		}
	}

	/**
	 * 查找特定的宠物信息
	 * 
	 * @param petId
	 * @return
	 */
	public Pet petInfoByPetId(int petId) {
		Pet pet = petdao.petById(petId);
		return pet;
	}
}
