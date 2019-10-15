package myhostelproject.hostel.gui.blockGui;

import myhostelproject.hostel.hstlmodels.blockmodels;
import myhostelproject.hostel.hstlservices.blockservice;

import javax.imageio.plugins.bmp.BMPImageWriteParam;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class hstlblokEditgui extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfenterBlkid;
	private JPanel InternPanel;
	private JTextField tfBlknum;
	private JTextField tfBlkname;
	private JRadioButton blkstat2,blkstat1;
	/**
	 * Create the panel.
	 */
	public hstlblokEditgui() {
		setBackground(new Color(0, 102, 153));
		setLayout(null);
		
		JLabel lblNewLabel_11 = new JLabel("Enter the Block id");
		lblNewLabel_11.setBounds(51, 25, 134, 14);
		add(lblNewLabel_11);
		
		final JPanel InternPanel = new JPanel();
		InternPanel.setBackground(new Color(153, 204, 204));
		InternPanel.setBounds(0, 94, 490, 270);
		add(InternPanel);
		InternPanel.setLayout(null);
		InternPanel.setVisible(false);
		
		tfenterBlkid = new JTextField();/// text button for entering block id
		tfenterBlkid.setBounds(239, 22, 83, 20);
		add(tfenterBlkid);
		tfenterBlkid.setColumns(10);
		

		final JRadioButton blkstat1 = new JRadioButton("Available");		// Block Status 
		blkstat1.setBackground(new Color(153, 204, 204));
		blkstat1.setSelected(false);								
		blkstat1.setBounds(272, 120, 109, 23);	// listners availabe for radio btn Actionlistner, itemchanged, statechanged
		InternPanel.add(blkstat1);
		
		final JRadioButton blkstat2 = new JRadioButton("NotAvailable"); 
		blkstat2.setBackground(new Color(153, 204, 204));
		blkstat2.setBounds(272, 146, 109, 23);
		blkstat2.setSelected(false);
		InternPanel.add(blkstat2);

		final ButtonGroup bg=new ButtonGroup();						//button group
		bg.add(blkstat2);
		bg.add(blkstat1);
		bg.clearSelection();
		
	///////////////////////////////////////////////////////////////SUBMITTIING BLOCK ID TO EDIT BLOCKS
		JButton btnBlokeditok = new JButton("Ok");
		btnBlokeditok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				
				if(tfenterBlkid.getText().equals(""))JOptionPane.showMessageDialog(null, "Enter something in it......silkworm!");
				blockmodels bm=new blockmodels();
				bm.setBlkId(Integer.parseInt(tfenterBlkid.getText().trim()));
				
				blockservice bs=new blockservice();
				bs.checkBid(bm);//calling block service checkbid method. which return object blockmodels.
				int flg=bm.getFlag();
				if(flg==0){
					InternPanel.setVisible(false);
					tfenterBlkid.setText("");
				}
				else{					
					tfBlknum.setText(String.valueOf(bm.getBlkNum()));//displaying obtained value from blockmodels//// block number
					tfBlkname.setText(bm.getBlkNam());////block name 
					
					if(bm.getBlkStat().equals("Available")){///////ENABLING radio buttons. ie: STATUS
						blkstat1.setSelected(true);
						blkstat2.setSelected(false);
					}
					else{
						blkstat2.setSelected(true);
						blkstat1.setSelected(false);
					}
					InternPanel.setVisible(true);/////(under else part)if true block id then set internal panel visible
				}
			}	
		});
		btnBlokeditok.setBounds(364, 21, 75, 23);
		add(btnBlokeditok);
		
		JButton btnNewButton = new JButton("Clear");///////////clear bentered blockid in blockedit panel
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tfenterBlkid.getText().equals(""))JOptionPane.showMessageDialog(null, "What is in it, nothing to clear....!");
				else tfenterBlkid.setText("");
			}
		});
		btnNewButton.setBounds(364, 55, 75, 23);
		add(btnNewButton);
	
		JLabel lblNewLabel_5 = new JLabel("Enter the Block Number");
		lblNewLabel_5.setBounds(86, 46, 165, 14);
		InternPanel.add(lblNewLabel_5);
		
		tfBlknum = new JTextField();
		tfBlknum.setBounds(272, 43, 58, 20);
		InternPanel.add(tfBlknum);
		tfBlknum.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Enter the Block Name");
		lblNewLabel_6.setBounds(86, 71, 157, 14);
		InternPanel.add(lblNewLabel_6);
		
		tfBlkname = new JTextField();
		tfBlkname.setBounds(272, 74, 157, 20);
		InternPanel.add(tfBlkname);
		tfBlkname.setColumns(10);
		
		
		JLabel lblNewLabel = new JLabel("Block Status");
		lblNewLabel.setBounds(86, 120, 144, 14);
		InternPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Edit or Update Record");
		lblNewLabel_1.setBounds(175, 11, 267, 14);
		InternPanel.add(lblNewLabel_1);
/////////////////////////////////////////////////////////////////////////////////////button for Updating block records
		
		JButton btnintUpdate = new JButton("Update");
		btnintUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				blockmodels bm=new blockmodels();// filling block model with data.
				bm.setBlkId(Integer.parseInt(tfenterBlkid.getText().trim()));
				bm.setBlkNum(Integer.parseInt(tfBlknum.getText().trim()));
				bm.setBlkNam(tfBlkname.getText().trim());
				if(blkstat1.isSelected())bm.setBlkStat(blkstat1.getActionCommand().trim());// determining which radiobutton is selected.
				else bm.setBlkStat(blkstat2.getActionCommand().trim());
				
				blockservice bs=new blockservice();
				bs.blkupdate(bm);
				
			}
		});
		btnintUpdate.setBounds(204, 208, 89, 23);
		InternPanel.add(btnintUpdate);
		
///
		JButton btnintDelete = new JButton("Delete");
		btnintDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				blockmodels bm=new blockmodels();
				bm.setBlkId(Integer.parseInt(tfenterBlkid.getText().trim()));
				new blockservice().blkdel(bm);
			}
		});
		btnintDelete.setBounds(328, 208, 89, 23);
		InternPanel.add(btnintDelete);
		
	

		JButton btnintClear = new JButton("Clear");
		btnintClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tfBlkname.setText("");
				tfBlknum.setText("");
				bg.clearSelection();
			}
		});
		btnintClear.setBounds(75, 208, 89, 23);
		InternPanel.add(btnintClear);
		
		}
}
