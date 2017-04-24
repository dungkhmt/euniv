var doc = new jsPDF();
//doc.fromHTML($('#html-profile').get(0), 15, 15, {
//    'width': '100%',
//    'font': 'times'
//  },
//  function() {
//	  $('.preview-pane').attr('src', doc.output('dataurlstring'));
//  });
//
//$.get(window.location.origin + "/webapp/cp/profile-scientist/information?User_ID=10170", function(information) {
//	console.log(information)
//	
//});
$(document).ready(function(){
	var pdf = new jsPDF('p','pt','a4');
	pdf.addHTML($("#html-profile")[0],function() {
		$(".preview-pane")[0].src = pdf.output('datauristring');
	});

//var pdf = new jsPDF('p', 'pt', 'a4', true);
//pdf.setFont('Times New Roman');
//var canvas = pdf.canvas;
//canvas.height = 72 * 11;
//canvas.width=72 * 8.5;;
//// var width = 400;
//var a = document.getElementById("html-profile").outerHTML;
//console.log(a)
//
//html2pdf(document.getElementById("html-profile").outerHTML, pdf, function(pdf) {
//         $(".preview-pane")[0].src = pdf.output('datauristring');
//
//     }
// );
//
})