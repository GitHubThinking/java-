package com.seu.vcampus.live;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import com.only.OnlyScrollPane;
import com.only.OnlyTable;

import io.Client;
import util.Commodity;
import util.Message;
import util.ShoppingCart;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class CartTab {
	private JPanel pnlCart;
	private JPanel pnlGoods;
	private int EcardNumber;
	private JScrollPane cartList;
	private JLabel lblTotal;
	private JLabel lblTotalM;
	private JButton btnPurchase;
	
	public CartTab(JPanel panel,int Ecard) {
		EcardNumber = Ecard;
		
		pnlCart =  new JPanel();
		pnlCart.setBackground(SystemColor.activeCaptionBorder);
		pnlCart.setBounds(0,0,850,382);
		pnlCart.setOpaque(false);
		pnlCart.setLayout(null);
		panel.add(pnlCart);
		
		pnlGoods = new JPanel();
		pnlGoods.setBounds(0,0,850,340);
		pnlGoods.setLayout(null);
		pnlGoods.setBackground(SystemColor.inactiveCaptionBorder);
		pnlGoods.setPreferredSize(new Dimension(850,500));		//同样默认可以放5个方格
		pnlCart.add(pnlGoods);
		
		cartList = new JScrollPane();
		cartList.setBounds(0,0,850,340);
		cartList.setBackground(SystemColor.inactiveCaptionBorder);
		cartList.setLayout(null);
		pnlCart.add(cartList);
		
		lblTotalM = new JLabel("0.00");
		lblTotalM.setForeground(Color.red);
		lblTotalM.setBounds(445, 352, 72, 18);
		pnlCart.add(lblTotalM);
		
		btnPurchase = new JButton("\u8D2D\u4E70");
		btnPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Message msgBuy_s = new Message();
				msgBuy_s.setEcardNumber(EcardNumber);
				msgBuy_s.setType(1806);
				try {
					Message msgBuy_r = new Client().start(msgBuy_s);
					switch(msgBuy_r.getType()) {
					case 1101:
						JOptionPane.showMessageDialog(null, "购买成功!");
						lblTotalM.setText("0.00");
						pnlGoods.removeAll();
						fresh();//重新加载
						pnlGoods.repaint();break;
					case 7777:
						JOptionPane.showMessageDialog(null, "余额不足了QAQ");break;
					default:
						JOptionPane.showMessageDialog(null, "数据传输出错，购买失败");break;
					}
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnPurchase.setEnabled(false);
		btnPurchase.setBounds(563, 348, 113, 27);
		pnlCart.add(btnPurchase);
		
		lblTotal = new JLabel("\u603B\u4EF7\u683C\uFF1A");
		lblTotal.setBounds(359, 352, 72, 18);
		pnlCart.add(lblTotal);
		
		
		
		JButton btnDelAll = new JButton("\u6E05\u7A7A");
		btnDelAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "是否确定清空？");
				if(option == JOptionPane.YES_OPTION) {
					Message msgDelAll_s = new Message();
					msgDelAll_s.setType(1803);
					msgDelAll_s.setEcardNumber(EcardNumber);
					try {
						Message msgDelAll_r = new Client().start(msgDelAll_s);
						if(msgDelAll_r.getType() == 1101) {
							JOptionPane.showMessageDialog(null, "购物车清空成功");
							pnlGoods.removeAll();
							pnlGoods.repaint();
						}
					} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
					}
				}
			
			}
		});
		btnDelAll.setBounds(26, 348, 113, 27);
		pnlCart.add(btnDelAll);
//		JLabel lblCart = new JLabel("Cart");
//		lblCart.setBounds(228, 129, 72, 18);
//		pnlCart.add(lblCart);

		
	}
	void fresh() {

		Message SendMessage = new Message();	//先显示购物车基本信息
		SendMessage.setType(1801);
		SendMessage.setEcardNumber(EcardNumber);
		try {
			Message infoMsg = new Client().start(SendMessage);
			if(infoMsg.getType() == 1101) {
			ArrayList<ShoppingCart> scList = infoMsg.getShoppingCartList();
			ArrayList<Commodity> commoList = infoMsg.getCommoList();
			int scListSize = scList.size();
			
			int []select = new int[scListSize];		//用于表示购物车中商品选择状态
			for(int i = 0;i<scListSize;i++) {
				select[i] = 0;		//初始化为0.未选中状态
			}
			
			double []totalMoney=new double[scListSize];		//存储每个方块的商品总价值
			for(int i = 0;i<scListSize;i++) {
				totalMoney[i] = 0;		//初始化为0元
			}
			
			System.out.println("购物车大小为："+scListSize);
			if(scListSize == 0) {
				JOptionPane.showMessageDialog(null, "购物车是空的哦！");
			}
			else {
				if(scListSize>5)
					pnlGoods.setPreferredSize(new Dimension(690,100*scListSize));//列表扩容
				for (int index = 0; index < scListSize; index++) {
				JPanel pnlItem = new JPanel();	//用于存放每样商品的信息
				pnlItem.setBackground(Color.white);	
				pnlItem.setBounds(0,index*100,800,100);
				pnlItem.setBorder(BorderFactory.createTitledBorder(""));//设置面板边框，实现分组框的效果
				pnlItem.setBorder(BorderFactory.createLineBorder(Color.gray));//设置面板边框颜色
				pnlGoods.add(pnlItem);
				pnlItem.setLayout(null);
				
				final int x = index;
				int amount = scList.get(x).getCommoAmount();		//购物车中商品数目
				
				JLabel lblPicture = new JLabel();
				lblPicture.setBounds(10,10,80,80);
				ImageIcon ii = new ImageIcon("pictures/goods/"+commoList.get(x).getPicturePath()+".jpg");
				ii.setImage(ii.getImage().getScaledInstance(80, 80,Image.SCALE_DEFAULT));//
				lblPicture.setIcon(ii);
				lblPicture.setHorizontalAlignment(SwingConstants.CENTER);
				pnlItem.add(lblPicture);
				
				JLabel lblName = new JLabel();
				lblName.setBounds(150,10,80,80);
				lblName.setText(commoList.get(x).getProduct());
				pnlItem.add(lblName);
				
				
				JLabel lblPrice = new JLabel();
				lblPrice.setBounds(250,10,100,80);
				lblPrice.setText("单价："+commoList.get(x).getPrice()+"元");
				lblPrice.setForeground(Color.red);
				pnlItem.add(lblPrice);
				
				JTextField txfAmount = new JTextField();
				txfAmount.setBounds(460,40,60,20);
				txfAmount.setEditable(false);
				txfAmount.setHorizontalAlignment(JTextField.CENTER);
				txfAmount.setText(""+amount);
				pnlItem.add(txfAmount);
				txfAmount.addKeyListener(new KeyAdapter(){	//只能输入数字
					public void keyTyped(KeyEvent e) {
						int keyChar = e.getKeyChar();				
						if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){}
						else{
								e.consume(); //关键，屏蔽掉非法输入
							}
						}
				});
				
				JButton btnSub = new JButton("-");
				btnSub.setMargin(new Insets(0,0,0,0));
				btnSub.setBounds(430,40,20,20);
				pnlItem.add(btnSub);
				btnSub.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int a = Integer.parseInt((txfAmount.getText())) - 1;
						if(a == 0) {
							btnSub.setEnabled(false);
						}
						txfAmount.setText(a+"");
						if(select[x]==1) {
							lblTotalM.setText(Double.parseDouble(lblTotalM.getText())-commoList.get(x).getPrice()+"");
						}
						Message msgSub_s = new Message();
						msgSub_s.setType(1807);
						msgSub_s.setEcardNumber(EcardNumber);
						msgSub_s.setData(commoList.get(x).getID()+";"+a);
						try {
							Message msgSub_r = new Client().start(msgSub_s);
							if(msgSub_r.getType() == 1101) {
								System.out.println("购物车数据修改成功");
							}
							else {
								System.out.println("购物车数据修改失败");
							}
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						}
						
						
					}
				});
				
				
				JButton btnAdd = new JButton("+");
				btnAdd.setMargin(new Insets(0,0,0,0));
				btnAdd.setBounds(530,40,20,20);
				pnlItem.add(btnAdd);
				btnAdd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int a = Integer.parseInt((txfAmount.getText())) + 1;
						txfAmount.setText(a+"");
						btnSub.setEnabled(true);
						if(select[x]==1) {
							lblTotalM.setText(Double.parseDouble(lblTotalM.getText())+commoList.get(x).getPrice()+"");
						}
						Message msgAdd_s = new Message();
						msgAdd_s.setType(1807);
						msgAdd_s.setEcardNumber(EcardNumber);
						msgAdd_s.setData(commoList.get(x).getID()+";"+a);
						try {
							Message msgAdd_r = new Client().start(msgAdd_s);
							if(msgAdd_r.getType() == 1101) {
								System.out.println("购物车数据修改成功");
							}
							else {
								System.out.println("购物车数据修改失败");
							}
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						}
					}
				});
				
				JCheckBox ckbState = new JCheckBox();
				ckbState.setBounds(580,40,20,20);
				pnlItem.add(ckbState);
				ckbState.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						select[x]=1-select[x];	//改变选择状态
						totalMoney[x] = Integer.parseInt((txfAmount.getText()))*select[x]*commoList.get(x).getPrice();
						double money=0;
						int flag=0;
						for(int i=0;i<scListSize;i++) {
							money+=totalMoney[i];
							flag+=select[i];
						}
						if(flag>0) {
							btnPurchase.setEnabled(true);
						}
						else {
							btnPurchase.setEnabled(false);
						}
						lblTotalM.setText(money+"");
					}
				});
				
				JButton btnDelete = new JButton("删除");
				btnDelete.setBounds(620,35,60,30);
				btnDelete.setMargin(new Insets(0,0,0,0));
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Message delMsg_s = new Message();
						delMsg_s.setType(1805);
						delMsg_s.setEcardNumber(EcardNumber);
						delMsg_s.setData(commoList.get(x).getID()+"");
						try {
							Message delMsg_r = new Client().start(delMsg_s);
							if(delMsg_r.getType() == 1101) {
								System.out.println("商品删除成功");
								pnlGoods.removeAll();
								fresh();//重新加载
								pnlGoods.repaint();
							}
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						}
						
					}
				});
				pnlItem.add(btnDelete);
				
				}
			}
			
			}else {
				JOptionPane.showMessageDialog(null, "Error：接收数据出现问题！"); 
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
