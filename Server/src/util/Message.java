package util;

import java.util.ArrayList;

import util.ByteArray;

public class Message implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	static public String _IP; // IP地址
	private int _EcardNumber; // 一卡通号
	private int _type; // 操作码
	private User _user; // 登陆信息
	private BasicInfo _basicInfo; // 个人基本信息
	private EcardInfo _EcardInfo; // 一卡通信息
	private BookInLib _book; // 单本书
	private CourseOffered _course; // 单个课程
	private Commodity _commo; // 单个商品
	private Email _sendEmail;
	private ByteArray _byteArray;
	private String _tempPassword; // 零时的密码，用于充钱时校验密码

	private int _stuNum; // 学生总数
	// private String
	private String _bookNum; // 单本书编号（用于管理员删除）
	private int _courseNum; // 课程编号（用于管理员删除）
	private int _commoID;// 商品编号（用于管理员删除）

	private String _data;// 为辅助实现功能所留的变量（图书馆借书还书的编号等）
	// private AdministrativeRight _Admin;

	private ArrayList<LibraryRecord> _libraryRecordList; // 图书记录链表
	private ArrayList<BookInLib> _bookList; // 图书链表
	private ArrayList<BookInLib> _allBooks; // 所有书链表
	private ArrayList<CourseOffered> _courseList; // 课程链表
	private ArrayList<EcardRecord> _EcardRecordList; // 一卡通信息链表
	private ArrayList<ShoppingCart> _shoppingCartList; // 购物车链表
	private ArrayList<Commodity> _commoList; // 商品链表
	private ArrayList<ShoppingRecord> _shoppingRecordList; // 消费记录链表
	private ArrayList<String> _admin; // 权限链表
	private ArrayList<BasicInfo> _allBasicInfos; // 基本信息链表
	private ArrayList<CourseOffered> _allCourse; // 所有课程链表
	private ArrayList<ByteArray> _byteArrayList;

	private ArrayList<String> _stringList;//
	private ArrayList<Email> _receiveEmailList;

	public Message() {
		_basicInfo = new BasicInfo();
		_bookList = new ArrayList<BookInLib>();
		_courseList = new ArrayList<CourseOffered>();
		_EcardInfo = new EcardInfo();
		setCommoList(new ArrayList<Commodity>());
		_shoppingCartList = new ArrayList<ShoppingCart>();
		_shoppingRecordList = new ArrayList<ShoppingRecord>();
		_libraryRecordList = new ArrayList<LibraryRecord>();
		_EcardRecordList = new ArrayList<EcardRecord>();
		_admin = new ArrayList<String>();
		_stringList = new ArrayList<String>();
		_receiveEmailList = new ArrayList<Email>();
		_byteArrayList = new ArrayList<ByteArray>();
		_allBasicInfos = new ArrayList<BasicInfo>();
		_commoList=new ArrayList<Commodity>();

	}

	public Message(int Ecard) {
		_EcardNumber = Ecard;

		_basicInfo = new BasicInfo();
		_bookList = new ArrayList<BookInLib>();
		_courseList = new ArrayList<CourseOffered>();
		_EcardInfo = new EcardInfo();
		setCommoList(new ArrayList<Commodity>());
		_shoppingCartList = new ArrayList<ShoppingCart>();
		_shoppingRecordList = new ArrayList<ShoppingRecord>();
		_libraryRecordList = new ArrayList<LibraryRecord>();
		_EcardRecordList = new ArrayList<EcardRecord>();
		_admin = new ArrayList<String>();
		_stringList = new ArrayList<String>();
		_receiveEmailList = new ArrayList<Email>();
		_byteArrayList = new ArrayList<ByteArray>();
		_allBasicInfos = new ArrayList<BasicInfo>();
		_commoList=new ArrayList<Commodity>();
	}

	public void setTempPassword(String _tempPassword) {
		this._tempPassword = _tempPassword;
	}

	public String getTempPassword() {
		return this._tempPassword;
	}

	public void setEcardNumber(int data) {
		this._EcardNumber = data;
	}

	public int getEcardNumber() {
		return _EcardNumber;
	}

	public void setType(int data) {
		this._type = data;
	}

	public int getType() {
		return _type;
	}

	public void setBasicInfo(BasicInfo data) {
		this._basicInfo = data;
	}

	public BasicInfo getBasicInfo() {
		return _basicInfo;
	}

	public void setUser(User data) {
		this._user = data;
	}

	public User getUser() {
		return _user;
	}

	public String getData() {
		return _data;
	}

	public void setData(String _data) {
		this._data = _data;
	}

	public ArrayList<BookInLib> getBooklist() {
		return _bookList;
	}

	public void setBooklist(ArrayList<BookInLib> _booklist) {
		this._bookList = _booklist;
	}

	public ArrayList<CourseOffered> getCourseList() {
		return _courseList;
	}

	public void setCourseList(ArrayList<CourseOffered> _courseList) {
		this._courseList = _courseList;
	}

	public EcardInfo getEcardInfo() {
		return _EcardInfo;
	}

	public void setEcardInfo(EcardInfo _EcardInfo) {
		this._EcardInfo = _EcardInfo;
	}

	public ArrayList<EcardRecord> getEcardRecordList() {
		return _EcardRecordList;
	}

	public void setEcardRecordList(ArrayList<EcardRecord> _EcardRecordList) {
		this._EcardRecordList = _EcardRecordList;
	}

	public ArrayList<ShoppingCart> getShoppingCartList() {
		return _shoppingCartList;
	}

	public void setShoppingCartList(ArrayList<ShoppingCart> _shoppingCartList) {
		this._shoppingCartList = _shoppingCartList;
	}

	public int getCommoID() {
		return _commoID;
	}

	public void setCommoID(int _commoID) {
		this._commoID = _commoID;
	}

	public ArrayList<Commodity> getCommoList() {
		return _commoList;
	}

	public void setCommoList(ArrayList<Commodity> _commoList) {
		this._commoList = _commoList;
	}

	public ArrayList<ShoppingRecord> getShoppingRecord() {
		return _shoppingRecordList;
	}

	public void setShoppingRecord(ArrayList<ShoppingRecord> _shoppingRecordList) {
		this._shoppingRecordList = _shoppingRecordList;
	}

	public ArrayList<LibraryRecord> getLibraryRecordList() {
		return _libraryRecordList;
	}

	public void setLibraryRecordList(ArrayList<LibraryRecord> _libraryRecordList) {
		this._libraryRecordList = _libraryRecordList;
	}

	public CourseOffered getCourse() {
		return _course;
	}

	public void setCourse(CourseOffered _course) {
		this._course = _course;
	}

	public ArrayList<String> getAdmin() {
		return _admin;
	}

	public void setAdmin(ArrayList<String> _admin) {
		this._admin = _admin;
	}

	public void setbook(BookInLib book) {
		this._book = book;
	}

	public BookInLib getbook() {
		return this._book;
	}

	public void setCommo(Commodity _commo) {
		this._commo = _commo;
	}

	public Commodity getCommo() {
		return this._commo;
	}

	public void setCourseNum(ArrayList<CourseOffered> a) {
		this._courseNum = a.size();
	}

	public int getCourseNum() {
		return this._courseNum;
	}

	public void setStudentNum(ArrayList<BasicInfo> a) {
		this._stuNum = a.size();
	}

	public int getStudentNum() {
		return this._stuNum;
	}

	public void setAllBasicInfos(ArrayList<BasicInfo> ab) {
		this._allBasicInfos = ab;
	}

	public ArrayList<BasicInfo> getAllBasicInfo() {
		return this._allBasicInfos;
	}

	public ArrayList<CourseOffered> getAllCourse() {
		return this._allCourse;
	}

	public void setAllCourse(ArrayList<CourseOffered> c) {
		this._allCourse = c;
	}

	public void setbookNum(String _bookNum) {
		this._bookNum = _bookNum;
	}

	public String getBookNum() {
		return this._bookNum;
	}

	public void setAllBook(ArrayList<BookInLib> _allBooks) {
		this._allBooks = _allBooks;
	}

	public ArrayList<BookInLib> getAllBook() {
		return this._allBooks;
	}

	public ArrayList<String> getStringList() {
		return _stringList;
	}

	public void setStringList(ArrayList<String> _stringList) {
		this._stringList = _stringList;
	}

	public ArrayList<Email> getEmailList() {
		return _receiveEmailList;
	}

	public void setEmailList(ArrayList<Email> _receiveEmailList) {
		this._receiveEmailList = _receiveEmailList;
	}

	public Email getEmail() {
		return _sendEmail;
	}

	public void setEmail(Email _sendEmail) {
		this._sendEmail = _sendEmail;
	}

	public String getIP() {
		return _IP;
	}

	public ByteArray getByteArray() {
		return _byteArray;
	}

	public void setByteArray(ByteArray _byteArray) {
		this._byteArray = _byteArray;
	}

	public ArrayList<ByteArray> getByteArrayList() {
		return _byteArrayList;
	}

	public void setByteArrayList(ArrayList<ByteArray> _byteArrayList) {
		this._byteArrayList = _byteArrayList;
	}
}
