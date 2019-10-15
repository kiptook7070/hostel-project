package myhostelproject.hostel.hstlservices;

import myhostelproject.hostel.hstlconnection.hstlconnection;
import myhostelproject.hostel.hstlmodels.attModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class atendanService {
	protected static int rcnt = 0;
	Connection con=hstlconnection.getConnection();
	PreparedStatement ps;
	ResultSet rs;
	
	
	public  DefaultListModel selectStuds() {
		try {
			DefaultListModel dlm=new DefaultListModel();
			  ps=con.prepareStatement("select distinct std_id,stdname from students where stdstat='Active'");
			  rs=ps.executeQuery();
			while(rs.next()) {
					dlm.addElement(rs.getInt(1)+"                             "+rs.getString(2));
			}
			return dlm;
			
		} catch (SQLException e) {
			System.out.println("Sql error in attendance servise selectStuds==============");
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("error in attendance servise selectStuds==============");
			System.out.println(e.getMessage());
		}
		
		return null;
	}
	
										
	public int insertAtt(attModel am) {
		int[] absid=am.getAbsntId();
		int[]prsntid=am.getPresntId();
		String[] absnam=am.getAbsnam();
		String[] presntname=am.getPrsntname();
		int f=0;	
		
						
		for(int i=0;i<prsntid.length;i++) { 
			try {
				  ps=con.prepareStatement("insert into hostelattendance(`day`,`mnth`,`year`,`stdid`,`stdname`,`attStat`)values(?,?,?,?,?,?)");
						ps.setInt(1,am.getDay());
						ps.setString(2,am.getMnth());
						ps.setInt(3,am.getYear());
						ps.setInt(4,prsntid[i]);
						ps.setString(5,presntname[i]);
						ps.setString(6,"Present");
							System.out.println("PRSENTS:\n"+ps);
					ps.executeUpdate();
				f=1;
			} catch (SQLException e) {
				System.out.println("sql Error occored in Presentees insertAtt of Att Service :-"+e.getMessage());
				e.printStackTrace();
				f=0;
			}
		}
							
		for(int i=0;i<absid.length;i++) {
			try {
				  ps=con.prepareStatement("insert into hostelattendance(`day`,`mnth`,`year`,`stdid`,`stdname`,`attStat`)values(?,?,?,?,?,?)");
					ps.setInt(1,am.getDay());
					ps.setString(2,am.getMnth());
					ps.setInt(3,am.getYear());
					ps.setInt(4,absid[i]);
					ps.setString(5,absnam[i]);
					ps.setString(6,"Absent");
						System.out.println("ABSENTEES\n"+ps);
				ps.executeUpdate();
				f=1;
			} catch (SQLException e) {
				System.out.println("sql Error occored in Absentees of Att Service:-"+e.getMessage());
				e.printStackTrace();
				f=0;
			}
		}
		if(f==1) return 1;    
		else return 0;	
	}
	
										
		
	public DefaultTableModel ViewAttend(){
		 try {
			
			Vector<Vector<Object>>rows=new Vector<Vector<Object>>();
			Vector<Object> colnames = new Vector<Object>();			
			ps=con.prepareStatement("SELECT * FROM `hostelattendance`");
			rs=ps.executeQuery();
			if(!rs.isBeforeFirst()) JOptionPane.showMessageDialog(null,"No record Found in Attendance...!","No Records",JOptionPane.ERROR_MESSAGE);// if no record found
			else {
						ResultSetMetaData rsm=rs.getMetaData();
						final int colnt=rsm.getColumnCount();
						Vector<Object> onerow;
						
						colnames.add("Day");
						colnames.add("Month");
						colnames.add("Year");
						colnames.add("StudentId");
						colnames.add("Student Name");
						colnames.add("Attendance Status");
										
						while(rs.next()) {
							onerow=new Vector<Object>();
							for(int j=1;j<=colnt;j++) onerow.add(rs.getObject(j));
							rows.add(onerow);
							rcnt++;
						}
						return new DefaultTableModel(rows,colnames){
							private static final long serialVersionUID = 1L;
							public boolean isCellEditable(int row, int column) {//user cannot edit table but can select records
								return false;
							}
							private void fireTableDataChange() {
								 super.fireTableDataChanged();
							}
							private void fireTableRowChanged(){
								 super.fireTableRowsUpdated(columncnt(),rowcnt());
							}
							private int rowcnt() {
								return rcnt;
							}
							private int columncnt() {
								return colnt;
							}
							
						};	
					}
			} catch (SQLException e) {
			System.out.println("sql Error occored in ViewAttend of Att Service:-"+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
