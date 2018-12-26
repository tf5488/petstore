package service;

import java.util.List;
import java.util.Scanner;

import dao.PetOwnerDao;
import dao.PetOwnerDaoImpl;
import entity.PetOwner;

public class PetOwnerService {
	Scanner input = new Scanner(System.in);
	PetOwnerDao petownerdao = new PetOwnerDaoImpl();
	PetService petservice = new PetService();
	PetStoreService petstoreservice = new PetStoreService();
	AccountService accountservice = new AccountService();
	private int money;
	private String name;
	private String password;
	private int id;
	private int petId;

	/**
	 * 用户信息的验证
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	public PetOwner findOwner(String name, String password) {
		PetOwner petuser = petownerdao.findOwner(name, password);
		return petuser;
	}

	/**
	 * 初始化信息
	 */
	public void showAll() {
		System.out.println("所有宠物主人从MySQL中醒来!");
		List<PetOwner> list = petownerdao.showAll();
		System.out.println("*************************************");
		System.out.println("序号\t主人姓名");
		for (PetOwner petowner : list) {
			int id = petowner.getId();
			String name = petowner.getName();
			System.out.println(id + "\t" + name);
		}
		System.out.println("*************************************");
	}

	/**
	 * 显示主人信息
	 */
	public void showInfo() {
		List<PetOwner> list = petownerdao.showAll();
		System.out.println("*************************************");
		System.out.println("序号\t主人姓名");
		for (PetOwner petowner : list) {
			int id = petowner.getId();
			String name = petowner.getName();
			System.out.println(id + "\t" + name);
		}
	}

	/**
	 * 宠物主人登录
	 * 
	 * @return
	 */
	public int petMaster() {
		int temp = 0;
		System.out.println("请输入主人的名字:");
		name = input.next();
		System.out.println("请输入主人的密码:");
		password = input.next();
		PetOwner user = findOwner(name, password);
		if (user != null) {
			System.out.println("登录成功!");
			money = user.getMoney();
			id = user.getId();
			temp = 1;
		} else {
			System.out.println("用户名或密码错误!请重新输入!");
			petMaster();
		}
		return temp;
	}

	/**
	 * 宠物主人菜单
	 * 
	 * @return
	 */
	public int petList() {
		update();
		System.out.println("************基本信息************");
		System.out.print("名字:");
		System.out.println(name);
		System.out.println("元宝数:");
		System.out.println(money);
		int temp = 0;
		do {
			System.out.println("您可以进行以下操作:");
			System.out.println("1.购买宠物");
			System.out.println("2.卖出宠物");
			System.out.println("3.宠物活动");
			System.out.println("请根据需要执行的操作,选择序号输入,退出请输入0:");
			temp = input.nextInt();
			if (temp != 1 && temp != 2 && temp != 0 && temp != 3) {
				System.out.println("输入错误,请重新输入!");
			}
		} while (temp != 1 && temp != 2 && temp != 0 && temp != 3);
		return temp;
	}

	/**
	 * 主人菜单操作
	 */
	public void ownerOperation() {
		int temp = petList();
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
			PetAction();
			break;
		}
	}

	/**
	 * 宠物活动
	 */
	private void PetAction() {
		int temp2 = petservice.findById(id);
		if (temp2 == 0) {
			System.out.println("请选择宠物ID:");
			int petid = input.nextInt();
			int temp = petservice.isOwner(id, petid);
			if (temp == 1) {
				petId = petid;
				actionOperation();
			} else {
				System.out.println("你输入的宠物ID错误!请重新输入.");
				PetAction();
			}
		} else {
			ownerOperation();
		}
	}

	/**
	 * 宠物活动清单操作
	 */
	private void actionOperation() {
		int temp = actionList();
		switch (temp) {
		case 0:
			ownerOperation();
			break;
		case 1:
			petEat();
			break;
		case 2:
			petPlay();
			break;
		}

	}

	/**
	 * 陪宠物玩耍,健康值+10,金钱-5
	 */
	private void petPlay() {
		update();
		if (money > 5) {
			System.out.println("你花费了5元宝让宠物跑了个马拉松,宠物开心极了!健康值+10");
			delePrice(id, 5);
			petservice.addLove(petId, 10);
		}
		actionOperation();
	}

	/**
	 * 宠物吃东西,爱心值+10,金钱-5
	 */
	private void petEat() {
		update();
		if (money > 5) {
			System.out.println("你花费了5元宝喂养了宠物,宠物开心极了!爱心值+10");
			delePrice(id, 5);
			petservice.addHealth(petId, 10);
		}
		actionOperation();
	}

	/**
	 * 活动清单
	 */
	private int actionList() {
		int temp = 0;
		do {
			System.out.println("您可以进行以下操作:");
			System.out.println("1.喂东西");
			System.out.println("2.玩耍");
			System.out.println("请根据需要执行的操作,选择序号输入,退出请输入0:");
			temp = input.nextInt();
			if (temp != 1 && temp != 2 && temp != 0) {
				System.out.println("输入错误,请重新输入!");
			}
		} while (temp != 1 && temp != 2 && temp != 0);
		return temp;
	}

	/**
	 * 卖出宠物
	 */
	private void sellPet() {
		int temp2 = petservice.findById(id);
		if (temp2 == 0) {
			System.out.println("请输入你要拍卖的宠物ID:");
			int petId = input.nextInt();
			petstoreservice.showInfo();
			System.out.println("请输入购买宠物的商户ID:");
			int storeId = input.nextInt();
			int temp = petservice.dealById(petId,storeId,0);
			int price = petservice.priceById(petId);
			if (temp > 0) {
				System.out.println("拍卖成功!获得收益:" + price + "元宝.");
				addPrice(id, price);
				accountservice.insertAccount(petId, id, storeId, price);
			} else {
				System.out.println("拍卖失败!请重新操作.");
				sellPet();
			}
			ownerOperation();
		} else {
			ownerOperation();
		}

	}

	/**
	 * 增加用户余额
	 * 
	 * @param id
	 * @param price
	 */
	private void addPrice(int id, int price) {
		int temp = petownerdao.addPrice(id, price);
		if (temp == 0) {
			System.out.println("余额系统错误!");
		}
	}

	/**
	 * 扣除用户余额
	 * 
	 * @param id
	 * @param price
	 */
	public void delePrice(int id, int price) {
		int temp = petownerdao.delePrice(id, price);
		if (temp == 0) {
			System.out.println("余额系统错误!");
		}
	}

	/**
	 * 用户购买宠物
	 */
	private void buyPet() {
		petservice.findSellPet();
		System.out.println("请选择你要购买的宠物ID:");
		int petId = input.nextInt();
		int price = petservice.priceById(petId);
		if (price > money) {
			System.out.println("对不起,你的金额不足!请重新选择.");
			buyPet();
		} else {
			int temp = petservice.buyPet(id, petId);
			if (temp > 0) {
				System.out.println("交易成功!恭喜你获得一个宠物,共花费:" + price);
				delePrice(id, price);
				accountservice.insertAccount(petId, id, 0, price);
			} else {
				System.out.println("购买失败!请重新操作.");
				buyPet();
			}
			ownerOperation();
		}
	}

	/**
	 * 更新用户余额
	 */
	public void update() {
		PetOwner user = findOwner(name, password);
		if (user != null) {
			money = user.getMoney();
		}
	}
}
