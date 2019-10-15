package myhostelproject.hostel.gui._adminGUI;

import myhostelproject.hostel.hstlconnection.hstlconnection;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;

public class login extends JFrame{

	private JPanel contentPane;
	private JTextField tfName;
	private JPasswordField pwfield;
	private int cnt;

	
	public login() {
		cnt=3;
		setResizable(false);
		setTitle("LOGIN TO CONTINUE");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(450, 250, 424, 278);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter User Name");
		lblNewLabel.setBounds(29, 57, 128, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Password");
		lblNewLabel_1.setBounds(29, 114, 128, 14);
		contentPane.add(lblNewLabel_1);
		
		tfName = new JTextField();
		tfName.setBounds(247, 54, 102, 20);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		pwfield = new JPasswordField();
		pwfield.setBounds(247, 111, 102, 20);
		contentPane.add(pwfield);
			///------------------cancel
		JButton btnCancel = new JButton("cancel",new ImageIcon(ClassLoader.getSystemResource("myhostelproject/hostel/gui/_adminGUI/images/cancel.PNG")));;
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x=JOptionPane.showConfirmDialog(null,"Are you sure to quit","Conform",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
				if(x==0) System.exit(0);
				else return;
			}
		});
		btnCancel.setBounds(29, 189, 89, 23);
		contentPane.add(btnCancel);
		
		JButton btnConnect = new JButton("Ok",new ImageIcon(ClassLoader.getSystemResource("myhostelproject/hostel/gui/_adminGUI/images/login.PNG")));;
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con=hstlconnection.getConnection();
				try {
					PreparedStatement ps=con.prepareStatement("select name,password from login where name=? and password=?");
						ps.setString(1, tfName.getText());
						ps.setString(2, pwfield.getText());
						System.out.println(ps);
					ResultSet rs=ps.executeQuery();
					if(rs.next()) {
						login.this.setVisible(false);// close login dialog			
						hstladmin.MainTabbedPane.setVisible(true);
						if(tfName.getText().equals("admin"))hstladmin.viewLogTbl.setVisible(true);
								
					}
					else {
						cnt--;
						if(cnt>0)
							JOptionPane.showMessageDialog(null, "		Error...! in Login \n     Try Again \n      No of Trys left "+cnt);
						else { 
							JOptionPane.showMessageDialog(null, "		Error...! in Login....\n       Software is Quitting..!");
							System.exit(0);
						}	
						
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				catch (Exception e2) {
					e2.getMessage();
				}
			}
		});
		btnConnect.setBounds(284, 189, 89, 23);
		contentPane.add(btnConnect);
		
		/////////////////--------------------------clear
		JButton btnClear = new JButton("Clear",new ImageIcon(ClassLoader.getSystemResource("myhostelproject/hostel/gui/_adminGUI/images/clear.PNG")));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfName.setText("");
				pwfield.setText("");
			}
		});
		btnClear.setBounds(161, 189, 89, 23);
		contentPane.add(btnClear);
	}
}
