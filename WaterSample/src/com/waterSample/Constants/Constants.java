package com.waterSample.Constants;
/**
 * 
 */

/**
 * @author Louis Arokiaraj Gilbert
 * Class which contains all the constant values and makes available to other files.
 */

public class Constants {
	/*
	 * Constants to access the Database Connection
	 */
	public final static String DATABASE_NAME ="watersample";
	public final static String DATABASE_USERNAME = "root";
	public final static String DATABASE_PASSWORD = "";
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	
	/*
	 * Constants used to access Factor Weights Table
	 */
	public final static String FACTOR_WEIGHT_TABLE_NAME = "factor_weights";
	public final static String CHLOROFORM_WEIGHT = "chloroform_weight";
	public final static String BROMOFORM_WEIGHT = "bromoform_weight";
	public final static String BROMODICHLOROMETHANE_WEIGHT = "bromodichloromethane_weight";
	public final static String DIBROMICHLOROMETHANE_WEIGHT = "dibromichloromethane_weight";
	
	/*
	 * Constants used to access Water Sample Table
	 */
	
	public final static String SAMPLE_WEIGHTS_TABLE_NAME = "water_samples";
	public final static String ID = "id";
	public final static String SITE = "site";
	public final static String CHLOROFORM = "chloroform";
	public final static String BROMOFORM = "bromoform";
	public final static String BROMODICHLOROMETHANE ="bromodichloromethane";
	public final static String DIBROMICHLOROMETHANE = "dibromichloromethane";
}
