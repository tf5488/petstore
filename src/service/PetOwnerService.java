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
	 * �û���Ϣ����֤
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
	 * ��ʼ����Ϣ
	 */
	public void showAll() {
		System.out.println("���г������˴�MySQL������!");
		List<PetOwner> list = petownerdao.showAll();
		System.out.println("*************************************");
		System.out.println("���\t��������");
		for (PetOwner petowner : list) {
			int id = petowner.getId();
			String name = petowner.getName();
			System.out.println(id + "\t" + name);
		}
		System.out.println("*************************************");
	}

	/**
	 * ��ʾ������Ϣ
	 */
	public void showInfo() {
		List<PetOwner> list = petownerdao.showAll();
		System.out.println("*************************************");
		System.out.println("���\t��������");
		for (PetOwner petowner : list) {
			int id = petowner.getId();
			String name = petowner.getName();
			System.out.println(id + "\t" + name);
		}
	}

	/**
	 * �������˵�¼
	 * 
	 * @return
	 */
	public int petMaster() {
		int temp = 0;
		System.out.println("���������˵�����:");
		name = input.next();
		System.out.println("���������˵�����:");
		password = input.next();
		PetOwner user = findOwner(name, password);
		if (user != null) {
			System.out.println("��¼�ɹ�!");
			money = user.getMoney();
			id = user.getId();
			temp = 1;
		} else {
			System.out.println("�û������������!����������!");
			petMaster();
		}
		return temp;
	}

	/**
	 * �������˲˵�
	 * 
	 * @return
	 */
	public int petList() {
		update();
		System.out.println("************������Ϣ************");
		System.out.print("����:");
		System.out.println(name);
		System.out.println("Ԫ����:");
		System.out.println(money);
		int temp = 0;
		do {
			System.out.println("�����Խ������²���:");
			System.out.println("1.�������");
			System.out.println("2.��������");
			System.out.println("3.����");
			System.out.println("�������Ҫִ�еĲ���,ѡ���������,�˳�������0:");
			temp = input.nextInt();
			if (temp != 1 && temp != 2 && temp != 0 && temp != 3) {
				System.out.println("�������,����������!");
			}
		} while (temp != 1 && temp != 2 && temp != 0 && temp != 3);
		return temp;
	}

	/**
	 * ���˲˵�����
	 */
	public void ownerOperation() {
		int temp = petList();
		switch (temp) {
		case 0:
			System.out.println("�˳�����,лл���ʹ��!");
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
	 * ����
	 */
	private void PetAction() {
		int temp2 = petservice.findById(id);
		if (temp2 == 0) {
			System.out.println("��ѡ�����ID:");
			int petid = input.nextInt();
			int temp = petservice.isOwner(id, petid);
			if (temp == 1) {
				petId = petid;
				actionOperation();
			} else {
				System.out.println("������ĳ���ID����!����������.");
				PetAction();
			}
		} else {
			ownerOperation();
		}
	}

	/**
	 * �����嵥����
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
	 * �������ˣ,����ֵ+10,��Ǯ-5
	 */
	private void petPlay() {
		update();
		if (money > 5) {
			System.out.println("�㻨����5Ԫ���ó������˸�������,���￪�ļ���!����ֵ+10");
			delePrice(id, 5);
			petservice.addLove(petId, 10);
		}
		actionOperation();
	}

	/**
	 * ����Զ���,����ֵ+10,��Ǯ-5
	 */
	private void petEat() {
		update();
		if (money > 5) {
			System.out.println("�㻨����5Ԫ��ι���˳���,���￪�ļ���!����ֵ+10");
			delePrice(id, 5);
			petservice.addHealth(petId, 10);
		}
		actionOperation();
	}

	/**
	 * ��嵥
	 */
	private int actionList() {
		int temp = 0;
		do {
			System.out.println("�����Խ������²���:");
			System.out.println("1.ι����");
			System.out.println("2.��ˣ");
			System.out.println("�������Ҫִ�еĲ���,ѡ���������,�˳�������0:");
			temp = input.nextInt();
			if (temp != 1 && temp != 2 && temp != 0) {
				System.out.println("�������,����������!");
			}
		} while (temp != 1 && temp != 2 && temp != 0);
		return temp;
	}

	/**
	 * ��������
	 */
	private void sellPet() {
		int temp2 = petservice.findById(id);
		if (temp2 == 0) {
			System.out.println("��������Ҫ�����ĳ���ID:");
			int petId = input.nextInt();
			petstoreservice.showInfo();
			System.out.println("�����빺�������̻�ID:");
			int storeId = input.nextInt();
			int temp = petservice.dealById(petId,storeId,0);
			int price = petservice.priceById(petId);
			if (temp > 0) {
				System.out.println("�����ɹ�!�������:" + price + "Ԫ��.");
				addPrice(id, price);
				accountservice.insertAccount(petId, id, storeId, price);
			} else {
				System.out.println("����ʧ��!�����²���.");
				sellPet();
			}
			ownerOperation();
		} else {
			ownerOperation();
		}

	}

	/**
	 * �����û����
	 * 
	 * @param id
	 * @param price
	 */
	private void addPrice(int id, int price) {
		int temp = petownerdao.addPrice(id, price);
		if (temp == 0) {
			System.out.println("���ϵͳ����!");
		}
	}

	/**
	 * �۳��û����
	 * 
	 * @param id
	 * @param price
	 */
	public void delePrice(int id, int price) {
		int temp = petownerdao.delePrice(id, price);
		if (temp == 0) {
			System.out.println("���ϵͳ����!");
		}
	}

	/**
	 * �û��������
	 */
	private void buyPet() {
		petservice.findSellPet();
		System.out.println("��ѡ����Ҫ����ĳ���ID:");
		int petId = input.nextInt();
		int price = petservice.priceById(petId);
		if (price > money) {
			System.out.println("�Բ���,��Ľ���!������ѡ��.");
			buyPet();
		} else {
			int temp = petservice.buyPet(id, petId);
			if (temp > 0) {
				System.out.println("���׳ɹ�!��ϲ����һ������,������:" + price);
				delePrice(id, price);
				accountservice.insertAccount(petId, id, 0, price);
			} else {
				System.out.println("����ʧ��!�����²���.");
				buyPet();
			}
			ownerOperation();
		}
	}

	/**
	 * �����û����
	 */
	public void update() {
		PetOwner user = findOwner(name, password);
		if (user != null) {
			money = user.getMoney();
		}
	}
}
