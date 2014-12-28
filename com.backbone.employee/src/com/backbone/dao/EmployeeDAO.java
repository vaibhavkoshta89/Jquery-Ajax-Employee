/**
 * 
 */
package com.backbone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import com.backbone.bean.EmployeeBean;
import com.backbone.util.ToJSON;

/**
 * @author vaibhav
 *
 */
public class EmployeeDAO {

	public static Response findAll() throws Exception{
		PreparedStatement query = null;
		//String myString = null;
		String returnString = null;
		Connection conn = null;
		
		try {
			
			conn = ConnectionHelper.RestFulDemoConn().getConnection(); //calls the method defined in the Oracle308tube package
			
			//simple sql query to return the date/time
			query = conn.prepareStatement("select * " +
					"from EMP");
			ResultSet rs = query.executeQuery();
			JSONArray json = ToJSON.toJSONArray(rs);
			//loops through the results and save it into myString
			
			
			query.close(); //close connection
			
			returnString = json.toString();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * The finally cause will always run. Even if the the method get a error.
		 * You want to make sure the connection to the database is closed.
		 */
		finally {
			if (conn != null) conn.close();
		}
		
		  return Response.ok(returnString, MediaType.APPLICATION_JSON).build();
		
		
	}
	
	public static Response findById(String id) throws Exception{
		PreparedStatement query = null;
		//String myString = null;
		String returnString = null;
		Connection conn = null;
		System.out.println("id-"+id);
		try {
			
			conn = ConnectionHelper.RestFulDemoConn().getConnection(); //calls the method defined in the Oracle308tube package
			
			//simple sql query to return the date/time
			query = conn.prepareStatement("select * " +
					"from EMP where EMPNO=?");
			query.setInt(1, Integer.parseInt((id)));
			ResultSet rs = query.executeQuery();
			JSONArray json = ToJSON.toJSONArray(rs);
			//loops through the results and save it into myString
			
			
			query.close(); //close connection
			
			returnString = json.toString();
			System.out.println(returnString);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * The finally cause will always run. Even if the the method get a error.
		 * You want to make sure the connection to the database is closed.
		 */
		finally {
			if (conn != null) conn.close();
		}
		
		  return Response.ok(returnString, MediaType.APPLICATION_JSON).build();
		
		
	}
	
	public static int insertEmp(int EMPNO,
			String ENAME,String JOB, int MGR, Date HIREDATE,
			int SAL,int COMM, int DEPTNO) throws Exception{
		PreparedStatement query = null;
		
		Connection conn = null;
		
		try {
			
			conn = ConnectionHelper.RestFulDemoConn().getConnection(); //calls the method defined in the Oracle308tube package
			
			//simple sql query to return the date/time
			query = conn.prepareStatement("insert into EMP  " +
					"(EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO) values " +
					"(?,?,?,?,?,?,?,?)");
			query.setInt(1, EMPNO);
			query.setString(2, ENAME);
			query.setString(3, JOB);

			
			query.setInt(4, MGR);
			java.sql.Date sqlDate = new java.sql.Date(HIREDATE.getTime());
			query.setDate(5, sqlDate);
			query.setInt(6, SAL);
			query.setInt(7, COMM);
			query.setInt(8, DEPTNO);
			query.executeUpdate(); //note the new command for insert statement
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * The finally cause will always run. Even if the the method get a error.
		 * You want to make sure the connection to the database is closed.
		 */
		finally {
			if (conn != null) conn.close();
		}
		
		  return 200;
		
		
	}
	
	public static int update(int EMPNO,
			String ENAME,String JOB, int MGR, Date HIREDATE,
			int SAL,int COMM, int DEPTNO) throws Exception{
		PreparedStatement query = null;
		
		Connection conn = null;
		
		try {
			
			conn = ConnectionHelper.RestFulDemoConn().getConnection(); //calls the method defined in the Oracle308tube package
			
			//simple sql query to return the date/time
			query = conn.prepareStatement("update emp set  " +
					"ENAME=?,JOB=?,MGR=?,HIREDATE=?,SAL=?,COMM=?,DEPTNO=? where EMPNO=?" );
			query.setInt(1, EMPNO);
			query.setString(2, ENAME);
			query.setString(3, JOB);

			
			query.setInt(4, MGR);
			java.sql.Date sqlDate = new java.sql.Date(HIREDATE.getTime());
			query.setDate(5, sqlDate);
			query.setInt(6, SAL);
			query.setInt(7, COMM);
			query.setInt(8, DEPTNO);
			query.executeUpdate(); //note the new command for insert statement
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * The finally cause will always run. Even if the the method get a error.
		 * You want to make sure the connection to the database is closed.
		 */
		finally {
			if (conn != null) conn.close();
		}
		
		  return 200;
		
		
	}
	
}
