package com.seu.vcampus.Email;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.seu.vcampus.tablechange.TableButtonEdit_Select;
import com.seu.vcampus.tablechange.TableButtonRender;

import javax.swing.ListSelectionModel;


import io.Client;
import util.Email;
import util.Message;

public class PanelInbox extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private DefaultTableModel model;

	private String textEmail;
    
    private int Ecard;
    private ArrayList<String>bodies;//什么时候refresh比较合适？
    private CardLayout cardLayout;
    
    private int innerWidth = 865;
	private int innerHeight = 375;
	private int left = 20;
	private int top = 140;
	private int btnWidth = 140;
	private int btnHeight = 40;
	
	private JScrollPane jsp;

	private JPanel switchPanel;
	private JPanel readPanel;
	private JPanel getPanel;
	
	public PanelInbox(int num) {
		this.Ecard=num;
		this.textEmail= null;
		
		
		setBounds(0, 0, innerWidth, innerHeight);
		setOpaque(true);
		setLayout(null);

		
		table = new JTable();
		table.setBounds(0, 0, innerWidth, innerHeight);
		table.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		table.setShowVerticalLines(false);


		String[] coloumNames = {"发送人","标题","时间","阅读"};
	
		model = new DefaultTableModel(null,coloumNames){
			private static final long serialVersionUID = 975854289835222403L;

			public boolean isCellEditable(int row, int column) {
				// 带有按钮列的功能这里必须要返回true不然按钮点击时不会触发编辑效果，也就不会触发事件。
				
				if (column == 3 ) {
					return true;
				} else {
					return false;
				}
			}
		};
		table.setModel(model);
	
		table.getColumnModel().getColumn(3).setCellRenderer(new InboxTableButtonRenderer());
		table.getColumnModel().getColumn(3).setCellEditor(new InboxTableButtonEditor(table,Ecard));
		table.getColumnModel().getColumn(0).setPreferredWidth(35);
		table.getColumnModel().getColumn(1).setPreferredWidth(400);
		table.getColumnModel().getColumn(2).setPreferredWidth(20);
		table.getColumnModel().getColumn(3).setPreferredWidth(15);

		table.setRowSelectionAllowed(false);

		//设置表头的字体大小
		JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 35));// 设置表头大小
        head.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        head.setBackground(new Color(185,234,125));
		table.setRowHeight(30);//原来要放这里
		
		//内容隔行变色
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
  
        //居中显示
		r.setHorizontalAlignment(JLabel.CENTER);   
		table.setDefaultRenderer(Object.class, r);
		setLayout(null);

				
		jsp = new JScrollPane(table);
		jsp.setBounds(0, 0, innerWidth, innerHeight);
		jsp.getViewport().setBackground(new Color(255,255,255));
		
		add(jsp);
		

	}
	
	
	public void fresh() {
		Message messageSender=new Message(Ecard);
		messageSender.setType(1702);
		try {
			Message messageReceiver = new Client().start(messageSender);
			ArrayList<Email> emails=messageReceiver.getEmailList();
			if(messageReceiver.getType()==1101) {
				model.setRowCount(0);// 清空表格
				bodies=new ArrayList<String>(emails.size());
				for(int i=0;i<emails.size();++i) {
					int sender=emails.get(i).getSender();
					String title=emails.get(i).getTitle();
					String time=emails.get(i).getSendingTime().toString();
					bodies.add(i, emails.get(i).getBody());
					String read="阅读";
					
					model.addRow(new Object[] {sender,title,time,read});
				}
			}
			else {
				System.out.println("空信箱");
			}
			
			
		} catch (ClassNotFoundException event) {
			event.printStackTrace();
		}
	}
	


  }          
