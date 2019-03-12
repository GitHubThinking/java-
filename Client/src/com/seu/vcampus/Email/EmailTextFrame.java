package com.seu.vcampus.Email;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Font;


public class EmailTextFrame extends JFrame{
	

	public EmailTextFrame(String text) {
		
		
		setBounds(100, 100, 715, 453);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
		getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		textArea.setBounds(0, 0, 693, 397);
		textArea.setText(text);
		getContentPane().add(textArea);
		
		
		FrameMiddle();

		
	}
	
	public void FrameMiddle() {
		Toolkit kit = Toolkit.getDefaultToolkit();              //���幤�߰� 
	    Dimension screenSize = kit.getScreenSize();             //��ȡ��Ļ�ĳߴ�
	    int screenWidth = screenSize.width;                     //��ȡ��Ļ�Ŀ� 
	    int screenHeight = screenSize.height;                   //��ȡ��Ļ�ĸ�
	    int dialogWidth=this.getWidth();
	    int dialogHeight=this.getHeight();
	    this.setLocation(screenWidth/2-dialogWidth/2, screenHeight/2-dialogHeight/2);//���ô��ھ�����ʾ
	}
	
	
}
