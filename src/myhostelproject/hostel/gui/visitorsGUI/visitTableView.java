package myhostelproject.hostel.gui.visitorsGUI;

import myhostelproject.hostel.hstlservices.visitorservices;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SpinnerDateModel;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.PatternSyntaxException;
import javax.swing.JButton;

public class visitTableView extends JPanel {
	private JTable tablevstr;
	private TableRowSorter<TableModel>rowsort;
	private JButton btndateSer;
	private JSpinner spinner;
	private JButton btnSearTime;
	/**
	 * Create the panel.
	 */
	public visitTableView() {
		setBackground(new Color(230, 230, 250));
		setLayout(null);
		
		final JDateChooser dcoosSearch = new JDateChooser();//------search with datechooser
		dcoosSearch.setDateFormatString("dd-MM-yyyy");
		dcoosSearch.setBounds(381, 336, 134, 20);
		dcoosSearch.setVisible(false);
		add(dcoosSearch);
				
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 51, 829, 111);
		add(scrollPane);
	
		//++++++++++++++++===================--------------------- VISITOR TABLE=========================
		visitorservices vs2=new visitorservices();
		tablevstr = new JTable(vs2.vistTable());
		tablevstr.setToolTipText("click each column header to show\r\n as Ascending or Descending order");
		tablevstr.setEnabled(true);
		rowsort =new TableRowSorter<TableModel>(vs2.vistTable());
		tablevstr.setRowSelectionAllowed(true);
		tablevstr.setRowSorter(rowsort);
		scrollPane.setViewportView(tablevstr);
		tablevstr.setBackground(new Color(102, 205, 170));
		tablevstr.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel lblNewLabel = new JLabel("Visitor Details Display");
		lblNewLabel.setForeground(new Color(51, 0, 153));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel.setBounds(72, 11, 561, 28);
		add(lblNewLabel);
		
		
		
		visitorservices vs1=new visitorservices();
		final JComboBox coboSearchnam = new JComboBox(vs1.vistComb());
		coboSearchnam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str=String.valueOf(coboSearchnam.getSelectedItem());
				if(str=="-Select-")rowsort.setRowFilter(null);
				else filterSerach(str);///----calling search method
			}
		});
		coboSearchnam.setBounds(381, 385, 134, 20);
		coboSearchnam.setVisible(false);
		add(coboSearchnam);
		
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Search with Date");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dcoosSearch.setVisible(true);
				coboSearchnam.setVisible(false);
				btnSearTime.setVisible(false);
				coboSearchnam.setSelectedIndex(0);
				spinner.setVisible(false);
				btndateSer.setVisible(true);
				btndateSer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Date dt=dcoosSearch.getDate();
						String sdat=String.format("%1$td-%1$tm-%1$tY", dt);
						sdat=sdat.trim();
						
						filterSerach(sdat);
					}
				});
				
			}
		});
		chckbxNewCheckBox.setBounds(121, 334, 169, 23);
		add(chckbxNewCheckBox);
		
		btndateSer = new JButton("OK");
		btndateSer.setBounds(554, 333, 64, 23);
		add(btndateSer);
		btndateSer.setVisible(false);
		
/////
	JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Search with Student Name");
	chckbxNewCheckBox_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		 coboSearchnam.setVisible(true);
		 dcoosSearch.setVisible(false);
		 dcoosSearch.setCalendar(null);
		 spinner.setVisible(false);
		 btndateSer.setVisible(false);
			dcoosSearch.setCalendar(null);
			btnSearTime.setVisible(false);
		}
	});
	chckbxNewCheckBox_1.setBounds(121, 382, 212, 23);
	add(chckbxNewCheckBox_1);
	
	
	JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Search with Time");
	chckbxNewCheckBox_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			spinner.setVisible(true);	
			btnSearTime.setVisible(true);
			btnSearTime.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SimpleDateFormat sd=new SimpleDateFormat("hh:mm a");
					Date dt=new Date();
					dt=(Date)spinner.getValue();
					String s=sd.format(dt);
					s=s.trim(); 
					filterSerach(s);
				}
			});
			dcoosSearch.setCalendar(null);
			coboSearchnam.setSelectedIndex(0);
		 coboSearchnam.setVisible(false);
		 dcoosSearch.setVisible(false);
		 btndateSer.setVisible(false);
		}
	});
	chckbxNewCheckBox_2.setBounds(121, 440, 134, 23);
	add(chckbxNewCheckBox_2);
	
	ButtonGroup bg=new ButtonGroup();
	bg.add(chckbxNewCheckBox);
	bg.add(chckbxNewCheckBox_1);
	bg.add(chckbxNewCheckBox_2);
		
	spinner = new JSpinner(new SpinnerDateModel(new Date(23400000L), null, null, Calendar.DAY_OF_MONTH));
	JSpinner.DateEditor de=new JSpinner.DateEditor(spinner,"hh:mm a");
	spinner.setEditor(de);
	spinner.setBounds(381, 441, 134, 20);
	spinner.setVisible(false);
	add(spinner);
	
	btnSearTime = new JButton("OK");
	btnSearTime.setBounds(554, 440, 64, 23);
	btnSearTime.setVisible(false);
	add(btnSearTime);
	}
		
	public void filterSerach(String s) {
		
		try{
				rowsort.setRowFilter(RowFilter.regexFilter(s));
			}
		catch(PatternSyntaxException p){
				p.printStackTrace();
				System.err.println("Bad regex pattern");
			}
		
	}
}   

