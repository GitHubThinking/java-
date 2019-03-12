package com.seu.vcampus.library;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

//import com.seu.vcampus.client.tablechange.TableWidth;
import io.Client;

import com.seu.vcampus.Special.MyTextField;
import com.seu.vcampus.tableLibrarychange.TableButtonEdit_Borrow;
import com.seu.vcampus.tableLibrarychange.TableButtonRender2;
import util.BookInLib;
import util.LibraryRecord;
import util.Message;

public class SearchBooks {

	private JFrame frame;
	private int Ecard;
	
	private JPanel searchBooks;
	private MyTextField textField;
	private JComboBox<String> comboBox;
	private JButton btnSearch;
	private JPanel pnlBookList;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane jsp;
	
	private int innerWidth = 890;
	private int innerHeight = 400;
	private int tableHeight = 310;
	private String searchType;
	private String searchContain;
	


	/**
	 * Create the application.
	 */
	public SearchBooks(int number, JPanel panel) {
	
		this.Ecard = number;
		searchBooks = panel;
		System.out.println(Ecard);
		
		searchBooks.setBounds(0, 0, innerWidth, innerHeight);
		
		searchBooks.setLayout(null);
		
		String[] searchWays={"按书名","按作者"}; 
		comboBox = new JComboBox(searchWays);
		comboBox.setBounds(180, 20, 100, 35);
		searchBooks.add(comboBox);
		
		textField = new MyTextField(20);
		textField.setBounds(295, 20, 300, 35);
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		textField.setOpaque(false);
		textField.setVisible(true);

		searchBooks.add(textField);
		textField.setColumns(10);
		
		btnSearch = new JButton("查询");
		btnSearch.setBounds(600, 20, 75, 35);
		searchBooks.add(btnSearch);
		
		pnlBookList = new JPanel();
		pnlBookList.setBounds(0, 70, innerWidth, tableHeight);
		pnlBookList.setLayout(null);
		searchBooks.add(pnlBookList);
		
		
		//显示图书
		table = new JTable();
		table.setBackground(new Color(255,255,255));	
		table.setBounds(0, 0, innerWidth, tableHeight);
		table.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		
		String[] BookMessages = {
				"书本编号", 
				"书本名称",
				"作者",
				"作者国籍",
				"出版社",
				"标签",
				"收录日期",
				"地点",
				"数量",
				"状态"
				};
		Object[][] data = {
				{
					"01010144", 
					"数据结构",
					"孙琰斌",
					"中国",
					"东南大学出版社",
					"计算机类",
					"2018-10-7",
					"李文正图书馆",
					"4/10",
					"可借"
				},
				{
					"33333334", 
					"java设计模式",
					"宋凯坤",
					"中国",
					"东南大学出版社",
					"计算机类",
					"2018-10-7",
					"李文正图书馆",
					"4/10",
					"已借"
				},
		};
		
		model = new DefaultTableModel(data,BookMessages)
		{
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				// 带有按钮列的功能这里必须要返回true不然按钮点击时不会触发编辑效果，也就不会触发事件。
				
				if (column == 9 ) {
					return true;
				} else {
					return false;
				}
			}
		};
		table.setModel(model);
		table.getColumnModel().getColumn(9).setCellRenderer(new TableButtonRender2());
		table.getColumnModel().getColumn(9).setCellEditor(new TableButtonEdit_Borrow(table, Ecard));
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(155);
		table.getColumnModel().getColumn(5).setPreferredWidth(90);
		table.getColumnModel().getColumn(6).setPreferredWidth(75);
		table.getColumnModel().getColumn(7).setPreferredWidth(75);
		table.getColumnModel().getColumn(8).setPreferredWidth(50);
		table.getColumnModel().getColumn(9).setPreferredWidth(65);
		table.setRowSelectionAllowed(false);
	
		//设置表头的字体大小
		JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 35));// 设置表头大小
        head.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		table.setRowHeight(30);//原来要放这里
		
		//内容隔行变色
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
        
        //居中显示
      	r.setHorizontalAlignment(JLabel.CENTER);   
      	table.setDefaultRenderer(Object.class, r);
      	table.setBorder(null);
      	//在jsp中导入table中
      	jsp = new JScrollPane(table);
      	jsp.setVisible(true);
		jsp.setBounds(0, 0, innerWidth, tableHeight);
		jsp.setBorder(null);
//		.setBorderPainted(false);
		pnlBookList.add(jsp);
		
		
		//添加事件
		myEvent();
	}
	
	public void myEvent() {
		comboBox.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				//如果选中了一个 
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					//这里写你的任务 ，比如取到现在的值  
					System.out.println(comboBox.getSelectedItem());							
				}				   
			}

		});
		
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//只有搜索框中有内容才能进行检索
				//searchContain
				searchContain = textField.getText();
				searchType = (String) comboBox.getSelectedItem();	
				
				if(!textField.getText().isEmpty()) {			
					searchBooks(searchType,searchContain,Ecard);
				}
				else {
					System.out.println("请输入书名");
				}
							
			}
			
		});
	}
	
	public void searchBooks(String Type, String Contain, int num) {
//		"书本编号", "书本名称","作者","作者国籍",
		/*"出版社",
		"标签",
		"收录日期",
		"地点",
		"数量",
		"状态"*/
		int EcardNumber = num;
		Message senderMessage = new Message(EcardNumber);
		if(Type.equals("按书名")){
			senderMessage.setType(1405); //按书名查找			
		} else if(Type.equals("按作者")){
			senderMessage.setType(1406);//按作者名查找
		}
		senderMessage.setData(Contain);
		try {
			final Message message =  new io.Client().start(senderMessage);
			if (message.getType() == 1101){
				model.setRowCount(0);
				ArrayList<BookInLib> tempList = message.getBooklist();
				ArrayList<LibraryRecord> recordBooks = message.getLibraryRecordList();
				int bookCount = tempList.size();
				
				
				
				//显示列表
				for (int index = 0; index < bookCount; ++index) {

					java.util.Date d=new Date(0);
					d=tempList.get(index).getCollectDate();
					ArrayList<String> y = tempList.get(index).getTabs();
					String str ="";
				
					
					for(int i = 0;i < y.size();i++) {
						str += y.get(i);
					}
					
				
					
					
					//这种方式也行，但是为了方便起见，还是使用Vector
					model.addRow(new Object[] {
						tempList.get(index).getbookNumber(),
						tempList.get(index).getbookTitle(),
						tempList.get(index).getAuthor(),
						tempList.get(index).getAuthorCountry(),
						tempList.get(index).getPublisher(),
						str,
						d.toString(),
						tempList.get(index).getPlace(),
						tempList.get(index).getRemain() + "/" + tempList.get(index).getTotal(),
						"可借"						
					});
					

				}
//				TableWidth.FitTableColumns(tblLibraryRecord);
			} else {
				JOptionPane.showMessageDialog(null, "Error：搜索无结果");
			}
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
	
	
	
	public void fresh() {
		
		Message messageSend = new Message(Ecard);
		messageSend.setType(2203);
		try {
			Message messageBack = new Client().start(messageSend);
			if (messageBack.getType() == 1101) {
				model.setRowCount(0);// 清空表格
				ArrayList<BookInLib> bookList = messageBack.getBooklist();
				int bookcount = ( bookList.size() > 10 )? 10 : bookList.size() ;
				String state = "";
				for (int i = 0; i< bookcount; i++) {
					
					
					
					if( bookList.get(i).getRemain() == 0 ) {
						state = "无";
					}else {
						state = "可借";
					}
					
					DateFormat d1 = DateFormat.getDateTimeInstance();
					java.util.Date d = bookList.get(i).getCollectDate();
					Timestamp timestamp = new Timestamp(d.getTime()); 
					
					String time = timestamp.toString();
					
					model.addRow(new Object[] { 
							bookList.get(i).getbookNumber(), 
							bookList.get(i).getbookTitle(), 
							bookList.get(i).getAuthor(),
							bookList.get(i).getAuthorCountry(),
							bookList.get(i).getPublisher(), 
							bookList.get(i).getTabs(),													
							time,
							bookList.get(i).getPlace(), 
							bookList.get(i).getRemain() + "/" + bookList.get(i).getTotal(), 
							state 
					});
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
