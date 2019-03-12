package server;

import java.io.BufferedInputStream;
//import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import database.ChangeInfo;
//import database.ChangeInfo;
import database.Operation;
import util.Message;

public class ServerThread extends Thread {
	static String hello = "From Server: Hello world";
	Socket sock;

	public ServerThread(Socket s) {
		sock = s;
	}

	public void run() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(sock.getInputStream()));// �ӿͻ��˻�ȡ��Ϣ����
			ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());// ��ͻ��˷�����Ϣ����

			Message message = (Message) ois.readObject();// �ѿͻ��˷�����������ȡ�ɶ�����ʽ
			System.out.println("���յ���Ϣ����" + message.getType());
			// System.out.println(message.getEcardNumber());

			if (message != null) {

				Operation op = new Operation();
				ChangeInfo admin = new ChangeInfo();
				Connection conn = null;

				try {
					try {
						Class.forName("com.hxtt.sql.access.AccessDriver");
						Properties prop = new Properties();
						prop.put("charSet", "GB2312");
						conn = DriverManager.getConnection("jdbc:Access:///E:/vCampus1.1.accdb", prop);
					} catch (Exception e) {
						e.printStackTrace();
					}

					switch (message.getType()) {

					// ��½
					case 1201:
						message.setType(op.Login(conn, message));
						break;

					/* ������Ϣ��ѧ����ģ�� */
					// �鿴���˻�����Ϣ
					case 1301:
						message.setType(op.BasicInfoDisplay(conn, message.getEcardNumber(), message.getBasicInfo(),
								message.getByteArray()));
						break;

					// ���¸��˻�����Ϣ
					case 1302:
						message.setType(op.UpdateBasicInfo(conn, message.getEcardNumber(), message.getBasicInfo()));
						break;

					// �ϴ���Ƭ
					case 1303:
						message.setType(op.UploadPhoto(conn, message.getBasicInfo().getPicturePath(), message.getByteArray()));
						break;

					/* ͼ��ģ�� */
					// ��ʾ�ѽ�ͼ����Ϣ
					case 1401:
						message.setType(
								op.borrowDispaly(conn, message.getEcardNumber(), message.getLibraryRecordList()));
						break;

					// ����ͼ��
					case 1402:
						message.setType(op.renewBook(conn, message.getEcardNumber(), message));
//						message.setType(op.borrowBook(conn, message.getEcardNumber(), message.getData()));
						break;

					// ����
					case 1403:
						message.setType(op.returnBook(conn, message.getEcardNumber(), message.getData()));
						break;

					// ��ʾ�����¼
					case 1404:
						message.setType(
								op.borrowHistory(conn, message.getEcardNumber(), message.getLibraryRecordList()));
						break;
						// ������������
					case 1405:
						message.setType(op.searchByBookTitle(conn, message.getData(), message.getBooklist()));
						break;

					// ����������������
					case 1406:
						message.setType(op.searchByauthor(conn, message.getData(), message.getBooklist()));
						break;

					// ����
					case 1407:
						message.setType(op.borrowBook(conn, message.getEcardNumber(), message.getData()));
						break;

					/* ѡ��ģ�� */
					// ��ʾ��ѧ�ڿγ� ����ʾ��ѡ�γ�
					case 1501:
						message.setType(
								op.selectedCourseDisplay(conn, message.getEcardNumber(), message.getCourseList()));
						break;

					// ��ʾ��ѡȫ���γ�
					case 1502:
						message.setType(
								op.optionalCourseDisplay(conn, message.getEcardNumber(), message.getCourseList()));
						break;

					// Ԥѡĳ�ſ�
					case 1503:
						message.setType(op.selectCourse(conn, message.getEcardNumber(),
								Integer.parseInt(message.getData()), message));
						break;

					// ��ѡĳ�ڿ�
					case 1504:
						message.setType(
								op.deleteCourse(conn, message.getEcardNumber(), Integer.parseInt(message.getData())));
						break;

					/* һ��ͨģ�� */
					// ��ʾһ��ͨ������Ϣ
					case 1601:
						message.setType(op.EcardInfoDisplay(conn, message.getEcardNumber(), message.getEcardInfo()));
						break;

					// ��ʧ
					case 1602:
						message.setType(op.reportLoss(conn, message.getEcardNumber()));
						break;

					// �ⶳ
					case 1603:
						message.setType(op.Unfreeze(conn, message.getEcardNumber()));
						break;

					// ��Ǯ
					case 1604:
						message.setType(op.Charge(conn, message.getEcardNumber(), message.getData(),
								message.getTempPassword()));
						break;

					// ��ѯ���Ѽ�¼
					case 1605:
						message.setType(op.ConsumeRecord(conn, message.getEcardNumber(), message.getEcardRecordList()));
						break;

					/* �ʼ�ģ�� */
					// д�ʼ�
					case 1701:
						message.setType(op.SendEmail(conn, message));
						break;

					// �鿴�ʼ�
					case 1702:
						message.setType(op.ReceiveEmail(conn, message.getEcardNumber(), message.getEmailList()));
						break;

					/* �̵�ģ�� */
					// ��ʾ���ﳵ��Ϣ
					case 1801:
						message.setType(op.ShoppingCartDisplay(conn, message.getEcardNumber(),
								message.getShoppingCartList(), message.getCommoList()));
						break;

					// �鿴������ʷ
					case 1802:
						message.setType(op.ShoppingRecord(conn, message.getEcardNumber(), message.getCommoList(),
								message.getShoppingRecord()));
						break;

					// ��չ��ﳵ
					case 1803:
						message.setType(op.DelectAllCommos(conn, message.getEcardNumber()));
						break;

					// ѡ��ĳ����Ʒ
					case 1804:
						message.setType(op.SelectACommo(conn, message.getEcardNumber(), message.getData()));
						break;

					// ��ѡĳ����Ʒ
					case 1805:
						message.setType(
								op.DeleteACommo(conn, message.getEcardNumber(), Integer.parseInt(message.getData())));
						break;

					// ������Ʒ
					case 1806:
						message.setType(op.PayForShoppingCart(conn, message.getEcardNumber()));
						break;

					// �޸ļ���
					case 1807:
						message.setType(op.ChangeShoppingCart(conn, message.getEcardNumber(), message.getData()));
						break;

					// ��ʾ��Ʒ��Ϣ
					case 1808:
						message.setType(op.CommoInfoDisplay(conn, message.getCommoList(), message.getByteArrayList()));
						break;

					/* ����Աģ�� */
					// ��ӻ�����Ϣ
					case 2101:
						message.setType(admin.addBasicInfo(conn, message.getBasicInfo().getEcardNumber(),
								message.getBasicInfo().getStuNumber(), message.getBasicInfo().getNameString(),
								message.getBasicInfo().getNameSpelling(), message.getBasicInfo().getGender(),
								message.getBasicInfo().getBirthday(), message.getBasicInfo().getCollege(),
								message.getBasicInfo().getMajor(), message.getBasicInfo()
										.getPicturePath(),
								message.getBasicInfo().getEmail(), message.getBasicInfo().getIdentity(), /*
																											 * ����Ĭ��000000
																											 * ������û���������
																											 */
								message.getBasicInfo().getIDNumber(), message.getBasicInfo().getTelephone(),
								message.getBasicInfo().getHomeAddress()));
						break;

					// ɾ��������Ϣ
					case 2102:
						message.setType(admin.deleteBasicInfo(conn, message.getEcardNumber()));
						break;

					// ��ȡȫ��������Ϣ
					case 2103:
						message.setType(admin.listAllBasicInfo(conn,message.getAllBasicInfo()));
						break;

					// ���ͼ����Ϣ
					case 2201:
						message.setType(
								admin.addBook(conn, message.getbook().getbookNumber(), message.getbook().getbookTitle(),
										message.getbook().getAuthor(), message.getbook().getAuthorCountry(),
										message.getbook().getPublisher(), message.getbook().getTabs(),
										message.getbook().getPlace(), message.getbook().getTotal()));
						break;

					// ɾ��ͼ����Ϣ
					case 2202:
						message.setType(admin.deleteBook(conn, message.getData()));
						break;

					// ��ȡȫ��ͼ����Ϣ
					case 2203:
						message.setType(admin.listAllBook(conn,message.getBooklist()));
						break;

					// ��ӿγ���Ϣ
					case 2301:
						message.setType(admin.addCourse(conn, message.getCourse().getCourseNumber(),
								message.getCourse().getCourseName(), message.getCourse().getCourseCredit(),
								message.getCourse().getTeacher(), message.getCourse()
										.getCourseTime(),
								message.getCourse().getClassroom(), message.getCourse().getCourseProperty(), /*
																												 * message
																												 * .
																												 * getCourse
																												 * ().
																												 * getState
																												 * (),
																												 */
								message.getCourse().getMax()));
						break;

					// ɾ���γ���Ϣ
					case 2302:
						message.setType(admin.deleteCourse(conn, Integer.parseInt(message.getData())));
						break;

					// ��ȡȫ���γ���Ϣ
					case 2303:
						message.setType(admin.listAllCourse(conn, message.getCourseList()));
						break;

					// �����Ʒ��Ϣ
					case 2401:
						message.setType(admin.addCommo(conn, message.getCommo().getID(), message.getCommo().getBrand(),
								message.getCommo().getProduct(), message.getCommo().getPrice(),
								message.getCommo().getBriefInfo(), message.getCommo().getPicturePath(),
								message.getCommo().getStock()));
						break;

					// ɾ����Ʒ��Ϣ
					case 2402:
						message.setType(admin.deleteCommo(conn, message.getCommoID()));
						break;

					// ��ȡȫ����Ʒ��Ϣ
					case 2403:
						message.setType(admin.listAllCommo(conn,message.getCommoList()));
						break;
						
					case 2404:
						message.setType(admin.updateCommo(conn, message.getCommo()));
						break;

					}
				} finally {
					if (null != conn) {
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}

			// �������, һ��Ҫflush����
			oos.writeObject(message);// ��������Ѷ������
			System.out.println("������Ϣ���� " + message.getType());
			oos.flush();// ˢ�������

			oos.close();
			ois.close();
			sock.close();
		} catch (IOException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
