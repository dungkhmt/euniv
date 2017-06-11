var dataAppliedProjects;
var table;
$(document).ready(function(){
	$.get(window.location.origin + "/webapp/cp/appliedprojects/getAppliedProjects", function(appliedprojects) {
		
		for(var i = 0; i < appliedprojects.length; ++i) {
			appliedprojects[i].change = '<div id="changeAppliedProjects"><span id="editAppliedProjects" class="glyphicon glyphicon-pencil" onclick="changeAppliedProjects(' + appliedprojects[i].ap_ID + ')"  data-toggle="modal" data-target="#edit-appliedprojects"></span>'
						+	'<span id="deleteAppliedProjects" class="glyphicon glyphicon-trash" onclick="deleteAppliedProjects(' + appliedprojects[i].ap_ID + ')"></span></div>';
		}
		dataAppliedProjects = appliedprojects;
		table = $('#dataTables-view-appliedprojects').DataTable({
			 data: appliedprojects,
	        columns: [
	            { "data": "ap_ID" },
	            { "data": "ap_Code" },
	            { "data": "ap_Name" },
	            { "data": "ap_Scope" },
	            { "data": "ap_Date" },
	            { "data": "ap_StaffCode"},
	            { "data": "change"}
	        ]
	    });
	});

	
});

function changeAppliedProjects(id) {
	document.getElementsByClassName("modal-title")[0].innerHTML = "Chỉnh sửa công trình nghiên cứu";

	Array.from($(".modal-body")[0].children).forEach(function (value, index) {
		value.remove();
	});
	appliedprojects = dataAppliedProjects.find(function(appliedprojects) {
		return appliedprojects.ap_ID===id;
	})
	
	$(".modal-body").append(
			'<div class="container-fluid">'+
			    '<div class="row inline-box">'+
			      '<div class="th">Tên công trình</div>'+
			      '<input type="text" class="form-control" value = "'+appliedprojects.ap_Name+'" id="ap_Name">'+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Hình thức, quy mô</div>'+
			      '<input type="text" class="form-control" value = "'+appliedprojects.ap_Scope+'" id="ap_Scope">'+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Thời gian</div>'+
			      '<input type="text" class="form-control" value = "'+appliedprojects.ap_Date+'" id="ap_Date">'+
			    '</div>'+
		    '</div>'
	);
	
	Array.from($(".modal-footer")[0].children).forEach(function (value, index) {
		value.remove();
	});
	
	$(".modal-footer").append(
		'<button type="button" class="btn btn-success" onclick="update('+id+')" id="update">Update</button>' +
     	'<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>'
	);
}

function deleteAppliedProjects(id) {
	var r = confirm("Are you sure to delete \'" + id + '\'');
	if (r == true) {
		$.ajax({
		  type: "POST",
		  url: window.location.origin + "/webapp/cp/appliedprojects/delete?id=" + id,
		  data: {},
		  success: function(){
			  var index;
			  for (var i in dataAppliedProjects) {
	  		     if (dataAppliedProjects[i].ap_ID == id) {
	  		    	index = i;
	  		        break; //Stop this loop, we found it!
	  		     }
	  		   }
			  table.row(index).remove().draw();
			  dataAppliedProjects= $.grep(dataAppliedProjects, function(e){ 
				     return e.ap_ID != id; 
				});
		  },
		  error: function(XMLHttpRequest, textStatus, errorThrown) {
		     alert("some error");
		  },
		  statusCode: {
		    404: function() {
		      alert( "applied projects not found" );
		    }
		  }
		});
	}
};

function update(id) {

	$("#update").attr('disabled','disabled');
	var AP_ID = id,
	AP_Name = $("#ap_Name").val(),
	AP_Scope = $("#ap_Scope").val(),
	AP_Date = $("#ap_Date").val();
	$.ajax({
	  type: "POST",
	  url: window.location.origin + "/webapp/cp/appliedprojects/update",
	  contentType : 'application/json; charset=utf-8',
      dataType : 'json',
	  data: JSON.stringify({
		  "AP_ID": AP_ID,
		  "AP_Name": AP_Name,
		  "AP_Scope": AP_Scope,
		  "AP_Date": AP_Date
	  }),
	  statusCode: {
	    404: function() {
	      alert( "applied projects not found" );
	    },
	  	202: function() {
	  		var index;
	  		dataAppliedProjects.forEach(function(value, index){
	  			if(value.ap_ID == id){
	  				value.ap_Name = AP_Name;
	  				value.ap_Scope = AP_Scope;
	  				value.ap_Date = AP_Date;
	  				
	  				var temp = table.row(index).data();
	  				temp.ap_Name = AP_Name;
	  				temp.ap_Scope = AP_Scope;
	  				temp.ap_Date = AP_Date;
	  				table.row(index).data(temp);
	  				$("#edit-appliedprojects").modal("hide");
	  				return ;
	  			}
	  		})
	  		}
	  }
	});
}

function newAppliedProjects() {
	document.getElementsByClassName("modal-title")[0].innerHTML = "Thêm mới công trình nghiên cứu";

	Array.from($(".modal-body")[0].children).forEach(function (value, index) {
		value.remove();
	});
	
	$(".modal-body").append(
			'<div class="container-fluid">'+
			    '<div class="row inline-box">'+
			      '<div class="th">Tên công trình </div>'+
			      '<input type="text" class="form-control" id="ap_Name">'+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Mô hình, quy mô</div>'+
			      '<input type="text" class="form-control" id="ap_Scope">'+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Thời gian</div>'+
			      '<input type="text" class="form-control" id="ap_Date">'+
			    '</div>'+
		    '</div>'
	);
	
	Array.from($(".modal-footer")[0].children).forEach(function (value, index) {
		value.remove();
	});
	
	$(".modal-footer").append(
		'<button type="button" class="btn btn-success" onclick="save()" id="save">Save</button>' +
     	'<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>'
	);
}


function save() {
	$("#save").attr('disabled','disabled');
	var AP_Name = $("#ap_Name").val(),
	AP_Scope = $("#ap_Scope").val(),
	AP_Date = $("#ap_Date").val();
	$.ajax({
	  type: "POST",
	  contentType : 'application/json; charset=utf-8',
      dataType : 'json',
	  url: window.location.origin + "/webapp/cp/appliedprojects/add",
	  data: JSON.stringify({
		  "AP_Name": AP_Name,
		  "AP_Scope": AP_Scope,
		  "AP_Date": AP_Date
	  }),
	  statusCode: {
	    404: function() {
	      alert( "applied projects not found" );
	    },
	    201: function(newAppliedProjects){
			newAppliedProjects.change = '<div id="changeAppliedProjects"><span id="editAppliedProjects" class="glyphicon glyphicon-pencil" onclick="changeAppliedProjects(' + newAppliedProjects.ap_ID + ')"  data-toggle="modal" data-target="#edit-appliedprojects"></span>'
			+	'<span id="deleteAppliedProjects" class="glyphicon glyphicon-trash" onclick="deleteAppliedProjects(' + newAppliedProjects.ap_ID + ')"></span></div>';
			dataAppliedProjects.push(newAppliedProjects);
			table.row.add(newAppliedProjects).draw();
			$("#edit-appliedprojects").modal("hide");
	    }
	  }
	});
}
