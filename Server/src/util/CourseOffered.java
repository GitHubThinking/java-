package util;

public class CourseOffered implements java.io.Serializable {

	private static final long serialVersionUID = -3481383730680012705L;
	private int _courseNumber;     //课程编号
	private String _courseName;    //课程名称
	private double _courseCredit;  //学分
	private String _teacher; //讲师
	private String _courseTime;    //(星期-开始节数-结束节数);()...
	private String _classroom;   //课程地点 3101->J3-101
    private String _courseProperty;//课程性质（必修，选修······）
    private Boolean _state;      //已选是1,没选是0
    private int _max;            //课程人数上限
    private int _selected;			//课程当前已选人数
    
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

