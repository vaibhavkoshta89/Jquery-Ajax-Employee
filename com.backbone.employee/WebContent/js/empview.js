
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
	el:'.employee-form',
	render:function(options){
		var that = this;
		if(options.id){
			console.log(options.id);
			that.employee = new Employee({id: options.id});
			
			that.employee.fetch({
				success:function(employee){
				
				var template = _.template($("#edit-employee-template").html(),{employee: employee});
				
				that.$el.html(template);
				}
			})
			
		}
		else{
		var template = _.template($("#edit-employee-template").html(),{employee: null});
		this.$el.html(template);
		}
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
				router.navigate('',{trigger:true});
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
		"new":"addUser",
		"editEmp/:id":"addUser"
	}
	
});

var router =new window.Router1();

	this.router.on("route:home",function(){
		empList.render();
		
	});
	this.router.on("route:addUser",function(id){
		editUser.render({id:id});
		
	});
Backbone.history.start();
