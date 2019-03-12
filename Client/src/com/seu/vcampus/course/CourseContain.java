package com.seu.vcampus.course;

import java.awt.Color;
//import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.ArrayList;

//import javax.swing.JFrame;
import javax.swing.JPanel;

//import com.seu.vcampus.course.*;
import io.Client;
import util.CourseOffered;
import util.Message;;

//课程表
public class CourseContain {

	// private JFrame frame;
	private JPanel ppp;
	private JPanel pnlCourseList;
	private int Wideth = 885;
	private int Height = 370;
	private int singleWideth = Wideth / 16;
	private int singleHeight = Height / 14;
	private int startWideth = Wideth - singleWideth * 15 - 1;
	private int startHeight = Height - singleHeight * 13 - 1;
	private int Ecard;

	private CourseShow cl;

	public CourseContain(int number, JPanel panel) {
		Ecard = number;
		System.out.println(number);
		pnlCourseList = new JPanel() {
			private static final long serialVersionUID = -4128190233953588137L;

			public void paint(Graphics g) {
				g.setColor(new Color(230, 230, 230));
				g.drawLine(1, 1, 1, Height);
				g.drawLine(startWideth, 1, startWideth, Height);
				for (int i = 1; i <= 5; i++)
					g.drawLine(startWideth + singleWideth * 3 * i, 1, startWideth + singleWideth * 3 * i, Height);
				g.drawLine(1, 1, Wideth, 1);
				g.drawLine(1, startHeight, Wideth, startHeight);
				for (int j = 1; j <= 13; j++)
					g.drawLine(1, startHeight + singleHeight * j, Wideth, startHeight + singleHeight * j);
			}
		};

		pnlCourseList.setBounds(0, 0, Wideth, Height);
		pnlCourseList.setBackground(new Color(255, 255, 255));
		pnlCourseList.setLayout(null);
	
		ppp = panel;

		fresh();
		
	}

	public void fresh() {
			
		ppp.removeAll();
		cl = new CourseShow(Wideth, Height, ppp);
		// 先暂且这样放
		// ppp.add(cl.addPanel(11, 13, 5, "高等数学A" + "\n@" + "J8-301"));
		Message messageSend = new Message(Ecard);
		messageSend.setType(1501);
		try {
			Message messageBack = new Client().start(messageSend);
			if (messageBack.getType() == 1101) {
				ArrayList<CourseOffered> courseList = messageBack.getCourseList();
				
				for (CourseOffered course : courseList) {
					String[] y = course.getCourseTime().split("-|;");
					int randomnum = (int) Math.floor(Math.random()*6);
					
					
					for (int i = 0; i < y.length / 3; ++i) {
						ppp.add(cl.addPanel(
								Integer.parseInt(y[3 * i + 1]), 
								Integer.parseInt(y[3 * i + 2]),
								Integer.parseInt(y[3 * i]), 
								course.getCourseName(),
								course.getClassroom(),	
								randomnum,
								course.getTeacher(),
								course.getCourseCredit(),
								course.getCourseProperty()
								));
					}
					ppp.repaint();
				}
			} else {
				System.out.println("未查询到本学期课程");
			}
		} catch (ClassNotFoundException event) {
			event.printStackTrace();
		}
		messageSend = null;
		System.out.println("显示课程表");
		ppp.add(pnlCourseList);

	}
}
