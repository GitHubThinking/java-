package com.seu.vcampus.Email;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class InboxTableButtonRenderer implements TableCellRenderer{
	
	private JButton button;

	public InboxTableButtonRenderer() {
		
		this.button = new JButton();
		
		// 设置按钮的大小及位置。
		this.button.setBounds(0, 0, 50, 15);

	}


	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		// 只为按钮赋值即可。也可以作其它操作，如绘背景等。
		this.button.setText(value == null ? "" : String.valueOf(value));
		
		if(column == 3) {
			this.button.setEnabled(true);
		}
			
		return this.button;
	}

}
