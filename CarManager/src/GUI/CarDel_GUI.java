package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import DAO.DAO_CarManager;

public class CarDel_GUI extends JFrame implements ActionListener{
	DAO_CarManager dao_carmanager = null;
	private JPanel carDelSP = new JPanel();
	private JLabel carDelLB = new JLabel(Car_GUI.carnum+" 번 차량삭제 하시겠습니까?");
	private JButton yesBtn = new JButton("예");
	private JButton noBtn = new JButton("아니오");
	public CarDel_GUI() {
		dao_carmanager = DAO_CarManager.getInstance();
		init();
	}

	private void init() {
		this.add("South",carDelSP);
		this.add("Center",carDelLB);
		carDelSP.setLayout(new GridLayout(1,2));
		carDelSP.add(yesBtn);
		carDelSP.add(noBtn);
		
		this.setBounds(10, 10, 300, 130);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		yesBtn.addActionListener(this);
		noBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(noBtn)) {
			dispose();
		}
		if (e.getSource().equals(yesBtn)) {
			dao_carmanager.carDel(Car_GUI.carnum);
			Car_GUI.getCarList();
			dispose();
		}
		
	}
}
