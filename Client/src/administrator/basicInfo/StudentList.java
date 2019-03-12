package administrator.basicInfo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import io.Client;

import com.only.OnlyScrollPane;
import com.only.OnlyTable;
import com.seu.vcampus.tablechange.TableButtonEdit_DStudent;
import com.seu.vcampus.tablechange.TableButtonEdit_Delete;
import com.seu.vcampus.tablechange.TableButtonEdit_Select;
import com.seu.vcampus.tablechange.TableButtonRender;

import util.BasicInfo;
import util.CourseOffered;
import util.Message;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;

public class StudentList {

	OnlyScrollPane scrollPane;
	// private JFrame frame;
	private JPanel StudentList;
	private OnlyTable table;
	// private JButton btnNewButton;
	private DefaultTableModel model;
	private JScrollPane jsp;
	private JButton[] button;
	private int Ecard;

	public StudentList(int number, JPanel panel) {
		
		

		this.Ecard = number;
		StudentList = new JPanel();
		//StudentList = panel;
		StudentList.setBounds(0, 0, 798, 368);
		panel.add(StudentList);
		StudentList.setLayout(null);

		table = new OnlyTable();
		
		scrollPane = new OnlyScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(0, 0, 790, 368);
		StudentList.add(scrollPane);
		
		table.setBounds(0, 0, 790, 368);
		table.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		
		
		button = new JButton[5];
		for (int i = 0; i < button.length; i++) {
			button[i] = new JButton("123");
			button[i].setBounds(0, 0, 20, 20);
		}
		final String[] columnNames = { "一卡通号", "姓名", "姓名拼音", "生日", "照片路径", "身份证号", "学号", "性别", "学院", "专业", "邮箱", "身份类型",
				"密码", "联系方式", "地址", "开除" };

		model = new DefaultTableModel(null, columnNames) {
			private static final long serialVersionUID = 975854289835222403L;

			public boolean isCellEditable(int row, int column) {
				// 带有按钮列的功能这里必须要返回true不然按钮点击时不会触发编辑效果，也就不会触发事件。

				if (column == 15) {
					return true;
				} else {
					return false;
				}
			}
		};
		table.setModel(model);
		table.getColumnModel().getColumn(15).setCellRenderer(new TableButtonRender());
		table.getColumnModel().getColumn(15).setCellEditor(new TableButtonEdit_DStudent(table, Ecard));
		table.getColumnModel().getColumn(0).setPreferredWidth(55);
		table.getColumnModel().getColumn(1).setPreferredWidth(55);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getColumnModel().getColumn(5).setPreferredWidth(220);
		table.getColumnModel().getColumn(6).setPreferredWidth(60);
		table.getColumnModel().getColumn(7).setPreferredWidth(50);
		table.getColumnModel().getColumn(8).setPreferredWidth(80);
		table.getColumnModel().getColumn(9).setPreferredWidth(120);
		table.getColumnModel().getColumn(10).setPreferredWidth(120);
		table.getColumnModel().getColumn(11).setPreferredWidth(50);
		table.getColumnModel().getColumn(12).setPreferredWidth(50);
		table.getColumnModel().getColumn(13).setPreferredWidth(80);
		table.getColumnModel().getColumn(14).setPreferredWidth(50);
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
		//StudentList.setLayout(null);

		//jsp = new JScrollPane(table);
		//jsp.setBounds(0, 0, 790, 368);
		//StudentList.add(jsp);

	}

	public void fresh() {
		// 课程编号 课程性质 课程名称 授课教师 授课时间 上课地点 学分 选课 退课
		Message messageSend = new Message(Ecard);
		messageSend.setType(2103);
		try {
			Message messageBack = new Client().start(messageSend);
			if (messageBack.getType() == 1101) {
				model.setRowCount(0);// 清空表格
				// ArrayList<CourseOffered> courseList = messageBack.getCourseList();
				ArrayList<BasicInfo> studentList = messageBack.getAllBasicInfo();
				for (BasicInfo student : studentList) {

					String state = "开除";

					model.addRow(new Object[] { student.getEcardNumber(), student.getNameString(),
							student.getNameSpelling(), student.getBirthday().toString(), student.getPicturePath(),
							student.getIDNumber(), student.getStuNumber(), student.getGender(), student.getCollege(),
							student.getMajor(), student.getEmail(), student.getIdentity(), student.getPassword(),
							student.getTelephone(), student.getHomeAddress(), state });
				}
			} else {
				System.out.println("没有可以开除的学生");
			}
		} catch (ClassNotFoundException event) {
			event.printStackTrace();
		}
		messageSend = null;
	}

}
