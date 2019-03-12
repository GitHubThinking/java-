package com.seu.vcampus.MainPage;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
  
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
  
public class Swing{
  
    JPanel sp;
    Timer timer;
    int w = 600;
    int h = 400;
    int px = w - 20;
    int py = h - 20;
   
    int intentX = 0;
    int intentY = 0;
    		
    public Swing(int x,int y,double speedX,double speedY, JPanel panel) {

    	px = x;
    	py = y;
        sp = panel;
        sp.setLocation(px, py);
        intentX = px;
        intentY = py;
        
        timer = new Timer(1000/60, new ActionListener() {//每10毫秒改变一次太阳的位置, 可以自己加快或者放慢,修改毫秒数就可以
  
            public void actionPerformed(ActionEvent e) {
                sp.setLocation(px -= speedX, py -= speedY);// 可以修改成px-=3 ,py-=2等方式来控制每次移动的快慢

                if (px <=  240) {
                	px = 1240;
//                    timer.stop();
                }
            }
        });
        timer.start();//开始
    }
  
}