package myhostelproject.hostel.gui.visitorsGUI;

import myhostelproject.hostel.hstlservices.visitorservices;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Color;

public class vistEditTableView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private TableRowSorter<TableModel> rsort;

	/**
	 * Create the panel.
	 */
	public vistEditTableView() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 883, 93);
		add(scrollPane);
		
		visitorservices vs=new visitorservices();
		table = new JTable(vs.vistTable());
		table.setEnabled(true);
		table.setFillsViewportHeight(true);
		rsort=new TableRowSorter<TableModel>(vs.vistTable());
		table.setRowSorter(rsort);
		 table.setRowSelectionAllowed(true);
		
		table.setBackground(new Color(210, 180, 140));
		scrollPane.setViewportView(table);

	}
}
