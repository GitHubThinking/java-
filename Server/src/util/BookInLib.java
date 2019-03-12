package util;

import java.util.ArrayList;
import java.util.Date;

/**
 * 书本基本信息类
 *
 * @version  1.0 30 Aug 2016
 * @author  MiracleXXX
 */
public class BookInLib implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String _bookTitle;             //书籍名称
	private String _bookNumber;           //书籍编号
	private String _author;           //作者
	private String _authorCountry;    //作者国籍
	private String _publisher;  //出版社
	private Date _collectDate;        //收藏时间
	private ArrayList<String> _tabs;  //标签
	private String _place;
	private int _total;
	private int _remain;
	
	
	public BookInLib() {
		setbookTitle(null);
		setbookNumber(null);
		setAuthor(null);
		setAuthorCountry(null);
		setPublisher(null);
		setCollectDate(new Date());
		setTabs(new ArrayList<String>());
		setTotal(0);
		setRemain(0);
		
		
	}
	
	//setter and getter
	public String getbookTitle() {
		return _bookTitle;
	}
	
	public void setbookTitle(String _bookTitle) {
		this._bookTitle = _bookTitle;
	}
	
	public String getbookNumber() {
		return _bookNumber;
	}
	
	public void setbookNumber(String _bookNumber) {
		this._bookNumber = _bookNumber;
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

	public Date getCollectDate() {
		return _collectDate;
	}

	public void setCollectDate(Date _collectDate) {
		this._collectDate = _collectDate;
	}

	public ArrayList<String> getTabs() {
		return _tabs;
	}

	public void setTabs(ArrayList<String> _tabs) {
		this._tabs = _tabs;
	}

	public String getPlace() {
		return _place;
	}

	public void setPlace(String _place) {
		this._place = _place;
	}
	
	public int getTotal() {
		return _total;
	}

	public void setTotal(int _total) {
		this._total = _total;
	}
	
	public int getRemain() {
		return _remain;
	}

	public void setRemain(int _remain) {
		this._remain = _remain;
	}
	
}