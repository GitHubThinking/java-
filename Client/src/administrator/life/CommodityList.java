package administrator.life;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.seu.vcampus.tablechange.TableButtonEdit_Select;
import com.seu.vcampus.tablechange.TableButtonEdit_UpCommo;
import com.seu.vcampus.tablechange.TableButtonRender;

import io.Client;
import util.Commodity;
import util.CourseOffered;
import util.Message;

//import com.seu.vcampus.client.tablechange.TableButtonEdit_Select;
//import com.seu.vcampus.client.tablechange.TableButtonRender;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;

public class CommodityList {

	// private JFrame frame;
	private JPanel commodityList;
	private JTable table;

	private DefaultTableModel model;
	private JScrollPane jsp;
	private int Ecard;

	private int innerWidth = 798;
	private int innerHeight = 368;

	public CommodityList(int number, JPanel panel) {

		this.Ecard = number;

		commodityList = panel;
		commodityList.setBounds(0, 0, innerWidth, innerHeight);

		table = new JTable();
		table.setBounds(0, 0, innerWidth, innerHeight);
		table.setFont(new Font("΢���ź�", Font.PLAIN, 12));

		String[] coloumNames = { "��Ʒ���", "��Ʒ����", "Ʒ��", "��Ʒ��ǩ", "�۸�", "���", "ͼƬ·��","����", "ɾ��" };

		model = new DefaultTableModel(null, coloumNames) {
			private static final long serialVersionUID = 975854289835222403L;

			public boolean isCellEditable(int row, int column) {
				// ���а�ť�еĹ����������Ҫ����true��Ȼ��ť���ʱ���ᴥ���༭Ч����Ҳ�Ͳ��ᴥ���¼���

				if (column == 7 || column == 8) {
					return true;
				} else {
					return false;
				}
			}
		};
		table.setModel(model);
		table.getColumnModel().getColumn(7).setCellRenderer(new TableButtonRender());
		table.getColumnModel().getColumn(7).setCellEditor(new TableButtonEdit_UpCommo(table, Ecard, null));
		table.getColumnModel().getColumn(8).setCellRenderer(new TableButtonRender());
		table.getColumnModel().getColumn(8).setCellEditor(new TableButtonEdit_UpCommo(table, Ecard, null));
		table.getColumnModel().getColumn(0).setPreferredWidth(55);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(20);
		table.getColumnModel().getColumn(3).setPreferredWidth(15);
		table.getColumnModel().getColumn(4).setPreferredWidth(40);
		table.getColumnModel().getColumn(5).setPreferredWidth(40);
		table.getColumnModel().getColumn(6).setPreferredWidth(40);
		table.setRowSelectionAllowed(false);

		// ���ñ�ͷ�������С
		JTableHeader head = table.getTableHeader(); // �������������
		head.setPreferredSize(new Dimension(head.getWidth(), 35));// ���ñ�ͷ��С
		head.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		table.setRowHeight(25);// ԭ��Ҫ������

		// ���ݸ��б�ɫ
		DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				// table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
				if (row % 2 == 1)
					setBackground(new Color(240, 240, 240));
				else if (row % 2 == 0)
					setBackground(Color.white);
				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
		};

		// ������ʾ
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);
		commodityList.setLayout(null);

		jsp = new JScrollPane(table);
		jsp.setBounds(0, 0, innerWidth, innerHeight);

		commodityList.add(jsp);

	}

	public void fresh() {
		Message messageSend = new Message(Ecard);
		messageSend.setType(2403);
		try {
			Message messageBack = new Client().start(messageSend);
			if (messageBack.getType() == 1101) {
				model.setRowCount(0);// ��ձ��
				ArrayList<Commodity> commoList = messageBack.getCommoList();

				for (Commodity commo : commoList) {
					String state = "";
					String state1 = "";
					state = "����";
					state1 = "ɾ��";
					model.addRow(new Object[] { commo.getID(), commo.getProduct(), commo.getBrand(),
							commo.getBriefInfo(), commo.getPrice(), commo.getStock(), commo.getPicturePath(),state, state1

					});
				}
			} else {
				System.out.println("δ��ѯ��ϵͳ�д�����Ʒ");
			}
		} catch (ClassNotFoundException event) {
			event.printStackTrace();
		}
		messageSend = null;
	}

}