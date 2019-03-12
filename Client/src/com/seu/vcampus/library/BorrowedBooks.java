package com.seu.vcampus.library;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
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

import com.seu.vcampus.tableLibrarychange.TableButtonEdit_Return;
import com.seu.vcampus.tableLibrarychange.TableButtonRender2;
import util.LibraryRecord;

import io.Client;
import util.BookInLib;
import util.LibraryRecord;
import util.Message;

public class BorrowedBooks {

	private JFrame frame;
	private JPanel borrowedBooks;
	private JTable table;

	private JScrollPane jsp;
	private DefaultTableModel model;
	private int innerWidth = 880;
	private int innerHeight = 400;

	private int Ecard;

	public BorrowedBooks(int number, JPanel panel) {

		// frame = new JFrame();
		// frame.setBounds(100, 100, 1000, 600);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.getContentPane().setLayout(null);

		this.Ecard = number;
		System.out.println(Ecard);
		borrowedBooks = panel;

		borrowedBooks.setBounds(0, 0, innerWidth, innerHeight);
		borrowedBooks.setLayout(null);

		table = new JTable();
		table.setBackground(new Color(255, 255, 255));
		table.setBounds(0, 0, innerWidth, innerHeight);
		table.setFont(new Font("微软雅黑", Font.PLAIN, 12));

		String[] BookMessages = { "书本编号", "书本名称", "作者", "出版社", "借书日期", "应还日期", "还书", "续借" };
		model = new DefaultTableModel(null, BookMessages) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				// 带有按钮列的功能这里必须要返回true不然按钮点击时不会触发编辑效果，也就不会触发事件。

				if (column == 6 || column == 7) {
					return true;
				} else {
					return false;
				}
			}
		};
		table.setModel(model);
		table.getColumnModel().getColumn(6).setCellRenderer(new TableButtonRender2());
		table.getColumnModel().getColumn(6).setCellEditor(new TableButtonEdit_Return(table, Ecard, model));
		table.getColumnModel().getColumn(7).setCellRenderer(new TableButtonRender2());
		table.getColumnModel().getColumn(7).setCellEditor(new TableButtonEdit_Return(table, Ecard, model));
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setPreferredWidth(180);
		table.getColumnModel().getColumn(4).setPreferredWidth(70);
		table.getColumnModel().getColumn(5).setPreferredWidth(70);
		table.getColumnModel().getColumn(6).setPreferredWidth(50);
		table.getColumnModel().getColumn(7).setPreferredWidth(50);
		table.setRowSelectionAllowed(false);

		// 设置表头的字体大小
		JTableHeader head = table.getTableHeader(); // 创建表格标题对象
		head.setPreferredSize(new Dimension(head.getWidth(), 35));// 设置表头大小
		head.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		table.setRowHeight(30);// 原来要放这里

		// 内容隔行变色
		DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				// table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
				if (row % 2 == 1)
					setBackground(new Color(240, 240, 240));
				else if (row % 2 == 0)
					setBackground(Color.white);
				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
		};

		// 居中显示
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);
		table.setBorder(null);
		// 在jsp中导入table中
		jsp = new JScrollPane(table);
		jsp.setVisible(true);
		jsp.setBounds(0, 0, innerWidth, innerHeight);
		jsp.setBorder(null);
		// .setBorderPainted(false);
		borrowedBooks.add(jsp);

		fresh();
	}

	public void fresh() {
		// "书本编号", "书本名称","作者","出版社""借书日期","应还日期","还书","续借"
		Message messageSend = new Message(Ecard);
		messageSend.setType(1401);
		try {
			Message messageLib = new Client().start(messageSend);
			ArrayList<LibraryRecord> tempList = messageLib.getLibraryRecordList();

			if (messageLib.getType() == 1101) {// 返回数据成功

				int bookCount = tempList.size();
				model.setRowCount(0);

				for (int index = 0; index < bookCount; index++) {
					if (tempList.get(index).getbookTitle() != null) {

						model.addRow(new Object[] { tempList.get(index).getBookNumber(),
								tempList.get(index).getbookTitle(), tempList.get(index).getAuthor(),
								tempList.get(index).getPublisher(), tempList.get(index).getBorrowTime().toString(),
								tempList.get(index).getReturnTime().toString(), "还书", "续借" });
					}
				}
			} 
			else {
				JOptionPane.showMessageDialog(null, "Error：未接收到信息！");
			}
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
	}

}
