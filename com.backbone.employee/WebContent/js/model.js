
var Employee = Backbone.Model.extend({
	urlRoot: "/com.backbone.employee/api/emp",
	defaults: {
		"empno": null,
	    "ename":  "",
	    "job":  "",
	    "mgr":  null,
	    "hiredate":  null,
	    "sal":  null,
	    "comm":  null,
	    "deptno":  null
	  }
});




