package com.seu.vcampus.Special;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.ActionEvent;

public class MyButton extends JButton{

	private String src;
	private int left;
	private int top;
	private int width;
	private int height;
	//�̳�JButton��
	public MyButton() {
	
		
		this.setFocusPainted(false);//���������ޱ߿�
		this.setBorder(null);
		this.setContentAreaFilled(false);//����͸����ť	
		
	}
	

	public void setBound(int x,int y,int w,int h) {
		this.left = x;
		this.top = y;
		this.width = w;
		this.height = h;
		this.setBounds(left, top, width, height);
		
	}
	
	public void setImage(String str) {
		this.src = str; 
		ImageIcon btnimg2 = new ImageIcon(src); // ʵ������ť���󣬲������ð�ť����ʾͼƬ
		Image temp2 = btnimg2.getImage().getScaledInstance(width, height, btnimg2.getImage().SCALE_DEFAULT);
		btnimg2 = new ImageIcon(temp2);
		this.setIcon(btnimg2);
	}
	
	


}
