/**
 * 
 * TableButtonRender.java
 * @author Ice
 * @version 1.0
 * 2016.9.15
 * 课程表格按钮渲染器类
 *
 */

package com.seu.vcampus.tablechange;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class TableButtonRender implements TableCellRenderer {
	// private JPanel panel;

	private JButton button;

	public TableButtonRender() {

		this.button = new JButton();

		// 设置按钮的大小及位置。
		this.button.setBounds(0, 0, 50, 15);

	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// 只为按钮赋值即可。也可以作其它操作，如绘背景等。
		this.button.setText(value == null ? "" : String.valueOf(value));
		if (column == 8) {

			if (String.valueOf(value).equals("已选") || String.valueOf(value).equals("冲突")
					|| String.valueOf(value).equals("已满") || String.valueOf(value).equals("已删"))
				this.button.setEnabled(false);
			else
				this.button.setEnabled(true);
		}

		else if (column == 9) {

			if (String.valueOf(value).equals("未选") || String.valueOf(value).equals("已删"))
				this.button.setEnabled(false);
			else if (String.valueOf(value).equals("退课"))
				this.button.setEnabled(true);
		} else if (column == 15) {

			if (String.valueOf(value).equals("已删"))
				this.button.setEnabled(false);
		}

		return this.button;
	}

}
