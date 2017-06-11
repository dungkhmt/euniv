var dataWorkExperiences;
var table;
$(document).ready(function(){
	$.get(window.location.origin + "/webapp/cp/workexperiences/getWorkExperiences", function(workexperiences) {
		
		for(var i = 0; i < workexperiences.length; ++i) {
			workexperiences[i].change = '<div id="changeWorkExperiences"><span id="editWorkExperiences" class="glyphicon glyphicon-pencil" onclick="changeWorkExperiences(' + workexperiences[i].we_ID + ')"  data-toggle="modal" data-target="#edit-workexperiences"></span>'
						+	'<span id="deleteWorkExperiences" class="glyphicon glyphicon-trash" onclick="deleteWorkExperiences(' + workexperiences[i].we_ID + ')"></span></div>';
		}
		dataWorkExperiences = workexperiences;
		table = $('#dataTables-view-workexperiences').DataTable({
			 data: workexperiences,
	        columns: [
	            { "data": "we_ID" },
	            { "data": "we_CODE" },
	            { "data": "we_StaffCode" },
	            { "data": "we_Position" },
	            { "data": "we_Domain" },
	            { "data": "we_Institution"},
	            { "data": "change"}
	        ]
	    });
	});

	
});

function changeWorkExperiences(id) {
	document.getElementsByClassName("modal-title")[0].innerHTML = "Chỉnh sửa quá trình công tác";

	Array.from($(".modal-body")[0].children).forEach(function (value, index) {
		value.remove();
	});
	workexperiences = dataWorkExperiences.find(function(workexperiences) {
		return workexperiences.we_ID===id;
	})
	
	$(".modal-body").append(
			'<div class="container-fluid">'+
			    '<div class="row inline-box">'+
			      '<div class="th">Position</div>'+
			      '<input type="text" class="form-control" value = "'+workexperiences.we_Position+'" id="we_Position">'+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Domain </div>'+
			      '<input type="text" class="form-control" value = "'+workexperiences.we_Domain+'" id="we_Domain">'+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Institution</div>'+
			      '<input type="text" class="form-control" value = "'+workexperiences.we_Institution+'" id="we_Institution">'+
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

function deleteWorkExperiences(id) {
	var r = confirm("Are you sure to delete \'" + id + '\'');
	if (r == true) {
		$.ajax({
		  type: "POST",
		  url: window.location.origin + "/webapp/cp/workexperiences/delete?id=" + id,
		  data: {},
		  success: function(){
			  var index;
			  for (var i in dataWorkExperiences) {
	  		     if (dataWorkExperiences[i].we_ID == id) {
	  		    	index = i;
	  		        break; //Stop this loop, we found it!
	  		     }
	  		   }
			  table.row(index).remove().draw();
			  dataWorkExperiences  = $.grep(dataWorkExperiences, function(e){ 
				     return e.we_ID != id; 
				});
		  },
		  error: function(XMLHttpRequest, textStatus, errorThrown) {
		     alert("some error");
		  },
		  statusCode: {
		    404: function() {
		      alert( "Work Experiences not found" );
		    }
		  }
		});
	}
};

function update(id) {

	$("#update").attr('disabled','disabled');
	var WE_ID = id,
	WE_Position = $("#we_Position").val(),
	WE_Domain = $("#we_Domain").val(),
	WE_Institution = $("#we_Institution").val();
	$.ajax({
	  type: "POST",
	  url: window.location.origin + "/webapp/cp/workexperiences/update",
	  contentType : 'application/json; charset=utf-8',
      dataType : 'json',
	  data: JSON.stringify({
		  "WE_ID": WE_ID,
		  "WE_Position": WE_Position,
		  "WE_Domain": WE_Domain,
		  "WE_Institution": WE_Institution
		  		  
	  }),
	  statusCode: {
	    404: function() {
	      alert( "work experiences not found" );
	    },
	  	202: function() {
	  		var index;
	  		for (var i in dataWorkExperiences) {
  		     if (dataWorkExperiences[i].we_ID == id) {
  		    	dataWorkExperiences[i].we_Position = WE_Position;
  		    	dataWorkExperiences[i].we_Domain = WE_Domain;
  		    	dataWorkExperiences[i].we_Institution = WE_Institution;
  		    	index = i;
  		        break; //Stop this loop, we found it!
  		     }
  		   }
			var temp = table.row(index).data();
			temp.we_Position = WE_Position;
			temp.we_Domain = WE_Domain;
			temp.we_Institution =WE_Institution;
			table.row(index).data(temp);
	  		$("#edit-workexperiences").modal("hide");
	    }
	  }
	});
}

function newWorkExperiences() {
	document.getElementsByClassName("modal-title")[0].innerHTML = "Thêm mới quá trình công tác";

	Array.from($(".modal-body")[0].children).forEach(function (value, index) {
		value.remove();
	});
	
	$(".modal-body").append(
			'<div class="container-fluid">'+
			    '<div class="row inline-box">'+
			      '<div class="th">Vị trí công tác</div>'+
			      '<input type="text" class="form-control" id="we_Position">'+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Lĩnh vực chuyên môn</div>'+
			      '<input type="text" class="form-control" id="we_Domain">'+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Cơ quan công tác</div>'+
			      '<input type="text" class="form-control" id="we_Institution">'+
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
	var WE_Position = $("#we_Position").val(),
	WE_Domain = $("#we_Domain").val(),
	WE_Institution = $("#we_Institution").val();
	$.ajax({
	  type: "POST",
	  contentType : 'application/json; charset=utf-8',
      dataType : 'json',
	  url: window.location.origin + "/webapp/cp/workexperiences/add",
	  data: JSON.stringify({
		  "WE_Position": WE_Position,
		  "WE_Domain": WE_Domain,
		  "WE_Institution": WE_Institution
	  }),
	  statusCode: {
	    404: function() {
	      alert( "work experiences not found" );
	    },
	    201: function(newWorkExperiences){
			newWorkExperiences.change = '<div id="changeWorkExperiences"><span id="editWorkExperiences" class="glyphicon glyphicon-pencil" onclick="changeWorkExperiences(' + newWorkExperiences.we_ID + ')"  data-toggle="modal" data-target="#edit-workexperiences"></span>'
			+	'<span id="deleteWorkExperiences" class="glyphicon glyphicon-trash" onclick="deleteWorkExperiences(' + newWorkExperiences.we_ID + ')"></span></div>';
			dataWorkExperiences.push(newWorkExperiences);
			table.row.add(newWorkExperiences).draw();
			$("#edit-workexperiences").modal("hide");
	    }
	  }
	});
}
