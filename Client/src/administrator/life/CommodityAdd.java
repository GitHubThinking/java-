package administrator.life;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.only.OnlyFileChooser;
import io.Client;
import util.ByteArray;
import util.Commodity;
import util.Message;
import java.awt.Color;
import java.awt.Font;

public class CommodityAdd{
	JButton jb1;
	JPanel BigPanel = new JPanel();
	ByteArray byteArray = new ByteArray();
	JLabel _image = new JLabel();
	private JTextField brand;
	
	//private int Ecard;
	
	public CommodityAdd(int e, JPanel panel) {
		//this.Ecard = e;
		
		BigPanel = new JPanel();
		BigPanel.setBackground(new Color(153,193,104));
		BigPanel.setBounds(0, 0, 818, 450);
		BigPanel.setLayout(null);
		panel.add(BigPanel);
		
		JLabel JLableGoodsNumber = new JLabel();
		JLableGoodsNumber.setBackground(new Color(153,193,104));
		JLableGoodsNumber.setLocation(36, 124);
		JLableGoodsNumber.setSize(120, 39);
		final JTextField JTextFieldGoodsNumber = new JTextField(15);
		JTextFieldGoodsNumber.setSize(108, 34);
		JTextFieldGoodsNumber.setLocation(162, 126);
		JLableGoodsNumber.setText("商品数量");
		BigPanel.add(JLableGoodsNumber);
		BigPanel.add(JTextFieldGoodsNumber);
		
		JLabel JLableGoodsID = new JLabel();
		JLableGoodsID.setBackground(new Color(153,193,104));
		JLableGoodsID.setLocation(36, 30);
		JLableGoodsID.setSize(120, 39);
		JLableGoodsID.setText("商品编号");			
		final JTextField JTextFieldGoodsID = new JTextField(30);
		JTextFieldGoodsID.setLocation(162, 32);
		JTextFieldGoodsID.setSize(108, 34);
		BigPanel.add(JLableGoodsID);
		BigPanel.add(JTextFieldGoodsID);
		
	
		JLabel JLableGoodsPrice = new JLabel();
		JLableGoodsPrice.setBackground(new Color(153,193,104));
		JLableGoodsPrice.setLocation(36, 224);
		JLableGoodsPrice.setSize(120, 39);
		final JTextField JTextFieldGoodsPrice = new JTextField(15);
		JTextFieldGoodsPrice.setLocation(162, 226);
		JTextFieldGoodsPrice.setSize(108, 34);
		JLableGoodsPrice.setText("\u5546\u54C1\u5355\u4EF7");
		BigPanel.add(JLableGoodsPrice);
		BigPanel.add(JTextFieldGoodsPrice);
		
		JLabel JLableGoodsPic = new JLabel();
		JLableGoodsPic.setBackground(new Color(153,193,104));
		JLableGoodsPic.setLocation(36, 310);
		JLableGoodsPic.setSize(120, 39);
		final JTextField JTextFieldGoodsPic = new JTextField();
		JTextFieldGoodsPic.setLocation(162, 312);
		JTextFieldGoodsPic.setSize(322, 34);
		JLableGoodsPic.setText("图片路径");
		BigPanel.add(JLableGoodsPic);
		BigPanel.add(JTextFieldGoodsPic);
		
		JButton btn_UploadPhoto = new JButton("\u4E0A\u4F20");
		btn_UploadPhoto.setFont(new Font("黑体", Font.PLAIN, 18));
		btn_UploadPhoto.setBounds(484, 312, 70, 35);
		BigPanel.add(btn_UploadPhoto);
		
		btn_UploadPhoto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
		
				JFileChooser chooser = new JFileChooser();
				BigPanel.add(chooser);
				chooser.setLocation(159, 112);
				chooser.setSize(510, 327);
		
				chooser.setFileFilter(new FileNameExtensionFilter("JPG","jpg"));
				String imgPath = null;
				int returnVal = chooser.showOpenDialog(BigPanel);
				if (returnVal == OnlyFileChooser.APPROVE_OPTION) {
					imgPath = chooser.getSelectedFile().getAbsolutePath();
					JTextFieldGoodsPic.setText(imgPath);
					_image.setIcon(new ImageIcon(imgPath));
				}
		
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
			}
		});
		
		
		JLabel JLableGoodsContent = new JLabel();
		JLableGoodsContent.setBackground(new Color(153,193,104));
		JLableGoodsContent.setSize(129, 39);
		JLableGoodsContent.setLocation(355, 123);
		final JTextField JTextFieldGoodsContent = new JTextField();
		JTextFieldGoodsContent.setLocation(446, 126);
		JTextFieldGoodsContent.setSize(108, 34);
		JLableGoodsContent.setText("\u5546\u54C1\u6807\u7B7E");
		BigPanel.add(JLableGoodsContent);
		BigPanel.add(JTextFieldGoodsContent);
		
		JLabel JLableGoodsName= new JLabel();
		JLableGoodsName.setBackground(new Color(153,193,104));
		JLableGoodsName.setLocation(355, 224);
		JLableGoodsName.setSize(130, 39);
		final JTextField JTextFieldGoodsName = new JTextField(15);
		JTextFieldGoodsName.setLocation(446, 226);
		JTextFieldGoodsName.setSize(108, 34);
		JLableGoodsName.setText("商品名称");
		BigPanel.add(JLableGoodsName);
		BigPanel.add(JTextFieldGoodsName);
		

		jb1 = new JButton("OK");
		jb1.setBounds(622, 310, 137, 39);
		BigPanel.add(jb1);
			
		_image.setBounds(580, 29, 212, 231);
		BigPanel.add(_image);
		
		JLabel brandlabel = new JLabel("\u5546\u54C1\u54C1\u724C");
		brandlabel.setBounds(349, 31, 135, 35);
		BigPanel.add(brandlabel);
		
		brand = new JTextField();
		brand.setBounds(446, 32, 108, 34);
		BigPanel.add(brand);
		brand.setColumns(10);
				

		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Commodity mygood = new Commodity();
				mygood.setID(Integer.parseInt(JTextFieldGoodsID.getText()));
				mygood.setStock(Integer.parseInt(JTextFieldGoodsNumber.getText()));
				mygood.setPrice(Double.parseDouble(JTextFieldGoodsPrice.getText()));
				mygood.setPicturePath(JTextFieldGoodsPic.getText());
				mygood.setBriefInfo(JTextFieldGoodsContent.getText());
				mygood.setProduct(JTextFieldGoodsName.getText());
				mygood.setBrand(brand.getText());

				Message msg = new Message();
				msg.setByteArray(byteArray);
				msg.setCommo(mygood);
				msg.setType(2401);

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
