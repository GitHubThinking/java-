package util;

public class Commodity implements java.io.Serializable {

	private static final long serialVersionUID = -1628627823619818674L;
	private int _commoID;
	private String _brand;
	private String _product;
	private double _price;
	private String briefInfo;
	private String _picturePath;
	private int _stock;
	
	public int getID() {
		return _commoID;
	}

	public void setID(int _commoID) {
		this._commoID = _commoID;
	}

	public String getBrand() {
		return _brand;
	}

	public void setBrand(String _brand) {
		this._brand = _brand;
	}
	
	public String getProduct() {
		return _product;
	}

	public void setProduct(String _product) {
		this._product = _product;
	}
	
	public double getPrice() {
		return _price;
	}
	
    public void setPrice(double _price) {
		this._price = _price;
	}

    public String getBriefInfo() {
 		return briefInfo;
 	}

    public void setBriefInfo(String briefInfo) {
 		this.briefInfo = briefInfo;
 	}
     
    public String getPicturePath() {
		return _picturePath;
	}

    public void setPicturePath(String _picturePath) {
		this._picturePath = _picturePath;
	}

	public int getStock() {
		return _stock;
	}

	public void setStock(int _stock) {
		this._stock = _stock;
	}


}

