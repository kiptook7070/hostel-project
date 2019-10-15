package myhostelproject.hostel.gui.blockGui;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;

public class hstlblockgui extends JPanel {
	private static final long serialVersionUID = 1L;
	JTabbedPane tabbedPane,tabpanView;
	/**
	 * Create the panel.
	 */
	public hstlblockgui() {
		setBackground(new Color(152, 251, 152));
		setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(398, 274, 491, 381);
		add(tabbedPane);
		
		final JTabbedPane tabpanView = new JTabbedPane(JTabbedPane.TOP);
		tabpanView.setBounds(398, 27, 491, 210);
		add(tabpanView);
		
		JButton btnBlokAdd = new JButton("ADD BLOCK");
		btnBlokAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hstlblokAddgui hadd= new hstlblokAddgui();
				tabbedPane.removeAll();
				tabbedPane.add("Add Blocks",hadd); // adding method 'hstbblockaddgui ' to click event of add block button
			}
		});
		btnBlokAdd.setBounds(86, 274, 146, 56);
		add(btnBlokAdd);
		
		JButton btnBlokView = new JButton("VIEW BLOCK");
		btnBlokView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			hstllblokViewgui hview=new hstllblokViewgui();
			tabpanView.removeAll();
			tabpanView.addTab("View of Blocks",hview);/// viewing data entered in database
			}
		});
		btnBlokView.setBounds(86, 27, 146, 56);
		add(btnBlokView);
		
		JButton btnBlokEdit = new JButton("EDIT BLOCK");/////////To show edit panel
		btnBlokEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.removeAll();
				hstlblokEditgui hsbEd=new hstlblokEditgui();
				tabbedPane.addTab("Block Edit", null,hsbEd,null);
			}
		});
		btnBlokEdit.setBounds(86, 395, 146, 56);
		add(btnBlokEdit);
		
		
		
		
		

	}
}
