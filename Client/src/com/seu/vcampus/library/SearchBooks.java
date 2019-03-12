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
		
		String[] searchWays={"������","������"}; 
		comboBox = new JComboBox(searchWays);
		comboBox.setBounds(180, 20, 100, 35);
		searchBooks.add(comboBox);
		
		textField = new MyTextField(20);
		textField.setBounds(295, 20, 300, 35);
		textField.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		textField.setOpaque(false);
		textField.setVisible(true);

		searchBooks.add(textField);
		textField.setColumns(10);
		
		btnSearch = new JButton("��ѯ");
		btnSearch.setBounds(600, 20, 75, 35);
		searchBooks.add(btnSearch);
		
		pnlBookList = new JPanel();
		pnlBookList.setBounds(0, 70, innerWidth, tableHeight);
		pnlBookList.setLayout(null);
		searchBooks.add(pnlBookList);
		
		
		//��ʾͼ��
		table = new JTable();
		table.setBackground(new Color(255,255,255));	
		table.setBounds(0, 0, innerWidth, tableHeight);
		table.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		
		String[] BookMessages = {
				"�鱾���", 
				"�鱾����",
				"����",
				"���߹���",
				"������",
				"��ǩ",
				"��¼����",
				"�ص�",
				"����",
				"״̬"
				};
		Object[][] data = {
				{
					"01010144", 
					"���ݽṹ",
					"������",
					"�й�",
					"���ϴ�ѧ������",
					"�������",
					"2018-10-7",
					"������ͼ���",
					"4/10",
					"�ɽ�"
				},
				{
					"33333334", 
					"java���ģʽ",
					"�ο���",
					"�й�",
					"���ϴ�ѧ������",
					"�������",
					"2018-10-7",
					"������ͼ���",
					"4/10",
					"�ѽ�"
				},
		};
		
		model = new DefaultTableModel(data,BookMessages)
		{
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				// ���а�ť�еĹ����������Ҫ����true��Ȼ��ť���ʱ���ᴥ���༭Ч����Ҳ�Ͳ��ᴥ���¼���
				
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
	
		//���ñ�ͷ�������С
		JTableHeader head = table.getTableHeader(); // �������������
        head.setPreferredSize(new Dimension(head.getWidth(), 35));// ���ñ�ͷ��С
        head.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		table.setRowHeight(30);//ԭ��Ҫ������
		
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
      	table.setBorder(null);
      	//��jsp�е���table��
      	jsp = new JScrollPane(table);
      	jsp.setVisible(true);
		jsp.setBounds(0, 0, innerWidth, tableHeight);
		jsp.setBorder(null);
//		.setBorderPainted(false);
		pnlBookList.add(jsp);
		
		
		//����¼�
		myEvent();
	}
	
	public void myEvent() {
		comboBox.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				//���ѡ����һ�� 
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					//����д������� ������ȡ�����ڵ�ֵ  
					System.out.println(comboBox.getSelectedItem());							
				}				   
			}

		});
		
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//ֻ���������������ݲ��ܽ��м���
				//searchContain
				searchContain = textField.getText();
				searchType = (String) comboBox.getSelectedItem();	
				
				if(!textField.getText().isEmpty()) {			
					searchBooks(searchType,searchContain,Ecard);
				}
				else {
					System.out.println("����������");
				}
							
			}
			
		});
	}
	
	public void searchBooks(String Type, String Contain, int num) {
//		"�鱾���", "�鱾����","����","���߹���",
		/*"������",
		"��ǩ",
		"��¼����",
		"�ص�",
		"����",
		"״̬"*/
		int EcardNumber = num;
		Message senderMessage = new Message(EcardNumber);
		if(Type.equals("������")){
			senderMessage.setType(1405); //����������			
		} else if(Type.equals("������")){
			senderMessage.setType(1406);//������������
		}
		senderMessage.setData(Contain);
		try {
			final Message message =  new io.Client().start(senderMessage);
			if (message.getType() == 1101){
				model.setRowCount(0);
				ArrayList<BookInLib> tempList = message.getBooklist();
				ArrayList<LibraryRecord> recordBooks = message.getLibraryRecordList();
				int bookCount = tempList.size();
				
				
				
				//��ʾ�б�
				for (int index = 0; index < bookCount; ++index) {

					java.util.Date d=new Date(0);
					d=tempList.get(index).getCollectDate();
					ArrayList<String> y = tempList.get(index).getTabs();
					String str ="";
				
					
					for(int i = 0;i < y.size();i++) {
						str += y.get(i);
					}
					
				
					
					
					//���ַ�ʽҲ�У�����Ϊ�˷������������ʹ��Vector
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
						"�ɽ�"						
					});
					

				}
//				TableWidth.FitTableColumns(tblLibraryRecord);
			} else {
				JOptionPane.showMessageDialog(null, "Error�������޽��");
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
				model.setRowCount(0);// ��ձ��
				ArrayList<BookInLib> bookList = messageBack.getBooklist();
				int bookcount = ( bookList.size() > 10 )? 10 : bookList.size() ;
				String state = "";
				for (int i = 0; i< bookcount; i++) {
					
					
					
					if( bookList.get(i).getRemain() == 0 ) {
						state = "��";
					}else {
						state = "�ɽ�";
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
				System.out.println("û�п���ɾ���鼮");
			}
		} catch (ClassNotFoundException event) {
			event.printStackTrace();
		}
		messageSend = null;
	}
}
