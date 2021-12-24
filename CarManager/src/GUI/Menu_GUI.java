package GUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Menu_GUI extends JFrame implements ActionListener{
	
	//����ȭ�� ������Ʈ
	private JPanel northP = new JPanel();
	private JPanel centerP = new JPanel();
	private JPanel southP = new JPanel();
	private JLabel mainLB = new JLabel("���������ý��� ver.1.0");
	private Button exitBtn = new Button("����");
	//���θ޴� ������Ʈ
	private JPanel mainP_L = new JPanel();
	private JPanel mainP_R = new JPanel();
	private Button carBtn = new Button("������������");
	private Button itemBtn = new Button("����ǰ��"+"\n"+"��������");
	private Button recordBtn = new Button("���񳻿����");
	private Button userBtn = new Button("������������");


	public Menu_GUI() {
		init();
		mainMenu();
		actionList();
	}


	private void actionList() {
		exitBtn.addActionListener(this);
		carBtn.addActionListener(this);
		itemBtn.addActionListener(this);
		userBtn.addActionListener(this);
		recordBtn.addActionListener(this);
	}

	private void mainMenu() {
		centerP.add(mainP_L);
		centerP.add(mainP_R);
		mainP_L.setLayout(new GridLayout(3,1));
		mainP_R.setLayout(new BorderLayout());
		mainP_L.add(carBtn);
		mainP_L.add(itemBtn);
		mainP_L.add(userBtn);
		mainP_R.add(recordBtn);
	}

	private void init() {
		this.add("North",northP);
		this.add("Center",centerP);
		this.add("South",southP);
		northP.add(mainLB);
		southP.add(exitBtn);
		centerP.setLayout(new GridLayout());
		this.setBounds(10, 10, 500, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(exitBtn)) {
			System.exit(0);
		}
		if(e.getSource().equals(carBtn)) {
			new Car_GUI();
			dispose();
		}
		if (e.getSource().equals(itemBtn)) {
			new Item_GUI();
			dispose();
		}
		if (e.getSource().equals(userBtn)) {
			new User_GUI();
			dispose();
		}if (e.getSource().equals(recordBtn)) {
			new Record_GUI();
			dispose();
		}

	
	}

}
