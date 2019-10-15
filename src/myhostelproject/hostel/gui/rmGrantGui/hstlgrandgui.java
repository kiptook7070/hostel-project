package myhostelproject.hostel.gui.rmGrantGui;

import myhostelproject.hostel.hstlmodels.grandmodels;
import myhostelproject.hostel.hstlservices.grandservices;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;
import javax.swing.SwingConstants;

import myhostelproject.org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class hstlgrandgui extends JPanel {
	
	private static final long serialVersionUID = 2832253598524186503L;
	private JRadioButton rbstat1,rbstat2;
	private JComboBox cbRoomNum,cbFloorNum,cbBlkNam,cbAddStudidGrand;
	private JButton btnClear,btnGrndAddsubm;
	private ComboBoxModel flrmodel,roommodel;
	private Vector<Object> vo=new Vector<Object>();
	private JTextField tfstudStat,tfblkStat,tfFlrstat,tfRmStat,tfRmBed,tfParntnam,tfStdnam,tfRrent;
	private ButtonGroup bg;
	private JButton btnRefreshview;
	private JTabbedPane tabpTableview;
	private JButton btnOkEditGrandId;
	public JTabbedPane tabpEditGrand;
	private JTextField tfGrndIdEdit;
	
	
	
	public hstlgrandgui() {
		setBackground(new Color(224, 255, 255));
		setLayout(null);
		 
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1173, 830);
		add(tabbedPane);
		
		JPanel panelViewEdit = new JPanel();
		panelViewEdit.setBackground(new Color(153, 204, 204));
		tabbedPane.addTab("View or Edit", null, panelViewEdit, null);
		panelViewEdit.setLayout(null);
		
		tabpTableview = new JTabbedPane(JTabbedPane.TOP);
		tabpTableview.setBounds(10, 11, 859, 163);
		panelViewEdit.add(tabpTableview);
		
												//////////////===========Refresh granded table view--------------------------------
		btnRefreshview = new JButton("Refresh Grand view");
		btnRefreshview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabpTableview.removeAll();
				hstlgrandTableview gv=new hstlgrandTableview();
				tabpTableview.addTab("Granded List",null,gv,null);
			}
		});
		btnRefreshview.setBounds(20, 185, 219, 23);
		panelViewEdit.add(btnRefreshview);
		
						////////////////////////--------------------------Grand Edit panel view  in Tabbed pane
		hstlGrandEditgui he=new hstlGrandEditgui();
		tabpEditGrand = new JTabbedPane(JTabbedPane.TOP);
		tabpEditGrand.setBounds(10, 231, 1091, 417);
		tabpEditGrand.setVisible(he.flag);
		panelViewEdit.add(tabpEditGrand);
		
		JLabel lblNewLabel_14 = new JLabel("Enter the Grand Id to Edit");
		lblNewLabel_14.setBounds(320, 189, 156, 14);
		panelViewEdit.add(lblNewLabel_14);
		
		tfGrndIdEdit = new JTextField();
		tfGrndIdEdit.setBounds(491, 185, 86, 20);
		panelViewEdit.add(tfGrndIdEdit);
		tfGrndIdEdit.setColumns(10);
		
						///////////////////////////-----------------Grand Edit  Ok Button
		btnOkEditGrandId = new JButton("OK");
		btnOkEditGrandId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				grandservices gs=new grandservices();
				grandmodels gm=new grandmodels();
				 gm.setGrndId(Integer.parseInt(tfGrndIdEdit.getText().trim()));
				 gm=gs.grandOk(gm);
				 if(gm.getGrndFlg()==1) {
					hstlGrandEditgui hged=new hstlGrandEditgui();
					hged.grandEditsetValue(gm);/// passing values to grand edit gui to set values.
					tabpEditGrand.removeAll();
					tabpEditGrand.add("Grand Edit",hged);
					tabpEditGrand.setVisible(true);
				 }
				 else if(gm.getGrndFlg()==0){
					 tfGrndIdEdit.setText("");
					 tabpEditGrand.setVisible(false);
				 }
			 }
		});
		btnOkEditGrandId.setBounds(597, 185, 55, 23);
		panelViewEdit.add(btnOkEditGrandId);
		
		JPanel panelAddGrand = new JPanel();
		panelAddGrand.setBackground(new Color(255, 204, 204));
		tabbedPane.addTab("New Allocation ", null, panelAddGrand, null);
		panelAddGrand.setLayout(null);
				
		JLabel lblNewLabel = new JLabel("Enter Room Grand Details For New Students");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(257, 11, 490, 47);
		panelAddGrand.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Select Student ID");
		lblNewLabel_1.setBounds(45, 133, 184, 14);
		panelAddGrand.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Student Name");
		lblNewLabel_2.setBounds(105, 158, 93, 14);
		panelAddGrand.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Parent Name");
		lblNewLabel_3.setBounds(105, 185, 103, 14);
		panelAddGrand.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Select Block to Grand");
		lblNewLabel_4.setBounds(45, 247, 137, 14);
		panelAddGrand.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Select Floor");
		lblNewLabel_5.setBounds(45, 314, 137, 14);
		panelAddGrand.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Select Room ");
		lblNewLabel_6.setBounds(584, 130, 137, 14);
		panelAddGrand.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Enter Room Rent");
		lblNewLabel_7.setBounds(584, 261, 130, 14);
		panelAddGrand.add(lblNewLabel_7);
		
		JLabel lblNewLabel_9 = new JLabel("Status");
		lblNewLabel_9.setBounds(584, 318, 93, 14);
		panelAddGrand.add(lblNewLabel_9);
		
		vo.add("-Select-");
		flrmodel=new DefaultComboBoxModel(vo);///initial setting for floor combobox value
		roommodel=new DefaultComboBoxModel(vo);///initial setting for room combobox value
		
				////////////////-----------------------student id combo----------------
		grandservices gs=new grandservices();
		cbAddStudidGrand = new JComboBox(gs.grndstudcbId());
		cbAddStudidGrand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grandmodels gm=new grandmodels();
				if(!cbAddStudidGrand.getSelectedItem().equals("-Select-")) //if selection != "-Select-" then
					gm.setGrndstudId(Integer.parseInt(String.valueOf(cbAddStudidGrand.getSelectedItem())));
				grandservices gs1=new grandservices();
				gm=gs1.getstudnames(gm);
				tfStdnam.setText(gm.getGrndstudNam());
				tfParntnam.setText(gm.getGrndprntNam());
				tfstudStat.setText(gm.getGrndStudStat());
				
			}
		});
		cbAddStudidGrand.setBounds(245, 130, 137, 20);
		panelAddGrand.add(cbAddStudidGrand);
		
		tfStdnam = new JTextField();
		tfStdnam.setForeground(new Color(255, 255, 255));
		tfStdnam.setBackground(new Color(0, 0, 0));
		tfStdnam.setEditable(false);
		tfStdnam.setEnabled(false);
		tfStdnam.setBounds(245, 158, 158, 20);
		panelAddGrand.add(tfStdnam);
		tfStdnam.setColumns(10);
		
		tfParntnam = new JTextField();
		tfParntnam.setForeground(new Color(255, 255, 255));
		tfParntnam.setBackground(new Color(0, 0, 0));
		tfParntnam.setEnabled(false);
		tfParntnam.setEditable(false);
		tfParntnam.setBounds(245, 182, 158, 20);
		panelAddGrand.add(tfParntnam);
		tfParntnam.setColumns(10);
		
		//////////////////////////////=====----combo box Block name---============================================ 
		grandservices gs1=new grandservices();
		cbBlkNam = new JComboBox(gs1.grndbloknam());
		cbBlkNam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grandservices gs=new grandservices();
				grandmodels gm=new grandmodels();
				 gm.setGrndBlkname(String.valueOf(cbBlkNam.getSelectedItem()));
				 	 
				 cbFloorNum.setModel(new DefaultComboBoxModel(gs.getfloornums(gm).toArray()));//--setting new combo model datas
				 
				 tfblkStat.setText(gm.getGrndBlkStat());
			}
		});
		cbBlkNam.setBounds(245, 244, 108, 20);
		panelAddGrand.add(cbBlkNam);
		
		/////////////////////////////////=====combo box floor number============-
		cbFloorNum = new JComboBox(flrmodel);
		cbFloorNum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//////action for displaying corresponding room number attached to floor
				grandservices gs=new grandservices();
				grandmodels gm=new grandmodels();
				 gm.setGrndFlornum(String.valueOf(cbFloorNum.getSelectedItem()));
				 cbRoomNum.setModel(new DefaultComboBoxModel(gs.getroomnames(gm).toArray()));//setting new datas to room combo model
				 tfFlrstat.setText(gm.getGrndFlrStat());
			}
		});
		cbFloorNum.setBounds(245, 311, 108, 20);
		panelAddGrand.add(cbFloorNum);
		
						///===============----------------combo box to add room number
		cbRoomNum = new JComboBox(roommodel);
		cbRoomNum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				grandservices gs=new grandservices();
				grandmodels gm=new grandmodels();
				gm.setGrndRoomnum(String.valueOf(cbRoomNum.getSelectedItem()));
				gm=gs.getroomdetails(gm);
				tfRmStat.setText(gm.getGrndRmStat());
				tfRmBed.setText(gm.getGrndRmTotb());
			}
		});
		cbRoomNum.setBounds(759, 130, 108, 20);
		panelAddGrand.add(cbRoomNum);
		
		tfRrent = new JTextField();
		tfRrent.setBounds(758, 258, 86, 20);
		panelAddGrand.add(tfRrent);
		tfRrent.setColumns(10);
		
		rbstat2 = new JRadioButton("Granded");
		rbstat2.setBackground(new Color(255, 204, 204));
		rbstat2.setBounds(730, 314, 109, 23);
		panelAddGrand.add(rbstat2);
		
		rbstat1 = new JRadioButton("Not Granded");
		rbstat1.setBackground(new Color(255, 204, 204));
		rbstat1.setBounds(730, 356, 109, 23);
		panelAddGrand.add(rbstat1);
		
		bg=new ButtonGroup();
		bg.add(rbstat1);
		bg.add(rbstat2);
		
		//////------------============-----------------Clearing Grand add fields
		btnClear=new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tfRrent.setText("");
				tfblkStat.setText("");
				tfFlrstat.setText("");
				tfParntnam.setText("");
				tfRmBed.setText("");
				tfRmStat.setText("");
				tfStdnam.setText("");
				tfstudStat.setText("");
				cbAddStudidGrand.setSelectedIndex(0);
				cbBlkNam.setSelectedIndex(0);
				cbFloorNum.setSelectedIndex(0);
				cbRoomNum.setSelectedIndex(0);
				bg.clearSelection();
			}
		});
		btnClear.setBounds(375, 443, 93, 23);
		panelAddGrand.add(btnClear);
		
		////////////////-------------------------------Button to submit Grand Details===========================
		btnGrndAddsubm = new JButton("Submit");
		btnGrndAddsubm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grandservices gs=new grandservices();
				grandmodels gm=new grandmodels();	
				if(cbAddStudidGrand.getSelectedItem().equals("-Select-")||cbBlkNam.getSelectedItem().equals("-Select-" )||cbFloorNum.getSelectedItem().equals("-Select-" )||cbRoomNum.getSelectedItem().equals("-Select-")||tfRrent.getText().isEmpty()||(!rbstat1.isSelected()&&!rbstat2.isSelected())) {
					JOptionPane.showMessageDialog(null, "Fill given Fields...!");
					return;
				}else if(tfblkStat.getText().equals("Not Available")||tfFlrstat.getText().equals("Not Available")||tfRmStat.getText().equals("Not Available")||tfstudStat.getText().equals("Not Available")) {
					JOptionPane.showMessageDialog(null, "Select Any ' Available ' one...!");
					return;
				}
				else {
					gm.setGrndstudId(Integer.parseInt(String.valueOf((cbAddStudidGrand.getSelectedItem()))));
					gm.setGrndBlkname(String.valueOf(cbBlkNam.getSelectedItem()));
					gm.setGrndFlornum(String.valueOf(cbFloorNum.getSelectedItem()));
					gm.setGrndRoomnum(String.valueOf(cbRoomNum.getSelectedItem()));
					gm.setGrndRmrent(Long.parseLong(tfRrent.getText()));
					if(rbstat1.isSelected()) gm.setGrndStat(rbstat1.getActionCommand());
					else gm.setGrndStat(rbstat2.getActionCommand());
					gs.grndSubmit(gm);
				}
			}
		});
		btnGrndAddsubm.setBounds(580, 441, 89, 27);
		panelAddGrand.add(btnGrndAddsubm);
		
		JLabel lblNewLabel_8 = new JLabel("Student Status");
		lblNewLabel_8.setBounds(105, 212, 122, 14);
		panelAddGrand.add(lblNewLabel_8);
		
		tfstudStat = new JTextField();
		tfstudStat.setEnabled(false);
		tfstudStat.setEditable(false);
		tfstudStat.setBackground(new Color(0, 0, 0));
		tfstudStat.setForeground(new Color(255, 255, 255));
		tfstudStat.setBounds(245, 209, 108, 20);
		panelAddGrand.add(tfstudStat);
		tfstudStat.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Block Status");
		lblNewLabel_10.setBounds(105, 281, 93, 14);
		panelAddGrand.add(lblNewLabel_10);
		
		tfblkStat = new JTextField();
		tfblkStat.setBackground(new Color(0, 0, 0));
		tfblkStat.setForeground(new Color(255, 255, 255));
		tfblkStat.setEditable(false);
		tfblkStat.setEnabled(false);
		tfblkStat.setBounds(245, 275, 108, 20);
		panelAddGrand.add(tfblkStat);
		tfblkStat.setColumns(10);
		
		tfFlrstat = new JTextField();
		tfFlrstat.setEnabled(false);
		tfFlrstat.setEditable(false);
		tfFlrstat.setForeground(Color.WHITE);
		tfFlrstat.setBackground(Color.BLACK);
		tfFlrstat.setBounds(245, 342, 108, 20);
		panelAddGrand.add(tfFlrstat);
		tfFlrstat.setColumns(10);
		
		tfRmStat = new JTextField();
		tfRmStat.setEnabled(false);
		tfRmStat.setEditable(false);
		tfRmStat.setBackground(Color.BLACK);
		tfRmStat.setForeground(Color.WHITE);
		tfRmStat.setBounds(758, 161, 86, 20);
		panelAddGrand.add(tfRmStat);
		tfRmStat.setColumns(10);
		
		tfRmBed = new JTextField();
		tfRmBed.setEditable(false);
		tfRmBed.setEnabled(false);
		tfRmBed.setBackground(Color.BLACK);
		tfRmBed.setForeground(Color.BLACK);
		tfRmBed.setBounds(758, 195, 86, 20);
		panelAddGrand.add(tfRmBed);
		tfRmBed.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Room Status");
		lblNewLabel_11.setBounds(616, 158, 117, 14);
		panelAddGrand.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("Total Number of Beds");
		lblNewLabel_12.setBounds(616, 198, 142, 14);
		panelAddGrand.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("Floor Status");
		lblNewLabel_13.setBounds(105, 345, 103, 14);
		panelAddGrand.add(lblNewLabel_13);
		panelAddGrand.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{cbAddStudidGrand, cbBlkNam, cbFloorNum, cbRoomNum, tfRrent, rbstat2, rbstat1, btnClear, btnGrndAddsubm}));

	}
}
