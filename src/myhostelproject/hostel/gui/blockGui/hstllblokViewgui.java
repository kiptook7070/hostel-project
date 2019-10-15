package myhostelproject.hostel.gui.blockGui;

import myhostelproject.hostel.hstlservices.blockservice;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import java.awt.Font;

public class hstllblokViewgui extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public hstllblokViewgui() {
		setBackground(new Color(0, 139, 139));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("THE DATA ENTERED TO BLOCK DATABASE");
		lblNewLabel.setBounds(44, 25, 313, 14);
		add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 430, 134);
		add(scrollPane);
		
		blockservice bs=new blockservice();
		table = new JTable(bs.rsttbl());
		table.setEnabled(true);
		table.setFont(new Font("Cordia New", Font.BOLD | Font.ITALIC, 22));
		table.setBackground(new Color(255, 153, 204));
		table.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 102, 153), null, null, null));
		scrollPane.setViewportView(table);

	}
}
