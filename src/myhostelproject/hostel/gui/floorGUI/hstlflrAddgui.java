package myhostelproject.hostel.gui.floorGUI;

import myhostelproject.hostel.hstlmodels.floormodels;
import myhostelproject.hostel.hstlservices.floorservices;

import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class hstlflrAddgui extends JPanel {
	private JTextField tfflrNum;
	private JTextField tfflrNam;

	/**
	 * Create the panel.
	 */
	public hstlflrAddgui() {
		setBackground(new Color(0, 153, 153));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select Block Name");
		lblNewLabel.setBounds(24, 61, 190, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Floor num");
		lblNewLabel_1.setBounds(24, 97, 174, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Enter Floor Name");
		lblNewLabel_2.setBounds(24, 135, 174, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Select Floor Status");
		lblNewLabel_3.setBounds(24, 173, 174, 14);
		add(lblNewLabel_3);
		
		floorservices fs=new floorservices();
		final JComboBox cobflrblknam = new JComboBox(fs.comboview());
		cobflrblknam.setOpaque(false);
		cobflrblknam.setBounds(247, 58, 113, 20);
		add(cobflrblknam);

		
		tfflrNum = new JTextField();
		tfflrNum.setBounds(247, 94, 60, 20);
		add(tfflrNum);
		tfflrNum.setColumns(10);
		
		tfflrNam = new JTextField();
		tfflrNam.setBounds(246, 132, 132, 20);
		add(tfflrNam);
		tfflrNam.setColumns(10);
		
		final JRadioButton rbstat1 = new JRadioButton("Available");
		rbstat1.setBackground(new Color(0, 153, 153));
		rbstat1.setBounds(247, 169, 109, 23);
		add(rbstat1);
		
		final JRadioButton rdstat2 = new JRadioButton("Not Available");
		rdstat2.setBackground(new Color(0, 153, 153));
		rdstat2.setBounds(247, 195, 109, 23);
		add(rdstat2);
		
		final ButtonGroup bg=new ButtonGroup();
		bg.add(rbstat1);
		bg.add(rdstat2);
		
		JButton btnflrClr = new JButton("Clear");
		btnflrClr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			cobflrblknam.setSelectedIndex(0);
			tfflrNam.setText("");
			tfflrNum.setText("");
			bg.clearSelection();
			}
		});
		btnflrClr.setBounds(90, 257, 89, 23);
		add(btnflrClr);
		/////////////////////////////////////////////inserting floordetails or submitting floor details  to database 
		JButton btnflrOk = new JButton("Submit");
		btnflrOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				floormodels fm=new floormodels();//passing values to floor model
				
				if(cobflrblknam.getSelectedItem()=="-Select-")JOptionPane.showMessageDialog(null, "Select a Block name.");
				 else fm.setBlknam(String.valueOf(cobflrblknam.getSelectedItem()));/// setting combobox value to floormodel by converting object to string.
				fm.setFlrnum(Integer.parseInt(tfflrNum.getText()));
				fm.setFlrnam(tfflrNam.getText());
				if(rbstat1.isSelected())fm.setFlrstat(rbstat1.getActionCommand());
				else fm.setFlrstat(rdstat2.getActionCommand());
				
				floorservices fs=new floorservices();
				fs.flrInsert(fm);
			}
		});
		btnflrOk.setBounds(247, 257, 89, 23);
		add(btnflrOk);

	}
}
