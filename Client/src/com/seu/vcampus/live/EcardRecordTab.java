package com.seu.vcampus.live;

import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import com.only.OnlyScrollPane;
import com.only.OnlyTable;

import io.Client;
import util.EcardRecord;
import util.Message;

public class EcardRecordTab {
	private JPanel pnlRecord;
	private OnlyTable recordTable;
	private OnlyScrollPane sp;
	private int EcardNumber;
	private DefaultTableModel defaultModel;
	
	private int Width = 900;
	private int Height = 550;
	private int innerWidth = 900;
	private int innerHeight = 400;
	private int left = 20;
	private int top = 30;
	private int btnWidth = 140;
	private int btnHeight = 40;
	
	public EcardRecordTab(JPanel panel,int Ecard) {
		EcardNumber = Ecard;
		pnlRecord = new JPanel();
		pnlRecord.setLayout(null);
		pnlRecord.setBounds(0,0,innerWidth,innerHeight);
		panel.add(pnlRecord);
		
		String[] n = {"时间","操作","金额收支（元）","账户余额(元)"};
		defaultModel = new DefaultTableModel(null,n) {
			public boolean isCellEditable(int row, int column) {
					return false;			//表格内容不可编辑
			}
		};
		


		
		
	}
	
	void fresh() {
		
		Message SendMessage = new Message();
		SendMessage.setType(1605);
		SendMessage.setEcardNumber(EcardNumber);
		
		try {
			Message msg = new Client().start(SendMessage);
			ArrayList<EcardRecord> recordList = msg.getEcardRecordList();
			//System.out.print("是"+EcardNumber);
			if (msg.getType() == 1101) {//返回数据成功
				
				int opNumber = recordList.size();
				if(opNumber == 0){
					JOptionPane.showMessageDialog(null, "Error：没有流水记录╮(╯Д╰)╭ "); 
				} else {
					defaultModel.setRowCount(0);
					for (int index = opNumber-1; index > 0; index--) {
						Vector<String> tempVector = new Vector<String>(6);
						tempVector.add(""+recordList.get(index).getActionDate().toString());
						tempVector.add(""+recordList.get(index).getAction());
						DecimalFormat df = new DecimalFormat("0.00"); 	//将金额保留2位小数输出，避免浮点陷阱
						tempVector.add(df.format(recordList.get(index).getBalance()-recordList.get(index-1).getBalance()));
						tempVector.add(df.format(recordList.get(index).getBalance()));
						defaultModel.addRow(tempVector);
					}
			
				}
				
				recordTable = new OnlyTable();
				recordTable.setModel(defaultModel);
				recordTable.setFont(new Font("微软雅黑", Font.PLAIN, 14));
				recordTable.setRowHeight(30);//原来要放这里
				
				sp = new OnlyScrollPane(recordTable);
				sp.setViewportView(recordTable);
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
