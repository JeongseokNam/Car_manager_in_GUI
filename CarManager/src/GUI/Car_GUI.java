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
import DTO.DTO_car;

public class Car_GUI extends JFrame implements ActionListener {
	static DAO_CarManager dao_carmanager = null;
	// 차량정보관리 컴포넌트
	private JPanel carP = new JPanel();
	private JPanel carSP = new JPanel();
	private Button carAddBtn = new Button("차량등록");
	private Button carDelBtn = new Button("차량삭제");
	private Button carBackBtn = new Button("뒤로가기");
	// 차량리스트 컴포넌트
	private static String colNames[] = { "차량번호", "차종", "KM", "관리자명" };
	private static DefaultTableModel model = new DefaultTableModel(colNames, 0);
	private JTable carTB = new JTable(model);
	private JScrollPane carScr = new JScrollPane(carTB);
	static int carnum = 0;
	public Car_GUI() {
		dao_carmanager = DAO_CarManager.getInstance();
		getCarList();
		init();
	}
	public static void getCarList() {
		model.setNumRows(0);
		ArrayList<DTO_car> carList = dao_carmanager.setCarList();
		for (int i = 0; i < carList.size(); i++) {
			model.addRow(new Object[] { carList.get(i).getCarNum(), carList.get(i).getCarType(), carList.get(i).getKm(),
					carList.get(i).getName() });

		}
	}

	private void init() {
		this.add("Center", carP);
		this.add("South", carSP);
		carP.add("Center", carScr);
		carSP.setLayout(new GridLayout(1, 3));
		carSP.add(carAddBtn);
		carSP.add(carDelBtn);
		carSP.add(carBackBtn);
		this.setBounds(10, 10, 500, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		carBackBtn.addActionListener(this);
		carAddBtn.addActionListener(this);
		carDelBtn.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(carBackBtn)) {
			new Menu_GUI();
			dispose();
		}
		if (e.getSource().equals(carAddBtn)) {
			new CarAdd_GUI();
		}
		if (e.getSource().equals(carDelBtn)) {
			int row = carTB.getSelectedRow();
			Object value= carTB.getValueAt(row,0);
			carnum=(int)value;
			new CarDel_GUI();
		}
	}
}
