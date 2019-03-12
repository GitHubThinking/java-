package util;

import java.util.Date;


public class LibraryRecord implements java.io.Serializable {

	private static final long serialVersionUID = 8845322462332311250L;
	private int _EcardNumber;
	private String _bookNumber;
	
	private String _bookTitle;
	private String _author;           //作者
	private String _authorCountry;    //作者国籍
	private String _publisher;  //出版社
	
	private Date _borrowTime;
	private Date _returnTime;
	private int _renewals;
	private boolean _isReturn;//是否归还
	private boolean _isFined;//是否超期被罚款
	
 	public void setEcardNumber(int _EcardNumber) {
		this._EcardNumber = _EcardNumber;
	}
	
	public int getEcardNumber() {
		return _EcardNumber;
	}
	
	public void setBookNumber(String _bookNumber) {
		this._bookNumber = _bookNumber;
	}
	
	public String getbookTitle() {
		return _bookTitle;
	}
	
	public void setbookTitle(String _bookTitle) {
		this._bookTitle = _bookTitle;
	}
	
	public String getAuthor() {
		return _author;
	}

	public void setAuthor(String _author) {
		this._author = _author;
	}

	public String getAuthorCountry() {
		return _authorCountry;
	}

	public void setAuthorCountry(String _autherCountry) {
		this._authorCountry = _autherCountry;
	}

	public String getPublisher() {
		return _publisher;
	}

	public void setPublisher(String _publisher) {
		this._publisher = _publisher;
	}
	
    public String getBookNumber() {
		return _bookNumber;
	}
	
    public void setBorrowTime(Date _borrowTime) {
		this._borrowTime = _borrowTime;
	}
	
    public Date getBorrowTime() {
		return _borrowTime;
	}
	
    public void setReturnTime(Date _returnTime) {
		this._returnTime = _returnTime;
	}
	
    public Date getReturnTime() {
		return _returnTime;
	}
	
	public void setRenewals(int _renewals) {
		this._renewals = _renewals;
	}
	
	public int getRenewals() {
		return _renewals;
	}
	
	public void setIsReturn(boolean _isReturn){
		this._isReturn=_isReturn;
	}
	
	public boolean getIsReturn(){
		return this._isReturn;
	}
	
	public void setIsFined(boolean _isFined){
		this._isFined=_isFined;
	}
	
	public boolean getIsFined(){
		return this._isFined;
	}
}
