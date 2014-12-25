/**
 * 
 */
package com.backbone.employee.processor;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import com.backbone.dao.EmployeeDAO;
import com.backbone.util.EmployeeEntry;
import com.backbone.bean.*;

/**
 * @author vaibhav
 *
 */
@Path("/emp/")
public class EmployeeDetails {
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response findAll() throws Exception{
		System.out.println("findAll()");
		return EmployeeDAO.findAll();
	}
	
	@Path("/empUpdate/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response findById(@PathParam("id") String id) throws Exception{
		System.out.println("findById()");
		return EmployeeDAO.findById(id);
	}
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertEmployee(String incomingData) throws Exception{
		System.out.println("insertEmployee()");
		String returnString = null;
		
		
		
		try {
			System.out.println("incomingData: " + incomingData);
			
			/*
			 * ObjectMapper is from Jackson Processor framework
			 * http://jackson.codehaus.org/
			 * 
			 * Using the readValue method, you can parse the json from the http request
			 * and data bind it to a Java Class.
			 */
			ObjectMapper mapper = new ObjectMapper();  
			EmployeeEntry itemEntry = mapper.readValue(incomingData, EmployeeEntry.class);
			
			int http_code = EmployeeDAO.insertEmp(itemEntry.EMPNO, 
													itemEntry.ENAME, 
													itemEntry.JOB, 
													itemEntry.MGR, 
													itemEntry.HIREDATE, 
													itemEntry.SAL,itemEntry.COMM,itemEntry.DEPTNO);
			
			if( http_code == 200 ) {
				//returnString = jsonArray.toString();
				returnString = "Item inserted";
			} else {
				return Response.status(500).entity("Unable to process Item").build();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(incomingData).build();
	}
		
	}
	

