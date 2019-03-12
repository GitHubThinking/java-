package com.seu.vcampus.course;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;


public class CourseShow {

//	private JFrame frame;
	private int Wideth;
	private int Height;
	private int singleWideth;
	private int singleHeight;
	private int startWideth;
	private int startHeight;
	private JPanel courseShow;
	private static JPanel ddd;
	

	
	public CourseShow(int w, int h, JPanel panel) {
		

		this.Wideth = w;
		this.Height = h;
		this.singleWideth = Wideth / 16;
		this.singleHeight = Height / 14;
		this.startWideth = Wideth - singleWideth * 15 - 1;
		this.startHeight = Height - singleHeight * 13 - 1;

		courseShow = new JPanel();
		courseShow.setBounds(0, 0, Wideth, Height);
		courseShow.setOpaque(false);
//		courseShow.setBackground(new Color(0,0,0));
		courseShow.setLayout(null);

		for (int i = 1; i < 6; ++i) {
			String dayTitle = null;
			switch (i) {
			case 1:
				dayTitle = "����һ";
				break;
			case 2:
				dayTitle = "���ڶ�";
				break;
			case 3:
				dayTitle = "������";
				break;
			case 4:
				dayTitle = "������";
				break;
			case 5:
				dayTitle = "������";
				break;
			default:
				dayTitle = null;
				break;
			}
			courseShow.add(addXTitle(i, dayTitle));
		}

		for (int i = 1; i < 14; ++i) {
			courseShow.add(addYTitle(i, Integer.toString(i)));
		}
//		panel.add(courseShow);
		panel.add(courseShow, new Integer(Integer.MAX_VALUE));
//		panel.repaint();
		courseShow.setVisible(true);
	
		//��ʱ��ô��
//		initialize();
	}

	public void setWH(int w, int h) {
		Wideth = w;
		Height = h;
	}

	public JPanel addXTitle(int xOrder, String title) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBounds(startWideth + (xOrder - 1) * singleWideth * 3 + 1, 7,
				singleWideth * 3 - 2, singleHeight - 2);
		JLabel jl = new JLabel(title, JLabel.CENTER);
		panel.add(jl, BorderLayout.CENTER);
		panel.setOpaque(false);
//		panel.setBorder(new EtchedBorder(new Color(107,189,232), new Color(107,189,232)));
		return panel;
	}

	public JPanel addYTitle(int yxOrder, String title) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBounds(3, startHeight + (yxOrder - 1) * singleHeight + 3,
				startWideth - 3, singleHeight - 4);
		JLabel jl = new JLabel(title, JLabel.CENTER);
		panel.add(jl, BorderLayout.CENTER);
		panel.setOpaque(false);

		return panel;
	}

	public JPanel addPanel(int start, int over, int week, String title1, String title2,int random,String teacher,double grade,String type) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBounds(startWideth + (week - 1) * singleWideth * 3 + 2,
				startHeight + (start - 1) * singleHeight + 2,
				singleWideth * 3 - 2, (over - start + 1) * singleHeight - 2);
		//ʹ��html��ǩ������jlabel�л���
		String title = "<html><body><p align=\"center\">"+ title1 +"<br/>@"+ title2 +"</p></body></html>";
		JLabel jl = new JLabel(title, JLabel.CENTER);	
		jl.setBounds(0, 0, 20, 30);
		panel.add(jl, BorderLayout.CENTER);
		panel.setOpaque(true);
		
		
		//���Ŀγ���ɫ
		int Color[][] = {
				
				{104, 204, 206},//ǳ��ɫ
				{204, 255, 102},//ǳ��ɫ
				{253,153,203},//�ۺ�ɫ
				{153, 204, 51},//��ɫ
				{255, 255, 100},//����ɫ
				{206,165,75},//����ɫ
		};
		int r = Color[random][0];
		int g = Color[random][1];
		int b = Color[random][2];
		panel.setBackground(new Color(r, g, b));
		panel.setBorder(new EtchedBorder());
		panel.repaint();
		
		String text = 
				"<html><body><p align=\"center\">"+ 
				"�γ����ƣ�" + title1 + "<br/>" + 
				"���ң�" + title2 +	"<br/>" +	
				"�γ����ͣ�" + type + "<br/>" +
				"��ʦ��" + teacher +	 "<br/>" +	
				"ѧ�֣�" + grade + "<br/>" +
				"2018-9-14" + "<br/>" +
				"</p></body></html>";
	
		panel.setToolTipText(text);
		return panel;
	}
	
	


}
