package com.seu.vcampus.Email;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.seu.vcampus.Special.MyButton;
import com.seu.vcampus.Special.MyTextField;

import io.Client;
import util.Message;
import util.Email;

public class PanelSend extends JPanel{
	
	private JLabel labelReceiver;
	private MyTextField textReceiver;
	private JLabel labelTitle;
	private MyTextField textTitle;
	private JLabel labelBody;
	private JTextArea textBody;
	private MyButton btnSend;	
	private MyButton btnClear;
	
	
	private int EcardNumber;
	
	private int innerWidth = 900;
	private int innerHeight = 385;
	private int left = 20;
	private int top = 140;
	private int btnWidth = 140;
	private int btnHeight = 40;
	
	private int textWidth = 500;
	private int textHight = 20;
	private int wordBoxWidth = 500;
	private int wordBoxHeight = 220;
	
	
	
	public PanelSend(int EcardNum) {
		
		 this.EcardNumber=EcardNum;
		 
		 setBackground(new Color(245, 245, 245));
		 setBounds(120, 0, innerWidth, innerHeight);
		 setLayout(null);
		
		 labelReceiver = new JLabel();
		 ImageIcon img0 = new ImageIcon("src/img_email/F收件人.png");
		 labelReceiver.setIcon(img0);
		 labelReceiver.setBounds(56, -10, img0.getIconWidth(), img0.getIconHeight());
		 add(labelReceiver);//
		 textReceiver=new MyTextField(20);//标题输入
		 textReceiver.setBounds(195, 10, 500, 29);
		 textReceiver.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		 textReceiver.setColumns(11);//
		 add(textReceiver);//
		 
		 labelTitle = new JLabel();
		 ImageIcon img1 = new ImageIcon("src/img_email/F标题.png");
		 labelTitle.setIcon(img1);
		 labelTitle.setBounds(70, 45, img1.getIconWidth(), img1.getIconHeight());
		 add(labelTitle);//
		 textTitle=new MyTextField(20);//标题输入
		 textTitle.setBounds(195, 60, 500, 29);
		 textTitle.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		 textTitle.setColumns(11);//
		 add(textTitle);//
		 
		 labelBody = new JLabel();
		 ImageIcon img2 = new ImageIcon("src/img_email/F正文.png");
		 labelBody.setIcon(img2);
		 labelBody.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		 labelBody.setBounds(65, 90, img2.getIconWidth(), img2.getIconHeight());
		 add(labelBody);//
		 textBody=new JTextArea(20, 20);//正文输入
		 textBody.setBounds(195, 110, wordBoxWidth, wordBoxHeight);
		 textBody.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
		 textBody.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		 add(textBody);//
		 
		 btnSend=new MyButton();
		 btnSend.setBound(725, 200, 100, 37);	 
		 btnSend.setImage("src/img_email/F发送.png");
		 add(btnSend);//
		 
		 btnClear = new MyButton();
		 btnClear.setBound(725, 250, 100, 37);	 
		 btnClear.setImage("src/img_email/F清空.png");
		 add(btnClear);//
		 
		 myEvent();
	}
	
	public void myEvent(){
		btnSend.addActionListener(new ActionListener() {
				 public void actionPerformed(ActionEvent e) {
			     Email aEmail=new Email();
			     aEmail.setReceiver(Integer.valueOf(textReceiver.getText()).intValue());
			     aEmail.setTitle(String.valueOf(textTitle.getText()));
			     aEmail.setBody(String.valueOf(textBody.getText()));
			     
			     Message messageSender=new Message(EcardNumber);
			     messageSender.setEmail(aEmail);
			     messageSender.setType(1701);
			     
			     try {
			    	 
			    	 Message messageReceiver=new Client().start(messageSender);
			    	 if(messageReceiver.getType()==1101) {
			    		 JOptionPane.showMessageDialog(null, "发送成功");

			    		 textBody.setText(null);
			    		 JDialog dialog=new JDialog();
			    		 dialog.getContentPane().add(new JLabel("Send."));
			    		 dialog.setVisible(true);
			    	 }
			    	 
			    	 else {
			    		 System.out.println("发送失败");
			    	 }
			     }
			     catch (ClassNotFoundException event) {
			    	 event.printStackTrace();
				}
			     
			     messageSender=null;
				}
			}
		);
		btnClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				textBody.setText("");
				textReceiver.setText("");
				textTitle.setText("");
			}
		
		});
			

	}
	

}
