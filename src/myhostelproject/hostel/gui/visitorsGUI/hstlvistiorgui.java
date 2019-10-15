package myhostelproject.hostel.gui.visitorsGUI;

import myhostelproject.hostel.hstlmodels.visitormodels;
import myhostelproject.hostel.hstlservices.visitorservices;

import javax.swing.JPanel;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerDateModel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class hstlvistiorgui extends JPanel {
	private JTextField tfvNam;
	private JTextField tfVph;
	private JTextArea textArea;
	private JComboBox cbVstStudd, cbVstRel;
	private JSpinner spinnTm1,spinnTm2; 
	private JPanel vistEdit;
	private JTabbedPane tabpaVisEdit;
	private JComboBox cbEditVistStudNam;
	private JDateChooser dateChooserEditVst;
	private JSpinner spinnEditVstLevTm;
	private JComboBox cbEditRelat;
	private JTextField tfEditVsitSerlNo;
	private JTextField tfEditVstNam;
	private JTextField tfEditVstPh;
	private JTextArea textAreaEdit;
	private JPanel panelVisitEdit;
	private JButton refresh;
	
	
	public hstlvistiorgui() {
		setBackground(new Color(255, 153, 102));
		setLayout(null);
		
		JTabbedPane viewtabpan1 = new JTabbedPane(JTabbedPane.TOP);
		viewtabpan1.setBackground(new Color(255, 160, 122));
		viewtabpan1.setBounds(0, 0, 1155, 696);
		add(viewtabpan1);
		
		final JPanel vstSearch = new JPanel();
		viewtabpan1.addTab("Visitor Search", null, vstSearch, null);
		vstSearch.setLayout(null);
		
		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);///---search result
		tabbedPane.setBounds(0, 22, 919, 646);
		
		vstSearch.add(tabbedPane);
		
		refresh = new JButton("Refresh Table View");
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final visitTableView vt=new visitTableView();
				tabbedPane.removeAll();
				tabbedPane.addTab("Visitor Table",null, vt,null);
			}
		});
		refresh.setBounds(382, 0, 238, 23);
		vstSearch.add(refresh);
		
		JPanel vstadd = new JPanel();
		viewtabpan1.addTab("Visitor Add", null, vstadd, null);
		vstadd.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter Visitor Name");
		lblNewLabel.setBounds(23, 121, 182, 14);
		vstadd.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Visitor Phone Number");
		lblNewLabel_1.setBounds(23, 187, 221, 14);
		vstadd.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Select Student To Visit");
		lblNewLabel_2.setBounds(23, 247, 166, 14);
		vstadd.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Relation to Student");
		lblNewLabel_3.setBounds(23, 321, 137, 14);
		vstadd.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Visiting  Date");
		lblNewLabel_4.setBounds(23, 389, 166, 25);
		vstadd.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Entry time");
		lblNewLabel_5.setBounds(611, 124, 112, 14);
		vstadd.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Leaving Time");
		lblNewLabel_6.setBounds(611, 190, 112, 14);
		vstadd.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Visiting Purpose");
		lblNewLabel_7.setBounds(611, 254, 119, 14);
		vstadd.add(lblNewLabel_7);
		
		tfvNam = new JTextField();
		tfvNam.setBounds(286, 118, 149, 20);
		vstadd.add(tfvNam);
		tfvNam.setColumns(10);
		
		tfVph = new JTextField();
		tfVph.setBounds(286, 184, 86, 20);
		vstadd.add(tfVph);
		tfVph.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(750, 249, 154, 107);
		vstadd.add(scrollPane);
		visitorservices vs=new visitorservices();
		 textArea = new JTextArea();
		 scrollPane.setViewportView(textArea);
		 textArea.setColumns(3);
		 textArea.setBackground(new Color(204, 255, 204));
		 
			//===combo box for showing student list
		 cbVstStudd = new JComboBox(vs.vistComb());
		 cbVstStudd.setBounds(286, 244, 149, 20);
		 vstadd.add(cbVstStudd);
		 
		 final JDateChooser dateChooser = new JDateChooser();
		 dateChooser.setDateFormatString("dd-MM-yyyy");
		 dateChooser.setBounds(286, 403, 125, 20);
		 vstadd.add(dateChooser);
		 
		 	
		     spinnTm1 = new JSpinner(new SpinnerDateModel());///======-------entry time
		     spinnTm1.setEnabled(false);
		     JSpinner.DateEditor timeedi1=new JSpinner.DateEditor(spinnTm1,"hh:mm a");	
		     spinnTm1.setEditor(timeedi1);
		     spinnTm1.setValue(new Date());
		     spinnTm1.setBounds(749, 121, 97, 20);
		     vstadd.add(spinnTm1);
		     
		     spinnTm2 = new JSpinner(new SpinnerDateModel(new Date(1375641000232L), null, null, Calendar.DAY_OF_MONTH));////leaving Time
		     JSpinner.DateEditor timed2=new JSpinner.DateEditor(spinnTm2,"hh:mm a");
		     spinnTm2.setEditor(timed2);
		     //spinnTm2.setValue("");
		     spinnTm2.setBounds(750, 178, 96, 20);
		     vstadd.add(spinnTm2);
		     
		     cbVstRel = new JComboBox();//////////combo box relation
		     cbVstRel.setModel(new DefaultComboBoxModel(new String[] {"-Select-", "Father", "Mother", "Brother", "Sister", "Friend", "Relative"}));
		     cbVstRel.setBounds(286, 318, 97, 20);
		     vstadd.add(cbVstRel);
		     
		
		     JButton btnAddVistclr = new JButton("Clear");
		     btnAddVistclr.addActionListener(new ActionListener() {
		     	public void actionPerformed(ActionEvent arg0) {
		     		tfvNam.setText("");
		     		tfVph.setText("");
		     	    textArea.setText("");
		     	    dateChooser.setCalendar(null);
		     	    cbVstRel.setSelectedIndex(0);  
		     	    cbVstStudd.setSelectedIndex(0);
		     	 
		     	}
		     });
		     btnAddVistclr.setBounds(370, 478, 89, 23);
		     vstadd.add(btnAddVistclr);
		     
//////	
		     JButton btnVstaddSub = new JButton("Submit");
		     btnVstaddSub.addActionListener(new ActionListener() {
		     	public void actionPerformed(ActionEvent arg0) {
		     		String time1;
		     		visitormodels vm=new visitormodels();
		     		vm.setVstnam(tfvNam.getText());
		     		try {
		     		 vm.setVstph(Long.parseLong(tfVph.getText()));
		     		}
		     		catch(Exception e) { 
		     			JOptionPane.showMessageDialog(null, "Error due to phone number field entry  or Not Filled Any Details"); 
		     			tfVph.setText("");
		     			return;
		     		}
		     		 if(cbVstStudd.getSelectedItem().equals("-Select-"))JOptionPane.showMessageDialog(null, "Select student name.");
		     		  else vm.setVstudnam(String.valueOf(cbVstStudd.getSelectedItem()));
		     		 if(cbVstRel.getSelectedItem().equals("-Select-"))JOptionPane.showMessageDialog(null, "Select Relationship.");
		     		  else vm.setVstrelat(String.valueOf(cbVstRel.getSelectedItem()));
		     		 //--getting date
		     		  Date dt=dateChooser.getDate();
		     		  String sd=String.format("%1$td-%1$tm-%1$tY",dt); 
		     		  vm.setVstdate(sd.trim());
		     		 
		     		//----convert to date format				  
		     		SimpleDateFormat fmt=new SimpleDateFormat("hh:mm a");
		     		Date tm=new Date();
		     		tm=(Date)spinnTm1.getValue();
		     		 time1=fmt.format(tm);
		     		 vm.setVstEtme(time1);				

		     		Date tm1=new Date();
		     		tm1=(Date)spinnTm2.getValue();
		     		time1=fmt.format(tm1);
		     		
		     	    vm.setVstLtme(time1);			
		     	    
		     		 vm.setVstpurp(textArea.getText());
		     		visitorservices vs=new visitorservices();
		     		vs.vistIns(vm);
		     	
		     		 
		       }
		     });
		     btnVstaddSub.setBounds(603, 478, 89, 23);
		     vstadd.add(btnVstaddSub);
		     
		     JLabel lblNewLabel_17 = new JLabel("Add New Vistor Here");
		     lblNewLabel_17.setFont(new Font("Tahoma", Font.BOLD, 15));
		     lblNewLabel_17.setBounds(182, 29, 334, 29);
		     vstadd.add(lblNewLabel_17);
		
		vistEdit = new JPanel();
		viewtabpan1.addTab("Visitor Edit", null, vistEdit, null);
		vistEdit.setLayout(null);
		
		tabpaVisEdit = new JTabbedPane(JTabbedPane.TOP);
		tabpaVisEdit.setBounds(10, 11, 918, 140);
		vistEdit.add(tabpaVisEdit);
				
		JButton btnEditVistrView = new JButton("Refresh Visitor View");
		btnEditVistrView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			  tabpaVisEdit.removeAll();
			 tabpaVisEdit.addTab("Visitor Details", null,new vistEditTableView(),null);
			}
		});
		btnEditVistrView.setBounds(10, 162, 221, 23);
		vistEdit.add(btnEditVistrView);
		
		visitorservices vss=new visitorservices();
		
		tfEditVsitSerlNo = new JTextField();
		tfEditVsitSerlNo.setBounds(510, 162, 97, 20);
		vistEdit.add(tfEditVsitSerlNo);
		tfEditVsitSerlNo.setColumns(10);
		
		JLabel lblNewLabel_16 = new JLabel("Enter Visitor Serial Number to Edit");
		lblNewLabel_16.setBounds(295, 166, 196, 14);
		vistEdit.add(lblNewLabel_16);
		
		
		JButton btnEditSerlOk = new JButton("OK");
		btnEditSerlOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if((tfEditVsitSerlNo.getText().isEmpty()))JOptionPane.showMessageDialog(null,"Enter some value");
				else {
						visitormodels vm=new visitormodels();
						vm.setVstslno(Integer.parseInt(tfEditVsitSerlNo.getText()));
						visitorservices vs=new visitorservices();
						vm=vs.vistOk(vm);
						if(vm.getVstflag()==1) {/// checking if required data returned
							tfEditVstNam.setText(vm.getVstnam());
							tfEditVstPh.setText(String.valueOf(vm.getVstph()));
							
							Date dov = null;
							try {
							DateFormat df=new SimpleDateFormat("dd-MM-yyyy");
							dov=df.parse(vm.getVstdate());				///===getting date to date chooser
							}
							catch(Exception du){
								System.out.println("Date cannot retrive error.... "+du);
							}
							dateChooserEditVst.setDate(dov);// set date to date chooser
							cbEditVistStudNam.setSelectedItem(vm.getVstudnam());
							cbEditRelat.setSelectedItem(vm.getVstrelat());
							textAreaEdit.setText(vm.getVstpurp());
							String stm=vm.getVstLtme();//getting spinner time value.
							Date tm = null;
							try {
								tm=new SimpleDateFormat("hh:mm a").parse(stm);//converting to spinner time value.
							}
							catch(Exception e ) {
							System.out.println("cannot parse time.....");
							}
							spinnEditVstLevTm.setValue(tm);///set spinner time value
							panelVisitEdit.setVisible(true);
						}
						else {
							panelVisitEdit.setVisible(false);
							tfEditVsitSerlNo.setText("");
							JOptionPane.showMessageDialog(null, "Serial Number is not Valid.");
						}
						
				}
			}
		});
		btnEditSerlOk.setBounds(646, 162, 62, 23);
		vistEdit.add(btnEditSerlOk);
		
		panelVisitEdit = new JPanel();
		panelVisitEdit.setBackground(new Color(204, 204, 153));
		panelVisitEdit.setBounds(20, 220, 940, 400);
		vistEdit.add(panelVisitEdit);
		panelVisitEdit.setVisible(false);
		panelVisitEdit.setLayout(null);
		
		JLabel lblNewLabel_9 = new JLabel("Phone Number of Visitor");
		lblNewLabel_9.setBounds(26, 126, 146, 14);
		panelVisitEdit.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Student to Visit");
		lblNewLabel_10.setBounds(26, 180, 146, 14);
		panelVisitEdit.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Relation to Student");
		lblNewLabel_11.setBounds(26, 234, 146, 14);
		panelVisitEdit.add(lblNewLabel_11);
		
		tfEditVstPh = new JTextField();
		tfEditVstPh.setBounds(279, 123, 122, 20);
		panelVisitEdit.add(tfEditVstPh);
		tfEditVstPh.setColumns(10);
		cbEditVistStudNam = new JComboBox(vss.vistComb());
		cbEditVistStudNam.setBounds(279, 177, 122, 20);
		panelVisitEdit.add(cbEditVistStudNam);
		
		cbEditRelat = new JComboBox();
		cbEditRelat.setBounds(279, 231, 122, 20);
		panelVisitEdit.add(cbEditRelat);
		cbEditRelat.setModel(new DefaultComboBoxModel(new String[] {"-Select-", "Father", "Mother", "Brother", "Sister", "Friend", "Relative"}));
		
		JLabel lblNewLabel_15 = new JLabel("Edit Visitor Details Here");
		lblNewLabel_15.setBounds(26, 11, 280, 14);
		panelVisitEdit.add(lblNewLabel_15);
		lblNewLabel_15.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_8 = new JLabel("Name of Visitor");
		lblNewLabel_8.setBounds(26, 77, 146, 14);
		panelVisitEdit.add(lblNewLabel_8);
		
		tfEditVstNam = new JTextField();
		tfEditVstNam.setBounds(280, 74, 171, 20);
		panelVisitEdit.add(tfEditVstNam);
		tfEditVstNam.setColumns(10);
		
///////////---------------------------------------------------------Edit visitor Clear-----------------		
		JButton btnEditVstClr = new JButton("Clear");
		btnEditVstClr.setBounds(182, 319, 89, 23);
		panelVisitEdit.add(btnEditVstClr);
		btnEditVstClr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cbEditRelat.setSelectedIndex(0);
				cbEditVistStudNam.setSelectedIndex(0);
				tfEditVstNam.setText("");
				tfEditVstPh.setText("");
				tfEditVsitSerlNo.setText("");
				dateChooserEditVst.setCalendar(null);
				textAreaEdit.setText("");
			}
		});
		
		////////////===========================Delete a visitor record============================================
		JButton btnEditVstDel = new JButton("Delete");
		btnEditVstDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visitormodels vm=new visitormodels();
				vm.setVstslno(Integer.parseInt(tfEditVsitSerlNo.getText()));
				visitorservices vs=new visitorservices(); 
				vs.vistDel(vm);
				panelVisitEdit.setVisible(false);
				tfEditVsitSerlNo.setText("");
			}
		});
		btnEditVstDel.setBounds(402, 319, 89, 23);
		panelVisitEdit.add(btnEditVstDel);
		
		//////////////========================Edit Visitor Update=======-------------------- 
		JButton btnEditVstUpdate = new JButton("Update");
		btnEditVstUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 visitormodels vm=new visitormodels();
				  vm.setVstslno(Integer.parseInt(tfEditVsitSerlNo.getText()));
				  vm.setVstnam(tfEditVstNam.getText());
				  vm.setVstph(Long.parseLong(tfEditVstPh.getText()));
				  vm.setVstpurp(textAreaEdit.getText());
				  vm.setVstudnam(String.valueOf(cbEditVistStudNam.getSelectedItem()));
				  vm.setVstrelat(String.valueOf(cbEditRelat.getSelectedItem()));
				  Date dd=dateChooserEditVst.getDate();///converting datechooser value to string
				  String sd=String.format("%1$td-%1$tm-%1$tY",dd);
				  sd=sd.trim();
				  vm.setVstdate(sd);
				  
				  SimpleDateFormat st=new SimpleDateFormat("hh:mm a");///converting spinner time value to string
				  dd=(Date)spinnEditVstLevTm.getValue();
				  String tm=st.format(dd);
				  tm=tm.trim();
				  vm.setVstLtme(tm);
				  
				 visitorservices vs=new visitorservices();
				 vs.vistUpdate(vm);
				  
			}
		});
		btnEditVstUpdate.setBounds(627, 319, 89, 23);
		panelVisitEdit.add(btnEditVstUpdate);
		
		JLabel lblNewLabel_12 = new JLabel("Visiting Date");
		lblNewLabel_12.setBounds(583, 77, 122, 14);
		panelVisitEdit.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("Leaving Time");
		lblNewLabel_13.setBounds(583, 114, 133, 14);
		panelVisitEdit.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("Visiting Purpose");
		lblNewLabel_14.setBounds(583, 180, 133, 14);
		panelVisitEdit.add(lblNewLabel_14);
		
		textAreaEdit = new JTextArea();
		textAreaEdit.setBounds(726, 175, 122, 97);
		panelVisitEdit.add(textAreaEdit);
		textAreaEdit.setBackground(new Color(204, 255, 204));
		
						//////======---Setting visitor leave edit spinner as time model
		spinnEditVstLevTm = new JSpinner(new SpinnerDateModel());
		spinnEditVstLevTm.setBounds(726, 111, 112, 20);
		panelVisitEdit.add(spinnEditVstLevTm);
		JSpinner.DateEditor timeed=new JSpinner.DateEditor(spinnEditVstLevTm,"hh:mm a");
		spinnEditVstLevTm.setEditor(timeed);
		
		dateChooserEditVst = new JDateChooser();
		dateChooserEditVst.setBounds(726, 71, 112, 20);
		panelVisitEdit.add(dateChooserEditVst);
		dateChooserEditVst.setDateFormatString("dd-MM-yyyy");
		}
}
