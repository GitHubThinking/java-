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
		textArea.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textArea.setBounds(0, 0, 693, 397);
		textArea.setText(text);
		getContentPane().add(textArea);
		
		
		FrameMiddle();

		
	}
	
	public void FrameMiddle() {
		Toolkit kit = Toolkit.getDefaultToolkit();              //定义工具包 
	    Dimension screenSize = kit.getScreenSize();             //获取屏幕的尺寸
	    int screenWidth = screenSize.width;                     //获取屏幕的宽 
	    int screenHeight = screenSize.height;                   //获取屏幕的高
	    int dialogWidth=this.getWidth();
	    int dialogHeight=this.getHeight();
	    this.setLocation(screenWidth/2-dialogWidth/2, screenHeight/2-dialogHeight/2);//设置窗口居中显示
	}
	
	
}
