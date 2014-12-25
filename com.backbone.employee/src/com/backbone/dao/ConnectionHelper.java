package com.backbone.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionHelper
{
	private static DataSource employeeDetail = null; //hold the database object
	private static Context context = null; //used to lookup the database connection in weblogic
	
	/**
	 * This is a public method that will return the 308tube database connection.
	 * 
	 * @return Database object
	 * @throws Exception
	 */
	public static DataSource RestFulDemoConn() throws Exception {
		
		/**
		 * check to see if the database object is already defined...
		 * if it is, then return the connection, no need to look it up again.
		 */
		if (employeeDetail != null) {
			return employeeDetail;
		}
		
		try {
			
			/**
			 * This only needs to run one time to get the database object.
			 * context is used to lookup the database object in weblogic
			 * Oracle308tube will hold the database object
			 */
			if (context == null) {
				context = new InitialContext();
			}
			
			employeeDetail = (DataSource) context.lookup("OracleXE_DataSource_Restful");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return employeeDetail;
		
	}

}