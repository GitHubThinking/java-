package com.seu.vcampus.tableLibrarychange;


import java.awt.Component;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class TableButtonRender2 implements TableCellRenderer {
	// private JPanel panel;

	private JButton button;

	public TableButtonRender2() {

		this.button = new JButton();
		// 设置按钮的大小及位置。
		this.button.setBounds(0, 0, 50, 35);
		
		
	}


	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		// 只为按钮赋值即可。也可以作其它操作，如绘背景等。
		this.button.setText(value == null ? "" : String.valueOf(value));

		if (column == 9) {

			if (String.valueOf(value).equals("已借") || String.valueOf(value).equals("已满") || String.valueOf(value).equals("无"))
				this.button.setEnabled(false);
			else if(String.valueOf(value).equals("可借"))
				this.button.setEnabled(true);
		}
		
		return this.button;
	}

}
