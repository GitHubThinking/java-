package com.seu.vcampus.live;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import com.only.OnlyScrollPane;
import com.only.OnlyTable;

import io.Client;
import util.Commodity;
import util.Message;
import util.ShoppingRecord;

public class RecordTab {

	private int EcardNumber;
	private JPanel pnlRecord;
	private OnlyTable Record_Table;
	private OnlyScrollPane sp;
	private DefaultTableModel defaultModel;
	

	private int Width = 900;
	private int Height = 550;
	private int innerWidth = 900;
	private int innerHeight = 385;
	private int left = 20;
	private int top = 150;
	private int btnWidth = 140;
	private int btnHeight = 40;
	
	public RecordTab(JPanel panel,int Ecard) {
		EcardNumber = Ecard;
		pnlRecord =  new JPanel();
		pnlRecord.setBounds(0,0,innerWidth,innerHeight);
		pnlRecord.setOpaque(false);
		pnlRecord.setLayout(null);
		panel.add(pnlRecord);

		String[] n = {"商品编号","商品名称","商品数量","商品价格(元)","购买时间","商品介绍"};
		defaultModel = new DefaultTableModel(null,n) {
			public boolean isCellEditable(int row, int column) {
					return false;			//表格内容不可编辑
			}
		};

	}

	void fresh() {
		Message SendMessage = new Message();
		SendMessage.setType(1802);
		SendMessage.setEcardNumber(EcardNumber);
		
		try {
			//System.out.print("是"+EcardNumber);
			Message msg = new Client().start(SendMessage);
			//System.out.println("一卡通号为:"+msg.getEcardNumber());
			ArrayList<ShoppingRecord> recordList = msg.getShoppingRecord();
			//System.out.println("购买列表为："+recordList.toString());
			ArrayList<Commodity> commodity = msg.getCommoList();
			//System.out.println("商品列表为："+commodity.toString());
			if (msg.getType() == 1101) {//返回数据成功
				//double totalPrice=0;
				
				int goodsNumber = recordList.size();
				//System.out.println("购买的商品件数为"+goodsNumber);
				if(goodsNumber == 0){
					JOptionPane.showMessageDialog(null, "Error：没有购物记录╮(╯Д╰)╭ "); 
				} else {
					defaultModel.setRowCount(0);
					for (int index = goodsNumber-1; index > 0; index--) {
						Vector<String> tempVector = new Vector<String>(6);
						tempVector.add(""+recordList.get(index).getCommoID());
						tempVector.add(""+commodity.get(index).getProduct());
						tempVector.add(""+recordList.get(index).getPurchaseAmount());
						tempVector.add(""+commodity.get(index).getPrice());
						tempVector.add(""+recordList.get(index).getPurchaseTime().toString());
						tempVector.add(""+commodity.get(index).getBriefInfo());
						defaultModel.addRow(tempVector);
						//totalPrice = totalPrice + commodity.get(index).getPrice()*recordList.get(index).getPurchaseAmount();
					}
				}
				Record_Table = new OnlyTable();
				Record_Table.setModel(defaultModel);
				Record_Table.setBounds(0, 0, innerWidth, innerHeight);
				sp = new OnlyScrollPane(Record_Table);
				sp.setViewportView(Record_Table);
				sp.getViewport().setBackground(new Color(255,255,255));
				sp.setBounds(0, 0, innerWidth, innerHeight);
				pnlRecord.add(sp);	
			} else {
				JOptionPane.showMessageDialog(null, "Error：未接收到信息！╮(╯Д╰)╭ "); 
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}
	
	
}
