package myhostelproject.hostel.gui.studentGUI;

import myhostelproject.hostel.hstlmodels.studentmodels;
import myhostelproject.hostel.hstlservices.studentservices;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;
import myhostelproject.org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.util.Date;

public class hstlstudentgui extends JPanel {
	private JTextField tfaddStudnam;
	private JTextField tfaddPhnum;
	private JTextField tfaddEmail;
	private JTextField tfaddParnam;
	private JTextField tfaddParPh;
	private JTextField tfaddParEmail;
	private JTextField tfStudIDEdit;

	/**
	 * Create the panel.
	 */
	 public hstlstudentgui() {
		setBackground(new Color(216, 191, 216));
		setLayout(null);
		
		Dimension sz=Toolkit.getDefaultToolkit().getScreenSize();
		JTabbedPane Stud_tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		Stud_tabbedPane.setBounds(0, 11, sz.width, sz.height);
		add(Stud_tabbedPane);
		
		
		final JPanel studViewpanel = new JPanel();
		studViewpanel.setBackground(Color.PINK);
		Stud_tabbedPane.addTab("View or Edit Student", null, studViewpanel, null);
		studViewpanel.setLayout(null);
		
		final JTabbedPane tpstudview = new JTabbedPane(JTabbedPane.TOP);
		tpstudview.setBackground(new Color(255, 153, 204));
		tpstudview.setBounds(0, 51, 1133, 147);
		studViewpanel.add(tpstudview);
		
		JLabel lblNewLabel = new JLabel("STUDENT DETAILS OF HOSTEL");
		lblNewLabel.setForeground(new Color(102, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 19));
		lblNewLabel.setBounds(319, 0, 393, 39);
		studViewpanel.add(lblNewLabel);
		
								//======================Refresh button to refresh table details=============================
		JButton btnNewButton = new JButton("Refresh Details");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tpstudview.removeAll();
				studtableViewpane sv=new studtableViewpane();
				tpstudview.addTab("Table View",null,sv,null);
			}
		});
		btnNewButton.setBounds(123, 29, 140, 23);
		studViewpanel.add(btnNewButton);
		
		
		final JTabbedPane tabp1StdEdit = new JTabbedPane(JTabbedPane.TOP);
		tabp1StdEdit.setBounds(0, 278, 1228, 407);
		studViewpanel.add(tabp1StdEdit);
		
		JLabel lblNewLabel_1 = new JLabel("Enter the Student ID for Editing");
		lblNewLabel_1.setBounds(10, 237, 225, 14);
		studViewpanel.add(lblNewLabel_1);
		
		tfStudIDEdit = new JTextField();
		tfStudIDEdit.setBounds(236, 234, 100, 20);
		studViewpanel.add(tfStudIDEdit);
		tfStudIDEdit.setColumns(10);
		
		JButton btnStudEdiOK = new JButton("OK");
		btnStudEdiOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					tabp1StdEdit.removeAll();
					studentmodels sm=new studentmodels();
					sm.setStdId(Integer.parseInt(tfStudIDEdit.getText().trim()));
					studentservices ss=new studentservices();
					sm=ss.editidOk(sm);
			   
					if(sm.getStdfflag()==1){
						hstlstdEditpanel edi=new hstlstdEditpanel();
						edi.setStdValue(sm);
						tabp1StdEdit.addTab("Editor",null,edi,null);
					}
					else {
			    	
			    	tfStudIDEdit.setText("");
			    }
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null, "Enter only numbers...!");
				tfStudIDEdit.setText("");
			}
			}
		});
		btnStudEdiOK.setBounds(383, 233, 68, 23);
		studViewpanel.add(btnStudEdiOK);
		
		JPanel studaddpanel = new JPanel();
		studaddpanel.setBackground(new Color(153, 204, 153));
		Stud_tabbedPane.addTab("Add Student", null, studaddpanel, null);
		studaddpanel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("ENTER STUDENT DETAILS");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(153, 102, 51));
		lblNewLabel_2.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel_2.setBounds(325, 11, 388, 40);
		studaddpanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Enter student Name");
		lblNewLabel_3.setBounds(289, 83, 199, 14);
		studaddpanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Select Gender");
		lblNewLabel_4.setBounds(289, 121, 206, 14);
		studaddpanel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Select Date of Birth");
		lblNewLabel_5.setBounds(289, 157, 231, 14);
		studaddpanel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Enter Address");
		lblNewLabel_6.setBounds(289, 198, 231, 14);
		studaddpanel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Enter Phone Number");
		lblNewLabel_7.setBounds(290, 303, 237, 14);
		studaddpanel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Enter Valied E-mail ID");
		lblNewLabel_8.setBounds(289, 342, 231, 14);
		studaddpanel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Enter Parent Name");
		lblNewLabel_9.setBounds(289, 378, 231, 14);
		studaddpanel.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Enter Parent's Phone Number");
		lblNewLabel_10.setBounds(289, 419, 221, 14);
		studaddpanel.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Enter parent's E-mail ID");
		lblNewLabel_11.setBounds(289, 456, 231, 14);
		studaddpanel.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("Select Status of Student");
		lblNewLabel_12.setBackground(new Color(153, 204, 153));
		lblNewLabel_12.setBounds(289, 499, 206, 14);
		studaddpanel.add(lblNewLabel_12);
		
		tfaddStudnam = new JTextField();
		tfaddStudnam.setBounds(548, 80, 165, 20);
		studaddpanel.add(tfaddStudnam);
		tfaddStudnam.setColumns(10);
		
		tfaddPhnum = new JTextField();
		tfaddPhnum.setText("");
		tfaddPhnum.setBounds(548, 300, 125, 20);
		studaddpanel.add(tfaddPhnum);
		tfaddPhnum.setColumns(10);
		
		tfaddEmail = new JTextField();
		tfaddEmail.setText("");
		tfaddEmail.setBounds(548, 339, 141, 20);
		studaddpanel.add(tfaddEmail);
		tfaddEmail.setColumns(10);
		
		tfaddParnam = new JTextField();
		tfaddParnam.setText("");
		tfaddParnam.setBounds(548, 375, 165, 20);
		studaddpanel.add(tfaddParnam);
		tfaddParnam.setColumns(10);
		
		tfaddParPh = new JTextField();
		tfaddParPh.setText("");
		tfaddParPh.setBounds(548, 416, 125, 20);
		studaddpanel.add(tfaddParPh);
		tfaddParPh.setColumns(10);
		
		tfaddParEmail = new JTextField();
		tfaddParEmail.setText("");
		tfaddParEmail.setBounds(548, 453, 141, 20);
		studaddpanel.add(tfaddParEmail);
		tfaddParEmail.setColumns(10);
		
		final JTextArea taAddaddr = new JTextArea();
		taAddaddr.setColumns(3);
		taAddaddr.setTabSize(0);
		taAddaddr.setLineWrap(true);
		taAddaddr.setFont(new Font("Monospaced", Font.PLAIN, 13));
		taAddaddr.setBounds(548, 193, 199, 91);
		taAddaddr.scrollRectToVisible(getBounds());
		studaddpanel.add(taAddaddr);
		
		final JRadioButton rbaddGend1 = new JRadioButton("Male");
		rbaddGend1.setBackground(new Color(153, 204, 153));
		rbaddGend1.setBounds(546, 117, 109, 23);
		studaddpanel.add(rbaddGend1);
		
		final JRadioButton rbAddGend2 = new JRadioButton("Female");
		rbAddGend2.setBackground(new Color(153, 204, 153));
		rbAddGend2.setBounds(679, 117, 109, 23);
		studaddpanel.add(rbAddGend2);
		
		final ButtonGroup bg1=new ButtonGroup(); ////button group of gender
		bg1.add(rbaddGend1);
		bg1.add(rbAddGend2);
		
		final JRadioButton rbAddstat1 = new JRadioButton("Active");
		rbAddstat1.setBackground(new Color(153, 204, 153));
		rbAddstat1.setBounds(546, 495, 109, 23);
		studaddpanel.add(rbAddstat1);
		
		final JRadioButton rbAddstat2 = new JRadioButton("In Active");
		rbAddstat2.setBackground(new Color(153, 204, 153));
		rbAddstat2.setBounds(548, 537, 109, 23);
		studaddpanel.add(rbAddstat2);
		
		final ButtonGroup bg2=new ButtonGroup();////button group of Status
		bg2.add(rbAddstat1);
		bg2.add(rbAddstat2);
		
		final JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd-MM-yyyy");
		dateChooser.getCalendarButton().setForeground(new Color(30, 144, 255));
		dateChooser.getCalendarButton().setBackground(new Color(220, 20, 60));
		dateChooser.setBackground(new Color(255, 182, 193));
		dateChooser.setForeground(Color.BLACK);
		dateChooser.setBounds(545, 157, 133, 20);
		studaddpanel.add(dateChooser);
		dateChooser.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{dateChooser.getCalendarButton()}));

		
		
		
		
		JButton btnAddClear = new JButton("Clear All");
		btnAddClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			bg1.clearSelection();
			bg2.clearSelection();
			taAddaddr.setText("");
			tfaddEmail.setText("");
			tfaddParEmail.setText("");
			tfaddParnam.setText("");
			tfaddParPh.setText("");
			tfaddPhnum.setText("");
			tfaddStudnam.setText("");
			dateChooser.setCalendar(null);
			}
		});
		btnAddClear.setBounds(44, 201, 89, 72);
		studaddpanel.add(btnAddClear);
		
		JButton btnAddSubmit = new JButton("Submit");
		btnAddSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			try{
				studentmodels sm=new studentmodels();//--model
				
				 sm.setStudNam(tfaddStudnam.getText().trim());//1 std name
				 if(rbaddGend1.isSelected())sm.setStudGend(rbaddGend1.getActionCommand());//2 std gender
				  else sm.setStudGend(rbAddGend2.getActionCommand());
				 //------3 std_dob 
	 			 Date dcf=dateChooser.getDate();
				 String stdob=String.format("%1$td-%1$tm-%1$tY", dcf);
				 sm.setStdDob(stdob.trim());
				 
				 sm.setStudAddr(taAddaddr.getText().trim());//4 address
				 sm.setStudPh(Long.parseLong(tfaddPhnum.getText().trim()));//5 std ph
				 sm.setStudEml(tfaddEmail.getText().trim());//6 std email
				 sm.setParntname(tfaddParnam.getText().trim());//7 parnt name
				 sm.setParEml(tfaddParEmail.getText().trim());//8 parnt email
				 sm.setParPh(Long.parseLong(tfaddParPh.getText().trim()));// 9 parnt ph
				 if(rbAddstat1.isSelected())sm.setStudStat(rbAddstat1.getActionCommand());//10 stud stat
				 else sm.setStudStat(rbAddstat2.getActionCommand());
				
				 studentservices ss=new studentservices();
				 ss.addStud(sm);
			  }
			catch(Exception e){
				JOptionPane.showMessageDialog(null, "Fill coresponding fields....!");
				System.out.println("Error occoured...!");
			}
			}
		});
		btnAddSubmit.setBounds(44, 362, 89, 72);
		studaddpanel.add(btnAddSubmit);
		
		studentservices ss=new studentservices();
		


	}
}
