var dataAwards;
var table;
$(document).ready(function(){
	$.get(window.location.origin + "/webapp/cp/awards/getAwards", function(awards) {
		
		for(var i = 0; i < awards.length; ++i) {
			awards[i].change = '<div id="changeAwards"><span id="editAwards" class="glyphicon glyphicon-pencil" onclick="changeAwards(' + awards[i].aw_ID + ')"  data-toggle="modal" data-target="#edit-awards"></span>'
						+	'<span id="deleteAwards" class="glyphicon glyphicon-trash" onclick="deleteAwards(' + awards[i].aw_ID + ')"></span></div>';
		}
		dataAwards = awards;
		table = $('#dataTables-view-awards').DataTable({
			 data: awards,
	        columns: [
	            { "data": "aw_ID" },
	            { "data": "aw_Code" },
	            { "data": "aw_Name" },
	            { "data": "aw_Date" },
	            { "data": "aw_StaffCode"},
	            { "data": "change"}
	        ]
	    });
	});

	
});

function changeAwards(id) {
	document.getElementsByClassName("modal-title")[0].innerHTML = "Chỉnh sửa giải thưởng";

	Array.from($(".modal-body")[0].children).forEach(function (value, index) {
		value.remove();
	});
	awards = dataAwards.find(function(awards) {
		return awards.aw_ID===id;
	})
	
	$(".modal-body").append(
			'<div class="container-fluid">'+
			    '<div class="row inline-box">'+
			      '<div class="th">Tên giải thưởng</div>'+
			      '<input type="text" class="form-control" value = "'+awards.aw_Name+'" id="aw_Name">'+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Date</div>'+
			      '<input type="text" class="form-control" value = "'+awards.aw_Date+'" id="aw_Date">'+
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

function deleteAwards(id) {
	var r = confirm("Are you sure to delete \'" + id + '\'');
	if (r == true) {
		$.ajax({
		  type: "POST",
		  url: window.location.origin + "/webapp/cp/awards/delete?id=" + id,
		  data: {},
		  success: function(){
			  var index;
			  for (var i in dataAwards){
	  		     if (dataAwards[i].aw_ID == id) {
	  		    	index = i;
	  		        break; //Stop this loop, we found it!
	  		     }
	  		   }
			  table.row(index).remove().draw();
			  dataAwards= $.grep(dataAwards, function(e){ 
				     return e.aw_ID != id; 
				});
		  },
		  error: function(XMLHttpRequest, textStatus, errorThrown) {
		     alert("some error");
		  },
		  statusCode: {
		    404: function() {
		      alert( "awards not found" );
		    }
		  }
		});
	}
};

function update(id) {

	$("#update").attr('disabled','disabled');
	var AW_ID = id,
	AW_Name = $("#aw_Name").val(),
	AW_Date = $("#aw_Date").val();
	$.ajax({
	  type: "POST",
	  url: window.location.origin + "/webapp/cp/awards/update",
	  contentType : 'application/json; charset=utf-8',
      dataType : 'json',
	  data: JSON.stringify({
		  "AW_ID": AW_ID,
		  "AW_Name": AW_Name,
		  "AW_Date": AW_Date
	  }),
	  statusCode: {
	    404: function() {
	      alert( "awards not found" );
	    },
	  	202: function() {
	  		var index;
	  		dataAwards.forEach(function(value, index){
	  			if(value.aw_ID == id){
	  				value.aw_Name = AW_Name;
	  				value.aw_Date = AW_Date;
	  				
	  				var temp = table.row(index).data();
	  				temp.aw_Name = AW_Name;
	  				temp.aw_Date = AW_Date;
	  				table.row(index).data(temp);
	  				$("#edit-awards").modal("hide");
	  				return ;
	  			}
	  		})
	  		}
	  }
	});
}

function newAwards() {
	document.getElementsByClassName("modal-title")[0].innerHTML = "Thêm mới giải thưởng";

	Array.from($(".modal-body")[0].children).forEach(function (value, index) {
		value.remove();
	});
	
	$(".modal-body").append(
			'<div class="container-fluid">'+
			    '<div class="row inline-box">'+
			      '<div class="th">Tên giải thưởng </div>'+
			      '<input type="text" class="form-control" id="aw_Name">'+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Thời gian</div>'+
			      '<input type="text" class="form-control" id="aw_Date">'+
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
	var AW_Name = $("#aw_Name").val(),
	AW_Date = $("#aw_Date").val();
	$.ajax({
	  type: "POST",
	  contentType : 'application/json; charset=utf-8',
      dataType : 'json',
	  url: window.location.origin + "/webapp/cp/awards/add",
	  data: JSON.stringify({
		  "AW_Name": AW_Name,
		  "AW_Date": AW_Date
	  }),
	  statusCode: {
	    404: function() {
	      alert( "awards not found" );
	    },
	    201: function(newAwards){
			newAwards.change = '<div id="changeAwards"><span id="editAwards" class="glyphicon glyphicon-pencil" onclick="changeAwards(' + newAwards.aw_ID + ')"  data-toggle="modal" data-target="#edit-awards"></span>'
			+	'<span id="deleteAwards" class="glyphicon glyphicon-trash" onclick="deleteAwards(' + newAwards.aw_ID + ')"></span></div>';
			dataAwards.push(newAwards);
			table.row.add(newAwards).draw();
			$("#edit-awards").modal("hide");
	    }
	  }
	});
}
