package databaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import interfaces.IDatabase;

public class ConnectionManager implements IDatabase{

	private static final String URL = "jdbc:mysql://localhost:3306/humanresources";
	private static final String USER = "root";
	private static final String PASSWORD = "nikeegurayy8";
	
	@Override
	public Connection connect(String url, String user, String password){
		Connection result = null;
		
		try{
			result = DriverManager.getConnection(url, user, password);
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public Connection link(){
		
		Connection link = connect(URL, USER, PASSWORD);
		if(link == null){
		      System.out.println("MySQL не е пуснат!");
		}
		
		return link;
	}

}
