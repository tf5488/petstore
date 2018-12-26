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

	// ϵͳ��ʼ�� ��ʾ������Ϣ
	public static void allInfo() {
		System.out.println("�����̵�����.......");
		pet.showAll();
		userservice.showAll();
		storeservice.showAll();
	}

	// ��ҳ
	public static void firstMenu() {
		System.out.println("��ѡ���¼ģʽ:1.�������� 2.�̵�����");
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
			System.out.println("�������,����������!");
			firstMenu();
		}
	}

}
