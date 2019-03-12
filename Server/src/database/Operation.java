package database;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;

import util.BasicInfo;
import util.BookInLib;
import util.ByteArray;
//import util.AdministrativeRight;
import util.LibraryRecord;
import util.CourseOffered;
import util.EcardInfo;
import util.EcardRecord;
import util.Commodity;
import util.Message;
//import util.User;
import util.ShoppingRecord;
import util.ShoppingCart;
import util.Email;

//import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class Operation {
	private PreparedStatement stmt = null;

	// ��½
	public int Login(Connection conn, Message message) {
		int Ecard = message.getUser().getEcardNumber();
		int statecode = 0;
		String password = message.getUser().getPassword();
		try {
			stmt = conn.prepareStatement("select password,identity,nameString from BasicInfo where EcardNumber  = ?");
			System.out.println(Ecard);
			System.out.println(message.getUser().getPassword());
			stmt.setInt(1, Ecard);
			ResultSet re = stmt.executeQuery();
			re.next();
			if (re.getString(1).equals(password)) {
				System.out.println("��½�ɹ�");
				statecode = re.getInt(2);// ?
				message.setData(re.getString(3));

//				stmt = conn.prepareStatement("select * from AdministrativeRight where EcardNumber  = ?");
//				stmt.setInt(1, Ecard);
//				ResultSet rs = stmt.executeQuery();
//				rs.next();
//				ArrayList<String> AuthorityList = message.getAdmin();
//				AuthorityList.add(String.valueOf(rs.getString(2)));
//				AuthorityList.add(String.valueOf(rs.getString(3)));
//				AuthorityList.add(String.valueOf(rs.getString(4)));
//				AuthorityList.add(String.valueOf(rs.getString(5)));

			} else {
				System.out.println("��½ʧ��");
				statecode = 1102;
			}
		} catch (

		SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}
		return statecode;
	}

	// ��ʾ�Ѿ�ѡ�˵Ŀ�
	public int selectedCourseDisplay(Connection conn, int Ecard, ArrayList<CourseOffered> courselist) {
		int statecode = 0;
		PreparedStatement stmt1 = null; // ��ΪҪ��������������Ҫ�������ݿ�׼������
		int courseNumber;
		try {
			stmt = conn.prepareStatement("select * from CourseSelected where EcardNumber = ? ");
			stmt.setInt(1, Ecard);
			ResultSet re = stmt.executeQuery();
			while (re.next()) {
				courseNumber = re.getInt(2);
				stmt1 = conn.prepareStatement("select * from CourseOffered where courseNumber = ? ");
				stmt1.setInt(1, courseNumber);
				ResultSet rs = stmt1.executeQuery();
				CourseOffered course = new CourseOffered();
				rs.next();
				course.setCourseNumber(rs.getInt(1));
				course.setCourseName(rs.getString(2));
				course.setCourseCredit(rs.getDouble(3));
				course.setTeacher(rs.getString(4));
				course.setCourseTime(rs.getString(5));
				course.setClassroom(rs.getString(6));
				course.setCourseProperty(rs.getString(7));
				courselist.add(course);
				statecode = 1101;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}
		return statecode;
	}

	// ��ʾ�ܹ�ѡ��Ŀγ��б�
	public int optionalCourseDisplay(Connection conn, int Ecard, ArrayList<CourseOffered> courselist) {
		int statecode = 1202;
		int num;
		try {
			stmt = conn.prepareStatement("select * from CourseOffered");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				CourseOffered course = new CourseOffered();
				num = rs.getInt(1);
				course.setCourseNumber(num);
				course.setCourseName(rs.getString(2));
				course.setCourseCredit(rs.getDouble(3));
				course.setTeacher(rs.getString(4));
				course.setCourseTime(rs.getString(5));
				course.setClassroom(rs.getString(6));
				course.setCourseProperty(rs.getString(7));
				course.setMax(rs.getInt(9));
				course.setSelected(rs.getInt(10));
				course.setState(true);

				stmt = conn.prepareStatement("select courseNumber from CourseSelected where EcardNumber = ?");
				stmt.setInt(1, Ecard);
				ResultSet re = stmt.executeQuery(); // ����λͬѧ��û��ѡ�����ſΣ�ѡ�˾Ͳ�����ѡ��
				try {
					while (re.next()) {
						if (re.getInt(1) == num) {
							course.setState(false);
							break;
						} /*
							 * else { course.setState(true); }
							 */
					}
				} catch (SQLException e) {
					System.out.println("��ѡ�γ�Ϊ��");
					course.setState(false);
				}

				courselist.add(course);
				statecode = 1101;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}
		return statecode;
	}

	// ѡ��γ�
	public int selectCourse(Connection conn, int Ecard, int courseNumber, Message message) {
		int statecode = 1102;
		// �γ̱��ά���飬13��5�У�������
		String cou[][] = new String[13][5];
		for (int i = 0; i < 13; ++i) {
			for (int j = 0; j < 5; ++j) {
				cou[i][j] = "";
			}
		}
		message.setData("");
		try {
			// ������ѡ�γ̵Ŀγ̱���ø�ѧ��������ѡ��Ŀγ̣�
			stmt = conn.prepareStatement("select courseNumber from CourseSelected where EcardNumber = ?");
			stmt.setInt(1, Ecard);
			ResultSet re = stmt.executeQuery();
			// ��ȥ���пγ̵ı����ҵ���Щ�γ̵�ʱ��
			while (re.next()) {
				stmt = conn.prepareStatement("select courseTime,courseName from CourseOffered where courseNumber = ?");
				stmt.setInt(1, re.getInt(1));
				ResultSet rs = stmt.executeQuery();
				rs.next();
				String[] y = rs.getString(1).split("-|;");
				String couname = rs.getString(2);
				for (int i = 0; i < y.length / 3; ++i) // һ�ܿγ�����
				{
					// ֱ����ÿһ�οε�ʱ�䣬����6-10
					for (int j = Integer.parseInt(y[3 * i + 1]); j <= Integer.parseInt(y[3 * i + 2]); ++j) {
						// -1��������ַ
						cou[j - 1][Integer.parseInt(y[3 * i]) - 1] = couname;
					}
				}
			}
			// ������Ҫѡ�Ŀγ�ʱ�����ѡ�γ̱�����ͻ���ܹ��ӱ��ж�����ͻ�γ�����
			stmt = conn.prepareStatement("select courseTime from CourseOffered where courseNumber = ?");
			stmt.setInt(1, courseNumber);
			ResultSet rz = stmt.executeQuery();
			rz.next();
			String[] y = rz.getString(1).split("-|;");
			boolean z = false;
			String s = "";
			// y������Ҫѡ������ſε�ʱ��
			for (int i = 0; i < y.length / 3; ++i) // ������ÿһ�οε�
			{
				for (int j = Integer.parseInt(y[3 * i + 1]); j <= Integer.parseInt(y[3 * i + 2]); ++j) {
					if (!message.getData().isEmpty()) {
						// x���Ѿ���ͻ�Ŀγ�����
						String[] x = message.getData().split(";");
						for (int k = 0; k < x.length; ++k) {
							// �Ա��ǲ���ͬһ�ſΣ������ظ���ӣ�
							z = cou[j - 1][Integer.parseInt(y[3 * i]) - 1].equals(x[k]);
						}
					}
					// �����ǣ���һ�ų�ͻ�Ŀ�
					if (!z) {
						s += cou[j - 1][Integer.parseInt(y[3 * i]) - 1] + ";";
						message.setData(s);
					}
				}
				z = false;
			}
			stmt = conn.prepareStatement("select max,selected from CourseOffered where courseNumber = ?");
			stmt.setInt(1, courseNumber);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			int max = rs.getInt(1);
			System.out.println(max);
			int now = rs.getInt(2);
			System.out.println(now);
			boolean overflow = (max <= now);

			String[] x = message.getData().split(";");
			if ((x.length == 0) && !overflow) {
				stmt = conn.prepareStatement("INSERT INTO CourseSelected values(?,?)");
				stmt.setInt(1, Ecard);
				stmt.setInt(2, courseNumber);
				stmt.execute();
				// ����ѡ�κ��Ŀǰѡ������
				stmt = conn.prepareStatement("select selected from CourseOffered where courseNumber = ?");
				stmt.setInt(1, courseNumber);
				ResultSet rx = stmt.executeQuery();
				rx.next();
				stmt = conn.prepareStatement("update CourseOffered set selected = ? where courseNumber = ?");
				stmt.setInt(1, rx.getInt(1) + 1);
				stmt.setInt(2, courseNumber);
				stmt.execute();

				statecode = 1101;
			} else {
				statecode = 1515;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}
		return statecode;
	}

	// ��ѡ�γ�
	public int deleteCourse(Connection conn, int Ecard, int courseNumber) {
		int statecode = 1202;
		try {

			stmt = conn.prepareStatement("DELETE FROM CourseSelected WHERE EcardNumber = ? and courseNumber = ?");
			stmt.setInt(1, Ecard);
			stmt.setInt(2, courseNumber);
			stmt.execute();

			stmt = conn.prepareStatement("select selected from CourseOffered where courseNumber = ?");
			stmt.setInt(1, courseNumber);
			ResultSet re = stmt.executeQuery();
			re.next();
			int a = re.getInt(1) - 1;

			stmt = conn.prepareStatement("update CourseOffered set selected = ? where courseNumber = ?");
			stmt.setInt(1, a);
			stmt.setInt(2, courseNumber);
			stmt.execute();

			statecode = 1101;
		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}
		return statecode;
	}

	// ��ʾһ��ͨ״̬
	public int EcardInfoDisplay(Connection conn, int Ecard, EcardInfo Ecardinfo) {
		int statecode = 1102;
		try {
			stmt = conn.prepareStatement("SELECT state, balance FROM ECardInfo WHERE EcardNumber = ? ");
			stmt.setInt(1, Ecard);
			ResultSet re = stmt.executeQuery();
			re.next();
			Ecardinfo.setState(re.getInt(1));
			Ecardinfo.setBalance(re.getDouble(2));
			statecode = 1101;
		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}
		System.out.println(Ecardinfo.getState());
		System.out.println(Ecardinfo.getBalance());
		return statecode;
	}

	// ��ʧ
	public int reportLoss(Connection conn, int Ecard) {
		int statecode = 1102;
		try {
			stmt = conn.prepareStatement("UPDATE ECardInfo SET state = 2 where EcardNumber = ? ");
			stmt.setInt(1, Ecard);
			stmt.execute();
			statecode = 1101;
		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}
		return statecode;
	}

	// ���
	public int Unfreeze(Connection conn, int Ecard) {
		int statecode = 1102;
		try {
			stmt = conn.prepareStatement("UPDATE ECardInfo SET state = 1 where EcardNumber = ?");
			stmt.setInt(1, Ecard);
			stmt.execute();
			statecode = 1101;
		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}

		return statecode;
	}

	// ��ֵ
	public int Charge(Connection conn, int Ecard, String m, String password) {
		int statecode = 0;
		double money = Double.parseDouble(m);
		double balance = 0.0;
		try {
			stmt = conn.prepareStatement("select password,identity,nameString from BasicInfo where EcardNumber  = ?");
			System.out.println(Ecard);
			stmt.setInt(1, Ecard);
			ResultSet re_ = stmt.executeQuery();
			re_.next();
			if (re_.getString(1).equals(password)) {

				stmt = conn.prepareStatement("SELECT balance FROM ECardInfo where EcardNumber = ?");
				stmt.setInt(1, Ecard);
				ResultSet re = stmt.executeQuery();
				re.next();
				stmt = conn.prepareStatement("UPDATE ECardInfo SET balance = ? where EcardNumber = ?");
				balance = re.getDouble(1);
				stmt.setDouble(1, money + balance);
				stmt.setInt(2, Ecard);
				stmt.execute();

				// ��һ��ͨ���Ѽ�¼�м���һ��ͨ��ֵ��¼
				stmt = conn.prepareStatement("insert into ECardRecord Values(?,?,?,?,?)");
				stmt.setInt(1, Ecard);
				stmt.setString(2, "��ֵ");
				stmt.setDate(3, new java.sql.Date(System.currentTimeMillis()));
				stmt.setDouble(4, money);
				stmt.setDouble(5, balance + money);
				stmt.execute();

				statecode = 1101;
			} else {
				System.out.println("���������������������");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}
		System.out.println(statecode);
		return statecode;
	}

	// һ��ͨ��ˮ��ʾ
	public int ConsumeRecord(Connection conn, int Ecard, ArrayList<EcardRecord> EcardRecordList) {
		int statecode = 1102;
		try {
			stmt = conn.prepareStatement("SELECT * FROM ECardRecord WHERE EcardNumber = ? ");
			stmt.setInt(1, Ecard);
			ResultSet re = stmt.executeQuery();
			while (re.next()) {
				EcardRecord Ecardrecord = new EcardRecord();
				Ecardrecord.setAction(re.getString(2));
				Ecardrecord.setActionDate(re.getDate(3));
				Ecardrecord.setAmount(re.getDouble(4));
				Ecardrecord.setBalance(re.getDouble(5));
				EcardRecordList.add(Ecardrecord);
			}
			for (int i = 0; i < EcardRecordList.size(); i++) {
				System.out.println(EcardRecordList.get(i).getAction());
				System.out.println(EcardRecordList.get(i).getActionDate());
				System.out.println(EcardRecordList.get(i).getAmount());
				System.out.println(EcardRecordList.get(i).getBalance());
			}
			statecode = 1101;
		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}

		return statecode;
	}

	// �����ʼ�
	public int SendEmail(Connection conn, Message message) {
		int statecode = 1102;
		try {
			stmt = conn.prepareStatement("INSERT INTO Email values(?,?,?,?,?)");
			// ResultSet re = stmt.executeQuery();
			stmt.setInt(1, message.getEcardNumber());
			stmt.setInt(2, message.getEmail().getReceiver());
			stmt.setString(3, message.getEmail().getTitle());
			stmt.setString(4, message.getEmail().getBody());
			stmt.setDate(5, new java.sql.Date(System.currentTimeMillis()));
			// stmt.setString(6, null);
			stmt.execute();
			statecode = 1101;
		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}
		return statecode;
	}

	// ��ʾ�����ʼ�
	public int ReceiveEmail(Connection conn, int Ecard, ArrayList<Email> _receiveEmail) {
		int statecode = 1102;
		try {
			stmt = conn.prepareStatement("SELECT * FROM Email WHERE receiver = ? ");
			stmt.setInt(1, Ecard);
			ResultSet re = stmt.executeQuery();
			while (re.next()) {
				Email email = new Email();
				email.setSender(re.getInt(1));
				email.setReceiver(Ecard);
				email.setTitle(re.getString(3));
				email.setBody(re.getString(4));
				email.setSendingTime(re.getDate(5));
				_receiveEmail.add(email);
			}
			statecode = 1101;
			// System.out.println(_receiveEmail.get(0).getSender());
			// System.out.println(_receiveEmail.get(0).getReceiver());
			// System.out.println(_receiveEmail.get(0).getTitle());
			// System.out.println(_receiveEmail.get(0).getBody());
			// System.out.println(_receiveEmail.get(0).getSendingTime());
		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}

		return statecode;
	}

	// ��ʾ���˵�ǰ���ļ�¼
	public int borrowDispaly(Connection conn, int EcardNum, ArrayList<LibraryRecord> borrowList) {
		int statecode = 1101;

		try {
			stmt = conn.prepareStatement("select * from LibraryRecord where EcardNumber = ?");
			stmt.setInt(1, EcardNum);
			ResultSet rs = stmt.executeQuery();
			int back;// �Ƿ�黹��־

			while (rs.next()) {
				LibraryRecord borrowedRecord = new LibraryRecord();
				back = rs.getInt("return");
				if (back == 0)// ֻ��δ�黹��ͼ�������������ǰ����
				{
					borrowedRecord.setBookNumber(rs.getString("bookNumber"));
					borrowedRecord.setbookTitle(rs.getString("bookTitle"));
					borrowedRecord.setAuthor(rs.getString("author"));
					borrowedRecord.setAuthorCountry(rs.getString("authorCountry"));
					borrowedRecord.setPublisher(rs.getString("publisher"));
					borrowedRecord.setBorrowTime(rs.getDate("borrowTime"));
					borrowedRecord.setReturnTime(rs.getDate("returnTime"));
					borrowedRecord.setRenewals(rs.getInt("renewals"));
					borrowedRecord.setIsReturn(rs.getBoolean("return"));
					borrowedRecord.setIsFined(rs.getBoolean("fined"));
				}

				borrowList.add(borrowedRecord);
				statecode = 1101;

			}

		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}
		return statecode;
	}

	// �����鼮,������鰴ťִ�иú���
	public int borrowBook(Connection conn, int EcardNum, String bookNum) {
		int statecode = 0;
		try {
			stmt = conn.prepareStatement("select * from BookInLib where bookNumber = ?");
			stmt.setString(1, bookNum);
			ResultSet rs = stmt.executeQuery();
			rs.next();

			int remain = rs.getInt("remain");

			if (remain > 0) {
				// ����ͼ���¼
				Calendar calendar = new GregorianCalendar();
				java.sql.Date sqlDate = new java.sql.Date(calendar.getTime().getTime());

				stmt = conn.prepareStatement("INSERT INTO LibraryRecord VALUES(?,?,?,?,?,?,?,?,?,?,?)");
				stmt.setInt(1, EcardNum);
				stmt.setString(2, rs.getString("bookNumber"));
				stmt.setString(3, rs.getString("bookTitle"));
				stmt.setString(4, rs.getString("author"));
				stmt.setString(5, rs.getString("authorCountry"));
				stmt.setString(6, rs.getString("publisher"));
				stmt.setDate(7, sqlDate);
				calendar.add(Calendar.MONTH, 1);
				sqlDate = new java.sql.Date(calendar.getTime().getTime());

				stmt.setDate(8, sqlDate);
				stmt.setInt(9, 0);
				stmt.setBoolean(10, false);
				stmt.setBoolean(11, false);
				stmt.executeUpdate();

				// �޸�ͼ����Ϣ���ɽ�������
				stmt = conn.prepareStatement("UPDATE BookInLib SET remain=remain-1 WHERE bookNumber=?");
				stmt.setString(1, bookNum);
				stmt.executeUpdate();
				statecode = 1101;
			} else {
				System.out.println("Sorry,all books haven been lent.");
				// ������ʾ�޿ɽ�ͼ��
			}

		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}
		return statecode;
	}

	// �����鼮
	public int renewBook(Connection conn, int EcardNum, Message message) {
		int statecode = 0;
		try {
			// �Ƴٹ黹ʱ��һ����
			stmt = conn.prepareStatement("select * from LibraryRecord where EcardNumber = ? AND bookNumber = ?");
			stmt.setInt(1, EcardNum);
			stmt.setString(2, message.getData());// ������谴ťʱ�Ѹ����bookNumber������message�е�_data����
			ResultSet rs = stmt.executeQuery();
			rs.next();

			int renewals = rs.getInt("renewals");// �õ����������Ϣ

			if (renewals < 2)// ������������ܳ���2��
			{
				Date utilDate = rs.getDate("returnTime");
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(utilDate);
				calendar.add(Calendar.MONTH, 1);
				java.sql.Date sqlDate = new java.sql.Date(calendar.getTime().getTime());
				++renewals;
				stmt = conn.prepareStatement(
						"UPDATE LibraryRecord SET returnTime = ?, renewals=? WHERE EcardNumber = ? AND bookNumber=?");
				stmt.setDate(1, sqlDate);
				stmt.setInt(2, renewals);
				stmt.setInt(3, EcardNum);
				stmt.setString(4, message.getData());
				stmt.executeUpdate();
				statecode = 1101;
				// message.setData(time);//ԭ�⣺�����µĹ黹ʱ����_data������ԭ���Ĺ黹ʱ�������String�ͣ������ڲ������ˣ���Ҫ�Ļ�������Message�����һ��Date�͵Ķ��ñ���
			} else {
				System.out.println("Sorry, you can't renew this book any more.");
				// ���浯����ʾ���޷��ٴ����裬���ڹ涨����ǰ�黹
			}

		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}

		return statecode;
	}

	// �黹�鼮
	public int returnBook(Connection conn, Integer EcardNum, String bookNum) {
		int statecode = 0;
		try {
			stmt = conn.prepareStatement(
					"Update LibraryRecord set ReturnTime = ? where EcardNumber = ? and bookNumber = ? ");
			stmt.setDate(1, new java.sql.Date(System.currentTimeMillis()));
			//
			stmt.setInt(2, EcardNum);
			stmt.setString(3, bookNum);
			stmt.execute();

			stmt = conn
					.prepareStatement("UPDATE LibraryRecord SET return = ? where EcardNumber = ? and bookNumber = ? ");
			stmt.setBoolean(1, true);
			stmt.setInt(2, EcardNum);
			stmt.setString(3, bookNum);
			stmt.execute();

			stmt.executeUpdate();

			stmt = conn.prepareStatement("UPDATE BookInLib SET remain=remain+1 WHERE bookNumber=?");
			stmt.setString(1, bookNum);
			stmt.executeUpdate();

			statecode = 1101;

		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}
		return statecode;
	}

	// �����鼮��ʹ������������
	public int searchByBookTitle(Connection conn, String data, ArrayList<BookInLib> bookList) {// data����
		int statecode = 0;
		String tabs = "";

		try {
			stmt = conn.prepareStatement("select * from BookInLib where bookTitle like ?");// ģ����ѯ
			stmt.setString(1, data + "%");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				BookInLib book = new BookInLib();
				book.setbookNumber(rs.getString("bookNumber"));
				book.setbookTitle(rs.getString("bookTitle"));
				book.setAuthor(rs.getString("author"));
				book.setAuthorCountry(rs.getString("authorCountry"));
				book.setPublisher(rs.getString("publisher"));
				book.setCollectDate(rs.getDate("collectDate"));

				String[] newstr = tabs.split(";");
				ArrayList<String> temp = new ArrayList<String>();
				for (int i = 0; i < newstr.length; ++i) {
					temp.add(newstr[i]);
				}
				book.setTabs(temp);

				book.setPlace(rs.getString("place"));
				book.setTotal(rs.getInt("total"));
				book.setRemain(rs.getInt("remain"));
				bookList.add(book);
				statecode = 1101;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}
		return statecode;
	}

	// �����鼮��ʹ����������������
	public int searchByauthor(Connection conn, String data, ArrayList<BookInLib> bookList) {
		int statecode = 0;
		String tabs = "";

		try {
			stmt = conn.prepareStatement("select * from BookInLib where author like ?");// ģ����ѯ
			stmt.setString(1, data + "%");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				BookInLib book = new BookInLib();
				book.setbookNumber(rs.getString("bookNumber"));
				book.setbookTitle(rs.getString("bookTitle"));
				book.setAuthor(rs.getString("author"));
				book.setAuthorCountry(rs.getString("authorCountry"));
				book.setPublisher(rs.getString("publisher"));
				book.setCollectDate(rs.getDate("collectDate"));

				String[] newstr = tabs.split(";");
				ArrayList<String> temp = new ArrayList<String>();
				for (int i = 0; i < newstr.length; ++i) {
					temp.add(newstr[i]);
				}
				book.setTabs(temp);

				book.setPlace(rs.getString("place"));
				book.setTotal(rs.getInt("total"));
				book.setRemain(rs.getInt("remain"));
				bookList.add(book);
				statecode = 1101;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}
		return statecode;
	}

	// ��ʷ����
	public int borrowHistory(Connection conn, int EcardNum, ArrayList<LibraryRecord> borrowHistory) {
		int statecode = 0;

		try {
			stmt = conn.prepareStatement("select * from LibraryRecord where EcardNumber = ?");
			stmt.setInt(1, EcardNum);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				LibraryRecord borrowedRecord = new LibraryRecord();
				borrowedRecord.setBookNumber(rs.getString("bookNumber"));
				borrowedRecord.setbookTitle(rs.getString("bookTitle"));
				borrowedRecord.setAuthor(rs.getString("author"));
				borrowedRecord.setAuthorCountry(rs.getString("authorCountry"));
				borrowedRecord.setPublisher(rs.getString("publisher"));
				borrowedRecord.setBorrowTime(rs.getDate("borrowTime"));
				borrowedRecord.setReturnTime(rs.getDate("returnTime"));
				borrowedRecord.setRenewals(rs.getInt("renewals"));

				borrowHistory.add(borrowedRecord);
				statecode = 1101;

			}

		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}
		return statecode;
	}

	// ��ѡ��������е���Ʒ
	public int ShoppingCartDisplay(Connection conn, int EcardNum, ArrayList<ShoppingCart> shoppingCartList,
			ArrayList<Commodity> commoList) {
		int statecode = 1102;
		int commoID;
		try {
			stmt = conn.prepareStatement("SELECT * FROM ShoppingCart WHERE EcardNumber = ? ");
			stmt.setInt(1, EcardNum);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ShoppingCart aCommo = new ShoppingCart();
				commoID = rs.getInt("commoID");
				aCommo.setCommoID(commoID);
				aCommo.setCommoAmount(rs.getInt("amountInCart"));
				shoppingCartList.add(aCommo);

				stmt = conn.prepareStatement("select * from Commodity where commoID = ? ");
				stmt.setInt(1, commoID);
				ResultSet rs1 = stmt.executeQuery();
				rs1.next();

				Commodity commo = new Commodity();
				commo.setID(rs1.getInt("commoID"));
				commo.setProduct(rs1.getString("product"));
				commo.setBrand(rs1.getString("brand"));
				commo.setPrice(rs1.getInt("price"));
				commo.setBriefInfo(rs1.getString("briefInfo"));
				commo.setPicturePath(rs1.getString("picture"));
				commo.setStock(rs1.getInt("stock"));
				commoList.add(commo);
			}
			statecode = 1101;
		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}

		return statecode;
	}

	// ��һ����Ʒ���빺�ﳵ
	public int SelectACommo(Connection conn, int EcardNum, String data) {
		int statecode = 1102;
		String[] str = data.split(";");
		try {
			// judge whether the goods exists in cart or not
			stmt = conn.prepareStatement("select amountInCart from ShoppingCart where EcardNumber = ? and commoID = ?");
			stmt.setInt(1, EcardNum);
			stmt.setInt(2, Integer.parseInt(str[0]));
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int newNumber = rs.getInt(1) + Integer.parseInt(str[1]);// ?
				stmt = conn.prepareStatement(
						"update ShoppingCart set amountInCart = ? where EcardNumber = ? and commoID = ?");
				stmt.setInt(1, newNumber);
				stmt.setInt(2, EcardNum);
				stmt.setInt(3, Integer.parseInt(str[0]));

			} else {
				stmt = conn.prepareStatement("insert into ShoppingCart values(?,?,?)");
				stmt.setInt(1, EcardNum);
				stmt.setInt(2, Integer.parseInt(str[0]));
				stmt.setInt(3, Integer.parseInt(str[1]));
			}
			stmt.executeUpdate();
			statecode = 1101;
		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}
		return statecode;
	}

	// ��һ����Ʒ�ӹ��ﳵ���Ƴ�
	public int DeleteACommo(Connection conn, int EcardNum, int commoID) {
		int statecode = 1102;
		try {
			stmt = conn.prepareStatement("select * from ShoppingCart where EcardNumber = ? and commoID = ?");
			stmt.setInt(1, EcardNum);
			stmt.setInt(2, commoID);
			ResultSet re = stmt.executeQuery();
			if (re.next()) {
				stmt = conn.prepareStatement("delete from ShoppingCart where EcardNumber = ? and commoID = ? ");
				stmt.setInt(1, EcardNum);
				stmt.setInt(2, commoID);
				stmt.executeUpdate();
				statecode = 1101;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}
		return statecode;
	}

	// �����ﳵ�е���Ʒȫ���Ƴ�
	public int DelectAllCommos(Connection conn, int EcardNum) {
		int statecode = 1102;
		try {
			stmt = conn.prepareStatement("delete from ShoppingCart where EcardNumber = ? ");
			stmt.setInt(1, EcardNum);
			stmt.executeUpdate();

			statecode = 1101;

		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}
		return statecode;
	}

	// ��ʾ�����¼
	public int ShoppingRecord(Connection conn, int EcardNum, ArrayList<Commodity> commoList,
			ArrayList<ShoppingRecord> shoppingRecordList) {
		int statecode = 1102;
		int commoID;
		try {
			stmt = conn.prepareStatement("SELECT * FROM ShoppingRecord WHERE EcradNumber = ?");
			stmt.setInt(1, EcardNum);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ShoppingRecord aShoppingRecord = new ShoppingRecord();
				commoID = rs.getInt("commoID");
				aShoppingRecord.setCommoID(commoID);
				aShoppingRecord.setPurchaseAmount(rs.getInt("purchaseAmount"));
				aShoppingRecord.setPurchaseTime(rs.getDate("purchaseTime"));
				shoppingRecordList.add(aShoppingRecord);

				stmt = conn.prepareStatement("select * from Commodity where commoID = ? ");
				stmt.setInt(1, commoID);
				ResultSet rs1 = stmt.executeQuery();
				rs1.next();

				Commodity commo = new Commodity();
				commo.setID(rs1.getInt("commoID"));
				commo.setProduct(rs1.getString("product"));
				commo.setBrand(rs1.getString("brand"));
				commo.setPrice(rs1.getInt("price"));
				commo.setBriefInfo(rs1.getString("briefInfo"));
				commo.setPicturePath(rs1.getString("picture"));
				commo.setStock(rs1.getInt("stock"));
				commoList.add(commo);
			}
			statecode = 1101;
		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}

		return statecode;
	}

	// �µ�
	public int PayForShoppingCart(Connection conn, int EcardNum) {
		int statecode = 0;
		int commoID, amountInCart;
		String product = "";
		String brand = "";
		int stock = 0;
		double price = 0, balance = 0, totalPrice = 0.0;
		boolean isFeezed = false;
		try {
			stmt = conn.prepareStatement("SELECT state FROM ECardInfo where EcardNumber = ? ");
			stmt.setInt(1, EcardNum);
			ResultSet re = stmt.executeQuery();
			re.next();
			int state = re.getInt("state");
			if (state == 1)
				isFeezed = true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (isFeezed) {
			try {
				// �����ܼ�
				stmt = conn.prepareStatement("SELECT commoID,amountInCart FROM ShoppingCart WHERE EcardNumber = ?");
				stmt.setInt(1, EcardNum);
				ResultSet re = stmt.executeQuery();
				while (re.next()) {
					commoID = re.getInt("commoID");
					amountInCart = re.getInt("amountInCart");

					stmt = conn.prepareStatement("select * from Commodity where commoID = ? ");
					stmt.setInt(1, commoID);
					ResultSet rs = stmt.executeQuery();
					rs.next();
					totalPrice += rs.getDouble("price") * amountInCart;
				}

				// �ж����
				stmt = conn.prepareStatement("SELECT balance FROM EcardInfo WHERE EcardNumber = ?");
				stmt.setInt(1, EcardNum);
				ResultSet rz = stmt.executeQuery();
				rz.next();
				balance = rz.getDouble("balance");

				if (totalPrice <= balance) {

					stmt = conn
							.prepareStatement("SELECT commoID,amountInCart FROM ShoppingCart WHERE EcardNumber = ? ");
					stmt.setInt(1, EcardNum);
					re = stmt.executeQuery();
					while (re.next()) {
						commoID = re.getInt("commoID");
						amountInCart = re.getInt("amountInCart");

						stmt = conn.prepareStatement("select * from Commodity where commoID = ? ");
						stmt.setInt(1, commoID);
						ResultSet rs = stmt.executeQuery();
						rs.next();
						product = rs.getString("product");
						brand = rs.getString("brand");
						price = rs.getDouble("price") * amountInCart;
						stock = rs.getInt("stock");

						stmt = conn.prepareStatement("update Commodity set stock = ? where commoID = ?");
						stmt.setInt(1, stock - amountInCart);
						stmt.setInt(2, commoID);
						stmt.execute();

						// ���빺���¼
						java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
						stmt = conn.prepareStatement("insert into ShoppingRecord Values(?,?,?,?)");
						stmt.setInt(1, EcardNum);
						stmt.setInt(2, commoID);
						stmt.setInt(3, amountInCart);
						stmt.setDate(4, currentDate);
						stmt.executeUpdate();

						// ����һ��ͨ���Ѽ�¼
						stmt = conn.prepareStatement("insert into EcardRecord Values(?,?,?,?,?)");
						stmt.setInt(1, EcardNum);
						stmt.setString(2, "purchase" + brand + product);
						stmt.setDate(3, currentDate);
						stmt.setInt(4, amountInCart);
						stmt.setDouble(5, balance - price);
						stmt.executeUpdate();
					}

					// һ��ͨͳһ�ۿ�
					stmt = conn.prepareStatement("Update EcardInfo set balance = balance - ? where EcardNumber = ?");
					stmt.setDouble(1, price);
					stmt.setInt(2, EcardNum);
					stmt.executeUpdate();

					// ɾ�����ﳵ��Ϣ
					stmt = conn.prepareStatement("DELETE FROM ShoppingCart WHERE EcardNumber = ?");
					stmt.setInt(1, EcardNum);
					stmt.executeUpdate();

					statecode = 1101;
				} else { // ����
					statecode = 7777;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				statecode = 1102;
			}
		}

		return statecode;
	}

	// ��Ʒ��Ϣ
	public int CommoInfoDisplay(Connection conn, ArrayList<Commodity> commoList, ArrayList<ByteArray> byteArrayList) {
		int statecode = 1102;
		//String pic = "";
		try {
			stmt = conn.prepareStatement("select * from Commodity");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Commodity commo = new Commodity();
				commo.setID(rs.getInt("commoID"));
				commo.setProduct(rs.getString("product"));
				commo.setBrand(rs.getString("brand"));
				commo.setPrice(rs.getInt("price"));
				commo.setBriefInfo(rs.getString("briefInfo"));
				commo.setPicturePath(rs.getString("picture"));
				commo.setStock(rs.getInt("stock"));

				commoList.add(commo);
//				pic = rs.getString("picture");
//				String imgPath = new File("").getAbsolutePath().replace('\\', '/') + "/pictures/goods/" + pic + ".jpg";
//				try {
//					ByteArray byteArray = new ByteArray();
//					byte[] imageData = null;
//					BufferedImage image = ImageIO.read(new FileInputStream(imgPath));
//					ByteArrayOutputStream baos = new ByteArrayOutputStream();
//					ImageIO.write(image, "jpg", baos);
//					imageData = baos.toByteArray();
//					byteArray.setImageData(imageData);
//					byteArrayList.add(byteArray);
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
			}
			statecode = 1101;
		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}
		//System.out.println(byteArrayList.size()+"hahhaha");
		return statecode;
	}

	// ���Ĺ��ﳵ�е���Ϣ
	public int ChangeShoppingCart(Connection conn, int EcardNum, String amountInCartStr) {
		int statecode = 1102;
		int commoID;
		int amountInCart;
		String[] s = amountInCartStr.split(";");
		commoID = Integer.parseInt(s[0]);
		amountInCart = Integer.parseInt(s[1]);
		try {

			stmt = conn
					.prepareStatement("update ShoppingCart set amountInCart = ? where EcardNumber = ? and commoID = ?");
			stmt.setInt(1, amountInCart);
			stmt.setInt(2, EcardNum);
			stmt.setInt(3, commoID);
			stmt.executeUpdate();
			statecode = 1101;
		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}
		return statecode;
	}

	// ��ʾ������Ϣ
	public int BasicInfoDisplay(Connection conn, int EcardNum, BasicInfo basicinfo, ByteArray byteArray) {
		int statecode = 1102;
		try {
			stmt = conn.prepareStatement("SELECT * FROM BasicInfo WHERE EcardNumber = ? ");
			stmt.setInt(1, EcardNum);
			ResultSet re = stmt.executeQuery();
			re.next();

			basicinfo.setEcardNumber(re.getInt(1));
			basicinfo.setStuNumber(re.getString(2));
			basicinfo.setNameString(re.getString(3));
			basicinfo.setNameSpelling(re.getString(4));
			basicinfo.setGender(re.getInt(5));
			basicinfo.setBirthday((Date) re.getDate(6));
			basicinfo.setCollege(re.getString(7));
			basicinfo.setMajor(re.getString(8));
			basicinfo.setPicturePath(re.getString(9));
			basicinfo.setEmail(re.getString(10));
			basicinfo.setIdentity(re.getInt(11));
			basicinfo.setTelephone(re.getString(14));
			basicinfo.setHomeAddress(re.getString(15));
			basicinfo.setIDNumber(re.getString(13));
			basicinfo.setPassword(re.getString(12));

//			String imgPath = new File("").getAbsolutePath().replace('\\', '/') + "/Server/users/" + EcardNum + ".jpg";
//			try {
//				byte[] imageData = null;
//				BufferedImage image = ImageIO.read(new FileInputStream(imgPath));
//				ByteArrayOutputStream baos = new ByteArrayOutputStream();
//				ImageIO.write(image, "jpg", baos);
//				imageData = baos.toByteArray();
//				byteArray.setImageData(imageData);
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}

			statecode = 1101;
		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}
		return statecode;
	}

	// ���»�����Ϣ
	public int UpdateBasicInfo(Connection conn, int EcardNum, BasicInfo basicinfo) {
		int statecode = 1102;
		try {
			stmt = conn.prepareStatement(
					"Update BasicInfo set Email = ?,telephoneNumber = ?,homeAddress = ?,password = ?  WHERE EcardNumber = ? ");
			stmt.setString(1, basicinfo.getEmail());
			stmt.setString(2, basicinfo.getTelephone());
			stmt.setString(3, basicinfo.getHomeAddress());
			stmt.setString(4, basicinfo.getPassword());
			stmt.setInt(5,EcardNum );
			stmt.executeUpdate();
			statecode = 1101;
		} catch (SQLException e) {
			e.printStackTrace();
			statecode = 1102;
		}
		return statecode;
	}

	// �ϴ���Ƭ
	public int UploadPhoto(Connection conn, String pic, ByteArray byteArray) {
		int statecode = 1102;
		ByteArrayInputStream bais = new ByteArrayInputStream(byteArray.getImageData());
		BufferedImage imageBuffered;
		try {
			imageBuffered = ImageIO.read(bais);
			BufferedImage imageBufferedSmall = new BufferedImage(256, 256, BufferedImage.TYPE_INT_BGR);
			Graphics2D graphics = (Graphics2D) imageBufferedSmall.getGraphics();
			graphics.drawImage(imageBuffered, 0, 0, 256, 256, null);
			graphics.dispose();
			imageBufferedSmall.flush();

			String newImgPath = new File("").getAbsolutePath().replace('\\', '/') + "/pictures/people/";
			ImageIO.write(imageBufferedSmall, "jpg", new File(newImgPath + pic + ".jpg"));
			statecode = 1101;
		} catch (IOException e) {
			e.printStackTrace();
			statecode = 1102;
		}

		return statecode;
	}

}
