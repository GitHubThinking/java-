package util;

import java.util.Date;

public class EcardRecord implements java.io.Serializable {

	private static final long serialVersionUID = 2639876051429484767L;
	private String _action;
	private Date _actionDate;
	private double _amount;
	private double _balance;
	
	public EcardRecord() {
		setActionDate(new Date());
	}

	public String getAction() {
		return _action;
	}

	public void setAction(String _action) {
		this._action = _action;
	}

	public Date getActionDate() {
		return _actionDate;
	}

	public void setActionDate(Date _actionDate) {
		this._actionDate = _actionDate;
	}

	public double getAmount() {
		return _amount;
	}

	public void setAmount(double _amount) {
		this._amount = _amount;
	}

	public double getBalance() {
		return _balance;
	}

	public void setBalance(double balance) {
		this._balance = balance;
	}
}

