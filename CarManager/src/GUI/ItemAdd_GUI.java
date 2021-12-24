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
import DTO.DTO_item;

public class ItemAdd_GUI extends JFrame implements ActionListener {
	private JPanel itemAddP = new JPanel();
	private JLabel nameLB = new JLabel("정비품목명");
	private JLabel periodLB = new JLabel("정비주기");
	private TextField nameTF = new TextField(5);
	private TextField  periodTF = new TextField(5);
	private Button setBtn = new Button("등록");
	private Button cancelBtn = new Button("종료");
	DAO_CarManager dao_CarManager = null;
	
	public ItemAdd_GUI() {
		init();
		dao_CarManager=DAO_CarManager.getInstance();
	}
	private void init() {
		this.add("Center",itemAddP);
		itemAddP.setLayout(new GridLayout(1,6));
		itemAddP.add(nameLB);
		itemAddP.add(nameTF);
		itemAddP.add(periodLB);
		itemAddP.add(periodTF);
		itemAddP.add(setBtn);
		itemAddP.add(cancelBtn);
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
			DTO_item dto = new DTO_item();
			dto.setName(nameTF.getText());
			dto.setPeriod(Integer.parseInt(periodTF.getText()));
			dao_CarManager.itemAdd(dto);
			nameTF.setText("");
			periodTF.setText("");
			Item_GUI.getItemList();
		}

	}

}
