package myhostelproject.hostel.gui.floorGUI;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class hstlfloorgui extends JPanel {

	
	public hstlfloorgui() {
		setBackground(new Color(255, 192, 203));
		setLayout(null);
		
		final JTabbedPane tabbedPane1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane1.setBounds(255, 35, 536, 248);
		add(tabbedPane1);
		
		final JTabbedPane tabbedPane2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane2.setBounds(255, 349, 507, 354);
		add(tabbedPane2);
		
		
		JButton btnAddFloor = new JButton("ADD FLOOR");
		btnAddFloor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hstlflrAddgui fa=new hstlflrAddgui();
				tabbedPane2.removeAll();
				tabbedPane2.addTab("Insert floor", null,fa,null);
			
			}
		});
		btnAddFloor.setBounds(33, 349, 146, 56);
		add(btnAddFloor);
		
		JButton btnViewFloor = new JButton("VIEW FLOOR");
		btnViewFloor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hstlflrViewgui fvg=new hstlflrViewgui();
				tabbedPane1.removeAll();
				tabbedPane1.addTab("Floor Details",null,fvg,null);
			}
		});
		btnViewFloor.setBounds(33, 35, 146, 56);
		add(btnViewFloor);
		
		JButton btnEditFloor = new JButton("EDIT FLOOR");
		btnEditFloor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hstlflrEditgui hed=new hstlflrEditgui();
				tabbedPane2.removeAll();
				tabbedPane2.addTab("Edit Floor Record",null,hed,null);
			}
		});
		btnEditFloor.setBounds(33, 477, 146, 56);
		add(btnEditFloor);
		
		
		

	}
}
