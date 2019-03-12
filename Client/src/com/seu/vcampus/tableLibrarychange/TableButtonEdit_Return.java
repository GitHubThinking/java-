package com.seu.vcampus.tableLibrarychange;

import java.awt.Component;
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

public class TableButtonEdit_Return extends DefaultCellEditor {

	private static final long serialVersionUID = 6252094175242686739L;
	private JButton button;
	// private ChooseCourse cs=new ChooseCourse();

	public TableButtonEdit_Return(final JTable table, final int Ecard, DefaultTableModel model) {
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
				TableButtonEdit_Return.this.fireEditingCanceled();

				if (table.getValueAt(table.getSelectedRow(), 6).equals("����") && table.getSelectedColumn() == 6) {
					System.out.println("����");
					int row = table.getSelectedRow();
					returnBooks(table, Ecard);
					model.removeRow(row);// ɾ��ĳ��
				} else if (table.getValueAt(table.getSelectedRow(), 7).equals("����") && table.getSelectedColumn() == 7) {
					// int courseNode = (int) table.getValueAt(table.getSelectedRow(), 0);
					System.out.println("����");
					renewBook(table, Ecard);
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
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
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

	private void returnBooks(JTable tbl, int Ecard) {
		Message messageSend = new Message(Ecard);
		messageSend.setType(1403);
		messageSend.setData((String) tbl.getValueAt(tbl.getSelectedRow(), 0));
		try {

			System.out.println(messageSend.getData());
			Message messageBack = new Client().start(messageSend);
			if (messageBack.getType() == 1101) {

				JOptionPane.showMessageDialog(null, "����ɹ�");
				System.out.println("����ɹ�");
			} else {
				System.out.println("����ʧ��");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void renewBook(JTable tbl, int Ecard) {
		Message messageSend = new Message(Ecard);
		messageSend.setType(1402);
		messageSend.setData((String) tbl.getValueAt(tbl.getSelectedRow(), 0));
		try {
			Message messageBack = new Client().start(messageSend);
			if (messageBack.getType() == 1101) {
				// tbl.setValueAt(messageBack.getData(), tbl.getSelectedRow(), 7);
				JOptionPane.showMessageDialog(null, "����ɹ�");
				System.out.println("����ɹ�");

			} else if (messageBack.getType() == 0) {
				JOptionPane.showMessageDialog(null, "���������Ѿ������ڽ����������������ˣ�");
			} else {
				System.out.println("����ʧ��");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}