var $employee1= $('#employee');

$.ajax({
	url:'/com.backbone.employee/api/emp',
	type:'GET',
	success:function(employees){
		$.each(employees,function(i, employee){
			$employee1.append("<tr><td>"+employee.EMPNO+"</td><td>"+employee.ENAME+
					"</td><td>"+employee.JOB+"</td><td>"+employee.MGR+
					"</td><td>"+employee.HIREDATE+"</td><td>"+employee.SAL+
					"</td><td>"+employee.COMM+"</td><td>"+employee.DEPTNO+
					"</td><td><a onclick='doalert(this);' href='#post-employee' title='http://localhost:7001/com.backbone.employee/api/emp/empUpdate/"+employee.EMPNO+"' class='btn btn-primary editBtn'>Edit</button>"+
					"</td></tr>");
		})
	}
	
});

function doalert(obj){
	
	$.ajax({
		url:obj.getAttribute("title"),
		type:'GET',
		success:function(employees){
			$.each(employees,function(i, employee){
			
			$('#empno').val(employee.EMPNO);
			$('#ename').val(employee.ENAME);
			$('#job').val(employee.JOB);
			$('#mgr').val(employee.MGR);
			$('#hiredate').val(employee.HIREDATE);
			$('#sal').val(employee.SAL);
			$('#comm').val(employee.COMM);
			$('#deptno').val(employee.DEPTNO);
			})
		}
	});
}

$('#submitEmployee').click(function(e) {
	
	e.preventDefault(); //cancel form submit
	
	
	var jsObj =JSON.stringify({ "EMPNO": $('#empno').val(), "ENAME": $('#ename').val(),
		"JOB": $('#job').val(),"MGR": $('#mgr').val(),"HIREDATE": $('#hiredate').val(),
		"SAL": $('#sal').val(),"COMM": $('#comm').val(),"DEPTNO": $('#deptno').val()});
//		, ajaxObj = {};
	
	console.log(jsObj);
	
	ajaxObj = {  
		type: "POST",
		url: "http://localhost:7001/com.backbone.employee/api/emp", 
		//data: JSON.stringify(jsObj), 
		data:jsObj,
		contentType:"application/json",
		error: function(jqXHR, textStatus, errorThrown) {
			console.log("Error " + jqXHR.getAllResponseHeaders() + " " + errorThrown);
		},
		success: function(employee){
				console.log("Added data:"+employee);
				$employee1.append("<tr><td>"+employee.EMPNO+"</td><td>"+employee.ENAME+
						"</td><td>"+employee.JOB+"</td><td>"+employee.MGR+
						"</td><td>"+employee.HIREDATE+"</td><td>"+employee.SAL+
						"</td><td>"+employee.COMM+"</td><td>"+employee.DEPTNO+"</td></tr>");
			
		},
		complete: function(XMLHttpRequest) {
			//console.log( XMLHttpRequest.getAllResponseHeaders() );
		}, 
		dataType: "json" //request JSON
	};
	
	$.ajax(ajaxObj);
});