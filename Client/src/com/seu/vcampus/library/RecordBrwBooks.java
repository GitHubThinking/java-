package com.seu.vcampus.library;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


import io.Client;
import util.BookInLib;
import util.LibraryRecord;
import util.Message;


public class RecordBrwBooks {

	private JFrame frame;
	private int innerWidth = 880;
	private int innerHeight = 400;
	
	private JPanel recordbooks;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane jsp;
	
	private int Ecard;
	
	
	
	public RecordBrwBooks(int number, JPanel panel) {
		this.Ecard = number;
		recordbooks = panel;
		System.out.println(Ecard);
		recordbooks.setBounds(0, 0, innerWidth, innerHeight);
		recordbooks.setLayout(null);
			
		
		table = new JTable();
		table.setBackground(new Color(255,255,255));
		
		table.setBounds(0, 0, innerWidth, innerHeight);
		table.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		recordbooks.add(table);
		
		String[] BookMessages = {
				"�鱾���", 
				"�鱾����",
				"����",
				"������",		
				"��������",
				"��������"
				};
		
		model = new DefaultTableModel(null,BookMessages) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				// ���а�ť�еĹ����������Ҫ����true��Ȼ��ť���ʱ���ᴥ���༭Ч����Ҳ�Ͳ��ᴥ���¼���
				return false;				
			}
		};
		
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(75);
		table.getColumnModel().getColumn(4).setPreferredWidth(75);
		table.getColumnModel().getColumn(4).setPreferredWidth(75);
		table.setRowSelectionAllowed(false);
		
		//���ñ�ͷ�������С
		JTableHeader head = table.getTableHeader(); // �������������
        head.setPreferredSize(new Dimension(head.getWidth(), 35));// ���ñ�ͷ��С
        head.setBackground(new Color(223,239,213));
        head.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		table.setRowHeight(30);//ԭ��Ҫ������
		
		//���ݸ��б�ɫ
		DefaultTableCellRenderer r = new DefaultTableCellRenderer(){
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                // table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
                if (row % 2 == 1)
                    setBackground(new Color(223,239,213));
                else if (row % 2 == 0)
                    setBackground(Color.white);
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
		
      //������ʾ
      	r.setHorizontalAlignment(JLabel.CENTER);   
      	table.setDefaultRenderer(Object.class, r);
      	table.setBorder(null);
      	//��jsp�е���table��
      	jsp = new JScrollPane(table);
      	jsp.setVisible(true);
		jsp.setBounds(0, 0, innerWidth, innerHeight);
		jsp.setBorder(null);
		jsp.getViewport().setBackground(new Color(255,255,255));
		recordbooks.add(jsp);
	}

	public void fresh() {
//		"�鱾���", 
//		"�鱾����",
//		"����",
//		"������",			
//		"��������",
//		"��������"
		Message senderMessage = new Message(Ecard);
		senderMessage.setType(1404);
		try {
			final Message messageLib =  new Client().start(senderMessage);
			
			//final ArrayList<BookInLib> tempList = messageLib.getBooklist();
			ArrayList<LibraryRecord> tempNumberList = messageLib.getLibraryRecordList();
			if (messageLib.getType() == 1101)
			{//�������ݳɹ�
				
				int bookCount = tempNumberList.size();
				if(bookCount == 0)
				{
					JOptionPane.showMessageDialog(null, "Error��û�н��ļ�¼"); 
				} else {
					model.setRowCount(0);
					for (int index = 0; index < bookCount; ++index) {
						
						Date d = tempNumberList.get(index).getBorrowTime();
						
						model.addRow(new Object[] {
								tempNumberList.get(index).getBookNumber(),
								tempNumberList.get(index).getbookTitle(),
								tempNumberList.get(index).getAuthor(),
								tempNumberList.get(index).getPublisher(),
								tempNumberList.get(index).getBorrowTime(),
								tempNumberList.get(index).getReturnTime(),
								"����",
								"����"							
						});
								
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Error��δ���յ���Ϣ��"); 
			}
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
		
		
	}
}
