package myhostelproject.hostel.gui._adminGUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class loginView extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;

	public static void main(String[] args) {
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
	public loginView() {
		setResizable(false);
		setBackground(new Color(204, 255, 204));
		setTitle("Users List Details");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 658, 181);		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		scrollPane_1 = new JScrollPane();
		table1 tb1=new table1();
		scrollPane_1.setViewportView(tb1.table);		
		scrollPane_1.setBounds(23, 11, 619, 99);
		contentPane.add(scrollPane_1);
		
		JButton btnNewButton = new JButton("Refresh Table");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table1 tb1=new table1();
				scrollPane_1.setViewportView(tb1.table);	
			}
		});
		btnNewButton.setForeground(new Color(0, 102, 102));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(244, 121, 185, 23);
		contentPane.add(btnNewButton);
	}
}
