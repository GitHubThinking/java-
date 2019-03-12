package com.seu.vcampus.Special;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyFrame extends JFrame{

	private JLayeredPane layeredPane;  
	private ImageIcon imgIcon;
	
	public MyFrame(String string) {
		layeredPane=new JLayeredPane();  
		ImageIcon image=new ImageIcon(string);//随便找一张图就可以看到效果。
		this.setLayout(null);
		layeredPane.setLayout(null);
		
		
		JPanel jp=new JPanel();  
        jp.setBounds(0,0,image.getIconWidth(),image.getIconHeight());  
        
        JLabel jl=new JLabel(image);  

        jp.add(jl);  
        jp.setOpaque(false);
//        JButton jb=new JButton("测试按钮");  
//        jb.setBounds(0,0,100,100); 
        //将jp放到最底层。                                    /*层次关系可见方法2下面的注释*/
        layeredPane.add(jp,JLayeredPane.DEFAULT_LAYER);  
        //将jb放到高一层的地方  
//        layeredPane.add(jb,JLayeredPane.MODAL_LAYER); 
        
        this.setLayeredPane(layeredPane);  
        this.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        
        this.setUndecorated(true);
        this.setBackground(new Color(0,0,0,0));
        this.setVisible(true);
	}
	
	public void add(JPanel panel) {
		layeredPane.add(panel,JLayeredPane.MODAL_LAYER); 
	}
	public void add(JButton button) {
		layeredPane.add(button,JLayeredPane.MODAL_LAYER); 
	}
	public void add(JLabel label) {
		layeredPane.add(label,JLayeredPane.MODAL_LAYER); 
	}
	public void add(JTextField textfield) {
		layeredPane.add(textfield,JLayeredPane.MODAL_LAYER); 
	}
	
	public void setBound(int w,int h) {
		this.setBounds(0, 0, w, h);      
	}
	
}
