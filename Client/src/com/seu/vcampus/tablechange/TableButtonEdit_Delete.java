package com.seu.vcampus.tablechange;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;


import io.Client;
import util.Message;


public class TableButtonEdit_Delete extends DefaultCellEditor {

	private static final long serialVersionUID = 6252094175242686739L;
	private JButton button;
	//private ChooseCourse cs=new ChooseCourse();

	public TableButtonEdit_Delete(final JTable table, final int Ecard) {
		// DefautlCellEditor�д˹���������Ҫ����һ�������������ʹ�õ���ֱ��newһ�����ɡ�
		super(new JTextField());

		// ���õ�����μ���༭��
		this.setClickCountToStart(1);

		this.button = new JButton();

		// Ϊ��ť����¼�������ֻ�����ActionListner�¼���Mouse�¼���Ч��
		this.button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ����ȡ���༭���¼����������tableModel��setValue������
				TableButtonEdit_Delete.this.fireEditingCanceled();
				
				if (table.getValueAt(table.getSelectedRow(), 8).equals("ɾ��") && table.getSelectedColumn() == 8 ) {
					int courseNode =  (int) table.getValueAt(table.getSelectedRow(), 0);
					table.setValueAt("��ɾ", table.getSelectedRow(),8);
					deleteCourse(Integer.toString(courseNode), table, Ecard);
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

	private void deleteCourse(String courseNode, JTable table, int Ecard) {
		Message messageSend = new Message(Ecard);
		messageSend.setType(2302);
		messageSend.setData(courseNode);
		try {
			Message messageBack = new Client().start(messageSend);
			if (messageBack.getType() == 1101) {
				JOptionPane.showMessageDialog(null, "ɾ�γɹ�");
				table.setValueAt("��ɾ", table.getSelectedRow(), 8);

			} else if (messageBack.getType() == 1102) {
				JOptionPane.showMessageDialog(null,
						"ɾ��ʧ��");
			} else {
				System.out.println("ɾ��ʧ�ܣ�δ�����ݿ�����");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}