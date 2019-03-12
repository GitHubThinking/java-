package administrator.libray;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import io.Client;
//import util.CourseOffered;
import util.BookInLib;
import util.Message;

public class BookAdd {

	private JFrame frame;
	private JTextField bookNumber;
	private JTextField bookTitle;
	private JTextField author;
	private JTextField authorCountry;
	private JTextField pulisher;
	private JTextField tabss;
	private JTextField place;
	private JTextField total;

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
					BookAdd window = new BookAdd(213163520, bbb);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BookAdd(int number, JPanel panel) {
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

		bookNumber = new JTextField();
		bookNumber.setBounds(181, 65, 156, 41);
		bigPanel.add(bookNumber);
		bookNumber.setColumns(10);

		bookTitle = new JTextField();
		bookTitle.setBounds(181, 118, 156, 41);
		bigPanel.add(bookTitle);
		bookTitle.setColumns(10);

		author = new JTextField();
		author.setBounds(181, 177, 156, 41);
		bigPanel.add(author);
		author.setColumns(10);

		authorCountry = new JTextField();
		authorCountry.setBounds(181, 227, 156, 41);
		bigPanel.add(authorCountry);
		authorCountry.setColumns(10);

		pulisher = new JTextField();
		pulisher.setBounds(598, 65, 156, 41);
		bigPanel.add(pulisher);
		pulisher.setColumns(10);

		JButton ok = new JButton("OK");
		ok.setBounds(339, 293, 101, 35);
		bigPanel.add(ok);

		JLabel lblNewLabel = new JLabel("\u56FE\u4E66\u7F16\u53F7");
		lblNewLabel.setBounds(36, 68, 135, 35);
		bigPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\u56FE\u4E66\u540D\u79F0");
		lblNewLabel_1.setBounds(36, 121, 135, 35);
		bigPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("\u56FE\u4E66\u4F5C\u8005");
		lblNewLabel_2.setBounds(36, 180, 135, 35);
		bigPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("\u4F5C\u8005\u56FD\u7C4D");
		lblNewLabel_3.setBounds(36, 230, 135, 35);
		bigPanel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("\u51FA\u7248\u5355\u4F4D");
		lblNewLabel_4.setBounds(437, 68, 135, 35);
		bigPanel.add(lblNewLabel_4);

		tabss = new JTextField();
		tabss.setBounds(598, 118, 156, 41);
		bigPanel.add(tabss);
		tabss.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("\u56FE\u4E66\u6807\u7B7E");
		lblNewLabel_5.setBounds(437, 121, 135, 35);
		bigPanel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("\u9986\u85CF\u4F4D\u7F6E");
		lblNewLabel_6.setBounds(437, 180, 135, 35);
		bigPanel.add(lblNewLabel_6);

		place = new JTextField();
		place.setBounds(598, 177, 156, 41);
		bigPanel.add(place);
		place.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("\u56FE\u4E66\u6570\u91CF");
		lblNewLabel_7.setBounds(437, 230, 135, 35);
		bigPanel.add(lblNewLabel_7);

		total = new JTextField();
		total.setBounds(598, 227, 156, 41);
		bigPanel.add(total);
		total.setColumns(10);

		lblNewLabel_8 = new JLabel("\u6DFB\u52A0\u56FE\u4E66");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBounds(328, 26, 135, 35);
		bigPanel.add(lblNewLabel_8);

		ok.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				BookInLib mybook = new BookInLib();
				mybook.setbookNumber(bookNumber.getText());
				mybook.setbookTitle(bookTitle.getText());
				mybook.setAuthor(author.getText());
				mybook.setAuthorCountry(authorCountry.getText());
				mybook.setPublisher(pulisher.getText());
				String[]tabs=tabss.getText().split(",");
				ArrayList<String> _tabs=new ArrayList<String>();
				for(int i=0;i<tabs.length;i++)
					_tabs.add(tabs[i]);
				mybook.setTabs(_tabs);
				mybook.setPlace(place.getText());
				mybook.setTotal(Integer.parseInt(total.getText()));
				mybook.setRemain(Integer.parseInt(total.getText()));
				Message msg = new Message();
				msg.setbook(mybook);
				msg.setType(2201);

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
