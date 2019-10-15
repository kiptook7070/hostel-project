package myhostelproject.hostel.gui._adminGUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import myhostelproject.hostel.gui.ablcGUI.hstlablcgui;
import myhostelproject.hostel.gui.attendanceGUI.hstlattendgui;
import myhostelproject.hostel.gui.blockGui.hstlblockgui;
import myhostelproject.hostel.gui.floorGUI.hstlfloorgui;

import myhostelproject.hostel.gui.rmGrantGui.hstlgrandgui;
import myhostelproject.hostel.gui.roomGUI.hstlroomgui;
import myhostelproject.hostel.gui.studentGUI.hstlstudentgui;
import myhostelproject.hostel.gui.visitorsGUI.hstlvistiorgui;
import java.awt.Color;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;


public class hstladmin {

	private JFrame frmAdministratprHome;
	protected static JTabbedPane MainTabbedPane;
	private JButton btnLogIn;
	public static JButton viewLogTbl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		}
		catch(Exception unused) {}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				Dimension screensz=Toolkit.getDefaultToolkit().getScreenSize();
				
				try {
					hstladmin window = new hstladmin();
					window.frmAdministratprHome.setBounds(0, 0,screensz.width,screensz.height);
					window.frmAdministratprHome.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the application.
	 */
	public hstladmin() {
		initialize();
	}

	
	private void initialize() {
		frmAdministratprHome = new JFrame();
		frmAdministratprHome.setResizable(false);
		frmAdministratprHome.getContentPane().setBackground(new Color(0, 139, 139));
		frmAdministratprHome.setTitle("-----ADMINISTRATOR  HOME  WINDOW-----");
		frmAdministratprHome.setBounds(100, 100, 1046, 715);
		
		frmAdministratprHome.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		frmAdministratprHome.getContentPane().setLayout(null);
		
		 
		Dimension screensz1=Toolkit.getDefaultToolkit().getScreenSize();
		
		
		JButton btquit = new JButton("Quit",new ImageIcon(ClassLoader.getSystemResource("myhostelproject/hostel/gui/_adminGUI/images/cancel.PNG")));;
		btquit.setLocation(36, 412);
		btquit.setSize(131, 23);
		btquit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((JOptionPane.showConfirmDialog(null,"Are you sure to quit","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE))==0) System.exit(0);
			}
		});
		frmAdministratprHome.getContentPane().add(btquit);
		MainTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		MainTabbedPane.setBounds(210, 0, 1229, 766);
		frmAdministratprHome.getContentPane().add(MainTabbedPane);
		MainTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		MainTabbedPane.setBackground(new Color(0, 153, 153));
		MainTabbedPane.setVisible(false);
		
		
                //hstlattendgui hsatt=new hstlattendgui();
                hstlstudentgui hstu=new  hstlstudentgui();
		MainTabbedPane.addTab("STUDENT",null,hstu,null);
		MainTabbedPane.setBackgroundAt(0, new Color(0, 204, 204));
		
		hstlvistiorgui hvis=new hstlvistiorgui();			
		MainTabbedPane.addTab("VISITORS",null,hvis,null);
		MainTabbedPane.setBackgroundAt(1, new Color(0, 204, 204));
		
		
                
               //hstlstudentgui hstu=new  hstlstudentgui();
               hstlattendgui hsatt=new hstlattendgui();
		MainTabbedPane.addTab("ATTENDANCE",null,hsatt,null);
		MainTabbedPane.setBackgroundAt(2, new Color(0, 204, 204));
		
		try {
			hstlablcgui hablc=new hstlablcgui();
			MainTabbedPane.addTab("ROOM DETAILS", null,hablc,null);
			MainTabbedPane.setBackgroundAt(3, new Color(0, 204, 204));
		} catch (Exception e) {	}
		
		
		hstlgrandgui hgr=new hstlgrandgui();
		MainTabbedPane.addTab("ROOM ALLOCATION", null,hgr,null);
		MainTabbedPane.setBackgroundAt(4, new Color(0, 204, 204));
		
		hstlroomgui hr=new hstlroomgui();	
		MainTabbedPane.addTab("ROOMS",null,hr,null);
		MainTabbedPane.setBackgroundAt(5, new Color(0, 204, 255));
		
		hstlfloorgui hf=new hstlfloorgui();	
		hf.setBackground(new Color(255, 192, 203));
		MainTabbedPane.addTab("AVAILABLE FLOORS",null,hf,null);
		MainTabbedPane.setBackgroundAt(6, new Color(0, 204, 204));
		
		
		hstlblockgui hb= new hstlblockgui();
		hb.setBackground(new Color(0, 100, 0));
		MainTabbedPane.addTab("BLOCKS", null,hb,null);
		MainTabbedPane.setBackgroundAt(7, new Color(0, 204, 204));
                
                 hstabout ab= new hstabout();
            ab.setBackground(new Color(0, 100, 0));
		MainTabbedPane.addTab("ABOUT", null,ab,null);
		MainTabbedPane.setBackgroundAt(7, new Color(0, 204, 204));
		
		btnLogIn = new JButton("LogIn",new ImageIcon(ClassLoader.getSystemResource("myhostelproject/hostel/gui/_adminGUI/images/login.PNG")));/// -- log in
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
					try {
						login frame = new login();
						frame.setVisible(true);
						btnLogIn.setEnabled(false);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			});
			}
		});
		btnLogIn.setBounds(36, 70, 131, 23);
		frmAdministratprHome.getContentPane().add(btnLogIn);
		
		viewLogTbl = new JButton("View Login Table");
		viewLogTbl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
					try {
						loginView frame = new loginView();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			});
			}
		});
		viewLogTbl.setBounds(36, 220, 131, 23);
		frmAdministratprHome.getContentPane().add(viewLogTbl);
		viewLogTbl.setVisible(false); 
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
