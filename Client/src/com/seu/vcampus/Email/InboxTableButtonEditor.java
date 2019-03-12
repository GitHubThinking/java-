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
		// DefautlCellEditor�д˹���������Ҫ����һ����������Ϊ����Ҫ�ã�ֱ��newһ�����ɡ�
		super(new JTextField());
		
		this.Ecard = num;
		
		//���ü���༭�������
		this.setClickCountToStart(1);

		this.button = new JButton();
		
		//Ϊ��ť����¼�������ֻ�����ActionListner�¼���Mouse�¼���Ч��
		this.button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// ����ȡ���༭���¼����������tableModel��setValue������
				InboxTableButtonEditor.this.fireEditingCanceled();
				
				if(table.getValueAt(table.getSelectedRow(), 3).equals("�Ķ�") && table.getSelectedColumn()==3) {
					
					ReadFrame(table);				
				}
			}
		});
		
	}
	
	
	/**
	 * ������д����ı༭����������һ��JPanel���󼴿ɣ�Ҳ����ֱ�ӷ���һ��Button���󣬵��������������������Ԫ��
	 */
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		// ֻΪ��ť��ֵ���ɡ�Ҳ����������������
		this.button.setText(value == null ? "" : String.valueOf(value));
		
		// return this.panel;
		return this.button;
	}
	
	
	/**
	 * ��д�༭��Ԫ��ʱ��ȡ��ֵ���������д��������ܻ�Ϊ��ť���ô����ֵ��
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
						"�����ˣ�" + emails.get(num).getSender()+
						"\n"+					
						"���⣺" + emails.get(num).getTitle() +
						"\n"+
						"ʱ�䣺" + emails.get(num).getSendingTime().toString()+
						"\n"+
						"���ݣ�\n"+
						"           "+ emails.get(num).getBody() +
						"\n";
				System.out.println(emailText);
				frame = new EmailTextFrame(emailText);
				frame.setVisible(true);
			}
			else {
				System.out.println("������");
			}
			
			
		} catch (ClassNotFoundException event) {
			event.printStackTrace();
		}
	}
	

}
