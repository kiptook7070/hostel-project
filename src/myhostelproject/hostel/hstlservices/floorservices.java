package myhostelproject.hostel.hstlservices;

import myhostelproject.hostel.hstlconnection.hstlconnection;
import myhostelproject.hostel.hstlmodels.blockmodels;
import myhostelproject.hostel.hstlmodels.floormodels;

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

import com.mysql.jdbc.Statement;

public class floorservices {
	
	
	
	public floormodels editidOk(floormodels fm){
		try{
			
			PreparedStatement ps=con.prepareStatement("select flrblokname,flornum,florname,florstatus from hstlfloor where flor_id=? ");
			ps.setInt(1, fm.getFlrid());
			ResultSet rs=ps.executeQuery();
			if(!rs.isBeforeFirst()&&rs.getRow()==0){
				
				fm.setFlag(0);
				return fm;
			}
			else{
				while(rs.next()){
					fm.setBlknam(rs.getString(1));
					fm.setFlrnum(rs.getInt(2));
					fm.setFlrnam(rs.getString(3));
					fm.setFlrstat(rs.getString(4));
				}
				fm.setFlag(1);
				return fm;
			}
		}
		catch(SQLException se){ 
			
			System.out.println("SQl Exception from method for button OK of floor edit Panel");
			se.printStackTrace();
		}	
		catch (Exception e) {
			System.out.println("Exception from method for button OK of floor edit Panel");
			e.printStackTrace();
		}
		return null;
	}
	
	
	Connection con=hstlconnection.getConnection();
		
		public ComboBoxModel comboview(){
			try{
				Statement st=(Statement) con.createStatement();
				ResultSet rs=st.executeQuery("select distinct blokname from hostelblocks order by 1");
				Vector<Object> coldat=new Vector<Object>();
	            
				coldat.add("-Select-");
				while(rs.next()){
					int i=1;
					coldat.add(rs.getObject(i++));
				}
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
				System.out.println("EXCEPTION  in combo OCCOURED..."+e);
			}
			return null;
		}///end of combobox fech
		
		
		
	public TableModel florview(){
		try{
			PreparedStatement ps=con.prepareStatement("select * from hstlfloor order by florname");
			ResultSet rs=ps.executeQuery();
			ResultSetMetaData rmetad=rs.getMetaData();
			
			int numcolms=rmetad.getColumnCount();
			Vector<String> colname=new Vector<String>();
			
			for(int colm=1;colm<=numcolms;colm++)colname.addElement(rmetad.getColumnLabel(colm));
			
			
			Vector<Vector<Object>> rows=new Vector<Vector<Object>>();
			
			while(rs.next()){
				Vector<Object> onerow=new Vector<Object>();
				for(int i=1;i<=numcolms;i++) onerow.add(rs.getObject(i));
				rows.add(onerow);
			}
			rs.close();
			ps.close();
			con.close();
			
				
			return new DefaultTableModel(rows,colname) {
				public boolean isCellEditable(int row, int column) {//user cannot edit table but can select rows or cells 
					return false;
				}
			}; 
		}
		catch(SQLException sql){
			JOptionPane.showMessageDialog(null, "No Records to show, Records may be empty");
			System.out.println("SQL error from View FLOOR");
			sql.printStackTrace();
		}
		
		catch(Exception e){
			System.out.println("Error from View FLOOR");
			e.printStackTrace();
			
		}
		return null;
	}////END OF FLOOR TABLE VIEW METHOD
	
	//////////////////////////////////////////////////////////////////floor data INSERTION method//////////////////////////
	public void flrInsert(floormodels fm){
		try{
			System.out.println(fm.getBlknam());
			
			//SELECT flor_id FROM hstlfloor WHERE blokname like 'akc' and flornum LIKE 1 AND florname LIKE  'florak' AND florstatus LIKE  'Available'
			PreparedStatement ps=con.prepareStatement("select flor_id from hstlfloor where flrblokname like ? and flornum like ? and florname like ? and florstatus like ?");
			ps.setString(1,fm.getBlknam());
			ps.setInt(2,fm.getFlrnum());
			ps.setString(3, fm.getFlrnam());
			ps.setString(4, fm.getFlrstat());
			
			ResultSet rs=ps.executeQuery();
				// if record already exists the did not insert. else insert
			if(rs.next())JOptionPane.showMessageDialog(null,"Entered Floor record already exists...!");
			else{						///////			flor_id	blokname	flornum	florname	florstatus
				ps=con.prepareStatement("insert into hstlfloor(flrblokname,flornum,florname,florstatus)values(?,?,?,?)");
				ps.setString(1,fm.getBlknam());
				ps.setInt(2,fm.getFlrnum());
				ps.setString(3, fm.getFlrnam());
				ps.setString(4, fm.getFlrstat());
				
				ps.executeUpdate();
				rs=ps.getGeneratedKeys();
				if(rs.next())JOptionPane.showMessageDialog(null, "ONE floor record Inserted....!!!");
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
	}/////////////////////end of floor Insert method
	
//////////////////////////////////////////////////////Floor Delete method//////////////////////////////////////////////////////////////
	public void flrdelete(floormodels fm){
		int x;
		x=JOptionPane.showConfirmDialog(null,"Are you sure to delete a Record ","Confirm Delete",JOptionPane.YES_NO_OPTION);
		if(x==0){
			try{
				PreparedStatement ps=con.prepareStatement("delete from hstlfloor where flor_id=?");
				ps.setInt(1,fm.getFlrid());
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Deleted one Floor record...!!!");
			}
			catch(SQLException sq){
				System.out.println("SQL Error from floor of Floorservice. ");
				sq.printStackTrace();
			}
			catch (Exception e) {
				System.out.println(" Error from  floor of Floorservice. ");
				e.printStackTrace();
			}
		}
		else JOptionPane.showMessageDialog(null, "Watch out what you are clicking...!@#@&^*&");		
		
	}//////////////////end of floor Delete method
	
	////////////////////////////////////////////////FLOOR UPDATE method////////////////////////////////////////////////////
	public void flrupdate(floormodels fm){
		int x;
		System.out.println("In the Floor update Service method ");
		x=JOptionPane.showConfirmDialog(null,"Are you sure to Update the Record ","Confirm Delete",JOptionPane.YES_NO_OPTION);
		try{
			if(x==0){
				/////////checking record already exists.
				System.out.println("In the Floor update already check Service method... ");
				PreparedStatement ps=con.prepareStatement("select flor_id from hstlfloor where flrblokname like ? and flornum like ? and florname like ? and  florstatus  like ?");
					ps.setString(1,fm.getBlknam());
					ps.setInt(2,fm.getFlrnum());
					ps.setString(3, fm.getFlrnam());
					ps.setString(4, fm.getFlrstat());
					ResultSet rs=ps.executeQuery();
					if(rs.next())JOptionPane.showMessageDialog(null, "Record already exists....");///if record exists then print message.
					else{
						System.out.println("In the floor update Service method,   checked if already exists, about to update. ");
						ps=con.prepareStatement("update hstlfloor set flrblokname=?,flornum=?,florname=?,florstatus=? where flor_id=?");
							ps.setString(1,fm.getBlknam());
							ps.setInt(2,fm.getFlrnum());
							ps.setString(3, fm.getFlrnam());
							ps.setString(4, fm.getFlrstat());
							ps.setInt(5,fm.getFlrid());
						 
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

}
