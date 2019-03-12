package com.seu.vcampus.Clock;


import java.awt.Font;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Time implements Runnable{
	private JLabel lab;
	private JLabel panel;
	private String year;
	private String month;
	private String day;
	private String hour;
	private String minute;
	private String second;
	private String date;
	
	private int index;
	public Time(JLabel P,int num) {
		this.panel = P;
		this.index = num;
	}
	


	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			//ʱ��
			DateFormat d1 = DateFormat.getDateTimeInstance();
			Date d = new Date();
			Timestamp timestamp = new Timestamp(d.getTime()); 
			
			//����
			String[] weekDays = {"������", "����һ", "���ڶ�", "������", "������", "������", "������"};			
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);		
			int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
	        if (w < 0)
	            w = 0;
	        
			
			//��ȡ��
			String[] stringTime = timestamp.toString().split(":| |-");
			double sec = Math.floor ( Double.parseDouble(stringTime[5]) );
			int s =  (int) Math.floor(sec);
			stringTime[5] = Integer.toString(s);
			
			//����
			year = stringTime[0];
			month = stringTime[1];
			day =  stringTime[2];
			hour = stringTime[3];
			minute = stringTime[4];
			second = stringTime[5];
			date = weekDays[w];
			
			year = stringTime[0] ;
			month = stringTime[1];
			day =  stringTime[2];
			int hours = Integer.parseInt(stringTime[3]);
//			System.out.println(hours);
			hour = (hours >= 10)? stringTime[3] :  stringTime[3];
			int minutes = Integer.parseInt(stringTime[4]);	
//			System.out.println(minutes);
			minute = (minutes >= 10)? stringTime[4] :  stringTime[4];
			int seconds = Integer.parseInt(stringTime[5]);
			second = (seconds >= 10)? stringTime[5] : "0" + stringTime[5];
			date = weekDays[w];
			
			
//			JLabel ymdLabel = new JLabel();
			if(index == 1) {
				panel.setText( year + "��" + month + "��" + day + "��" );
				panel.setFont(new Font("΢���ź�", Font.PLAIN, 15));
			}
			
			else if(index == 2) {
				panel.setText(hour + ":" + minute + ":" + second);		
				panel.setFont(new Font("΢���ź�", Font.PLAIN, 20));
			}
			else if(index == 3) {
				panel.setText(date);		
				panel.setFont(new Font("΢���ź�", Font.PLAIN, 13));
			}
			
//			JLabel ymdLabel = new JLabel();
//			if(index == 1) {
//				panel.setText( year + "��" + month + "��" + day + "��" );
//				panel.setFont(new Font("΢���ź�", Font.PLAIN, 15));
//			}
			
//			else if(index == 2) {
//				panel.setText(hour + ":" + minute + ":" + second);		
//				panel.setFont(new Font("΢���ź�", Font.PLAIN, 20));
//			}
//			else if(index == 3) {
//				panel.setText(date);		
//				panel.setFont(new Font("΢���ź�", Font.PLAIN, 14));
//			}
			
//			JLabel hmsLabel = new JLabel();
//			
//			
//			panel.setLayout(null);
//			panel.add(ymdLabel);
//			panel.add(hmsLabel);
			
			try {
				Thread.sleep(1000);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}


	
}
