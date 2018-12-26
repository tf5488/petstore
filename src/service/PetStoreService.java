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

	// ������֤
	public PetStore findStore(String name, String password) {
		PetStore petstore = petstoredao.findStore(name, password);
		return petstore;
	}

	// ��ʼ����Ϣ
	public void showAll() {
		System.out.println("���г������˴�MySQL������!");
		List<PetStore> list = petstoredao.showAll();
		System.out.println("*************************************");
		System.out.println("���\t�����̵�����");
		for (PetStore petsore : list) {
			int id = petsore.getId();
			String name = petsore.getName();
			System.out.println(id + "\t" + name);
		}
		System.out.println("*************************************");
	}

	// �̵����˵�¼
	public int storeMaster() {
		int temp = 0;
		System.out.println("���������������:");
		name = input.next();
		System.out.println("���������������:");
		password = input.next();
		PetStore petstore = findStore(name, password);
		if (petstore != null) {
			System.out.println("��¼�ɹ�!");
			id = petstore.getId();
			balance = petstore.getBalance();
			temp = 1;
		} else {
			System.out.println("�û������������!����������!");
			storeMaster();
		}
		return temp;
	}

	// �̵����˲˵�
	public int storeList() {
		System.out.println("************�����̵������Ϣ************");
		update();
		System.out.print("����:");
		System.out.println(name);
		System.out.println("Ԫ����:");
		System.out.println(balance);
		int temp = 9;
		do {
			System.out.println("�����Խ������²���:");
			System.out.println("1.�������");
			System.out.println("2.��������");
			System.out.println("3.��������");
			System.out.println("4.��ѯ���۳���");
			System.out.println("5.�鿴�̵����");
			System.out.println("6.�鿴��Ʒ��Ŀ");
			System.out.println("7.�������̵�");
			System.out.println("�������Ҫִ�еĲ���,ѡ���������,�˳�������0:");
			temp = input.nextInt();
			if (temp != 1 && temp != 2 && temp != 3 && temp != 4 && temp != 5
					&& temp != 6 && temp != 7 && temp != 0) {
				System.out.println("�������,����������!");
			}
		} while (temp != 1 && temp != 2 && temp != 3 && temp != 4 && temp != 5
				&& temp != 6 && temp != 7 && temp != 0);
		return temp;
	}

	/**
	 * ��Ҳ˵�����
	 */
	public void storeOperation() {
		int temp = storeList();
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
	 * �½�һ���̻�
	 */
	private void createStore() {
		System.out.println("��������Ҫ�½����̻�����:");
		String name = input.next();
		List<PetStore> list = petstoredao.showAll();
		for (PetStore petsore : list) {
			String storeName = petsore.getName();
			if (name.equals(storeName)) {
				System.out.println("�Բ�����̻��Ѵ���!�����²���.");
				createStore();
			}
		}
		System.out.println("��������:");
		String password = input.next();
		System.out.println("������˻����:");
		int balance = input.nextInt();
		int temp = petstoredao.addDate(name, password, balance);
		if (temp > 0) {
			System.out.println("�̻������ɹ�!");
		} else {
			System.out.println("�����̻�ʧ��!�����²���.");
			createStore();
		}
		storeOperation();
	}

	/**
	 * �鿴��ǰ�û��˻���Ϣ
	 */
	private void accountInfo() {
		accountservice.showAll(id);
		storeOperation();
	}

	/**
	 * ��ǰ�˻����
	 */
	private void storeSurplus() {
		update();
		accountservice.showSurplus(id);
		System.out.println("��ǰ�˻����:" + balance);
		storeOperation();
	}

	/**
	 * ����һ���³���
	 */
	private void createPet() {
		pettypeservice.showType();
		System.out.println("��ѡ�������ĳ�������ID:");
		int typeId = input.nextInt();
		System.out.println("��������������:");
		String name = input.next();
		int temp = petservice.createPet(name, typeId);
		if (temp > 0) {
			System.out.println("��ϲ��ɹ�������һ���³���:" + name);
		} else {
			System.out.println("�Բ���,����ʧ����!�����²���.");
			createPet();
		}
		storeOperation();
	}

	/**
	 * ������
	 */
	private void sellPet() {
		int temp2 = petservice.findByStore(id);
		if (temp2 == 0) {
			System.out.println("��ѡ����Ҫ�����ĳ���ID:");
			int petId = input.nextInt();
			petownerservice.showInfo();
			System.out.println("��ѡ������������ID:");
			int ownerId = input.nextInt();
			int price = petservice.priceById(petId);
			int temp = petservice.dealById(petId, 0, ownerId);
			if (temp > 0) {
				System.out.println("�����ɹ�!�������:" + price + "Ԫ��.");
				addPrice(id, price);
				accountservice.insertAccount(petId, ownerId, id, price);
			} else {
				System.out.println("����ʧ��!�����²���.");
				sellPet();
			}
			storeOperation();
		} else {
			storeOperation();
		}
	}

	/**
	 * �ع�����
	 */
	private void buyPet() {
		petservice.findSellPet();
		System.out.println("��ѡ����Ҫ����ĳ���ID:");
		int petId = input.nextInt();
		int price = petservice.priceById(petId);
		int temp = petservice.storeByPet(id, petId);
		if (temp > 0) {
			System.out.println("���׳ɹ�!��ϲ����һ������,������:" + price);
			delePrice(id, price);
			accountservice.insertAccount(petId, 0, id, price);
		} else {
			System.out.println("����ʧ��!�����²���.");
			buyPet();
		}
		storeOperation();
	}

	/**
	 * �����̻����
	 * 
	 * @param id2
	 * @param price
	 */
	private void delePrice(int id2, int price) {
		int temp = petstoredao.delePrice(id2, price);
		if (temp == 0) {
			System.out.println("���ϵͳ����!");
		}
	}

	/**
	 * �����̻����
	 * 
	 * @param id2
	 * @param price
	 */
	private void addPrice(int id2, int price) {
		int temp = petstoredao.addPrice(id2, price);
		if (temp == 0) {
			System.out.println("���ϵͳ����!");
		}
	}

	/**
	 * �����̻����
	 */
	public void update() {
		PetStore petstore = findStore(name, password);
		if (petstore != null) {
			balance = petstore.getBalance();
		}
	}

	/**
	 * ��ʾ�̻���Ϣ
	 */
	public void showInfo() {
		List<PetStore> list = petstoredao.showAll();
		System.out.println("*************************************");
		System.out.println("���\t�����̵�����");
		for (PetStore petsore : list) {
			int id = petsore.getId();
			String name = petsore.getName();
			System.out.println(id + "\t" + name);
		}
	}
}
