
package administrator.course;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import io.Client;
import util.CourseOffered;
import util.Message;

public class CourseAdd {

	private JFrame frame;
	private JTextField courseNumber;
	private JTextField courseName;
	private JTextField courseCredit;
	private JTextField teacher;
	private JTextField courseTime;
	private JTextField classroom;
	private JTextField courseProperty;
	private JTextField max;

	private int Ecard;

	private JPanel bigPanel;
	private JLabel lblNewLabel_8;

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
					CourseAdd window = new CourseAdd(213163520, bbb);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CourseAdd(int number, JPanel panel) {
		Ecard = number;
		initialize();
		panel.add(bigPanel);
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
		bigPanel.setLayout(null);
		frame.getContentPane().add(bigPanel);
		frame.setBounds(100, 100, 857, 562);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		courseNumber = new JTextField();
		courseNumber.setBounds(181, 65, 156, 41);
		bigPanel.add(courseNumber);
		courseNumber.setColumns(10);

		courseName = new JTextField();
		courseName.setBounds(181, 118, 156, 41);
		bigPanel.add(courseName);
		courseName.setColumns(10);

		courseCredit = new JTextField();
		courseCredit.setBounds(181, 177, 156, 41);
		bigPanel.add(courseCredit);
		courseCredit.setColumns(10);

		teacher = new JTextField();
		teacher.setBounds(181, 227, 156, 41);
		bigPanel.add(teacher);
		teacher.setColumns(10);

		courseTime = new JTextField();
		courseTime.setBounds(598, 65, 156, 41);
		bigPanel.add(courseTime);
		courseTime.setColumns(10);

		JButton ok = new JButton("OK");
		ok.setBounds(339, 293, 101, 35);
		bigPanel.add(ok);

		JLabel lblNewLabel = new JLabel("\u8BFE\u7A0B\u7F16\u53F7");
		lblNewLabel.setBounds(36, 68, 135, 35);
		bigPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\u8BFE\u7A0B\u540D\u79F0");
		lblNewLabel_1.setBounds(36, 121, 135, 35);
		bigPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("\u6388\u8BFE\u5B66\u5206");
		lblNewLabel_2.setBounds(36, 180, 135, 35);
		bigPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("\u6388\u8BFE\u6559\u5E08");
		lblNewLabel_3.setBounds(36, 230, 135, 35);
		bigPanel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("\u6388\u8BFE\u65F6\u95F4");
		lblNewLabel_4.setBounds(437, 68, 135, 35);
		bigPanel.add(lblNewLabel_4);

		classroom = new JTextField();
		classroom.setBounds(598, 118, 156, 41);
		bigPanel.add(classroom);
		classroom.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("\u6388\u8BFE\u5730\u70B9");
		lblNewLabel_5.setBounds(437, 121, 135, 35);
		bigPanel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("\u8BFE\u7A0B\u7C7B\u578B");
		lblNewLabel_6.setBounds(437, 180, 135, 35);
		bigPanel.add(lblNewLabel_6);

		courseProperty = new JTextField();
		courseProperty.setBounds(598, 177, 156, 41);
		bigPanel.add(courseProperty);
		courseProperty.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("\u53EF\u9009\u4EBA\u6570");
		lblNewLabel_7.setBounds(437, 230, 135, 35);
		bigPanel.add(lblNewLabel_7);

		max = new JTextField();
		max.setBounds(598, 227, 156, 41);
		bigPanel.add(max);
		max.setColumns(10);

		lblNewLabel_8 = new JLabel("\u6DFB\u52A0\u8BFE\u7A0B");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBounds(328, 26, 135, 35);
		bigPanel.add(lblNewLabel_8);

		ok.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				CourseOffered mycourse = new CourseOffered();
				mycourse.setCourseNumber(Integer.parseInt(courseNumber.getText()));
				mycourse.setCourseName(courseName.getText());
				mycourse.setCourseCredit(Double.parseDouble(courseCredit.getText()));
				mycourse.setTeacher(teacher.getText());
				mycourse.setCourseTime(courseTime.getText());
				mycourse.setClassroom(classroom.getText());
				mycourse.setCourseProperty(courseProperty.getText());
				mycourse.setMax(Integer.parseInt(max.getText()));
				mycourse.setSelected(0);
				mycourse.setState(false);
				Message msg = new Message();
				msg.setCourse(mycourse);
				msg.setType(2301);

				try {
					Message message = new Client().start(msg);
					if (message.getType() == 1101) {
						JOptionPane.showMessageDialog(null, "²Ù×÷³É¹¦£¡");
					} else {
						JOptionPane.showMessageDialog(null, "Error£ºÍøÂç´íÎó£¬ÇëÖØÊÔ");
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}

		});

	}
}
