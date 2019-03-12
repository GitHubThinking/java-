package administrator.life;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JPanel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

//import com.seu.vcampus.course.*;

public class Life extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel bigPanel;

	private int Ecard;
	private JButton Button1;
	private JButton Button3;
	CardLayout cardLayout;

	// 用于设置切换按钮
	private JButton lastbtn;

	
	@SuppressWarnings("unused")
	//private BookList listBook;
	private CommodityAdd commodityAdd;
	private CommodityList commodityList;

	public Life(int Ecard) {
		this.Ecard = Ecard;
		System.out.println(this.Ecard);
		this.setBounds(0, 0, 818, 450);
		setLayout(null);

		Button1 = new JButton("添加商品");
		Button1.setBackground(new Color(153,193,104));
		Button1.setForeground(Color.BLACK);
		Button1.setBounds(10, 10, 140, 37);
		add(Button1);

		Button3 = new JButton("商品列表");
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
		commodityAdd = new CommodityAdd(Ecard, penelAdd);

		JPanel panelUpDate = new JPanel();
		panelUpDate.setBounds(10, 10, 778, 348);
		bigPanel.add("panelUpDate", panelUpDate);
		panelUpDate.setLayout(null);
		//JLabel label2 = new JLabel("更新商品");
		//label2.setBounds(358, 10, 54, 15);
		//panelUpDate.add(label2);

		JPanel panelList = new JPanel();
		panelList.setBounds(10, 10, 778, 348);
		bigPanel.add("panelList", panelList);
		panelList.setLayout(null);
		commodityList=new CommodityList(Ecard, panelList);
		

		Button1.setActionCommand("penelAdd");
		Button3.setActionCommand("panelList");

		// 设置监听事件
		myEvent();

		// 设置按钮样式
		lastbtn = Button1;
		lastbtn.setEnabled(false);

	}
	public void UpdateThread()
	{
		bigPanel.repaint();
		cardLayout.show(bigPanel, "panelUpDate");
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
				commodityList.fresh();
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
