var dataForeignLanguages;
var table;
$(document).ready(function(){
	$.get(window.location.origin + "/webapp/cp/foreignlanguages/getForeignLanguages", function(foreignlanguages) {
		
		for(var i = 0; i < foreignlanguages.length; ++i) {
			foreignlanguages[i].change = '<div id="changeForeignLanguages"><span id="editForeignLanguages" class="glyphicon glyphicon-pencil" onclick="changeForeignLanguages(' + foreignlanguages[i].lg_ID + ')"  data-toggle="modal" data-target="#edit-foreignlanguages"></span>'
						+	'<span id="deleteForeignLanguages" class="glyphicon glyphicon-trash" onclick="deleteForeignLanguages(' + foreignlanguages[i].lg_ID + ')"></span></div>';
		}
		dataForeignLanguages = foreignlanguages;
		table = $('#dataTables-view-foreignlanguages').DataTable({
			 data: foreignlanguages,
	        columns: [
	            { "data": "lg_ID" },	       
	            { "data": "lg_Name" },
	            { "data": "lg_Listen" },
	            { "data": "lg_Speak" },
	            { "data": "lg_Reading"},
	            { "data": "lg_Writing"},	                
	            { "data": "change"}
	        ]
	    });
	});

	
});

function changeForeignLanguages(id) {
	document.getElementsByClassName("modal-title")[0].innerHTML = "Chỉnh sửa trình độ ngoại ngữ";

	Array.from($(".modal-body")[0].children).forEach(function (value, index) {
		value.remove();
	});
	foreignlanguages = dataForeignLanguages.find(function(foreignlanguages) {
		return foreignlanguages.lg_ID===id;
	})
	
	$(".modal-body").append(
			'<div class="container-fluid">'+
		    '<div class="row inline-box">'+
		      '<div class="th">Tên ngoại ngữ</div>'+
		      '<input type="text" class="form-control" value = "'+foreignlanguages.lg_Name+'" id="lg_Name">'+
		    '</div>'+
		    '<div class="row inline-box">'+
		      '<div class="th">Nghe</div>'+
		    //  '<input type="text" class="form-control" value = "'+foreignlanguages.lg_Listen+'" id="lg_Listen">'+
		     addSelect("lg_Listen")+ 
		    '</div>'+
		    '<div class="row inline-box">'+
		      '<div class="th">Nói</div>'+
		     // '<input type="text" class="form-control" value = "'+foreignlanguages.lg_Speak+'" id="lg_Speak">'+
		     addSelect("lg_Speak")+
		    '</div>'+
		    '<div class="row inline-box">'+
		      '<div class="th">Đọc</div>'+
		      //'<input type="text" class="form-control" value = "'+foreignlanguages.lg_Reading+'" id="lg_Reading">'+
		     addSelect("lg_Reading")+
		    '</div>'+
		    '<div class="row inline-box">'+
		      '<div class="th">Viết</div>'+
		     // '<input type="text" class="form-control" value = "'+foreignlanguages.lg_Writing+'" id="lg_Writing">'+
		      addSelect("lg_Writing")+
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

function deleteForeignLanguages(id) {
	var r = confirm("Are you sure to delete \'" + id + '\'');
	if (r == true) {
		$.ajax({
		  type: "POST",
		  url: window.location.origin + "/webapp/cp/foreignlanguages/delete?id=" + id,
		  data: {},
		  success: function(){
			  var index;
			  for (var i in dataForeignLanguages) {
	  		     if (dataForeignLanguages[i].lg_ID == id) {
	  		    	index = i;
	  		        break; //Stop this loop, we found it!
	  		     }
	  		   }
			  table.row(index).remove().draw();
			  dataForeignLanguages = $.grep(dataForeignLanguages, function(e){ 
				     return e.edu_ID != id; 
				});
		  },
		  error: function(XMLHttpRequest, textStatus, errorThrown) {
		     alert("some error");
		  },
		  statusCode: {
		    404: function() {
		      alert( "foreign languages not found" );
		    }
		  }
		});
	}
};

function addSelect( idName){
	 return '<select id = "'+idName+'">'+
   	'<option value="Tốt">Tốt</option>' +
   	'<option value="Khá">Khá</option>' +
   	'<option value="Trung bình">Trung bình</option>'+
   	'</select>'

}

function update(id) {

	$("#update").attr('disabled','disabled');
	var LG_ID = id,
	LG_Name = $("#lg_Name").val(),
	LG_Listen = $("#lg_Listen").val(),
	LG_Speak = $("#lg_Speak").val(),
	LG_Reading = $("#lg_Reading").val();
	LG_Writing = $("#lg_Writing").val();
	
	$.ajax({
	  type: "POST",
	  url: window.location.origin + "/webapp/cp/foreignlanguages/update",
	  contentType : 'application/json; charset=utf-8',
      dataType : 'json',
	  data: JSON.stringify({
		  "LG_ID": LG_ID,
		  "LG_Name": LG_Name,
		  "LG_Listen": LG_Listen,
		  "LG_Speak": LG_Speak,
		  "LG_Reading": LG_Reading,
		  "LG_Writing": LG_Writing
	  }),
	  statusCode: {
	    404: function() {
	      alert( "foreign languages not found" );
	    },
	  	202: function() {
	  		var index;
	  		for (var i in dataForeignLanguages) {
	  		     if (dataForeignLanguages[i].lg_ID == id) {
	  		    	dataForeignLanguages[i].lg_Name = LG_Name;
	  		    	dataForeignLanguages[i].lg_Listen = LG_Listen;
	  		    	dataForeignLanguages[i].lg_Speak = LG_Speak;
	  		    	dataForeignLanguages[i].lg_Reading = LG_Reading;
	  		    	dataForeignLanguages[i].lg_Writing = LG_Writing;	  		    	
	  		    	index = i;
	  		        break; //Stop this loop, we found it!
	  		     }
	  		   }
	  		
	  		
	  		var temp = table.row(index).data();
			temp.lg_Name = LG_Name;
			temp.lg_Listen = LG_Listen;
			temp.lg_Speak = LG_Speak;
			temp.lg_Reading = LG_Reading;
			temp.lg_Writing = LG_Writing;
			table.row(index).data(temp);
	  		$("#edit-foreignlanguages").modal("hide");
	    }
	  }
	});
}

function newForeignLanguages() {
	document.getElementsByClassName("modal-title")[0].innerHTML = "Thêm mới ngoại ngữ";

	Array.from($(".modal-body")[0].children).forEach(function (value, index) {
		value.remove();
	});
	
	$(".modal-body").append(
			'<div class="container-fluid">'+
			    '<div class="row inline-box">'+
			      '<div class="th">Tên ngoại ngữ</div>'+
			      '<input type="text" class="form-control" id="lg_Name">'+
			    '</div>'+			    
			    '<div class="row inline-box">'+
			      '<div class="th">Nghe</div>'+
			     // '<input type="text" class="form-control" id="lg_Listen">'+
			      addSelect("lg_Listen")+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Nói</div>'+
			     // '<input type="text" class="form-control" id="lg_Speak">'+
			      addSelect("lg_Speak")+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Đọc</div>'+
			     // '<input type="text" class="form-control" id="lg_Reading">'+
			      addSelect("lg_Reading")+
			    '</div>'+
			    '<div class="row inline-box">'+
			      '<div class="th">Viết</div>'+
			     // '<input type="text" class="form-control" id="lg_Writing">'+
			      addSelect("lg_Writing")+
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
	var LG_Name = $("#lg_Name").val(),
	LG_Listen = $("#lg_Listen").val(),
	LG_Speak = $("#lg_Speak").val(),
	LG_Reading = $("#lg_Reading").val(),
	LG_Writing = $("#lg_Writing").val();
	$.ajax({
	  type: "POST",
	  contentType : 'application/json; charset=utf-8',
      dataType : 'json',
	  url: window.location.origin + "/webapp/cp/foreignlanguages/add",
	  data: JSON.stringify({
		  "LG_Name": LG_Name,
		  "LG_Listen": LG_Listen,
		  "LG_Speak": LG_Speak,
		  "LG_Reading": LG_Reading,
		  "LG_Writing": LG_Writing,	
	  }),
	  statusCode: {
	    404: function() {
	      alert( "foreign languages not found" );
	    },
	    201: function(newForeignLanguages){
	    	newForeignLanguages.change = '<div id="changeForeignLanguages"><span id="editForeignLanguages" class="glyphicon glyphicon-pencil" onclick="changeForeignLanguages(' + newForeignLanguages.lg_ID + ')"  data-toggle="modal" data-target="#edit-foreignlanguages"></span>'
			+	'<span id="deleteForeignLanguages" class="glyphicon glyphicon-trash" onclick="deleteForeignLanguages(' + newForeignLanguages.lg_ID + ')"></span></div>';
			dataForeignLanguages.push(newForeignLanguages);
			table.row.add(newForeignLanguages).draw();
			$("#edit-foreignlanguages").modal("hide");
	    }
	  }
	});
}
