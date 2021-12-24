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

public class UserDel_GUI extends JFrame implements ActionListener{
	DAO_CarManager dao_carmanager = null;
	private JPanel userDelSP = new JPanel();
	private JLabel userDelLB = new JLabel(User_GUI.userName+" 관리자 삭제 하시겠습니까?");
	private JButton yesBtn = new JButton("예");
	private JButton noBtn = new JButton("아니오");
	public UserDel_GUI() {
		dao_carmanager = DAO_CarManager.getInstance();
		init();
	}
	private void init() {
		this.add("South",userDelSP);
		this.add("Center",userDelLB);
		userDelSP.setLayout(new GridLayout(1,2));
		userDelSP.add(yesBtn);
		userDelSP.add(noBtn);
		
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
			dao_carmanager.userDel(User_GUI.userName);
			User_GUI.getUserList();
			dispose();
		}
	}
}

