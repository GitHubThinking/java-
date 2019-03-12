

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
		// DefautlCellEditor有此构造器，需要传入一个，但这个不会使用到，直接new一个即可。
		super(new JTextField());

		//判断是选课还是已选课表，对下面选择哪种形式的响应会有用
		this.type = num;
		// 设置点击几次激活编辑。
		this.setClickCountToStart(1);

		this.button = new JButton();

		// 为按钮添加事件。这里只能添加ActionListner事件，Mouse事件无效。
		this.button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 触发取消编辑的事件，不会调用tableModel的setValue方法。
				TableButtonEdit_Select.this.fireEditingCanceled();
				
				if(type == 1) {
					if (table.getValueAt(table.getSelectedRow(), 8).equals("选课") && table.getSelectedColumn() == 8 ) {
						int courseNode =  (int) table.getValueAt(table.getSelectedRow(), 0);
						
						
						
						chooseCourse(Integer.toString(courseNode), table, Ecard);
					}
					else if (table.getValueAt(table.getSelectedRow(), 9).equals("退课") && table.getSelectedColumn() == 9 ) {
						int courseNode =  (int) table.getValueAt(table.getSelectedRow(), 0);

						
						deleteCourse(Integer.toString(courseNode), table, Ecard);
					}
				}
				else if(type == 2) {
					if (table.getValueAt(table.getSelectedRow(), 8).equals("退课") && table.getSelectedColumn() == 8 ) {
						int courseNode =  (int) table.getValueAt(table.getSelectedRow(), 0);
						int row = table.getSelectedRow();					
						deleteCourse(Integer.toString(courseNode), table, Ecard);
						model.removeRow(row);//删除某行
					}
				}


				// 这里可以做其它操作。
				// 可以将table传入，通过getSelectedRow,getSelectColumn方法获取到当前选择的行和列及其它操作等。
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

	private void chooseCourse(String courseNode, JTable table, int Ecard) {
		Message messageSend = new Message(Ecard);
		messageSend.setType(1503);
		messageSend.setData(courseNode);
		try {
			Message messageBack = new Client().start(messageSend);
			if (messageBack.getType() == 1101) {
				JOptionPane.showMessageDialog(null, "选课成功");
				String count = (String) table.getValueAt(
						table.getSelectedRow(), 7);
				String[] temp = count.split("/");
				temp[0] = String.valueOf(Integer.parseInt(temp[0]) - 1);
				count = temp[0] + "/" + temp[1];
				table.setValueAt(count, table.getSelectedRow(), 7);
				table.setValueAt("已选", table.getSelectedRow(),8);
				table.setValueAt("退课", table.getSelectedRow(),9);

			} else if (messageBack.getType() == 1515) {
				JOptionPane.showMessageDialog(null,
						"冲突课程 : " + messageBack.getData());
				table.setValueAt("冲突", table.getSelectedRow(), 8);
			} else {
				System.out.println("选课失败");
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
				JOptionPane.showMessageDialog(null, "退课成功");
				String count = (String) table.getValueAt(
						table.getSelectedRow(), 7);
				String[] temp = count.split("/");
				temp[0] = String.valueOf(Integer.parseInt(temp[0]) - 1);
				count = temp[0] + "/" + temp[1];
				table.setValueAt(count, table.getSelectedRow(), 7);
				table.setValueAt("选课", table.getSelectedRow(), 8);
				table.setValueAt("未选", table.getSelectedRow(),9);
				//cs.fresh();
			} 
			else {
				System.out.println("退课失败");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}