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
        
        timer = new Timer(1000/60, new ActionListener() {//ÿ10����ı�һ��̫����λ��, �����Լ��ӿ���߷���,�޸ĺ������Ϳ���
  
            public void actionPerformed(ActionEvent e) {
                sp.setLocation(px -= speedX, py -= speedY);// �����޸ĳ�px-=3 ,py-=2�ȷ�ʽ������ÿ���ƶ��Ŀ���

                if (px <=  240) {
                	px = 1240;
//                    timer.stop();
                }
            }
        });
        timer.start();//��ʼ
    }
  
}