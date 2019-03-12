package administrator;

import java.awt.CardLayout;
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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import administrator.basicInfo.StudentManage;
import administrator.course.Course;
import administrator.libray.Libray;
import administrator.life.Life;

//import com.seu.vcampus.course.*;

import util.Message;

public class AdministratorMain {

	Message message = new Message();
	public JFrame Admframe;
	JPanel panel = new JPanel();
	JButton Button1 = new JButton();
	JButton Button2 = new JButton();
	JButton Button3 = new JButton();
	JButton Button4 = new JButton();
	JButton Button5 = new JButton();
	JButton Btnclose = new JButton("\u00D7");
	JButton Btnsmall = new JButton("-");

	private CardLayout cardLayout;

	int lastBtn_num = 1;
	JButton lastBtn = Button1;

	private JPanel MainPanel;
	private JPanel SwitchPanel;
	private JPanel PanelMainPage;
	private JPanel PanelMan;
	private JPanel PanelStore;
	private JPanel PanelLibrary;
	private JPanel PanelCourse;

	// ���Ľ���
	private Course course;
	private Libray libray;
	private StudentManage studentManage;
	private Life life;

	// �����ƶ�
	private boolean isMoved;
	private Point pre_point;
	private Point end_point;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// StudentMain window = new StudentMain();
	// window.Studentframe.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the application.
	 */
	public AdministratorMain(Message message) {
		this.message = message;
		int Ecard = message.getUser().getEcardNumber();
		System.out.println(Ecard);
		course = new Course(Ecard);
		libray = new Libray(Ecard);
		studentManage = new StudentManage(Ecard);
		life = new Life(Ecard);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Admframe = new JFrame();
		Admframe.setBounds(100, 100, 900, 500);
		;
		Admframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Admframe.setUndecorated(true);
		Admframe.getContentPane().setLayout(null);
		panel.setBounds(0, 0, 62, 500);
		panel.setBackground(new Color(153,193,104));
		Admframe.getContentPane().add(panel);
		panel.setLayout(null);

		// ����ͼƬ��ͬ·��
		String path = this.getClass().getClassLoader().getResource("img_Index_w/").getPath();
		String path2 = this.getClass().getClassLoader().getResource("img_Index_b/").getPath();

		Button1.setBounds(10, 45, 52, 50);
		panel.add(Button1);
		ImageIcon btnimg1 = new ImageIcon("src/img_Index_w/1.png"); // ʵ������ť���󣬲������ð�ť����ʾͼƬ
		Image temp = btnimg1.getImage().getScaledInstance(40, 40, btnimg1.getImage().SCALE_DEFAULT);
		btnimg1 = new ImageIcon(temp);
		Button1.setBorderPainted(false);
		Button1.setFocusPainted(false);// �������ֽ�����ޱ߿�
		Button1.setContentAreaFilled(true);// ����͸����ť
		Button1.setBackground(new Color(238, 238, 238));
		Button1.setIcon(btnimg1);

		Button2.setBounds(10, 94, 52, 50);
		ImageIcon btnimg2 = new ImageIcon("src/img_Index_w/2.png"); // ʵ������ť���󣬲������ð�ť����ʾͼƬ
		Image temp2 = btnimg2.getImage().getScaledInstance(40, 40, btnimg2.getImage().SCALE_DEFAULT);
		btnimg2 = new ImageIcon(temp2);
		Button2.setBorderPainted(false);
		Button2.setFocusPainted(false);// �������ֽ�����ޱ߿�
		Button2.setContentAreaFilled(false);// ����͸����ť
		Button2.setIcon(btnimg2);
		panel.add(Button2);

		Button3.setBounds(10, 145, 52, 50);
		ImageIcon btnimg3 = new ImageIcon("src/img_Index_w/3.png"); // ʵ������ť���󣬲������ð�ť����ʾͼƬ
		Image temp3 = btnimg3.getImage().getScaledInstance(40, 40, btnimg3.getImage().SCALE_DEFAULT);
		btnimg3 = new ImageIcon(temp3);
		Button3.setBorderPainted(false);
		Button3.setFocusPainted(false);// �������ֽ�����ޱ߿�
		Button3.setContentAreaFilled(false);// ����͸����ť
		Button3.setIcon(btnimg3);
		panel.add(Button3);

		Button4.setBounds(10, 205, 52, 50);
		ImageIcon btnimg4 = new ImageIcon("src/img_Index_w/4.png"); // ʵ������ť���󣬲������ð�ť����ʾͼƬ
		Image temp4 = btnimg4.getImage().getScaledInstance(40, 40, btnimg4.getImage().SCALE_DEFAULT);
		btnimg4 = new ImageIcon(temp4);
		Button4.setBorderPainted(false);
		Button4.setFocusPainted(false);// �������ֽ�����ޱ߿�
		Button4.setContentAreaFilled(false);// ����͸����ť
		Button4.setIcon(btnimg4);
		panel.add(Button4);

		Button5.setBounds(10, 251, 52, 50);
		ImageIcon btnimg5 = new ImageIcon("src/img_Index_w/5.png"); // ʵ������ť���󣬲������ð�ť����ʾͼƬ
		Image temp5 = btnimg5.getImage().getScaledInstance(40, 40, btnimg5.getImage().SCALE_DEFAULT);
		btnimg5 = new ImageIcon(temp5);
		Button5.setBorderPainted(false);
		Button5.setFocusPainted(false);// �������ֽ�����ޱ߿�
		Button5.setContentAreaFilled(false);// ����͸����ť
		Button5.setIcon(btnimg5);
		panel.add(Button5);
		Btnclose.setBounds(844, 0, 56, 38);

		Btnclose.setFont(new Font("����", Font.BOLD, 15));
		Btnclose.setContentAreaFilled(false);// ����͸����ť
		Btnclose.setBorderPainted(false);
		Btnclose.setFocusPainted(false);// �������ֽ�����ޱ߿�
		Admframe.getContentPane().add(Btnclose);
		Btnclose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		Btnsmall.setBounds(787, -2, 56, 38);

		Btnsmall.setFont(new Font("����", Font.BOLD, 20));
		Btnsmall.setContentAreaFilled(false);// ����͸����ť
		Btnsmall.setBorderPainted(false);
		Btnsmall.setFocusPainted(false);// �������ֽ�����ޱ߿�
		Admframe.getContentPane().add(Btnsmall);
		Btnsmall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Admframe.setExtendedState(JFrame.ICONIFIED);
			}
		});

		String path3 = this.getClass().getClassLoader().getResource("img_Login/").getPath();
		// �޸�logo
		Image logoimg = Toolkit.getDefaultToolkit().getImage(path3 + "symble.png");
		Admframe.setIconImage(logoimg);
		// ���ͼƬ
		ImageIcon img = new ImageIcon(path3 + "symble.png");
		JLabel imglabel = new JLabel(img);
		imglabel.setBounds(0, 0, 0, 0);
		Admframe.getContentPane().add(imglabel);

		MainPanel = new JPanel();
		MainPanel.setBounds(72, 40, 818, 450);
		Admframe.getContentPane().add(MainPanel);
		MainPanel.setLayout(null);

		SwitchPanel = new JPanel();
		SwitchPanel.setBounds(0, 0, 818, 450);
		MainPanel.add(SwitchPanel);
		cardLayout = new CardLayout();
		SwitchPanel.setLayout(cardLayout);

		// �л��Ľ������
		PanelMainPage = new JPanel();
		PanelMainPage.setBounds(0, 0, 818, 450);
		SwitchPanel.add("PanelMainPage", PanelMainPage);
		PanelMainPage.setLayout(null);
		JLabel label = new JLabel("PanelMainPage");
		label.setBounds(0, 0, 100, 20);
		PanelMainPage.add(label);

		////////////
		PanelMan = new JPanel();
		PanelMan.setBounds(0, 0, 818, 450);
		SwitchPanel.add("PanelMan", PanelMan);
		PanelMan.setLayout(null);	
		PanelMan.add(studentManage);
		// cardLayout

		//////////////
		PanelStore = new JPanel();
		PanelStore.setBounds(0, 0, 818, 450);
		SwitchPanel.add("PanelStore", PanelStore);
		PanelStore.setLayout(null);
		//JLabel label3 = new JLabel("PanelStore");
		//label3.setBounds(0, 0, 100, 20);
		PanelStore.add(life);

		/////////
		PanelCourse = new JPanel();
		PanelCourse.setBounds(0, 0, 818, 450);
		SwitchPanel.add("PanelCourse", PanelCourse);
		PanelCourse.setLayout(null);
		PanelCourse.add(course);

		//////
		PanelLibrary = new JPanel();
		PanelLibrary.setBounds(0, 0, 818, 450);
		SwitchPanel.add("PanelLibrary", PanelLibrary);
		PanelLibrary.setLayout(null);
		// JLabel label5 = new JLabel("PanelLibrary");
		// label5.setBounds(0,0,100,20);
		PanelLibrary.add(libray);

		Button1.setActionCommand("PanelMainPage");
		Button2.setActionCommand("PanelMan");
		Button3.setActionCommand("PanelStore");
		Button4.setActionCommand("PanelCourse");
		Button5.setActionCommand("PanelLibrary");

		// ���ü����¼�
		myEvent();

		// ��Ļ����
		FrameMiddle();

		// ���ϼ�������ȷ�����ڿ��ƶ�
		setDragable(Admframe);
	}

	public void myEvent() {

		Button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				changeBtn(Button1, 1);
				cardLayout.show(SwitchPanel, "PanelMainPage");
			}
		});
		Button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				changeBtn(Button2, 2);
				cardLayout.show(SwitchPanel, "PanelMan");
			}
		});
		Button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				changeBtn(Button3, 3);
				cardLayout.show(SwitchPanel, "PanelStore");

			}
		});
		Button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				changeBtn(Button4, 4);
				cardLayout.show(SwitchPanel, "PanelCourse");

			}
		});
		Button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				changeBtn(Button5, 5);
				cardLayout.show(SwitchPanel, "PanelLibrary");
			}
		});
	}

	public void changeBtn(JButton newBtn, int num) {

		if (num != lastBtn_num) {
			String path = this.getClass().getClassLoader().getResource("img_Index_b/").getPath();
			ImageIcon btnimg = new ImageIcon("src/img_Index_b/" + num + ".png"); // ʵ������ť���󣬲������ð�ť����ʾͼƬ

			Image temp = btnimg.getImage().getScaledInstance(40, 40, btnimg.getImage().SCALE_DEFAULT);
			btnimg = new ImageIcon(temp);
			newBtn.setContentAreaFilled(true);// ���ò�͸����ť
			newBtn.setBackground(new Color(238, 238, 238));
			newBtn.setIcon(btnimg);

			String path2 = this.getClass().getClassLoader().getResource("img_Index_w/").getPath();
			ImageIcon btnimg2 = new ImageIcon("src/img_Index_w/"+ lastBtn_num + ".png"); // ʵ������ť���󣬲������ð�ť����ʾͼƬ

			Image temp2 = btnimg2.getImage().getScaledInstance(40, 40, btnimg2.getImage().SCALE_DEFAULT);
			btnimg2 = new ImageIcon(temp2);
			lastBtn.setContentAreaFilled(false);// ����͸����ť
			lastBtn.setIcon(btnimg2);

			lastBtn = newBtn;
			lastBtn_num = num;
		}
	}

	// ���ô����ƶ�
	public void setDragable(JFrame frame) {
		Admframe.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				isMoved = false;// ����ͷ����Ժ��ǲ�������ק����
				frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
				isMoved = true;
				pre_point = new Point(e.getX(), e.getY());// �õ�����ȥ��λ��
				frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		Admframe.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (isMoved) {// �ж��Ƿ������ק
					end_point = new Point(frame.getLocation().x + e.getX() - pre_point.x,
							frame.getLocation().y + e.getY() - pre_point.y);
					frame.setLocation(end_point);
				}
			}
		});
	}

	// ���ô��ھ���
	public void FrameMiddle() {
		Toolkit kit = Toolkit.getDefaultToolkit(); // ���幤�߰�
		Dimension screenSize = kit.getScreenSize(); // ��ȡ��Ļ�ĳߴ�
		int windowWidth = Admframe.getWidth(); // ��ô��ڿ�
		int windowHeight = Admframe.getHeight(); // ��ô��ڸ�
		int screenWidth = screenSize.width; // ��ȡ��Ļ�Ŀ�
		int screenHeight = screenSize.height; // ��ȡ��Ļ�ĸ�
		Admframe.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);// ���ô��ھ�����ʾ
	}
}
