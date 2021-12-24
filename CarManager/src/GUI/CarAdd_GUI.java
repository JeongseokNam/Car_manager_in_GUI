package GUI;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import DAO.DAO_CarManager;
import DTO.DTO_car;

public class CarAdd_GUI extends JFrame implements ActionListener {
	private JPanel carAddP = new JPanel();
	private JLabel carnumLB = new JLabel("차량번호");
	private JLabel cartypeLB = new JLabel("차종");
	private JLabel kmLB = new JLabel("km");
	private JLabel userLB = new JLabel("관리자명");
	private TextField carnumTF = new TextField(5);
	private TextField cartypeTF = new TextField(5);
	private TextField kmTF = new TextField(5);
	private TextField userTF = new TextField(5);
	private Button setBtn = new Button("등록");
	private Button cancelBtn = new Button("종료");
	DAO_CarManager dao_CarManager = null;
	
	public CarAdd_GUI() {
		init();
		dao_CarManager=DAO_CarManager.getInstance();
	}
	private void init() {
		this.add("Center",carAddP);
		carAddP.setLayout(new GridLayout(1,10));
		carAddP.add(carnumLB);
		carAddP.add(carnumTF);
		carAddP.add(cartypeLB);
		carAddP.add(cartypeTF);
		carAddP.add(kmLB);
		carAddP.add(kmTF);
		carAddP.add(userLB);
		carAddP.add(userTF);
		carAddP.add(setBtn);
		carAddP.add(cancelBtn);
		
		this.setBounds(100,100,600,100);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		setBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(cancelBtn)) {
			dispose();
		}
		if (e.getSource().equals(setBtn)) {
			DTO_car dto = new DTO_car();
			dto.setCarNum(Integer.parseInt(carnumTF.getText()));
			dto.setCarType(cartypeTF.getText());
			dto.setKm(Integer.parseInt(kmTF.getText()));
			dto.setName(userTF.getText());
			dao_CarManager.carAdd(dto);
			carnumTF.setText("");
			cartypeTF.setText("");
			kmTF.setText("");
			userTF.setText("");

			Car_GUI.getCarList();
		}
		
	} 
}
