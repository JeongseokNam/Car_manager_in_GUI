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
import DTO.DTO_record;

public class AddRecord_GUI  extends JFrame implements ActionListener{
	private JPanel addRecordP = new JPanel();
	private JLabel carnumLB = new JLabel("차량번호");
	private JLabel nameLB = new JLabel("정비품목");
	private JLabel kmLB = new JLabel("km");
	private JLabel priceLB = new JLabel("정비금액");
	private TextField carnumTF = new TextField(5);
	private TextField nameTF = new TextField(5);
	private TextField kmTF = new TextField(5);
	private TextField priceTF = new TextField(5);
	private Button setBtn = new Button("등록");
	private Button cancelBtn = new Button("종료");
	DAO_CarManager dao_CarManager = null;
	
	public AddRecord_GUI() {
		init();
		dao_CarManager=DAO_CarManager.getInstance();
	}
	private void init() {
		this.add("Center",addRecordP);
		addRecordP.setLayout(new GridLayout(1,10));
		addRecordP.add(carnumLB);
		addRecordP.add(carnumTF);
		addRecordP.add(nameLB);
		addRecordP.add(nameTF);
		addRecordP.add(kmLB);
		addRecordP.add(kmTF);
		addRecordP.add(priceLB);
		addRecordP.add(priceTF);
		addRecordP.add(setBtn);
		addRecordP.add(cancelBtn);
		
		this.setBounds(100,100,600,100);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		setBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(setBtn)) {
			DTO_record dto = new DTO_record();
			dto.setCarNum(Integer.parseInt(carnumTF.getText()));
			dto.setName(nameTF.getText());
			dto.setKm(Integer.parseInt(kmTF.getText()));
			dto.setPrice(Integer.parseInt(priceTF.getText()));
			dao_CarManager.addRecord(dto);
			carnumTF.setText("");
			nameTF.setText("");
			kmTF.setText("");
			priceTF.setText("");
			
		}
		if (e.getSource().equals(cancelBtn)) {
			dispose();
		}
	}

}
