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
import DTO.DTO_car;

public class UpdateCar_GUI extends JFrame implements ActionListener{
	DAO_CarManager dao_carmanager = null;
	// 화면 컴포넌트
	private JPanel carP = new JPanel();
	private JPanel carSP = new JPanel();
	private TextField kmTF = new TextField();
	private Button setBtn = new Button("수정");
	private Button BackBtn = new Button("뒤로가기");

	// 리스트 컴포넌트
	private static String colNames[] = { "차량번호", "차종", "KM", "관리자명" };
	private static DefaultTableModel model = new DefaultTableModel(colNames, 0);
	private JTable carTB = new JTable(model);
	private JScrollPane carScr = new JScrollPane(carTB);

	public UpdateCar_GUI() {
		dao_carmanager = DAO_CarManager.getInstance();
		getCarList();
		init();
		actionList();
	}

	private void actionList() {
		setBtn.addActionListener(this);
		BackBtn.addActionListener(this);
		
	}

	private void init() {
		this.add("Center", carP);
		this.add("South", carSP);
		carP.add("Center", carScr);
		carSP.setLayout(new GridLayout(1, 3));
		carSP.add(kmTF);
		carSP.add(setBtn);
		carSP.add(BackBtn);
		
		this.setBounds(10, 10, 500, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

	}

	private void getCarList() {
		model.setNumRows(0);
		ArrayList<DTO_car> carList = dao_carmanager.setCarList();
		for (int i = 0; i < carList.size(); i++) {
			model.addRow(new Object[] { carList.get(i).getCarNum(), carList.get(i).getCarType(), carList.get(i).getKm(),
					carList.get(i).getName() });
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(setBtn)) {
			int row = carTB.getSelectedRow();
			Object value= carTB.getValueAt(row,0);
			int carnum=(int)value;
			int carkm = Integer.parseInt(kmTF.getText());
			dao_carmanager.setCarKM(carkm,carnum);
			getCarList();
		}
		if (e.getSource().equals(BackBtn)) {
			new Record_GUI();
			dispose();
		}
		
	}
}
