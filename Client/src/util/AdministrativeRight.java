package util;

import java.io.Serializable;


public class AdministrativeRight implements Serializable {

	private static final long serialVersionUID = 1L;
	private int _EcardNumber;            //һ��ͨ��
	private boolean _bookAdmin;      //ͼ�����Ȩ��
	private boolean _courseAdmin;    //�γ̹���Ȩ��
	private boolean _shopAdmin;     //�̵����Ȩ��
	private boolean _basicInfoAdmin; //�������Ȩ��
	
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
