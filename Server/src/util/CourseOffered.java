package util;

public class CourseOffered implements java.io.Serializable {

	private static final long serialVersionUID = -3481383730680012705L;
	private int _courseNumber;     //�γ̱��
	private String _courseName;    //�γ�����
	private double _courseCredit;  //ѧ��
	private String _teacher; //��ʦ
	private String _courseTime;    //(����-��ʼ����-��������);()...
	private String _classroom;   //�γ̵ص� 3101->J3-101
    private String _courseProperty;//�γ����ʣ����ޣ�ѡ�ޡ�������������
    private Boolean _state;      //��ѡ��1,ûѡ��0
    private int _max;            //�γ���������
    private int _selected;			//�γ̵�ǰ��ѡ����
    
    public CourseOffered(){
    	_state = false;
    }
    
	public int getCourseNumber() {
		return _courseNumber;
	}
	public void setCourseNumber(int _courseNumber) {
		this._courseNumber = _courseNumber;
	}
	public String getCourseName() {
		return _courseName;
	}
	public void setCourseName(String _courseName) {
		this._courseName = _courseName;
	}
	public double getCourseCredit() {
		return _courseCredit;
	}
	public void setCourseCredit(double _courseCredit) {
		this._courseCredit = _courseCredit;
	}
	public String getTeacher() {
		return _teacher;
	}
	public void setTeacher(String _courseTeacher) {
		this._teacher = _courseTeacher;
	}
	public String getCourseTime() {
		return _courseTime;
	}
	public void setCourseTime(String _courseTime) {
		this._courseTime = _courseTime;
	}
	public String getClassroom() {
		return _classroom;
	}
	public void setClassroom(String _coursePlace) {
		this._classroom = _coursePlace;
	}
	
	public String getCourseProperty() {
		return _courseProperty;
	}
	public void setCourseProperty(String _courseCharacter) {
		this._courseProperty = _courseCharacter;
	}
	public Boolean getState() {
		return _state;
	}
	public void setState(Boolean _state) {
		this._state = _state;
	}
	public int getMax() {
		return _max;
	}
	public void setMax(int _max) {
		this._max = _max;
	}
	public int getSelected() {
		return _selected;
	}
	public void setSelected(int _now) {
		this._selected = _now;
	}
}

