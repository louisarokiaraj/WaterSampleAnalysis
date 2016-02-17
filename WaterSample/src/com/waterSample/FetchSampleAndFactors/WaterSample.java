package com.waterSample.FetchSampleAndFactors;
import java.sql.*;

import com.waterSample.Constants.Constants;
import com.waterSample.DBConnection.DBConnection;

/*
 * @author : Louis Arokiaraj Gilbert
 * This Class defines the variables and functions related to Water Sample Table Data
 */

public class WaterSample {
	Statement stmt = null;
	public int id;
	public String site;
	public double chloroform;
	public double bromoform;
	public double bromodichloromethane;
	public double dibromichloromethane; 

	/*
	 * Constructor to initialize the variables
	 */

	public WaterSample(){
		this.id = 0;
		this.site = null;
		this.chloroform = 0;
		this.bromoform = 0;
		this.bromodichloromethane = 0;
		this.dibromichloromethane = 0;
	}

	/*
	 * @param : Sample ID 
	 * Function to fetch the values from Water Sample Tables w.r.t given ID
	 */
	public void find(int sampleID) throws Exception{
		try{
			DBConnection connObj = new DBConnection();
			stmt = connObj.getConnection();
			String sql;
			sql = "SELECT "+Constants.ID+", "+Constants.SITE+", "+Constants.CHLOROFORM+", "+Constants.BROMOFORM+", "+Constants.BROMODICHLOROMETHANE+" ,"+Constants.DIBROMICHLOROMETHANE+" FROM "+Constants.SAMPLE_WEIGHTS_TABLE_NAME+" WHERE id="+sampleID;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				this.id = rs.getInt(Constants.ID);
				this.site = rs.getString(Constants.SITE);
				this.chloroform = rs.getDouble(Constants.CHLOROFORM);
				this.bromoform = rs.getDouble(Constants.BROMOFORM);
				this.bromodichloromethane = rs.getDouble(Constants.BROMODICHLOROMETHANE);
				this.dibromichloromethane = rs.getDouble(Constants.DIBROMICHLOROMETHANE);
			}
			if(this.chloroform < 0 || this.bromoform < 0 || this.bromodichloromethane < 0 || this.dibromichloromethane < 0){
				throw new Exception("Individual water sample content values cannot be negative.!! NOT VALID !! ");
			}
			if(this.chloroform+this.bromoform+this.bromodichloromethane+this.dibromichloromethane > 0.080){
				throw new Exception("their collective concentration exceeds .080 mg/L,record DELETED from table !! BAD WATER SAMPLE");
			}
			if(this.site == null && this.chloroform == 0 && this.bromoform == 0 && this.bromodichloromethane == 0 && this.dibromichloromethane ==0 && this.id ==0){
				throw new Exception("No Valid Record Found in WaterSample Table!!");
			}
			rs.close();
			connObj.closeConnection();
		}catch(SQLException e){
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
}
