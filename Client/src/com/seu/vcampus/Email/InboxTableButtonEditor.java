package com.seu.vcampus.Email;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import io.Client;
import util.Message;
import util.Email;


public class InboxTableButtonEditor extends DefaultCellEditor{
	
	private static final long serialVersionUID = 1L;
	private JButton button;

	private int Ecard;
	
	private EmailTextFrame frame;
	
	public InboxTableButtonEditor(final JTable table,int num) {
		// DefautlCellEditor有此构造器，需要传入一个参数，因为不需要用，直接new一个即可。
		super(new JTextField());
		
		this.Ecard = num;
		
		//设置激活编辑点击次数
		this.setClickCountToStart(1);

		this.button = new JButton();
		
		//为按钮添加事件，这里只能添加ActionListner事件，Mouse事件无效。
		this.button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// 触发取消编辑的事件，不会调用tableModel的setValue方法。
				InboxTableButtonEditor.this.fireEditingCanceled();
				
				if(table.getValueAt(table.getSelectedRow(), 3).equals("阅读") && table.getSelectedColumn()==3) {
					
					ReadFrame(table);				
				}
			}
		});
		
	}
	
	
	/**
	 * 这里重写父类的编辑方法，返回一个JPanel对象即可（也可以直接返回一个Button对象，但是那样会填充满整个单元格）
	 */
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		// 只为按钮赋值即可。也可以作其它操作。
		this.button.setText(value == null ? "" : String.valueOf(value));
		
		// return this.panel;
		return this.button;
	}
	
	
	/**
	 * 重写编辑单元格时获取的值。如果不重写，这里可能会为按钮设置错误的值。
	 */
	@Override
	public Object getCellEditorValue() {
		return this.button.getText();
	}
	
	public void ReadFrame(JTable table) {
		Message messageSender=new Message(Ecard);
		messageSender.setType(1702);
		try {
			Message messageReceiver = new Client().start(messageSender);
			ArrayList<Email> emails=messageReceiver.getEmailList();

			if(messageReceiver.getType()==1101) {
				
				
				int num = table.getSelectedRow();		
				
				String emailText = 
						"发件人：" + emails.get(num).getSender()+
						"\n"+					
						"标题：" + emails.get(num).getTitle() +
						"\n"+
						"时间：" + emails.get(num).getSendingTime().toString()+
						"\n"+
						"内容：\n"+
						"           "+ emails.get(num).getBody() +
						"\n";
				System.out.println(emailText);
				frame = new EmailTextFrame(emailText);
				frame.setVisible(true);
			}
			else {
				System.out.println("空信箱");
			}
			
			
		} catch (ClassNotFoundException event) {
			event.printStackTrace();
		}
	}
	

}
