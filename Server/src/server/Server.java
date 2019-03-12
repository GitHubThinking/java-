package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.only.OnlyFrame;
import com.only.OnlyIPV4AddressField;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Server extends OnlyFrame {

	private static final long serialVersionUID = 8262545505418678622L;
	private JPanel panel;
	private OnlyIPV4AddressField textFieldIP;
	private JTextArea textArea;
	public volatile boolean exit = false;
	private JButton btnStart = new JButton("");
	private JButton btnStop = new JButton("");

	public Server() {
		getTitlePane().getContentPane().setBackground(Color.WHITE);
		setTitle("MyServer");
		setResizable(false);// 禁止窗口更改大小及移动
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		btnStop.setIcon(new ImageIcon(Server.class.getResource("/img_close_open/close.png")));
		btnStop.setBackground(Color.WHITE);
		btnStop.setEnabled(false);
		btnStop.setBounds(130, 217, 40, 38);
		panel.add(btnStop);

		JLabel lblNewLabel = new JLabel("IP:");
		lblNewLabel.setBackground(Color.CYAN);
		lblNewLabel.setBounds(26, 81, 61, 21);
		panel.add(lblNewLabel);

		JLabel lblPort = new JLabel("PORT:");
		lblPort.setBackground(Color.CYAN);
		lblPort.setBounds(26, 128, 61, 21);
		panel.add(lblPort);

		InetAddress addr;
		String IP = null;
		try {
			addr = InetAddress.getLocalHost();
			IP = addr.getHostAddress().toString();// 获得本机IP
			System.out.println(IP);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		textFieldIP = new OnlyIPV4AddressField();
		textFieldIP.setForeground(Color.WHITE);
		textFieldIP.setBackground(Color.BLACK);
		textFieldIP.setText(IP);
		textFieldIP.setBounds(87, 76, 134, 26);
		panel.add(textFieldIP);
		textFieldIP.setColumns(10);

		JLabel txtPort = new JLabel();
		txtPort.setForeground(Color.BLACK);
		txtPort.setBackground(Color.BLACK);
		txtPort.setText("7777");
		txtPort.setBounds(112, 130, 82, 17);
		panel.add(txtPort);
		btnStart.setIcon(new ImageIcon(Server.class.getResource("/img_close_open/selected.png")));
		btnStart.setBackground(Color.WHITE);
		
		btnStart.setBounds(50, 217, 40, 38);
		panel.add(btnStart);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		scrollPane.setBounds(247, 49, 191, 241);
		panel.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setRowHeaderView(textArea);

		setVisible(true);


		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit = true;
				textArea.setText(textArea.getText() + "\n" + "服务端终止");
				// Run = false;
				btnStop.setEnabled(false);
				btnStart.setEnabled(true);
				new Thread(new Runnable() {
					public void run() {
						start(7777);
					}
				}).start();
			}
		});
		
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						exit = false;
						textArea.setText(textArea.getText() + "\n" + "启动成功");
						btnStart.setEnabled(false);
						btnStop.setEnabled(true);
						start(7777);
					}
				}).start();
			}
		});
		
	}

	public static void main(String[] args) {
		
		new Server();
	}

	@SuppressWarnings("resource")
	public void start(int port) {
		ServerSocket s = null;
		try {
			s = new ServerSocket(port);
		} catch (IOException e) {
			textArea.setText(textArea.getText() + "\n" + e.toString());
			System.exit(1);
		}
		int curRequestCount = 0;
		while (true) {
			try {
				Socket cs = s.accept(); //服务器收到的socket
				new ServerThread(cs).start(); //这里调用了ServerThread的start()函数
				textArea.setText(textArea.getText() + "\n" + "接收到了 第" + (++curRequestCount) + "个请求");
				//cs.close();
			} catch (IOException e) {
				textArea.setText(textArea.getText() + "\n" + e.toString());
			}
		}
	}
}
