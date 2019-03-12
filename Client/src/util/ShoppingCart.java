package util;

public class ShoppingCart implements java.io.Serializable{

	private static final long serialVersionUID = -3592076712701704097L;
	private int _commoID;
	private int _AmountInCart;
	public int getCommoID() {
		return _commoID;
	}
	public void setCommoID(int _commoID) {
		this._commoID = _commoID;
	}
	public int getCommoAmount() {
		return _AmountInCart;
	}
	public void setCommoAmount(int _AmountInCart) {
		this._AmountInCart = _AmountInCart;
	}
}
