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
		// DefautlCellEditor有此构造器，需要传入一个，但这个不会使用到，直接new一个即可。
		super(new JTextField());

		
		
		// 设置点击几次激活编辑。
		this.setClickCountToStart(1);

		this.button = new JButton();

		// 为按钮添加事件。这里只能添加ActionListner事件，Mouse事件无效。
		this.button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 触发取消编辑的事件，不会调用tableModel的setValue方法。
				TableButtonEdit_UpCommo.this.fireEditingCanceled();
					if (table.getSelectedColumn() == 7) {
						//table.setValueAt("更新", table.getSelectedRow(),7);
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
					
					else if(table.getValueAt(table.getSelectedRow(), 8).equals("删除") && table.getSelectedColumn() == 8 ) {
						int courseNode =  (int) table.getValueAt(table.getSelectedRow(), 0);
						int row = table.getSelectedRow();
						table.setValueAt("已删", table.getSelectedRow(),7);
						table.setValueAt("已删", table.getSelectedRow(),8);
						deleteCommo(Integer.toString(courseNode), table);
						//
						//model.removeRow(row);//删除某行
					}
				}
				
				// 这里可以做其它操作。
				// 可以将table传入，通过getSelectedRow,getSelectColumn方法获取到当前选择的行和列及其它操作等
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
				JOptionPane.showMessageDialog(null, "删除成功");
			} 
			else {
				System.out.println("退课失败");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}