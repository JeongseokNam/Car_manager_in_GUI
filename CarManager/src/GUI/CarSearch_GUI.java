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
import DTO.DTO_SeachInfo;

public class CarSearch_GUI extends JFrame implements ActionListener {
	static DAO_CarManager dao_carmanager = null;
	private JPanel searchP = new JPanel();
	private JPanel searchSP = new JPanel();
	private Button BackBtn = new Button("뒤로가기");
	private static String colNames[] = { "차량번호", "정비품목" };
	private static DefaultTableModel model = new DefaultTableModel(colNames, 0);
	private JTable searchTB = new JTable(model);
	private JScrollPane recordScr = new JScrollPane(searchTB);

	public CarSearch_GUI() {
		dao_carmanager = DAO_CarManager.getInstance();
		init();
		getSeachList();
	}

	private void getSeachList() {
		model.setNumRows(0);
		ArrayList<DTO_SeachInfo> list = new ArrayList<>();
		DTO_SeachInfo dto=null;
		ArrayList<Integer> numList = dao_carmanager.getCarnum();
		ArrayList<String> itemList = dao_carmanager.getItemName();
		for (int i = 0; i < numList.size(); i++) {
			for (int j = 0; j < itemList.size(); j++) {
				dto=dao_carmanager.setSeachList(numList.get(i),itemList.get(j));
				if (dto!=null) {
					list.add(dto);
				}
				dto=null;
			}
		}
		for (int i = 0; i < list.size(); i++) {
			model.addRow(new Object[] { list.get(i).getCarNum(), list.get(i).getItemName() });
		}
	}

	private void init() {
		this.add("Center", searchP);
		this.add("South", searchSP);
		searchP.add("Center", recordScr);
		searchSP.setLayout(new GridLayout());
		searchSP.add(BackBtn);
		this.setBounds(10, 10, 500, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		BackBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(BackBtn)) {
			new Record_GUI();
			dispose();
		}
	}
}
