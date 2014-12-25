
var Employees = Backbone.Collection.extend({
	
		url:'/com.backbone.employee/api/emp'
});
$.fn.serializeObject = function() {
	  var o = {};
	  var a = this.serializeArray();
	  $.each(a, function() {
	      if (o[this.name] !== undefined) {
	          if (!o[this.name].push) {
	              o[this.name] = [o[this.name]];
	          }
	          o[this.name].push(this.value || '');
	      } else {
	          o[this.name] = this.value || '';
	      }
	  });
	  return o;
	};
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

window.UserEdit = Backbone.View.extend({
	el:'.page',
	render:function(){
		
		var template = _.template($("#edit-employee-template").html(),{});
		this.$el.html(template);
	},
	events:{
		'click .addUser':'saveEmployee'
	},
	saveEmployee:function(ev){
		console.log($(".form-horizontal").serializeObject());
		var empDetails = $(".form-horizontal").serializeObject();
		var employee = new Employee();
		employee.save(empDetails,{
			success:function(){
				alert("User saved!!");
			}
		});
		console.log(empDetails)
		return false;
	},
 
	
});

var empList = new window.EmpList();
var editUser = new window.UserEdit();

window.Router1 = Backbone.Router.extend({
	routes:{
		"":"home",
		"new":"addUser"
	}
	
});

var router =new window.Router1();

	this.router.on("route:home",function(){
		empList.render();
		
	});
	this.router.on("route:addUser",function(){
		editUser.render();
		
	});
Backbone.history.start();
