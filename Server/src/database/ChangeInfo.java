package database;

//import java.io.File; //for test
import java.sql.Connection;
//import java.sql.DriverManager; //for test
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
//import java.util.Properties; //for test

import util.BasicInfo;
import util.BookInLib;
import util.Commodity;
import util.CourseOffered;

public class ChangeInfo {
	// private JPanel adminPane;
	private PreparedStatement stmt = null;

	/* 基本信息管理 */

	public int addBasicInfo(Connection conn, int EcardNumber, String stuNumber, String nameString, String nameSpelling,
			int gender, Date birthday, String college, String major, String picturePath, String Email, int identity,
			/* String password, */String IDNumber, String telephoneNumber, String homeAddress) {

		int statecode = 0;
		try {
			stmt = conn.prepareStatement("INSERT INTO BasicInfo values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setInt(1, EcardNumber);
			stmt.setString(2, stuNumber);
			stmt.setString(3, nameString);
			stmt.setString(4, nameSpelling);
			stmt.setInt(5, gender);
			java.sql.Date sqlDate = new java.sql.Date(birthday.getTime());
			stmt.setDate(6, sqlDate);
			stmt.setString(7, college);
			stmt.setString(8, major);
			stmt.setString(9, picturePath);
			stmt.setString(10, Email);
			stmt.setInt(11, identity);
			stmt.setString(12, "000000");
			stmt.setString(13, IDNumber);
			stmt.setString(14, telephoneNumber);
			stmt.setString(15, homeAddress);

			stmt.executeUpdate();

			statecode = 1101;
		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}
		return statecode;
	}

	public int deleteBasicInfo(Connection conn, int EcardNumber) {
		int statecode = 0;
		try {
			stmt = conn.prepareStatement("DELETE FROM BasicInfo WHERE EcardNumber = ? ");
			stmt.setInt(1, EcardNumber);
			stmt.executeUpdate();

			statecode = 1101;
		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}
		return statecode;
	}

	public int listAllBasicInfo(Connection conn, ArrayList<BasicInfo> all) {
		int statecode = 0;

		try {
			stmt = conn.prepareStatement("SELECT * FROM BasicInfo");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				BasicInfo info = new BasicInfo();
				info.setEcardNumber(rs.getInt("EcardNumber"));
				info.setStuNumber(rs.getString("stuNumber"));
				info.setNameString(rs.getString("nameString"));
				info.setNameSpelling(rs.getString("nameSpelling"));
				info.setGender(rs.getInt("gender"));
				info.setBirthday(rs.getDate("birthday"));
				info.setCollege(rs.getString("college"));
				info.setMajor(rs.getString("major"));
				info.setPicturePath(rs.getString("picturePath"));
				info.setEmail(rs.getString("Email"));
				info.setIdentity(rs.getInt("identity"));
				info.setIDNumber(rs.getString("IDNumber"));
				info.setTelephone(rs.getString("telephoneNumber"));
				info.setHomeAddress(rs.getString("homeAddress"));
				all.add(info);
			}

			statecode = 1101;

		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}

		return statecode;
	}

	/* 图书管理 */

	public int addBook(Connection conn, String bookNumber, String bookTitle, String author, String authorCountry,
			String pulisher, ArrayList<String> tabs, String place, int total) {
		int statecode = 0;
		try {
			stmt = conn.prepareStatement("INSERT INTO BookInLib values(?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, bookNumber);
			stmt.setString(2, bookTitle);
			stmt.setString(3, author);
			stmt.setString(4, authorCountry);
			stmt.setString(5, pulisher);

			java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
			stmt.setDate(6, currentDate);

			String temp = "";
			int size = tabs.size();
			for (int i = 0; i < size; ++i) {
				temp += tabs.get(i);
			}
			stmt.setString(7, temp);

			stmt.setString(8, place);
			stmt.setInt(9, total);
			stmt.setInt(10, total);// new book total=remain.
			stmt.executeUpdate();

			statecode = 1101;
		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}
		return statecode;
	}

	public int deleteBook(Connection conn, String bookNumber) {
		int statecode = 0;
		try {
			stmt = conn.prepareStatement("DELETE FROM BookInLib WHERE bookNumber = ? ");
			stmt.setString(1, bookNumber);
			stmt.executeUpdate();
			statecode = 1101;
		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}
		return statecode;
	}

	public int listAllBook(Connection conn, ArrayList<BookInLib> all) {
		int statecode = 0;
		// all = new ArrayList<BookInLib>();

		try {
			stmt = conn.prepareStatement("SELECT * FROM BookInLib");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				BookInLib aBook = new BookInLib();
				aBook.setbookNumber(rs.getString("bookNumber"));
				aBook.setbookTitle(rs.getString("bookTitle"));
				aBook.setAuthor(rs.getString("author"));
				aBook.setAuthorCountry(rs.getString("authorCountry"));
				aBook.setPublisher(rs.getString("Publisher"));
				aBook.setPlace(rs.getString("Place"));
				String[] temp = rs.getString("tabs").split(";");
				ArrayList<String> ttt = new ArrayList<String>();
				for (int i = 0; i < temp.length; i++)
					ttt.add(temp[i]);
				aBook.setTabs(ttt);
				aBook.setTotal(rs.getInt("total"));
				aBook.setRemain(rs.getInt("remain"));
				// aBook.setTabs(rs.get);
				all.add(aBook);
			}

			statecode = 1101;

		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}

		return statecode;
	}

	/* 课程管理 */

	// 参数courseCredit指学分，courseProperty指课程性质（必修，选修······），state表示课程是否选满（未满，已满）
	public int addCourse(Connection conn, int courseNumber, String courseName, double courseCredit, String teacher,
			String courseTime, String classroom, String courseProperty, /* boolean state, */int max) {
		int statecode = 0;
		try {
			stmt = conn.prepareStatement("INSERT INTO CourseOffered values(?,?,?,?,?,?,?,?,?,?)");
			stmt.setInt(1, courseNumber);
			stmt.setString(2, courseName);
			stmt.setDouble(3, courseCredit);
			stmt.setString(4, teacher);
			stmt.setString(5, courseTime);
			stmt.setString(6, classroom);
			stmt.setString(7, courseProperty);
			stmt.setBoolean(8, false);
			stmt.setInt(9, max);
			stmt.setInt(10, 0);
			stmt.executeUpdate();

			statecode = 1101;
		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}
		return statecode;
	}

	public int deleteCourse(Connection conn, int courseNumber) {
		int statecode = 0;
		try {
			stmt = conn.prepareStatement("DELETE FROM CourseOffered WHERE courseNumber = ? ");
			stmt.setInt(1, courseNumber);
			stmt.executeUpdate();

			statecode = 1101;
		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}
		return statecode;
	}

	public int listAllCourse(Connection conn, ArrayList<CourseOffered> all) {
		int statecode = 0;
		// ArrayList<CourseOffered> all = new ArrayList<CourseOffered>();

		try {
			stmt = conn.prepareStatement("SELECT * FROM CourseOffered");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				CourseOffered c = new CourseOffered();
				c.setCourseNumber(rs.getInt("courseNumber"));
				c.setCourseName(rs.getString("courseName"));
				c.setCourseCredit(rs.getDouble("courseCredit"));
				c.setTeacher(rs.getString("teacher"));
				c.setCourseTime(rs.getString("courseTime"));
				c.setClassroom(rs.getString("classroom"));
				c.setCourseProperty(rs.getString("courseProperty"));
				c.setState(rs.getBoolean("state"));
				c.setMax(rs.getInt("max"));
				c.setSelected(rs.getInt("selected"));

				all.add(c);
			}

			statecode = 1101;

		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}

		return statecode;
	}

	/* 商品管理 */

	// commo(Commodity,商品简写),参数brand（商标名，eg.农夫山泉），product（产品，eg.饮用水），stock（库存量）
	public int addCommo(Connection conn, int commoID, String brand, String product, double price, String briefInfo,
			String picture, int stock) {
		int statecode = 0;
		try {
			stmt = conn.prepareStatement("INSERT INTO Commodity values(?,?,?,?,?,?,?)");
			stmt.setInt(1, commoID);
			stmt.setString(2, brand);
			stmt.setString(3, product);
			stmt.setDouble(4, price);
			stmt.setString(5, briefInfo);
			stmt.setString(6, picture);
			stmt.setInt(7, stock);

			stmt.executeUpdate();

			statecode = 1101;
		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}
		return statecode;
	}

	public int deleteCommo(Connection conn, int commoID) {
		int statecode = 0;
		try {
			stmt = conn.prepareStatement("DELETE FROM Commodity WHERE commoID = ? ");
			stmt.setInt(1, commoID);
			stmt.execute();

			statecode = 1101;
		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}
		return statecode;
	}

	public int updateCommo(Connection conn, Commodity commo) {
		int statecode = 0;
		try {
			stmt = conn.prepareStatement(
					"Update Commodity set brand = ?,product = ?,price = ?,briefInfo = ?,picture = ?,stock = ? WHERE commoID = ? ");
			stmt.setString(1, commo.getBrand());
			stmt.setString(2, commo.getProduct());
			stmt.setDouble(3, commo.getPrice());
			stmt.setString(4, commo.getBriefInfo());
			stmt.setString(5, commo.getPicturePath());
			stmt.setInt(6, commo.getStock());
			stmt.setInt(7, commo.getID());
			stmt.executeUpdate();
			statecode = 1101;
		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}
		return statecode;
	}

	public int listAllCommo(Connection conn, ArrayList<Commodity> all) {
		int statecode = 0;
		// all = new ArrayList<Commodity>();

		try {
			stmt = conn.prepareStatement("SELECT * FROM Commodity");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Commodity commo = new Commodity();
				commo.setID(rs.getInt("commoID"));
				commo.setBrand(rs.getString("brand"));
				commo.setProduct(rs.getString("product"));
				commo.setPrice(rs.getDouble("price"));
				commo.setBriefInfo(rs.getString("briefInfo"));
				commo.setPicturePath(rs.getString("picture"));
				commo.setStock(rs.getInt("stock"));

				all.add(commo);
			}

			statecode = 1101;

		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}

		return statecode;
	}
}
