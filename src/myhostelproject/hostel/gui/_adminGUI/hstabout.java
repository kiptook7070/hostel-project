
package myhostelproject.hostel.gui._adminGUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;



class hstabout extends JPanel{
 private static final long serialVersionUID = 1L;
	JTabbedPane tabbedPane,tabpanView;
    
    
    
    
    public hstabout(){
      setBackground(new Color(152, 251, 152));
		setLayout(null);
		

		
		JButton btnAbout = new JButton("ABOUT",new ImageIcon(ClassLoader.getSystemResource("myhostelproject/hostel/gui/_adminGUI/images/appicon.PNG")));
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				about ab= new about();
				
			}
		});
		btnAbout.setBounds(86, 274, 146, 56);
		add(btnAbout);
             
		
    }
    
}
