package com.seu.vcampus.MainPage;


import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.seu.vcampus.Special.ImageJPanel;
import com.seu.vcampus.Special.MyButton;
import com.seu.vcampus.Special.MyPanel;

public class MainPage extends JPanel{

	private JFrame frame;
	private JPanel mainPage;
	
	private int Width = 800;
	private int Height = 400;
	private int innerWidth = 880;
	private int innerHeight = 400;
	private int left = 40;
	private int top = 150;
	private int btnWidth = 140;
	private int btnHeight = 40;
	private int btnLeft = 20;
	private JLabel label_2;

	private JLabel labletext;
	ArrayList<String> text;
	
	private JButton musicbtn;

	private Date date;
	
	private Date nowdate;
	
	public MainPage(Date date) {
		this.date = date;
		nowdate = new Date();
//		frame = new JFrame();
//		frame.setBounds(100, 100, 900, 300);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().setLayout(null);
		
		mainPage = this;
		mainPage.setBounds(left,top,innerWidth,innerHeight);
		mainPage.setBackground(new Color(255,255,255));
		mainPage.setOpaque(false);
		mainPage.setLayout(null);
		
		JPanel LittlePanel1 = new JPanel();
		LittlePanel1.setBounds(279, 23, 286, 67);
		LittlePanel1.setBackground(new Color(242,200,194));
		LittlePanel1.setLayout(null);
		mainPage.add(LittlePanel1);
		
		JLabel label_6 = new JLabel("\u8DDD\u79BB\u8003\u7814\u8FD8\u6709");
		label_6.setFont(new Font("华文行楷", Font.BOLD, 30));
		label_6.setBounds(10, 10, 192, 47);
		LittlePanel1.add(label_6);
		
		JLabel label_7 = new JLabel("\u5929");
		label_7.setFont(new Font("微软雅黑", Font.BOLD, 20));
		label_7.setBounds(248, 16, 29, 34);
		LittlePanel1.add(label_7);
		
		
		String examinationDay = Integer.toString(examination(nowdate,"2018-12-22")) ;
		JLabel Examination = new JLabel();
		Examination.setText(examinationDay);
		Examination.setFont(new Font("宋体", Font.BOLD, 20));
		Examination.setBounds(212, 14, 43, 41);
		LittlePanel1.add(Examination);


		JPanel ImgagePanel = new JPanel();
		ImgagePanel.setBounds(477, 112, 370, 267);
		ImgagePanel.setLayout(null);
		ImgagePanel.setBackground(new Color(255,255,205));
		ImageJPanel imgchange = new ImageJPanel();
		imgchange.setBounds(23, 63, 325, 181);
		ImgagePanel.add(imgchange);
		
		mainPage.add(ImgagePanel);
		
		JLabel label = new JLabel("\u65B0\u751F\u519B\u8BAD\u98CE\u91C7");
		label.setFont(new Font("幼圆", Font.BOLD, 20));
		label.setBounds(123, 10, 131, 43);
		ImgagePanel.add(label);
			
		
		JPanel CenterPanel = new JPanel();
		CenterPanel.setBounds(123, 132, 234, 162);
		CenterPanel.setOpaque(false);
		MyButton logoBtn = new MyButton();
		logoBtn.setBound(0, 0, 234, 162);
		logoBtn.setImage("src/img_Mainpage/学校.png");
		CenterPanel.add(logoBtn);
		mainPage.add(CenterPanel);


		JPanel Littlepanel2 = new JPanel();
		Littlepanel2.setBackground(new Color(223,252,250));
		Littlepanel2.setBounds(588, 23, 259, 67);
		Littlepanel2.setBackground(new Color(214,253,251));
		mainPage.add(Littlepanel2);
		Littlepanel2.setLayout(null);
		
		JLabel label_1 = new JLabel("\u8DDD\u79BB");
		label_1.setFont(new Font("华文彩云", Font.PLAIN, 30));
		label_1.setBounds(10, 10, 65, 47);
		Littlepanel2.add(label_1);
		
		JLabel KAOYAN = new JLabel("\u751F\u65E5\u8FD8\u6709");
		KAOYAN.setFont(new Font("微软雅黑", Font.BOLD, 20));
		KAOYAN.setBounds(74, 30, 80, 27);
		Littlepanel2.add(KAOYAN);
		
		JLabel label_4 = new JLabel("\u5929");
		label_4.setFont(new Font("幼圆", Font.BOLD, 25));
		label_4.setBounds(216, 26, 33, 35);
		Littlepanel2.add(label_4);
		
		
		String distentday = Integer.toString(birthday(nowdate,date)) ;
		
		JLabel SHENGRI = new JLabel();
		SHENGRI.setText(distentday);
		SHENGRI.setFont(new Font("幼圆", Font.BOLD, 25));
		SHENGRI.setBounds(168, 26, 56, 35);
		Littlepanel2.add(SHENGRI);
	}

	// throws ParseException
	public int birthday(Date date1,Date date2) {
			
//		String 到 Date
		DateFormat df = DateFormat.getDateInstance();
		df= new SimpleDateFormat("yyyy-MM-dd");

		Date date_1 = date1;//当前时间
		Date date_2 = date2;//出生日期
		
		Timestamp timestamp1 = new Timestamp(date_1.getTime()); 
		Timestamp timestamp2 = new Timestamp(date_2.getTime()); 
		
//		Date 到 string		
		//对当前时间
		String[] stringTime1 = timestamp1.toString().split(":| |-");
		String nowYear = stringTime1[0];
		int nowYears = Integer.parseInt(nowYear);
		
		String nowMonth = stringTime1[1];
		int nowMonths = Integer.parseInt(nowMonth);
		
		String nowDay = stringTime1[2];
		int nowDays = Integer.parseInt(nowDay);
		
		//对生日
		String[] stringTime2 = timestamp2.toString().split(":| |-");
		
		String birthmonth = stringTime2[1];
		int birthmonths = Integer.parseInt(birthmonth);
		
		String birthday = stringTime2[2];
		int birthdays = Integer.parseInt(birthday);
		
		//这里先不处理生日
		if( nowMonths > birthmonths ) {
			nowYears += 1;
		}else if(nowMonths == birthmonths){
			if( nowDays > birthdays) {
				nowYears += 1;
			}
		}
		
		String newbirthyear = Integer.toString(nowYears);
		String birthTime = newbirthyear + "-" + birthmonth + "-" + birthday;
		
		Date newBirthDate = new Date();
		try {
			newBirthDate = df.parse(birthTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//要过的生日时间
		Date nowTime = date_1;//当前时间
		
		long time1 = nowTime.getTime();
		
		long time2 = newBirthDate.getTime();
		
		long time3 = Math.abs(time2-time1);
		     
		int day = (int) (time3/1000/60/60/24);
		//将两个日期之间的毫秒数换算成天数
		System.out.println("两日期相隔"+day+"天");
		return day;
		
		
	}
	
	public int examination(Date date1,String strdate) {
		
//		String 到 Date
		DateFormat df = DateFormat.getDateInstance();
		df= new SimpleDateFormat("yyyy-MM-dd");

		Date date_1 = date1;//当前时间		
		Date newEXDate = new Date();//考研时间
		try {
			newEXDate = df.parse(strdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long time1 = date_1.getTime();
		
		long time2 = newEXDate.getTime();
		
		int day = (int) ((time2-time1)/1000/60/60/24);

		return day;
	}

	
}
