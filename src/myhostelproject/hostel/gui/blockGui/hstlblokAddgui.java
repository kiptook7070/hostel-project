package myhostelproject.hostel.gui.blockGui;


import myhostelproject.hostel.hstlmodels.blockmodels;
import myhostelproject.hostel.hstlservices.blockservice;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class hstlblokAddgui extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfBlknum;
	private JTextField tfBlkname;

	/**
	 * Create the panel.
	 */
	public hstlblokAddgui() {
			
	
		setBackground(new Color(51, 153, 153));
		setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Enter the Block Number");
		lblNewLabel_5.setBounds(33, 48, 232, 14);
		add(lblNewLabel_5);
		
		tfBlknum = new JTextField();
		tfBlknum.setBounds(272, 45, 58, 20);
		add(tfBlknum);
		tfBlknum.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Enter the Block Name");
		lblNewLabel_6.setBounds(33, 95, 157, 14);
		add(lblNewLabel_6);
		
		tfBlkname = new JTextField();
		tfBlkname.setBounds(272, 92, 157, 20);
		add(tfBlkname);
		tfBlkname.setColumns(10);
		
		final JRadioButton blkstat1 = new JRadioButton("Available");		
		blkstat1.setBackground(new Color(51, 153, 153));
		blkstat1.setSelected(false);								
		blkstat1.setBounds(271, 145, 109, 23);	
		add(blkstat1);
		
		final JRadioButton blkstat2 = new JRadioButton("Not Available"); 
		blkstat2.setBackground(new Color(51, 153, 153));
		blkstat2.setBounds(271, 192, 109, 23);
		blkstat2.setSelected(false);
		add(blkstat2);

		final ButtonGroup bg=new ButtonGroup();						
		bg.add(blkstat2);
		bg.add(blkstat1);
		
								
		
		JButton btnOKadd = new JButton("Submit");				
		btnOKadd.addActionListener(new ActionListener() {				
				public void actionPerformed(ActionEvent arg0) {
				
				blockmodels bm=new blockmodels();
				
				bm.setBlkNum(Integer.parseInt(tfBlknum.getText()));
				bm.setBlkNam(tfBlkname.getText());
				if(blkstat1.isSelected()) bm.setBlkStat(blkstat1.getActionCommand());		 
				else bm.setBlkStat(blkstat2.getActionCommand());								
				
				blockservice bs=new blockservice();
													  
				bs.addblok(bm);						 
				
				
				tfBlknum.setText("");
				tfBlkname.setText("");
				bg.clearSelection();
				
			}
		});
		btnOKadd.setBounds(309, 248, 89, 23);
		add(btnOKadd);
		
		JButton btnclear = new JButton("Clear");			
		btnclear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfBlknum.setText("");
				tfBlkname.setText("");
				bg.clearSelection();
				
			}
		});
		btnclear.setBounds(118, 248, 89, 23);
		add(btnclear);
		
		JLabel lblNewLabel_8 = new JLabel("Status of Availability");
		lblNewLabel_8.setBounds(33, 149, 201, 14);
		add(lblNewLabel_8);

	}
	
}
