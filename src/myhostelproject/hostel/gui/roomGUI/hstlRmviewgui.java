package myhostelproject.hostel.gui.roomGUI;

import myhostelproject.hostel.hstlservices.roomservices;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class hstlRmviewgui extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public hstlRmviewgui() {
		setBackground(new Color(51, 102, 153));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 430, 124);
		add(scrollPane);
		
		roomservices rs=new roomservices();
		table = new JTable(rs.rmview());
		table.setBackground(new Color(255, 153, 204));
		table.setEnabled(true);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("VIEW DETAILS OF ROOMS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(57, 11, 281, 36);
		add(lblNewLabel);

	}
}
