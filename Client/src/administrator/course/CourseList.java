package administrator.course;

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

import com.seu.vcampus.tablechange.TableButtonEdit_Delete;
import com.seu.vcampus.tablechange.TableButtonEdit_Select;
import com.seu.vcampus.tablechange.TableButtonRender;
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

public class CourseList {

//	private JFrame frame;
	private JPanel CourseList;
	private JTable table;
	private JButton btnNewButton;
	private DefaultTableModel model;
	private JScrollPane jsp;
	private JButton[] button;
	private int Ecard;

	public CourseList(int number, JPanel panel) {
		
		this.Ecard=number;
			
		CourseList = panel;
		CourseList.setBounds(0, 0, 798, 368);
		
		table = new JTable();
		table.setBounds(0, 0, 790, 368);
		table.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		
		button = new JButton[5];
		for(int i=0;i<button.length;i++){
			button[i] = new JButton("123");
			button[i].setBounds(0,0,20,20);
		}
		String[] coloumNames = {"�γ̱��", "�γ�����","����", "ѧ��", "��ʦ", "�Ͽ�ʱ��", "�ص�", "����","ɾ��"};
		
		

		
		model = new DefaultTableModel(null,coloumNames){
			private static final long serialVersionUID = 975854289835222403L;

			public boolean isCellEditable(int row, int column) {
				// ���а�ť�еĹ����������Ҫ����true��Ȼ��ť���ʱ���ᴥ���༭Ч����Ҳ�Ͳ��ᴥ���¼���
				
				if (column == 8) {
					return true;
				} else {
					return false;
				}
			}
		};
		table.setModel(model);
		table.getColumnModel().getColumn(8).setCellRenderer(new TableButtonRender());
		table.getColumnModel().getColumn(8).setCellEditor(new TableButtonEdit_Delete(table, Ecard));
		table.getColumnModel().getColumn(0).setPreferredWidth(55);
		table.getColumnModel().getColumn(1).setPreferredWidth(110);
		table.getColumnModel().getColumn(2).setPreferredWidth(20);
		table.getColumnModel().getColumn(3).setPreferredWidth(15);
		table.getColumnModel().getColumn(4).setPreferredWidth(40);
		table.getColumnModel().getColumn(5).setPreferredWidth(220);
		table.getColumnModel().getColumn(6).setPreferredWidth(20);
		table.getColumnModel().getColumn(7).setPreferredWidth(40);
		table.getColumnModel().getColumn(8).setPreferredWidth(45);
		table.setRowSelectionAllowed(false);

		//���ñ�ͷ�������С
		JTableHeader head = table.getTableHeader(); // �������������
        head.setPreferredSize(new Dimension(head.getWidth(), 35));// ���ñ�ͷ��С
        head.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		table.setRowHeight(25);//ԭ��Ҫ������
		
		//���ݸ��б�ɫ
		DefaultTableCellRenderer r = new DefaultTableCellRenderer(){
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                // table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
                if (row % 2 == 1)
                    setBackground(new Color(240,240,240));
                else if (row % 2 == 0)
                    setBackground(Color.white);
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
  
        //������ʾ
		r.setHorizontalAlignment(JLabel.CENTER);   
		table.setDefaultRenderer(Object.class, r);
		CourseList.setLayout(null);

		jsp = new JScrollPane(table);
		jsp.setBounds(0, 0, 790, 368);
		
		CourseList.add(jsp);

	}
	public void fresh()
	{
		// �γ̱�� �γ����� �γ����� �ڿν�ʦ �ڿ�ʱ�� �Ͽεص� ѧ�� ѡ�� �˿�
				Message messageSend = new Message(Ecard);
				messageSend.setType(2303);
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
							String state = "ɾ��";
					
							model.addRow(new Object[] {
									course.getCourseNumber(),
									course.getCourseName(),
									course.getCourseProperty(),
									course.getCourseCredit(),
									course.getTeacher(),
									courseTime,
									course.getClassroom(),
									course.getSelected() + "/" + course.getMax(), 
									state	
							});
						}
					} else {
						System.out.println("û�п���ɾ�Ŀγ�");
					}
				} catch (ClassNotFoundException event) {
					event.printStackTrace();
				}
				messageSend = null;
	}
	
}
