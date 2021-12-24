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
import DTO.DTO_user;

public class UserAdd_GUI extends JFrame implements ActionListener {
	DAO_CarManager dao_CarManager = null;
	private JPanel userAddP = new JPanel();
	private JLabel NameLB = new JLabel("이름");
	private JLabel teamLB = new JLabel("부서");
	private JLabel rankLB = new JLabel("직급");
	private TextField NameTF = new TextField(5);
	private TextField teamTF = new TextField(5);
	private TextField rankTF = new TextField(5);
	private Button setBtn = new Button("등록");
	private Button cancelBtn = new Button("종료");

	public UserAdd_GUI() {
		dao_CarManager = DAO_CarManager.getInstance();
		init();
	}

	private void init() {
		this.add("Center",userAddP);
		userAddP.setLayout(new GridLayout(1,8));
		userAddP.add(NameLB);
		userAddP.add(NameTF);
		userAddP.add(teamLB);
		userAddP.add(teamTF);
		userAddP.add(rankLB);
		userAddP.add(rankTF);
		userAddP.add(setBtn);
		userAddP.add(cancelBtn);
		
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
			DTO_user dto = new DTO_user();
			dto.setName(NameTF.getText());
			dto.setTeam(teamTF.getText());
			dto.setRank(rankTF.getText());
			dao_CarManager.userAdd(dto);
			NameTF.setText("");
			teamTF.setText("");
			rankTF.setText("");

			User_GUI.getUserList();
		}

	}
}
