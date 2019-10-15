package myhostelproject.hostel.gui.roomGUI;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class hstlroomgui extends JPanel {

	/**
	 * Create the panel.
	 */
	public hstlroomgui() {
		setBackground(new Color(173, 216, 230));
		setLayout(null);
		
			
		final JTabbedPane tabbedPane1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane1.setBounds(260, 31, 454, 242);
		add(tabbedPane1);
		
		final JTabbedPane tabbedPane2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane2.setBounds(260, 330, 441, 371);
		add(tabbedPane2);

		JButton btnAddRoom = new JButton("ADD ROOM");
		btnAddRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hstlRmaddgui ra=new hstlRmaddgui();
				tabbedPane2.removeAll();
				tabbedPane2.addTab("Add Room Details",null,ra,null);
			}
		});
		btnAddRoom.setBounds(38, 349, 146, 56);
		add(btnAddRoom);
		
		JButton btnViewRoom = new JButton("VIEW  ROOM");
		btnViewRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hstlRmviewgui rv=new hstlRmviewgui();
				tabbedPane1.removeAll();
				tabbedPane1.addTab("Room Details",null,rv,null);
			}
		});
		btnViewRoom.setBounds(38, 30, 146, 56);
		add(btnViewRoom);
		
		JButton btnEditRoom = new JButton("EDIT  ROOM");
		btnEditRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			hstlRmeditgui re=new hstlRmeditgui();
			tabbedPane2.removeAll();
			tabbedPane2.addTab("Edit Room Details",null,re,null);
			}
		});
		btnEditRoom.setBounds(38, 458, 146, 56);
		add(btnEditRoom);
		
	
	}
}
