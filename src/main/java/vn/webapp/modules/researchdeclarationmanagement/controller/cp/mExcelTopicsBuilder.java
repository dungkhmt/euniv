package vn.webapp.modules.researchdeclarationmanagement.controller.cp;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import vn.webapp.modules.researchdeclarationmanagement.model.mPatents;
import vn.webapp.modules.researchdeclarationmanagement.model.mTopics;

public class mExcelTopicsBuilder extends AbstractExcelView {
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// get data model which is passed by the Spring container
		List<mTopics> listTopics = (List<mTopics>) model.get("topicsList");
		List<mPatents> listPatents = (List<mPatents>) model.get("patentsList");
		
		// create a new Excel sheet
		HSSFSheet sheet = workbook.createSheet("Bao cao khoa hoc");
		sheet.setDefaultColumnWidth(15);
		
		// preparing data
		String yearOfPaper = (String) model.get("yearOfPaper");
		String name  = (String) model.get("name");
		String staffEmail  = (String) model.get("staffEmail");
		String staffName  = (String) model.get("staffName");
		String staffPhone  = (String) model.get("staffPhone");
		String staffDepartementCode  = (String) model.get("staffDepartementCode");
		String staffDepartementName  = (String) model.get("staffDepartementName");
		
		int iCurrentRow = 1;
		/**
		 * 1. Create a cell for the title
		 */
		// create style for title cells
		CellStyle style = workbook.createCellStyle();
		Font fontTitle = workbook.createFont();
		fontTitle.setFontHeightInPoints((short)12);
		fontTitle.setFontName("Times New Roman");
		fontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fontTitle.setColor(HSSFColor.BLACK.index);
		style.setFont(fontTitle);
		
		// create title of the paper
		HSSFRow title = sheet.createRow(iCurrentRow);
		title.createCell(1).setCellValue("BẢNG KÊ KHAI KHỐI LƯỢNG NGHIÊN CỨU KHOA HỌC  NĂM HỌC " + yearOfPaper);
		title.getCell(1).setCellStyle(style);
		sheet.setColumnWidth(0, 400);
		sheet.addMergedRegion(new CellRangeAddress(
		            1, //first row (0-based)
		            1, //last row  (0-based)
		            1, //first column (0-based)
		            8  //last column  (0-based)
		    ));
		
		/**
		 * Create a cell for sub title
		 */
		// create style for sub title cells
		CellStyle styleSubtitle = workbook.createCellStyle();
		Font fontSubTitle = workbook.createFont();
		fontSubTitle.setFontHeightInPoints((short)10);
		fontSubTitle.setFontName("Times New Roman");
		fontSubTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fontSubTitle.setColor(HSSFColor.BLACK.index);
		styleSubtitle.setFont(fontSubTitle);
		
		iCurrentRow++;
		// create sub title of the paper
		HSSFRow subTitle = sheet.createRow(iCurrentRow);
		subTitle.createCell(1).setCellValue("(ĐỀ TÀI NGHIÊN CỨU KHOA HỌC CÁC CẤP – BẰNG ĐỘC QUYỀN SÁNG CHẾ/GIẢI PHÁP HỮU ÍCH)");
		subTitle.getCell(1).setCellStyle(styleSubtitle);
		sheet.addMergedRegion(new CellRangeAddress(
	            2, //first row (0-based)
	            2, //last row  (0-based)
	            1, //first column (0-based)
	            4  //last column  (0-based)
	    ));
		
		
		/**
		 * Create a cell for author's info
		 */
		CellStyle styleAuthorInfo = workbook.createCellStyle();
		Font fontAuthorInfo = workbook.createFont();
		fontAuthorInfo.setFontHeightInPoints((short)10);
		fontAuthorInfo.setFontName("Times New Roman");
		fontAuthorInfo.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fontAuthorInfo.setColor(HSSFColor.BLACK.index);
		styleAuthorInfo.setFont(fontAuthorInfo);
		
		iCurrentRow = 5;
		// Create a cell for Name - Phone
		HSSFRow authorInfo = sheet.createRow(iCurrentRow);
		authorInfo.createCell(1).setCellValue("Họ và tên cán bộ : " + staffName);
		authorInfo.getCell(1).setCellStyle(styleSubtitle);
		sheet.addMergedRegion(new CellRangeAddress(
	            5, //first row (0-based)
	            5, //last row  (0-based)
	            1, //first column (0-based)
	            6  //last column  (0-based)
	    ));
		
		authorInfo.createCell(8).setCellValue("Tel:   (CQ)  -   (NR) -   (DĐ): " + staffPhone);
		authorInfo.getCell(8).setCellStyle(styleSubtitle);
		sheet.addMergedRegion(new CellRangeAddress(
	            5, //first row (0-based)
	            5, //last row  (0-based)
	            8, //first column (0-based)
	            12  //last column  (0-based)
	    ));
		
		iCurrentRow++;
		HSSFRow authorUnitInfo = sheet.createRow(6);
		// Create a cell for Department - School
		authorUnitInfo.createCell(1).setCellValue("Bộ môn : " + staffDepartementName);
		authorUnitInfo.getCell(1).setCellStyle(styleSubtitle);
		sheet.addMergedRegion(new CellRangeAddress(
	            6, //first row (0-based)
	            6, //last row  (0-based)
	            1, //first column (0-based)
	            6  //last column  (0-based)
	    ));
		
		authorUnitInfo.createCell(8).setCellValue("Khoa (Viện, Trung tâm): CNTT&TT");
		authorUnitInfo.getCell(8).setCellStyle(styleSubtitle);
		sheet.addMergedRegion(new CellRangeAddress(
	            6, //first row (0-based)
	            6, //last row  (0-based)
	            8, //first column (0-based)
	            12  //last column  (0-based)
	    ));
		
		iCurrentRow = 9;
		// create sub title of the paper
		HSSFRow subTitle2 = sheet.createRow(iCurrentRow);
		subTitle2.createCell(1).setCellValue("I.  ĐỀ TÀI NGHIÊN CỨU KHOA HỌC CÁC CẤP:");
		subTitle2.getCell(1).setCellStyle(styleSubtitle);
		sheet.addMergedRegion(new CellRangeAddress(
	            2, //first row (0-based)
	            2, //last row  (0-based)
	            1, //first column (0-based)
	            4  //last column  (0-based)
	    ));
		
		/**
		 * Create the box
		 */
		// Style the cell with borders all around.
	    CellStyle styleBox = workbook.createCellStyle();
	    styleBox.setWrapText(true);
	    styleBox.setAlignment(CellStyle.ALIGN_CENTER);
	    styleBox.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	    styleBox.setBorderBottom(CellStyle.BORDER_THIN);
	    styleBox.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    styleBox.setBorderLeft(CellStyle.BORDER_THIN);
	    styleBox.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    styleBox.setBorderRight(CellStyle.BORDER_THIN);
	    styleBox.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    styleBox.setBorderTop(CellStyle.BORDER_THIN);
	    styleBox.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    styleBox.setFont(fontSubTitle);
	    
	    iCurrentRow = 11;
		HSSFRow boxHeader = sheet.createRow(iCurrentRow);
		boxHeader.createCell(1).setCellValue("STT");
		boxHeader.getCell(1).setCellStyle(styleBox);
		
		boxHeader.createCell(2).setCellValue("Tên đề tài (dự án), thời gian thực hiện. Những người cùng thực hiện, đơn vị, vai trò .");
		boxHeader.getCell(2).setCellStyle(styleBox);
		
		boxHeader.createCell(3).setCellValue("Kinh phí được cấp (Triệu đồng)");
		boxHeader.getCell(3).setCellStyle(styleBox);
		
		boxHeader.createCell(4).setCellValue("Đề tài KHCN, dự án, cấp Nhà nước");
		boxHeader.getCell(4).setCellStyle(styleBox);
		
		boxHeader.createCell(5).setCellValue("Đề tài, dự án cấp Bộ, thành phố và tương đương");
		boxHeader.getCell(5).setCellStyle(styleBox);
		
		boxHeader.createCell(6).setCellValue("Đề tài thuộc quỹ Nafosted");
		boxHeader.getCell(6).setCellStyle(styleBox);
		
		boxHeader.createCell(7).setCellValue("Đề tài, dự án, HTQT");
		boxHeader.getCell(7).setCellStyle(styleBox);
		
		boxHeader.createCell(8).setCellValue("Đề tài cấp trường");
		boxHeader.getCell(8).setCellStyle(styleBox);
		
		boxHeader.createCell(9).setCellValue("Số giờ quy đổi của  đề tài,  dự án ");
		boxHeader.getCell(9).setCellStyle(styleBox);
		
		
		boxHeader.createCell(10).setCellValue("Số giờ quy đổi cho người kê khai (I)");
		boxHeader.getCell(10).setCellStyle(styleBox);
		
		
		/**
		 * Show numbers of column
		 */
		iCurrentRow++;
		HSSFRow boxNo = sheet.createRow(iCurrentRow);
		for(int iNo = 1; iNo <=10; iNo++){
			boxNo.createCell(iNo).setCellValue(iNo);
			boxNo.getCell(iNo).setCellStyle(styleBox);
		}

		/**
		 * Show all contents for the first category
		 */
		CellStyle styleContent = workbook.createCellStyle();
		Font fontContent = workbook.createFont();
		fontContent.setFontHeightInPoints((short)10);
		fontContent.setFontName("Times New Roman");
		fontContent.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		fontContent.setColor(HSSFColor.BLACK.index);
		styleContent.setFont(fontContent);
		
		// Style the cell with borders all around.
	    CellStyle styleContentBox = workbook.createCellStyle();
	    styleContentBox.setBorderBottom(CellStyle.BORDER_THIN);
	    styleContentBox.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    styleContentBox.setBorderLeft(CellStyle.BORDER_THIN);
	    styleContentBox.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    styleContentBox.setBorderRight(CellStyle.BORDER_THIN);
	    styleContentBox.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    styleContentBox.setBorderTop(CellStyle.BORDER_THIN);
	    styleContentBox.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    styleContentBox.setFont(fontContent);
	    
		int iCount = 1;
		iCurrentRow++;
		int iTotalConvertedHours = 0;
		int iTotalAuthorConvertedHours = 0;
		for (mTopics aTopic : listTopics) { 
			
				HSSFRow boxFisrtCatContent = sheet.createRow(iCurrentRow);
				boxFisrtCatContent.createCell(1).setCellValue(iCount++);
				boxFisrtCatContent.getCell(1).setCellStyle(styleContentBox);
				
				// Name
				boxFisrtCatContent.createCell(2).setCellValue(aTopic.getPROJDECL_Name());
				boxFisrtCatContent.getCell(2).setCellStyle(styleContentBox);
				
				// Budget
				boxFisrtCatContent.createCell(3).setCellValue(aTopic.getPROJDECL_Budget());
				boxFisrtCatContent.getCell(3).setCellStyle(styleContentBox);
				
				String sChosen = "x";
				for(int iIterator = 4; iIterator <= 8; iIterator++){
					if(iIterator == 4 && aTopic.getPROJDECL_ProjCategory_Code().equals("NATIONAL")){
						// National level
						boxFisrtCatContent.createCell(iIterator).setCellValue(sChosen);
						boxFisrtCatContent.getCell(iIterator).setCellStyle(styleContentBox);
					}else if(iIterator == 5 && aTopic.getPROJDECL_ProjCategory_Code().equals("EMINISTRY")){
						// Ministry level
						boxFisrtCatContent.createCell(iIterator).setCellValue(sChosen);
						boxFisrtCatContent.getCell(iIterator).setCellStyle(styleContentBox);
					}else if(iIterator == 6 && aTopic.getPROJDECL_ProjCategory_Code().equals("SMINISTRY")){
						// Nafosted foundation level
						boxFisrtCatContent.createCell(iIterator).setCellValue(sChosen);
						boxFisrtCatContent.getCell(iIterator).setCellStyle(styleContentBox);
					}else if(iIterator == 7 && aTopic.getPROJDECL_ProjCategory_Code().equals("INTERNATIONAL")){
						// HTQT level
						boxFisrtCatContent.createCell(iIterator).setCellValue(sChosen);
						boxFisrtCatContent.getCell(iIterator).setCellStyle(styleContentBox);
					}else if(iIterator == 8 && aTopic.getPROJDECL_ProjCategory_Code().equals("UNIVERSTITY")){
						// University level
						boxFisrtCatContent.createCell(iIterator).setCellValue(sChosen);
						boxFisrtCatContent.getCell(iIterator).setCellStyle(styleContentBox);
					}else{
						boxFisrtCatContent.createCell(iIterator).setCellValue("");
						boxFisrtCatContent.getCell(iIterator).setCellStyle(styleContentBox);
					}
				}
				
				// Converted hours
				boxFisrtCatContent.createCell(9).setCellValue(aTopic.getPROJDECL_ConvertedHours());
				boxFisrtCatContent.getCell(9).setCellStyle(styleContentBox);
				
				// Author Converted hours
				boxFisrtCatContent.createCell(10).setCellValue(aTopic.getPROJDECL_AuthorConvertedHours());
				boxFisrtCatContent.getCell(10).setCellStyle(styleContentBox);
				
				iTotalConvertedHours += aTopic.getPROJDECL_ConvertedHours();
				iTotalAuthorConvertedHours += aTopic.getPROJDECL_AuthorConvertedHours();
				iCurrentRow++;
			
		}
		
		/**
		 * Papers - total line
		 */
		HSSFRow boxNoTotal = sheet.createRow(iCurrentRow);
		for(int iNo = 1; iNo <=10; iNo++){
			
			if(iNo == 2){
				boxNoTotal.createCell(iNo).setCellValue("Tổng cộng");
				boxNoTotal.getCell(iNo).setCellStyle(styleBox);
			}else if (iNo == 9){
				boxNoTotal.createCell(iNo).setCellValue(iTotalConvertedHours);
				boxNoTotal.getCell(iNo).setCellStyle(styleBox);
			}else if (iNo == 10){
				boxNoTotal.createCell(iNo).setCellValue(iTotalAuthorConvertedHours);
				boxNoTotal.getCell(iNo).setCellStyle(styleBox);
			}else{
				boxNoTotal.createCell(iNo).setCellValue("");
				boxNoTotal.getCell(iNo).setCellStyle(styleBox);
			}
		}
		
		/**
		 * Part 2 - Patents
		 */
		iCurrentRow += 4;
		// create sub title of the paper
		HSSFRow patentTitle = sheet.createRow(iCurrentRow);
		patentTitle.createCell(1).setCellValue("II.  BẰNG ĐỘC QUYỀN SÁNG CHẾ/GIẢI PHÁP HỮU ÍCH:");
		patentTitle.getCell(1).setCellStyle(styleSubtitle);
		sheet.addMergedRegion(new CellRangeAddress(
	            2, //first row (0-based)
	            2, //last row  (0-based)
	            1, //first column (0-based)
	            4  //last column  (0-based)
	    ));
		
		iCurrentRow +=2;
		HSSFRow boxPaptentHeader = sheet.createRow(iCurrentRow);
		boxPaptentHeader.createCell(1).setCellValue("STT");
		boxPaptentHeader.getCell(1).setCellStyle(styleBox);
		
		boxPaptentHeader.createCell(2).setCellValue("Tên tác giả/các tác giả ");
		boxPaptentHeader.getCell(2).setCellStyle(styleBox);
		
		boxPaptentHeader.createCell(3).setCellValue("Loại văn bằng");
		boxPaptentHeader.getCell(3).setCellStyle(styleBox);
		
		boxPaptentHeader.createCell(4).setCellValue("Số bằng");
		boxPaptentHeader.getCell(4).setCellStyle(styleBox);
		
		boxPaptentHeader.createCell(5).setCellValue("Tên Sáng chế/Giải pháp hữu ích");
		boxPaptentHeader.getCell(5).setCellStyle(styleBox);
		
		boxPaptentHeader.createCell(6).setCellValue("Ngày tháng năm được cấp");
		boxPaptentHeader.getCell(6).setCellStyle(styleBox);
		
		boxPaptentHeader.createCell(7).setCellValue("Số giờ quy đổi của văn bằng");
		boxPaptentHeader.getCell(7).setCellStyle(styleBox);
		
		boxPaptentHeader.createCell(8).setCellValue("Số giờ quy đổi  cho người kê khai (II)");
		boxPaptentHeader.getCell(8).setCellStyle(styleBox);
		
		/**
		 * Show numbers of column patents listPatents
		 */
		iCurrentRow++;
		HSSFRow boxPatentNo = sheet.createRow(iCurrentRow);
		for(int iNo = 1; iNo <=8; iNo++){
			boxPatentNo.createCell(iNo).setCellValue(iNo);
			boxPatentNo.getCell(iNo).setCellStyle(styleBox);
		}
		
		// Show all patents
		int iTotalPatentsConvertedHours = 0;
		int iTotalPatentsAuthorConvertedHours = 0;
		if(listPatents != null){
			iCount = 1;
			iCurrentRow++;
			for (mPatents aPatent : listPatents) { 
				
				HSSFRow boxFisrtCatContent = sheet.createRow(iCurrentRow);
				boxFisrtCatContent.createCell(1).setCellValue(iCount++);
				boxFisrtCatContent.getCell(1).setCellStyle(styleContentBox);
				
				// Authors
				boxFisrtCatContent.createCell(2).setCellValue(aPatent.getPAT_Authors());
				boxFisrtCatContent.getCell(2).setCellStyle(styleContentBox);
				
				// Category
				boxFisrtCatContent.createCell(3).setCellValue(aPatent.getPAT_Type());
				boxFisrtCatContent.getCell(3).setCellStyle(styleContentBox);
				
				// Serial
				boxFisrtCatContent.createCell(4).setCellValue(aPatent.getPAT_Number());
				boxFisrtCatContent.getCell(4).setCellStyle(styleContentBox);
				
				// Name
				boxFisrtCatContent.createCell(5).setCellValue(aPatent.getPAT_Name());
				boxFisrtCatContent.getCell(5).setCellStyle(styleContentBox);
				
				// Date Of Issue
				boxFisrtCatContent.createCell(6).setCellValue(aPatent.getPAT_DateOfIssue());
				boxFisrtCatContent.getCell(6).setCellStyle(styleContentBox);
				
				// Converted hours
				boxFisrtCatContent.createCell(7).setCellValue(aPatent.getPAT_ConvertedHours());
				boxFisrtCatContent.getCell(7).setCellStyle(styleContentBox);
				
				// Author Converted hours
				boxFisrtCatContent.createCell(8).setCellValue(aPatent.getPAT_AuthorConvertedHours());
				boxFisrtCatContent.getCell(8).setCellStyle(styleContentBox);
				
				// For patents
				iTotalPatentsConvertedHours += aPatent.getPAT_ConvertedHours();
				iTotalPatentsAuthorConvertedHours += aPatent.getPAT_AuthorConvertedHours();
				
				// For patents and papers
				iTotalAuthorConvertedHours += aPatent.getPAT_AuthorConvertedHours();
				iCurrentRow++;
			}
		}
		
		/**
		 * Patents - total line
		 */
		HSSFRow boxPatentNoTotal = sheet.createRow(iCurrentRow);
		for(int iNo = 1; iNo <=8; iNo++){
			if(iNo == 2){
				boxPatentNoTotal.createCell(iNo).setCellValue("Tổng cộng");
				boxPatentNoTotal.getCell(iNo).setCellStyle(styleBox);
			}else if (iNo == 7){
				boxPatentNoTotal.createCell(iNo).setCellValue(iTotalPatentsConvertedHours);
				boxPatentNoTotal.getCell(iNo).setCellStyle(styleBox);
			}else if (iNo == 8){
				boxPatentNoTotal.createCell(iNo).setCellValue(iTotalPatentsAuthorConvertedHours);
				boxPatentNoTotal.getCell(iNo).setCellStyle(styleBox);
			}else{
				boxPatentNoTotal.createCell(iNo).setCellValue("");
				boxPatentNoTotal.getCell(iNo).setCellStyle(styleBox);
			}
		}
				
		/**
		 * Confirmation and Signature
		 */
		iCurrentRow += 2;
		HSSFRow totalConvertedTime = sheet.createRow(iCurrentRow);
		totalConvertedTime.createCell(4).setCellValue("Tổng số giờ qui đổi của người kê khai = "+iTotalAuthorConvertedHours);
		totalConvertedTime.getCell(4).setCellStyle(styleSubtitle);
		
		iCurrentRow +=3 ;
		HSSFRow dateEnd = sheet.createRow(iCurrentRow);
		dateEnd.createCell(6).setCellValue("Hà Nội, Ngày.........tháng.........năm.........");
		dateEnd.getCell(6).setCellStyle(styleSubtitle);
		
		iCurrentRow++;
		HSSFRow confirmation = sheet.createRow(iCurrentRow);
		confirmation.createCell(2).setCellValue("Xác nhận của Khoa (Viện , TT)");
		confirmation.getCell(2).setCellStyle(styleSubtitle);
		
		confirmation.createCell(4).setCellValue("Xác nhận của Bộ môn");
		confirmation.getCell(4).setCellStyle(styleSubtitle);
		
		confirmation.createCell(6).setCellValue("Người kê khai");
		confirmation.getCell(6).setCellStyle(styleSubtitle);
		
	}
}
