package myhostelproject.hostel.hstlservices;

import myhostelproject.hostel.hstlconnection.hstlconnection;
import myhostelproject.hostel.hstlmodels.floormodels;
import myhostelproject.hostel.hstlmodels.studentmodels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class studentservices {
	Connection con=hstlconnection.getConnection();
	
	//================================================View Student table==============================
	public DefaultTableModel studView(){
		try{
			PreparedStatement ps=con.prepareStatement("select * from students where 1");//to see in student alphabetical order 
			ResultSet rs=ps.executeQuery();
			ResultSetMetaData rsmeta=rs.getMetaData();
			
			int colcnt=rsmeta.getColumnCount();
			///column names
			Vector<Object> colname=new Vector<Object>();
				colname.add("Student Id");
				colname.add("Name");
				colname.add("Gender");
				colname.add("Dob");
				colname.add("Address");
				colname.add("Phone_No");
				colname.add("e-mail");
				colname.add("Parent Name");
				colname.add("Prnt Ph");
				colname.add("Prnt e-mail");
				colname.add("Stdnt Status");
				
				//get all rows
			Vector<Object> rows=new Vector<Object>(); 
			
			while(rs.next()){
				Vector<Object> onerow=new Vector<Object>();
				 for(int i=1;i<=colcnt;i++) onerow.add(rs.getObject(i));
				rows.add(onerow);
			}
				return new DefaultTableModel(rows,colname) {
					public boolean isCellEditable(int row, int column) {//user cannot edit table but can select rows or cells 
					return false;
					}
				};	
		}
		catch(SQLException sq1){
			System.out.println("SQL EXception occoured in student table diplay "+sq1);
			sq1.printStackTrace();
		}
		catch (Exception e1) {
			System.out.println("EXception occoured in student table diplay "+e1);
			e1.printStackTrace();
		}
		return null;
	}//================================end of student table view==============
	
//===================================================================method for ADDING OR submitting Student Record===================
	public void  addStud(studentmodels sm){
		try{
			PreparedStatement ps=con.prepareStatement("select std_id from students where stdname like ? and stdgend like ? and stddob like ? and stdaddr like ? and stdph like ? and stdemail like ? and stdparnam like ? and stdparph like ? and stdparem like ? and stdstat like ? ");
			    ps.setString(1,sm.getStudNam());
	        	ps.setString(2,sm.getStudGend());
			    ps.setString(3,sm.getStdDob());
			    ps.setString(4,sm.getStudAddr());
			    ps.setLong(5,sm.getStudPh());
			    ps.setString(6,sm.getStudEml());
			    ps.setString(7,sm.getParntname());
			    ps.setLong(8,sm.getParPh());
			    ps.setString(9,sm.getParEml());
			    ps.setString(10,sm.getStudStat());
			    System.out.println(ps);
		  ResultSet rs=ps.executeQuery();
		  if(rs.next()){
			  JOptionPane.showMessageDialog(null, "Entered record Already Exist....!");
			  sm.setStdfflag(0);
		  }	
		  else{
			  ps=con.prepareStatement("insert into students(stdname,stdgend,stddob,stdaddr,stdph,stdemail,stdparnam,stdparph,stdparem,stdstat)values(?,?,?,?,?,?,?,?,?,?)");
			    ps.setString(1,sm.getStudNam());
	        	ps.setString(2,sm.getStudGend());
			    ps.setString(3,sm.getStdDob());
			    ps.setString(4,sm.getStudAddr());
			    ps.setLong(5,sm.getStudPh());
			    ps.setString(6,sm.getStudEml());
			    ps.setString(7,sm.getParntname());
			    ps.setLong(8,sm.getParPh());
			    ps.setString(9,sm.getParEml());
			    ps.setString(10,sm.getStudStat());
		    
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Student Record Inserted Successfully...!!!"); 
		  }
		}
		catch(SQLException se){
			JOptionPane.showMessageDialog(null, "Enter corresponding numbers in phone number field...!");
			System.out.println("Sql Exception Occoured in Student Insert....!"+se);
			se.printStackTrace();
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No entry must be Left alone, Enter values...!");
			System.out.println("Exception Occoured in Student Insert....!"+e);
			e.printStackTrace();
		}
	}//////End of Student insert method..
	
	//==============================================STUDENT EDIT ID OK BUTTON FUNCTION====================================//
	public studentmodels editidOk(studentmodels sm){
		try{
			
			PreparedStatement ps=con.prepareStatement("select stdname,stdgend,stddob,stdaddr,stdph,stdemail,stdparnam,stdparph,stdparem,stdstat from students where  std_id=?");
			ps.setInt(1, sm.getStdId());
			ResultSet rs=ps.executeQuery();
			if(!rs.isBeforeFirst()&&rs.getRow()==0){
				JOptionPane.showMessageDialog(null, "NO Student Record is Available..!");
				sm.setStdfflag(0);
				return sm;
			}
			else{
				while(rs.next()){
					sm.setStudNam(rs.getString(1));	
					sm.setStudGend(rs.getString(2));
					sm.setStdDob(rs.getString(3));
					sm.setStudAddr(rs.getString(4));
					sm.setStudPh(rs.getLong(5));
					sm.setStudEml(rs.getString(6));
					sm.setParntname(rs.getString(7));;
					sm.setParPh(rs.getLong(8));
					sm.setStudStat(rs.getString(10));
					
				}
				
				sm.setStdfflag(1);
				return sm;
			}
		}
		catch(SQLException se){ 
			
			System.out.println("SQl Exception from method for button OK of studentedit Panel");
			se.printStackTrace();
		}	
		catch (Exception e) {
			System.out.println("Exception from method for button OK of studentedit Panel");
			e.printStackTrace();
		}
		return null;
	}/////end of student edit botton ok method
	
	//-----------------------------------==STUDENT RECORD DELETE---------------------------------------------
	public studentmodels studDel(studentmodels sm){
		int x;
		x=JOptionPane.showConfirmDialog(null,"Are you sure to delete a Record ","Confirm Delete",JOptionPane.YES_NO_OPTION);
		if(x==0){
			try{
				PreparedStatement ps=con.prepareStatement("delete from hstlstud where std_id=?");
				ps.setInt(1,sm.getStdId());
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Deleted one STUDENT record...!!!");
				
			}
			catch(SQLException sq){
				System.out.println("SQL Error from student delete of studentservice. ");
				sq.printStackTrace();
			}
			catch (Exception e) {
				System.out.println(" Error from  Student Delete of studentservice. ");
				e.printStackTrace();
			}
		}
		else JOptionPane.showMessageDialog(null, "Watch out what you are clicking...!@#@&^*&");		
		
		return null;
				
	}//=====----------------------------------END DELETE STUDENT RECORD 
	
////////////////////////////////////////////////Student UPDATE method////////////////////////////////////////////////////
	public void stuUpdate(studentmodels sm){
		int x;
		System.out.println("In the Student update Service method ");
		x=JOptionPane.showConfirmDialog(null,"Are you sure to Update the Record ","Confirm Delete",JOptionPane.YES_NO_OPTION);
		try{
			if(x==0){
				/////////checking record already exists.
				System.out.println("In the Student update already check Service method... ");
				PreparedStatement ps=con.prepareStatement("select std_id from students where stdname like ? and stdgend like ? and stddob like ? and stdaddr like ? and stdph like ? and stdemail like ? and stdparnam like ? and stdparph like ? and stdparem like ? and stdstat like ? ");
			    ps.setString(1,sm.getStudNam());
	        	ps.setString(2,sm.getStudGend());
			    ps.setString(3,sm.getStdDob());
			    ps.setString(4,sm.getStudAddr());
			    ps.setLong(5,sm.getStudPh());
			    ps.setString(6,sm.getStudEml());
			    ps.setString(7,sm.getParntname());
			    ps.setLong(8,sm.getParPh());
			    ps.setString(9,sm.getParEml());
			    ps.setString(10,sm.getStudStat());
			    System.out.println(ps);
			    ResultSet rs=ps.executeQuery();
				if(rs.next())JOptionPane.showMessageDialog(null, "Record already exists....");///if record exists then print message.
				else{
					System.out.println("In the Student update Service method,   checked if already exists, about to update. ");
					ps=con.prepareStatement("UPDATE  students SET  stdname=?,stdgend=?,stddob=?,stdaddr=?,stdph=?,stdemail=?,stdparnam=?,stdparph=?,stdparem=?,stdstat=? WHERE std_id=?");
				     ps.setString(1,sm.getStudNam());
		        	 ps.setString(2,sm.getStudGend());
				     ps.setString(3,sm.getStdDob());
				     ps.setString(4,sm.getStudAddr());
				     ps.setLong(5,sm.getStudPh());
				     ps.setString(6,sm.getStudEml());
				     ps.setString(7,sm.getParntname());
				     ps.setLong(8,sm.getParPh());
				     ps.setString(9,sm.getParEml());
				     ps.setString(10,sm.getStudStat());
				     ps.setInt(11,sm.getStdId());
			    ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Student Record Inserted Successfully...!!!"); 
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

}/////====================================END OF STUDENT SERVICES.
