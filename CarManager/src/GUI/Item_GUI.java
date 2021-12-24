package GUI;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import DAO.DAO_CarManager;
import DTO.DTO_item;

public class Item_GUI extends JFrame implements ActionListener {
	static DAO_CarManager dao_carmanager = null;
	// 메뉴 컴포넌트
	private JPanel itemP = new JPanel();
	private JPanel itemSP = new JPanel();
	private Button itemAddBtn = new Button("품목등록");
	private Button itemDelBtn = new Button("품목삭제");
	private Button itemBackBtn = new Button("뒤로가기");
	// 품목리스트컴포넌트
	private static String colNames[] = { "이름", "정비주기   (단위:KM)" };
	private static DefaultTableModel model = new DefaultTableModel(colNames, 0);
	private JTable itemTB = new JTable(model);
	private JScrollPane itemScr = new JScrollPane(itemTB);
	static String itemName = null;
	
	public Item_GUI() {
		dao_carmanager = DAO_CarManager.getInstance();
		getItemList();
		init();
		addList();
	}

	private void addList() {
		itemBackBtn.addActionListener(this);
		itemAddBtn.addActionListener(this);
		itemDelBtn.addActionListener(this);

	}

	private void init() {
		this.add("Center", itemP);
		this.add("South", itemSP);
		itemP.add("Center", itemScr);
		itemSP.setLayout(new GridLayout(1, 3));
		itemSP.add(itemAddBtn);
		itemSP.add(itemDelBtn);
		itemSP.add(itemBackBtn);
		this.setBounds(10, 10, 500, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

	}

	public static void getItemList() {
		model.setNumRows(0);
		ArrayList<DTO_item> itemList = dao_carmanager.setItemList();
		for (int i = 0; i < itemList.size(); i++) {
			model.addRow(new Object[] { itemList.get(i).getName(),itemList.get(i).getPeriod()});
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(itemBackBtn)) {
			new Menu_GUI();
			dispose();
		}
		if (e.getSource().equals(itemAddBtn)) {

			new ItemAdd_GUI();
			
		}
		if (e.getSource().equals(itemDelBtn)) {
			int row = itemTB.getSelectedRow();
			Object value = itemTB.getValueAt(row, 0);
			itemName=(String) value;
			new ItemDel_GUI();
		}

	}

}
