package GUI;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.TextField;
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
import DTO.DTO_record;

public class RecordList_GUI extends JFrame implements ActionListener{
	static DAO_CarManager dao_carmanager = null;
	private JPanel listP = new JPanel();
	private JPanel listSP = new JPanel();
	private TextField searchTF = new TextField(5);
	private Button searchBtn = new Button("차량번호로검색");
	private Button DelBtn = new Button("삭제");
	private Button BackBtn = new Button("뒤로가기");
	
	private static String colNames[] = { "차량번호", "정비품목", "정비날짜", "정비일KM", "정비금액"};
	private static DefaultTableModel model = new DefaultTableModel(colNames, 0);
	private JTable recordTB = new JTable(model);
	private JScrollPane recordScr = new JScrollPane(recordTB);
	static int carnum = 0;
	static int km=0;
	static String name = null;
	public RecordList_GUI() {
		dao_carmanager = DAO_CarManager.getInstance();
		getRecordList();
		init();
	}

	private void init() {
		this.add("Center",listP);
		this.add("South",listSP);
		listP.add("Center",recordScr);
		listSP.setLayout(new GridLayout(1,4));
		listSP.add(searchTF);
		listSP.add(searchBtn);
		listSP.add(DelBtn);
		listSP.add(BackBtn);
		this.setBounds(10, 10, 500, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		searchBtn.addActionListener(this);
		BackBtn.addActionListener(this);
		DelBtn.addActionListener(this);
	}

	public static void getRecordList() {
		model.setNumRows(0);
		ArrayList<DTO_record> recordList = dao_carmanager.setRecordList();
		for (int i = 0; i < recordList.size(); i++) {
			model.addRow(new Object[] {recordList.get(i).getCarNum(),
					recordList.get(i).getName(),recordList.get(i).getDate()
					,recordList.get(i).getKm(),recordList.get(i).getKm(),
					recordList.get(i).getPrice()});
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(BackBtn)) {
			new Record_GUI();
			dispose();
		}
		if (e.getSource().equals(DelBtn)) {
			int row = recordTB.getSelectedRow();
			Object value= recordTB.getValueAt(row, 0);
			carnum=(int)value;
			value= recordTB.getValueAt(row, 1);
			name=(String)value;
			value= recordTB.getValueAt(row, 3);
			km=(int)value;
			new RecordDel_GUI();
		}
		if (e.getSource().equals(searchBtn)) {
			DTO_record dto = new DTO_record();
			dto.setCarNum(Integer.parseInt(searchTF.getText()));
			getSearchRecord(dto);
			
		}
	}

	private void getSearchRecord(DTO_record dto) {
		model.setNumRows(0);
		ArrayList<DTO_record> recordList = dao_carmanager.searchRecord(dto);
		for (int i = 0; i < recordList.size(); i++) {
			model.addRow(new Object[] {recordList.get(i).getCarNum(),
					recordList.get(i).getName(),recordList.get(i).getDate()
					,recordList.get(i).getKm(),recordList.get(i).getKm(),
					recordList.get(i).getPrice()});
		}
	}
	
}
