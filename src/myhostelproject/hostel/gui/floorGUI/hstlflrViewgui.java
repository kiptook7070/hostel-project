package myhostelproject.hostel.gui.floorGUI;

import myhostelproject.hostel.hstlservices.floorservices;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

public class hstlflrViewgui extends JPanel {
	private JTable flrtable;

	/**
	 * Create the panel.
	 */
	public hstlflrViewgui() {
		setBackground(new Color(0, 204, 153));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DETAILS OF FLOOR");
		lblNewLabel.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(128, 22, 260, 28);
		add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(10, 61, 512, 159);
		add(scrollPane);
		
		System.out.println("In the hostel view gui");
		floorservices fs=new floorservices(); 
		flrtable = new JTable(fs.florview());
		flrtable.setEnabled(true);
		flrtable.setBackground(new Color(255, 153, 153));
		flrtable.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 12));
		flrtable.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 204, 153), new Color(0, 0, 153), null, null));
		scrollPane.setViewportView(flrtable);

	}
}
