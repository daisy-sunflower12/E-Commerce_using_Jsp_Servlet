package com.model;

public class UserPurchase extends Product{

	private int purchaseId;
	private String purchaseDt;
	private int prodId;
	private int userId;
//	private String prodName;
	private int totalUnit;
	private int purchaseByUser;
	private int purchaseFromUser;
	private double totalCostPrice;
	private double userBalance;

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getPurchaseDt() {
		return purchaseDt;
	}

	public void setPurchaseDt(String purchaseDt) {
		this.purchaseDt = purchaseDt;
	}

	public int getTotalUnit() {
		return totalUnit;
	}

	public void setTotalUnit(int totalUnit) {
		this.totalUnit = totalUnit;
	}

	public int getPurchaseByUser() {
		return purchaseByUser;
	}

	public void setPurchaseByUser(int purchaseByUser) {
		this.purchaseByUser = purchaseByUser;
	}

	public int getPurchaseFromUser() {
		return purchaseFromUser;
	}

	public void setPurchaseFromUser(int purchaseFromUser) {
		this.purchaseFromUser = purchaseFromUser;
	}

	public int getProdId() {
		return prodId;
	}

	public void setProdId(int prodId) {
		this.prodId = prodId;
	}

//	public String getProdName() {
//		return prodName;
//	}
//
//	public void setProdName(String prodName) {
//		this.prodName = prodName;
//	}

	public double getTotalCostPrice() {
		return totalCostPrice;
	}

	public void setTotalCostPrice(double d) {
		this.totalCostPrice = d;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getUserBalance() {
		return userBalance;
	}

	public void setUserBalance(double userBalance) {
		this.userBalance = userBalance;
	}

	
}
