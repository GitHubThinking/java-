package com.seu.vcampus.Special;

import java.awt.*;
    import java.awt.event.*;
    import javax.swing.*;
    public class ImageJPanel extends JPanel{
    	ImageJPanel mp;
        int index = 0;
        ImageIcon[] imgs = {
                new ImageIcon("src/img_Mainpage/1.jpg"),
                new ImageIcon("src/img_Mainpage/2.jpg"),
                new ImageIcon("src/img_Mainpage/3.jpg"),
               
            };
             
        public ImageJPanel() {
        	mp = this;        
            Timer timer = new Timer(7000,new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	mp.repaint();
                }
            });
            timer.start();
        }
        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(imgs[index%imgs.length].getImage(),0,0,mp.getWidth() , mp.getHeight(),this);
            index++;
        }
    }