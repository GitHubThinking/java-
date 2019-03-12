

package com.seu.vcampus.tablechange;

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


public class TableButtonEdit_Select extends DefaultCellEditor {

	private static final long serialVersionUID = 6252094175242686739L;
	private JButton button;
	private int type;

	public TableButtonEdit_Select(final JTable table, final int Ecard,int num,DefaultTableModel model) {
		// DefautlCellEditor�д˹���������Ҫ����һ�������������ʹ�õ���ֱ��newһ�����ɡ�
		super(new JTextField());

		//�ж���ѡ�λ�����ѡ�α�������ѡ��������ʽ����Ӧ������
		this.type = num;
		// ���õ�����μ���༭��
		this.setClickCountToStart(1);

		this.button = new JButton();

		// Ϊ��ť����¼�������ֻ�����ActionListner�¼���Mouse�¼���Ч��
		this.button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ����ȡ���༭���¼����������tableModel��setValue������
				TableButtonEdit_Select.this.fireEditingCanceled();
				
				if(type == 1) {
					if (table.getValueAt(table.getSelectedRow(), 8).equals("ѡ��") && table.getSelectedColumn() == 8 ) {
						int courseNode =  (int) table.getValueAt(table.getSelectedRow(), 0);
						
						
						
						chooseCourse(Integer.toString(courseNode), table, Ecard);
					}
					else if (table.getValueAt(table.getSelectedRow(), 9).equals("�˿�") && table.getSelectedColumn() == 9 ) {
						int courseNode =  (int) table.getValueAt(table.getSelectedRow(), 0);

						
						deleteCourse(Integer.toString(courseNode), table, Ecard);
					}
				}
				else if(type == 2) {
					if (table.getValueAt(table.getSelectedRow(), 8).equals("�˿�") && table.getSelectedColumn() == 8 ) {
						int courseNode =  (int) table.getValueAt(table.getSelectedRow(), 0);
						int row = table.getSelectedRow();					
						deleteCourse(Integer.toString(courseNode), table, Ecard);
						model.removeRow(row);//ɾ��ĳ��
					}
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

	private void chooseCourse(String courseNode, JTable table, int Ecard) {
		Message messageSend = new Message(Ecard);
		messageSend.setType(1503);
		messageSend.setData(courseNode);
		try {
			Message messageBack = new Client().start(messageSend);
			if (messageBack.getType() == 1101) {
				JOptionPane.showMessageDialog(null, "ѡ�γɹ�");
				String count = (String) table.getValueAt(
						table.getSelectedRow(), 7);
				String[] temp = count.split("/");
				temp[0] = String.valueOf(Integer.parseInt(temp[0]) - 1);
				count = temp[0] + "/" + temp[1];
				table.setValueAt(count, table.getSelectedRow(), 7);
				table.setValueAt("��ѡ", table.getSelectedRow(),8);
				table.setValueAt("�˿�", table.getSelectedRow(),9);

			} else if (messageBack.getType() == 1515) {
				JOptionPane.showMessageDialog(null,
						"��ͻ�γ� : " + messageBack.getData());
				table.setValueAt("��ͻ", table.getSelectedRow(), 8);
			} else {
				System.out.println("ѡ��ʧ��");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	private void deleteCourse(String courseNode, JTable table, int Ecard) {
		Message messageSend = new Message(Ecard);
		messageSend.setType(1504);
		messageSend.setData(courseNode);
		try {
			Message messageBack = new Client().start(messageSend);
			if (messageBack.getType() == 1101) {
				JOptionPane.showMessageDialog(null, "�˿γɹ�");
				String count = (String) table.getValueAt(
						table.getSelectedRow(), 7);
				String[] temp = count.split("/");
				temp[0] = String.valueOf(Integer.parseInt(temp[0]) - 1);
				count = temp[0] + "/" + temp[1];
				table.setValueAt(count, table.getSelectedRow(), 7);
				table.setValueAt("ѡ��", table.getSelectedRow(), 8);
				table.setValueAt("δѡ", table.getSelectedRow(),9);
				//cs.fresh();
			} 
			else {
				System.out.println("�˿�ʧ��");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}