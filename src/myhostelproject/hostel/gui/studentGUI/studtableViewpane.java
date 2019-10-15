package myhostelproject.hostel.gui.studentGUI;

import myhostelproject.hostel.hstlservices.studentservices;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class studtableViewpane extends JPanel {
	private JTable table;
	private TableRowSorter<TableModel>trows;
	
	/**
	 * Create the panel.
	 */
	public studtableViewpane() {
		setBackground(new Color(102, 204, 153));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setEnabled(false);
		scrollPane.setBounds(0, 0, 1128, 123);
		add(scrollPane);
		
		studentservices ss=new studentservices();
		table = new JTable(ss.studView());
		table.setFillsViewportHeight(true);
		table.setToolTipText("click each column header to show\r\n as Ascending or Descending order");
		trows=new TableRowSorter<TableModel>(ss.studView());
		table.setRowSorter(trows);
		table.setBackground(new Color(255, 102, 153));
		scrollPane.setViewportView(table);
		

	}
}
