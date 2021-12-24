package GUI;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Record_GUI extends JFrame implements ActionListener{
	//record ��� ������Ʈ
	private JPanel northP = new JPanel();
	private JPanel centerP = new JPanel();
	private JPanel southP = new JPanel();
	private JLabel mainLB = new JLabel("���������ý��� ver.1.0");
	private Button backBtn = new Button("�ڷΰ���");
	//���ڵ� �޴� ������Ʈ
	private Button carSearchBtn = new Button("������������ȸ");
	private Button recordListBtn = new Button("�������񳻿���ȸ");
	private Button addRecordBtn = new Button("���񳻿����");
	private Button updateCarBtn = new Button("����Ű�μ�����");
	
	
	public Record_GUI() {
		init();
		menu();
		actionList();
	}
	
	private void actionList() {
		carSearchBtn.addActionListener(this);
		recordListBtn.addActionListener(this);
		addRecordBtn.addActionListener(this);
		updateCarBtn.addActionListener(this);
		backBtn.addActionListener(this);
		
	}

	private void menu() {
		centerP.setLayout(new GridLayout(1,4));
		centerP.add(carSearchBtn);
		centerP.add(recordListBtn);
		centerP.add(addRecordBtn);
		centerP.add(updateCarBtn);
		
	}
	private void init() {
		this.add("North",northP);
		this.add("Center",centerP);
		this.add("South",southP);
		northP.add(mainLB);
		southP.add(backBtn);
		centerP.setLayout(new GridLayout());
		
		this.setBounds(10, 10, 500, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(updateCarBtn)) {
			new UpdateCar_GUI();
			dispose();
		}
		if (e.getSource().equals(backBtn)) {
			new Menu_GUI();
			dispose();
		}
		if (e.getSource().equals(addRecordBtn)) {
			new AddRecord_GUI();
		}
		if (e.getSource().equals(recordListBtn)) {
			new RecordList_GUI();
			dispose();
		}
		if (e.getSource().equals(carSearchBtn)) {
			new CarSearch_GUI();
			dispose();
		}
	}
	
}
