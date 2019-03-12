package com.seu.vcampus.live;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.seu.vcampus.Special.MyButton;
import com.seu.vcampus.Special.MyPanel;

import io.Client;
import util.Message;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class LiveTab {

	private JPanel liveTab;
	private JPanel liveSwitchTab;
	private JPanel pnlEcard;
	private JPanel pnlShop;
	private JPanel pnlEcardRecord;
	
	private JPanel pnlShopSwitch;	
	private JPanel pnlBuy;
	private JPanel pnlCart;
	private JPanel pnlRecord;
	
	private CardLayout cardlayout;
	private CardLayout cardlayout_Shop;
	
	private MyButton btnEcard;
	private MyButton btnShop;
	private MyButton btnEcardRecord;
	
	private MyButton btnFreeze;
	private MyButton btnRecharge;
	private MyButton btnUnfreeze;
	
	private JTextField txfEcard;
	private JTextField txfBalance;
	private JTextField txfEcardStatus;
	
	private int EcardNumber;
	private double EcardBalance;
	private int EcardState;
	
	private BuyTab buytab;
	private CartTab carttab;
	private RecordTab recordtab;
	private EcardRecordTab ecardrecordtab;
	private JLabel lblNewLabel;
	
	private int Width = 900;
	private int Height = 550;
	private int innerWidth = 900;
	private int innerHeight = 390;
	private int left = 20;
	private int top = 140;
	private int btnWidth = 140;
	private int btnHeight = 40;
	private int btnTop = 20;
	
	private int ShopButtonLeft = 30;
	private int pnlShopWidth = 720;
	private int pnlShopLeft = 160;
	
	//���������л���ť
	private MyButton lastbtn;
	private String LastImg;
	
	
	public LiveTab(MyPanel pnl,int Ecard) {

		
		
		
		EcardNumber = Ecard;

		liveTab = new JPanel();
		liveTab.setBounds(20, 0, Width, Height);
		liveTab.setOpaque(false);
		liveTab.setLayout(null);
		pnl.add(liveTab);
		pnl.setOpaque(false);
		
		liveSwitchTab = new JPanel();
		liveSwitchTab.setBorder(new LineBorder(new Color(155,193,104)));
		liveSwitchTab.setBounds(0,top,innerWidth,innerHeight);
		cardlayout = new CardLayout();
		liveSwitchTab.setLayout(cardlayout);
		liveTab.add(liveSwitchTab);
		
		pnlEcardRecord = new JPanel();
		pnlEcardRecord.setLayout(null);
		pnlEcardRecord.setBounds(0,30,innerWidth,innerHeight);
		liveSwitchTab.add("pnlEcardRecord", pnlEcardRecord);
		
		
		pnlEcard = new JPanel();
		pnlEcard.setLayout(null);
		pnlEcard.setBounds(0,30,innerWidth,innerHeight);
		liveSwitchTab.add("pnlEcard",pnlEcard);
		
		txfEcard = new JTextField();
		txfEcard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(107,170,27))); 
		txfEcard.setFont(new Font("΢���ź� Light", Font.PLAIN, 24));
		txfEcard.setEditable(false);
		txfEcard.setBounds(200, 140, 160, 40);
		pnlEcard.add(txfEcard);
		txfEcard.setColumns(10);
		
		JLabel lblEcard = new JLabel("\u4E00\u5361\u901A\u53F7\uFF1A");
		lblEcard.setFont(new Font("΢���ź� Light", Font.PLAIN, 24));
		lblEcard.setBounds(80, 140, 120, 40);
		pnlEcard.add(lblEcard);
		
		JLabel lblInfo = new JLabel("\u4E2A\u4EBA\u4FE1\u606F\uFF1A");
		lblInfo.setFont(new Font("΢���ź�", Font.BOLD, 31));
		lblInfo.setBounds(50, 65, 200, 55);
		pnlEcard.add(lblInfo);
		
		JLabel lblBalance = new JLabel("\u8D26\u6237\u4F59\u989D\uFF1A");
		lblBalance.setFont(new Font("΢���ź� Light", Font.PLAIN, 24));
		lblBalance.setBounds(80, 200, 120, 40);
		pnlEcard.add(lblBalance);
		
		txfBalance = new JTextField();
		txfBalance.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(107,170,27))); 
		txfBalance.setFont(new Font("΢���ź� Light", Font.PLAIN, 24));
		txfBalance.setEditable(false);
		txfBalance.setBounds(200, 200, 160, 40);
		pnlEcard.add(txfBalance);
		txfBalance.setColumns(10);
		
		JLabel lblEcardStatus = new JLabel("\u72B6\u6001\uFF1A");
		lblEcardStatus.setFont(new Font("΢���ź� Light", Font.PLAIN, 24));
		lblEcardStatus.setBounds(80, 260, 120, 40);
		pnlEcard.add(lblEcardStatus);
		
		txfEcardStatus = new JTextField();
		txfEcardStatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(107,170,27))); 
		txfEcardStatus.setFont(new Font("΢���ź� Light", Font.PLAIN, 24));
		txfEcardStatus.setEditable(false);
		txfEcardStatus.setColumns(10);
		txfEcardStatus.setBounds(200, 260, 160, 40);
		pnlEcard.add(txfEcardStatus);
		
		JLabel lblService = new JLabel("\u4E00\u5361\u901A\u670D\u52A1\uFF1A");
		lblService.setFont(new Font("΢���ź�", Font.BOLD, 31));
		lblService.setBounds(500, 65, 200, 55);
		pnlEcard.add(lblService);
		
		RechargeFrame rf = new RechargeFrame(this,Ecard);
		btnRecharge = new MyButton();
		btnRecharge.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		btnRecharge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("�����ֵ����");
				rf.setVisible(true);
			}
		});
		btnRecharge.setBound(530, 140, 120, 40);
		btnRecharge.setImage("src/img_live/��ֵ.png");
		pnlEcard.add(btnRecharge);
		
		btnFreeze = new MyButton();
		btnFreeze.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		btnFreeze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Message SendMessage = new Message();
				SendMessage.setType(1602);
				SendMessage.setEcardNumber(EcardNumber);
				try {
					Message msg = new Client().start(SendMessage);
					switch(msg.getType()) {
					case 1101:
						JOptionPane.showMessageDialog(null,"��ʧ�ɹ�!");break;
					case 0:
						JOptionPane.showMessageDialog(null, "��ʧʧ��!","fault", JOptionPane.ERROR_MESSAGE);break;
					default:
						JOptionPane.showMessageDialog(null, "������һ��δ֪����","fault", JOptionPane.ERROR_MESSAGE);break;
					}
					fresh(EcardNumber);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnFreeze.setBound(530, 200, 120, 40);
		btnFreeze.setImage("src/img_live/��ʧ.png");
		pnlEcard.add(btnFreeze);
		
		btnUnfreeze = new MyButton();
		btnUnfreeze.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		btnUnfreeze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Message SendMessage = new Message();
				SendMessage.setType(1603);
				SendMessage.setEcardNumber(EcardNumber);
				try {
					Message msg = new Client().start(SendMessage);
					switch(msg.getType()) {
					case 1101:
						JOptionPane.showMessageDialog(null,"�ⶳ�ɹ�!");break;
					case 0:
						JOptionPane.showMessageDialog(null, "��ʧʧ��!","fault", JOptionPane.ERROR_MESSAGE);break;
					default:
						JOptionPane.showMessageDialog(null, "������һ��δ֪����","fault", JOptionPane.ERROR_MESSAGE);break;
					}
					fresh(EcardNumber);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnUnfreeze.setBound(530, 260, 120, 40);
		btnUnfreeze.setImage("src/img_live/���.png");
		pnlEcard.add(btnUnfreeze);
		
		
		pnlShop = new JPanel();
		pnlShop.setLayout(null);
		pnlShop.setBounds(0,30,innerWidth,380);
		liveSwitchTab.add("pnlShop",pnlShop);
		
		pnlShopSwitch = new JPanel();
		pnlShopSwitch.setBounds(pnlShopLeft, 13,pnlShopWidth , 380);
		pnlShopSwitch.setOpaque(false);
		cardlayout_Shop = new CardLayout();
		pnlShop.add(pnlShopSwitch);
		pnlShopSwitch.setLayout(cardlayout_Shop);
		
		MyButton btnBuy = new MyButton();
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout_Shop.show(pnlShopSwitch, "pnlBuy");
				buytab.fresh();
			}
		});
		btnBuy.setBound(ShopButtonLeft, 76, 100, 60);
		btnBuy.setImage("src/img_live/F�̳�.png");
		pnlShop.add(btnBuy);
		
		
		
		MyButton btnCart = new MyButton();
		btnCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout_Shop.show(pnlShopSwitch, "pnlCart");
				carttab.fresh();
			}
		});
		btnCart.setBound(ShopButtonLeft, 151, 100, 60);
		btnCart.setImage("src/img_live/F���ﳵ.png");
		pnlShop.add(btnCart);
		
		MyButton btnRecord = new MyButton();
		btnRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout_Shop.show(pnlShopSwitch, "pnlRecord");
				recordtab.fresh();
			}
		});
		btnRecord.setBound(ShopButtonLeft, 239, 100, 60);
		btnRecord.setImage("src/img_live/F���Ѽ�¼.png");
		pnlShop.add(btnRecord);
		
	
		
		pnlBuy = new JPanel();
		pnlBuy.setLayout(null);
		pnlBuy.setBounds(0,0,innerWidth,innerHeight);
		buytab = new BuyTab(pnlBuy,EcardNumber);
		pnlShopSwitch.add("pnlBuy",pnlBuy);
		
		pnlCart = new JPanel();
		pnlCart.setLayout(null);
		pnlCart.setBounds(0, 0, 690, 380);
		carttab = new CartTab(pnlCart,EcardNumber);
		pnlShopSwitch.add("pnlCart",pnlCart);
	
		pnlRecord = new JPanel();
		pnlRecord.setLayout(null);
		pnlRecord.setBounds(0,0,690,380);
		recordtab = new RecordTab(pnlRecord,EcardNumber);
		System.out.println("һ��ͨ��������"+EcardNumber);
		pnlShopSwitch.add("pnlRecord",pnlRecord);
	
		
		fresh(Ecard);		//ˢ������
		
		cardlayout.show(liveSwitchTab,"pnlEcard");		//Ĭ����ʾΪһ��ͨ����
		
		
		
		btnEcard = new MyButton();
		//���ð�ť��ʽ
		lastbtn = btnEcard;
		LastImg = "һ��ͨ";
		
		btnEcard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("��ʾһ��ͨ����");
				
				changeBtn(btnEcard,"һ��ͨ");
				cardlayout.show(liveSwitchTab,"pnlEcard");
			}
		});
		btnEcard.setActionCommand("btnEcard");
		btnEcard.setBound(20,btnTop,btnWidth,btnHeight);
		btnEcard.setImage("src/img_live/Lһ��ͨ.png");
		//btnEcard.addActionListener(this);
		liveTab.add(btnEcard);
		
		
		btnShop = new MyButton();
		btnShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("��ʾ�̵����");
				
				changeBtn(btnShop,"�̵�");
				cardlayout.show(liveSwitchTab,"pnlShop");
				cardlayout_Shop.show(pnlShopSwitch, "pnlBuy");	//Ĭ����ʾ��Ʒ�б�
				buytab.fresh();
			}
		});
		btnShop.setActionCommand("btnShop");
		btnShop.setBound(btnWidth+20, btnTop, btnWidth, btnHeight);
		btnShop.setImage("src/img_live/D�̵�.png");
		//btnShop.addActionListener(this);
		liveTab.add(btnShop);
		
		btnEcardRecord = new MyButton();
		btnEcardRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("��ʾ��ˮ��¼");
				changeBtn(btnEcardRecord,"��ˮ��¼");
				ecardrecordtab = new EcardRecordTab(pnlEcardRecord,EcardNumber);
				ecardrecordtab.fresh();
				cardlayout.show(liveSwitchTab, "pnlEcardRecord");
			}
		});
		btnEcardRecord.setActionCommand("btnEcardRecord");
		btnEcardRecord.setBound(btnWidth*2+20, btnTop, btnWidth, btnHeight);
		btnEcardRecord.setImage("src/img_live/D��ˮ��¼.png");
		liveTab.add(btnEcardRecord);
		
		
		
	}
	public void fresh(int Ecard) {
		EcardNumber = Ecard;
		Message SendMessage  = new Message();
		SendMessage.setType(1601);
		SendMessage.setEcardNumber(Ecard);
		try {
			Message message = new Client().start(SendMessage);
			EcardBalance = message.getEcardInfo().getBalance();
			EcardState = message.getEcardInfo().getState();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		txfEcard.setText(EcardNumber+"");
		DecimalFormat df = new DecimalFormat("0.00"); 	//������2λС����������⸡������
		txfBalance.setText(df.format(EcardBalance));
		switch(EcardState) {
		case 1:
			txfEcardStatus.setText("����");
			btnUnfreeze.setEnabled(false);
			btnRecharge.setEnabled(true);
			btnFreeze.setEnabled(true);  	//����״̬�²��ɽⶳ����������
			break;
		case 2:
			txfEcardStatus.setText("��ʧ��");
			btnRecharge.setEnabled(false);
			btnUnfreeze.setEnabled(true);
			btnFreeze.setEnabled(false);   	//��ʧ�п��Խⶳ
			break;
	
		}
		
	}
	
	public void changeBtn(MyButton btn,String string) {

		if( LastImg != string) {
			btn.setImage("src/img_live/L"+string + ".png");
			
			lastbtn.setImage("src/img_live/D"+LastImg + ".png");
			
			lastbtn = btn;
			LastImg = string;
		}		
	}
}
