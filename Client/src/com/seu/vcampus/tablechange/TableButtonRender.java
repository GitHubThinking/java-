/**
 * 
 * TableButtonRender.java
 * @author Ice
 * @version 1.0
 * 2016.9.15
 * �γ̱��ť��Ⱦ����
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

		// ���ð�ť�Ĵ�С��λ�á�
		this.button.setBounds(0, 0, 50, 15);

	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// ֻΪ��ť��ֵ���ɡ�Ҳ������������������汳���ȡ�
		this.button.setText(value == null ? "" : String.valueOf(value));
		if (column == 8) {

			if (String.valueOf(value).equals("��ѡ") || String.valueOf(value).equals("��ͻ")
					|| String.valueOf(value).equals("����") || String.valueOf(value).equals("��ɾ"))
				this.button.setEnabled(false);
			else
				this.button.setEnabled(true);
		}

		else if (column == 9) {

			if (String.valueOf(value).equals("δѡ") || String.valueOf(value).equals("��ɾ"))
				this.button.setEnabled(false);
			else if (String.valueOf(value).equals("�˿�"))
				this.button.setEnabled(true);
		} else if (column == 15) {

			if (String.valueOf(value).equals("��ɾ"))
				this.button.setEnabled(false);
		}

		return this.button;
	}

}
