$(document).ready(function() {
		
	row = $(".gradeX");
	row.each(function(){
		examiner1Col = $(this).children(".examiner1Col");
		examiner2Col = $(this).children(".examiner2Col");
		presidentCol = $(this).children(".presidentCol");
		secretaryCol = $(this).children(".secretaryCol");
		commissionerCol = $(this).children(".commissionerCol");
		slotCol = $(this).children(".slotCol");
		roomCol = $(this).children(".roomCol");
		
		examiner1Val = $(examiner1Col).children().eq(0);
		examiner2Val = $(examiner2Col).children().eq(0);
		presidentVal = $(presidentCol).children().eq(0);
		secretaryVal = $(secretaryCol).children().eq(0);
		commissionerVal = $(commissionerCol).children().eq(0);
		slotVal = $(slotCol).children().eq(0);
		roomVal = $(roomCol).children().eq(0);	
		
		if($(examiner1Val).text().length < 4){
			$(examiner1Val).text('Chưa xếp');
		}
		if($(examiner2Val).text().length < 4){
			$(examiner2Val).text('Chưa xếp');
		}
		if($(presidentVal).text().length < 4){
			$(presidentVal).text('Chưa xếp');
		}
		if($(secretaryVal).text().length < 4){
			$(secretaryVal).text('Chưa xếp');
		}
		if($(commissionerVal).text().length < 4){
			$(commissionerVal).text('Chưa xếp');
		}
		if($(roomVal).text().length < 4){
			$(roomVal).text('Chưa xếp');
		}
		if($(slotVal).text().length < 4){
			$(slotVal).text('Chưa xếp');
		}
		
		
	});
	
});
$('#save').click(function(){
	
	size = $("#listMasterDefenseJuryThesisSize").val();
	defensesession = $("#defenseSession").val();
	listthesis = "";
	listexaminer1 = "";
	listexaminer2 = "";
	listpresident = "";
	listsecretary = "";
	listcommissioner = "";
	listslot = "";
	listroom = "";
	for(i=0;i<size;i++){
		if($("#uti-"+i).children(".thesis").val().length > 3)
			listthesis += $("#uti-"+i).children(".thesis").val()+',';
		else
			listthesis += 'null'+',';
		
		if($("#uti-"+i).children(".examiner1").val().length > 3)			
			listexaminer1 += $("#uti-"+i).children(".examiner1").val()+',';
		else
			listexaminer1 += 'null'+',';
		
		if($("#uti-"+i).children(".examiner2").val().length > 3)			
			listexaminer2 += $("#uti-"+i).children(".examiner2").val()+',';
		else
			listexaminer2 += 'null'+',';
		
		if($("#uti-"+i).children(".president").val().length > 3)			
			listpresident += $("#uti-"+i).children(".president").val()+',';
		else
			listpresident += 'null'+',';
		
		if($("#uti-"+i).children(".secretary").val().length > 3)			
			listsecretary += $("#uti-"+i).children(".secretary").val()+',';
		else
			listsecretary += 'null'+',';
		
		if($("#uti-"+i).children(".commissioner").val().length > 3)			
			listcommissioner += $("#uti-"+i).children(".commissioner").val()+',';
		else
			listcommissioner += 'null'+',';
		
		if($("#uti-"+i).children(".slot").val().length > 3)			
			listslot += $("#uti-"+i).children(".slot").val()+',';
		else
			listslot += 'null'+',';
		
		if($("#uti-"+i).children(".room").val().length > 3)			
			listroom += $("#uti-"+i).children(".room").val()+',';
		else
			listroom += 'null'+',';		
	}
	stdata = {"defensesession":defensesession,"listthesis":listthesis,"listexaminer1":listexaminer1,"listexaminer2":listexaminer2,"listpresident":listpresident,"listsecretary":listsecretary,"listcommissioner":listcommissioner,"listslot":listslot,"listroom":listroom};
	$.ajax({
		type: "POST",
		url:'/webapp/mm/save-defense.html',
		data:stdata,
		dataType: "text",
		success:function(s,x){		
			$("#status").attr("class", "alert alert-success");	
			$("#status").html(s);
		},
		error:function(data, textStatus, jqXHR){
			alert(textStatus);
		}
	});
	
});

	
$('#cancel').click(function() {
	window.location = baseUrl + "/mm/defensesession.html";
});
	
function editJury(the_sz_TypeForm, the_sz_Id) {
					
  	var sz_currentElement = '';
  	var sz_NameInputChanged = '';
    var cDialog, form,
    name = $( "#master-name" ),
    allFields = $( [] ).add( name ),
    tips = $( ".validateTips" );
    var nameVal = '', 
    	nameTitle = '';
	function addUser() {
	   	switch (the_sz_TypeForm){
	   		case 'dialog-form-master-outer':					    		
				nameVal= $("select#master-name-outer option:selected").val();
			    nameTitle = $("select#master-name-outer option:selected").text();
			break;
			case 'dialog-form-master-inner':					    		
				nameVal= $("select#master-name-inner option:selected").val();
				nameTitle = $("select#master-name-inner option:selected").text();
			break;					    	
			case 'dialog-form-room':
				nameVal= $("select#room-add option:selected").val();
			    nameTitle = $("select#room-add option:selected").text();
			break;
			case 'dialog-form-slot' :
				nameVal= $("select#slot-add option:selected").val();
				nameTitle= $("select#slot-add option:selected").text();
			break;
			case 'dialog-form-info' :
				nameVal= $("input#jury-info").val();
				nameTitle= $("input#jury-info").val();
			break;
			default :
				nameVal= $("input#jury-info").val();
				nameTitle= $("input#jury-info").val();
			}
				      
			$( sz_currentElement ).html("");
			if ( nameVal != "" ) {
				a_elements = the_sz_Id.split("-");
				var id = a_elements[1];
				var element = a_elements[0];
				$("#val-"+the_sz_Id).html(nameTitle);				
				$("#uti-"+id).children("."+element).val(nameVal);
			    cDialog.dialog( "close" );
			}
			check();
			return true;
	}
				 	
   cDialog = $( "#"+the_sz_TypeForm ).dialog({
	   autoOpen: false,
	   height: 170,
	   width: 350,
      modal: true,
      buttons: {
        "Sửa": addUser,
        "Hủy": function() {
            cDialog.dialog( "close" );
        }
      },
      close: function() {
        form[ 0 ].reset();
        allFields.removeClass( "ui-state-error" );
      }
    });
 
    form = cDialog.find( "form" ).on( "submit", function( event ) {
      event.preventDefault();				      
      addUser();      
    });
  
    cDialog.dialog( "open" );
}

function deleteJury(the_sz_Id) {
	
	a_elements = the_sz_Id.split("-");
	var id = a_elements[1];
	var element = a_elements[0];
	$("#val-"+the_sz_Id).html("Chưa xếp");				
	$("#uti-"+id).children("."+element).val("");	
	check();
}


$(".generatePDF").click(function () {    	
	studentName = $(this).parent().siblings(".studentNameCol").html();
	thesisCode = $(this).parent().siblings(".thesisCode").val();
	mentorName = $(this).parent().siblings(".mentorNameCol").html();
	examiner1 = $(this).siblings(".uti").children(".examiner1").val();
	examiner2 = $(this).siblings(".uti").children(".examiner2").val();
	president = $(this).siblings(".uti").children(".president").val();
	secretary = $(this).siblings(".uti").children(".secretary").val();
	commissioner = $(this).siblings(".uti").children(".commissioner").val();
	slot = $(this).siblings(".uti").children(".slot").val();
	room = $(this).siblings(".uti").children(".room").val();
		
	stdata = {"studentName":studentName,"thesisCode":thesisCode,"mentorName":mentorName,"examiner1":examiner1,"examiner2":examiner2,"president":president,"secretary":secretary,"commissioner":commissioner,"slot":slot,"room":room};
	$.ajax({
		type: "POST",
		url:'/webapp/mmservice/generateJuryPdf.html',
		data:stdata,
		dataType: "text",
		success:function(s,x){
			window.open('/webapp/mm/viewPdf/'+s+'.html','_blank');
			/*stdata = {"masterDefenseJuryThesis_ID":s};
			$.ajax({
				type: "POST",
				url:'/webapp/mmservice/viewpdf.html',
				data:stdata,
				dataType: "application/pdf",
				success:function(s,x){
					//window.open('/webapp/mm/viewPdf/'+s+'.html','_blank');
					
					
					
				},
				error:function(data, textStatus, jqXHR){
					alert(textStatus);
				}
			});	*/
			
			
		},
		error:function(data, textStatus, jqXHR){
			alert(textStatus);
		}
	});
});

$(".clear-row").click(function(){
	uti = $(this).parents();
	
	$(uti).siblings(".examiner1Col").children().eq(0).html("Chưa xếp");	
	$(uti).siblings(".examiner2Col").children().eq(0).html("Chưa xếp");
	$(uti).siblings(".presidentCol").children().eq(0).html("Chưa xếp");
	$(uti).siblings(".secretaryCol").children().eq(0).html("Chưa xếp");
	$(uti).siblings(".commissionerCol").children().eq(0).html("Chưa xếp");
	$(uti).siblings(".slotCol").children().eq(0).html("Chưa xếp");
	$(uti).siblings(".roomCol").children().eq(0).html("Chưa xếp");	
	
	$(this).siblings(".uti").children(".examiner1").val("");	
	$(this).siblings(".uti").children(".examiner2").val("");
	$(this).siblings(".uti").children(".president").val("");
	$(this).siblings(".uti").children(".secretary").val("");
	$(this).siblings(".uti").children(".commissioner").val("");
	$(this).siblings(".uti").children(".slot").val("");
	$(uti).siblings(".uti").children(".room").val("");	
	
	check();
	
});

$("#excel").click(function(e) {
	var filename = $('#file-name').val();
	tableToExcel('dataTables-Juries', filename);
});

$('#autoSchedule').click(function(){
	size = $("#listMasterDefenseJuryThesisSize").val();
	defensesession = $("#defenseSession").val();
	listsupervisor = "";
	listthesis = "";
	listexaminer1 = "";
	listexaminer2 = "";
	listpresident = "";
	listsecretary = "";
	listcommissioner = "";
	listslot = "";
	listroom = "";
	for(i=0;i<size;i++){
		
		listsupervisor += $("#uti-"+i).children(".supervisor").val()+',';
		
		if($("#uti-"+i).children(".thesis").val().length > 3)
			listthesis += $("#uti-"+i).children(".thesis").val()+',';
		else
			listthesis += 'null'+',';
		
		if($("#uti-"+i).children(".examiner1").val().length > 3)			
			listexaminer1 += $("#uti-"+i).children(".examiner1").val()+',';
		else
			listexaminer1 += 'null'+',';
		
		if($("#uti-"+i).children(".examiner2").val().length > 3)			
			listexaminer2 += $("#uti-"+i).children(".examiner2").val()+',';
		else
			listexaminer2 += 'null'+',';
		
		if($("#uti-"+i).children(".president").val().length > 3)			
			listpresident += $("#uti-"+i).children(".president").val()+',';
		else
			listpresident += 'null'+',';
		
		if($("#uti-"+i).children(".secretary").val().length > 3)			
			listsecretary += $("#uti-"+i).children(".secretary").val()+',';
		else
			listsecretary += 'null'+',';
		
		if($("#uti-"+i).children(".commissioner").val().length > 3)			
			listcommissioner += $("#uti-"+i).children(".commissioner").val()+',';
		else
			listcommissioner += 'null'+',';
		
		if($("#uti-"+i).children(".slot").val().length > 3)			
			listslot += $("#uti-"+i).children(".slot").val()+',';
		else
			listslot += 'null'+',';
		
		if($("#uti-"+i).children(".room").val().length > 3)			
			listroom += $("#uti-"+i).children(".room").val()+',';
		else
			listroom += 'null'+',';		
	}
	stdata = {"defensesession":defensesession, "listsupervisor":listsupervisor,"listthesis":listthesis,"listexaminer1":listexaminer1,"listexaminer2":listexaminer2,"listpresident":listpresident,"listsecretary":listsecretary,"listcommissioner":listcommissioner,"listslot":listslot,"listroom":listroom};
	$.ajax({
		type: "POST",
		url:'/webapp/mm/autoSchedule.html',
		data:stdata,
		dataType: "json",
		timeout: 300000,
		beforeSend:function(){
			$("#status").attr("class","alert alert-info");
			$("#status").html("Lịch đang được xếp, xin vui lòng chờ ...");		
		},
		success:function(s,x){		
			
			size = $("#listMasterDefenseJuryThesisSize").val();
			for(i = 0;i < size; i++){				
								
				$("#uti-"+i).children(".examiner1").val(s[i][0]);
				$("#val-examiner1-"+i).html(s[i][1]);
				
				$("#uti-"+i).children(".examiner2").val(s[i][2]);
				$("#val-examiner2-"+i).html(s[i][3]);
				
				$("#uti-"+i).children(".president").val(s[i][4]);
				$("#val-president-"+i).html(s[i][5]);
				
				$("#uti-"+i).children(".secretary").val(s[i][6]);
				$("#val-secretary-"+i).html(s[i][7]);
				
				$("#uti-"+i).children(".commissioner").val(s[i][8]);
				$("#val-commissioner-"+i).html(s[i][9]);
				
				$("#uti-"+i).children(".slot").val(s[i][10]);
				$("#val-slot-"+i).html(s[i][11]);
				
				$("#uti-"+i).children(".room").val(s[i][12]);
				$("#val-room-"+i).html(s[i][13]);								
			}	
			resetcheck();
			$("#status").attr("class","alert alert-success");
			$("#status").html("Lịch đã được xếp tự động thành công");		
			
		},
		error:function(data, textStatus, jqXHR){
			$("#status").attr("class","alert alert-warning");
			$("#status").html("Không thể sắp xếp lịch tự động");
		}
	});	
});

function resetcheck(){
	
	size = $("#listMasterDefenseJuryThesisSize").val();	
	for(i=0; i<size; i++){
		$("#val-supervisor-"+i).attr("style", "");
		$("#val-examiner1-"+i).attr("style", "");
		$("#val-examiner2-"+i).attr("style", "");
		$("#val-president-"+i).attr("style", "");
		$("#val-secretary-"+i).attr("style", "");
		$("#val-commissioner-"+i).attr("style", "");
		$("#val-slot-"+i).attr("style", "");
		$("#val-room-"+i).attr("style", "");	
	}
	$("#status").attr("class","");
	$("#status").html("");

}

function check(){
	size = $("#listMasterDefenseJuryThesisSize").val();	
	var stdata = new Array(size);
	var vio = new Array(size);
	var count = 0;	
	
	row = $(".gradeX");
	row.each(function(){
		var uti = $(this).children(".utility").children(".uti");
		var supervisor = $(uti).children(".supervisor").val();
		var examiner1 = $(uti).children(".examiner1").val();
		var examiner2 = $(uti).children(".examiner2").val();
		var president = $(uti).children(".president").val();
		var secretary = $(uti).children(".secretary").val();
		var commissioner = $(uti).children(".commissioner").val();
		var slot = $(uti).children(".slot").val();
		var room = $(uti).children(".room").val();
				
		stdata[count] = new Array(9);
		stdata[count][0] = count;
		stdata[count][1] = supervisor;
		
		if(examiner1 == '')
			stdata[count][2] = 'null';
		else
			stdata[count][2] = examiner1;
		
		if(examiner2 == '')
			stdata[count][3] = 'null';
		else
			stdata[count][3] = examiner2;
		
		if(president == '')
			stdata[count][4] = 'null';
		else
			stdata[count][4] = president;
		
		if(secretary == '')
			stdata[count][5] = 'null';
		else
			stdata[count][5] = secretary;
		
		if(commissioner == '')
			stdata[count][6] = 'null';
		else
			stdata[count][6] = commissioner;
		
		if(slot == '')
			stdata[count][7] = 'null';
		else
			stdata[count][7] = slot;
		
		if(room == '')
			stdata[count][8] = 'null';
		else
			stdata[count][8] = room;
		
		vio[count] = new Array(8);
		vio[count][0] = 0;
		vio[count][1] = 0;
		vio[count][2] = 0;
		vio[count][3] = 0;
		vio[count][4] = 0;
		vio[count][5] = 0;
		vio[count][6] = 0;
		vio[count][7] = 0;
		count++;
	});
	
	violation = 0;
	for(i=0; i<count; i++){
		if(stdata[i][1] == stdata[i][3]){
			vio[i][0]++;
			vio[i][2]++;
			violation++;
		}
		
		if(stdata[i][1] == stdata[i][4]){			
			vio[i][0]++;
			vio[i][3]++;
			violation++;
		}
		
		if(stdata[i][1] == stdata[i][5]){			
			vio[i][0]++;
			vio[i][4]++;
			violation++;
		}
		
		if(stdata[i][3] == stdata[i][4] && stdata[i][3] != "null"){			
			vio[i][2]++;
			vio[i][3]++;
			violation++;
		}
		
		if(stdata[i][3] == stdata[i][5] && stdata[i][3] != "null"){			
			vio[i][2]++;
			vio[i][4]++;
			violation++;
		}
		
		if(stdata[i][4] == stdata[i][5] && stdata[i][4] != "null"){			
			vio[i][3]++;
			vio[i][4]++;
			violation++;
		}
		
		if(stdata[i][2] == stdata[i][6] && stdata[i][2] != "null"){			
			vio[i][1]++;
			vio[i][5]++;
			violation++;
		}		
	}
	
	for(i=0; i<count-1; i++)
		for(j=i+1; j<count; j++){
			if(stdata[i][7] == stdata[j][7] && stdata[i][7] != "null" &&
				stdata[i][8] == stdata[j][8] && stdata[i][8] != "null"){
				vio[i][6]++;
				vio[i][7]++;
				vio[j][6]++;
				vio[j][7]++;				
				violation++;
			}
			
			if(stdata[i][7] == stdata[j][7] && stdata[i][7] != "null" &&
					stdata[i][2] == stdata[j][2] && stdata[i][2] != "null"){
					vio[i][1]++;
					vio[i][6]++;
					vio[j][1]++;
					vio[j][6]++;					
					violation++;
				}
			
			if(stdata[i][7] == stdata[j][7] && stdata[i][7] != "null" &&
					stdata[i][2] == stdata[j][6] && stdata[i][2] != "null"){
					vio[i][1]++;
					vio[i][6]++;
					vio[j][5]++;
					vio[j][6]++;				
					violation++;
				}
			
			if(stdata[i][7] == stdata[j][7] && stdata[i][7] != "null" &&
					stdata[i][6] == stdata[j][2] && stdata[i][6] != "null"){
					vio[i][5]++;
					vio[i][6]++;
					vio[j][1]++;
					vio[j][6]++;				
					violation++;
				}
			
			if(stdata[i][7] == stdata[j][7] && stdata[i][7] != "null" &&
					stdata[i][6] == stdata[j][6] && stdata[i][2] != "null"){
					vio[i][5]++;
					vio[i][6]++;
					vio[j][5]++;
					vio[j][6]++;				
					violation++;
				}
			
			if(stdata[i][7] == stdata[j][7] && stdata[i][7] != "null" &&
					stdata[i][3] == stdata[j][3] && stdata[i][3] != "null"){
					vio[i][2]++;
					vio[i][6]++;
					vio[j][2]++;
					vio[j][6]++;				
					violation++;
				}
			
			if(stdata[i][7] == stdata[j][7] && stdata[i][7] != "null" &&
					stdata[i][3] == stdata[j][4] && stdata[i][3] != "null"){
					vio[i][2]++;
					vio[i][6]++;
					vio[j][3]++;
					vio[j][6]++;				
					violation++;
				}
			
			if(stdata[i][7] == stdata[j][7] && stdata[i][7] != "null" &&
					stdata[i][3] == stdata[j][5] && stdata[i][3] != "null"){
					vio[i][2]++;
					vio[i][6]++;
					vio[j][4]++;
					vio[j][6]++;				
					violation++;
				}
			
			if(stdata[i][7] == stdata[j][7] && stdata[i][7] != "null" &&
					stdata[i][4] == stdata[j][3] && stdata[i][4] != "null"){
					vio[i][3]++;
					vio[i][6]++;
					vio[j][2]++;
					vio[j][6]++;				
					violation++;
				}
			
			if(stdata[i][7] == stdata[j][7] && stdata[i][7] != "null" &&
					stdata[i][4] == stdata[j][4] && stdata[i][4] != "null"){
					vio[i][3]++;
					vio[i][6]++;
					vio[j][3]++;
					vio[j][6]++;				
					violation++;
				}
			
			if(stdata[i][7] == stdata[j][7] && stdata[i][7] != "null" &&
					stdata[i][4] == stdata[j][5] && stdata[i][4] != "null"){
					vio[i][3]++;
					vio[i][6]++;
					vio[j][4]++;
					vio[j][6]++;				
					violation++;
				}
			
			if(stdata[i][7] == stdata[j][7] && stdata[i][7] != "null" &&
					stdata[i][5] == stdata[j][3] && stdata[i][5] != "null"){
					vio[i][4]++;
					vio[i][6]++;
					vio[j][2]++;
					vio[j][6]++;				
					violation++;
				}
			
			if(stdata[i][7] == stdata[j][7] && stdata[i][7] != "null" &&
					stdata[i][5] == stdata[j][4] && stdata[i][5] != "null"){
					vio[i][4]++;
					vio[i][6]++;
					vio[j][3]++;
					vio[j][6]++;				
					violation++;
				}
			
			if(stdata[i][7] == stdata[j][7] && stdata[i][7] != "null" &&
					stdata[i][5] == stdata[j][5] && stdata[i][5] != "null"){
					vio[i][4]++;
					vio[i][6]++;
					vio[j][4]++;
					vio[j][6]++;				
					violation++;
				}
				
		
		}
	for(i=0; i<size; i++){
		if(vio[i][0] > 0){
			$("#val-supervisor-"+i).attr("style", "color:#FF0000;");			
		}
		else{
			$("#val-supervisor-"+i).attr("style", "");
		}
			
		if(vio[i][1] > 0){
			$("#val-examiner1-"+i).attr("style", "color:#FF0000;");			
		}
		else{
			$("#val-examiner1-"+i).attr("style", "");
		}
		if(vio[i][2] > 0){
			$("#val-examiner2-"+i).attr("style", "color:#FF0000;");			
		}
		else{
			$("#val-examiner2-"+i).attr("style", "");
		}
		if(vio[i][3] > 0){
			$("#val-president-"+i).attr("style", "color:#FF0000;");			
		}
		else{
			$("#val-president-"+i).attr("style", "");
		}
		if(vio[i][4] > 0){
			$("#val-secretary-"+i).attr("style", "color:#FF0000;");			
		}
		else{
			$("#val-secretary-"+i).attr("style", "");
		}
		if(vio[i][5] > 0){
			$("#val-commissioner-"+i).attr("style", "color:#FF0000;");			
		}
		else{
			$("#val-commissioner-"+i).attr("style", "");
		}
		if(vio[i][6] > 0){
			$("#val-slot-"+i).attr("style", "color:#FF0000;");			
		}
		else{
			$("#val-slot-"+i).attr("style", "");
		}
		if(vio[i][7] > 0){
			$("#val-room-"+i).attr("style", "color:#FF0000;");			
		}
		else{
			$("#val-room-"+i).attr("style", "");
		}
	}
	$("#status").attr("class","alert alert-info");
	$("#status").html("Lịch đã được kiểm tra, số vi phạm là : "+violation);

}

$('#checkSchedule').click(function(){		
	check();	
});

$('#reloadSchedule').click(function(){	
	defensesession = $("#defenseSession").val();
	stdata = {"defensesession":defensesession};	
	$.ajax({
		type: "POST",
		url:'/webapp/mm/reloadSchedule.html',
		data:stdata,
		dataType: "json",		
		success:function(s,x){	
			size = $("#listMasterDefenseJuryThesisSize").val();
			for(i = 0;i < size; i++){				
								
				$("#uti-"+i).children(".examiner1").val(s[i][0]);
				$("#val-examiner1-"+i).html(s[i][1]);
				
				$("#uti-"+i).children(".examiner2").val(s[i][2]);
				$("#val-examiner2-"+i).html(s[i][3]);
				
				$("#uti-"+i).children(".president").val(s[i][4]);
				$("#val-president-"+i).html(s[i][5]);
				
				$("#uti-"+i).children(".secretary").val(s[i][6]);
				$("#val-secretary-"+i).html(s[i][7]);
				
				$("#uti-"+i).children(".commissioner").val(s[i][8]);
				$("#val-commissioner-"+i).html(s[i][9]);
				
				$("#uti-"+i).children(".slot").val(s[i][10]);
				$("#val-slot-"+i).html(s[i][11]);
				
				$("#uti-"+i).children(".room").val(s[i][12]);
				$("#val-room-"+i).html(s[i][13]);								
			}	
			resetcheck();			
			$("#status").attr("class","alert alert-success");
			$("#status").html("Hội đồng đã được khởi tạo lại theo trạng thái đã lưu gần nhất");

		},
		error:function(data, textStatus, jqXHR){
			alert(textStatus);			
		}
	});
});






