package util;

import java.io.Serializable;


public class AdministrativeRight implements Serializable {

	private static final long serialVersionUID = 1L;
	private int _EcardNumber;            //一卡通号
	private boolean _bookAdmin;      //图书管理权限
	private boolean _courseAdmin;    //课程管理权限
	private boolean _shopAdmin;     //商店管理权限
	private boolean _basicInfoAdmin; //宿舍管理权限
	
	public AdministrativeRight() {
		setEcardNumber(0);
		setBookAdmin(false);
		setCourseAdmin(false);
		setshopAdmin(false);
		setbasicInfoAdmin(false);
	}
	
	//setter and getter
	public boolean getBookAdmin() {
		return _bookAdmin;
	}
	public void setBookAdmin(boolean _bookAdmin) {
		this._bookAdmin = _bookAdmin;
	}
	
	public boolean getCourseAdmin() {
		return _courseAdmin;
	}
	public void setCourseAdmin(boolean _courseAdmin) {
		this._courseAdmin = _courseAdmin;
	}
	
	public boolean getshopAdmin() {
		return _shopAdmin;
	}
	public void setshopAdmin(boolean _shopAdmin) {
		this._shopAdmin = _shopAdmin;
	}
	
	public boolean getbasicInfoAdmin() {
		return _basicInfoAdmin;
	}
	public void setbasicInfoAdmin(boolean _basicInfoAdmin) {
		this._basicInfoAdmin = _basicInfoAdmin;
	}
	
	public int getEcardNumber() {
		return _EcardNumber;
	}
	public void setEcardNumber(int _EcardNumber) {
		this._EcardNumber = _EcardNumber;
	}
}
