package GUI;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import DAO.DAO_CarManager;
import DTO.DTO_user;

public class User_GUI extends JFrame implements ActionListener {
	static DAO_CarManager dao_carmanager = null;
	//관리자정보관리 컴포넌트
	private JPanel userP = new JPanel();
	private JPanel userSP = new JPanel();
	private Button userAddBtn = new Button("차량관리자등록");
	private Button userDelBtn = new Button("차랸관리자삭제");
	private Button userBackBtn = new Button("뒤로가기");
	//관리자리스트 컴포넌트
	private static String colNames[] = { "이름", "부서", "직급"};
	private static DefaultTableModel model = new DefaultTableModel(colNames, 0);
	private JTable userTB = new JTable(model);
	private JScrollPane userScr = new JScrollPane(userTB);
	static String userName = null;
	public User_GUI() {
		dao_carmanager = DAO_CarManager.getInstance();
		getUserList();
		init();
	}



	private void init() {
		this.add("Center",userP);
		this.add("South",userSP);
		userP.add("Center", userScr);
		userSP.setLayout(new GridLayout(1, 3));
		userSP.add(userAddBtn);
		userSP.add(userDelBtn);
		userSP.add(userBackBtn);
		
		this.setBounds(10, 10, 500, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		userAddBtn.addActionListener(this);
		userDelBtn.addActionListener(this);
		userBackBtn.addActionListener(this);
		
	}

	public static void getUserList() {
		model.setNumRows(0);
		ArrayList<DTO_user> userList = dao_carmanager.setUserList();
		for (int i = 0; i < userList.size(); i++) {
			model.addRow(new Object[] {userList.get(i).getName(),userList.get(i).getTeam(),
					userList.get(i).getRank()});
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(userBackBtn)) {
			new Menu_GUI();
			dispose();
		}
		if (e.getSource().equals(userAddBtn)) {
			new UserAdd_GUI();
		}
		if (e.getSource().equals(userDelBtn)) {
			int row = userTB.getSelectedRow();
			Object value= userTB.getValueAt(row,0);
			userName=(String)value;
			new UserDel_GUI();
		}
	}
}
