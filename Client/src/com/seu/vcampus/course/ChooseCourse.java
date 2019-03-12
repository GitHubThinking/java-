package com.seu.vcampus.course;

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

import com.seu.vcampus.tablechange.TableButtonEdit_Select;
import com.seu.vcampus.tablechange.TableButtonRender;

import io.Client;
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

public class ChooseCourse {

//	private JFrame frame;
	private JPanel chooseCourse;
	private JTable table;
	private JButton btnNewButton;
	private DefaultTableModel model;
	private JScrollPane jsp;
	private JButton[] button;
	private int Ecard;
	private TableButtonRender t;
	private TableButtonRender tt;

	private int innerWidth = 885;
	private int innerHeight = 370;
	
	public ChooseCourse(int number, JPanel panel) {
		
		this.Ecard=number;
			
		chooseCourse = panel;
		chooseCourse.setBounds(0, 0, innerWidth, innerHeight);
		
		table = new JTable();
		table.setBounds(0, 0, innerWidth, innerHeight);
		table.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		
		
		String[] coloumNames = {"�γ̱��", "�γ�����","����", "ѧ��", "��ʦ", "�Ͽ�ʱ��", "�ص�", "����", "״̬","�˿�"};


		
		model = new DefaultTableModel(null,coloumNames){
			private static final long serialVersionUID = 975854289835222403L;

			public boolean isCellEditable(int row, int column) {
				// ���а�ť�еĹ����������Ҫ����true��Ȼ��ť���ʱ���ᴥ���༭Ч����Ҳ�Ͳ��ᴥ���¼���
				
				if (column == 8 || column == 9 ) {
					return true;
				} else {
					return false;
				}
			}
		};
		table.setModel(model);
		t = new TableButtonRender();
//		tt = new TableButtonRender();
		table.getColumnModel().getColumn(8).setCellRenderer(new TableButtonRender());
		table.getColumnModel().getColumn(8).setCellEditor(new TableButtonEdit_Select(table, Ecard,1,null));
		table.getColumnModel().getColumn(9).setCellRenderer(new TableButtonRender());
		table.getColumnModel().getColumn(9).setCellEditor(new TableButtonEdit_Select(table, Ecard,1,null));
		table.getColumnModel().getColumn(0).setPreferredWidth(55);
		table.getColumnModel().getColumn(1).setPreferredWidth(110);
		table.getColumnModel().getColumn(2).setPreferredWidth(20);
		table.getColumnModel().getColumn(3).setPreferredWidth(15);
		table.getColumnModel().getColumn(4).setPreferredWidth(40);
		table.getColumnModel().getColumn(5).setPreferredWidth(220);
		table.getColumnModel().getColumn(6).setPreferredWidth(20);
		table.getColumnModel().getColumn(7).setPreferredWidth(40);
		table.getColumnModel().getColumn(8).setPreferredWidth(45);
		table.getColumnModel().getColumn(9).setPreferredWidth(45);
		table.setRowSelectionAllowed(false);

		//���ñ�ͷ�������С
		JTableHeader head = table.getTableHeader(); // �������������
        head.setPreferredSize(new Dimension(head.getWidth(), 35));// ���ñ�ͷ��С 
        head.setBackground(new Color(223,239,213));
        head.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		table.setRowHeight(25);//ԭ��Ҫ������
		
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
		chooseCourse.setLayout(null);

		jsp = new JScrollPane(table);
		jsp.setBounds(0, 0, innerWidth, innerHeight);
		jsp.getViewport().setBackground(new Color(255,255,255));
		
		chooseCourse.add(jsp);

	}
	public void fresh()
	{
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
							for (int i = 0; i < y.length / 3; ++i) {
								courseTime += "��" + week[Integer.parseInt(y[3 * i])] + "(" + y[3 * i + 1]
										+ "-" + y[3 * i + 2] + ") ";
							}
							String state = "";
							String state1 = "";
							if (course.getState()) {
								state = "ѡ��";
								state1 = "δѡ";
							} else {
								state = "��ѡ";
								state1 = "�˿�";
							}
							if (course.getSelected() == course.getMax()) {
								state = "����";
								state1 = "";
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
									state,
									state1
									
							});
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
	
