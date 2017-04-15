var dataEducations;
var table;
$(document).ready(function(){
	$.get(window.location.origin + "/webapp/cp/educations/getEducations", function(educations) {
		
		for(var i = 0; i < educations.length; ++i) {
			educations[i].change = '<div id="changeEducation"><span id="editEducation" class="glyphicon glyphicon-pencil" onclick="changeEducation(' + educations[i].edu_ID + ')"  data-toggle="modal" data-target="#edit-education"></span>'
						+	'<span id="deleteEducation" class="glyphicon glyphicon-trash" onclick="deleteEducation(' + educations[i].edu_ID + ')"></span></div>';
		}
		dataEducations = educations;
		table = $('#dataTables-view-education').DataTable({
			 data: educations,
	        columns: [
	            { "data": "edu_ID" },
	            { "data": "edu_UserCode" },
	            { "data": "edu_Level" },
	            { "data": "edu_Institution" },
	            { "data": "edu_Major" },
	            { "data": "edu_CompleteDate"},
	            { "data": "change"}
	        ]
	    });
	});

	
});

function changeEducation(id) {
	document.getElementsByClassName("modal-title")[0].innerHTML = "Chỉnh sửa quá trình đào tạo";

	Array.from($(".modal-body")[0].children).forEach(function (value, index) {
		value.remove();
	});
	education = dataEducations.find(function(education) {
		return education.edu_ID===id;
	})
	
	$(".modal-body").append(
			'<div class="container-fluid">'+
			    '<div class="row inline-box">'+
			      '<div class="th">Bậc đào tạo</div>'+
			      '<input type="text" class="form-control" value = "'+education.edu_Level+'" id="edu_Level">'+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Nơi đào tạo</div>'+
			      '<input type="text" class="form-control" value = "'+education.edu_Institution+'" id="edu_Institution">'+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Chuyên ngành</div>'+
			      '<input type="text" class="form-control" value = "'+education.edu_Major+'" id="edu_Major">'+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Thời gian tốt nghiệp</div>'+
			      '<input type="date" class="form-control" value = "'+education.edu_CompleteDate+'" id="edu_CompleteDate">'+
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

function deleteEducation(id) {
	var r = confirm("Are you sure to delete \'" + id + '\'');
	if (r == true) {
		$.ajax({
		  type: "POST",
		  url: window.location.origin + "/webapp/cp/educations/delete?id=" + id,
		  data: {},
		  success: function(){
			  var index;
			  for (var i in dataEducations) {
	  		     if (dataEducations[i].edu_ID == id) {
	  		    	index = i;
	  		        break; //Stop this loop, we found it!
	  		     }
	  		   }
			  table.row(index).remove().draw();
			  dataEducations = $.grep(dataEducations, function(e){ 
				     return e.edu_ID != id; 
				});
		  },
		  error: function(XMLHttpRequest, textStatus, errorThrown) {
		     alert("some error");
		  },
		  statusCode: {
		    404: function() {
		      alert( "education not found" );
		    }
		  }
		});
	}
};

function update(id) {

	$("#update").attr('disabled','disabled');
	var EDU_ID = id,
	EDU_Major = $("#edu_Major").val(),
	EDU_Institution = $("#edu_Institution").val(),
	EDU_Level = $("#edu_Level").val(),
	EDU_CompleteDate = $("#edu_CompleteDate").val();
	$.ajax({
	  type: "POST",
	  url: window.location.origin + "/webapp/cp/educations/update",
	  contentType : 'application/json; charset=utf-8',
      dataType : 'json',
	  data: JSON.stringify({
		  "EDU_ID": EDU_ID,
		  "EDU_Major": EDU_Major,
		  "EDU_Institution": EDU_Institution,
		  "EDU_Level": EDU_Level,
		  "EDU_CompleteDate": EDU_CompleteDate
	  }),
	  statusCode: {
	    404: function() {
	      alert( "education not found" );
	    },
	  	202: function() {
	  		var index;
	  		for (var i in dataEducations) {
  		     if (dataEducations[i].edu_ID == id) {
  		    	dataEducations[i].edu_Major = EDU_Major;
  		    	dataEducations[i].edu_Institution = EDU_Institution;
  		    	dataEducations[i].edu_Level = EDU_Level;
  		    	dataEducations[i].edu_CompleteDate = EDU_CompleteDate;
  		    	index = i;
  		        break; //Stop this loop, we found it!
  		     }
  		   }
			var temp = table.row(index).data();
			temp.edu_Major = EDU_Major;
			temp.edu_Institution = EDU_Institution;
			temp.edu_Level = EDU_Level;
			temp.edu_CompleteDate = EDU_CompleteDate;
			table.row(index).data(temp);
	  		$("#edit-education").modal("hide");
	    }
	  }
	});
}

function newEducation() {
	document.getElementsByClassName("modal-title")[0].innerHTML = "Thêm mới quá trình đào tạo";

	Array.from($(".modal-body")[0].children).forEach(function (value, index) {
		value.remove();
	});
	
	$(".modal-body").append(
			'<div class="container-fluid">'+
			    '<div class="row inline-box">'+
			      '<div class="th">Bậc đào tạo</div>'+
			      '<input type="text" class="form-control" id="edu_Level">'+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Nơi đào tạo</div>'+
			      '<input type="text" class="form-control" id="edu_Institution">'+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Chuyên ngành</div>'+
			      '<input type="text" class="form-control" id="edu_Major">'+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Thời gian tốt nghiệp</div>'+
			      '<input type="date" class="form-control" id="edu_CompleteDate">'+
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
	var EDU_Major = $("#edu_Major").val(),
	EDU_Institution = $("#edu_Institution").val(),
	EDU_Level = $("#edu_Institution").val(),
	EDU_CompleteDate = $("#edu_CompleteDate").val();
	$.ajax({
	  type: "POST",
	  contentType : 'application/json; charset=utf-8',
      dataType : 'json',
	  url: window.location.origin + "/webapp/cp/educations/add",
	  data: JSON.stringify({
		  "EDU_Major": EDU_Major,
		  "EDU_Institution": EDU_Institution,
		  "EDU_Level": EDU_Level,
		  "EDU_CompleteDate": EDU_CompleteDate
	  }),
	  statusCode: {
	    404: function() {
	      alert( "education not found" );
	    },
	    201: function(newEducation){
			newEducation.change = '<div id="changeEducation"><span id="editEducation" class="glyphicon glyphicon-pencil" onclick="changeEducation(' + newEducation.edu_ID + ')"  data-toggle="modal" data-target="#edit-education"></span>'
			+	'<span id="deleteEducation" class="glyphicon glyphicon-trash" onclick="deleteEducation(' + newEducation.edu_ID + ')"></span></div>';
			dataEducations.push(newEducation);
			table.row.add(newEducation).draw();
			$("#edit-education").modal("hide");
	    }
	  }
	});
}
