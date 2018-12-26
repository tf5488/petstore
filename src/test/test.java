package test;

import java.util.Scanner;

import service.PetOwnerService;
import service.PetService;
import service.PetStoreService;

public class test {
	private static Scanner input = new Scanner(System.in);
	private static PetOwnerService userservice = new PetOwnerService();
	private static PetStoreService storeservice = new PetStoreService();
	private static PetService pet = new PetService();

	public static void main(String[] args) {
		allInfo();
		firstMenu();
	}

	// 系统初始化 显示所有信息
	public static void allInfo() {
		System.out.println("宠物商店启动.......");
		pet.showAll();
		userservice.showAll();
		storeservice.showAll();
	}

	// 首页
	public static void firstMenu() {
		System.out.println("请选择登录模式:1.宠物主人 2.商店主人");
		int choose = input.nextInt();
		if (choose == 1) {
			int temp = 0;
			while (temp == 0) {
				temp = userservice.petMaster();
			}
			userservice.ownerOperation();
		} else if (choose == 2) {
			int temp = 0;
			while (temp == 0) {
				temp = storeservice.storeMaster();
			}
			storeservice.storeOperation();
		} else {
			System.out.println("输入错误,请重新输入!");
			firstMenu();
		}
	}

}
