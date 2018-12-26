package entity;

public class PetType {
	private int id;
	private String typeName;
	private int price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public PetType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PetType(int id, String typeName,int price) {
		super();
		this.id = id;
		this.typeName = typeName;
		this.price = price;
	}
}
