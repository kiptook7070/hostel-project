package myhostelproject.hostel.gui.floorGUI;

import myhostelproject.hostel.hstlmodels.floormodels;
import myhostelproject.hostel.hstlservices.floorservices;

import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.sql.SQLException;

public class hstlflrEditgui extends JPanel {
	private final JSeparator separator = new JSeparator();
	private JTextField tfenterFlridedit;
	private JTextField tfblkinnName;
	private JTextField tfinnFlrnumed;
	private JTextField tfinnflorNamed;

	/**
	 * Create the panel.
	 */
	public hstlflrEditgui() {
		setBackground(new Color(0, 153, 102));
		setLayout(null);
		separator.setBackground(new Color(204, 0, 0));
		separator.setBounds(0, 79, 533, 20);
		add(separator);
		
		JLabel lblNewLabel_4 = new JLabel("Enter Floor ID");
		lblNewLabel_4.setBounds(54, 36, 123, 14);
		add(lblNewLabel_4);
		
		tfenterFlridedit = new JTextField();
		tfenterFlridedit.setBounds(168, 33, 92, 20);
		add(tfenterFlridedit);
		tfenterFlridedit.setColumns(10);
		
		final JPanel innerPanelEditFlr = new JPanel();
		innerPanelEditFlr.setBackground(new Color(0, 153, 102));
		innerPanelEditFlr.setBounds(0, 79, 533, 274);
		add(innerPanelEditFlr);
		innerPanelEditFlr.setLayout(null);
		innerPanelEditFlr.setVisible(false);
		
		JLabel label = new JLabel("Block Name");
		label.setBounds(48, 36, 107, 14);
		innerPanelEditFlr.add(label);
		
		tfblkinnName = new JTextField();
		tfblkinnName.setFont(new Font("Courier New", Font.BOLD, 13));
		tfblkinnName.setBackground(Color.BLACK);
		tfblkinnName.setForeground(Color.WHITE);
		tfblkinnName.setEnabled(false);
		tfblkinnName.setColumns(10);
		tfblkinnName.setBounds(165, 33, 99, 20);
		innerPanelEditFlr.add(tfblkinnName);
		
		floorservices fs=new floorservices(); 
		final JComboBox comboBox = new JComboBox(fs.comboview());///////////combo box
		comboBox.setForeground(Color.BLACK);
		comboBox.setBackground(Color.WHITE);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tfblkinnName.setText(String.valueOf(comboBox.getSelectedItem()));
				System.out.println(comboBox.getName());
			}
		});
		comboBox.setBounds(333, 33, 116, 20);
		innerPanelEditFlr.add(comboBox);
		
		JLabel label_1 = new JLabel("Floor Number");
		label_1.setBounds(48, 73, 107, 14);
		innerPanelEditFlr.add(label_1);
		
		tfinnFlrnumed = new JTextField();
		tfinnFlrnumed.setColumns(10);
		tfinnFlrnumed.setBounds(165, 70, 56, 20);
		innerPanelEditFlr.add(tfinnFlrnumed);
		
		JLabel label_2 = new JLabel("Floor Name");
		label_2.setBounds(48, 108, 107, 14);
		innerPanelEditFlr.add(label_2);
		
		tfinnflorNamed = new JTextField();
		tfinnflorNamed.setColumns(10);
		tfinnflorNamed.setBounds(165, 105, 99, 20);
		innerPanelEditFlr.add(tfinnflorNamed);
		
		JLabel label_3 = new JLabel("Floor Status");
		label_3.setBounds(48, 145, 107, 14);
		innerPanelEditFlr.add(label_3);
		
		final JRadioButton rbEditflrstat1 = new JRadioButton("Available");
		rbEditflrstat1.setBackground(new Color(0, 153, 102));
		rbEditflrstat1.setBounds(165, 141, 116, 23);
		innerPanelEditFlr.add(rbEditflrstat1);
		
		final JRadioButton rbEditflrstat2 = new JRadioButton("Not Available");
		rbEditflrstat2.setBackground(new Color(0, 153, 102));
		rbEditflrstat2.setBounds(363, 141, 116, 23);
		innerPanelEditFlr.add(rbEditflrstat2);
		
		final ButtonGroup bg=new ButtonGroup();
		bg.add(rbEditflrstat1);
		bg.add(rbEditflrstat2);
		bg.clearSelection();
		
		JButton btnFlrInnerClr = new JButton("Clear");
		btnFlrInnerClr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			tfblkinnName.setText("");
			tfinnflorNamed.setText("");
			tfinnFlrnumed.setText("");
			bg.clearSelection();
			}
		});
		btnFlrInnerClr.setBounds(50, 202, 86, 23);
		innerPanelEditFlr.add(btnFlrInnerClr);
		
		///////////////////////////////////////////////BUTTON FOR UPDATE A RECORD/////////////////////////
		JButton btnEditUpdate = new JButton("Update");
		btnEditUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					floormodels fm=new floormodels();
				 fm.setFlrid(Integer.parseInt(tfenterFlridedit.getText().trim())); 
				 if(tfblkinnName.getText().equals("-Select-"))JOptionPane.showMessageDialog(null, "Select a Block Name");
				 else fm.setBlknam(tfblkinnName.getText().trim());
				 fm.setFlrnum(Integer.parseInt(tfinnFlrnumed.getText().trim()));
				 fm.setFlrnam(tfinnflorNamed.getText().trim());
				 if(rbEditflrstat1.isSelected()) fm.setFlrstat(rbEditflrstat1.getActionCommand().trim());
			    	else fm.setFlrstat(rbEditflrstat2.getActionCommand().trim());
				 floorservices fs=new floorservices();
				 fs.flrupdate(fm);///calling Update function in floor service
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, "Error in input..!");
				}
				
			}
		});
		btnEditUpdate.setBounds(219, 202, 89, 23);
		innerPanelEditFlr.add(btnEditUpdate);
		
		/////////////////////////////////////////////////BUTTON FOR DELETE A FLOOR RECORD//////////////////
		JButton btnFlrDelete = new JButton("Delete");
		btnFlrDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				floormodels fm=new floormodels();
				 fm.setFlrid(Integer.parseInt(tfenterFlridedit.getText().trim()));
			    floorservices fs=new floorservices();
				 fs.flrdelete(fm);///calling delete function in floor service
			}
		});
		btnFlrDelete.setBounds(373, 202, 89, 23);
		innerPanelEditFlr.add(btnFlrDelete);
		
		//////////////////////////////////////////////////////button OK for submitting FLOOR ID/////////////////////
		JButton btnEditEntrflrId = new JButton("OK");
		btnEditEntrflrId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					floormodels fm=new floormodels();
					fm.setFlrid(Integer.parseInt(tfenterFlridedit.getText()));
				
					floorservices fs=new floorservices();
					fm=fs.editidOk(fm);
				
					if(fm.getFlag()==1){
						innerPanelEditFlr.setVisible(true);
						tfblkinnName.setText(fm.getBlknam());
						tfinnFlrnumed.setText(String.valueOf(fm.getFlrnum()));
						tfinnflorNamed.setText(fm.getFlrnam());
						String stat=fm.getFlrstat();
						if(stat.equals("Available"))rbEditflrstat1.setSelected(true);
						else rbEditflrstat2.setSelected(true);
					}
					else{
						JOptionPane.showMessageDialog(null, "Entered Id Not Found...!");
						tfenterFlridedit.setText(null);
						innerPanelEditFlr.setVisible(false);
					}
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Enter Only Numbers");
					tfenterFlridedit.setText(null);
				}
			}
		});
		btnEditEntrflrId.setBounds(320, 32, 77, 23);
		add(btnEditEntrflrId);
		
		JButton btnflridclr = new JButton("Clear");
		btnflridclr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfenterFlridedit.setText("");
			}
		});
		btnflridclr.setBounds(422, 32, 68, 23);
		add(btnflridclr);

	}
}
