package util;

import java.io.Serializable;
import java.util.Date;

/**
 * ���˻�����Ϣ��
 *
 * @version  1.0 30 Aug 2016
 * @author  MiracleXXX
 */
public class BasicInfo implements Serializable {
    
	private static final long serialVersionUID = 1L;
	private int _EcardNumber;      //һ��ͨ��
	private String _stuNumber;          //ѧ�Ż�ְ����
	private String _nameString;    //����
	private String _nameSpelling;  //����ƴ��
	private int _gender;       //�Ա�  0Ϊ��,1ΪŮ
	private Date _birthday;        //��������
	private String _college;        //Ժϵ
	private String _major;         //רҵ
	private String _picturePath;   //��Ƭ
	private String _Email;         //����
	private int _identity;    
	private String _password;
	//no password here,password is used by User object.
	private String _IDNumber;      //���֤��
	private String _telephone;     //�绰����
	private String _homeAddress;   //��ͥסַ
	private int _loginState;
	
	public BasicInfo() {
		setEcardNumber(0);
		setStuNumber(null);
		setNameString(null);
		setNameSpelling(null);
		setGender(0);
		setBirthday(new Date());
		setCollege(null);
		setMajor(null);
		setPicturePath(null);
		setEmail(null);
		setIdentity(1);
		//no set password
		setPassword("000000");
		setIDNumber(null);
		setTelephone(null);
		setHomeAddress(null);
		setLoginState(0);
	}
	
	public String getPassword()
	{
		return _password;
	}
	public void setPassword(String _password)
	{
		this._password=_password;
	}
	//getter and setter  
	public int getEcardNumber() {
		return _EcardNumber;
	}
	public void setEcardNumber(int _EcardNumber) {
		this._EcardNumber = _EcardNumber;
	}
	
	public String getStuNumber() {
		return _stuNumber;
	}
	public void setStuNumber(String _stuNumber) {
		this._stuNumber = _stuNumber;
	}

	public String getNameString() {
		return _nameString;
	}
	public void setNameString(String _nameString) {
		this._nameString = _nameString;
	}

	public String getNameSpelling() {
		return _nameSpelling;
	}
	public void setNameSpelling(String _nameSpelling) {
		this._nameSpelling = _nameSpelling;
	}

	public int getGender() {
		return _gender;
	}
	public void setGender(int _gender) {
		this._gender = _gender;
	}

	public Date getBirthday() {
		return _birthday;
	}
	public void setBirthday(Date _birthday) {
		this._birthday = _birthday;
	}

	public String getCollege() {
		return _college;
	}
	public void setCollege(String _college) {
		this._college = _college;
	}

	public String getMajor() {
		return _major;
	}
	public void setMajor(String _major) {
		this._major = _major;
	}

	public String getPicturePath() {
		return _picturePath;
	}
	public void setPicturePath(String _picturePath) {
		this._picturePath = _picturePath;
	}

	public String getEmail() {
		return _Email;
	}
	public void setEmail(String _Email) {
		this._Email = _Email;
	}

	public int getIdentity() {
		return _identity;
	}
	public void setIdentity(int _identity) {
		this._identity = _identity;
	}

	//no methods about password here
	
	public String getIDNumber() {
		return _IDNumber;
	}
	public void setIDNumber(String _IDNumber) {
		this._IDNumber = _IDNumber;
	}


	public String getTelephone() {
		return _telephone;
	}
	public void setTelephone(String _telephone) {
		this._telephone = _telephone;
	}

	public String getHomeAddress() {
		return _homeAddress;
	}
	public void setHomeAddress(String _homeAddress) {
		this._homeAddress = _homeAddress;
	}

	
	public int getLoginState() {
		return _loginState;
	}
	public void setLoginState(int _loginState) {
		this._loginState = _loginState;
	}
	
	
}
