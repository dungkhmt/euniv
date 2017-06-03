var dataSupervisions;
var table;
$(document).ready(function(){
	$.get(window.location.origin + "/webapp/cp/supervisions/getSupervisions", function(supervisions) {
		
		for(var i = 0; i < supervisions.length; ++i) {
			supervisions[i].change = '<div id="changeSupervision"><span id="editSupervision" class="glyphicon glyphicon-pencil" onclick="changeSupervision(' + supervisions[i].sup_ID + ')"  data-toggle="modal" data-target="#edit-supervision"></span>'
						+	'<span id="deleteSupervision" class="glyphicon glyphicon-trash" onclick="deleteSupervision(' + supervisions[i].sup_ID + ')"></span></div>';
		}
		dataSupervisions = supervisions;
		table = $('#dataTables-view-supervision').DataTable({
			 data: supervisions,
	        columns: [
	            { "data": "sup_ID" },
	            { "data": "sup_StudentName" },
	            { "data": "sup_Cosupervision" },
	            { "data": "sup_Institution" },
	            { "data": "sup_ThesisTitle" },
	            { "data": "sup_SpecializationCode" },
	            { "data": "sup_TraingPeriod" },
	            { "data": "sup_DefensedDate" },
	            { "data": "change" }
	        ]
	    });
	});	
});

function changeSupervision(id) {
	document.getElementsByClassName("modal-title")[0].innerHTML = "Chỉnh sửa hướng dẫn nghiên cứu sinh/học viên cao học";

	Array.from($(".modal-body")[0].children).forEach(function (value, index) {
		value.remove();
	});
	supervision = dataSupervisions.find(function(supervision) {
		return supervision.sup_ID===id;
	})
	$(".modal-body").append(
			'<div class="container-fluid">'+
			    '<div class="row inline-box">'+
			      '<div class="th">Tên nghiên cứu sinh</div>'+
			      '<input type="text" class="form-control" id="sup_StudentName" value="'+supervision.sup_StudentName+'">'+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Chức danh</div>'+
			      '<input type="text" class="form-control" id="sup_Cosupervision" value="'+supervision.sup_Cosupervision+'">'+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Đơn vị công tác</div>'+
			      '<input type="text" class="form-control" id="sup_Institution" value="'+supervision.sup_Institution+'">'+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Tên đề tài</div>'+
			      '<input type="text" class="form-control" id="sup_ThesisTitle" value="'+supervision.sup_ThesisTitle+'">'+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Mã chuyên ngành</div>'+
			      '<input type="text" class="form-control" id="sup_SpecializationCode" value="'+supervision.sup_SpecializationCode+'">'+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Thời gian đào tạo</div>'+
			      '<input type="text" class="form-control" id="sup_TraingPeriod" value="'+supervision.sup_TraingPeriod+'">'+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Năm bảo vệ</div>'+
			      '<input type="text" class="form-control" id="sup_DefensedDate" value="'+supervision.sup_DefensedDate+'">'+
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

function deleteSupervision(id) {
	var r = confirm("Are you sure to delete \'" + id + '\'');
	if (r == true) {
		$.ajax({
		  type: "POST",
		  url: window.location.origin + "/webapp/cp/supervisions/delete?id=" + id,
		  data: {},
		  success: function(){
			  var index;
			  for (var i in dataSupervisions) {
	  		     if (dataSupervisions[i].sup_ID == id) {
	  		    	index = i;
	  		        break; //Stop this loop, we found it!
	  		     }
	  		   }
			  table.row(index).remove().draw();
			  dataSupervisions = $.grep(dataSupervisions, function(e){ 
				     return e.edu_ID != id; 
				});
		  },
		  error: function(XMLHttpRequest, textStatus, errorThrown) {
		     alert("some error");
		  },
		  statusCode: {
		    404: function() {
		      alert( "supervision not found" );
		    }
		  }
		});
	}
};

function update(id) {

	$("#update").attr('disabled','disabled');
	SUP_StudentName = $("#sup_StudentName").val(),
	SUP_Cosupervision = $("#sup_Cosupervision").val(),
	SUP_Institution = $("#sup_Institution").val(),
	SUP_ThesisTitle = $("#sup_ThesisTitle").val(),
	SUP_SpecializationCode = $("#sup_SpecializationCode").val(),
	SUP_TraingPeriod = $("#sup_TraingPeriod").val(),
	SUP_DefensedDate = $("#sup_DefensedDate").val();
	$.ajax({
	  type: "POST",
	  url: window.location.origin + "/webapp/cp/supervisions/update",
	  contentType : 'application/json; charset=utf-8',
      dataType : 'json',
	  data: JSON.stringify({
			SUP_ID: id,
			SUP_StudentName: SUP_StudentName,
			SUP_Cosupervision: SUP_Cosupervision,
			SUP_Institution: SUP_Institution,
			SUP_ThesisTitle: SUP_ThesisTitle,
			SUP_SpecializationCode: SUP_SpecializationCode,
			SUP_TraingPeriod: SUP_TraingPeriod,
			SUP_DefensedDate: SUP_DefensedDate
	  }),
	  statusCode: {
	    404: function() {
	      alert( "supervision not found" );
	    },
	  	202: function() {
	  		var index;
	  		for (var i in dataSupervisions) {
  		     if (dataSupervisions[i].sup_ID == id) {
  		    	dataSupervisions[i].sup_StudentName = SUP_StudentName;
  		    	dataSupervisions[i].sup_Cosupervision = SUP_Cosupervision;
  		    	dataSupervisions[i].sup_Institution = SUP_Institution;
  		    	dataSupervisions[i].sup_ThesisTitle = SUP_ThesisTitle;
  		    	dataSupervisions[i].sup_SpecializationCode = SUP_SpecializationCode;
  		    	dataSupervisions[i].sup_TraingPeriod = SUP_TraingPeriod;
  		    	dataSupervisions[i].sup_DefensedDate = SUP_DefensedDate;
  		    	index = i;
  		        break; //Stop this loop, we found it!
  		     }
  		   }
			var temp = table.row(index).data();
	    	temp.sup_StudentName = SUP_StudentName;
	    	temp.sup_Cosupervision = SUP_Cosupervision;
	    	temp.sup_Institution = SUP_Institution;
	    	temp.sup_ThesisTitle = SUP_ThesisTitle;
	    	temp.sup_SpecializationCode = SUP_SpecializationCode;
	    	temp.sup_TraingPeriod = SUP_TraingPeriod;
	    	temp.sup_DefensedDate = SUP_DefensedDate;
			table.row(index).data(temp);
	  		$("#edit-supervision").modal("hide");
	    }
	  }
	});
}

function newSupervision() {
	document.getElementsByClassName("modal-title")[0].innerHTML = "Thêm mới hướng dẫn nghiên cứu sinh/học viên cao học";

	Array.from($(".modal-body")[0].children).forEach(function (value, index) {
		value.remove();
	});
	$(".modal-body").append(
			'<div class="container-fluid">'+
			    '<div class="row inline-box">'+
			      '<div class="th">Tên nghiên cứu sinh</div>'+
			      '<input type="text" class="form-control" id="sup_StudentName">'+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Chức danh</div>'+
			      '<input type="text" class="form-control" id="sup_Cosupervision">'+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Đơn vị công tác</div>'+
			      '<input type="text" class="form-control" id="sup_Institution">'+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Tên đề tài</div>'+
			      '<input type="text" class="form-control" id="sup_ThesisTitle">'+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Mã chuyên ngành</div>'+
			      '<input type="text" class="form-control" id="sup_SpecializationCode">'+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Thời gian đào tạo</div>'+
			      '<input type="text" class="form-control" id="sup_TraingPeriod">'+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Năm bảo vệ</div>'+
			      '<input type="text" class="form-control" id="sup_DefensedDate">'+
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
	var SUP_StudentName = $("#sup_StudentName").val(),
	SUP_Cosupervision = $("#sup_Cosupervision").val(),
	SUP_Institution = $("#sup_Institution").val(),
	SUP_ThesisTitle = $("#sup_ThesisTitle").val(),
	SUP_SpecializationCode = $("#sup_SpecializationCode").val(),
	SUP_TraingPeriod = $("#sup_TraingPeriod").val(),
	SUP_DefensedDate = $("#sup_DefensedDate").val();
	$.ajax({
	  type: "POST",
	  contentType : 'application/json; charset=utf-8',
      dataType : 'json',
	  url: window.location.origin + "/webapp/cp/supervisions/add",
	  data: JSON.stringify({
			SUP_StudentName: SUP_StudentName,
			SUP_Cosupervision: SUP_Cosupervision,
			SUP_Institution: SUP_Institution,
			SUP_ThesisTitle: SUP_ThesisTitle,
			SUP_SpecializationCode: SUP_SpecializationCode,
			SUP_TraingPeriod: SUP_TraingPeriod,
			SUP_DefensedDate: SUP_DefensedDate
	  }),
	  statusCode: {
	    404: function() {
	      alert( "supervision not found" );
	    },
	    201: function(newSupervision){
			newSupervision.change = '<div id="changeSupervision"><span id="editSupervision" class="glyphicon glyphicon-pencil" onclick="changeSupervision(' + newSupervision.edu_ID + ')"  data-toggle="modal" data-target="#edit-supervision"></span>'
			+	'<span id="deleteSupervision" class="glyphicon glyphicon-trash" onclick="deleteSupervision(' + newSupervision.edu_ID + ')"></span></div>';
			dataSupervisions.push(newSupervision);
			table.row.add(newSupervision).draw();
			$("#edit-supervision").modal("hide");
	    }
	  }
	});
}
