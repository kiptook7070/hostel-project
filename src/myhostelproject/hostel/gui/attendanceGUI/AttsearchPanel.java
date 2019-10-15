package myhostelproject.hostel.gui.attendanceGUI;

import myhostelproject.hostel.hstlmodels.attModel;
import myhostelproject.hostel.hstlservices.atendanService;

import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.DefaultRowSorter;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.regex.PatternSyntaxException;
import com.toedter.calendar.JDateChooser;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;

public class AttsearchPanel extends JPanel {
	private JTable Viewtable;
	private JRadioButton rb1AttsearchID;
	private JRadioButton rb2AttsearchDate;
	private TableRowSorter<DefaultTableModel> tablRsrt;
	private JDateChooser dateChooserSearch;
	private JPanel dateSearchpanel;
	private JButton btnDateSearchOk;

	public AttsearchPanel() {
		setBackground(new Color(153, 255, 204));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(99, 38, 784, 199);
		add(scrollPane);
		
		atendanService ats=new atendanService();
		
		Viewtable = new JTable(ats.ViewAttend());
		tablRsrt=new TableRowSorter<DefaultTableModel>(ats.ViewAttend());// -----setting table fileter
		Viewtable.setRowSorter(tablRsrt);
		Viewtable.setFillsViewportHeight(true);
		Viewtable.setRowHeight(24);		/// ------------------ set all row height must needed for error free "printing" 
		scrollPane.setViewportView(Viewtable);
		
		
	
		rb1AttsearchID = new JRadioButton("Search With Student ID\r\n");
		rb1AttsearchID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					dateSearchpanel.setVisible(false);
					int id=Integer.parseInt(JOptionPane.showInputDialog(AttsearchPanel.this,"Enter the Strudent id to search ?"));
					
					if(id!=0) {
						try {
							tablRsrt.setRowFilter(RowFilter.numberFilter(ComparisonType.EQUAL,id, 3));
							Viewtable.setRowSorter(tablRsrt);
						}catch(PatternSyntaxException ps) {
							JOptionPane.showMessageDialog(null, "Bad Regx Pattern.....!");
							ps.printStackTrace();
						}
					}
				}catch(Exception ew) {}
				
				
			}
		});
		 
		rb1AttsearchID.setBackground(new Color(153, 255, 204));
		rb1AttsearchID.setBounds(37, 328, 196, 23);
		add(rb1AttsearchID);
		
		rb2AttsearchDate = new JRadioButton("Search With DATE");
		rb2AttsearchDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					dateSearchpanel.setVisible(true);			 
			 }
		});
				
		rb2AttsearchDate.setBackground(new Color(153, 255, 204));
		rb2AttsearchDate.setBounds(37, 417, 217, 23);
		add(rb2AttsearchDate);
		
		ButtonGroup bg=new ButtonGroup();
		bg.add(rb1AttsearchID);
		bg.add(rb2AttsearchDate);
		
		dateSearchpanel = new JPanel();
		dateSearchpanel.setBackground(new Color(153, 255, 204));
		dateSearchpanel.setBounds(282, 403, 353, 146);
		add(dateSearchpanel);
		dateSearchpanel.setVisible(false);
		dateSearchpanel.setLayout(null);
		
		dateChooserSearch = new JDateChooser();
		dateChooserSearch.setDateFormatString("dd-MM-yyyy");
		dateChooserSearch.setBounds(205, 33, 111, 20);
		dateSearchpanel.add(dateChooserSearch);
		
		JLabel lblNewLabel = new JLabel("Select Date to Search");
		lblNewLabel.setBounds(25, 33, 164, 20);
		dateSearchpanel.add(lblNewLabel);
		
		btnDateSearchOk = new JButton("Ok",new ImageIcon(ClassLoader.getSystemResource("myhostelproject/hostel/gui/_adminGUI/images/update.PNG")));;				///------------search attendance with date with date
		btnDateSearchOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Date daser=dateChooserSearch.getDate();
				String datStr=String.format("%1$td-%1$tm-%1$tY",daser);
				String []ard=(datStr.trim()).split("-");
				//JOptionPane.showMessageDialog(null, ard[0]+"\n"+ard[1]+"\n"+ard[2]+"\n");
				DateSearch(Integer.parseInt( ard[0]),ard[1],Integer.parseInt(ard[2]));
				
			}
		});
		btnDateSearchOk.setBounds(123, 94, 89, 23);
		dateSearchpanel.add(btnDateSearchOk);
		
	}
	public void DateSearch(int day,String mnth,int year) {
		System.out.println("From Date search Method in AttsearchPanel : "+day+" "+mnth+" "+year); 
	 	try {
	 		
			tablRsrt.setRowFilter(RowFilter.numberFilter(ComparisonType.EQUAL, year,2));// filter of year
			Viewtable.setRowSorter(tablRsrt);
			tablRsrt=new TableRowSorter<DefaultTableModel>((DefaultTableModel) Viewtable.getModel());
			tablRsrt.setRowFilter(RowFilter.regexFilter(mnth));// filter of month
			tablRsrt=new TableRowSorter<DefaultTableModel>((DefaultTableModel) Viewtable.getModel());
			Viewtable.setRowSorter(tablRsrt);
			tablRsrt=new TableRowSorter<DefaultTableModel>((DefaultTableModel) Viewtable.getModel());
	 		tablRsrt.setRowFilter(RowFilter.numberFilter(ComparisonType.EQUAL, day,0));/// filtering using numbers // -- the argument '0' is the column number to be sorted
	 		Viewtable.setRowSorter(tablRsrt );
	 				
	 	}
	 	catch(PatternSyntaxException p) {
			p.printStackTrace();
			System.err.println("Bad regex pattern");
		}
	}//end/
}