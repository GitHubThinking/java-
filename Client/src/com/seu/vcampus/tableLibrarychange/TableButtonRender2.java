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
		// ���ð�ť�Ĵ�С��λ�á�
		this.button.setBounds(0, 0, 50, 35);
		
		
	}


	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		// ֻΪ��ť��ֵ���ɡ�Ҳ������������������汳���ȡ�
		this.button.setText(value == null ? "" : String.valueOf(value));

		if (column == 9) {

			if (String.valueOf(value).equals("�ѽ�") || String.valueOf(value).equals("����") || String.valueOf(value).equals("��"))
				this.button.setEnabled(false);
			else if(String.valueOf(value).equals("�ɽ�"))
				this.button.setEnabled(true);
		}
		
		return this.button;
	}

}
