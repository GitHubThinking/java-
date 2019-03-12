package com.seu.vcampus.tableLibrarychange;


import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import io.Client;
import util.Message;


public class TableButtonEdit_Borrow extends DefaultCellEditor {

	private static final long serialVersionUID = 6252094175242686739L;
	private JButton button;
	//private ChooseCourse cs=new ChooseCourse();

	public TableButtonEdit_Borrow(final JTable table, final int Ecard) {
		// DefautlCellEditor�д˹���������Ҫ����һ�������������ʹ�õ���ֱ��newһ�����ɡ�
		super(new JTextField());

		// ���õ�����μ���༭��
		this.setClickCountToStart(1);

		this.button = new JButton();
		this.button.setFont(new Font("΢���ź�", Font.PLAIN, 10));
		
		// Ϊ��ť����¼�������ֻ�����ActionListner�¼���Mouse�¼���Ч��
		this.button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ����ȡ���༭���¼����������tableModel��setValue������
				TableButtonEdit_Borrow.this.fireEditingCanceled();
				
				if(table.getValueAt(table.getSelectedRow(), 9).equals("�ɽ�") &&  table.getSelectedColumn() == 9) {
					table.setValueAt("�ѽ�", table.getSelectedRow(),9);
					borrowBooks(table,Ecard);
				}
				
				// �������������������
				// ���Խ�table���룬ͨ��getSelectedRow,getSelectColumn������ȡ����ǰѡ����к��м����������ȡ�
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

	private void borrowBooks( JTable tbl, int Ecard) {
		Message messageSend = new Message(Ecard);
		messageSend.setType(1407);
		messageSend.setData((String) tbl.getValueAt(tbl.getSelectedRow(), 0));
		try {
			Message messageBack = new Client().start(messageSend);
			if (messageBack.getType() == 1101) {

				JOptionPane.showMessageDialog(null, "����ɹ�");
				
				String count = (String) tbl.getValueAt(
						tbl.getSelectedRow(), 8);
				String[] temp = count.split("/");
				temp[0] = String.valueOf(Integer.parseInt(temp[0]) - 1);
				count = temp[0] + "/" + temp[1];
				
				tbl.setValueAt(count, tbl.getSelectedRow(),8);
				tbl.setValueAt("�ѽ�", tbl.getSelectedRow(),9);
				System.out.println("����ɹ�");
			} else {
				System.out.println("����ʧ��");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	


}