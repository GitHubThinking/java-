package util;

import java.util.Date;

public class ShoppingRecord implements java.io.Serializable{

	private static final long serialVersionUID = -509549707109918867L;
	private int _EcardNumber;
	private int _commoID;
	private int _purchaseAmount;
	private Date _purchaseTime;
	
	public int getEcardNumber() {
		return _EcardNumber;
	}
	public void setEcardNumber(int _EcardNumber) {
		this._EcardNumber = _EcardNumber;
	}
	public int getCommoID() {
		return _commoID;
	}
	public void setCommoID(int _commoID) {
		this._commoID = _commoID;
	}
	public int getPurchaseAmount() {
		return _purchaseAmount;
	}
	public void setPurchaseAmount(int _purchaseAmount) {
		this._purchaseAmount = _purchaseAmount;
	}
	public Date getPurchaseTime() {
		return _purchaseTime;
	}
	public void setPurchaseTime(Date _purchaseTime) {
		this._purchaseTime = _purchaseTime;
	}
}

