package com.seu.vcampus.course;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.seu.vcampus.tablechange.TableButtonEdit_Select;
import com.seu.vcampus.tablechange.TableButtonRender;

import io.Client;
import util.CourseOffered;
import util.Message;

public class SelectedCourse {

	private JPanel selectedCourse;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane jsp;
	private int Ecard;
	
	private int innerWidth = 885;
	private int innerHeight = 370;
	
	
	public SelectedCourse(int number, JPanel panel) {
		this.Ecard = number;
		
		selectedCourse = panel;
		selectedCourse.setBounds(0, 0, innerWidth, innerHeight);
		
		table = new JTable();
		table.setBounds(0, 0, innerWidth, innerHeight);
		table.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			
		String[] coloumNames = {"课程编号", "课程名称","类型", "学分", "教师", "上课时间", "地点", "人数","退课"};
		Object[][] data={
				{"900001", "高等数学","必修","4.0" , "宋凯坤", "周二1-2节，周四3-4节", "J8-501", "100/100","退课"},
				{"900002", "数据结构","必修","4.0" , "孙琰斌", "周二11-13节，周四11-13节，周五11-13节", "J8-501", "100/100","退课"},
		};
		
		model = new DefaultTableModel(null,coloumNames){
			private static final long serialVersionUID = 975854289835222403L;

			public boolean isCellEditable(int row, int column) {
				// 带有按钮列的功能这里必须要返回true不然按钮点击时不会触发编辑效果，也就不会触发事件。		
				if (column == 8 ) {
					return true;
				} else {
					return false;
				}
			}
		};
		
		table.setModel(model);
		table.getColumnModel().getColumn(8).setCellRenderer(new TableButtonRender());
		table.getColumnModel().getColumn(8).setCellEditor(new TableButtonEdit_Select(table,Ecard,2,model));
		table.getColumnModel().getColumn(0).setPreferredWidth(55);
		table.getColumnModel().getColumn(1).setPreferredWidth(110);
		table.getColumnModel().getColumn(2).setPreferredWidth(20);
		table.getColumnModel().getColumn(3).setPreferredWidth(15);
		table.getColumnModel().getColumn(4).setPreferredWidth(40);
		table.getColumnModel().getColumn(5).setPreferredWidth(220);
		table.getColumnModel().getColumn(6).setPreferredWidth(20);
		table.getColumnModel().getColumn(7).setPreferredWidth(40);
		table.getColumnModel().getColumn(8).setPreferredWidth(50);
		table.setRowSelectionAllowed(false);
		
		//设置表头的字体大小
		JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 35));// 设置表头大小
        head.setBackground(new Color(223,239,213));
        head.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		table.setRowHeight(25);//设置每行的高度！原来要放这里
				
		//内容隔行变色
		DefaultTableCellRenderer r = new DefaultTableCellRenderer(){

			private static final long serialVersionUID = 1L;

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
  
        //居中显示
		r.setHorizontalAlignment(JLabel.CENTER);   
		table.setDefaultRenderer(Object.class, r);
		selectedCourse.setLayout(null);

		jsp = new JScrollPane(table);
		jsp.setBounds(0, 0, innerWidth, innerHeight);
		jsp.getViewport().setBackground(new Color(255,255,255));
		
		selectedCourse.add(jsp);

	}
	
	public void fresh() {
		// 课程编号 课程性质 课程名称 授课教师 授课时间 上课地点 学分 选课 退课
		Message messageSend = new Message(Ecard);
		messageSend.setType(1502);
		try {
			Message messageBack = new Client().start(messageSend);
			if (messageBack.getType() == 1101) {
				model.setRowCount(0);// 清空表格
				ArrayList<CourseOffered> courseList = messageBack.getCourseList();

				for (CourseOffered course : courseList) {
					String[] y = course.getCourseTime().split("-|;");
					String courseTime = "";
					String[] week = {"","一","二","三","四","五","六","日"};
					if(!course.getState()) {
						for (int i = 0; i < y.length / 3; ++i) {						
								courseTime += "周" + week[Integer.parseInt(y[3 * i])] + "(" + y[3 * i + 1]
										+ "-" + y[3 * i + 2] + ") ";						
						}		
						
						model.addRow(new Object[] {
								course.getCourseNumber(),
								course.getCourseName(),
								course.getCourseProperty(),
								course.getCourseCredit(),
								course.getTeacher(),
								courseTime,
								course.getClassroom(),
								course.getSelected() + "/" + course.getMax(), 
								"退课",							
						});
					}
				}
			} else {
				System.out.println("未查询到当前可选课程");
			}
		} catch (ClassNotFoundException event) {
			event.printStackTrace();
		}
		messageSend = null;
	}

}
