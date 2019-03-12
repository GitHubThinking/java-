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

import administrator.life.CommodityChange;
import administrator.life.Life;
import io.Client;
import util.Commodity;
import util.Message;



public class TableButtonEdit_UpCommo extends DefaultCellEditor {

	private static final long serialVersionUID = 6252094175242686739L;
	private JButton button;
	private int type;

	public TableButtonEdit_UpCommo(final JTable table, final int Ecard, DefaultTableModel model) {
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
				TableButtonEdit_UpCommo.this.fireEditingCanceled();
					if (table.getSelectedColumn() == 7) {
						//table.setValueAt("����", table.getSelectedRow(),7);
						Commodity commo=new Commodity();
						commo.setID((int) table.getValueAt(table.getSelectedRow(), 0));
						commo.setProduct((String)table.getValueAt(table.getSelectedRow(), 1));
						commo.setBrand((String)table.getValueAt(table.getSelectedRow(), 2));
						commo.setBriefInfo((String)table.getValueAt(table.getSelectedRow(), 3));
						commo.setPrice((double)table.getValueAt(table.getSelectedRow(), 4));
						commo.setStock((int)table.getValueAt(table.getSelectedRow(), 5));
						commo.setPicturePath((String)table.getValueAt(table.getSelectedRow(), 6));
						updateCommo(commo, table);
					}
					
					else if(table.getValueAt(table.getSelectedRow(), 8).equals("ɾ��") && table.getSelectedColumn() == 8 ) {
						int courseNode =  (int) table.getValueAt(table.getSelectedRow(), 0);
						int row = table.getSelectedRow();
						table.setValueAt("��ɾ", table.getSelectedRow(),7);
						table.setValueAt("��ɾ", table.getSelectedRow(),8);
						deleteCommo(Integer.toString(courseNode), table);
						//
						//model.removeRow(row);//ɾ��ĳ��
					}
				}
				
				// �������������������
				// ���Խ�table���룬ͨ��getSelectedRow,getSelectColumn������ȡ����ǰѡ����к��м�����������
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

	private void updateCommo(Commodity commo, JTable table) {
		CommodityChange commodityChange=new CommodityChange(commo);
		//commodityChange
	}
	
	
	private void deleteCommo(String commoID, JTable table) {
		Message messageSend = new Message();
		messageSend.setType(2402);
		messageSend.setCommoID(Integer.parseInt(commoID));
		try {
			Message messageBack = new Client().start(messageSend);
			if (messageBack.getType() == 1101) {
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
			} 
			else {
				System.out.println("�˿�ʧ��");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}