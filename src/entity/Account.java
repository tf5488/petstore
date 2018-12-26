package entity;

import java.sql.Date;

public class Account {
	private int id;
	private int petId;
	private int sellerId;
	private String dealType;
	private int buyerId;
	private int price;
	private Date dealTiem;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPetId() {
		return petId;
	}

	public void setPetId(int petId) {
		this.petId = petId;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public int getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getDealTiem() {
		return dealTiem;
	}

	public void setDealTiem(Date dealTiem) {
		this.dealTiem = dealTiem;
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(int id, int petId, int sellerId, String dealType, int buyerId,
			int price, Date dealTiem) {
		super();
		this.id = id;
		this.petId = petId;
		this.sellerId = sellerId;
		this.dealType = dealType;
		this.buyerId = buyerId;
		this.price = price;
		this.dealTiem = dealTiem;
	}
}
