package com.seu.vcampus.studentmain;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.seu.vcampus.Clock.Time;
import com.seu.vcampus.Email.Email;
import com.seu.vcampus.MainPage.MainPage;
import com.seu.vcampus.Special.MyButton;
import com.seu.vcampus.Special.MyFrame;
import com.seu.vcampus.Special.MyPanel;
import com.seu.vcampus.course.*;
import com.seu.vcampus.library.Library;
import com.seu.vcampus.live.LiveTab;
import com.seu.vcampus.student.Student;

import io.Client;
import util.BasicInfo;
import util.Message;




public class StudentMain {

	Message message=new Message();
	public MyFrame Studentframe2;
	MyPanel panel = new MyPanel();
	MyPanel panelIdentity = new MyPanel();
	JButton Button1 = new JButton();
	JButton Button2 = new JButton();
	JButton Button3 = new JButton();
	JButton Button4 = new JButton();
	JButton Button5 = new JButton();
	JButton Button6 = new JButton();
	MyButton Btnclose = new MyButton();
	MyButton Btnsmall = new MyButton();
	
	private CardLayout cardLayout;
	
	//用于转换修改的变量
	int lastBtn_num = 1;
	JButton lastBtn = Button1;
	int lastchangeme = 0;
	
	
	
	private MyPanel MainPanel;
	private MyPanel SwitchPanel;
	private MyPanel PanelMainPage;
	private MyPanel PanelMan;
	private MyPanel PanelLive;
	private MyPanel PanelLibrary;
	private MyPanel PanelCourse;
	private MyPanel PanelEmail;
	private MyPanel PanelEcard;

	//更改界面
	private Course course;
	private Library library;
	private LiveTab live;
	private Email email;
	private Student student;
	private MainPage mainpage;
	
	//设置移动
	private boolean isMoved;  
	private Point pre_point;  
	private Point end_point; 

	private int FrameWidth = 1000;
	private int FrameHeight = 600;
	private int MainWidth = 950;
	private int MainHeight = 570;
	private int left = 20;

	private int Ecard;
	private String Name;	
	private String stuNumber;
	private String stringSrc;
	private Date Birthday;
	private Date NowDay;
	
	private MyButton Myimg;
	
	private Time time;
	public StudentMain(Message message) {
		this.message=message;
		this.Ecard=message.getUser().getEcardNumber();
		getMyself();
		System.out.println(Ecard);
		course = new Course(Ecard);
		library = new Library(Ecard);
		email = new Email(Ecard);
		student = new Student(Ecard);
		mainpage = new MainPage(Birthday);
		
		
		initialize();
		 
	}

	
	private void initialize() {
		Studentframe2 = new MyFrame("src/img_bg/background1.png");
					
		panel.setBounds(50, 554, FrameWidth-100, 60);
		Studentframe2.add(panel);
		panel.setLayout(null);
		
		panelIdentity.setBounds(50, 70, FrameWidth-100, 60);
		panelIdentity.setOpaque(false);
		panelIdentity.setLayout(null);
		
		JLabel lableName = new JLabel("姓名:");
		lableName.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		lableName.setForeground(new Color(14,118,0));
		lableName.setBounds(30, 10, 70, 50);
		panelIdentity.add(lableName);
		
		JLabel stringName = new JLabel(Name);
		stringName.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		stringName.setBounds(100, 15, 150, 40);
		panelIdentity.add(stringName);
		

		JLabel lableNumber = new JLabel("学号:");
		lableNumber.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		lableNumber.setForeground(new Color(14,118,0));
		lableNumber.setBounds(230, 10, 70, 50);
		panelIdentity.add(lableNumber);
		
		JLabel stringNumber = new JLabel(stuNumber);
		stringNumber.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		stringNumber.setBounds(300, 15, 200, 40);
		panelIdentity.add(stringNumber);
		
		JLabel lableEcard = new JLabel("一卡通:");
		lableEcard.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		lableEcard.setForeground(new Color(14,118,0));
		lableEcard.setBounds(460, 10, 90, 50);
		panelIdentity.add(lableEcard);
		
		JLabel stringEcard = new JLabel(Integer.toString(Ecard));
		stringEcard.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		stringEcard.setBounds(550, 15, 200, 40);
		panelIdentity.add(stringEcard);
		
		Myimg = new MyButton();		
		Myimg.setBound(710,10,50,50);	
		Myimg.setBorder(BorderFactory.createLineBorder(new Color(14,118,0),2));
		Myimg.setImage("pictures/people/"+stringSrc+".jpg");
		panelIdentity.add(Myimg);
		Myimg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				changeBtn(Button2,2);
				cardLayout.show(SwitchPanel, "PanelMan");
				student.fresh();
				showMyself(0);
			}
		});
		
		JLabel TimeLabel1 = new JLabel();	
		new Thread(new Time(TimeLabel1,1)).start();
		TimeLabel1.setBounds(770, 10, 130, 20);
		
		JLabel TimeLabel2 = new JLabel();	
		new Thread(new Time(TimeLabel2,3)).start();
		TimeLabel2.setBounds(860, 35, 130, 20);
//		
		JLabel TimeLabel3 = new JLabel();	
		new Thread(new Time(TimeLabel3,2)).start();
		TimeLabel3.setBounds(770, 35, 130, 20);
//		
		
		
		panelIdentity.add(TimeLabel1);
		panelIdentity.add(TimeLabel2);
		panelIdentity.add(TimeLabel3);
//		
		Studentframe2.add(panelIdentity);
		
		int point = 200;
		setButton(Button1,1,80,50,point);
		panel.add(Button1);
		
		setButton(Button2,2,80,50,point);
		panel.add(Button2);
		
		setButton(Button3,3,80,50,point);
		panel.add(Button3);
		
		setButton(Button4,4,80,50,point);
		panel.add(Button4);
		
		setButton(Button5,5,80,50,point);
		panel.add(Button5);
		
		setButton(Button6,6,80,50,point);
		panel.add(Button6);
		
		
		Btnclose.setBound(894, 21, 40, 38);		
		Btnclose.setImage("src/img_little/close2.png");
		Btnclose.setFont(new Font("宋体", Font.BOLD, 15));
		Btnclose.setContentAreaFilled(false);//设置透明按钮
		Btnclose.setBorderPainted(false);
		Btnclose.setFocusPainted(false);//设置文字焦点后无边框
		Studentframe2.add(Btnclose);
		Btnclose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		Btnsmall.setBound(838, 20, 40, 38);		
		Btnsmall.setImage("src/img_little/small.png");
		Btnsmall.setFont(new Font("宋体", Font.BOLD, 20));
		Btnsmall.setContentAreaFilled(false);//设置透明按钮
		Btnsmall.setBorderPainted(false);
		Btnsmall.setFocusPainted(false);//设置文字焦点后无边框
		Studentframe2.add(Btnsmall);
		Btnsmall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Studentframe2.setExtendedState(JFrame.ICONIFIED);
			}
		});
	
		
		MainPanel = new MyPanel();
		MainPanel.setBounds(24, 0, MainWidth, MainHeight);
		MainPanel.setOpaque(false);
		Studentframe2.add(MainPanel);
		MainPanel.setLayout(null);
				
		SwitchPanel = new MyPanel();
		SwitchPanel.setBounds(0, 0, MainWidth, MainHeight);
		MainPanel.setOpaque(false);
		MainPanel.add(SwitchPanel);
		cardLayout = new CardLayout();	
		SwitchPanel.setLayout(cardLayout);
		
		
//		//切换的界面设计
		PanelMainPage = new MyPanel();
		PanelMainPage.setBounds(left, 0, MainWidth, MainHeight);	
		PanelMainPage.setOpaque(false);//设置透明
		SwitchPanel.add("PanelMainPage",PanelMainPage);	
		PanelMainPage.setLayout(null);
		PanelMainPage.add(mainpage);

	
		
		////////////
		PanelMan = new MyPanel();
		PanelMan.setBounds(left, 0, MainWidth, MainHeight);	
		SwitchPanel.add("PanelMan",PanelMan);
		PanelMan.setLayout(null);
		PanelMan.setOpaque(false);
		PanelMan.add(student);


		//////////////
		PanelLive = new MyPanel();
		PanelLive.setBounds(left, 0, MainWidth, MainHeight);	
		SwitchPanel.add("PanelLive",PanelLive);
		PanelLive.setLayout(null);
		PanelLive.setOpaque(false);
		live = new LiveTab(PanelLive,message.getEcardNumber());
		

		
		/////////
		PanelCourse = new MyPanel();
		PanelCourse.setBounds(left, 0, MainWidth, MainHeight);	
		SwitchPanel.add("PanelCourse",PanelCourse);
		PanelCourse.setLayout(null);
		PanelCourse.setOpaque(false);
		PanelCourse.add(course);
		
		//////
		PanelLibrary = new MyPanel();
		PanelLibrary.setBounds(left, 0, MainWidth, MainHeight);	
		SwitchPanel.add("PanelLibrary",PanelLibrary);
		PanelLibrary.setLayout(null);	
		PanelLibrary.setOpaque(false);
		PanelLibrary.add(library);
		
		
		PanelEmail = new MyPanel();
		PanelEmail.setBounds(left, 0, MainWidth, MainHeight);	
		SwitchPanel.add("PanelEmail",PanelEmail);
		PanelEmail.setLayout(null);	
		PanelEmail.setOpaque(false);
		PanelEmail.add(email);
			

	
		//设置监听事件
		myEvent();
		
		//屏幕居中
		FrameMiddle();
		
		//加上监听器，确保窗口可移动
		setDragable(Studentframe2); 
	}
	
	public void myEvent() {

		Button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				changeBtn(Button1,1);
				cardLayout.show(SwitchPanel, "PanelMainPage");
				showMyself(1);
			}
		});
		Button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				changeBtn(Button2,2);
				cardLayout.show(SwitchPanel, "PanelMan");
				student.fresh();
				showMyself(0);
			}
		});
		Button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				changeBtn(Button3,3);
				cardLayout.show(SwitchPanel, "PanelLive");
				showMyself(1);
			}
		});
		Button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				changeBtn(Button4,4);
				cardLayout.show(SwitchPanel, "PanelCourse");
				showMyself(1);
				
			}
		});
		Button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				changeBtn(Button5,5);
				cardLayout.show(SwitchPanel, "PanelLibrary");
				showMyself(1);
			}
		});
		Button6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				changeBtn(Button6,6);
				cardLayout.show(SwitchPanel, "PanelEmail");
				showMyself(1);
			}
		});
	}
	
	public void changeBtn(JButton newBtn,int num) {

		if(num != lastBtn_num) {

			ImageIcon btnimg = new ImageIcon("src/img_Index_g/" + num + ".png"); // 实例化按钮对象，并且设置按钮上显示图片
		
			Image temp = btnimg.getImage().getScaledInstance(40, 40, btnimg.getImage().SCALE_DEFAULT);
			btnimg = new ImageIcon(temp);
			newBtn.setContentAreaFilled(true);//设置不透明按钮
			newBtn.setBackground(new Color(245,245,245));
			newBtn.setIcon(btnimg);
			
			ImageIcon btnimg2 = new ImageIcon("src/img_Index_w/" + lastBtn_num + ".png"); // 实例化按钮对象，并且设置按钮上显示图片

			Image temp2 = btnimg2.getImage().getScaledInstance(40, 40, btnimg2.getImage().SCALE_DEFAULT);
			btnimg2 = new ImageIcon(temp2);
			lastBtn.setContentAreaFilled(false);//设置透明按钮
			lastBtn.setIcon(btnimg2);
			
			lastBtn = newBtn;
			lastBtn_num = num;
		}
	}
	
	//设置窗口移动
	public void  setDragable(JFrame frame) {
		Studentframe2.addMouseListener(new MouseAdapter() {
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
		Studentframe2.addMouseMotionListener(new MouseMotionAdapter() {  
            public void mouseDragged(MouseEvent e) {  
                if (isMoved) {// 判断是否可以拖拽  
                    end_point = new Point(frame.getLocation().x + e.getX() - pre_point.x,  
                    		frame.getLocation().y + e.getY() - pre_point.y);  
                    frame.setLocation(end_point);  
                }  
            }  
        }); 
	}
	
	//设置窗口居中
	public void FrameMiddle() {
		Toolkit kit = Toolkit.getDefaultToolkit();              //定义工具包 
	    Dimension screenSize = kit.getScreenSize();             //获取屏幕的尺寸
	    int windowWidth = Studentframe2.getWidth();                     //获得窗口宽 
	    int windowHeight = Studentframe2.getHeight();                   //获得窗口高 
	    int screenWidth = screenSize.width;                     //获取屏幕的宽 
	    int screenHeight = screenSize.height;                   //获取屏幕的高
	    Studentframe2.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示
	}
	
	public void setButton(JButton btn,int num,int width,int height, int point) {
		
		btn.setBounds( point + width*(num-1), 0, width, height);
		ImageIcon btnimg;
		if(num == 1) {
			btnimg = new ImageIcon("src/img_Index_g/"+num+".png"); // 实例化按钮对象，并且设置按钮上显示图片
		}else {
			btnimg = new ImageIcon("src/img_Index_w/"+num+".png"); // 实例化按钮对象，并且设置按钮上显示图片
		}		
		Image temp = btnimg.getImage().getScaledInstance(40, 40, btnimg.getImage().SCALE_DEFAULT);
		btnimg = new ImageIcon(temp);
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);//设置文字焦点后无边框
		btn.setContentAreaFilled(true);//设置不透明按钮
		if(num == 1) {			
			btn.setBackground(new Color(245,245,245));
		}else {
			btn.setBackground(new Color(155,193,104));			
		}			
		btn.setIcon(btnimg);
	}
	
	
	public void showMyself(int flex) {
		if(flex == 1) {
			if( lastchangeme == 0 ) {
				getMyself();
				Myimg.setImage("pictures/people/"+stringSrc+".jpg");
				lastchangeme = 1;
			}
			
			panelIdentity.setVisible(true);
		}
		else {
			lastchangeme = 0;
			panelIdentity.setVisible(false);
		}
	}
	
	public void getMyself() {
		Message senderMessage = new Message(Ecard);
		senderMessage.setType(1301);
		try {
			Message messageBI =  new Client().start(senderMessage);
			if (messageBI.getType() == 1101) {//返回数据成功
				
				BasicInfo B = messageBI.getBasicInfo();
				Name = B.getNameString();
				stuNumber = B.getStuNumber();
				stringSrc = B.getPicturePath();
				Birthday = B.getBirthday();		
				
			} else {
				JOptionPane.showMessageDialog(null, "Error：未接收到信息！"); 
			}
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
	}
	
	
	
	
	
}
