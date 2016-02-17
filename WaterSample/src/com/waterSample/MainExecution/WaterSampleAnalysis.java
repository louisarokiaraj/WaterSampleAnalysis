package com.waterSample.MainExecution;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.waterSample.Constants.Constants;
import com.waterSample.DBConnection.DBConnection;
import com.waterSample.FetchSampleAndFactors.FactorWeights;
import com.waterSample.FetchSampleAndFactors.WaterSample;

/*
 * @author: Louis Arokiaraj Gilbert
 * This class calculates the Factor value and creates the HashMap  
 */

public class WaterSampleAnalysis {

	/*
	 * @param_1: WaterSample_Object
	 * @param_2: FactorWeights Object 
	 * Function to calculate the Factor value 
	 */

	public double calculateFactor(WaterSample water_obj,FactorWeights factor_obj){
		if(water_obj == null || factor_obj == null){
			return -1;
		}
		double factor_value = ((water_obj.chloroform*factor_obj.chloroform_weight)+(water_obj.bromoform*factor_obj.bromoform_weight)+(water_obj.bromodichloromethane*factor_obj.bromodichloromethane_weight)+(water_obj.dibromichloromethane*factor_obj.dibromichloromethane_weight));
		return factor_value;
	}

	/*
	 * @param_1: WaterSample_Object
	 * @param_2: True/False (to hash for all factors in Factor_Weights Table) 
	 * Function to build the HashMap 
	 */

	public int toHash(WaterSample water_obj,boolean flag){
		if(water_obj == null){
			return -1;
		}
		HashMap<String, Object> myMap = new HashMap<String, Object>();
		myMap.put(Constants.ID, water_obj.id);
		myMap.put(Constants.SITE,water_obj.site);
		myMap.put(Constants.CHLOROFORM, water_obj.chloroform);
		myMap.put(Constants.BROMOFORM, water_obj.bromoform);
		myMap.put(Constants.BROMODICHLOROMETHANE,water_obj.bromodichloromethane);
		myMap.put(Constants.DIBROMICHLOROMETHANE,water_obj.dibromichloromethane);
		if(flag == true){
			try{
				DBConnection connObj = new DBConnection();
				Statement stmt = null;
				stmt = connObj.getConnection();
				String sql;
				sql = "SELECT "+Constants.ID+" FROM "+Constants.FACTOR_WEIGHT_TABLE_NAME;
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					FactorWeights factorObj = new FactorWeights();
					factorObj.factorFind(rs.getInt(Constants.ID),true);
					if(factorObj.chloroform_weight < 0 || factorObj.bromoform_weight < 0 || factorObj.bromodichloromethane_weight <0 || factorObj.dibromichloromethane_weight < 0 ){
						myMap.put(("Factor_"+(factorObj.id)), "Factor Weights Negative. !Invalid");
					}else
						myMap.put(("Factor_"+(factorObj.id)), this.calculateFactor(water_obj, factorObj));
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		System.out.println("The HASHMAP values are :");
		for (Map.Entry<String, Object> entry : myMap.entrySet()) {
			System.out.println(entry.getKey()+" : "+entry.getValue());
		}
		return 1;
	}

	/*
	 * @param: Arguments
	 * Main Function which incorporates all the various classes and functionality
	 * Driver Function
	 */

	public static void main(String args[]){
		double factor = 0;
		boolean hash_flag=false;
		WaterSampleAnalysis baseObj = new WaterSampleAnalysis();
		FactorWeights factorObj = new FactorWeights();
		WaterSample waterObj = new WaterSample();
		int factor_weight_id = 0;
		int site_id = 0;
		System.out.println("Enter the Sample_ID :");
		Scanner input = new Scanner(System.in);
		site_id = input.nextInt();
		System.out.println("Enter the Factor_weight_ID :");
		factor_weight_id = input.nextInt();
		System.out.println("Enter the Hash Flag (true / false)");
		hash_flag = input.nextBoolean();
		try {
			waterObj.find(site_id);
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
			System.exit(0);
		}
		try {
			factorObj.factorFind(factor_weight_id,false);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		factor = baseObj.calculateFactor(waterObj, factorObj);
		if(factor < 0){
			System.out.println("Input Parameters are null, Kindly check !!");
			System.exit(0);
		}
		System.out.println("The Calculated Factor for "+factor_weight_id+" is "+factor);
		if(baseObj.toHash(waterObj,hash_flag)<0){
			System.out.println("Error in the input argument, Check");
			System.exit(0);
		}

	}
}
