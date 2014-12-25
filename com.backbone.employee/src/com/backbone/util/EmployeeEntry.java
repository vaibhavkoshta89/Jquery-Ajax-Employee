package com.backbone.util;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeEntry {
	
		public int empno;
		public String ename;
		public String job;
		public int mgr;
		public Date hiredate;
		public int sal;
		public int comm;
		public int deptno;
	
}
