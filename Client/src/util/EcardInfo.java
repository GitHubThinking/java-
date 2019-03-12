package util;

public class EcardInfo implements java.io.Serializable {
	
	private static final long serialVersionUID = -9094744682408999903L;
	private int _state;   // 1为正常,2为挂失中，3为被冻结
	private double _balance;
	
	public EcardInfo() {
		setState(1);
	}

	public int getState() {
		return _state;
	}

	public void setState(int _state) {
		this._state = _state;
	}

	public double getBalance() {
		return _balance;
	}

	public void setBalance(double _balance) {
		this._balance = _balance;
	}
	
	public void changeBalance(double money) {
		if (_state == 1)
			this._balance += money;
	}

}
