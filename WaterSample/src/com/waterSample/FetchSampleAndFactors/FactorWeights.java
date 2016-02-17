package com.waterSample.FetchSampleAndFactors;
import java.sql.ResultSet;
import java.sql.Statement;

import com.waterSample.Constants.Constants;
import com.waterSample.DBConnection.DBConnection;

/*
 * @author : Louis Arokiaraj Gilbert
 * This Class defines the variables and functions related to Factor Weights Table Data
 */

public class FactorWeights {
	DBConnection connObj = new DBConnection();
	Statement stmt = null;
	public int id;
	public double chloroform_weight;
	public double bromoform_weight;
	public double bromodichloromethane_weight;
	public double dibromichloromethane_weight;

	/*
	 *  Constructor to initialize the variables
	 */

	public FactorWeights(){
		this.id=0;
		this.chloroform_weight=0;
		this.bromodichloromethane_weight=0;
		this.bromoform_weight=0;
		this.dibromichloromethane_weight=0;
	}

	/*
	 * @param : factor_weight_id
	 * Function to fetch the values from Factor Weights Tables w.r.t given ID
	 */

	public void factorFind(int factorWeightID,boolean excpFlag) throws Exception{
		try{
			stmt = connObj.getConnection();
			String sql;
			sql = "SELECT "+Constants.ID+", "+Constants.CHLOROFORM_WEIGHT+", "+Constants.BROMOFORM_WEIGHT+", "+Constants.BROMODICHLOROMETHANE_WEIGHT+", "+Constants.DIBROMICHLOROMETHANE_WEIGHT+" FROM "+Constants.FACTOR_WEIGHT_TABLE_NAME+" WHERE id="+factorWeightID;
			ResultSet rs = stmt.executeQuery(sql);	
			while(rs.next()){
				this.id = rs.getInt(Constants.ID);
				this.chloroform_weight = rs.getDouble(Constants.CHLOROFORM_WEIGHT);
				this.bromoform_weight = rs.getDouble(Constants.BROMOFORM_WEIGHT);
				this.bromodichloromethane_weight = rs.getDouble(Constants.BROMODICHLOROMETHANE_WEIGHT);
				this.dibromichloromethane_weight = rs.getDouble(Constants.DIBROMICHLOROMETHANE_WEIGHT);
			}
			if((this.chloroform_weight < 0 || this.bromoform_weight < 0 || this.bromodichloromethane_weight < 0 || this.dibromichloromethane_weight < 0) && excpFlag == false){
				throw new Exception("Individual factor_weight values cannot be negative NOT VALID !!");
			}
			if(this.id ==0 && this.chloroform_weight == 0 && this.bromoform_weight == 0 && this.bromodichloromethane_weight == 0 && this.dibromichloromethane_weight ==0){
				throw new Exception("No Valid Record Found in Factor Weights Table !!");
			}
			rs.close();
			connObj.closeConnection();
		}catch(Exception e){
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
}
