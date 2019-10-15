package myhostelproject.hostel.gui.ablcGUI;

import myhostelproject.hostel.hstlmodels.ablcmodels;
import myhostelproject.hostel.hstlservices.ablcservices;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class hstlablcgui extends JPanel {
	private JTextField tfOtherExp,tfDefMessfee,tfStdnm,tfStudblk,tfstdRm,tfstdFlr,tfRoomRent,tfTotfee;
	private JComboBox cbStdIds;
	private JYearChooser yearChooser;
	private JMonthChooser monthChooser;
	private JTabbedPane tabpView;
	private JCheckBox chbOthexp,chckbEditmessfee;
	 
	 
	
	public hstlablcgui() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1366, 800);
		add(tabbedPane);
		
		JPanel Viewpanel = new JPanel();
		tabbedPane.addTab("View or Search", null, Viewpanel, null);
		Viewpanel.setLayout(null);
		
		
		tabpView = new JTabbedPane(JTabbedPane.TOP);
		tabpView.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
				tabpView.removeAll();
				ablcView abv=new ablcView();
				tabpView.addTab("Views",abv);
				tabpView.setSelectedComponent(abv);
				tabpView.setBounds(0, 11, 1201, 915);
			}
			public void ancestorMoved(AncestorEvent arg0) {
			}
			public void ancestorRemoved(AncestorEvent arg0) {
			}
		});
		try {
			ablcView abv=new ablcView();
			tabpView.addTab("Views",abv);
			tabpView.setSelectedComponent(abv);
			tabpView.setBounds(0, 11, 1201, 915);
			Viewpanel.add(tabpView);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		JPanel AddRent = new JPanel();
		AddRent.setForeground(Color.RED);
		tabbedPane.addTab("Hostel Rent ", null, AddRent, null);
		AddRent.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select Student ID");
		lblNewLabel.setBounds(49, 170, 209, 14);
		AddRent.add(lblNewLabel);
		
				////////-----------------------------------Student Id combo box Model
		ablcservices as=new ablcservices();
		cbStdIds = new JComboBox();
		cbStdIds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ablcservices as=new ablcservices();
				ablcmodels am=new ablcmodels();
				if(!cbStdIds.getSelectedItem().equals("-Select-")) {
				am.setAblcstdId(Integer.parseInt(String.valueOf(cbStdIds.getSelectedItem())));
				am=as.ablcStdinfo(am);
				tfStdnm.setText(am.getAblcStudNam());
				tfStudblk.setText(am.getAblcBlknam());
				tfstdFlr.setText(am.getAblcFlornum());
				tfstdRm.setText(am.getAblcRmnum());
				tfRoomRent.setText(String.valueOf(am.getAblcrmRent()));
				}
				else {
					tfRoomRent.setText("");
					tfstdFlr.setText("");
					tfstdRm.setText("");
					tfStdnm.setText("");
					tfTotfee.setText("Rs.0");
					tfStudblk.setText("");
				}
			}
		});
		cbStdIds.setModel(new DefaultComboBoxModel(as.getStUdid().toArray()));
		cbStdIds.setBounds(310, 167, 91, 20);
		AddRent.add(cbStdIds);
		
		tfOtherExp = new JTextField("0",5);
		tfOtherExp.setHorizontalAlignment(JTextField.RIGHT);
		tfOtherExp.setBackground(Color.BLACK);
		tfOtherExp.setForeground(Color.WHITE);
		tfOtherExp.setEnabled(false);
		tfOtherExp.setEditable(false);
	 	tfOtherExp.setBounds(888, 218, 86, 20);
		AddRent.add(tfOtherExp);
		tfOtherExp.setColumns(10);
		
		tfDefMessfee = new JTextField("1000",5);
		tfDefMessfee.setHorizontalAlignment(JTextField.RIGHT);///----to display numbers in right side of Jtextfield
		tfDefMessfee.setForeground(Color.WHITE);
		tfDefMessfee.setBackground(Color.BLACK);
		tfDefMessfee.setEditable(false);
		tfDefMessfee.setEnabled(false);
		tfDefMessfee.setBounds(310, 83, 86, 20);
		AddRent.add(tfDefMessfee);
		tfDefMessfee.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Other Expence of This Student");
		lblNewLabel_1.setBounds(672, 224, 188, 14);
		AddRent.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Current Mess fee");
		lblNewLabel_3.setBounds(49, 86, 112, 14);
		AddRent.add(lblNewLabel_3);
													
		chckbEditmessfee = new JCheckBox("Edit Current Mess Fee");
		chckbEditmessfee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String val=tfDefMessfee.getText();
				int x=JOptionPane.showConfirmDialog(null, "You are Going \n To Change \n Current Mess Fee", "Confirm...!!!", JOptionPane.WARNING_MESSAGE); 
				if(x==0) {
					String s= JOptionPane.showInputDialog(null,"Enter New Mess Fee","New Mess Fee");
					if(Integer.parseInt(s)>0)tfDefMessfee.setText(s);
					else{
					JOptionPane.showMessageDialog (null, "No Change in Mess Fee", "Information", JOptionPane.ERROR_MESSAGE);
					tfDefMessfee.setText(val);
					}
				}
				chckbEditmessfee.setSelected(false);
			}
		});
		chckbEditmessfee.setBounds(305, 118, 218, 23);
		AddRent.add(chckbEditmessfee);
		
		JLabel lblNewLabel_2 = new JLabel("Student Name");
		lblNewLabel_2.setBounds(130, 224, 125, 14);
		AddRent.add(lblNewLabel_2);
		
		tfStdnm = new JTextField();
		tfStdnm.setBackground(Color.BLACK);
		tfStdnm.setForeground(Color.WHITE);
		tfStdnm.setEnabled(false);
		tfStdnm.setEditable(false);
		tfStdnm.setBounds(309, 221, 159, 20);
		AddRent.add(tfStdnm);
		tfStdnm.setColumns(10);
																
		chbOthexp = new JCheckBox("Enter If Any Other Expences");
		chbOthexp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String val=JOptionPane.showInputDialog(null,"Enter Other Expences of Current\n Selected Student","");
				chbOthexp.setSelected(false); 
				if(Integer.parseInt(val)>0)tfOtherExp.setText(val);
				else {
					JOptionPane.showMessageDialog(null, "Cannot Change value \n Because value is Negative or 0","Error In Input",JOptionPane.WARNING_MESSAGE);
					tfOtherExp.setText("0");
				}
			}
		});
		chbOthexp.setBounds(888, 270, 218, 23);
		AddRent.add(chbOthexp);
		
		JLabel lblNewLabel_4 = new JLabel("Block Name");
		lblNewLabel_4.setBounds(129, 274, 100, 14);
		AddRent.add(lblNewLabel_4);
		
		tfStudblk = new JTextField();
		tfStudblk.setForeground(Color.WHITE);
		tfStudblk.setBackground(Color.BLACK);
		tfStudblk.setEditable(false);
		tfStudblk.setEnabled(false);
		tfStudblk.setBounds(308, 271, 112, 20);
		AddRent.add(tfStudblk);
		tfStudblk.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Floor Number");
		lblNewLabel_5.setBounds(130, 321, 86, 14);
		AddRent.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Room Number");
		lblNewLabel_6.setBounds(130, 364, 86, 14);
		AddRent.add(lblNewLabel_6);
		
		tfstdRm = new JTextField();
		tfstdRm.setEnabled(false);
		tfstdRm.setEditable(false);
		tfstdRm.setBackground(Color.BLACK);
		tfstdRm.setForeground(Color.WHITE);
		tfstdRm.setBounds(309, 361, 86, 20);
		AddRent.add(tfstdRm);
		tfstdRm.setColumns(10);
		
		tfstdFlr = new JTextField();
		tfstdFlr.setEnabled(false);
		tfstdFlr.setEditable(false);
		tfstdFlr.setForeground(Color.WHITE);
		tfstdFlr.setBackground(Color.BLACK);
		tfstdFlr.setText("");
		tfstdFlr.setBounds(309, 318, 86, 20);
		AddRent.add(tfstdFlr);
		tfstdFlr.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Room Fee Of Student");
		lblNewLabel_7.setBounds(130, 405, 159, 14);
		AddRent.add(lblNewLabel_7);
		
		tfRoomRent = new JTextField();
		tfRoomRent.setBackground(Color.BLACK);
		tfRoomRent.setForeground(Color.WHITE);
		tfRoomRent.setEnabled(false);
		tfRoomRent.setEditable(false);
		tfRoomRent.setBounds(309, 402, 86, 20);
		AddRent.add(tfRoomRent);
		tfRoomRent.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Total Hostel Fee  of Student");
		lblNewLabel_8.setBounds(624, 345, 240, 14);
		AddRent.add(lblNewLabel_8);
		
		tfTotfee = new JTextField("0",5);
		tfTotfee.setHorizontalAlignment(JTextField.RIGHT);
		tfTotfee.setForeground(Color.RED);
		tfTotfee.setBackground(Color.BLACK);
		tfTotfee.setText("0");
		tfTotfee.setEnabled(false);
		tfTotfee.setEditable(false);
		tfTotfee.setBounds(885, 342, 125, 20);
		AddRent.add(tfTotfee);
		tfTotfee.setColumns(10);
		
		//////////////////////////////--------------Find total of Hostel fees
		 
		JButton btnTotal = new JButton("Find Total",new ImageIcon(ClassLoader.getSystemResource("myhostelproject/hostel/gui/_adminGUI/images/calc.PNG")));;
		btnTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tfTotfee.setText("0");
				Long msfe=Long.parseLong(tfDefMessfee.getText());
				Long rrent=Long.parseLong(tfRoomRent.getText());
				Long othe=Long.parseLong(tfOtherExp.getText());
				Long tot=msfe+rrent+othe;
				if((msfe+rrent+othe)>0)
					tfTotfee.setText(String.valueOf(tot));
				else
					tfTotfee.setText("0");
			}
		});
		btnTotal.setBounds(1031, 341, 89, 23);
		AddRent.add(btnTotal);
		
		////=====================================To save the  Ablc record
		
		JButton btnNewButton = new JButton("Save",new ImageIcon(ClassLoader.getSystemResource("myhostelproject/hostel/gui/_adminGUI/images/save.PNG")));;
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ablcservices as=new ablcservices();
				ablcmodels am=new ablcmodels();
						am.setAblcstdId(Integer.parseInt(String.valueOf(cbStdIds.getSelectedItem())));
						am.setAblcRmnum(tfstdRm.getText());
						am.setAblcrmRent(Long.parseLong(String.valueOf(tfRoomRent.getText())));
						am.setAblcMesfe(Long.parseLong(String.valueOf(tfDefMessfee.getText())));
						am.setAblcOthexp(Long.parseLong(String.valueOf(tfOtherExp.getText())));
						am.setAblcTot(Long.parseLong(String.valueOf(tfTotfee.getText())));
						am.setAblcMonth(monthname(monthChooser.getMonth()));
						am.setAblcyear(String.valueOf(yearChooser.getValue()));
				as.ablcSubmit(am);	
			}
		});
		btnNewButton.setBounds(885, 427, 89, 23);
		AddRent.add(btnNewButton);
		
		//-------------------------------------------Clearing All fields
		JButton btnNewButton_1 = new JButton("Clear",new ImageIcon(ClassLoader.getSystemResource("myhostelproject/hostel/gui/_adminGUI/images/clear.PNG")));;
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tfRoomRent.setText("");
				tfstdFlr.setText("");
				tfstdRm.setText("");
				tfStdnm.setText("");
				tfTotfee.setText("0");
				tfStudblk.setText("");
				cbStdIds.setSelectedItem("-Select-");
				tfOtherExp.setText("0");
			}
		});
		btnNewButton_1.setBounds(651, 427, 89, 23);
		AddRent.add(btnNewButton_1);
		
		JLabel lblNewLabel_9 = new JLabel("Ksh..");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_9.setBounds(273, 86, 34, 14);
		AddRent.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Ksh.");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_10.setBounds(852, 224, 34, 14);
		AddRent.add(lblNewLabel_10);
		
		monthChooser = new JMonthChooser();
		monthChooser.setMonth(0);
		monthChooser.setBounds(888, 142, 112, 20);
		AddRent.add(monthChooser);
		
		
		
		JLabel lblNewLabel_11 = new JLabel("Select Which Month's Fee");
		lblNewLabel_11.setBounds(672, 145, 195, 14);
		AddRent.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("Select Which Year");
		lblNewLabel_12.setBounds(672, 86, 195, 14);
		AddRent.add(lblNewLabel_12);
		
		yearChooser = new JYearChooser();
		yearChooser.setBounds(889, 83, 65, 20);
		AddRent.add(yearChooser);
		
		JLabel lblNewLabel_13 = new JLabel("Ksh.");
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_13.setBounds(839, 345, 46, 14);
		AddRent.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("Hostel Charges");
		lblNewLabel_14.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_14.setBounds(470, 11, 257, 40);
		AddRent.add(lblNewLabel_14);
		System.out.println(yearChooser.getValue() );
		}
	
	///==============to return month Name according to month chooser selection
	public String monthname(int mont) {
		switch(mont) {
		case 0:return "January";
		case 1:return"February"; 
		case 2:return"March"; 
		case 3:return"April"; 
		case 4:return"May"; 
		case 5:return"June"; 
		case 6:return"July"; 
		case 7:return"August"; 
		case 8:return"September"; 
		case 9:return"October"; 
		case 10:return"November"; 
		case 11:return"December"; 
		}
		return null;
	}//end of month Name
}
