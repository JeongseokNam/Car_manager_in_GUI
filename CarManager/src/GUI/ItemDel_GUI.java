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

public class ItemDel_GUI extends JFrame implements ActionListener{
	DAO_CarManager dao_carmanager = null;
	private JPanel itemDelSP = new JPanel();
	private JLabel itemDelLB = new JLabel(Item_GUI.itemName+" 품목삭제 하시겠습니까?");
	private JButton yesBtn = new JButton("예");
	private JButton noBtn = new JButton("아니오");
	public ItemDel_GUI() {
		dao_carmanager = DAO_CarManager.getInstance();
		init();
	}
	private void init() {
		this.add("South",itemDelSP);
		this.add("Center",itemDelLB);
		itemDelSP.setLayout(new GridLayout(1,2));
		itemDelSP.add(yesBtn);
		itemDelSP.add(noBtn);
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
			dao_carmanager.itemDel(Item_GUI.itemName);
			Item_GUI.getItemList();
			dispose();
		}
		
	}

}
