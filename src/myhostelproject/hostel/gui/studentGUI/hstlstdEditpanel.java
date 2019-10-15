package myhostelproject.hostel.gui.studentGUI;

import myhostelproject.hostel.hstlmodels.studentmodels;
import myhostelproject.hostel.hstlservices.studentservices;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.toedter.calendar.JDateChooser;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class hstlstdEditpanel extends JPanel {
	private static Date dob = null;
	private JRadioButton rbEditGend1,rbEditGend2,rbEdiStat1,rbEdiStat2;
	private JTextField tfEdistNam, tfStdEdiPh,tfEdiStdEmai,tfEdiParNam,tfEdiParPh,tfEdiParEmai;
	private JTextArea taediAddr; 
	private JDateChooser dateChooserEdi;
	private ButtonGroup bg1,bg2;
	private int delid;

	/**
	 * Create the panel.
	 */
	public hstlstdEditpanel() {
		setBackground(new Color(204, 204, 255));
		setLayout(null);
		
		JLabel lblStudentName = new JLabel("Student Name");
		lblStudentName.setBounds(36, 65, 147, 14);
		add(lblStudentName);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(36, 90, 124, 14);
		add(lblGender);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setBounds(36, 144, 147, 14);
		add(lblDateOfBirth);
		
		JLabel lblStudentsAddress = new JLabel("Student's Address");
		lblStudentsAddress.setBounds(36, 180, 147, 14);
		add(lblStudentsAddress);
		
		JLabel lblStudentsPhoneNumber = new JLabel("Student's Phone Number");
		lblStudentsPhoneNumber.setBounds(417, 68, 154, 14);
		add(lblStudentsPhoneNumber);
		
		JLabel lblStudentsEmailId = new JLabel("Student's E-mail ID");
		lblStudentsEmailId.setBounds(417, 109, 124, 14);
		add(lblStudentsEmailId);
		
		JLabel lblParentName = new JLabel("Parent Name");
		lblParentName.setBounds(417, 159, 109, 14);
		add(lblParentName);
		
		JLabel lblParentsPhoneNumber = new JLabel("Parent's Phone Number");
		lblParentsPhoneNumber.setBounds(417, 202, 138, 14);
		add(lblParentsPhoneNumber);
		
		JLabel lblParentsEmailId = new JLabel("Parent's E-mail ID");
		lblParentsEmailId.setBounds(756, 71, 131, 14);
		add(lblParentsEmailId);
		
		JLabel lblStatusOfStudent = new JLabel("Status of Student");
		lblStatusOfStudent.setBackground(new Color(153, 204, 153));
		lblStatusOfStudent.setBounds(756, 117, 124, 14);
		add(lblStatusOfStudent);
		
		 dateChooserEdi = new JDateChooser();
		dateChooserEdi.setDateFormatString("dd-MM-yyyy");
		dateChooserEdi.setBounds(201, 144, 124, 20);
		add(dateChooserEdi);
		
		 rbEditGend1 = new JRadioButton("Male");
		rbEditGend1.setBackground(new Color(204, 204, 255));
		rbEditGend1.setBounds(201, 86, 109, 23);
		add(rbEditGend1);
		
	    rbEditGend2 = new JRadioButton("Female");
		rbEditGend2.setBackground(new Color(204, 204, 255));
		rbEditGend2.setBounds(201, 114, 109, 23);
		add(rbEditGend2);
		
		 bg1=new ButtonGroup();///button group
		bg1.add(rbEditGend1);
		bg1.add(rbEditGend2);
		bg1.clearSelection();
		
		rbEdiStat1 = new JRadioButton("Active");
		rbEdiStat1.setBackground(new Color(204, 204, 255));
		rbEdiStat1.setBounds(899, 114, 109, 23);
		add(rbEdiStat1);
		
		rbEdiStat2 = new JRadioButton("In Active");
		rbEdiStat2.setBackground(new Color(204, 204, 255));
		rbEdiStat2.setBounds(899, 146, 109, 23);
		add(rbEdiStat2);
		
		 bg2=new ButtonGroup();//button group
		bg2.add(rbEdiStat1);
		bg2.add(rbEdiStat2);
		bg2.clearSelection();
		
		tfEdistNam = new JTextField();
		tfEdistNam.setBounds(201, 62, 147, 20);
		add(tfEdistNam);
		tfEdistNam.setColumns(10);
		
		tfStdEdiPh = new JTextField();
		tfStdEdiPh.setText("");
		tfStdEdiPh.setBounds(582, 65, 97, 20);
		add(tfStdEdiPh);
		tfStdEdiPh.setColumns(10);
		
		taediAddr = new JTextArea();
		taediAddr.setBounds(201, 175, 124, 80);
		add(taediAddr);
		
		tfEdiStdEmai = new JTextField();
		tfEdiStdEmai.setBounds(582, 106, 138, 20);
		add(tfEdiStdEmai);
		tfEdiStdEmai.setColumns(10);
		
		tfEdiParNam = new JTextField();
		tfEdiParNam.setBounds(582, 156, 142, 20);
		add(tfEdiParNam);
		tfEdiParNam.setColumns(10);
		
		tfEdiParPh = new JTextField();
		tfEdiParPh.setText("");
		tfEdiParPh.setBounds(582, 199, 86, 20);
		add(tfEdiParPh);
		tfEdiParPh.setColumns(10);
		
		tfEdiParEmai = new JTextField();
		tfEdiParEmai.setText("");
		tfEdiParEmai.setBounds(899, 65, 138, 20);
		add(tfEdiParEmai);
		tfEdiParEmai.setColumns(10);
		////////////=========----------------------------------Clearing Entire Fields
		JButton btnNewButton = new JButton("Clear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bg1.clearSelection();
				bg2.clearSelection();
				tfEdiParEmai.setText("");
				tfEdiParNam.setText("");
				tfEdiParPh.setText("");
				tfEdiStdEmai.setText("");
				tfEdistNam.setText("");
				taediAddr.setText("");
				tfStdEdiPh.setText("");
				dateChooserEdi.setCalendar(null);
			}
		});
		btnNewButton.setBounds(377, 290, 89, 23);
		add(btnNewButton);
///=====-------------------------------------------------Update student details--------------------------------		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentmodels sm2=new  studentmodels();
				
	 			 Date dcf=dateChooserEdi.getDate();
				 String stdob=String.format("%1$td-%1$tm-%1$tY", dcf);
				
				 
				 sm2.setStdId(delid);
				 
				if((stdob.equals(null))||(tfEdistNam.getText().isEmpty())||(taediAddr.getText().isEmpty())||(tfStdEdiPh.getText().isEmpty())||(tfEdiStdEmai.getText().isEmpty())||(tfEdiParNam.getText().isEmpty())||(tfEdiParEmai.getText().isEmpty())||(tfEdiParPh.getText().isEmpty())) {
					JOptionPane.showMessageDialog(null, "Fill  Corresponding fields...! ");
					return;
				}
				else {
					 sm2.setStudNam(tfEdistNam.getText().trim());//1 std name
					 if(rbEditGend1.isSelected())sm2.setStudGend(rbEditGend1.getActionCommand());//2 std gender
					  else sm2.setStudGend(rbEditGend2.getActionCommand());
					
					 sm2.setStdDob(stdob.trim());
					 sm2.setStudAddr(taediAddr.getText().trim());//4 address
					 sm2.setStudPh(Long.parseLong(tfStdEdiPh.getText().trim()));//5 std ph
					 sm2.setStudEml(tfEdiStdEmai.getText().trim());//6 std email
					 sm2.setParntname(tfEdiParNam.getText().trim());//7 parnt name
					 sm2.setParEml(tfEdiParEmai.getText().trim());//8 parnt email
					 sm2.setParPh(Long.parseLong(tfEdiParPh.getText().trim()));// 9 parnt ph
					 if(rbEdiStat1.isSelected())sm2.setStudStat(rbEdiStat1.getActionCommand());//10 stud stat
					 else sm2.setStudStat(rbEdiStat2.getActionCommand());
					
					 studentservices ss2=new studentservices();
					 ss2.stuUpdate(sm2);
			}
		  }
		});
		btnNewButton_1.setBounds(564, 290, 89, 23);
		add(btnNewButton_1);
		
		////===-------------------------------------------------delete student Details-------------------------
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				studentmodels sm1=new studentmodels();
				sm1.setStdId(delid);
				studentservices ss=new studentservices();
				sm1=ss.studDel(sm1);
			}
		});
		btnNewButton_2.setBounds(775, 290, 89, 23);
		add(btnNewButton_2);

	}
	
	//===-----------------------------------------set values in the fields for selected Student id--------------------------------------------------
	public void setStdValue(studentmodels sm){
		tfEdistNam.setText(sm.getStudNam());//===set name
		if(sm.getStudGend().equals("Male")) rbEditGend1.setSelected(true);//====set gender
		 else rbEditGend2.setSelected(true);
		//======to set date in date chooser========
		try{
			String sd=sm.getStdDob();
			DateFormat df=new SimpleDateFormat("dd-MM-yyyy");
		    dob=df.parse(sd);
		}
		catch(Exception du){
			System.out.println("Date cannot retrive error.... "+du);
		}
		 dateChooserEdi.setDate(dob);
		 delid=sm.getStdId();///getting student id to delete or update with same 
		 taediAddr.setText(sm.getStudAddr());
		 tfStdEdiPh.setText(String.valueOf(sm.getStudPh()));
		 tfEdiStdEmai.setText(sm.getStudEml());
		 tfEdiParNam.setText(sm.getParntname());
		 tfEdiParPh.setText(String.valueOf(sm.getParPh()));
		 tfEdiParEmai.setText(sm.getParEml());
		 if(sm.getStudStat().equals("Active")) rbEdiStat1.setSelected(true);
		 else rbEdiStat2.setSelected(true);
	}
}
