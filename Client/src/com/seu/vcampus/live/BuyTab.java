package com.seu.vcampus.live;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.seu.vcampus.Special.MyButton;

import io.Client;
import util.Commodity;
import util.Message;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.ScrollPaneConstants;

public class BuyTab {
	private JPanel pnlBuy;
	private JPanel pnlGoods;
	private int EcardNumber;
	private JLabel lblImg;
	private JLabel lblInfo;
	private JScrollPane goodsList;
	public BuyTab(JPanel panel,int Ecard) {
		EcardNumber = Ecard;
		
		pnlBuy =  new JPanel();
		pnlBuy.setBounds(0,0,720,382);
		pnlBuy.setOpaque(false);
		pnlBuy.setBackground(SystemColor.control);
		pnlBuy.setLayout(null);
		panel.add(pnlBuy);
		
		lblImg = new JLabel();
		lblImg.setBounds(14, 13, 300, 280);
		pnlBuy.add(lblImg);
		
		lblInfo = new JLabel("��Ʒ���");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(0, 303, 299, 66);
		pnlBuy.add(lblInfo);
		
		pnlGoods = new JPanel();
		pnlGoods.setSize(380, 360);
		pnlGoods.setLocation(311, 13);
		pnlGoods.setBackground(SystemColor.inactiveCaptionBorder);
		pnlGoods.setPreferredSize(new Dimension(370,500));
		//ÿ����Ʒ�����100���أ�Ĭ�ϴ�С�ܷ�5����Ʒ����
		pnlGoods.setLayout(null);
		pnlBuy.add(pnlGoods);
		goodsList = new JScrollPane(pnlGoods);
//		goodsList.setLayout(null);
		goodsList.setBackground(new Color(173,216,230));
		goodsList.setBounds(311, 13, 390, 360);
		pnlBuy.add(goodsList);
		//goodsList.add(pnlBuy)
	}
	
	
	void fresh() {
		Message SendMessage = new Message();
		SendMessage.setEcardNumber(EcardNumber);
		SendMessage.setType(1808);
		try {
			Message msg = new Client().start(SendMessage);
			if(msg.getType() == 1101) {
			
				ArrayList<Commodity> CommoList = msg.getCommoList();
				int commoSize = CommoList.size();
				System.out.println("��Ʒ�б��СΪ"+commoSize);
				if(commoSize == 0) {JOptionPane.showMessageDialog(null, "Error����Ʒ�б�Ϊ�գ�"); }
				else {
					if(commoSize>5)
						pnlGoods.setPreferredSize(new Dimension(350,100*commoSize));//�б�����
					for (int index = 0; index < commoSize; ++index) {
					JPanel pnlItem = new JPanel();	//���ڴ��ÿ����Ʒ����Ϣ
					pnlItem.setBackground(Color.WHITE);	
					pnlItem.setBounds(0,index*100,370,100);
					pnlItem.setBorder(BorderFactory.createTitledBorder(""));//�������߿�ʵ�ַ�����Ч��
					pnlItem.setBorder(BorderFactory.createLineBorder(Color.gray));//�������߿���ɫ
					pnlGoods.add(pnlItem);
					pnlItem.setLayout(null);
					final int x = index;
					pnlItem.addMouseListener(new MouseAdapter(){	//��������Ϣ��Ӧ
						@Override
						public void mousePressed(MouseEvent e) {
							pnlItem.setBounds(5, x*100+5, 360, 90);
							
							
							pnlItem.setBackground(Color.LIGHT_GRAY);	//�������ֱ߿�Ч����������ɻ�ɫ		
						}
						@Override
						public void mouseReleased(MouseEvent e) {	//�ɿ�������ʾ��Ӧ��Ʒ��Ϣ
							ImageIcon pic = new ImageIcon("pictures/goods/"+CommoList.get(x).getPicturePath()+".jpg");
							pic.setImage(pic.getImage().getScaledInstance(250, 250,Image.SCALE_DEFAULT));//
							lblImg.setIcon(pic);
							lblInfo.setText(CommoList.get(x).getBriefInfo());
							pnlItem.setBounds(0,x*100,370,100);
							pnlItem.setBackground(Color.WHITE);		//Ҫ�ָ�ԭ����ʽ
						}
					});
					
					JLabel lblPicture = new JLabel();
					lblPicture.setBounds(10,10,80,80);
					ImageIcon ii = new ImageIcon("pictures/goods/"+CommoList.get(x).getPicturePath()+".jpg");
					ii.setImage(ii.getImage().getScaledInstance(80, 80,Image.SCALE_DEFAULT));//
					lblPicture.setIcon(ii);
					lblPicture.setHorizontalAlignment(SwingConstants.CENTER);
					pnlItem.add(lblPicture);
					
					
					JLabel lblName = new JLabel("��Ʒ����");
					lblName.setBounds(100,10,80,20);
					pnlItem.add(lblName);
					
					JTextField txfName = new JTextField();
					txfName.setEditable(false);
					txfName.setBounds(190, 10, 80, 20);
					txfName.setText(CommoList.get(x).getProduct());
					pnlItem.add(txfName);
					
					
					JLabel lblPrice = new JLabel("�۸�");
					lblPrice.setBounds(100,40,80,20);
					pnlItem.add(lblPrice);
					
					JTextField txfPrice = new JTextField();
					txfPrice.setEditable(false);
					txfPrice.setBounds(190,40,80,20);
					txfPrice.setForeground(Color.RED);
					txfPrice.setText(CommoList.get(x).getPrice()+"Ԫ");
					pnlItem.add(txfPrice);
					
					JLabel lblAmount = new JLabel("����������");
					lblAmount.setBounds(100,70,80,20);
					pnlItem.add(lblAmount);
					
					JTextField txfAmount = new JTextField();
					txfAmount.setBounds(190, 70, 80, 20);
					pnlItem.add(txfAmount);
					txfAmount.addKeyListener(new KeyAdapter(){	//ֻ����������
						public void keyTyped(KeyEvent e) {
							int keyChar = e.getKeyChar();				
							if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){}
							else{
									e.consume(); //�ؼ������ε��Ƿ�����
								}
							}
					});
					
					JLabel lblStock = new JLabel("��棺");
					lblStock.setBounds(280,10,40,20);
					pnlItem.add(lblStock);
					
					JTextField txfStock = new JTextField();
					txfStock.setEditable(false);
					txfStock.setBounds(320,10,40,20);
					txfStock.setForeground(Color.BLUE);
					txfStock.setText(CommoList.get(x).getStock()+"��");
					pnlItem.add(txfStock);
					
					
					MyButton btnAdd = new MyButton();
					btnAdd.setBound(280,50,80,40);
					btnAdd.setImage("src/img_live/���.png");
					
					pnlItem.add(btnAdd);
					btnAdd.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(txfAmount.getText().equals("")) {JOptionPane.showMessageDialog(null, "Error:������������Ϊ�գ�");}
							else {
								int amount = Integer.parseInt(txfAmount.getText()); 
								if(amount>0) {
									Message AddMsg = new Message();
									AddMsg.setType(1804);
									AddMsg.setData(CommoList.get(x).getID()+";" + amount);
									AddMsg.setEcardNumber(EcardNumber);
									try {
										Message getMsg = new Client().start(AddMsg);
										if(getMsg.getType() == 1101) {
										JOptionPane.showMessageDialog(null, "���빺�ﳵ�ɹ�����ȥ���ﳵ���ư�o(^��^)o");
										}
										else {
											JOptionPane.showMessageDialog(null, "Error:����빺�ﳵʧ�ܣ�");
										}
									}catch (ClassNotFoundException e1) {
										e1.printStackTrace();
									}
								}
								else {JOptionPane.showMessageDialog(null, "Error:����������������");}
							}
						}
					});
					
				}
			}
				}else {
				JOptionPane.showMessageDialog(null, "Error���������ݳ������⣡"); 
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
