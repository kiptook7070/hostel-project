package myhostelproject.hostel.gui.rmGrantGui;

import myhostelproject.hostel.hstlmodels.grandmodels;
import myhostelproject.hostel.hstlservices.grandservices;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.xml.bind.Marshaller.Listener;

import org.w3c.dom.CDATASection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class hstlGrandEditgui extends JPanel {
	private JTextField tfstdnam,tfstdParnam,tfEditRmRent,tfstdStat,tfblkStat,tfEditFlrstat,tfEditRmstat,tfEditRmtotB;
	private JButton btnEditUpdate,btnEditDelete,btnEditClear;
	private JComboBox cbgrndStdId,cbEditBloknam,cbEditFloor,cbEditRmnum;
	private JRadioButton rbediGrnd2,rbediGrnd1;
	private ButtonGroup bg=new ButtonGroup();
	private int gid;
	public boolean flag=false;
	

	/**
	 * Create the panel.
	 */
	public hstlGrandEditgui() {
		setBackground(new Color(204, 204, 255));
		setLayout(null);
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setHorizontalAlignment(SwingConstants.LEFT);
		lblStudentId.setBounds(41, 70, 184, 14);
		add(lblStudentId);
		
		JLabel label_1 = new JLabel("Student Name");
		label_1.setBounds(95, 95, 93, 14);
		add(label_1);
		
		JLabel label_2 = new JLabel("Parent Name");
		label_2.setBounds(95, 122, 103, 14);
		add(label_2);
		
		JLabel lblBlockName = new JLabel("Block Name");
		lblBlockName.setBounds(35, 184, 137, 14);
		add(lblBlockName);
		
		JLabel lblFloorNumber = new JLabel("Floor Number");
		lblFloorNumber.setBounds(35, 251, 137, 14);
		add(lblFloorNumber);
		
		JLabel lblRoomNumber = new JLabel("Room Number");
		lblRoomNumber.setBounds(574, 67, 137, 14);
		add(lblRoomNumber);
		
		JLabel lblRoomRent = new JLabel("Room Rent");
		lblRoomRent.setBounds(574, 198, 130, 14);
		add(lblRoomRent);
		
		JLabel label_7 = new JLabel("Status");
		label_7.setBounds(574, 255, 93, 14);
		add(label_7);
		
			
		  grandservices gs=new grandservices();
		cbgrndStdId = new JComboBox(gs.grndstudcbId());
		cbgrndStdId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				grandmodels gm=new grandmodels();
				grandservices gs1=new grandservices();
				if(!cbgrndStdId.getSelectedItem().equals("-Select-")) //if selection != "-Select-" then
					gm.setGrndstudId(Integer.parseInt(String.valueOf(cbgrndStdId.getSelectedItem())));
				gm=gs1.getstudnames(gm);
				tfstdnam.setText(gm.getGrndstudNam());
				tfstdParnam.setText(gm.getGrndprntNam());
				tfstdStat.setText(gm.getGrndStudStat());
			}
		});
		cbgrndStdId.setBounds(235, 67, 137, 20);
		add(cbgrndStdId);
		
		tfstdnam = new JTextField();
		tfstdnam.setForeground(Color.WHITE);
		tfstdnam.setEnabled(false);
		tfstdnam.setEditable(false);
		tfstdnam.setColumns(10);
		tfstdnam.setBackground(Color.BLACK);
		tfstdnam.setBounds(235, 95, 158, 20);
		add(tfstdnam);
		
		tfstdParnam = new JTextField();
		tfstdParnam.setForeground(Color.WHITE);
		tfstdParnam.setEnabled(false);
		tfstdParnam.setEditable(false);
		tfstdParnam.setColumns(10);
		tfstdParnam.setBackground(Color.BLACK);
		tfstdParnam.setBounds(235, 119, 158, 20);
		add(tfstdParnam);
		
		cbEditBloknam = new JComboBox(gs.grndbloknam());
		cbEditBloknam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				grandservices gs1=new grandservices();
				grandmodels gm=new grandmodels();
				gm.setGrndBlkname(String.valueOf(cbEditBloknam.getSelectedItem()));
				cbEditFloor.setModel(new DefaultComboBoxModel(gs1.getfloornums(gm).toArray()));
				tfblkStat.setText(gm.getGrndBlkStat());
			}
		});
		cbEditBloknam.setBounds(235, 181, 108, 20);
		add(cbEditBloknam);
		
		cbEditFloor = new JComboBox();
		cbEditFloor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				grandservices gs=new grandservices();
				grandmodels gm=new grandmodels();
				 gm.setGrndFlornum(String.valueOf(cbEditFloor.getSelectedItem()));
				 cbEditRmnum.setModel(new DefaultComboBoxModel(gs.getroomnames(gm).toArray()));//setting new datas to room combo model
				 tfEditFlrstat.setText(gm.getGrndFlrStat());
			}
		});
		cbEditFloor.setBounds(235, 248, 108, 20);
		add(cbEditFloor);
		
		cbEditRmnum = new JComboBox( );
		cbEditRmnum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				grandservices gs=new grandservices();
				grandmodels gm=new grandmodels();
				gm.setGrndRoomnum(String.valueOf(cbEditRmnum.getSelectedItem()));
				gm=gs.getroomdetails(gm);
				tfEditRmstat.setText(gm.getGrndRmStat());
				tfEditRmtotB.setText(gm.getGrndRmTotb());
			}
		});
		cbEditRmnum.setBounds(748, 67, 108, 20);
		add(cbEditRmnum);
		
		tfEditRmRent = new JTextField();
		tfEditRmRent.setColumns(10);
		tfEditRmRent.setBounds(748, 195, 86, 20);
		add(tfEditRmRent);
		
		rbediGrnd1 = new JRadioButton("Granded");
		rbediGrnd1.setBackground(new Color(204, 204, 255));
		rbediGrnd1.setBounds(720, 251, 109, 23);
		add(rbediGrnd1);
		
		rbediGrnd2 = new JRadioButton("Not Granded");
		rbediGrnd2.setBackground(new Color(204, 204, 255));
		rbediGrnd2.setBounds(720, 293, 109, 23);
		add(rbediGrnd2);
		
		JLabel label_8 = new JLabel("Student Status");
		label_8.setBounds(95, 149, 122, 14);
		add(label_8);
		
		tfstdStat = new JTextField();
		tfstdStat.setForeground(Color.WHITE);
		tfstdStat.setEnabled(false);
		tfstdStat.setEditable(false);
		tfstdStat.setColumns(10);
		tfstdStat.setBackground(Color.BLACK);
		tfstdStat.setBounds(235, 146, 108, 20);
		add(tfstdStat);
		
		JLabel label_9 = new JLabel("Block Status");
		label_9.setBounds(95, 218, 93, 14);
		add(label_9);
		
		tfblkStat = new JTextField();
		tfblkStat.setForeground(Color.WHITE);
		tfblkStat.setEnabled(false);
		tfblkStat.setEditable(false);
		tfblkStat.setColumns(10);
		tfblkStat.setBackground(Color.BLACK);
		tfblkStat.setBounds(235, 212, 108, 20);
		add(tfblkStat);
		
		tfEditFlrstat = new JTextField();
		tfEditFlrstat.setForeground(Color.WHITE);
		tfEditFlrstat.setEnabled(false);
		tfEditFlrstat.setEditable(false);
		tfEditFlrstat.setColumns(10);
		tfEditFlrstat.setBackground(Color.BLACK);
		tfEditFlrstat.setBounds(235, 279, 108, 20);
		add(tfEditFlrstat);
		
		tfEditRmstat = new JTextField();
		tfEditRmstat.setForeground(Color.WHITE);
		tfEditRmstat.setEnabled(false);
		tfEditRmstat.setEditable(false);
		tfEditRmstat.setColumns(10);
		tfEditRmstat.setBackground(Color.BLACK);
		tfEditRmstat.setBounds(748, 98, 86, 20);
		add(tfEditRmstat);
		
		tfEditRmtotB = new JTextField();
		tfEditRmtotB.setForeground(Color.BLACK);
		tfEditRmtotB.setEnabled(false);
		tfEditRmtotB.setEditable(false);
		tfEditRmtotB.setColumns(10);
		tfEditRmtotB.setBackground(Color.BLACK);
		tfEditRmtotB.setBounds(748, 132, 86, 20);
		add(tfEditRmtotB);
		
		JLabel label_10 = new JLabel("Room Status");
		label_10.setBounds(606, 95, 117, 14);
		add(label_10);
		
		JLabel label_11 = new JLabel("Total Number of Beds");
		label_11.setBounds(606, 135, 142, 14);
		add(label_11);
		
		JLabel label_12 = new JLabel("Floor Status");
		label_12.setBounds(95, 282, 103, 14);
		add(label_12);
		
		 //button grouping 
		bg.add(rbediGrnd1);
		bg.add(rbediGrnd2);
		
		/////////////////////////////-------------------clearing all fields
		btnEditClear = new JButton("Clear");
		btnEditClear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				tfEditFlrstat.setText("");
				tfblkStat.setText("");
				tfEditRmRent.setText("");
				tfEditRmstat.setText("");
				tfEditRmtotB.setText("");
				tfstdnam.setText("");
				tfstdParnam.setText("");
				tfstdStat.setText("");
				cbEditBloknam.setSelectedIndex(0);
				cbEditFloor.setSelectedIndex(0);
				cbEditRmnum.setSelectedIndex(0);
				cbgrndStdId.setSelectedIndex(0);
				bg.clearSelection();
			}
		});
		btnEditClear.setBounds(189, 355, 89, 23);
		add(btnEditClear);
			////////////////////////===----------------------Update a Grand Record-------
		btnEditUpdate = new JButton("Update");
		btnEditUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				grandmodels gm=new grandmodels();
				grandservices gs=new grandservices();
				if( cbgrndStdId.getSelectedItem().equals("-Select-")||cbEditBloknam.getSelectedItem().equals("-Select-" )||cbEditFloor.getSelectedItem().equals("-Select-" )||cbEditRmnum.getSelectedItem().equals("-Select-")||tfEditRmRent.getText().isEmpty()||(!rbediGrnd1.isSelected()&&!rbediGrnd2.isSelected())) {
					JOptionPane.showMessageDialog(null, "Fill given Fields...!");
					return;
				}else if(tfblkStat.getText().equals("Not Available")||tfEditFlrstat.getText().equals("Not Available")||tfEditRmstat.getText().equals("Not Available")||tfstdStat.getText().equals("Not Available")) {
					JOptionPane.showMessageDialog(null, "Select Any ' Available ' one...!");
					return;
				}
				else {
				 gm.setGrndId(gid);
				 gm.setGrndstudId(Integer.parseInt(String.valueOf(cbgrndStdId.getSelectedItem())));
				 gm.setGrndBlkname(String.valueOf(cbEditBloknam.getSelectedItem()));
				 gm.setGrndFlornum(String.valueOf(cbEditFloor.getSelectedItem()));
	             gm.setGrndRoomnum(String.valueOf(cbEditRmnum.getSelectedItem())); 
				 gm.setGrndRmrent(Long.parseLong(tfEditRmRent.getText().trim())); 
				 if(rbediGrnd1.isSelected()) gm.setGrndStat(rbediGrnd1.getActionCommand());
				 else  gm.setGrndStat(rbediGrnd2.getActionCommand());
				gs.grandUpdate(gm);		
				}
			}
		});
		btnEditUpdate.setBounds(400, 355, 89, 23);
		add(btnEditUpdate);
		
		//////////////////////////////////////////---------------------------Delete a Grand Record
		btnEditDelete = new JButton("Delete");
		btnEditDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				grandservices gs=new grandservices();
				gs.grndDelt( gid );
			}
		});
		btnEditDelete.setBounds(615, 355, 89, 23);
		add(btnEditDelete);

	}
	
	public void grandEditsetValue(grandmodels gm) {
		
		gid=gm.getGrndId();
		cbgrndStdId.setSelectedItem(gm.getGrndstudId());
		cbEditBloknam.setSelectedItem(gm.getGrndBlkname());
		cbEditFloor.setSelectedItem(gm.getGrndFlornum());
		cbEditRmnum.setSelectedItem(gm.getGrndRoomnum());
		tfEditRmRent.setText(String.valueOf(gm.getGrndRmrent()));
		if(gm.getGrndStat().equals("Granded"))rbediGrnd1.setSelected(true);
		else rbediGrnd2.setSelected(true);  
	}//end of grandEditsetValue
}

