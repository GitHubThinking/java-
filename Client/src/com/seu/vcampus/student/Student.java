package com.seu.vcampus.student;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.only.OnlyFileChooser;
import com.seu.vcampus.Special.MyButton;
import util.ByteArray;

import io.Client;
import util.BasicInfo;
import util.Message;

public class Student extends JPanel {

	private JFrame frame;

	private JTextField NameField;
	private JTextField SexField;
	private JTextField stuNumberField;
	private JTextField EcardField;
	private JTextField PhoneField;
	private JTextField IdentityField;
	private JTextField CollegeField;
	private JTextField LocationField;
	private JTextField IDnumberField;
	private JTextField EmailField;
	private JTextField BirthdayField;
	private JTextField PinYinField;
	private JTextField Password;
	private JTextField MayjorField;
	private ArrayList<JTextField> txtList;
	private MyButton ChangePhoto;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private JLabel label_9;
	private JLabel label_10;
	private JLabel label_11;
	private JLabel label_12;
	private JLabel label_13;

	private int Ecard;

	private int Width = 900;
	private int Height = 550;
	private int innerWidth = 900;
	private int innerHeight = 480;
	private int left = 20;
	private int top = 70;
	private int btnWidth = 140;
	private int btnHeight = 40;
	private int btnLeft = 20;
	private MyButton btnSave;
	private MyButton btnChange;

	private JPanel myselfPanel;
	private JPanel panel;
	private MyButton img_label;
	
	private String _pic;

	public Student(int num) {
		// frame = new JFrame();
		// frame.setBounds(100, 100, 1200, 600);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.getContentPane().setLayout(null);
		this.Ecard = num;
		this._pic=new String();
		myselfPanel = this;
		myselfPanel.setBackground(new Color(245, 245, 245));
		myselfPanel.setLayout(null);

		myselfPanel.setBounds(left, top, innerWidth, innerHeight);
		setLayout(null);

		NameField = new JTextField("姓名");
		NameField.setBounds(423, 50, 136, 32);
		myselfPanel.add(NameField);
		NameField.setColumns(10);

		SexField = new JTextField("性别");
		SexField.setBounds(423, 180, 136, 32);
		myselfPanel.add(SexField);
		SexField.setColumns(10);

		stuNumberField = new JTextField("学号");
		stuNumberField.setBounds(423, 246, 136, 32);
		stuNumberField.setColumns(10);
		myselfPanel.add(stuNumberField);

		EcardField = new JTextField("一卡通");
		EcardField.setBounds(121, 348, 165, 32);
		EcardField.setColumns(10);
		myselfPanel.add(EcardField);

		PhoneField = new JTextField("电话号码");
		PhoneField.setBounds(423, 379, 136, 32);
		PhoneField.setColumns(10);
		myselfPanel.add(PhoneField);

		IdentityField = new JTextField("身份");
		IdentityField.setBounds(696, 50, 165, 32);
		IdentityField.setColumns(10);
		myselfPanel.add(IdentityField);

		CollegeField = new JTextField("学院");
		CollegeField.setBounds(696, 115, 165, 32);
		CollegeField.setColumns(10);
		myselfPanel.add(CollegeField);

		LocationField = new JTextField("籍贯");
		LocationField.setBounds(696, 246, 165, 32);
		LocationField.setColumns(10);
		myselfPanel.add(LocationField);

		IDnumberField = new JTextField("身份证");
		IDnumberField.setBounds(696, 310, 165, 32);
		IDnumberField.setColumns(10);
		myselfPanel.add(IDnumberField);

		EmailField = new JTextField("邮编");
		EmailField.setBounds(696, 379, 165, 32);
		EmailField.setColumns(10);
		myselfPanel.add(EmailField);

		BirthdayField = new JTextField("生日");
		BirthdayField.setBounds(423, 314, 136, 32);
		BirthdayField.setColumns(10);
		myselfPanel.add(BirthdayField);

		PinYinField = new JTextField("拼音");
		PinYinField.setBounds(423, 115, 136, 32);
		PinYinField.setColumns(10);
		myselfPanel.add(PinYinField);

		Password = new JTextField("密码");
		Password.setBounds(121, 401, 165, 32);
		Password.setColumns(10);
		myselfPanel.add(Password);

		ChangePhoto = new MyButton();
		ChangePhoto.setBounds(0, 0, 0, 0);
		ChangePhoto.setBound(132, 258, 120, 40);
		ChangePhoto.setImage("src/img_student/更换图片.png");
		myselfPanel.add(ChangePhoto);

		MayjorField = new JTextField("专业");
		MayjorField.setBounds(696, 180, 165, 32);
		MayjorField.setColumns(10);
		myselfPanel.add(MayjorField);

		panel = new JPanel();
		panel.setBounds(95, 50, 207, 188);
		myselfPanel.add(panel);
		panel.setLayout(null);

		img_label = new MyButton();
		img_label.setBound(0, 0, 207, 200);
		panel.add(img_label);

		JLabel label = new JLabel("拼音");
		label.setBounds(346, 115, 56, 32);
		label.setFont(new Font("微软雅黑", Font.BOLD, 20));
		myselfPanel.add(label);

		label_1 = new JLabel("姓名");
		label_1.setBounds(346, 50, 56, 32);

		label_1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		myselfPanel.add(label_1);

		label_2 = new JLabel("性别");
		label_2.setBounds(346, 180, 56, 32);

		label_2.setFont(new Font("微软雅黑", Font.BOLD, 20));
		myselfPanel.add(label_2);

		label_3 = new JLabel("学号");
		label_3.setBounds(346, 246, 56, 32);

		label_3.setFont(new Font("微软雅黑", Font.BOLD, 20));
		myselfPanel.add(label_3);

		label_4 = new JLabel("一卡通");
		label_4.setBounds(39, 344, 68, 32);

		label_4.setFont(new Font("微软雅黑", Font.BOLD, 20));
		myselfPanel.add(label_4);

		label_5 = new JLabel("身份");
		label_5.setBounds(630, 50, 56, 32);

		label_5.setFont(new Font("微软雅黑", Font.BOLD, 20));
		myselfPanel.add(label_5);

		label_6 = new JLabel("学院");
		label_6.setBounds(630, 115, 56, 32);

		label_6.setFont(new Font("微软雅黑", Font.BOLD, 20));
		myselfPanel.add(label_6);

		label_7 = new JLabel("籍贯");
		label_7.setBounds(630, 246, 56, 32);

		label_7.setFont(new Font("微软雅黑", Font.BOLD, 20));
		myselfPanel.add(label_7);

		label_8 = new JLabel("身份证");
		label_8.setBounds(619, 310, 68, 32);

		label_8.setFont(new Font("微软雅黑", Font.BOLD, 20));
		myselfPanel.add(label_8);

		label_9 = new JLabel("邮编");
		label_9.setBounds(630, 379, 56, 32);

		label_9.setFont(new Font("微软雅黑", Font.BOLD, 20));
		myselfPanel.add(label_9);

		label_10 = new JLabel("电话");
		label_10.setBounds(346, 375, 56, 32);

		label_10.setFont(new Font("微软雅黑", Font.BOLD, 20));
		myselfPanel.add(label_10);

		label_11 = new JLabel("生日");
		label_11.setBounds(346, 310, 56, 32);

		label_11.setFont(new Font("微软雅黑", Font.BOLD, 20));
		myselfPanel.add(label_11);

		label_12 = new JLabel("密码");
		label_12.setBounds(49, 401, 45, 32);

		label_12.setFont(new Font("微软雅黑", Font.BOLD, 20));
		myselfPanel.add(label_12);

		label_13 = new JLabel("专业");
		label_13.setBounds(630, 180, 56, 32);

		label_13.setFont(new Font("微软雅黑", Font.BOLD, 20));
		myselfPanel.add(label_13);

		btnChange = new MyButton();
		btnChange.setBound(452, 433, 80, 40);
		btnChange.setImage("src/img_student/更改.png");
		myselfPanel.add(btnChange);

		btnSave = new MyButton();
		btnSave.setBound(704, 433, 80, 40);
		btnSave.setImage("src/img_student/保存.png");
		myselfPanel.add(btnSave);

		Password.setEditable(false);
		LocationField.setEditable(false);
		PhoneField.setEditable(false);
		EmailField.setEditable(false);
		btnSave.setEnabled(false);

		// 事件
		myEvent();
	}

	public void fresh() {
		Message senderMessage = new Message(Ecard);
		senderMessage.setType(1301);
		try {
			Message messageBI = new Client().start(senderMessage);
			if (messageBI.getType() == 1101) {// 返回数据成功

				BasicInfo B = messageBI.getBasicInfo();

				img_label.setImage("pictures/people/" + B.getPicturePath() + ".jpg");
				
				_pic=B.getPicturePath();

				String sex = (B.getGender() == 1) ? "女" : "男";
				String identity = (B.getIdentity() == 1) ? "学生" : "老师";

				NameField.setText(B.getNameString());
				NameField.setEditable(false);
				SexField.setText(sex);
				SexField.setEditable(false);
				stuNumberField.setText(B.getStuNumber());
				stuNumberField.setEditable(false);
				EcardField.setText(Integer.toString(B.getEcardNumber()));
				EcardField.setEditable(false);
				PhoneField.setText(B.getTelephone());
				IdentityField.setText(identity);
				IdentityField.setEditable(false);
				CollegeField.setText(B.getCollege());
				CollegeField.setEditable(false);
				LocationField.setText(B.getHomeAddress());
				IDnumberField.setText(B.getIDNumber());
				IDnumberField.setEditable(false);
				EmailField.setText(B.getEmail());
				BirthdayField.setText(B.getBirthday().toString());
				BirthdayField.setEditable(false);
				PinYinField.setText(B.getNameSpelling());
				PinYinField.setEditable(false);
				Password.setText(B.getPassword());
				MayjorField.setText(B.getMajor());
				MayjorField.setEditable(false);
			} else {
				JOptionPane.showMessageDialog(null, "Error：未接收到信息！");
			}
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
	}

	public void myEvent() {
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocationField.setEditable(true);
				PhoneField.setEditable(true);
				EmailField.setEditable(true);
				btnSave.setEnabled(true);
				Password.setEditable(true);
			}
		});
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Message senderMessage = new Message(Ecard);
				BasicInfo B = senderMessage.getBasicInfo();
				B.setHomeAddress(LocationField.getText());
				B.setEmail(EmailField.getText());
				B.setTelephone(PhoneField.getText());
				B.setPassword(Password.getText());
				senderMessage.setBasicInfo(B);
				senderMessage.setType(1302);
				try {
					Message messageBI = new Client().start(senderMessage);
					if (messageBI.getType() == 1101) {
						JOptionPane.showMessageDialog(null, "Success:修改数据成功！(￣▽￣)");
						LocationField.setEditable(false);
						PhoneField.setEditable(false);
						EmailField.setEditable(false);
						btnSave.setEnabled(false);
						Password.setEditable(false);
					} else {
						JOptionPane.showMessageDialog(null, "Error：未接收到信息！〒_〒");
					}
				} catch (ClassNotFoundException e2) {
					e2.printStackTrace();
				}

			}
		});
		ChangePhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setLocation(71, 36);
				chooser.setSize(510, 327);

				chooser.setFileFilter(new FileNameExtensionFilter("JPG", "jpg"));
				String imgPath = null;
				int returnVal = chooser.showOpenDialog(frame);
				if (returnVal == OnlyFileChooser.APPROVE_OPTION) {
					imgPath = chooser.getSelectedFile().getAbsolutePath();
				}
				ByteArray byteArray = new ByteArray();
				byte[] imageData = null;
				try {
					BufferedImage image = ImageIO.read(new FileInputStream(imgPath));
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ImageIO.write(image, "jpg", baos);
					imageData = baos.toByteArray();
					byteArray.setImageData(imageData);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				// 发送图片信息
				
				ByteArrayInputStream bais = new ByteArrayInputStream(byteArray.getImageData());
				BufferedImage imageBuffered;
				try {
					imageBuffered = ImageIO.read(bais);
					BufferedImage imageBufferedSmall = new BufferedImage(256, 256, BufferedImage.TYPE_INT_BGR);
					Graphics2D graphics = (Graphics2D) imageBufferedSmall.getGraphics();
					graphics.drawImage(imageBuffered, 0, 0, 256, 256, null);
					graphics.dispose();
					imageBufferedSmall.flush();

					String newImgPath = new File("").getAbsolutePath().replace('\\', '/') + "/pictures/people/";
					ImageIO.write(imageBufferedSmall, "jpg", new File(newImgPath + _pic + ".jpg"));
				} catch (IOException e11) {
					e11.printStackTrace();
				}

				Message senderMessage = new Message(Ecard);
				senderMessage.getBasicInfo().setPicturePath(_pic);
				senderMessage.setByteArray(byteArray);
				senderMessage.setType(1303);// 上传照片
				try {
					Message messageBack = new Client().start(senderMessage);
					if (messageBack.getType() == 1101) {
						// 更新界面的图片
						
//						img_label.setIcon(new ImageIcon(imgPath));
						img_label.setImage(imgPath);
					} else {
						JOptionPane.showMessageDialog(null, "Error：上传失败！〒_〒");
					}
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}
