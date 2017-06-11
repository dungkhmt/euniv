var dataExperiencescientificreviews;
var table;
$(document).ready(function(){
	$.get(window.location.origin + "/webapp/cp/experiencescientificreview/getExperiencescientificreviews", function(experiencescientificreviews) {
		
		for(var i = 0; i < experiencescientificreviews.length; ++i) {
			experiencescientificreviews[i].change = '<div id="changeExperiencescientificreview"><span id="editExperiencescientificreview" class="glyphicon glyphicon-pencil" onclick="changeExperiencescientificreview(' + experiencescientificreviews[i].esv_ID + ')"  data-toggle="modal" data-target="#edit-experiencescientificreview"></span>'
						+	'<span id="deleteExperiencescientificreview" class="glyphicon glyphicon-trash" onclick="deleteExperiencescientificreview(' + experiencescientificreviews[i].esv_ID + ')"></span></div>';
		}
		dataExperiencescientificreviews = experiencescientificreviews;
		table = $('#dataTables-view-experiencescientificreview').DataTable({
			 data: experiencescientificreviews,
	        columns: [
	            { "data": "esv_ID" },
	            { "data": "esv_Name" },
	            { "data": "esv_NumberTimes" },
	            { "data": "change"}
	        ]
	    });
	});
});

function changeExperiencescientificreview(id) {
	document.getElementsByClassName("modal-title")[0].innerHTML = "Chỉnh sửa kinh nghiệm đánh giá KHCN";

	Array.from($(".modal-body")[0].children).forEach(function (value, index) {
		value.remove();
	});
	experiencescientificreview = dataExperiencescientificreviews.find(function(experiencescientificreview) {
		return experiencescientificreview.esv_ID===id;
	})
	
	$(".modal-body").append(
			'<div class="container-fluid">'+
			    '<div class="row inline-box">'+
			      '<div class="th">Hình thức hội đồng</div>'+
			      '<input type="text" class="form-control" value = "'+experiencescientificreview.esv_Name+'" id="esv_Name">'+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Số lần</div>'+
			      '<input type="text" class="form-control" value = "'+experiencescientificreview.esv_NumberTimes+'" id="esv_NumberTimes">'+
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

function deleteExperiencescientificreview(id) {
	var r = confirm("Are you sure to delete \'" + id + '\'');
	if (r == true) {
		$.ajax({
		  type: "POST",
		  url: window.location.origin + "/webapp/cp/experiencescientificreview/delete?id=" + id,
		  data: {},
		  success: function(){
			  var index;
			  for (var i in dataExperiencescientificreviews) {
	  		     if (dataExperiencescientificreviews[i].esv_ID == id) {
	  		    	index = i;
	  		        break; //Stop this loop, we found it!
	  		     }
	  		   }
			  table.row(index).remove().draw();
			  dataExperiencescientificreviews = $.grep(dataExperiencescientificreviews, function(e){ 
				     return e.esv_ID != id; 
				});
		  },
		  error: function(XMLHttpRequest, textStatus, errorThrown) {
		     alert("some error");
		  },
		  statusCode: {
		    404: function() {
		      alert( "experiencescientificreview not found" );
		    }
		  }
		});
	}
};

function update(id) {

	$("#update").attr('disabled','disabled');
	var esv_ID = id,
	esv_Name = $("#esv_Name").val(),
	esv_NumberTimes = $("#esv_NumberTimes").val();
	$.ajax({
	  type: "POST",
	  url: window.location.origin + "/webapp/cp/experiencescientificreview/update",
	  contentType : 'application/json; charset=utf-8',
      dataType : 'json',
	  data: JSON.stringify({
		  "ESV_ID": esv_ID,
		  "ESV_Name": esv_Name,
		  "ESV_NumberTimes": esv_NumberTimes
	  }),
	  statusCode: {
	    404: function() {
	      alert( "experiencescientificreview not found" );
	    },
	  	202: function() {
	  		var index;
	  		for (var i in dataExperiencescientificreviews) {
  		     if (dataExperiencescientificreviews[i].esv_ID == id) {
  		    	dataExperiencescientificreviews[i].esv_Name = esv_Name;
  		    	dataExperiencescientificreviews[i].esv_NumberTimes = esv_NumberTimes;
  		    	index = i;
  		        break; //Stop this loop, we found it!
  		     }
  		   }
			var temp = table.row(index).data();
			temp.esv_Name = esv_Name;
			temp.esv_NumberTimes = esv_NumberTimes;
			table.row(index).data(temp);
	  		$("#edit-experiencescientificreview").modal("hide");
	    }
	  }
	});
}

function newExperiencescientificreview() {
	document.getElementsByClassName("modal-title")[0].innerHTML = "Thêm mới kinh nghiệm đánh giá KHCN";

	Array.from($(".modal-body")[0].children).forEach(function (value, index) {
		value.remove();
	});
	
	$(".modal-body").append(
			'<div class="container-fluid">'+
			    '<div class="row inline-box">'+
			      '<div class="th">Hình thức hội đồng</div>'+
			      '<input type="text" class="form-control" id="esv_Name">'+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Số lần</div>'+
			      '<input type="text" class="form-control" id="esv_NumberTimes">'+
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
	var esv_Name = $("#esv_Name").val(),
	esv_NumberTimes = $("#esv_NumberTimes").val();
	$.ajax({
	  type: "POST",
	  contentType : 'application/json; charset=utf-8',
      dataType : 'json',
	  url: window.location.origin + "/webapp/cp/experiencescientificreview/add",
	  data: JSON.stringify({
		  "ESV_Name": esv_Name,
		  "ESV_NumberTimes": esv_NumberTimes
	  }),
	  statusCode: {
	    404: function() {
	      alert( "experiencescientificreview not found" );
	    },
	    201: function(newExperiencescientificreview){
			newExperiencescientificreview.change = '<div id="changeExperiencescientificreview"><span id="editExperiencescientificreview" class="glyphicon glyphicon-pencil" onclick="changeExperiencescientificreview(' + newExperiencescientificreview.esv_ID + ')"  data-toggle="modal" data-target="#edit-experiencescientificreview"></span>'
			+	'<span id="deleteExperiencescientificreview" class="glyphicon glyphicon-trash" onclick="deleteExperiencescientificreview(' + newExperiencescientificreview.esv_ID + ')"></span></div>';
			dataExperiencescientificreviews.push(newExperiencescientificreview);
			table.row.add(newExperiencescientificreview).draw();
			$("#edit-experiencescientificreview").modal("hide");
	    }
	  }
	});
}
