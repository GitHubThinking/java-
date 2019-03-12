package com.seu.vcampus.Email;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class InboxTableButtonRenderer implements TableCellRenderer{
	
	private JButton button;

	public InboxTableButtonRenderer() {
		
		this.button = new JButton();
		
		// ���ð�ť�Ĵ�С��λ�á�
		this.button.setBounds(0, 0, 50, 15);

	}


	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		// ֻΪ��ť��ֵ���ɡ�Ҳ������������������汳���ȡ�
		this.button.setText(value == null ? "" : String.valueOf(value));
		
		if(column == 3) {
			this.button.setEnabled(true);
		}
			
		return this.button;
	}

}
