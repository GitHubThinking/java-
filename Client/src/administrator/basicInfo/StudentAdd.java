package administrator.basicInfo;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import io.Client;
import util.BasicInfo;
import util.CourseOffered;
import util.Message;
import java.awt.Font;

public class StudentAdd {

	private JFrame frame;
	private JTextField EcardNumber;
	private JTextField Email;
	private JTextField nameString;
	private JTextField nameSpelling;
	private JTextField gender;
	private JTextField birthday;
	private JTextField college;
	private JTextField major;

	private int Ecard;

	private JPanel bigPanel;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JTextField picturePath;
	private JTextField stuNumber;
	private JTextField IDNumber;
	private JTextField telephoneNumber;
	private JTextField identity;
	private JTextField homeAddress;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JPanel bbb = new JPanel();
					StudentAdd window = new StudentAdd(213163520, bbb);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StudentAdd(int number, JPanel panel) {
		Ecard = number;
		initialize();
		panel.add(bigPanel);
		
		lblNewLabel_9 = new JLabel("\u56FE\u7247\u8DEF\u5F84");
		lblNewLabel_9.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_9.setBounds(36, 202, 84, 27);
		bigPanel.add(lblNewLabel_9);
		
		picturePath = new JTextField();
		picturePath.setBounds(145, 198, 193, 27);
		bigPanel.add(picturePath);
		picturePath.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("\u7535\u5B50\u90AE\u4EF6");
		lblNewLabel_10.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_10.setBounds(35, 96, 84, 27);
		bigPanel.add(lblNewLabel_10);
		
		stuNumber = new JTextField();
		stuNumber.setBounds(553, 198, 201, 27);
		bigPanel.add(stuNumber);
		stuNumber.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7");
		lblNewLabel_11.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_11.setBounds(36, 234, 84, 27);
		bigPanel.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("\u8EAB\u4EFD");
		lblNewLabel_12.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_12.setBounds(478, 234, 46, 27);
		bigPanel.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("\u7535\u8BDD\u53F7\u7801");
		lblNewLabel_13.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_13.setBounds(36, 266, 84, 27);
		bigPanel.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("\u5BB6\u4E61");
		lblNewLabel_14.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_14.setBounds(478, 266, 46, 27);
		bigPanel.add(lblNewLabel_14);
		
		IDNumber = new JTextField();
		IDNumber.setBounds(145, 230, 193, 27);
		bigPanel.add(IDNumber);
		IDNumber.setColumns(10);
		
		telephoneNumber = new JTextField();
		telephoneNumber.setBounds(145, 266, 193, 27);
		bigPanel.add(telephoneNumber);
		telephoneNumber.setColumns(10);
		
		identity = new JTextField();
		identity.setBounds(553, 230, 201, 27);
		bigPanel.add(identity);
		identity.setColumns(10);
		
		homeAddress = new JTextField();
		homeAddress.setBounds(553, 266, 201, 27);
		bigPanel.add(homeAddress);
		homeAddress.setColumns(10);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153,193,104));
		frame.getContentPane().setLayout(null);

		bigPanel = new JPanel();
		bigPanel.setBackground(new Color(153,193,104));
		bigPanel.setBounds(0, 0, 818, 450);
		frame.getContentPane().add(bigPanel);
		frame.setBounds(100, 100, 857, 562);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bigPanel.setLayout(null);

		EcardNumber = new JTextField();
		EcardNumber.setBounds(145, 62, 193, 27);
		bigPanel.add(EcardNumber);
		EcardNumber.setColumns(10);

		Email = new JTextField();
		Email.setBounds(145, 96, 193, 27);
		bigPanel.add(Email);
		Email.setColumns(10);

		nameString = new JTextField();
		nameString.setBounds(145, 131, 193, 27);
		bigPanel.add(nameString);
		nameString.setColumns(10);

		nameSpelling = new JTextField();
		nameSpelling.setBounds(145, 164, 193, 27);
		bigPanel.add(nameSpelling);
		nameSpelling.setColumns(10);

		gender = new JTextField();
		gender.setBounds(553, 62, 200, 27);
		bigPanel.add(gender);
		gender.setColumns(10);

		JButton ok = new JButton("OK");
		ok.setBounds(339, 307, 101, 35);
		bigPanel.add(ok);

		JLabel lblNewLabel = new JLabel("\u4E00\u5361\u901A\u53F7");
		lblNewLabel.setBounds(36, 66, 101, 27);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		bigPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\u5B66\u53F7");
		lblNewLabel_1.setBounds(478, 205, 46, 21);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		bigPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("\u5B66\u751F\u59D3\u540D");
		lblNewLabel_2.setBounds(36, 131, 84, 24);
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		bigPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("\u59D3\u540D\u62FC\u97F3");
		lblNewLabel_3.setBounds(36, 170, 84, 21);
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 20));
		bigPanel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("\u6027\u522B");
		lblNewLabel_4.setBounds(478, 66, 46, 27);
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 20));
		bigPanel.add(lblNewLabel_4);

		birthday = new JTextField();
		birthday.setBounds(553, 96, 200, 27);
		bigPanel.add(birthday);
		birthday.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("\u751F\u65E5");
		lblNewLabel_5.setBounds(478, 100, 46, 27);
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 20));
		bigPanel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("\u5B66\u9662");
		lblNewLabel_6.setBounds(478, 135, 46, 27);
		lblNewLabel_6.setFont(new Font("宋体", Font.PLAIN, 20));
		bigPanel.add(lblNewLabel_6);

		college = new JTextField();
		college.setBounds(553, 131, 200, 27);
		bigPanel.add(college);
		college.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("\u4E13\u4E1A");
		lblNewLabel_7.setBounds(478, 168, 46, 27);
		lblNewLabel_7.setFont(new Font("宋体", Font.PLAIN, 20));
		bigPanel.add(lblNewLabel_7);

		major = new JTextField();
		major.setBounds(552, 164, 201, 27);
		bigPanel.add(major);
		major.setColumns(10);

		lblNewLabel_8 = new JLabel("\u6DFB\u52A0\u5B66\u751F");
		lblNewLabel_8.setBounds(328, 11, 135, 35);
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		bigPanel.add(lblNewLabel_8);

		ok.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				BasicInfo mystudent = new BasicInfo();
				mystudent.setEcardNumber(Integer.parseInt(EcardNumber.getText()));
				mystudent.setEmail(Email.getText());
				mystudent.setNameString(nameString.getText());
				mystudent.setNameSpelling(nameSpelling.getText());
				mystudent.setGender(Integer.parseInt(gender.getText()));
				mystudent.setBirthday(Date.valueOf(birthday.getText()));
				mystudent.setCollege(college.getText());
				mystudent.setMajor(major.getText());
				
				mystudent.setStuNumber(stuNumber.getText());
				mystudent.setIdentity(Integer.parseInt(identity.getText()));
				mystudent.setHomeAddress(homeAddress.getText());
				mystudent.setTelephone(telephoneNumber.getText());
				mystudent.setIDNumber(IDNumber.getText());
				mystudent.setPicturePath(picturePath.getText());
				mystudent.setPassword("000000");
				
				Message msg = new Message();
				msg.setBasicInfo(mystudent);
				msg.setType(2101);

				try {
					Message message = new Client().start(msg);
					if (message.getType() == 1101) {
						JOptionPane.showMessageDialog(null, "操作成功！");
					} else {
						JOptionPane.showMessageDialog(null, "Error：网络错误，请重试");
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}

		});

	}
}
