package com.seu.vcampus.Special;

import java.awt.Color; 
import java.awt.FlowLayout; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.Insets; 
import java.awt.RenderingHints; 
import java.awt.Shape; 
import java.awt.event.MouseAdapter; 
import java.awt.event.MouseEvent; 
import java.awt.geom.RoundRectangle2D; 
import javax.swing.JFrame; 
import javax.swing.JTextField; 


public class MyTextField extends JTextField {

	 
	private static final long serialVersionUID = -1946802815417758252L; 
	
	public MyTextField(int columns){ 
	      super(columns); 
	      // 
	      setMargin(new Insets(0,5,0,5)); 
	} 
	
	
	@Override 
	protected void paintBorder(Graphics g) { 
	    int h = getHeight();// ��JComponent���ȡ�߿� 
	    int w = getWidth(); 
	    Graphics2D g2d = (Graphics2D)g.create(); 
	    Shape shape = g2d.getClip(); 
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
	    g2d.setClip(shape); 
	    g2d.drawRoundRect(0, 0, w - 2, h - 2, h-12, h+5); 
	    g2d.dispose(); 
	    super.paintBorder(g2d); 
	}
	
	public static void main(String[] args) { 
	   JFrame jf = new JFrame(); 
	   jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	   jf.setSize(300, 200); 
	   jf.setLayout(new FlowLayout()); 
	   MyTextField text = new MyTextField(20); 
	   jf.add(text); 
	   jf.setVisible(true); 
	} 


}

