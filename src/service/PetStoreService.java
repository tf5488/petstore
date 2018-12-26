package service;

import java.util.List;
import java.util.Scanner;

import dao.PetStoreDao;
import dao.PetStoreDaoImpl;
import entity.PetStore;

public class PetStoreService {
	Scanner input = new Scanner(System.in);
	PetStoreDao petstoredao = new PetStoreDaoImpl();
	PetService petservice = new PetService();
	PetOwnerService petownerservice = new PetOwnerService();
	PetTypeService pettypeservice = new PetTypeService();
	AccountService accountservice = new AccountService();
	private int balance;
	private String name;
	private String password;
	private int id;

	// 店主验证
	public PetStore findStore(String name, String password) {
		PetStore petstore = petstoredao.findStore(name, password);
		return petstore;
	}

	// 初始化信息
	public void showAll() {
		System.out.println("所有宠物主人从MySQL中醒来!");
		List<PetStore> list = petstoredao.showAll();
		System.out.println("*************************************");
		System.out.println("序号\t宠物商店名称");
		for (PetStore petsore : list) {
			int id = petsore.getId();
			String name = petsore.getName();
			System.out.println(id + "\t" + name);
		}
		System.out.println("*************************************");
	}

	// 商店主人登录
	public int storeMaster() {
		int temp = 0;
		System.out.println("请输入店主的名字:");
		name = input.next();
		System.out.println("请输入店主的密码:");
		password = input.next();
		PetStore petstore = findStore(name, password);
		if (petstore != null) {
			System.out.println("登录成功!");
			id = petstore.getId();
			balance = petstore.getBalance();
			temp = 1;
		} else {
			System.out.println("用户名或密码错误!请重新输入!");
			storeMaster();
		}
		return temp;
	}

	// 商店主人菜单
	public int storeList() {
		System.out.println("************宠物商店基本信息************");
		update();
		System.out.print("名字:");
		System.out.println(name);
		System.out.println("元宝数:");
		System.out.println(balance);
		int temp = 9;
		do {
			System.out.println("您可以进行以下操作:");
			System.out.println("1.购买宠物");
			System.out.println("2.卖出宠物");
			System.out.println("3.培育宠物");
			System.out.println("4.查询待售宠物");
			System.out.println("5.查看商店结余");
			System.out.println("6.查看商品账目");
			System.out.println("7.开宠物商店");
			System.out.println("请根据需要执行的操作,选择序号输入,退出请输入0:");
			temp = input.nextInt();
			if (temp != 1 && temp != 2 && temp != 3 && temp != 4 && temp != 5
					&& temp != 6 && temp != 7 && temp != 0) {
				System.out.println("输入错误,请重新输入!");
			}
		} while (temp != 1 && temp != 2 && temp != 3 && temp != 4 && temp != 5
				&& temp != 6 && temp != 7 && temp != 0);
		return temp;
	}

	/**
	 * 店家菜单操作
	 */
	public void storeOperation() {
		int temp = storeList();
		switch (temp) {
		case 0:
			System.out.println("退出程序,谢谢你的使用!");
			break;
		case 1:
			buyPet();
			break;
		case 2:
			sellPet();
			break;
		case 3:
			createPet();
			break;
		case 4:
			petservice.findSellPet();
			storeOperation();
			break;
		case 5:
			storeSurplus();
			break;
		case 6:
			accountInfo();
			break;
		case 7:
			createStore();
			break;
		}

	}

	/**
	 * 新建一个商户
	 */
	private void createStore() {
		System.out.println("请输入你要新建的商户名称:");
		String name = input.next();
		List<PetStore> list = petstoredao.showAll();
		for (PetStore petsore : list) {
			String storeName = petsore.getName();
			if (name.equals(storeName)) {
				System.out.println("对不起该商户已存在!请重新操作.");
				createStore();
			}
		}
		System.out.println("请输密码:");
		String password = input.next();
		System.out.println("请添加账户金额:");
		int balance = input.nextInt();
		int temp = petstoredao.addDate(name, password, balance);
		if (temp > 0) {
			System.out.println("商户创建成功!");
		} else {
			System.out.println("创建商户失败!请重新操作.");
			createStore();
		}
		storeOperation();
	}

	/**
	 * 查看当前用户账户信息
	 */
	private void accountInfo() {
		accountservice.showAll(id);
		storeOperation();
	}

	/**
	 * 当前账户余额
	 */
	private void storeSurplus() {
		update();
		accountservice.showSurplus(id);
		System.out.println("当前账户余额:" + balance);
		storeOperation();
	}

	/**
	 * 培育一个新宠物
	 */
	private void createPet() {
		pettypeservice.showType();
		System.out.println("请选择培育的宠物类型ID:");
		int typeId = input.nextInt();
		System.out.println("请输入宠物的名字:");
		String name = input.next();
		int temp = petservice.createPet(name, typeId);
		if (temp > 0) {
			System.out.println("恭喜你成功培育了一个新宠物:" + name);
		} else {
			System.out.println("对不起,培育失败了!请重新操作.");
			createPet();
		}
		storeOperation();
	}

	/**
	 * 卖宠物
	 */
	private void sellPet() {
		int temp2 = petservice.findByStore(id);
		if (temp2 == 0) {
			System.out.println("请选择你要拍卖的宠物ID:");
			int petId = input.nextInt();
			petownerservice.showInfo();
			System.out.println("请选择买宠物的主人ID:");
			int ownerId = input.nextInt();
			int price = petservice.priceById(petId);
			int temp = petservice.dealById(petId, 0, ownerId);
			if (temp > 0) {
				System.out.println("拍卖成功!获得收益:" + price + "元宝.");
				addPrice(id, price);
				accountservice.insertAccount(petId, ownerId, id, price);
			} else {
				System.out.println("购买失败!请重新操作.");
				sellPet();
			}
			storeOperation();
		} else {
			storeOperation();
		}
	}

	/**
	 * 回购宠物
	 */
	private void buyPet() {
		petservice.findSellPet();
		System.out.println("请选择你要购买的宠物ID:");
		int petId = input.nextInt();
		int price = petservice.priceById(petId);
		int temp = petservice.storeByPet(id, petId);
		if (temp > 0) {
			System.out.println("交易成功!恭喜你获得一个宠物,共花费:" + price);
			delePrice(id, price);
			accountservice.insertAccount(petId, 0, id, price);
		} else {
			System.out.println("购买失败!请重新操作.");
			buyPet();
		}
		storeOperation();
	}

	/**
	 * 减少商户余额
	 * 
	 * @param id2
	 * @param price
	 */
	private void delePrice(int id2, int price) {
		int temp = petstoredao.delePrice(id2, price);
		if (temp == 0) {
			System.out.println("余额系统错误!");
		}
	}

	/**
	 * 增加商户余额
	 * 
	 * @param id2
	 * @param price
	 */
	private void addPrice(int id2, int price) {
		int temp = petstoredao.addPrice(id2, price);
		if (temp == 0) {
			System.out.println("余额系统错误!");
		}
	}

	/**
	 * 更新商户余额
	 */
	public void update() {
		PetStore petstore = findStore(name, password);
		if (petstore != null) {
			balance = petstore.getBalance();
		}
	}

	/**
	 * 显示商户信息
	 */
	public void showInfo() {
		List<PetStore> list = petstoredao.showAll();
		System.out.println("*************************************");
		System.out.println("序号\t宠物商店名称");
		for (PetStore petsore : list) {
			int id = petsore.getId();
			String name = petsore.getName();
			System.out.println(id + "\t" + name);
		}
	}
}
