package com.waterSample.DBConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.waterSample.Constants.Constants;

/*
 * @author : Louis Arokiaraj Gilbert
 * This Class encapsulates the variables and functions to establish connection with DB
 */

public class DBConnection {

	static final String DB_URL = "jdbc:mysql://localhost:3306/"+Constants.DATABASE_NAME+"?autoReconnect=true&useSSL=false";
	static final String USER = Constants.DATABASE_USERNAME;
	static final String PASS = Constants.DATABASE_PASSWORD;
	Connection conn = null;
	Statement stmt = null;

	/*
	 * Function to establish connection with the DB
	 */

	public Statement getConnection(){
		try{
			Class.forName(Constants.JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
		}catch(Exception e){
			e.printStackTrace();
		}
		return stmt;
	}

	/*
	 * Function to close the connection with the DB
	 */
	
	public void closeConnection(){
		try{
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
