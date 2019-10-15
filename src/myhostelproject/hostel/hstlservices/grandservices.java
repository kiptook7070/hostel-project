package myhostelproject.hostel.hstlservices;

import myhostelproject.hostel.hstlconnection.hstlconnection;
import myhostelproject.hostel.hstlmodels.grandmodels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class grandservices {
	Connection con=hstlconnection.getConnection();
	
	
	public ComboBoxModel grndstudcbId() {
		System.out.println( "Student combo box id from grand service");
		try {
			PreparedStatement ps=con.prepareStatement("SELECT distinct std_id FROM students order by std_id ");
			ResultSet rs=ps.executeQuery();
			Vector<Object> sid=new Vector<Object>();
			
			sid.add("-Select-");
			if(rs.isBeforeFirst()&&rs.getRow()==0){
				while(rs.next()) {
					int i=1;
					sid.add(rs.getInt(i));
					
					i++;
				}
			}
			
			return new DefaultComboBoxModel(sid);
		}
		catch(SQLException se){
			System.out.println("SQL EXCEPTION in grand stud combo OCCOURED...");
			se.printStackTrace();
		}
		catch(Exception e){
		System.out.println("EXCEPTION  in grand stud combo OCCOURED..."+e);
		}
		return null;
		
	}///end of stud combo box grand
	

	public grandmodels getstudnames(grandmodels gm) {
		try {
			PreparedStatement ps=con.prepareStatement("SELECT  stdname,stdparnam,stdstat FROM students where std_id=?");
			 ps.setInt(1,gm.getGrndstudId());
			ResultSet rs=ps.executeQuery();
			if(rs.isBeforeFirst()&&(rs.getRow()==0)){
				while(rs.next()) {
					gm.setGrndstudNam(rs.getString(1));
					gm.setGrndprntNam(rs.getString(2));
					gm.setGrndStudStat(rs.getString(3));
				}
			}
			
		}
		catch(SQLException se){
			System.out.println("SQL EXCEPTION in grand stud name txtfld OCCOURED..."+se);
			se.printStackTrace();
		}
		catch(Exception e){
		System.out.println("EXCEPTION  in grand stud parnt name txtfld OCCOURED..."+e);
		}
			return gm;
	}///end of fetching student name and parent name
	

	public ComboBoxModel grndbloknam() {
		System.out.println( "block combo box name from grand service");
		try {
			PreparedStatement ps=con.prepareStatement("SELECT distinct blokname from hostelblocks order by blokname ");
			ResultSet rs=ps.executeQuery();
			Vector<Object> bid=new Vector<Object>();
			
			bid.add("-Select-");
			if(rs.isBeforeFirst()&&(rs.getRow()==0)){
				while(rs.next()) {
					int i=1;
					bid.add(rs.getString(i));
					
					i++;
				}
			}
			
			return new DefaultComboBoxModel(bid);
		}
		catch(SQLException se){
			System.out.println("SQL EXCEPTION in grand block combo name OCCOURED...");
			se.printStackTrace();
		}
		catch(Exception e){
		System.out.println("EXCEPTION  in grand block name combo OCCOURED..."+e);
		}
		return null;
		
	}//end of grand combo box getting block names
	
	///--------------------fetching floor name combo box items  -------------------------
	
		public ArrayList<String> getfloornums(grandmodels gm) {
			ArrayList<String> fn1=new ArrayList<String>();		
			String s=new String("-Select-");
			fn1.add(s);
			try {
				PreparedStatement ps=con.prepareStatement(" SELECT  flornum FROM hstlfloor where flrblokname =?");
				ps.setString(1,gm.getGrndBlkname());
				ResultSet rs=ps.executeQuery();
				if(rs.isBeforeFirst()&&(rs.getRow()==0)){
					while(rs.next()) {
						s=String.valueOf(rs.getInt(1));
						fn1.add(s);
					}
				}
				ps=con.prepareStatement("SELECT blokstatus from hostelblocks where blokname=?");
				ps.setString(1,gm.getGrndBlkname());
				 rs=ps.executeQuery();
				 if(rs.isBeforeFirst()&&(rs.getRow()==0)){
					while(rs.next()) {
						gm.setGrndBlkStat(String.valueOf(rs.getString(1)));
					}
				 }
				
				return fn1;
			}
			catch(SQLException se){
				System.out.println("SQL EXCEPTION in grand floor name OCCOURED..."+se);
				se.printStackTrace();
			}
			catch(Exception e){
			System.out.println("EXCEPTION  in grand floor name OCCOURED..."+e);
			}
				 return null;
		}///end of fetching floor name combo box items 
		
		///--------------------fetching room numbers in combo box items  -------------------------
		
			public ArrayList<String> getroomnames(grandmodels gm) {
				ArrayList<String> fn1=new ArrayList<String>();		///array list
				String s=new String("-Select-");
				fn1.add(s);
				try {
					PreparedStatement ps=con.prepareStatement(" SELECT  rmnum FROM hstlroom where rmFlrnum=?");
					ps.setString(1,gm.getGrndFlornum());
					ResultSet rs=ps.executeQuery();
					if(rs.isBeforeFirst()&&(rs.getRow()==0)){
						while(rs.next()) {
							s=rs.getString(1);
							fn1.add(s);
						}
					}
					ps=con.prepareStatement("SELECT florstatus from hstlfloor where flornum=?");
					ps.setString(1,gm.getGrndFlornum());
					 rs=ps.executeQuery();
					 if(rs.isBeforeFirst()&&(rs.getRow()==0)){
						while(rs.next()) {
							gm.setGrndFlrStat(String.valueOf(rs.getString(1)));
						}
					 }
					  
					return fn1;
				}
				catch(SQLException se){
					System.out.println("SQL EXCEPTION in grand room number OCCOURED..."+se);
					se.printStackTrace();
				}
				catch(Exception e){
				System.out.println("EXCEPTION  in grand room number OCCOURED..."+e);
				}
					 return null;
			}///end of fetching room numbers in combo box items 
		
		///--------------------fetching room status and total number of beds-------------------------
			public grandmodels getroomdetails(grandmodels gm) {
				try {
					PreparedStatement ps=con.prepareStatement(" select rmstatus,rmtotbed from hstlroom where rmnum=?");
					 ps.setString(1,gm.getGrndRoomnum());
					ResultSet rs=ps.executeQuery();
					if(rs.isBeforeFirst()&&(rs.getRow()==0)){
						while(rs.next()){
							gm.setGrndRmStat(rs.getString(1));
							gm.setGrndRmTotb(String.valueOf(rs.getInt(2)));
							
						}
					}
					 /*rs.close();
					ps.close();
					con.close(); */
					return gm;
				}
				catch(SQLException se){
					System.out.println("SQL EXCEPTION in grand room Details  OCCOURED..."+se);
					se.printStackTrace();
				}
				catch(Exception e){
				System.out.println("EXCEPTION  in grand room Details  OCCOURED..."+e);
				}
					return gm;
			}///end of fetching room status and total number of beds
			
		//===================================--------------------------------Grand Submit services	
			public void grndSubmit(grandmodels gm){
				try {	//checking if same data is there					 			   
					PreparedStatement ps=con.prepareStatement("select grand_id from hostelallocations where grndstudid like ? and grndblknam like ? and grndflrnam like ? and grndrmnum like ? and grndrmrent like ? and grndstat like ?");
							ps.setInt(1, gm.getGrndstudId());
							ps.setString(2,gm.getGrndBlkname());
							ps.setString(3, gm.getGrndFlornum());
							ps.setString(4,gm.getGrndRoomnum());
							ps.setLong(5,gm.getGrndRmrent());
							ps.setString(6,gm.getGrndStat());
							System.out.println(ps);
						ResultSet rs=ps.executeQuery(); 
						 if(rs.next()) {
							 JOptionPane.showMessageDialog(null, "Record Already Exists...!");
							 return;
						 }
						 else {	
						   ps=con.prepareStatement("select grndstudid from hostelallocations where grndstudid like ?");
						    ps.setInt(1,gm.getGrndstudId());
						   rs=ps.executeQuery();
						   if(rs.next()) {
							   JOptionPane.showMessageDialog(null, "Already Granded to student...!");
							   return;
						   }
						   else {
								   ps=con.prepareStatement("insert into hostelallocations(grndstudId,grndblknam,grndflrnam,grndrmnum,grndrmrent,grndstat)values(?,?,?,?,?,?)");
									ps.setInt(1, gm.getGrndstudId());
									ps.setString(2,gm.getGrndBlkname());
									ps.setString(3, gm.getGrndFlornum());
									ps.setString(4,gm.getGrndRoomnum());
									ps.setLong(5,gm.getGrndRmrent());
									ps.setString(6,gm.getGrndStat());
									System.out.println(ps);
								 ps.executeUpdate();
								 JOptionPane.showMessageDialog(null, "One Record Submitted Successfully....!!!");
						   }
						}
				}
				catch(SQLException se){
					System.out.println("SQL EXCEPTION in grand Submit  OCCOURED...");
					se.printStackTrace();
				}
				catch(Exception e){
				System.out.println("EXCEPTION  in grand room Submit  OCCOURED..."+e);
				}
								
			}//end of Grand Submit services
		
		/////-------------------------------Granded table view--------------------------------------------------
			private Vector<Object> row;
			private Vector<Object> colnam;
			public TableModel grandTable(){
				
				try{
					PreparedStatement ps=con.prepareStatement("select * from hostelallocations");
					ResultSet rs=ps.executeQuery();
					ResultSetMetaData rsmeta=rs.getMetaData();
					int colnum=rsmeta.getColumnCount();
					  colnam=new Vector<Object>();
					
					colnam.add("Allotment Id");
					colnam.add("Student Id");
					colnam.add("Block Name");
					colnam.add("Floor Number");
					colnam.add("Room Number");
					colnam.add("Room Rent");
					colnam.add("Status");
										
					row=new Vector<Object>();
					
					while(rs.next()){
						Vector<Object> onerow=new Vector<Object>();
						for(int i=1;i<=colnum;i++) onerow.add(rs.getObject(i));
					 row.add(onerow);
					}
					return new DefaultTableModel(row,colnam) {
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};
				}
				
				catch(SQLException se){
					System.out.println("SQL EXCEPTION in grand Table OCCOURED...");
					se.printStackTrace();
				}
				catch(Exception e){
					System.out.println("EXCEPTION  in grand Table  OCCOURED...");
					e.printStackTrace();
				}
				return null;
			
		   }//end of Granded table view
			
	//////---------------------------------------Grand Id Submitting ok button
			public grandmodels grandOk(grandmodels gm) {
				try {
					PreparedStatement ps=con.prepareStatement("select grndstudid,grndblknam,grndflrnam,grndrmnum,grndrmrent,grndstat from hostelallocations where grand_id=?");
					ps.setInt(1,gm.getGrndId());
					ResultSet rs=ps.executeQuery();
					if(!rs.isBeforeFirst()&&rs.getRow()==0) {
						JOptionPane.showMessageDialog(null, "No Records to show or ID is invalied");
						gm.setGrndFlg(0);
						return gm;
					}
					else {
						while(rs.next()) {
							gm.setGrndstudId(rs.getInt(1));
							gm.setGrndBlkname(rs.getString(2));
							gm.setGrndFlornum(rs.getString(3));
							gm.setGrndRoomnum(rs.getString(4));
							gm.setGrndRmrent(rs.getLong(5));
							gm.setGrndStat(rs.getString(6));
							
					  }
						
						gm.setGrndFlg(1);
					return gm;							
					}
				}
				catch(SQLException se){
					System.out.println("SQL EXCEPTION in grand btn OK OCCOURED...");
					se.printStackTrace();
				}
				catch(Exception e){
					System.out.println("EXCEPTION  in grand btn OK OCCOURED...");
					e.printStackTrace();
				}
				return null;
		}////end of Grand Id Submitting ok button
			
///========================================Delete a Grand record----------------------------------------
			public void grndDelt(int id) {
				try {
					int c=JOptionPane.showConfirmDialog(null,"Are you sure to delete a Granded Record","Confirm",JOptionPane.YES_NO_OPTION);
					if(c==0) {
						PreparedStatement ps=con.prepareStatement("delete from hstlgrand where grand_id=?");
						ps.setInt(1,id);
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "One Granded Record Deleted....!");
					}
					else JOptionPane.showMessageDialog(null, "Watch out what you are clicking....!");
				}
				catch(SQLException sq){
					System.out.println("SQL Error from Granded delete of Granded service ");
					sq.printStackTrace();
				}
				catch (Exception e) {
					System.out.println(" Error from  Granded delete of Granded service. ");
					e.printStackTrace();
				}
					
			}////end of Delete a Grand record
			
	/////////------------------------------------Granded Update	method	
			public void grandUpdate(grandmodels gm) {
				try {
				PreparedStatement ps=con.prepareStatement("select grand_id from hostelallocations where grndstudid like ? and grndblknam like ? and grndflrnam like ? and grndrmnum like ? and grndrmrent like ? and grndstat like ?");
				ps.setInt(1, gm.getGrndstudId());
				ps.setString(2,gm.getGrndBlkname());
				ps.setString(3, gm.getGrndFlornum());
				ps.setString(4,gm.getGrndRoomnum());
				ps.setLong(5,gm.getGrndRmrent());
				ps.setString(6,gm.getGrndStat());
				System.out.println(ps);
			ResultSet rs=ps.executeQuery(); 
			 if(rs.next()) {
				 JOptionPane.showMessageDialog(null, "Record Already Exists...!");
				 return;
			 }
			else {
				
					int c=JOptionPane.showConfirmDialog(null,"Are you sure to Update a Granded Record","Confirm",JOptionPane.YES_NO_OPTION);
						if(c==0) {
							  ps=con.prepareStatement("UPDATE  hostelallocations SET grndstudid=?,grndblknam=?,grndflrnam=?,grndrmnum=?,grndrmrent=?, grndstat=? WHERE  grand_id=? ");
								ps.setInt(1,gm.getGrndstudId());
								ps.setString(2,gm.getGrndBlkname());
								ps.setString(3,gm.getGrndFlornum());
								ps.setString(4, gm.getGrndRoomnum());
								ps.setLong(5,gm.getGrndRmrent());
								ps.setString(6,gm.getGrndStat());
								ps.setInt(7,gm.getGrndId());
							ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "One Granded Record Updated Successfully...!!!");
						}
						else JOptionPane.showMessageDialog(null, "Watch out what you are clicking....!");
					}
				}
					catch(SQLException sq){
						System.out.println("SQL Error from Granded update of Granded service ");
						sq.printStackTrace();
					}
					catch (Exception e) {
						System.out.println(" Error from  Granded update of Granded service. ");
						e.printStackTrace();
					}
			 
			 }//end of Granded Update method
		
}///end of grand service class
