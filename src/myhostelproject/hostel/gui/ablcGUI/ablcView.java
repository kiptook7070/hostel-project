package myhostelproject.hostel.gui.ablcGUI;

import myhostelproject.hostel.hstlmodels.ablcmodels;
import myhostelproject.hostel.hstlservices.ablcservices;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable.PrintMode;
import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.regex.PatternSyntaxException;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

public class ablcView extends JPanel {
	private JTable table;
    private TableRowSorter<TableModel>rsort ;
    private JTextField tfSercStudNam;
    private JTextField tfSercStRmno;
    private JPanel panelStudentPrint;
    private JButton btnPrintStud;
    private JComboBox cbStudId;
    private JPanel panelSerhMonth;
    private JLabel lblNewLabel_1;
    private JRadioButton rbSearch1;
    private JRadioButton rbSearch2;
    private JRadioButton rbSearch3;
    private JButton btnMnthOk;
    private JButton btnMnthprint;
    private JButton btnSerchYear;
    private JButton btnPrintYear;
    private JPanel panelSerhYear;
    private JYearChooser yearChooserSrchYear;
    private JMonthChooser monthChooser;;
	
	public ablcView() {
		setLayout(null);
		
		//////////////////////grouping radio buttons
		ButtonGroup bg=new ButtonGroup();
		bg.add(rbSearch1);
		bg.add(rbSearch2);
		bg.add(rbSearch3);
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(10, 41, 807, 110);
		add(scrollPane);
		
		ablcservices as=new ablcservices();
		table = new JTable(as.ablcTable());
		table.setBackground(new Color(204, 204, 255));
		rsort= new TableRowSorter<TableModel>(as.ablcTable());
		table.setRowSorter(rsort);
		table.setFillsViewportHeight(true);
		table.setRowHeight(24);				
		scrollPane.setViewportView(table);
		
		
		JLabel lblNewLabel = new JLabel("Details Table");
		lblNewLabel.setBounds(10, 11, 381, 14);
		add(lblNewLabel);
		
		panelStudentPrint = new JPanel();
		panelStudentPrint.setBackground(new Color(204, 255, 204));
		panelStudentPrint.setBounds(307, 172, 502, 159);
		panelStudentPrint.setVisible(false);
		add(panelStudentPrint);
		panelStudentPrint.setLayout(null);
		
		JLabel label = new JLabel("Select Student ID to Search");
		label.setBounds(26, 34, 226, 14);
		panelStudentPrint.add(label);
		
		//////////---------combo box for a Student only View by id in ablc table
		ablcservices as1=new ablcservices();
		cbStudId = new JComboBox();
		try {
			cbStudId.setModel(new DefaultComboBoxModel(as1.getStUdid().toArray()));
		}catch(NullPointerException e) {}
		cbStudId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ablcservices as=new ablcservices();
				ablcmodels am=new ablcmodels();
				if(!cbStudId.getSelectedItem().equals("-Select-")) {
					am.setAblcstdId(Integer.parseInt(String.valueOf(cbStudId.getSelectedItem())));
					am=as.ablcStdinfo(am);
					tfSercStudNam.setText(am.getAblcStudNam());
					tfSercStRmno.setText(am.getAblcRmnum());
					int i=Integer.parseInt(String.valueOf(cbStudId.getSelectedItem()));
					tableDispl(i,"","");
						btnPrintStud.setEnabled(true);
					
				}
				else {
					rsort.setRowFilter(null);	
					tfSercStudNam.setText("");
					tfSercStRmno.setText(""); 
					btnPrintStud.setEnabled(false);
				}
			}
		});
		cbStudId.setBounds(254, 30, 119, 20);
		panelStudentPrint.add(cbStudId);
		
		JLabel label_1 = new JLabel("Student Name");
		label_1.setBounds(26, 65, 119, 14);
		panelStudentPrint.add(label_1);
		
		JLabel label_2 = new JLabel("Student Room Number");
		label_2.setBounds(26, 96, 142, 14);
		panelStudentPrint.add(label_2);
		
		tfSercStRmno = new JTextField();
		tfSercStRmno.setBounds(254, 93, 119, 20);
		panelStudentPrint.add(tfSercStRmno);
		tfSercStRmno.setForeground(Color.WHITE);
		tfSercStRmno.setEnabled(false);
		tfSercStRmno.setEditable(false);
		tfSercStRmno.setColumns(10);
		tfSercStRmno.setBackground(Color.BLACK);
		
		
		
		btnPrintStud = new JButton("Print",new ImageIcon(ClassLoader.getSystemResource("myhostelproject/hostel/gui/_adminGUI/images/print.PNG")));;
		btnPrintStud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				 boolean complete = table.print(JTable.PrintMode.FIT_WIDTH,new MessageFormat("Student Name : "+tfSercStudNam.getText().toUpperCase()),new MessageFormat("Page {0}"),true,null,true,null);
				 printCompleteMsg(complete);
				}
				catch(PrinterException pe ) {
					
		            JOptionPane.showMessageDialog(null,"Printing Failed: " + pe.getMessage(),"Printing Result",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnPrintStud.setBounds(251, 124, 89, 23);
		panelStudentPrint.add(btnPrintStud);
		btnPrintStud.setEnabled(false);
		
		tfSercStudNam = new JTextField();
		tfSercStudNam.setBounds(254, 62, 153, 20);
		panelStudentPrint.add(tfSercStudNam);
		tfSercStudNam.setForeground(Color.WHITE);
		tfSercStudNam.setEnabled(false);
		tfSercStudNam.setEditable(false);
		tfSercStudNam.setColumns(10);
		tfSercStudNam.setBackground(Color.BLACK);
		
		panelSerhMonth = new JPanel();
		panelSerhMonth.setVisible(false);
		panelSerhMonth.setBackground(new Color(204, 255, 204));
		panelSerhMonth.setBounds(307, 342, 502, 76);
		add(panelSerhMonth);
		panelSerhMonth.setLayout(null);
		
		lblNewLabel_1 = new JLabel("Select Month");
		lblNewLabel_1.setBounds(30, 11, 134, 14);
		panelSerhMonth.add(lblNewLabel_1);
		
		monthChooser = new JMonthChooser();
		monthChooser.setBounds(205, 11, 118, 20);
		panelSerhMonth.add(monthChooser);
		
						///////////////////////----submit Month search ok button
		btnMnthOk = new JButton("Ok",new ImageIcon(ClassLoader.getSystemResource("myhostelproject/hostel/gui/_adminGUI/images/update.PNG")));;
		btnMnthOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final String mnth=monthName(monthChooser.getMonth());
				 
				tableDispl(0,"", mnth);
				 
				btnMnthprint.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							boolean cmplete=table.print(JTable.PrintMode.FIT_WIDTH,new MessageFormat("Month : "+mnth),new MessageFormat("Page {0}"), true, null,true,null);
							printCompleteMsg(cmplete);
						}
						catch(PrinterException p) {
							
							JOptionPane.showMessageDialog(null,"Printing Failed: " + p.getMessage(),"Printing Result",JOptionPane.ERROR_MESSAGE);
						}
					}

				
				});
				
			}
		});
		btnMnthOk.setBounds(205, 42, 61, 23);
		panelSerhMonth.add(btnMnthOk);
		
		btnMnthprint = new JButton("Print",new ImageIcon(ClassLoader.getSystemResource("myhostelproject/hostel/gui/_adminGUI/images/print.PNG")));;
		btnMnthprint.setEnabled(true);
		btnMnthprint.setBounds(331, 42, 91, 23);
		panelSerhMonth.add(btnMnthprint);
									
		rbSearch3 = new JRadioButton("Print Fees of Particular Year");
		rbSearch3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelStudentPrint.setVisible(false);
				 panelSerhYear.setVisible(true);
				 panelSerhMonth.setVisible(false); 
			}
		});
		rbSearch3.setBounds(38, 470, 209, 23);
		add(rbSearch3);
		
		panelSerhYear = new JPanel();
		panelSerhYear.setVisible(false);
		panelSerhYear.setBackground(new Color(204, 255, 204));
		panelSerhYear.setBounds(307, 429, 502, 90);
		add(panelSerhYear);
		panelSerhYear.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Select Year");
		lblNewLabel_3.setBounds(27, 31, 131, 14);
		panelSerhYear.add(lblNewLabel_3);
		
		yearChooserSrchYear = new JYearChooser();
		yearChooserSrchYear.setBounds(148, 31, 47, 20);
		panelSerhYear.add(yearChooserSrchYear);
		
	
		btnSerchYear = new JButton("Ok",new ImageIcon(ClassLoader.getSystemResource("myhostelproject/hostel/gui/_adminGUI/images/update.PNG")));;
		btnSerchYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnPrintYear.setEnabled(true);
				final String y=String.valueOf(yearChooserSrchYear.getYear());
			 	tableDispl(0,y,"");
				
				btnPrintYear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					 try {
						 	boolean complete=table.print( PrintMode.FIT_WIDTH,new MessageFormat("Hostel Fee of the Year : "+y),new MessageFormat("Page {0}"),true  ,null  ,true  ,null  );
						 	printCompleteMsg(complete);
					}  
					 catch(PrinterException p) {
						
							JOptionPane.showMessageDialog(null,"Printing Failed: " + p.getMessage(),"Printing Result",JOptionPane.ERROR_MESSAGE);
					 }
					}
				}); 
				
			}
		});
		btnSerchYear.setBounds(246, 27, 55, 23);
		panelSerhYear.add(btnSerchYear);
		
		btnPrintYear = new JButton("Print");
		btnPrintYear.setEnabled(false);
		btnPrintYear.setBounds(344, 27, 76, 23);
		panelSerhYear.add(btnPrintYear);
		
						
		rbSearch2 = new JRadioButton("Print Particular Month");
		rbSearch2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 panelStudentPrint.setVisible(false);
				 panelSerhYear.setVisible(false);
				 panelSerhMonth.setVisible(true); 
				 }
		});
		rbSearch2.setBounds(38, 342, 183, 23);
		add(rbSearch2);
										
		rbSearch1 = new JRadioButton("Print Particular Student Fee");
		rbSearch1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 panelStudentPrint.setVisible(true);
				 panelSerhYear.setVisible(false);
				 panelSerhMonth.setVisible(false); 
				 }
		});
		rbSearch1.setBounds(38, 172, 222, 23);
		add(rbSearch1);

	 }
					
		public void tableDispl(int s,String yer,String m) {
			 
		 	try {
		 		if(s!=0)rsort.setRowFilter(RowFilter.numberFilter(ComparisonType.EQUAL, s ,1));
				if (!m.equals("")) {
					rsort.setRowFilter(RowFilter.regexFilter(m));
				 	table.setRowSorter(rsort );		
				 }
				 if(!yer.equals("")) {
						rsort.setRowFilter(RowFilter.regexFilter(yer)); 
						
					} 
		 	}
		 	catch(PatternSyntaxException p) {
				p.printStackTrace();
				System.err.println("Bad regex pattern");
			}
		}
		
		
		public String monthName(int mont) {
			switch(mont) {
			case 0:return "January";
			case 1:return"February"; 
			case 2:return"March"; 
			case 3:return"April"; 
			case 4:return"May"; 
			case 5:return"June"; 
			case 6:return"July"; 
			case 7:return"August"; 
			case 8:return"September"; 
			case 9:return"October"; 
			case 10:return"November"; 
			case 11:return"December"; 
			}
			return null;
		}
	
		private void printCompleteMsg(boolean cmplete) {
			
            if (cmplete) {
               
                JOptionPane.showMessageDialog(null,
                                              "Printing Complete",
                                              "Printing Result",
                                              JOptionPane.INFORMATION_MESSAGE);
            } else {
                
                JOptionPane.showMessageDialog(null,
                                              "Printing Cancelled",
                                              "Printing Result",
                                              JOptionPane.INFORMATION_MESSAGE);
            }
			
		}///end of  Print Print Complete or cancel
}
