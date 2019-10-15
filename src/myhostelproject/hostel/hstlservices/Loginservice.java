package myhostelproject.hostel.hstlservices;

import myhostelproject.hostel.hstlconnection.hstlconnection;
import myhostelproject.hostel.hstlmodels.logmodlin;
import myhostelproject.hostel.hstlmodels.logmodlin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



public class Loginservice {
	static Connection con=hstlconnection.getConnection();
	private static int ccnt;
	private static int rcnt=0;
	private static Vector<Object> rows;
	
	public static DefaultTableModel disply() {
		PreparedStatement ps;
		try {
				ps = con.prepareStatement("select * from login");
				ResultSet rs=ps.executeQuery();
				Vector<Object> colname=new Vector<Object>();
				colname.add("LogId");
				colname.add("UserName");
				colname.add("Password");
				colname.add("Status");
				
					ResultSetMetaData rmet=rs.getMetaData();	
					 ccnt=rmet.getColumnCount();
					 
				 rows=new Vector<Object>();
					while(rs.next()) {
						Vector<Object> onerow=new Vector<Object>();
						for(int i=1;i<=ccnt;i++) {
							onerow.add(rs.getObject(i));
							
						}
						rcnt++;
					rows.add(onerow);	
				}
			return new DefaultTableModel(rows,colname) {
					/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
					public boolean isCellEditable(int row, int column) {
						return false;
					}
					 public int getColumnCount() {
						return ccnt;
					}

					@Override
					public int getRowCount() {
						
						return rcnt;
					} 

					 @Override
					
					public void fireTableDataChanged() {
						super.fireTableDataChanged();
					}
					public void fireTableCellUpdated() {
						super.fireTableCellUpdated(getRowCount(), getColumnCount());
						
					}
				};	
			
		} catch (SQLException e) {
			System.out.println("Sql Exception in Table data retrival");
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static ArrayList<logmodlin> SlctAllLogin()
	{
		logmodlin lm=null;
		ArrayList<logmodlin> arr=new ArrayList<logmodlin>();
		try
		{
			System.out.println(" From login service.........!");
	PreparedStatement p=con.prepareStatement("SELECT * FROM login");
		ResultSet rs=p.executeQuery();
				while(rs.next())
				{
					lm=new logmodlin();
					lm.setId(rs.getInt(1));
					lm.setUnam(rs.getString(2));
				    lm.setPasswd(rs.getString(3));
				    lm.setStat(rs.getString(4));
				    
					arr.add(lm);
				}
				return arr;
				}
				catch (SQLException e) {
					System.out.println("Sql Exception in Table data retrival of SlctAllLogin in Service");
					e.printStackTrace();
				}
				return null;
		}
	
	
	public static void deleteLogin(int id) {
		try {
			PreparedStatement ps=con.prepareStatement("delete from login where id=?");
				ps.setInt(1,id);
			if(ps.executeUpdate()!=0)JOptionPane.showMessageDialog(null,"SuccessFully Deleted from DataBase..!","Success..!",JOptionPane.INFORMATION_MESSAGE);
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateLogin(logmodlin lm) {
		
		try {
			PreparedStatement ps=con.prepareStatement("update login set `name`=?,`password`=?,`status`=? where `id`=? ");
				ps.setString(1,lm.getUnam());
				ps.setString(2,lm.getPasswd());
				ps.setString(3,lm.getStat());
				ps.setInt(4,lm.getId());
			if(ps.executeUpdate()!=0)JOptionPane.showMessageDialog(null,"SuccessFully Updated record at DataBase..!","Success..!",JOptionPane.INFORMATION_MESSAGE);
				return;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
