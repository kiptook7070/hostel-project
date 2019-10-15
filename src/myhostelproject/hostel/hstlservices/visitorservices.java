package myhostelproject.hostel.hstlservices;

import myhostelproject.hostel.hstlconnection.hstlconnection;
import myhostelproject.hostel.hstlmodels.visitormodels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class visitorservices {
	public Vector<Object> dfrow=new Vector<Object>();
	public Vector<Object> dcol=new Vector<Object>();
	Connection con=hstlconnection.getConnection();
	
	//=============================================combo box for current available student names
	public ComboBoxModel vistComb(){
		try{
			PreparedStatement ps=con.prepareStatement("select distinct stdname from students order by stdname ");
			ResultSet rs=ps.executeQuery();
			Vector<Object> cobval=new Vector<Object>();
			cobval.add("-Select-");
			while(rs.next()){
				int i=1;
				cobval.add(rs.getObject(i));
				i++;
			}
			rs.close();
			ps.close();
			con.close();
			return new DefaultComboBoxModel(cobval);
		}
		catch(SQLException se){
			System.out.println("SQL EXCEPTION in visitor combo OCCOURED...");
				se.printStackTrace();
	    }
	   catch(Exception e){
			System.out.println("EXCEPTION  in visitor combo OCCOURED..."+e);
	   }
		
		return null;
		
	}//================end of visitor combo box

	///-----------------------------------------------Inserting Visitor details-----------------------------
	public void vistIns(visitormodels vm){
		try{
			 
			PreparedStatement ps=con.prepareStatement("select vist_seri from hostelvisitors where vistname like ? and vistph like ? and viststudnam like ? and vistrelat like ? and  vistdate like ? and enterytm like ? and  leavtm like ? and vistpurps like ?");
			 ps.setString(1, vm.getVstnam());
			 ps.setLong(2,vm.getVstph());
			 ps.setString(3,vm.getVstudnam());
			 ps.setString(4,vm.getVstrelat());
			 ps.setString(5,vm.getVstdate());
			 ps.setString(6,vm.getVstEtme());
			 ps.setString(7,vm.getVstLtme());
			 ps.setString(8,vm.getVstpurp());
			 System.out.println(ps);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) JOptionPane.showMessageDialog(null, "Entered record Already Exist....!");
			else{
				ps=con.prepareStatement("insert into hostelvisitors(vistname,vistph,viststudnam,vistrelat,vistdate,enterytm,leavtm,vistpurps)values(?,?,?,?,?,?,?,?)");
				 ps.setString(1, vm.getVstnam());
				 ps.setLong(2,vm.getVstph());
				 ps.setString(3,vm.getVstudnam());
				 ps.setString(4,vm.getVstrelat());
				 ps.setString(5,vm.getVstdate());
				 ps.setString(6,vm.getVstEtme());
				 ps.setString(7,vm.getVstLtme());
				 ps.setString(8,vm.getVstpurp());
				 System.out.println(ps);
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "One Visitor Record Added!");
			}
		}
		catch(SQLException se){
					JOptionPane.showMessageDialog(null, "Data not Insert due to error in input.");
			System.out.println("SQL EXCEPTION in visitor Insert OCCOURED...");
				se.printStackTrace();
	    }
	   catch(Exception e){
			System.out.println("EXCEPTION  in visitor Insert OCCOURED..."+e);
	   }
	}//--------end of inserting visitor
	
	//====-------------------------------------Visitor Table View-----------------
	public Vector<Object> row;
	public Vector<Object> colnam;
	public TableModel vistTable(){
		
		try{
			PreparedStatement ps=con.prepareStatement("select * from hostelvisitors");
			ResultSet rs=ps.executeQuery();
			ResultSetMetaData rsmeta=rs.getMetaData();
			int colnum=rsmeta.getColumnCount();
			  colnam=new Vector<Object>();
			
			colnam.add("Serial Number");
			colnam.add("Visitor Name");
			colnam.add("Visitor Ph");
			colnam.add("Student to Visit");
			colnam.add("Releationship");
			colnam.add("Visit Date");
			colnam.add("Entry Time");
			colnam.add("Leaving Time");
			colnam.add("Visiting Purpose");
			
			row=new Vector<Object>();
			
			while(rs.next()){
				Vector<Object> onerow=new Vector<Object>();
				for(int i=1;i<=colnum;i++) onerow.add(rs.getObject(i));
			 row.add(onerow);
			}
			return new DefaultTableModel(row,colnam) {
				public boolean isCellEditable(int row, int column) {//user cannot edit table but can select rows or cells 
					return false;
				}
			};
		}
		
		catch(SQLException se){
			System.out.println("SQL EXCEPTION in visitor Table service OCCOURED...");
			se.printStackTrace();
		}
		catch(Exception e){
			System.out.println("EXCEPTION  in visitor Table service OCCOURED..."+e);
			e.printStackTrace();
		}
		return null;
	
   }//end of visit table views
	
	////=======================---------------------Visitor Edit Ok mehtod
	public visitormodels vistOk(visitormodels vm) {
		try {
		
			PreparedStatement ps=con.prepareStatement("select vistname,vistph,viststudnam,vistrelat,vistdate,leavtm,vistpurps from hostelvisitors where vist_seri=?");
			ps.setInt(1,vm.getVstslno());
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				vm.setVstnam(rs.getString(1));
				vm.setVstph(rs.getLong(2));
				vm.setVstudnam(rs.getString(3));
				vm.setVstrelat(rs.getString(4));
				vm.setVstdate(rs.getString(5));
				vm.setVstLtme(rs.getString(6));
				vm.setVstpurp(rs.getString(7));
				vm.setVstflag(1);
			}
			return vm;
		}
		catch(SQLException se){
			JOptionPane.showMessageDialog(null, "Error in visitor serial number input.");
			System.out.println("SQL EXCEPTION in visitor Edit OK OCCOURED...");
			se.printStackTrace();
		}
		catch(Exception e){
			System.out.println("EXCEPTION  in visitor Edit OK OCCOURED..."+e);
			e.printStackTrace();
		}
		return null;
	}///end of visit ok
	
	///////////////=-----------------------Visitor edit delete service
	public void vistDel(visitormodels vm){
		try {
			int c=JOptionPane.showConfirmDialog(null,"Are you sure to delete a Visitor Record","Confirm",JOptionPane.YES_NO_OPTION);
			if(c==0) {
				PreparedStatement ps=con.prepareStatement("delete from hostelvisitors where vist_seri=?");
				ps.setInt(1,vm.getVstslno());
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "One Visitor Record Deleted....!");
			}
			else JOptionPane.showMessageDialog(null, "Watch out what you are clicking....!");
		}
		catch(SQLException sq){
			System.out.println("SQL Error from visitor delete of visitor service ");
			sq.printStackTrace();
		}
		catch (Exception e) {
			System.out.println(" Error from  visitor delete of visitor service. ");
			e.printStackTrace();
		}
		
	}//====-----end of visitor delete service
	
	//===========================------------------------Visitor Edit service Update a Record===================
	public void vistUpdate(visitormodels vm) {
		int c=JOptionPane.showConfirmDialog(null,"Are you sure to delete a Visitor Record","Confirm",JOptionPane.YES_NO_OPTION);
		if(c==0) {
			try {
				PreparedStatement ps=con.prepareStatement("select vist_seri from hostelvisitors where vistname like ? and vistph like ? and viststudnam like ? and vistrelat like ? and  vistdate like ? and  leavtm like ? and vistpurps like ?");
				 ps.setString(1, vm.getVstnam());
				 ps.setLong(2,vm.getVstph());
				 ps.setString(3,vm.getVstudnam());
				 ps.setString(4,vm.getVstrelat());
				 ps.setString(5,vm.getVstdate());
				 ps.setString(6,vm.getVstLtme());
				 ps.setString(7,vm.getVstpurp());
				 System.out.println(ps);
				ResultSet rs=ps.executeQuery();
				if(rs.next()) JOptionPane.showMessageDialog(null, "Entered record Already Exist....!");
				else{
					ps=con.prepareStatement("update hostelvisitors set vistname=?,vistph=?,viststudnam=?,vistrelat=?,vistdate=?,leavtm=?,vistpurps=? where vist_seri=?");
					ps.setString(1, vm.getVstnam());
					 ps.setLong(2,vm.getVstph());
					 ps.setString(3,vm.getVstudnam());
					 ps.setString(4,vm.getVstrelat());
					 ps.setString(5,vm.getVstdate());
					 ps.setString(6,vm.getVstLtme());
					 ps.setString(7,vm.getVstpurp()); 
					 ps.setInt(8, vm.getVstslno());
					 System.out.println("Update Visitor query"+ps);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "One Visitor Record Updated....!");
				}
			}
			catch(SQLException sq){
				System.out.println("SQL Error from visitor Update of visitor service ");
				sq.printStackTrace();
			}
			catch (Exception e) {
				System.out.println(" Error from  visitor Update of visitor service. ");
				e.printStackTrace();
			}
		}
		else JOptionPane.showMessageDialog(null, "Watch out what you Click...!");
		
	}//====end of visitor update service
	
}///=============end of visit services