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
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(sock.getInputStream()));// 从客户端获取信息的流
			ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());// 向客户端发送信息的流

			Message message = (Message) ois.readObject();// 把客户端发过来的流读取成对象形式
			System.out.println("接收到消息类型" + message.getType());
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

					// 登陆
					case 1201:
						message.setType(op.Login(conn, message));
						break;

					/* 基本信息（学籍）模块 */
					// 查看个人基本信息
					case 1301:
						message.setType(op.BasicInfoDisplay(conn, message.getEcardNumber(), message.getBasicInfo(),
								message.getByteArray()));
						break;

					// 更新个人基本信息
					case 1302:
						message.setType(op.UpdateBasicInfo(conn, message.getEcardNumber(), message.getBasicInfo()));
						break;

					// 上传照片
					case 1303:
						message.setType(op.UploadPhoto(conn, message.getBasicInfo().getPicturePath(), message.getByteArray()));
						break;

					/* 图书模块 */
					// 显示已借图书信息
					case 1401:
						message.setType(
								op.borrowDispaly(conn, message.getEcardNumber(), message.getLibraryRecordList()));
						break;

					// 续借图书
					case 1402:
						message.setType(op.renewBook(conn, message.getEcardNumber(), message));
//						message.setType(op.borrowBook(conn, message.getEcardNumber(), message.getData()));
						break;

					// 还书
					case 1403:
						message.setType(op.returnBook(conn, message.getEcardNumber(), message.getData()));
						break;

					// 显示借书记录
					case 1404:
						message.setType(
								op.borrowHistory(conn, message.getEcardNumber(), message.getLibraryRecordList()));
						break;
						// 根据书名查找
					case 1405:
						message.setType(op.searchByBookTitle(conn, message.getData(), message.getBooklist()));
						break;

					// 根据作者姓名查找
					case 1406:
						message.setType(op.searchByauthor(conn, message.getData(), message.getBooklist()));
						break;

					// 借书
					case 1407:
						message.setType(op.borrowBook(conn, message.getEcardNumber(), message.getData()));
						break;

					/* 选课模块 */
					// 显示本学期课程 、显示已选课程
					case 1501:
						message.setType(
								op.selectedCourseDisplay(conn, message.getEcardNumber(), message.getCourseList()));
						break;

					// 显示可选全部课程
					case 1502:
						message.setType(
								op.optionalCourseDisplay(conn, message.getEcardNumber(), message.getCourseList()));
						break;

					// 预选某门课
					case 1503:
						message.setType(op.selectCourse(conn, message.getEcardNumber(),
								Integer.parseInt(message.getData()), message));
						break;

					// 退选某节课
					case 1504:
						message.setType(
								op.deleteCourse(conn, message.getEcardNumber(), Integer.parseInt(message.getData())));
						break;

					/* 一卡通模块 */
					// 显示一卡通基本信息
					case 1601:
						message.setType(op.EcardInfoDisplay(conn, message.getEcardNumber(), message.getEcardInfo()));
						break;

					// 挂失
					case 1602:
						message.setType(op.reportLoss(conn, message.getEcardNumber()));
						break;

					// 解冻
					case 1603:
						message.setType(op.Unfreeze(conn, message.getEcardNumber()));
						break;

					// 充钱
					case 1604:
						message.setType(op.Charge(conn, message.getEcardNumber(), message.getData(),
								message.getTempPassword()));
						break;

					// 查询消费记录
					case 1605:
						message.setType(op.ConsumeRecord(conn, message.getEcardNumber(), message.getEcardRecordList()));
						break;

					/* 邮件模块 */
					// 写邮件
					case 1701:
						message.setType(op.SendEmail(conn, message));
						break;

					// 查看邮件
					case 1702:
						message.setType(op.ReceiveEmail(conn, message.getEcardNumber(), message.getEmailList()));
						break;

					/* 商店模块 */
					// 显示购物车信息
					case 1801:
						message.setType(op.ShoppingCartDisplay(conn, message.getEcardNumber(),
								message.getShoppingCartList(), message.getCommoList()));
						break;

					// 查看购物历史
					case 1802:
						message.setType(op.ShoppingRecord(conn, message.getEcardNumber(), message.getCommoList(),
								message.getShoppingRecord()));
						break;

					// 清空购物车
					case 1803:
						message.setType(op.DelectAllCommos(conn, message.getEcardNumber()));
						break;

					// 选择某样商品
					case 1804:
						message.setType(op.SelectACommo(conn, message.getEcardNumber(), message.getData()));
						break;

					// 退选某样商品
					case 1805:
						message.setType(
								op.DeleteACommo(conn, message.getEcardNumber(), Integer.parseInt(message.getData())));
						break;

					// 购买商品
					case 1806:
						message.setType(op.PayForShoppingCart(conn, message.getEcardNumber()));
						break;

					// 修改件数
					case 1807:
						message.setType(op.ChangeShoppingCart(conn, message.getEcardNumber(), message.getData()));
						break;

					// 显示商品信息
					case 1808:
						message.setType(op.CommoInfoDisplay(conn, message.getCommoList(), message.getByteArrayList()));
						break;

					/* 管理员模块 */
					// 添加基本信息
					case 2101:
						message.setType(admin.addBasicInfo(conn, message.getBasicInfo().getEcardNumber(),
								message.getBasicInfo().getStuNumber(), message.getBasicInfo().getNameString(),
								message.getBasicInfo().getNameSpelling(), message.getBasicInfo().getGender(),
								message.getBasicInfo().getBirthday(), message.getBasicInfo().getCollege(),
								message.getBasicInfo().getMajor(), message.getBasicInfo()
										.getPicturePath(),
								message.getBasicInfo().getEmail(), message.getBasicInfo().getIdentity(), /*
																											 * 密码默认000000
																											 * ，这里没有密码参数
																											 */
								message.getBasicInfo().getIDNumber(), message.getBasicInfo().getTelephone(),
								message.getBasicInfo().getHomeAddress()));
						break;

					// 删除基本信息
					case 2102:
						message.setType(admin.deleteBasicInfo(conn, message.getEcardNumber()));
						break;

					// 提取全部基本信息
					case 2103:
						message.setType(admin.listAllBasicInfo(conn,message.getAllBasicInfo()));
						break;

					// 添加图书信息
					case 2201:
						message.setType(
								admin.addBook(conn, message.getbook().getbookNumber(), message.getbook().getbookTitle(),
										message.getbook().getAuthor(), message.getbook().getAuthorCountry(),
										message.getbook().getPublisher(), message.getbook().getTabs(),
										message.getbook().getPlace(), message.getbook().getTotal()));
						break;

					// 删除图书信息
					case 2202:
						message.setType(admin.deleteBook(conn, message.getData()));
						break;

					// 提取全部图书信息
					case 2203:
						message.setType(admin.listAllBook(conn,message.getBooklist()));
						break;

					// 添加课程信息
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

					// 删除课程信息
					case 2302:
						message.setType(admin.deleteCourse(conn, Integer.parseInt(message.getData())));
						break;

					// 提取全部课程信息
					case 2303:
						message.setType(admin.listAllCourse(conn, message.getCourseList()));
						break;

					// 添加商品信息
					case 2401:
						message.setType(admin.addCommo(conn, message.getCommo().getID(), message.getCommo().getBrand(),
								message.getCommo().getProduct(), message.getCommo().getPrice(),
								message.getCommo().getBriefInfo(), message.getCommo().getPicturePath(),
								message.getCommo().getStock()));
						break;

					// 删除商品信息
					case 2402:
						message.setType(admin.deleteCommo(conn, message.getCommoID()));
						break;

					// 提取全部商品信息
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

			// 输入对象, 一定要flush（）
			oos.writeObject(message);// 用输出流把对象输出
			System.out.println("返回消息类型 " + message.getType());
			oos.flush();// 刷新输出流

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
