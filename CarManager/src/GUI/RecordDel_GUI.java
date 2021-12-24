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

public class RecordDel_GUI extends JFrame implements ActionListener{
	DAO_CarManager dao_carmanager = null;
	private JPanel recordDelSP = new JPanel();
	private JLabel recordDelLB = new JLabel(RecordList_GUI.carnum+" �� ���� "+RecordList_GUI.name+" �� ���񳻿��� �����Ͻðڽ��ϱ�?");
	private JButton yesBtn = new JButton("��");
	private JButton noBtn = new JButton("�ƴϿ�");
	public RecordDel_GUI() {
		dao_carmanager = DAO_CarManager.getInstance();
		init();
	}
	private void init() {
		this.add("South",recordDelSP);
		this.add("Center",recordDelLB);
		recordDelSP.setLayout(new GridLayout(1,2));
		recordDelSP.add(yesBtn);
		recordDelSP.add(noBtn);
		
		this.setBounds(10, 10, 500, 130);
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
			dao_carmanager.recordDel(RecordList_GUI.carnum,RecordList_GUI.name,RecordList_GUI.km);
			dispose();
			RecordList_GUI.getRecordList();
		}
		
	}
}
