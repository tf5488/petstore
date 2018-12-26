package service;

import java.util.List;

import dao.PetTypeDao;
import dao.PetTypeDaoImpl;
import entity.PetType;

public class PetTypeService {
	PetTypeDao pettypedao = new PetTypeDaoImpl();

	/**
	 * 显示宠物类型信息
	 */
	public void showType() {
		List<PetType> list = pettypedao.showType();
		System.out.println("ID\t类型名称");
		for (PetType pettype : list) {
			int id = pettype.getId();
			String typeName = pettype.getTypeName();
			System.out.println(id + "\t" + typeName);
		}
	}

	/**
	 * 通过类型ID查找类型名称
	 * 
	 * @param typeId
	 * @return
	 */
	public String findById(int typeId) {
		String typeName = pettypedao.findById(typeId);
		return typeName;
	}

}
