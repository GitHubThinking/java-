package com.seu.vcampus.login;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import io.Client;
import util.Message;
import util.User;

import com.seu.vcampus.Special.MyFrame;
import com.seu.vcampus.studentmain.StudentMain;

import administrator.AdministratorMain;

public class Login {

	MyFrame Loginframe;
	private JTextField textField;
	private JTextField textField_1;
	// private JPanel panel;
	private User _person = new User();

	JButton btnLogin = new JButton();
	JButton btnClose = new JButton();
	JButton btnSmall = new JButton();

	// 窗口移动的元素
	private boolean isMoved;
	private Point pre_point;
	private Point end_point;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.Loginframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Loginframe = new MyFrame("src/img_Login/bg.png");
		Loginframe.setBound(450, 550);




		textField = new JTextField("213163520");
		textField.setBounds(130, 295, 200, 45);
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		textField.setForeground(new Color(245,245,245));
		textField.setOpaque(false);
		textField.setBorder(null);
		textField.setCaretColor(new Color(245,245,245));

		Loginframe.add(textField);
		textField.setColumns(10);

		textField_1 = new JPasswordField("iuxixi");
		textField_1.setBounds(130, 360, 200, 45);
		textField_1.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		textField_1.setForeground(new Color(245,245,245));
		textField_1.setOpaque(false);
		textField_1.setBorder(null);
		textField_1.setCaretColor(new Color(245,245,245));
		Loginframe.add(textField_1);
		textField_1.setColumns(10);
		

		btnLogin.setForeground(Color.BLACK);
		btnLogin.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
		btnLogin.setBounds(105, 435, 205, 40);
		btnLogin.setBorderPainted(false);
		btnLogin.setFocusPainted(false);// 设置文字焦点后无边框
		btnLogin.setContentAreaFilled(false);// 设置透明按钮
		Loginframe.add(btnLogin);

		
		btnClose.setFont(new Font("宋体", Font.BOLD, 15));
		btnClose.setBounds(335, 10, 65, 36);
		btnClose.setBorderPainted(false);
		btnClose.setFocusPainted(false);// 设置文字焦点后无边框
		btnClose.setContentAreaFilled(false);// 设置透明按钮
		Loginframe.add(btnClose);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		btnSmall.setFont(new Font("宋体", Font.BOLD, 17));
		btnSmall.setBounds(290, 10, 49, 36);
		btnSmall.setBorderPainted(false);
		btnSmall.setFocusPainted(false);// 设置文字焦点后无边框
		btnSmall.setContentAreaFilled(false);// 设置透明按钮
		Loginframe.add(btnSmall);
		btnSmall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Loginframe.setExtendedState(JFrame.ICONIFIED);
			}
		});

		// 加载监听事件
		myEvent();

		// 屏幕居中
		FrameMiddle();

		// 加上监听器，确保窗口可移动
		setDragable(Loginframe);
	}

	public void myEvent() {

		btnLogin.addActionListener(new ActionListener() {

			// @Override
			// public void actionPerformed(ActionEvent arg0) {
			//
			// Loginframe.setVisible(false);
			//
			// StudentMain stumain = new StudentMain();
			// stumain.Studentframe.setVisible(true);
			//
			// }

			public void actionPerformed(ActionEvent arg0) {
				final Message senderMessage = new Message();
				senderMessage.setEcardNumber(Integer.valueOf(textField.getText()).intValue());
				_person.setEcardNumber(Integer.valueOf(textField.getText()).intValue());
				_person.setPassword(String.valueOf(textField_1.getText()));
				senderMessage.setUser(_person);
				senderMessage.setType(1201);
				// 异步处理
				{
					new Thread(new Runnable() {
						public void run() {
							Calendar calendar_end_require = new GregorianCalendar();
							calendar_end_require.setTime(new java.util.Date(System.currentTimeMillis()));
							calendar_end_require.add(Calendar.SECOND, 1);// 登入最长时间为3秒
							while (true) {
								try {
									Message message = new Client().start(senderMessage);
									if (message.getType() == 0) {
										Loginframe.setVisible(false);

										AdministratorMain admmain = new AdministratorMain(message);
										admmain.Admframe.setVisible(true);
										break;
									} else if (message.getType() == 1) {
										Loginframe.setVisible(false);

										StudentMain stumain = new StudentMain(message);
										stumain.Studentframe2.setVisible(true);
										break;
									} else {
										JOptionPane.showMessageDialog(null, message.getType() + "Error:密码或一卡通号错误");
									}
								} catch (ClassNotFoundException e) {
									e.printStackTrace();
								}
								Calendar calendar_end = new GregorianCalendar();
								calendar_end.setTime(new java.util.Date(System.currentTimeMillis()));
								if (calendar_end.after(calendar_end_require)) {
									JOptionPane.showMessageDialog(null, "Error:登入超时！");
									break;
								}
							}
						}
					}).start();
				}

				// 异步处理
			}
		});
	}

	public void setDragable(JFrame frame) {
		Loginframe.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				isMoved = false;// 鼠标释放了以后，是不能再拖拽的了
				frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
				isMoved = true;
				pre_point = new Point(e.getX(), e.getY());// 得到按下去的位置
				frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		Loginframe.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (isMoved) {// 判断是否可以拖拽
					end_point = new Point(frame.getLocation().x + e.getX() - pre_point.x,
							frame.getLocation().y + e.getY() - pre_point.y);
					frame.setLocation(end_point);
				}
			}
		});
	}

	public void FrameMiddle() {
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		int windowWidth = Loginframe.getWidth(); // 获得窗口宽
		int windowHeight = Loginframe.getHeight(); // 获得窗口高
		int screenWidth = screenSize.width; // 获取屏幕的宽
		int screenHeight = screenSize.height; // 获取屏幕的高
		Loginframe.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);// 设置窗口居中显示
	}

	public User getPerson() {
		return this._person;
	}
}
