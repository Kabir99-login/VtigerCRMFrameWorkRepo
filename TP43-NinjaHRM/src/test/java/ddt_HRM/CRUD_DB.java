package ddt_HRM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class CRUD_DB {

	public static void main(String[] args) throws Throwable
	{
		Driver d=new Driver();
		
		DriverManager.registerDriver(d);
		Connection con=DriverManager.getConnection("jdbc:mysql://49.249.29.4:3307/ninza_hrm", "root@%", "root");
		
		Statement st=con.createStatement();
		
		//execute
	
//		st.execute("create table TEKPYD1 (name VARCHAR(20));");
//		st.execute("insert into TEKPYD1 values('asd');");
//		
//		boolean status = st.execute("select * from TEKPYD1;");
//		if(status==true)
//			System.out.println("Table created");
//		else 
//			System.out.println("Table not created");
		
		//ResultSet set = st.executeQuery("select 'project_id', 'createdBy'from project;");
		ResultSet set = st.executeQuery("select * from project;");
		while(set.next())
		{
			System.out.println("PID:"+set.getString(1)+" CreatedBy: "+set.getString(2)+" CreatedOn: "+set.getString(3)+" PName:"+set.getString(4)+" Status: "+set.getString(5)+" TeamSize: "+set.getString(6));
	        		
		}
		st.executeUpdate("insert into project values('NH_PROJ_907','manju','01/09/2026','bank','created','2')");
		
		
		con.close();
		



	}

}
