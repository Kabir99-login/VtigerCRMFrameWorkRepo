package genericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DatabaseUtility {
	
	public Connection getDbConnection(String url,String un, String pwd) throws Throwable 
	{
		Driver d=new Driver();
		DriverManager.registerDriver(d);
		return DriverManager.getConnection(url, un, pwd);
	}
	public boolean validateDataEntry(String tName, String cName, String data, String url,String un, String pwd) throws Throwable
	{
		Connection con=getDbConnection(url, un, pwd);
		Statement s=con.createStatement();
		return s.execute("Select * from "+tName+" where "+cName+"='"+data+"'");
		
	}
	
	public ResultSet readDataFromDb(String url,String un,String pwd,String query)throws Throwable 
	{
		Connection con=getDbConnection(url, un, pwd);
		Statement s = con.createStatement();
		return s.executeQuery(query);
	}
	
	public void closeConnection(Connection con) throws Throwable
	{
		con.close();
	}

}
