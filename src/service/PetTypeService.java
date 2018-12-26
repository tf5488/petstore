package service;

import java.util.List;

import dao.PetTypeDao;
import dao.PetTypeDaoImpl;
import entity.PetType;

public class PetTypeService {
	PetTypeDao pettypedao = new PetTypeDaoImpl();

	/**
	 * ��ʾ����������Ϣ
	 */
	public void showType() {
		List<PetType> list = pettypedao.showType();
		System.out.println("ID\t��������");
		for (PetType pettype : list) {
			int id = pettype.getId();
			String typeName = pettype.getTypeName();
			System.out.println(id + "\t" + typeName);
		}
	}

	/**
	 * ͨ������ID������������
	 * 
	 * @param typeId
	 * @return
	 */
	public String findById(int typeId) {
		String typeName = pettypedao.findById(typeId);
		return typeName;
	}

}
