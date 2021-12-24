package GUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import DAO.DAO_CarManager;
import DTO.DTO_car;
import DTO.DTO_item;
import DTO.DTO_record;
import DTO.DTO_user;

public class CarManager_GUI extends JFrame implements ActionListener,ItemListener {
	private DAO_CarManager dao_CarManager = null;
	private ArrayList<DTO_car> carList = null;
	private ArrayList<DTO_item> itemList = null;
	private ArrayList<DTO_record> recordList = null;
	private ArrayList<DTO_user> userList = null;
	
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
	//������������ ������Ʈ
	private JPanel carP = new JPanel();
	private Button carAddBtn = new Button("�������"); 
	private Button carDelBtn = new Button("��������"); 
	private Button carSearchBtn = new Button("�����˻�"); 
	private Button carListBtn = new Button("������ü����");
	private Button carBackBtn = new Button("�ڷΰ���");
	//������� ������Ʈ
	private JPanel carAddP= new JPanel();
	private JPanel carAddP_L= new JPanel();
	private JPanel carAddP_R= new JPanel();
	private JLabel carAddNumLB = new JLabel("������ȣ");
	private JLabel carAddTypeLB = new JLabel("����");
	private JLabel carAddKmLB = new JLabel("Ű�μ�");
	private JLabel carAddUserNameLB = new JLabel("�����ڸ�");
	private JTextField carAddNumTF = new JTextField(8);
	private JTextField carAddTypeTF = new JTextField(8);
	private JTextField carAddKmTF = new JTextField(8);
	private JTextField carAddUserNameTF = new JTextField(8);
	private Button carAddSaveBtn = new Button("����");
	private Button carAddbackBtn = new Button("�ڷΰ���");
	//�������� ������Ʈ
	private JPanel carDelP= new JPanel();
	private JTable carDelTB = new JTable();
	private JScrollPane carDelScr = new JScrollPane();
	private JButton carDelSetBtn = new JButton();
	private JButton cardelBackBtn = new JButton();
	
	public CarManager_GUI() {
		dao_CarManager.getInstance();
		init();
		mainMenu();
		actionList();
		carMenu();
		carAddMenu();
	}

	private void carAddMenu() {
		carAddP.setLayout(new GridLayout(1,2));
		carAddP.add(carAddP_L);
		carAddP.add(carAddP_R);
		carAddP_L.setLayout(new GridLayout(4,2));
		carAddP_L.add(carAddNumLB);
		carAddP_L.add(carAddNumTF);
		carAddP_L.add(carAddTypeLB);
		carAddP_L.add(carAddTypeTF);
		carAddP_L.add(carAddKmLB);
		carAddP_L.add(carAddKmTF);
		carAddP_L.add(carAddUserNameLB);
		carAddP_L.add(carAddUserNameTF);
		carAddP_R.add(carAddSaveBtn);
		carAddP_R.setLayout(new GridLayout());
	}

	private void carMenu() {
		carP.setLayout(new GridLayout(2,2));
		carP.add(carAddBtn);
		carP.add(carDelBtn);
		carP.add(carSearchBtn);
		carP.add(carListBtn);
	}

	private void actionList() {
		exitBtn.addActionListener(this);
		carBtn.addActionListener(this);
		carBackBtn.addActionListener(this);
		carAddBtn.addActionListener(this);
		carDelBtn.addActionListener(this);
		carSearchBtn.addActionListener(this);
		carListBtn.addActionListener(this);
		carAddSaveBtn.addActionListener(this);
		carAddbackBtn.addActionListener(this);
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
		this.setBounds(10, 10, 300, 350);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(exitBtn)) {
			System.exit(0);
		}
		if (e.getSource().equals(carBackBtn)) {
			this.remove(carP);
			this.add("Center",centerP);
			southP.remove(carBackBtn);
			southP.add(exitBtn);
			this.setVisible(false);
			this.setVisible(true);
		}
		if (e.getSource().equals(carBtn)) {
			this.remove(centerP);
			this.add("Center",carP);
			southP.remove(exitBtn);
			southP.add(carBackBtn);
			this.setVisible(false);
			this.setVisible(true);
		}
		if (e.getSource().equals(carAddBtn)) {
			this.remove(carP);
			this.add(carAddP);
			southP.remove(carBackBtn);
			southP.add(carAddbackBtn);
			this.setVisible(false);
			this.setVisible(true);
		}
		if (e.getSource().equals(carAddbackBtn)) {
			this.remove(carAddP);
			this.add(carP);
			southP.remove(carAddbackBtn);
			southP.add(carBackBtn);
			this.setVisible(false);
			this.setVisible(true);
		}
		if (e.getSource().equals(carAddSaveBtn)) {
			DTO_car dtoCar = new DTO_car();
			dtoCar.setCarNum(Integer.parseInt(carAddNumTF.getText()));
			dtoCar.setCarType(carAddTypeTF.getText());
			dtoCar.setKm(Integer.parseInt(carAddKmTF.getText()));
			dtoCar.setName(carAddUserNameTF.getText());
			dao_CarManager.carAdd(dtoCar);
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

}
