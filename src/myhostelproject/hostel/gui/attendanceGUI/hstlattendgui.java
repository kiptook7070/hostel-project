package myhostelproject.hostel.gui.attendanceGUI;

import myhostelproject.hostel.hstlmodels.attModel;
import myhostelproject.hostel.hstlservices.atendanService;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import java.awt.Component;
import javax.swing.Box;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.JInternalFrame;
import javax.xml.ws.handler.MessageContext.Scope;

public class hstlattendgui extends JPanel {

	Dimension dm=Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel panelmarkAtt;
	private JScrollPane scrollPane;

	String []values;
	private JList AbsntList,prsntList, dsplList;
	DefaultListModel lst2mdl=new DefaultListModel();
	DefaultListModel lst3mdl=new DefaultListModel();
	DefaultListModel lst1mdl,tmp,tmp2;
	
	private AttsearchPanel as;
	private JDateChooser dateChooser;
	private JScrollPane ScrollprsntLst;
	private JButton absntAddAll,absntRemovAll,AddprsntBtn,RmovPrsnt,PrsntAddAll,PrsntRmovAll,subm_Att;
	private JScrollPane ScrollattSearch;
	private Dimension dme=Toolkit.getDefaultToolkit().getScreenSize();
	/**
	 * Create the panel.
	 */
	public hstlattendgui() {
		setBackground(new Color(85, 107, 47));
		setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Attendance");
		lblNewLabel.setBounds(167, 11, 267, 21);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 34, dm.width,dm.height);
		add(tabbedPane);
		
		final JPanel Search_panel = new JPanel();
		Search_panel.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
				as=new AttsearchPanel();
				ScrollattSearch.removeAll();
				as.setBounds(10,11,(int)dme.getWidth(),(int)dme.getHeight());
				ScrollattSearch.add(as);
				
			}
			public void ancestorMoved(AncestorEvent arg0) {
			}
			public void ancestorRemoved(AncestorEvent arg0) {
			}
		
		});
		tabbedPane.addTab("Search Attendance", null, Search_panel, null);
		Search_panel.setLayout(null);
		
		 as=new AttsearchPanel();
		ScrollattSearch = new JScrollPane(as);
		ScrollattSearch.setBounds(-12, -11, 1388, 790);
		Search_panel.add(ScrollattSearch);
		
		panelmarkAtt = new JPanel();
		panelmarkAtt.setBackground(new Color(153, 255, 204));
		tabbedPane.addTab("Mark Attendance", null, panelmarkAtt, null);
		panelmarkAtt.setLayout(null);
		
		final Component verticalGlue = Box.createVerticalGlue();
		verticalGlue.setBounds(392, 5, 0, 0);
		panelmarkAtt.add(verticalGlue);
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd-MM-yyyy");
		dateChooser.setBounds(259, 42, 91, 20);
		panelmarkAtt.add(dateChooser);
		
		JLabel lblNewLabel_1 = new JLabel("Select Date");
		lblNewLabel_1.setBounds(52, 48, 115, 14);
		panelmarkAtt.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Display Students",new ImageIcon(ClassLoader.getSystemResource("myhostelproject/hostel/gui/_adminGUI/images/memlist.PNG")));;
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atendanService as=new atendanService();
				dsplList.setModel(as.selectStuds());
		}});
		btnNewButton.setBounds(441, 119, 162, 23);
		panelmarkAtt.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Student ID");
		lblNewLabel_2.setBounds(425, 182, 68, 14);
		panelmarkAtt.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Student Name");
		lblNewLabel_3.setBounds(515, 182, 99, 14);
		panelmarkAtt.add(lblNewLabel_3);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(425, 197, 200, 294);
		panelmarkAtt.add(scrollPane);
		
		dsplList = new JList();
		
		scrollPane.setRowHeaderView(dsplList);
		
		JButton btnNewButton_1 = new JButton("Add Absent ->");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					lst2mdl=(DefaultListModel) AbsntList.getModel();
					
				try {
					lst3mdl=(DefaultListModel)prsntList.getModel();
					if(lst2mdl.contains(dsplList.getSelectedValue())||lst3mdl.contains(dsplList.getSelectedValue()))
						JOptionPane.showMessageDialog(null, "Element Already exist \n in one of other two List...! or in both");
					else {
						lst1mdl=(DefaultListModel) dsplList.getModel(); 
						int ind=dsplList.getSelectedIndex();
						lst2mdl.addElement(dsplList.getSelectedValue());
						if(ind!=-1)lst1mdl.remove(ind);
					}
				} catch (Exception e) {
					if(lst2mdl.contains(dsplList.getSelectedValue()))
						JOptionPane.showMessageDialog(null, "Element Already exist \n in one of other two List...! or in both");
					else {
						lst1mdl=(DefaultListModel) dsplList.getModel(); 
						int ind=dsplList.getSelectedIndex();
						lst2mdl.addElement(dsplList.getSelectedValue());
						if(ind!=-1)lst1mdl.remove(ind);
					}	
				}	
					
			}
		});
		btnNewButton_1.setBounds(665, 253, 152, 23);
		panelmarkAtt.add(btnNewButton_1);
		
		ScrollprsntLst = new JScrollPane();
		ScrollprsntLst.setBounds(10, 197, 200, 294);
		panelmarkAtt.add(ScrollprsntLst);
		
		prsntList = new JList();
		prsntList.setModel(lst3mdl);
		ScrollprsntLst.setViewportView(prsntList);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(852, 197, 200, 301);
		panelmarkAtt.add(scrollPane_1);
		
		AbsntList = new JList();
		AbsntList.setModel(lst2mdl);
		scrollPane_1.setViewportView(AbsntList);
		
		JButton btnNewButton_2 = new JButton("<- Remove Absent");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 try {
					 DefaultListModel tmp=((DefaultListModel)AbsntList.getModel());
					 tmp.remove(AbsntList.getSelectedIndex());
				 }
					catch(Exception e7) {
						JOptionPane.showMessageDialog(null, "Select some thing from List of Absentees..?");
					}
			}
		});
		btnNewButton_2.setBounds(665, 308, 152, 23);
		panelmarkAtt.add(btnNewButton_2);
		
		JLabel label = new JLabel("Student ID");
		label.setBounds(852, 182, 68, 14);
		panelmarkAtt.add(label);
		
		JLabel label_1 = new JLabel("Student Name");
		label_1.setBounds(953, 182, 99, 14);
		panelmarkAtt.add(label_1);
		
		JLabel lblNewLabel_4 = new JLabel("List of Absentees");
		lblNewLabel_4.setBounds(903, 156, 152, 20);
		panelmarkAtt.add(lblNewLabel_4);
		
		subm_Att = new JButton("Submit",new ImageIcon(ClassLoader.getSystemResource("myhostelproject/hostel/gui/_adminGUI/images/save.PNG")));;			
		subm_Att.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Date dt=dateChooser.getDate();	
				String sd=String.format("%1$td-%1$tm-%1$tY",dt);
				
				lst2mdl=(DefaultListModel)AbsntList.getModel();		
				lst3mdl=(DefaultListModel)prsntList.getModel();		
				
				int []absid = new int[lst2mdl.getSize()];
				int []prsntid=new int[lst3mdl.getSize()];
				
				String[] absnames=new String[lst2mdl.getSize()];
				String[] prsntnames=new String[lst3mdl.getSize()];
				
				String st4[]=null,st5[]=null;		
				int k = 0;							
				
				for(int i=0;i<absid.length;i++) {		
					 st4=(String.valueOf(lst2mdl.elementAt(i)).split("\\s+"));       
					 absid[k]=Integer.parseInt(st4[0]);
					 absnames[k]=st4[1];
					 k++;
					 st4=null;
				}
				k=0;
				for(int i=0;i<prsntid.length;i++) {		
							
					 st5=(String.valueOf(lst3mdl.elementAt(i)).split("\\s+"));			
					 
					 prsntid[k]=Integer.parseInt(st5[0]);
					 prsntnames[k]=st5[1];
					 k++;
					 st5=null;
				}
				
				attModel am=new attModel();
				atendanService ats=new atendanService();
			
				try {
					String []date=(sd.trim()).split("-");
					am.setDay(Integer.parseInt(date[0]));
					am.setMnth(monthName(Integer.parseInt(date[1])));
					am.setYear(Integer.parseInt(date[2]));
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Enter Date....!!!");
					return;
				}
			
				am.setPrsntname(prsntnames);
				am.setPresntId(prsntid);
				am.setAbsntId(absid);
				am.setAbsnam(absnames);
				
				if(ats.insertAtt(am)==1) 								  /////--- calling insert method
					JOptionPane.showMessageDialog(null, "Successfully Added Attendance.....!");
				else JOptionPane.showMessageDialog(null, "Not Added Attendance.....!");

			}
		});
		
		JButton button = new JButton("Clear All",new ImageIcon(ClassLoader.getSystemResource("myhostelproject/hostel/gui/_adminGUI/images/clear.PNG")));; ///----------------------  to clear all data
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateChooser.setCalendar(null);
				try {
					((DefaultListModel)dsplList.getModel()).removeAllElements();
					((DefaultListModel)AbsntList.getModel()).removeAllElements();
					((DefaultListModel)prsntList.getModel()).removeAllElements();
				}catch(Exception es) {}
			}
		});
		button.setBounds(425, 536, 97, 23);
		panelmarkAtt.add(button);
		subm_Att.setBounds(525, 536, 97, 23);
		panelmarkAtt.add(subm_Att);
		
		AddprsntBtn = new JButton("<-Add Present");///-------  add to presents List 
		AddprsntBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lst3mdl=(DefaultListModel) prsntList.getModel();//------------for checking if element already exists in jlist "listAbsnt"
				try {
					lst2mdl=(DefaultListModel)AbsntList.getModel();
					if(lst2mdl.contains(dsplList.getSelectedValue())||lst3mdl.contains(dsplList.getSelectedValue()))//--do not allow multiple jlist entry
						JOptionPane.showMessageDialog(null, "Element Already exist \n in one of other two List...! or in both");
					else {
						lst1mdl=(DefaultListModel) dsplList.getModel(); 
						int ind=dsplList.getSelectedIndex();
						lst3mdl.addElement(dsplList.getSelectedValue());
						if(ind!=-1)lst1mdl.remove(ind);
					}
				}catch (Exception e1) {
					if(lst3mdl.contains(dsplList.getSelectedValue()))
						JOptionPane.showMessageDialog(null, "Element Already exist \n in one of other two List...! or in both");
					else {
						lst1mdl=(DefaultListModel) dsplList.getModel(); 
						int ind=dsplList.getSelectedIndex();
						lst3mdl.addElement(dsplList.getSelectedValue());
						if(ind!=-1)lst1mdl.remove(ind);
					}	
				}	
			}
		});
		AddprsntBtn.setBounds(240, 253, 152, 23);
		panelmarkAtt.add(AddprsntBtn);
		
		absntAddAll = new JButton("Add All ->");
		absntAddAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tmp=new DefaultListModel();
				tmp=(DefaultListModel) dsplList.getModel();
				tmp2=(DefaultListModel)prsntList.getModel();
				
				int f=0;
				for(int i=0;i<tmp.getSize();i++) {//----searching for similar elements
					for(int j=0;j<tmp2.size();j++) {
						if(tmp.elementAt(i).equals(tmp2.elementAt(j))) {
							JOptionPane.showMessageDialog(null, "Element   "+tmp2.elementAt(i)+"   AlreadyExists...in Absent List\n Cannot add to Prsent List");
							f=1;
							return;
						}
					}
				}
				if(f==0) /// if no similar elements found then insert 
					AbsntList.setModel(dsplList.getModel());
				}
		});
		absntAddAll.setBounds(665, 357, 152, 23);
		panelmarkAtt.add(absntAddAll);
		
		absntRemovAll = new JButton("<- Remove All");///--remove all students from Absebt jlist
		absntRemovAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				((DefaultListModel)AbsntList.getModel()).removeAllElements();
			}
		});
		absntRemovAll.setBounds(665, 410, 152, 23);
		panelmarkAtt.add(absntRemovAll);
		
		RmovPrsnt = new JButton("Remove Present - >");
		RmovPrsnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
					 DefaultListModel tmp=((DefaultListModel)prsntList.getModel());
					 tmp.remove(prsntList.getSelectedIndex());
				 }
					catch(Exception e7) {
						JOptionPane.showMessageDialog(null, "Select some thing from List of Absentees..?");
					}
				}
			
		});
		RmovPrsnt.setBounds(240, 300, 152, 23);
		panelmarkAtt.add(RmovPrsnt);
		
		PrsntAddAll = new JButton("<- Add All");///============================ add All student to presents
		PrsntAddAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tmp=new DefaultListModel();
				tmp=(DefaultListModel) dsplList.getModel();
				tmp2=(DefaultListModel)AbsntList.getModel();
				//JOptionPane.showMessageDialog(null, "Entering Loop....");
				int f=0;
				for(int i=0;i<tmp.getSize();i++) {
					for(int j=0;j<tmp2.size();j++) {
						if(tmp.elementAt(i).equals(tmp2.elementAt(j))) {
							JOptionPane.showMessageDialog(null, "Element   "+tmp2.elementAt(i)+"   AlreadyExists...in Absent List\n Cannot add to Prsent List");
							f=1;
							return;
						}
					}
				}
				if(f==0) {
					prsntList.setModel(dsplList.getModel());
					
				}
			}
		});
		PrsntAddAll.setBounds(240, 349, 152, 23);
		panelmarkAtt.add(PrsntAddAll);
		
		PrsntRmovAll = new JButton("Remove All - >");
		PrsntRmovAll.addActionListener(new ActionListener() {//--------- remove all students from Present jlist
			public void actionPerformed(ActionEvent e) {
				((DefaultListModel)prsntList.getModel()).removeAllElements();
			}
		});
		PrsntRmovAll.setBounds(240, 396, 152, 23);
		panelmarkAtt.add(PrsntRmovAll);
			
		JLabel label_2 = new JLabel("Student ID");
		label_2.setBounds(10, 182, 68, 14);
		panelmarkAtt.add(label_2);
		
		JLabel label_3 = new JLabel("Student Name");
		label_3.setBounds(99, 182, 99, 14);
		panelmarkAtt.add(label_3);
		
		JLabel lblListOfPresentees = new JLabel("List of Presentees");
		lblListOfPresentees.setBounds(52, 156, 152, 20);
		panelmarkAtt.add(lblListOfPresentees);
	}
	///==============to return month Name according to month chooser selection
			public String monthName(int month) {
				switch(month) {
				case 1:return "January";
				case 2:return"February"; 
				case 3:return"March"; 
				case 4:return"April"; 
				case 5:return"May"; 
				case 6:return"June"; 
				case 7:return"July"; 
				case 8:return"August"; 
				case 9:return"September"; 
				case 10:return"October"; 
				case 11:return"November"; 
				case 12:return"December"; 
				}
				return null;
			}//end of month Name
}
