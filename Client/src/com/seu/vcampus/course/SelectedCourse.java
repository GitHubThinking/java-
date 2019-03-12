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
		table.setFont(new Font("΢���ź�", Font.PLAIN, 12));
			
		String[] coloumNames = {"�γ̱��", "�γ�����","����", "ѧ��", "��ʦ", "�Ͽ�ʱ��", "�ص�", "����","�˿�"};
		Object[][] data={
				{"900001", "�ߵ���ѧ","����","4.0" , "�ο���", "�ܶ�1-2�ڣ�����3-4��", "J8-501", "100/100","�˿�"},
				{"900002", "���ݽṹ","����","4.0" , "������", "�ܶ�11-13�ڣ�����11-13�ڣ�����11-13��", "J8-501", "100/100","�˿�"},
		};
		
		model = new DefaultTableModel(null,coloumNames){
			private static final long serialVersionUID = 975854289835222403L;

			public boolean isCellEditable(int row, int column) {
				// ���а�ť�еĹ����������Ҫ����true��Ȼ��ť���ʱ���ᴥ���༭Ч����Ҳ�Ͳ��ᴥ���¼���		
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
		
		//���ñ�ͷ�������С
		JTableHeader head = table.getTableHeader(); // �������������
        head.setPreferredSize(new Dimension(head.getWidth(), 35));// ���ñ�ͷ��С
        head.setBackground(new Color(223,239,213));
        head.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		table.setRowHeight(25);//����ÿ�еĸ߶ȣ�ԭ��Ҫ������
				
		//���ݸ��б�ɫ
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
  
        //������ʾ
		r.setHorizontalAlignment(JLabel.CENTER);   
		table.setDefaultRenderer(Object.class, r);
		selectedCourse.setLayout(null);

		jsp = new JScrollPane(table);
		jsp.setBounds(0, 0, innerWidth, innerHeight);
		jsp.getViewport().setBackground(new Color(255,255,255));
		
		selectedCourse.add(jsp);

	}
	
	public void fresh() {
		// �γ̱�� �γ����� �γ����� �ڿν�ʦ �ڿ�ʱ�� �Ͽεص� ѧ�� ѡ�� �˿�
		Message messageSend = new Message(Ecard);
		messageSend.setType(1502);
		try {
			Message messageBack = new Client().start(messageSend);
			if (messageBack.getType() == 1101) {
				model.setRowCount(0);// ��ձ��
				ArrayList<CourseOffered> courseList = messageBack.getCourseList();

				for (CourseOffered course : courseList) {
					String[] y = course.getCourseTime().split("-|;");
					String courseTime = "";
					String[] week = {"","һ","��","��","��","��","��","��"};
					if(!course.getState()) {
						for (int i = 0; i < y.length / 3; ++i) {						
								courseTime += "��" + week[Integer.parseInt(y[3 * i])] + "(" + y[3 * i + 1]
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
								"�˿�",							
						});
					}
				}
			} else {
				System.out.println("δ��ѯ����ǰ��ѡ�γ�");
			}
		} catch (ClassNotFoundException event) {
			event.printStackTrace();
		}
		messageSend = null;
	}

}
