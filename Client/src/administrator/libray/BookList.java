package administrator.libray;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import io.Client;

import com.seu.vcampus.tablechange.TableButtonEdit_DBook;
import com.seu.vcampus.tablechange.TableButtonEdit_Delete;
import com.seu.vcampus.tablechange.TableButtonEdit_Select;
import com.seu.vcampus.tablechange.TableButtonRender;

import util.BookInLib;
import util.CourseOffered;
import util.Message;

//import com.seu.vcampus.client.tablechange.TableButtonEdit_Select;
//import com.seu.vcampus.client.tablechange.TableButtonRender;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;

public class BookList {

	// private JFrame frame;
	private JPanel CourseList;
	private JTable table;
	private JButton btnNewButton;
	private DefaultTableModel model;
	private JScrollPane jsp;
	private JButton[] button;
	private int Ecard;

	public BookList(int number, JPanel panel) {

		this.Ecard = number;

		CourseList = panel;
		CourseList.setBounds(0, 0, 798, 368);

		table = new JTable();
		table.setBounds(0, 0, 790, 368);
		table.setFont(new Font("微软雅黑", Font.PLAIN, 12));

		button = new JButton[5];
		for (int i = 0; i < button.length; i++) {
			button[i] = new JButton("123");
			button[i].setBounds(0, 0, 20, 20);
		}
		String[] coloumNames = { "图书编号", "图书名称", "作者", "国籍", "出版社", "收录时间", "标签", "地点", "数量", "删书" };

		model = new DefaultTableModel(null, coloumNames) {
			private static final long serialVersionUID = 975854289835222403L;

			public boolean isCellEditable(int row, int column) {
				// 带有按钮列的功能这里必须要返回true不然按钮点击时不会触发编辑效果，也就不会触发事件。

				if (column == 9) {
					return true;
				} else {
					return false;
				}
			}
		};
		table.setModel(model);
		table.getColumnModel().getColumn(9).setCellRenderer(new TableButtonRender());
		table.getColumnModel().getColumn(9).setCellEditor(new TableButtonEdit_DBook(table, Ecard));
		table.getColumnModel().getColumn(0).setPreferredWidth(55);
		table.getColumnModel().getColumn(1).setPreferredWidth(55);
		table.getColumnModel().getColumn(2).setPreferredWidth(20);
		table.getColumnModel().getColumn(3).setPreferredWidth(15);
		table.getColumnModel().getColumn(4).setPreferredWidth(40);
		table.getColumnModel().getColumn(5).setPreferredWidth(220);
		table.getColumnModel().getColumn(6).setPreferredWidth(90);
		table.getColumnModel().getColumn(7).setPreferredWidth(40);
		table.getColumnModel().getColumn(8).setPreferredWidth(45);
		table.getColumnModel().getColumn(9).setPreferredWidth(45);
		table.setRowSelectionAllowed(false);

		// 设置表头的字体大小
		JTableHeader head = table.getTableHeader(); // 创建表格标题对象
		head.setPreferredSize(new Dimension(head.getWidth(), 35));// 设置表头大小
		head.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		table.setRowHeight(25);// 原来要放这里

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
		CourseList.setLayout(null);

		jsp = new JScrollPane(table);
		jsp.setBounds(0, 0, 790, 368);

		CourseList.add(jsp);

	}

	public void fresh() {
		// 课程编号 课程性质 课程名称 授课教师 授课时间 上课地点 学分 选课 退课
		Message messageSend = new Message(Ecard);
		messageSend.setType(2203);
		try {
			Message messageBack = new Client().start(messageSend);
			if (messageBack.getType() == 1101) {
				model.setRowCount(0);// 清空表格
				ArrayList<BookInLib> bookList = messageBack.getBooklist();

				for (BookInLib book : bookList) {
					String state = "删书";
					model.addRow(new Object[] { book.getbookNumber(), book.getbookTitle(), book.getAuthor(),
							book.getAuthorCountry(), book.getPublisher(), book.getCollectDate(), book.getTabs(),
							book.getTabs(), book.getRemain() + "/" + book.getTotal(), state });
				}
			} else {
				System.out.println("没有可以删的书籍");
			}
		} catch (ClassNotFoundException event) {
			event.printStackTrace();
		}
		messageSend = null;
	}

}
