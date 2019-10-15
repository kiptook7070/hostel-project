package myhostelproject.hostel.gui.roomGUI;

import myhostelproject.hostel.hstlmodels.roommodels;
import myhostelproject.hostel.hstlservices.roomservices;

import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.Vector;
import java.awt.Dimension;

public class hstlRmeditgui extends JPanel {
	private JTextField tfrmediBlknam;
	private JTextField tfeditrmflrNum;
	private JTextField tfediRmNum;
	private JTextField tfeditotBed;
	private String str;
	final JComboBox cbediFlrnum,cbediBlknam;
	Vector<Object> vect1=new Vector<Object>();
	DefaultComboBoxModel model;
	private JTextField tfEdiRmId;
	/**
	 * Create the panel.
	 */
	public hstlRmeditgui() {
		setBackground(new Color(51, 153, 102));
		setLayout(null);
		
		final ButtonGroup bg=new ButtonGroup();
		
		roomservices rs=new roomservices();
		
		final JPanel panel = new JPanel();
		panel.setForeground(Color.GREEN);
		panel.setRequestFocusEnabled(false);
		panel.setSize(new Dimension(4, 0));
		panel.setBackground(new Color(51, 102, 153));
		panel.setAutoscrolls(true);
		panel.setBounds(0, 65, 450, 296);
		panel.setVisible(false);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select Block Name");
		lblNewLabel.setBounds(10, 46, 130, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Select Floor Number");
		lblNewLabel_1.setBounds(10, 92, 119, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Room Number");
		lblNewLabel_2.setBounds(10, 144, 130, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Total Beds in Room");
		lblNewLabel_3.setBounds(10, 172, 130, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Room Status");
		lblNewLabel_4.setBounds(10, 201, 130, 14);
		panel.add(lblNewLabel_4);
		
		JButton btnEditClear = new JButton("Clear");
		btnEditClear.setBounds(27, 248, 89, 23);
		panel.add(btnEditClear);
		
		final JRadioButton rbedirmStat2 = new JRadioButton("Not Available");
		rbedirmStat2.setBackground(new Color(51, 102, 153));
		rbedirmStat2.setBounds(275, 197, 109, 23);
		panel.add(rbedirmStat2);
		bg.add(rbedirmStat2);
		
		final JRadioButton rbedirmStat1 = new JRadioButton("Available");
		rbedirmStat1.setBackground(new Color(51, 102, 153));
		rbedirmStat1.setBounds(146, 197, 109, 23);
		panel.add(rbedirmStat1);
		bg.add(rbedirmStat1);
		
		////////////////////////////////////////////////////hstledit ACTION FOR BUTTON UPDATE////////////////////////
		JButton btnediUpdaterm = new JButton("Update");
		btnediUpdaterm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roommodels rmu=new roommodels();
				if(tfEdiRmId.getText().isEmpty()||tfrmediBlknam.getText().isEmpty()||tfeditrmflrNum.getText().isEmpty()||tfediRmNum.getText().isEmpty()||tfeditotBed.getText().isEmpty()||(!rbedirmStat1.isSelected()&&!rbedirmStat2.isSelected())) {
					JOptionPane.showMessageDialog(null, "Fill all Fields...!");
					return;
				}
				else {
					 rmu.setRm_id(Integer.parseInt(tfEdiRmId.getText().trim()));
					 rmu.setRmblknam(tfrmediBlknam.getText().trim());
					 rmu.setRmflrnum(Integer.parseInt(tfeditrmflrNum.getText().trim()));
					 rmu.setRmnum(tfediRmNum.getText().trim());
					 rmu.setRmtotbd(Integer.parseInt(tfeditotBed.getText().trim()));
					 if(rbedirmStat1.isSelected())rmu.setRmstat(rbedirmStat1.getActionCommand());
					  else rmu.setRmstat(rbedirmStat2.getActionCommand());
					roomservices rsu=new roomservices();
					 rsu.rmUpdate(rmu);
				}
			}
		});
		btnediUpdaterm.setBounds(166, 248, 89, 23);
		panel.add(btnediUpdaterm);
		
		tfeditotBed = new JTextField();
		tfeditotBed.setBounds(150, 169, 69, 20);
		panel.add(tfeditotBed);
		tfeditotBed.setColumns(10);
		
		tfediRmNum = new JTextField();
		tfediRmNum.setBounds(150, 141, 69, 20);
		panel.add(tfediRmNum);
		tfediRmNum.setColumns(10);
		
		tfeditrmflrNum = new JTextField();
		tfeditrmflrNum.setBounds(150, 89, 86, 20);
		panel.add(tfeditrmflrNum);
		tfeditrmflrNum.setBackground(Color.GRAY);
		tfeditrmflrNum.setEditable(false);
		tfeditrmflrNum.setEnabled(false);
		tfeditrmflrNum.setColumns(10);
		
		tfrmediBlknam = new JTextField();
		tfrmediBlknam.setForeground(Color.GREEN);
		tfrmediBlknam.setBounds(150, 43, 86, 20);
		panel.add(tfrmediBlknam);
		tfrmediBlknam.setBackground(Color.GRAY);
		tfrmediBlknam.setEditable(false);
		tfrmediBlknam.setEnabled(false);
		tfrmediBlknam.setColumns(10);
		cbediBlknam = new JComboBox(rs.comboviewBlkn());
		cbediBlknam.setBounds(298, 43, 86, 20);
		panel.add(cbediBlknam);
		
		 ////////////////////////////////////////////////hstl edit acion for Delete button//////////////////////
		 JButton btnediDelrm = new JButton("Delete");
		 btnediDelrm.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		roommodels rmd=new roommodels();
		 		rmd.setRm_id(Integer.parseInt(tfEdiRmId.getText()));
		 		roomservices rsd=new roomservices();
		 		rsd.rmdelete(rmd);
		 		panel.setVisible(false);
		 		tfEdiRmId.setText("");
		 	}
		 });
		 btnediDelrm.setBounds(295, 248, 89, 23);
		 panel.add(btnediDelrm);
		 
		 
		 vect1.add("-Select-");
			model=new DefaultComboBoxModel(vect1);
		 /////////////////////////////////////////////////////////////block combo//
		cbediBlknam.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				tfrmediBlknam.setText(String.valueOf(cbediBlknam.getSelectedItem()));////set current combo value to respective textbox.
				roommodels rm=new roommodels();
				roomservices rs=new roomservices();
				rm.setRmblknam(String.valueOf(cbediBlknam.getSelectedItem()));
				cbediFlrnum.setModel(new DefaultComboBoxModel(rs.getrmfloornames(rm).toArray()));	 
					
		}
			
		});
		
		 cbediFlrnum = new JComboBox(model);
		 cbediFlrnum.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent e) {
		 	tfeditrmflrNum.setText(String.valueOf(cbediFlrnum.getSelectedItem()));////set current combo value to respective textbox.
		 	}
		 });
		 cbediFlrnum.setBounds(298, 89, 86, 20);
		 panel.add(cbediFlrnum);
		 
		 JLabel lblNewLabel_5 = new JLabel("Enter Room ID");
		 lblNewLabel_5.setBounds(35, 21, 131, 23);
		 add(lblNewLabel_5);
		 
		 tfEdiRmId = new JTextField();
		 tfEdiRmId.setBounds(146, 22, 86, 20);
		 add(tfEdiRmId);
		 tfEdiRmId.setColumns(10);
		 		 
		 JButton btnEdClearId = new JButton("Clear");
		 btnEdClearId.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 	tfEdiRmId.setText("");
		 	}
		 });
		 btnEdClearId.setBounds(266, 41, 89, 23);
		 add(btnEdClearId);
		 									///////////////CLEARING
		btnEditClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					bg.clearSelection();
					tfediRmNum.setText("");
					tfeditotBed.setText("");
					tfeditrmflrNum.setText("");
					tfrmediBlknam.setText("");
					cbediBlknam.setSelectedIndex(0);
					cbediFlrnum.setSelectedItem("-Select-");
				}
				catch(Exception ee){
					JOptionPane.showMessageDialog(null, "NOTHING to clear......!");
				}
			}
		});
		////////////////////////////////////////////////////SUBMITTIG ROOM ID in OK button////////////
		 JButton btnEdiOkRmid = new JButton("OK");
		 btnEdiOkRmid.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 	 
		 		if(tfEdiRmId.getText().equals(""))JOptionPane.showMessageDialog(null, "Enter the Room ID....!");
		 	roommodels rmod=new roommodels();
		 		rmod.setRm_id(Integer.parseInt(tfEdiRmId.getText()));
		 	roomservices rs=new roomservices();
		 	rmod.setRmflag(1);
		 		rmod=rs.editOk(rmod);
		 		if(rmod.getRmflag()==0){
		 			tfEdiRmId.setText("");
		 			panel.setVisible(false);
		 		}
		 		else{
		 			panel.setVisible(true);
		 			tfrmediBlknam.setText(rmod.getRmblknam());
		 			tfeditrmflrNum.setText(String.valueOf(rmod.getRmflrnum()));
		 			tfediRmNum.setText(rmod.getRmnum());
		 			tfeditotBed.setText(String.valueOf(rmod.getRmtotbd()));
		 			if(rmod.getRmstat().equals("Available")) rbedirmStat1.setSelected(true);
		 			else rbedirmStat2.setSelected(true);
		 		}
		  
		 	
		 	
		 	}
		 });
		 btnEdiOkRmid.setBounds(266, 11, 89, 23);
		 add(btnEdiOkRmid);
	}///////end of Ok Submit button gui.
}
