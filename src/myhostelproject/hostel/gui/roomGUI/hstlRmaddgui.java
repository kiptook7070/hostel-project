package myhostelproject.hostel.gui.roomGUI;

import myhostelproject.hostel.hstlconnection.hstlconnection;
import myhostelproject.hostel.hstlmodels.roommodels;
import myhostelproject.hostel.hstlservices.roomservices;

import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class hstlRmaddgui extends JPanel {
	private static final JComboBox cbrmAddBlknam = null;
	private JTextField tfrmaddRmNum;
	private JTextField tfrmaddtotNumBed;
	private JComboBox cbrmaddFlrnum1=new JComboBox();
	String str;
	Vector<Object> vect1=new Vector<Object>();
	DefaultComboBoxModel model;
	private JComboBox cbrmAddBlknam_1;
	
	/**
	 * Create the panel.
	 */
	public hstlRmaddgui() {
		setBackground(new Color(0, 102, 153));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select Block Name");
		lblNewLabel.setBounds(49, 42, 156, 14);
		add(lblNewLabel);
		
		
		
		JLabel lblNewLabel_2 = new JLabel("Enter Room Number");
		lblNewLabel_2.setBounds(49, 95, 128, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Enter Total Number of Bed");
		lblNewLabel_3.setBounds(49, 120, 156, 14);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Select Room Status");
		lblNewLabel_4.setBounds(49, 158, 128, 14);
		add(lblNewLabel_4);
		
		//final JComboBox cbrmaddFlrnum = new JComboBox();
		JLabel lblNewLabel_1 = new JLabel("Select Floor Number");
		lblNewLabel_1.setBounds(49, 70, 128, 14);
		add(lblNewLabel_1);
														///////////////////blockname combobox//////////////
		vect1.add("-Select-");
		model=new DefaultComboBoxModel(vect1);
		final roomservices rs1=new roomservices();
		cbrmAddBlknam_1 = new JComboBox(rs1.comboviewBlkn());
		cbrmAddBlknam_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				roommodels rm=new roommodels();
				rm.setRmblknam(String.valueOf(cbrmAddBlknam_1.getSelectedItem()));
				roomservices rs=new roomservices();
				cbrmaddFlrnum1.setModel(new DefaultComboBoxModel(rs.getrmfloornames(rm).toArray()));
				
				
			}
		});
		
		cbrmAddBlknam_1.setBounds(268, 39, 90, 20);
		add(cbrmAddBlknam_1);
      
      
		cbrmaddFlrnum1=new JComboBox(model);
		cbrmaddFlrnum1.setEnabled(true);
		cbrmaddFlrnum1.setBounds(268, 67, 78, 20);
		cbrmaddFlrnum1.setVisible(true);
		add(cbrmaddFlrnum1);
		
		tfrmaddRmNum = new JTextField();
		tfrmaddRmNum.setBounds(268, 92, 67, 20);
		add(tfrmaddRmNum);
		tfrmaddRmNum.setColumns(10);
		
		tfrmaddtotNumBed = new JTextField();
		tfrmaddtotNumBed.setBounds(268, 117, 67, 20);
		add(tfrmaddtotNumBed);
		tfrmaddtotNumBed.setColumns(10);
		
		final JRadioButton rbrmaddStat1 = new JRadioButton("Available");
		rbrmaddStat1.setBackground(new Color(0, 102, 153));
		rbrmaddStat1.setBounds(268, 154, 109, 23);
		add(rbrmaddStat1);
		
		final JRadioButton rbrmaddStat2 = new JRadioButton("Not Available");
		rbrmaddStat2.setBackground(new Color(0, 102, 153));
		rbrmaddStat2.setBounds(268, 190, 109, 23);
		add(rbrmaddStat2);
		
		final ButtonGroup bg=new ButtonGroup();
		bg.add(rbrmaddStat1);
		bg.add(rbrmaddStat2);
		
		JButton btnaddrmSubm = new JButton("Submit");
		btnaddrmSubm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try{	
				System.out.println(cbrmaddFlrnum1.getSelectedItem());
				roommodels rm=new roommodels();
					
				 if(cbrmAddBlknam_1.getSelectedItem()=="-Select-")JOptionPane.showMessageDialog(null, "Select Block Name....!");
				 else rm.setRmblknam(String.valueOf(cbrmAddBlknam_1.getSelectedItem()).trim());
				
				 if(cbrmaddFlrnum1.getSelectedItem().equals("-Select-"))JOptionPane.showMessageDialog(null, "Select Floor Number....!");
				 else rm.setRmflrnum(Integer.parseInt(String.valueOf((cbrmaddFlrnum1.getSelectedItem())).trim()));
				 rm.setRmnum(tfrmaddRmNum.getText().trim());
				 rm.setRmtotbd(Integer.parseInt(tfrmaddtotNumBed.getText().trim()));
				 if(rbrmaddStat1.isSelected())rm.setRmstat(rbrmaddStat1.getActionCommand().trim()); 
				 else rm.setRmstat(rbrmaddStat2.getActionCommand().trim());
				 
				roomservices rs=new roomservices();
				rs.roomInsert(rm);
				
			}
			catch(Exception e3 ){
		 		JOptionPane.showMessageDialog(null, "Error in Entery or please Fill fields...!");
		 	}
		  }
		});
		btnaddrmSubm.setBounds(268, 249, 89, 23);
		add(btnaddrmSubm);
					        
		JButton btnrmaddClr = new JButton("Clear");
		btnrmaddClr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbrmAddBlknam_1.setSelectedIndex(0);
				cbrmaddFlrnum1.setSelectedItem("-Select-");
				tfrmaddRmNum.setText("");
				tfrmaddtotNumBed.setText("");
				bg.clearSelection();
			}
		});
		btnrmaddClr.setBounds(70, 249, 89, 23);
		add(btnrmaddClr);

	}
}
