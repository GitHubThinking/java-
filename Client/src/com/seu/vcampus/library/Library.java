package com.seu.vcampus.library;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.seu.vcampus.Special.MyButton;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Library extends JPanel{

	private JFrame frame;
	private	MyButton btnBorrowBooks;
	private MyButton btnSearchBooks;
	private MyButton btnRecordedBooks;
	
	private JPanel panel;
	private JPanel Bigpanel;
	
	private JPanel pnlBorrowBooks;
	private JPanel pnlSearchBooks;
	private JPanel pnlRecordedBooks;
	private CardLayout cardLayout;
	
	private BorrowedBooks borrowBooks;
	private SearchBooks searchBooks;
	private RecordBrwBooks recordedBooks;
	
	private int Ecard;
	
	private int Width = 900;
	private int Height = 550;
	private int innerWidth = 880;
	private int innerHeight = 400;
	private int left = 20;
	private int top = 135;
	private int btnWidth = 140;
	private int btnHeight = 40;
	private int btnLeft = 20;
	//���������л���ť
	private MyButton lastbtn;
	private String LastImg;


	public Library(int number) {
		this.Ecard = number;
		
		this.setBounds(left, 0, Width, Height);
		
		this.setLayout(null);
		this.setOpaque(false);
		
		//�л����������
		Bigpanel = new JPanel();
		Bigpanel.setBounds(10, top, Width, innerHeight);
		this.add(Bigpanel);
		cardLayout = new CardLayout();
		Bigpanel.setLayout(cardLayout);//�л�����

		
		btnBorrowBooks = new MyButton();
		btnBorrowBooks.setBound(btnLeft, 20, btnWidth, btnHeight);
		btnBorrowBooks.setImage("src/img_Library/L�ѽ�ͼ��.png");
		this.add(btnBorrowBooks);
		
		
		
		btnSearchBooks = new MyButton();
		btnSearchBooks.setBound(btnLeft+btnWidth, 20, btnWidth, btnHeight);
		btnSearchBooks.setImage("src/img_Library/D����.png");
		this.add(btnSearchBooks);
		
		btnRecordedBooks= new MyButton();
		btnRecordedBooks.setBound(btnLeft+btnWidth*2, 20, btnWidth, btnHeight);
		btnRecordedBooks.setImage("src/img_Library/D���ļ�¼.png");
		this.add(btnRecordedBooks);
		
		
		//�л��Ľ���
		pnlBorrowBooks = new JPanel();
		pnlBorrowBooks.setBounds(0, 0, innerWidth, innerHeight);
		Bigpanel.add("pnlBorrowBooks", pnlBorrowBooks);
		borrowBooks = new BorrowedBooks(Ecard,pnlBorrowBooks);
		
		
		pnlSearchBooks = new JPanel();
		pnlSearchBooks.setBounds(0, 0, innerWidth, innerHeight);
		Bigpanel.add("pnlSearchBooks", pnlSearchBooks);
		searchBooks = new SearchBooks(Ecard,pnlSearchBooks);
		
		
		pnlRecordedBooks = new JPanel();
		pnlRecordedBooks.setBounds(0, 0, innerWidth, innerHeight);
		Bigpanel.add("pnlRecordedBooks", pnlRecordedBooks);
		recordedBooks = new RecordBrwBooks(Ecard,pnlRecordedBooks);
	
		
		// ���ü����¼�
		myEvent();
		
		//���ð�ť��ʽ
		lastbtn = btnBorrowBooks;
		LastImg = "�ѽ�ͼ��";
	}


	public void myEvent() {
		btnBorrowBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				Bigpanel.repaint();
				cardLayout.show(Bigpanel, "pnlBorrowBooks");
				borrowBooks.fresh();
				changeBtn(btnBorrowBooks,"�ѽ�ͼ��");		
			}
			
		});
		btnSearchBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				Bigpanel.repaint();
				cardLayout.show(Bigpanel, "pnlSearchBooks");
				searchBooks.fresh();
				changeBtn(btnSearchBooks,"����");		
			}
			
		});
		btnRecordedBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

	
				Bigpanel.repaint();
				cardLayout.show(Bigpanel, "pnlRecordedBooks");
				recordedBooks.fresh();
				changeBtn(btnRecordedBooks,"���ļ�¼");		
			}
			
		});
	}
	
	public void changeBtn(MyButton btn,String string) {
		
		if( LastImg != string) {
			btn.setImage("src/img_Library/L" + string + ".png");
			
			lastbtn.setImage("src/img_Library/D" + LastImg + ".png");

			LastImg = string;
			lastbtn = btn;
		}
				
	}
}
