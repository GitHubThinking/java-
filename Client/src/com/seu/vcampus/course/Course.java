package com.seu.vcampus.course;

import java.awt.CardLayout;
import java.awt.Color;
//import java.awt.EventQueue;

//import javax.swing.JFrame;
import javax.swing.JPanel;

import com.seu.vcampus.Special.MyButton;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

//import com.seu.vcampus.course.*;

public class Course extends JPanel {


	private static final long serialVersionUID = 1L;

	JPanel Bigpanel;

	private int Ecard;
	private MyButton Button1;
	private MyButton Button2;
	private MyButton Button3;

	CardLayout cardLayout;

	//用于设置切换按钮
	private MyButton lastbtn;
	private String LastImg;
	
	private CourseContain courseContain;
	@SuppressWarnings("unused")
	private ChooseCourse chooseCourse;
	private SelectedCourse selectedCourse;
	
	private int Width = 900;
	private int Height = 550;
	private int innerWidth = 900;
	private int innerHeight = 375;
	private int left = 20;
	private int top = 150;
	private int btnWidth = 140;
	private int btnHeight = 40;
	
	
	public Course(int Ecard) {
		this.Ecard = Ecard;
		System.out.println(this.Ecard);
		this.setBounds(left, 0, Width, Height);
		setLayout(null);
		this.setOpaque(false);
		
		Button1 = new MyButton();
		Button1.setBound(20, 20, btnWidth, btnHeight);
		Button1.setImage("src/img_course/L课程表.png");
		add(Button1);

		Button2 = new MyButton();
		Button2.setBound(20+btnWidth, 20, btnWidth, btnHeight);
		Button2.setImage("src/img_course/D选课.png");
		add(Button2);
		
		
//		Button3 = new MyButton();
//		Button3.setBound(20+btnWidth*2, 20, btnWidth, btnHeight);
//		Button3.setImage("src/img_course/D已选课程.png");
//		add(Button3);
		
		cardLayout = new CardLayout();

		Bigpanel = new JPanel();
		Bigpanel.setBounds(10, top, innerWidth, innerHeight);
		add(Bigpanel);
		Bigpanel.setLayout(cardLayout);
		
		JPanel panelCourseList = new JPanel();
		panelCourseList.setBounds(10, 10, innerWidth, innerHeight);
		Bigpanel.add("panelCourseList", panelCourseList);
		panelCourseList.setLayout(null);
		panelCourseList.setBackground(new Color(255, 255, 255));
		courseContain = new CourseContain(Ecard, panelCourseList);

		JPanel panelChoose = new JPanel();
		panelChoose.setBounds(10, 10, innerWidth, innerHeight);
		Bigpanel.add("panelChoose", panelChoose);
		panelChoose.setLayout(null);
		chooseCourse = new ChooseCourse(Ecard, panelChoose);
		
		JPanel panelSelected = new JPanel();
		panelSelected.setBounds(10, 10, innerWidth, innerHeight);
		Bigpanel.add("panelSelected", panelSelected);
		panelSelected.setLayout(null);
		selectedCourse = new SelectedCourse(Ecard, panelSelected);
		
		
		// 设置监听事件
		myEvent();
		
		//设置按钮样式
		lastbtn = Button1;
		LastImg = "课程表";
		
				
	}

	public void myEvent() {

		Button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// changeBtn(Button1,1);
				
				Bigpanel.repaint();
				cardLayout.show(Bigpanel, "panelCourseList");
				courseContain.fresh();
				changeBtn(Button1,"课程表");
				
				
			}
			
		});
		Button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// changeBtn(Button2,2);
				Bigpanel.repaint();
				cardLayout.show(Bigpanel, "panelChoose");
				chooseCourse.fresh();
				changeBtn(Button2,"选课");

			}
		});
		
		
	}
	
	public void changeBtn(MyButton btn,String string) {

		if( LastImg != string) {
			btn.setImage("src/img_course/L"+string + ".png");
			
			lastbtn.setImage("src/img_course/D"+LastImg + ".png");
			
			lastbtn = btn;
			LastImg = string;
		}
		
		
	}
}
