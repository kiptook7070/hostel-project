package myhostelproject.hostel.hstlservices;

import myhostelproject.hostel.hstlconnection.hstlconnection;
import myhostelproject.hostel.hstlmodels.grandmodels;
import myhostelproject.hostel.hstlmodels.roommodels;

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

import com.mysql.jdbc.Statement;

public class roomservices {
Connection con=hstlconnection.getConnection();

////////////////////////////////////////////////////room view method or Display////////////////////////////////
	public TableModel rmview(){
		try{
			PreparedStatement ps=con.prepareStatement("select * from hstlroom order by 1");
			ResultSet rs=ps.executeQuery();
			ResultSetMetaData rsmeta=rs.getMetaData();
			
			int colnum=rsmeta.getColumnCount();
			Vector<String> colnam=new Vector<String>();
			colnam.add("Room_id");
			colnam.add("Block Name");
			colnam.add("Floor Number");
			colnam.add("Room Number");
			colnam.add("Total Bed");
			colnam.add("Room Status");
			
			/*for(int count=1;count<=colnum;count++) colnam.addElement(rsmeta.getColumnLabel(count));*/////getting column names.
			
			///get all rows
			Vector<Object> rows=new Vector<Object>();
			
			while(rs.next()){
				Vector<Object> onerow=new Vector<Object>();
				for(int i=1;i<=colnum;i++) onerow.add(rs.getObject(i));
				rows.add(onerow);
			}
			rs.close();
			ps.close();
			con.close();
			return new DefaultTableModel(rows,colnam) {
				public boolean isCellEditable(int row, int column) {//user cannot edit table but can select rows or cells 
				return false;}
			};
		}
		catch(SQLException sq){
			JOptionPane.showMessageDialog(null, "No Records to show, Records may be empty");
			System.out.println("Viewing Block data SQL error " );
			System.err.println("SQl execption Occoured....?");
		}
		catch(Exception e){
			System.out.println("Viewing Block data error " );
			System.err.println("Exception occoured...."+e);
		}
	return null;
	}//////////////////end of view methods

	
	////////////////////////////////////////////////////combobox feches block names and diplay method/////////////////////////
	String str;
	public ComboBoxModel comboviewBlkn(){
		try{
			
			
			
				//System.out.println("In the combo block num service s= "+s);
				Statement st=(Statement) con.createStatement();
				ResultSet rs=st.executeQuery("select distinct blokname from hostelblocks order by 1");
				Vector<Object> coldat=new Vector<Object>();
			
				coldat.add("-Select-");
				while(rs.next()){
						int i=1;
					coldat.add(rs.getObject(i++));
						
				}
			//if received str is not select the call combofloor method
							
				rs.close();
				st.close();
				con.close();
				return new DefaultComboBoxModel(coldat);
		}
		catch(SQLException se){
				System.out.println("SQL EXCEPTION in combo OCCOURED...");
					se.printStackTrace();
		}
		catch(Exception e){
				System.out.println("EXCEPTION  in room block combo OCCOURED..."+e);
		}
		return null;
	}///end of combobox block name fech
	
//============================================================ROOM INSERTION METHOD==================================================
	
	public void roomInsert(roommodels rm){
		try{
			//SELECT flor_id FROM hstlfloor WHERE blokname like 'akc' and flornum LIKE 1 AND florname LIKE  'florak' AND florstatus LIKE  'Available'
			PreparedStatement ps=con.prepareStatement("select rm_id from hstlroom where rmBlkname like ? and rmFlrnum like ? and rmnum like ? and rmtotbed like ? and rmstatus like ? ");
			ps.setString(1,rm.getRmblknam());
			ps.setInt(2,rm.getRmflrnum());
			ps.setString(3, rm.getRmnum());
			ps.setInt(4, rm.getRmtotbd());
			ps.setString(5,rm.getRmstat());
			
			ResultSet rs=ps.executeQuery();
			// if record already exists then do not insert. else insert
			if(rs.next())JOptionPane.showMessageDialog(null,"Entered room record already exists...!");
			else{						///////			flor_id	blokname	flornum	florname	florstatus
				ps=con.prepareStatement("insert into hstlroom(rmBlkname,rmFlrnum,rmnum,rmtotbed,rmstatus)values(?,?,?,?,?)");
				ps.setString(1,rm.getRmblknam());
				ps.setInt(2,rm.getRmflrnum());
				ps.setString(3, rm.getRmnum());
				ps.setInt(4, rm.getRmtotbd());
				ps.setString(5,rm.getRmstat());
				
				ps.executeUpdate();
				rs=ps.getGeneratedKeys();
				if(rs.next())JOptionPane.showMessageDialog(null, "ONE room record Inserted....!!!");
				else JOptionPane.showMessageDialog(null, "Not inserted....!!!");
			}
			rs.close();
			ps.close();
			con.close();
		}
		catch(SQLException sq){
			System.out.println("SQL ERROR FROM Insert floor record...?");
			sq.printStackTrace();
		}
		catch(Exception e){
			System.out.println("ERROR FROM Insert floor record...?");
			e.printStackTrace();
		}
	}
	
////===============================================method for Edit Ok buttons=====================================================
	public roommodels editOk(roommodels rm){
		try{
			PreparedStatement ps=con.prepareStatement("select rmBlkname,rmFlrnum,rmnum,rmtotbed,rmstatus from hstlroom where rm_id=?");
			ps.setInt(1,rm.getRm_id());
			ResultSet rs=ps.executeQuery();
			if(!rs.isBeforeFirst()&&rs.getRow()==0) {
				JOptionPane.showMessageDialog(null, "No Record Found For Entered Room_ID..!");
				rm.setRmflag(0);
				return rm;
			}
			else{
				while(rs.next()){
					rm.setRmblknam(rs.getString(1));
					rm.setRmflrnum(rs.getInt(2));
					rm.setRmnum(rs.getString(3));
					rm.setRmtotbd(rs.getInt(4));
					rm.setRmstat(rs.getString(5));
				}
			return rm;
			}
			
		}
		catch(SQLException sq3){
			System.out.println("SQL ERROR OCCOURED in Edit OK submitt...!");
			sq3.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("Exception occoured in Edit Ok Submitt");
			e.printStackTrace();
		}
		return null;
		
	}///////////////end of edit ok submit method
	
//==============================================================/hstl edit Room Delete method/==========================================
	public void rmdelete(roommodels rm){
		int x;
		x=JOptionPane.showConfirmDialog(null,"Are you sure to delete a Record ","Confirm Delete",JOptionPane.YES_NO_OPTION);
		if(x==0){
			try{
				PreparedStatement ps=con.prepareStatement("delete from hstlroom where rm_id=?");
				ps.setInt(1,rm.getRm_id());
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Deleted one Room record...!!!");
			}
			catch(SQLException sq){
				System.out.println("SQL Error from Room from roomservices. ");
				sq.printStackTrace();
			}
			catch (Exception e) {
				System.out.println(" Error from from Room from roomservices. ");
				e.printStackTrace();
			}
		}
		else JOptionPane.showMessageDialog(null, "Watch out what you are clicking...!@#@&^*&");		
		
	}//////////////////end of Room Delete method
	
//====================================================================ROOM UPDATE method=================================================/
public void rmUpdate(roommodels rm){
	int x;
	System.out.println("In the room update Service method ");
	x=JOptionPane.showConfirmDialog(null,"Are you sure to Update the Record ","Confirm Delete",JOptionPane.YES_NO_OPTION);
	try{
			if(x==0){
					/////////checking record already exists.
						System.out.println("In the room update already check Service method... ");
						PreparedStatement ps=con.prepareStatement("select rm_id from hstlroom where rmBlkname like ? and rmFlrnum like ? and rmnum like ? and rmtotbed like ? and  rmstatus  like ?");
							ps.setString(1,rm.getRmblknam());
							ps.setInt(2,rm.getRmflrnum());
							ps.setString(3, rm.getRmnum());
							ps.setInt(4, rm.getRmtotbd());
							ps.setString(5,rm.getRmstat());
						ResultSet rs=ps.executeQuery();
						if(rs.next())JOptionPane.showMessageDialog(null, "Record already exists....");///if record exists then print message.
						else{
							System.out.println("In the floor update Service method,   checked if already exists, about to update. ");
							ps=con.prepareStatement("update hstlroom set rmBlkname=?,rmFlrnum=?,rmnum=?,rmtotbed=?,rmstatus=? where rm_id=?");
							 ps.setString(1,rm.getRmblknam());
							 ps.setInt(2,rm.getRmflrnum());
							 ps.setString(3, rm.getRmnum());
							 ps.setInt(4, rm.getRmtotbd());
							 ps.setString(5,rm.getRmstat());
							 ps.setInt(6,rm.getRm_id());

							System.out.println(ps);
							ps.executeUpdate();
							JOptionPane.showMessageDialog(null, "One Record updated....!");
						}
			}
			else JOptionPane.showMessageDialog(null, "Watch out what you are clicking....!@#@&^*&");
		}

		catch(SQLException sq){
		JOptionPane.showMessageDialog(null, "Record not updated due to Error in Input");	
		sq.printStackTrace();
		}
		catch(Exception e){
		System.out.println("Error occured"+e);
		}
	}//////////////////////end of Update method

///--------------------fetching floor name for combo box items  -------------------------
public ArrayList<String> getrmfloornames(roommodels rm) {
	ArrayList<String> fn1=new ArrayList<String>();		///arrray list
	String s=new String("-Select-");
	fn1.add(s);
	try {
		PreparedStatement ps=con.prepareStatement("select distinct flornum from hstlfloor where flrblokname=? order by 1");
		ps.setString(1,rm.getRmblknam());
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			s=String.valueOf(rs.getInt(1));
			fn1.add(s);
		}
		 rs.close();
		ps.close();
		con.close();   
		return fn1;
	}
	catch(SQLException se){
		System.out.println("SQL EXCEPTION in room    OCCOURED..."+se);
		se.printStackTrace();
	}
	catch(Exception e){
	System.out.println("EXCEPTION  in room    OCCOURED..."+e);
	}
		 return null;
}///end of fetching floor name combo box items 

	
}////end of room services class.
