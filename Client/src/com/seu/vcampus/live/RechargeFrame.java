package com.seu.vcampus.live;

import javax.swing.JFrame;
import javax.swing.JTextField;

import io.Client;
import util.BasicInfo;
import util.Message;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class RechargeFrame extends JFrame {
	private JTextField txfEcard;
	private JTextField txfRecharge;
	private JTextField txfPassword;
	
	private int EcardNumber;
	private String Password;
	private double Recharge;
	
	private int Width = 900;
	private int Height = 550;
	private int innerWidth = 900;
	private int innerHeight = 410;
	private int left = 20;
	private int top = 150;
	private int btnWidth = 140;
	private int btnHeight = 40;
	
	public RechargeFrame(LiveTab lt,int EcardNumber) {
		
		setResizable(false);
		setBounds(700,400,409,332);
		this.EcardNumber = EcardNumber;
		
		getContentPane().setFont(new Font("微软雅黑", Font.PLAIN, 20));
		getContentPane().setLayout(null);
		
		JLabel lblEcard = new JLabel("\u4E00\u5361\u901A\u53F7\uFF1A");
		lblEcard.setBounds(80, 48, 103, 33);
		getContentPane().add(lblEcard);
		
		JLabel label = new JLabel("\u5145\u503C\u91D1\u989D\uFF1A");
		label.setBounds(80, 109, 86, 18);
		getContentPane().add(label);
		
		txfEcard = new JTextField();
		txfEcard.setEditable(false);
		txfEcard.setBounds(162, 52, 142, 24);
		getContentPane().add(txfEcard);
		txfEcard.setText(EcardNumber+"");
		txfEcard.setColumns(1);
		
		txfRecharge = new JTextField();
		txfRecharge.setBounds(162, 106, 142, 24);
		getContentPane().add(txfRecharge);
		txfRecharge.setColumns(1);
		
		txfPassword = new JPasswordField();
		txfPassword.setBounds(162, 158, 142, 24);
		getContentPane().add(txfPassword);
		txfPassword.setColumns(1);
		
		JLabel lblNewLabel = new JLabel("\u5BC6\u7801\uFF1A");
		lblNewLabel.setBounds(80, 161, 72, 18);
		getContentPane().add(lblNewLabel);
		
		JButton btnConfirm = new JButton("\u786E\u5B9A");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Password = txfPassword.getText();
				Recharge = Double.parseDouble(txfRecharge.getText());
				
				if(txfRecharge.getText().length()<1) {
					JOptionPane.showMessageDialog(null, "充值金额不能为空!","fault", JOptionPane.ERROR_MESSAGE);
				}
				else if(Recharge<=0) {
					JOptionPane.showMessageDialog(null, "充值金额不合法!","fault", JOptionPane.ERROR_MESSAGE);
				}
				else if(Password.length()<1) {
					JOptionPane.showMessageDialog(null, "密码不能为空!","fault", JOptionPane.ERROR_MESSAGE);
				}
				
				else {
					System.out.println(Recharge);
					System.out.println(Password);
					Message SendMessage  = new Message();
					SendMessage.setType(1604);
					SendMessage.setEcardNumber(EcardNumber);
					SendMessage.setTempPassword(Password);
					SendMessage.setData(txfRecharge.getText());
					try {
						Message msg = new Client().start(SendMessage);
						switch(msg.getType()) {
						case 1101:
							JOptionPane.showMessageDialog(null,"充值成功!");setVisible(false);break;
						case 0:
							JOptionPane.showMessageDialog(null, "密码错误!","fault", JOptionPane.ERROR_MESSAGE);break;
						default:
							JOptionPane.showMessageDialog(null, "出现了一个未知错误","fault", JOptionPane.ERROR_MESSAGE);break;
						}
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
		
				}
				
				lt.fresh(EcardNumber);
				txfRecharge.setText(null);
				txfPassword.setText(null);   	//清空输入栏
			}
		});
		btnConfirm.setBounds(89, 229, 113, 27);
		getContentPane().add(btnConfirm);
		
		JButton btnCancel = new JButton("\u53D6\u6D88");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txfRecharge.setText(null);
				txfPassword.setText(null);   	//清空输入栏
				setVisible(false);
			}
		});
		btnCancel.setBounds(239, 229, 113, 27);
		getContentPane().add(btnCancel);
		setTitle("\u4E00\u5361\u901A\u5145\u503C");
	}

//	public static void main(String[] args) {
//		
//		RechargeFrame a = new RechargeFrame(123456789);
//		a.setVisible(true);
//	}
}
