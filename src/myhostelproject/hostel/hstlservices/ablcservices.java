package myhostelproject.hostel.hstlservices;

import myhostelproject.hostel.hstlconnection.hstlconnection;
import myhostelproject.hostel.hstlmodels.ablcmodels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ablcservices {
	Connection con=hstlconnection.getConnection();
	
	
	public ArrayList<String> getStUdid( ){
		ArrayList<String> sid=new ArrayList<String>();
		try {
			sid.add("-Select-");
			PreparedStatement ps=con.prepareStatement("select grndstudid from hostelallocations order by grndstudid");
			ResultSet rs=ps.executeQuery();
			if(!rs.isBeforeFirst()&&rs.getRow()==0)JOptionPane.showMessageDialog(null, "No Records Available..!","DataBase Error",JOptionPane.ERROR_MESSAGE);
			else {
				while(rs.next()) {
						int i=rs.getInt(1);
						sid.add(String.valueOf(i));
				}
				return sid;
			}
		}
		catch(SQLException se){
			System.out.println("SQL EXCEPTION in Ablc Studid combo OCCOURED..."+se);
			se.printStackTrace();
		}
		catch(Exception e){
		System.out.println("EXCEPTION  inAblc Studid combo OCCOURED..."+e);
		}
		return null;
		
	}
	
	
	public TableModel ablcTable() {
		try {
					PreparedStatement ps=con.prepareStatement("SELECT * FROM hstlabls");
					ResultSet rs=ps.executeQuery();
					ResultSetMetaData rmeta=rs.getMetaData();
					
					int colnum=rmeta.getColumnCount();
					Vector<Object> colnam=new Vector<Object>();
					
					colnam.add("Serial Num");
					colnam.add("Student ID");
					colnam.add("Room Num");
					colnam.add("Room Rent");
					colnam.add("Mess Fee");
					colnam.add("Year");
					colnam.add("Month");
					colnam.add("Other expences");
					colnam.add("Total Fee");
					
					Vector<Object>rows=new Vector<Object>();
					while(rs.next()) {
						Vector<Object>onerow=new Vector<Object>();
						for(int i=1;i<=colnum;i++) onerow.add(rs.getObject(i));
						rows.add(onerow);
					}	
					return new DefaultTableModel(rows,colnam) {
						public boolean isCellEditable(int row, int column) {//user cannot edit table but can select rows or cells 
							return false;
						}
					};
			}
		catch(SQLException se){
			System.out.println("SQL EXCEPTION in Ablc Table View OCCOURED..."+se);
			se.printStackTrace();
		}
		catch(Exception e){
		System.out.println("EXCEPTION  in Ablc Table View OCCOURED..."+e);
		}
		return null;
		
	}
	

	public ablcmodels ablcStdinfo(ablcmodels am) {
		try{
			PreparedStatement ps=con.prepareStatement("select grndblknam,grndflrnam,grndrmnum,grndrmrent from hostelallocations where grndstudid=?");
			ps.setInt(1,am.getAblcstdId());
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				am.setAblcBlknam(rs.getString(1));
				am.setAblcFlornum(rs.getString(2));
				am.setAblcRmnum(rs.getString(3));
				am.setAblcrmRent(rs.getLong(4));
			}
			ps=con.prepareStatement("select grndflrnam from hostelallocations where grand_id=?");
			ps.setInt(1,am.getAblcstdId());
			rs=ps.executeQuery();
			while(rs.next())am.setAblcStudNam(rs.getString(1));
			return am;
		}
		catch(SQLException se){
			System.out.println("SQL EXCEPTION in Ablc Studid info OCCOURED..."+se);
			se.printStackTrace();
		}
		catch(Exception e){
		System.out.println("EXCEPTION  inAblc Studid info OCCOURED..."+e);
		}
		return null; 		
	}
	
	
	int sid;
	public void ablcSubmit(ablcmodels am) {
		try {
			PreparedStatement ps=con.prepareStatement("select srl_no from hstlabls where studid like ? and rmnum like ? and rent like ? and mesfe like ? and ayear like ? and amonth like ? and othexpn like ? and total like ? ");
			 ps.setInt(1,am.getAblcstdId());
			 ps.setString(2, am.getAblcRmnum());
			 ps.setLong(3,am.getAblcrmRent());
			 ps.setLong( 4,am.getAblcMesfe());
			 ps.setString(5, am.getAblcyear());
			 ps.setString(6, am.getAblcMonth());
			 ps.setLong( 7,am.getAblcOthexp());
			 ps.setLong( 8,am.getAblcTot());
			 System.out.println(ps);
			ResultSet rs=ps.executeQuery();
			if(rs.next())JOptionPane.showMessageDialog(null, "Already exists Same record...!","Error",JOptionPane.ERROR_MESSAGE);
			else {
				ps=con.prepareStatement("select studid from hstlabls where ayear like ? and amonth like ? and rmnum like ?");
				 ps.setString(1, am.getAblcyear());
				 ps.setString(2, am.getAblcMonth());
				 ps.setString(3, am.getAblcRmnum());
				 System.out.println(ps);
				 rs=ps.executeQuery();
				 if(rs.next())JOptionPane.showMessageDialog(null, "Already Assigned Hostel Fee to Student for\n This Moth and Year...!","Error",JOptionPane.ERROR_MESSAGE);
			 else {
					 ps=con.prepareStatement(" insert into hstlabls(studid,rmnum,rent,mesfe,ayear,amonth,othexpn,total) values(?,?,?,?,?,?,?,?)");
						 ps.setInt(1,am.getAblcstdId());
						 ps.setString(2, am.getAblcRmnum());
						 ps.setLong(3,am.getAblcrmRent());
						 ps.setLong( 4,am.getAblcMesfe());
						 ps.setString(5, am.getAblcyear());
						 ps.setString(6, am.getAblcMonth());
						 ps.setLong( 7,am.getAblcOthexp());
						 ps.setLong( 8,am.getAblcTot());
						 System.out.println(ps);
					 ps.executeUpdate();
					 JOptionPane.showMessageDialog(null, "One Ablc Record Inserted Successfully....!!!","Success",JOptionPane.INFORMATION_MESSAGE);
				 }
			}
		}
		catch(SQLException se){
			System.out.println("SQL EXCEPTION in Ablc  save OCCOURED..."+se);
			se.printStackTrace();
		}
		catch(Exception e){
		System.out.println("EXCEPTION  inAblc save OCCOURED..."+e);
		}
	}//end of Saving Ablc Values to Data base
	
	
}
