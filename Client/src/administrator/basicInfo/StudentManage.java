package administrator.basicInfo;

import java.awt.CardLayout;
import java.awt.Color;
//import java.awt.EventQueue;

//import javax.swing.JFrame;
import javax.swing.JPanel;

import com.seu.vcampus.course.ChooseCourse;
import com.seu.vcampus.course.CourseContain;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

//import com.seu.vcampus.course.*;

public class StudentManage extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel bigPanel;

	private int Ecard;
	private JButton Button1;
	private JButton Button3;
	CardLayout cardLayout;

	// ���������л���ť
	private JButton lastbtn;

	//private CourseContain courseContain;
	@SuppressWarnings("unused")
	private StudentList listStudent;
	private StudentAdd studentAdd;

	public StudentManage(int Ecard) {
		this.Ecard = Ecard;
		System.out.println(this.Ecard);
		this.setBounds(0, 0, 818, 450);
		setLayout(null);

		Button1 = new JButton("\u6DFB\u52A0\u5B66\u751F");
		Button1.setBackground(new Color(153,193,104));
		Button1.setForeground(Color.BLACK);
		Button1.setBounds(10, 10, 140, 37);
		add(Button1);

		Button3 = new JButton("\u5B66\u751F\u5217\u8868");
		Button3.setBackground(new Color(153,193,104));
		Button3.setBounds(176, 10, 140, 37);
		add(Button3);

		cardLayout = new CardLayout();

		bigPanel = new JPanel();
		bigPanel.setBounds(10, 72, 798, 368);
		add(bigPanel);
		bigPanel.setLayout(cardLayout);

		JPanel penelAdd = new JPanel();
		penelAdd.setBounds(10, 10, 778, 348);
		bigPanel.add("penelAdd", penelAdd);
		penelAdd.setLayout(null);
		penelAdd.setBackground(new Color(255, 255, 255));
		studentAdd = new StudentAdd(Ecard, penelAdd);

		JPanel panelDelete = new JPanel();
		panelDelete.setBounds(10, 10, 778, 348);
		bigPanel.add("panelDelete", panelDelete);
		panelDelete.setLayout(null);
		JLabel label2 = new JLabel("ɾ��ѧ�� ");
		label2.setBounds(358, 10, 54, 15);
		panelDelete.add(label2);

		JPanel panelList = new JPanel();
		panelList.setBounds(10, 10, 778, 348);
		bigPanel.add("panelList", panelList);
		panelList.setLayout(null);
		listStudent = new StudentList(Ecard, panelList);

		Button1.setActionCommand("penelAdd");
		Button3.setActionCommand("panelList");

		// ���ü����¼�
		myEvent();

		// ���ð�ť��ʽ
		lastbtn = Button1;
		lastbtn.setEnabled(false);

	}

	public void myEvent() {

		Button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// changeBtn(Button1,1);

				bigPanel.repaint();
				cardLayout.show(bigPanel, "penelAdd");
				//courseContain.fresh();
				changeBtn(Button1);

			}

		});
		Button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// changeBtn(Button3,3);
				bigPanel.repaint();
				cardLayout.show(bigPanel, "panelList");
				listStudent.fresh();
				changeBtn(Button3);
			}
		});
	}

	public void changeBtn(JButton btn) {
		btn.setEnabled(false);
		lastbtn.setEnabled(true);
		lastbtn = btn;

	}
}
