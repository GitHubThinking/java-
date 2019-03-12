package com.seu.vcampus.Email;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.seu.vcampus.Special.MyButton;

import javax.swing.JButton;



public class Email extends JPanel{

	     private static final long serialVersionUID = 1L;
	     
	     private JPanel panelEmail;//Ϊ�˿�Ƭ�������õİ���panelSend��panelInbox��panel
		 private MyButton btnNewLetter;//д�Ű�ť
		 private MyButton btnInbox;//�ռ��䰴ť
		 private PanelSend panelSend;//д�����
		 private PanelInbox panelInbox;//�������

		 CardLayout cardLayout;
		 
		 private int EcardNumber;
		 //private ArrayList<String> bodies;//����Ҫ
		 
		//���������л���ť
		private MyButton lastbtn;
		private String LastImg;
		 
		 
		 private int Width = 900;
		 private int Height = 550;
		 private int innerWidth = 885;
		 private int innerHeight = 355;
		 private int left = 20;
		 private int top = 140;
		 private int btnWidth = 120;
		 private int btnHeight = 40;
		 
		 //for test
		 public static void main(String[] args) {
		  Email window = new Email(213163262);
		  window.setVisible(true);
		 }

	 public Email(int EcardNum) {
		 this.EcardNumber=EcardNum;
	 	 setBackground(new Color(245, 245, 245));
		 
		 setLayout(null);
		 //setBackground(SystemColor.info);
         //Email���沼��
		 setOpaque(false);
		 this.setBounds(20, 0, 887, 541);
	
		
		 
		 //д�Ű�ť����
		 btnNewLetter=new MyButton();
		 btnNewLetter.setBound(20, 20, btnWidth, btnHeight);
		 btnNewLetter.setImage("src/img_email/Lд��.png");
		 add(btnNewLetter);
		 
		 //�ռ���ť����
		 btnInbox=new MyButton();
		 btnInbox.setBound(20+btnWidth, 20, btnWidth, btnHeight);
		 btnInbox.setImage("src/img_email/D�ռ���.png");
		 add(btnInbox);
		
		 
		 //panel Email
		 panelEmail = new JPanel();
		 panelEmail.setBounds(left, top, innerWidth, innerHeight);
		 add(panelEmail);
		 cardLayout = new CardLayout();//important
		 panelEmail.setLayout(cardLayout); 
		 	 
		 //panel send
		 panelSend=new PanelSend(EcardNumber);
		 panelSend.setBounds(0, top, innerWidth, innerHeight);
		 panelEmail.add(panelSend,"panelSend");
		 panelSend.setLayout(null);
		 
		//panel inbox
		 panelInbox=new PanelInbox(EcardNumber);
		 panelInbox.setBounds(0, top, innerWidth, innerHeight);
		 panelEmail.add(panelInbox,"panelInbox");
		 panelInbox.setLayout(null);
		 
		 
		 
		 //�¼�����
		 myEvent();
		 
		//���ð�ť��ʽ
		lastbtn = btnNewLetter;
		LastImg = "д��";
		 
	}
	 
	 
	 
	 public void myEvent() {

			btnNewLetter.addActionListener(new ActionListener() {
				 public void actionPerformed(ActionEvent e) {
					 
					 cardLayout.show(panelEmail, "panelSend");
					 changeBtn(btnNewLetter,"д��");
				}
			});
			
			
			btnInbox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					changeBtn(btnInbox,"�ռ���");
					cardLayout.show(panelEmail, "panelInbox");
					panelInbox.fresh();
					
					
				}
			});
				
		}
	 
	 public void changeBtn(MyButton btn,String string) {

			if( LastImg != string) {
				btn.setImage("src/img_email/L"+string + ".png");
				
				lastbtn.setImage("src/img_email/D"+LastImg + ".png");
				
				lastbtn = btn;
				LastImg = string;
			}
	
		}
}



