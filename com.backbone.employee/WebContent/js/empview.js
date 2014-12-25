
var Employees = Backbone.Collection.extend({
	
		url:'/com.backbone.employee/api/emp'
});
var employee;
var employees = new Employees();
_.each(employees,function(obj){console.log(obj.get("empno"))});

window.EmpList = Backbone.View.extend({
	el:'.page',
	render:function(){
		
		
		var that = this;
		employees.fetch({
			success: function(employees){
				
				var template = _.template($("#employee-list-template").html(),{employees:employees.models});
				that.$el.html(template);
				
			}
		});
		
	}
	
});

var empList = new window.EmpList();


window.Router1 = Backbone.Router.extend({
	routes:{
		"":"home"
	}
	
});

var router =new window.Router1();

	this.router.on("route:home",function(){
		empList.render();
		
	});
Backbone.history.start();
