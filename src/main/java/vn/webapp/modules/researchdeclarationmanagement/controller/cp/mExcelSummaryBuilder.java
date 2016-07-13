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

import vn.webapp.modules.researchdeclarationmanagement.model.mPapers;
import vn.webapp.modules.researchdeclarationmanagement.model.mPatents;
import vn.webapp.modules.researchdeclarationmanagement.model.mTopics;

public class mExcelSummaryBuilder extends AbstractExcelView {
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// get data model which is passed by the Spring container
		List<mTopics> listTopics = (List<mTopics>) model.get("topicsList");
		List<mPatents> listPatents = (List<mPatents>) model.get("patentsList");
		
		List<List<String>> summaryPapersList = (List<List<String>>) model.get("summaryPapersList");
		List<List<String>> summaryProjectsList = (List<List<String>>) model.get("summaryProjectsList");
		Map<String, List<mPapers>> summaryAllStaffsList = (Map<String, List<mPapers>>) model.get("summaryAllStaffsList");
		
		// To generate papers summary
		this.buildExcelDocumentSummaryPapers(model, workbook, summaryPapersList);
		
		// To generate projects summary
		this.buildExcelDocumentSummaryProject(model, workbook, summaryProjectsList);
		
		// To generate all staff's papers summary
		this.buildExcelDocumentSummary(model, workbook, summaryAllStaffsList);
		
		response.setHeader("Content-Disposition", "attachement; filename=\"" + "mau-ke-khai-"  + model.get("departmentCode") + 
				"-01BM-02BM-03BM.xls\"");
		
	}
	
	/**
	 * To generate papers summary
	 * @param model
	 * @param workbook
	 */
	public void buildExcelDocumentSummaryPapers(Map<String, Object> model, HSSFWorkbook workbook, List<List<String>> summaryPapersList){
		// create a new Excel sheet
		HSSFSheet sheet = workbook.createSheet("01-BM");
		sheet.setDefaultColumnWidth(15);
		
		// preparing data
		String yearOfPaper = (String) model.get("yearOfPaper");
		String staffDepartementName  = (String) model.get("staffDepartementName");
		
		int iCurrentRow = 3;
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
		title.createCell(1).setCellValue("BẢNG TỔNG HỢP KÊ KHAI KHỐI LƯỢNG NGHIÊN CỨU KHOA HỌC  NĂM HỌC " + yearOfPaper);
		title.getCell(1).setCellStyle(style);
		sheet.setColumnWidth(0, 400);
		sheet.addMergedRegion(new CellRangeAddress(
				iCurrentRow, //first row (0-based)
				iCurrentRow, //last row  (0-based)
	            1, //first column (0-based)
	            7  //last column  (0-based)
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
		subTitle.createCell(1).setCellValue("(BÀI BÁO KHOA HỌC)");
		subTitle.getCell(1).setCellStyle(styleSubtitle);
		sheet.addMergedRegion(new CellRangeAddress(
				iCurrentRow, //first row (0-based)
				iCurrentRow, //last row  (0-based)
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
		
		iCurrentRow += 3;
		HSSFRow authorUnitInfo = sheet.createRow(6);
		// Create a cell for Department - School
		authorUnitInfo.createCell(1).setCellValue("Bộ môn : " + staffDepartementName);
		authorUnitInfo.getCell(1).setCellStyle(styleSubtitle);
		sheet.addMergedRegion(new CellRangeAddress(
	            6, //first row (0-based)
	            6, //last row  (0-based)
	            1, //first column (0-based)
	            2  //last column  (0-based)
	    ));
		
		authorUnitInfo.createCell(5).setCellValue("Khoa (Viện, Trung tâm): Viện Công nghệ thông tin và Truyền thông");
		authorUnitInfo.getCell(5).setCellStyle(styleSubtitle);
		sheet.addMergedRegion(new CellRangeAddress(
	            6, //first row (0-based)
	            6, //last row  (0-based)
	            5, //first column (0-based)
	            8  //last column  (0-based)
	    ));
		
		/**
		 * Style for header
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
	    
	    /**
		 * Style for content
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
	    
		iCurrentRow += 3;
		HSSFRow boxHeader = sheet.createRow(iCurrentRow);
		boxHeader.createCell(1).setCellValue("STT");
		boxHeader.getCell(1).setCellStyle(styleBox);
		sheet.addMergedRegion(new CellRangeAddress(
				iCurrentRow, //first row (0-based)
	            (iCurrentRow+1), //last row  (0-based)
	            1, //first column (0-based)
	            1  //last column  (0-based)
	    ));
		
		
		boxHeader.createCell(2).setCellValue("Họ và tên");
		boxHeader.getCell(2).setCellStyle(styleBox);
		sheet.addMergedRegion(new CellRangeAddress(
				iCurrentRow, //first row (0-based)
				(iCurrentRow+1), //last row  (0-based)
	            2, //first column (0-based)
	            2  //last column  (0-based)
	    ));
		
		boxHeader.createCell(3).setCellValue("Tổng số bài báo đăng tạp chí, Proceeding");
		boxHeader.getCell(3).setCellStyle(styleBox);
		sheet.addMergedRegion(new CellRangeAddress(
				iCurrentRow, //first row (0-based)
				iCurrentRow, //last row  (0-based)
	            3, //first column (0-based)
	            4  //last column  (0-based)
	    ));
		
		boxHeader.createCell(5).setCellValue("Tổng số giờ quy đổi của người kê khai (I)");
		boxHeader.getCell(5).setCellStyle(styleBox);
		sheet.addMergedRegion(new CellRangeAddress(
				iCurrentRow, //first row (0-based)
				(iCurrentRow+1), //last row  (0-based)
	            5, //first column (0-based)
	            5  //last column  (0-based)
	    ));
		
		iCurrentRow++;
		HSSFRow boxHeader2 = sheet.createRow(iCurrentRow);
		boxHeader2.createCell(3).setCellValue("Tạp chí");
		boxHeader2.getCell(3).setCellStyle(styleBox);
		
		boxHeader2.createCell(4).setCellValue("Proceedings");
		boxHeader2.getCell(4).setCellStyle(styleBox);
		
		/**
		 * Show content papers summary
		 */
		int iCount = 1;
		iCurrentRow++;
		int iTotalConvertedHours = 0;
		for (List<String> paperSummary : summaryPapersList) { 
			
			HSSFRow boxFisrtCatContent = sheet.createRow(iCurrentRow);
			
			boxFisrtCatContent.createCell(1).setCellValue(iCount++);
			boxFisrtCatContent.getCell(1).setCellStyle(styleContentBox);
			
			boxFisrtCatContent.createCell(2).setCellValue(paperSummary.get(0));
			boxFisrtCatContent.getCell(2).setCellStyle(styleContentBox);
			
			boxFisrtCatContent.createCell(3).setCellValue(paperSummary.get(1));
			boxFisrtCatContent.getCell(3).setCellStyle(styleContentBox);
			
			boxFisrtCatContent.createCell(4).setCellValue(paperSummary.get(2));
			boxFisrtCatContent.getCell(4).setCellStyle(styleContentBox);
			
			boxFisrtCatContent.createCell(5).setCellValue(paperSummary.get(3));
			boxFisrtCatContent.getCell(5).setCellStyle(styleContentBox);
			
			iTotalConvertedHours += Integer.parseInt(paperSummary.get(3));
			iCurrentRow++;
		}
		
		HSSFRow totalPaperSummary = sheet.createRow(iCurrentRow);
		totalPaperSummary.createCell(1).setCellValue("");
		totalPaperSummary.getCell(1).setCellStyle(styleContentBox);
		
		totalPaperSummary.createCell(2).setCellValue("Total");
		totalPaperSummary.getCell(2).setCellStyle(styleBox);
		
		totalPaperSummary.createCell(3).setCellValue("");
		totalPaperSummary.getCell(3).setCellStyle(styleContentBox);
		
		totalPaperSummary.createCell(4).setCellValue("");
		totalPaperSummary.getCell(4).setCellStyle(styleContentBox);
		
		totalPaperSummary.createCell(5).setCellValue(Integer.toString(iTotalConvertedHours));
		totalPaperSummary.getCell(5).setCellStyle(styleBox);
		
		/**
		 * Note
		 */
		iCurrentRow += 3;
		HSSFRow note = sheet.createRow(iCurrentRow);
		note.createCell(1).setCellValue("Ghi chú:  Mục (I)  lấy từ bản kê khai của từng cá nhân");
		note.getCell(1).setCellStyle(styleContent);
		
		/**
		 * Date
		 */
		iCurrentRow += 4;
		HSSFRow dateConfirmation = sheet.createRow(iCurrentRow);
		dateConfirmation.createCell(4).setCellValue("Hà Nội, Ngày ....... tháng ....... năm .........");
		dateConfirmation.getCell(4).setCellStyle(styleContent);
		
		/**
		 * Confirmations
		 */
		iCurrentRow += 1;
		HSSFRow confirmation = sheet.createRow(iCurrentRow);
		confirmation.createCell(1).setCellValue("TRƯỞNG BỘ MÔN");
		confirmation.getCell(1).setCellStyle(styleSubtitle);
		
		confirmation.createCell(4).setCellValue("TRƯỞNG KHOA/VIỆN/TT");
		confirmation.getCell(4).setCellStyle(styleSubtitle);
	}
	
	
	/**
	 * To generate 2nd sheet (projects summary)
	 * @param model
	 * @param workbook
	 */
	public void buildExcelDocumentSummaryProject(Map<String, Object> model, HSSFWorkbook workbook, List<List<String>> summaryProjectsList){
		HSSFSheet sheet = workbook.createSheet("02-BM");
		
		sheet.setDefaultColumnWidth(15);
		
		// preparing data
		String yearOfPaper = (String) model.get("yearOfPaper");
		String staffDepartementName  = (String) model.get("staffDepartementName");
		
		int iCurrentRow = 3;
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
		title.createCell(1).setCellValue("BẢNG TỔNG HỢP KÊ KHAI KHỐI LƯỢNG NGHIÊN CỨU KHOA HỌC  NĂM HỌC " + yearOfPaper);
		title.getCell(1).setCellStyle(style);
		sheet.setColumnWidth(0, 400);
		sheet.addMergedRegion(new CellRangeAddress(
				iCurrentRow, //first row (0-based)
				iCurrentRow, //last row  (0-based)
		            1, //first column (0-based)
		            12  //last column  (0-based)
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
				iCurrentRow, //first row (0-based)
				iCurrentRow, //last row  (0-based)
	            1, //first column (0-based)
	            10  //last column  (0-based)
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
		
		iCurrentRow += 3;
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
		
		authorUnitInfo.createCell(8).setCellValue("Khoa (Viện, Trung tâm): Viện Công nghệ thông tin và Truyền thông");
		authorUnitInfo.getCell(8).setCellStyle(styleSubtitle);
		sheet.addMergedRegion(new CellRangeAddress(
	            6, //first row (0-based)
	            6, //last row  (0-based)
	            8, //first column (0-based)
	            14  //last column  (0-based)
	    ));
		
		/**
		 * Style for header
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
	    
	    /**
		 * Style for content
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
	    
		iCurrentRow += 3;
		HSSFRow boxHeader = sheet.createRow(iCurrentRow);
		boxHeader.createCell(1).setCellValue("STT");
		boxHeader.getCell(1).setCellStyle(styleBox);
		sheet.addMergedRegion(new CellRangeAddress(
				iCurrentRow, //first row (0-based)
	            (iCurrentRow+1), //last row  (0-based)
	            1, //first column (0-based)
	            1  //last column  (0-based)
	    ));
		
		
		boxHeader.createCell(2).setCellValue("Họ và tên");
		boxHeader.getCell(2).setCellStyle(styleBox);
		sheet.addMergedRegion(new CellRangeAddress(
				iCurrentRow, //first row (0-based)
				(iCurrentRow+1), //last row  (0-based)
	            2, //first column (0-based)
	            2  //last column  (0-based)
	    ));
		
		
		boxHeader.createCell(3).setCellValue("Tổng số đề tài, dự án NCKH");
		boxHeader.getCell(3).setCellStyle(styleBox);
		sheet.addMergedRegion(new CellRangeAddress(
				iCurrentRow, //first row (0-based)
				iCurrentRow, //last row  (0-based)
	            3, //first column (0-based)
	            8  //last column  (0-based)
	    ));
		
		boxHeader.createCell(9).setCellValue("Tổng số giờ quy đổi của người kê khai (I)");
		boxHeader.getCell(9).setCellStyle(styleBox);
		sheet.addMergedRegion(new CellRangeAddress(
				iCurrentRow, //first row (0-based)
				(iCurrentRow+1), //last row  (0-based)
	            9, //first column (0-based)
	            9  //last column  (0-based)
	    ));
		
		iCurrentRow++;
		HSSFRow boxHeader2 = sheet.createRow(iCurrentRow);
		boxHeader2.createCell(3).setCellValue("Đề tài KHCN, dự án cấp Nhà nước");
		boxHeader2.getCell(3).setCellStyle(styleBox);
		
		boxHeader2.createCell(4).setCellValue("Đề tài, dự án  cấp Bộ, thành phố và tương đương");
		boxHeader2.getCell(4).setCellStyle(styleBox);
		
		boxHeader2.createCell(5).setCellValue("Đề tài thuộc quỹ Nafosted");
		boxHeader2.getCell(5).setCellStyle(styleBox);
		
		boxHeader2.createCell(6).setCellValue("ĐT, dự án hợp tác quốc tế");
		boxHeader2.getCell(6).setCellStyle(styleBox);
		
		boxHeader2.createCell(7).setCellValue("TĐ cấp trường");
		boxHeader2.getCell(7).setCellStyle(styleBox);
		
		boxHeader2.createCell(8).setCellValue("Bằng độc quyền Sáng chế/Giải pháp hữu ích");
		boxHeader2.getCell(8).setCellStyle(styleBox);
		
		/**
		 * Show content papers summary
		 */
		String sNationalTotal = "";
		String sMinistryTotal = "";
		String sFoundationTotal = "";
		String sInternationalTotal = "";
		String sUniversityTotal = "";
		
		int iCount = 1;
		iCurrentRow++;
		int iTotalConvertedHours = 0;
		for (List<String> topicSummary : summaryProjectsList) { 
			
			HSSFRow boxFisrtCatContent = sheet.createRow(iCurrentRow);
			
			boxFisrtCatContent.createCell(1).setCellValue(iCount++);
			boxFisrtCatContent.getCell(1).setCellStyle(styleContentBox);
			
			boxFisrtCatContent.createCell(2).setCellValue(topicSummary.get(0));
			boxFisrtCatContent.getCell(2).setCellStyle(styleContentBox);
			
			sNationalTotal = (!topicSummary.get(1).equals("0")) ? topicSummary.get(1) : "";
			boxFisrtCatContent.createCell(3).setCellValue(sNationalTotal);
			boxFisrtCatContent.getCell(3).setCellStyle(styleContentBox);
			
			sMinistryTotal = (!topicSummary.get(2).equals("0")) ? topicSummary.get(2) : "";
			boxFisrtCatContent.createCell(4).setCellValue(sMinistryTotal);
			boxFisrtCatContent.getCell(4).setCellStyle(styleContentBox);
			
			sFoundationTotal = (!topicSummary.get(3).equals("0")) ? topicSummary.get(3) : "";
			boxFisrtCatContent.createCell(5).setCellValue(sFoundationTotal);
			boxFisrtCatContent.getCell(5).setCellStyle(styleContentBox);
			
			sInternationalTotal = (!topicSummary.get(4).equals("0")) ? topicSummary.get(4) : "";
			boxFisrtCatContent.createCell(6).setCellValue(sInternationalTotal);
			boxFisrtCatContent.getCell(6).setCellStyle(styleContentBox);
			
			sUniversityTotal = (!topicSummary.get(5).equals("0")) ? topicSummary.get(5) : "";
			boxFisrtCatContent.createCell(7).setCellValue(sUniversityTotal);
			boxFisrtCatContent.getCell(7).setCellStyle(styleContentBox);
			
			boxFisrtCatContent.createCell(8).setCellValue(topicSummary.get(6));
			boxFisrtCatContent.getCell(8).setCellStyle(styleContentBox);
			
			boxFisrtCatContent.createCell(9).setCellValue(topicSummary.get(7));
			boxFisrtCatContent.getCell(9).setCellStyle(styleContentBox);
			
			iTotalConvertedHours += Integer.parseInt(topicSummary.get(7));
			iCurrentRow++;
		}
		
		HSSFRow totalPaperSummary = sheet.createRow(iCurrentRow);
		totalPaperSummary.createCell(1).setCellValue("");
		totalPaperSummary.getCell(1).setCellStyle(styleContentBox);
		
		totalPaperSummary.createCell(2).setCellValue("Total");
		totalPaperSummary.getCell(2).setCellStyle(styleBox);
		
		totalPaperSummary.createCell(3).setCellValue("");
		totalPaperSummary.getCell(3).setCellStyle(styleContentBox);
		
		totalPaperSummary.createCell(4).setCellValue("");
		totalPaperSummary.getCell(4).setCellStyle(styleContentBox);
		
		totalPaperSummary.createCell(5).setCellValue("");
		totalPaperSummary.getCell(5).setCellStyle(styleContentBox);
		
		totalPaperSummary.createCell(6).setCellValue("");
		totalPaperSummary.getCell(6).setCellStyle(styleContentBox);
		
		totalPaperSummary.createCell(7).setCellValue("");
		totalPaperSummary.getCell(7).setCellStyle(styleContentBox);
		
		totalPaperSummary.createCell(8).setCellValue("");
		totalPaperSummary.getCell(8).setCellStyle(styleContentBox);
		
		totalPaperSummary.createCell(9).setCellValue(Integer.toString(iTotalConvertedHours));
		totalPaperSummary.getCell(9).setCellStyle(styleBox);
		
		/**
		 * Note
		 */
		iCurrentRow += 3;
		HSSFRow note = sheet.createRow(iCurrentRow);
		note.createCell(1).setCellValue("Ghi chú:  Mục (I)  lấy từ bản kê khai của từng cá nhân");
		note.getCell(1).setCellStyle(styleContent);
		
		/**
		 * Date
		 */
		iCurrentRow += 4;
		HSSFRow dateConfirmation = sheet.createRow(iCurrentRow);
		dateConfirmation.createCell(4).setCellValue("Hà Nội, Ngày ....... tháng ....... năm .........");
		dateConfirmation.getCell(4).setCellStyle(styleContent);
		
		/**
		 * Confirmations
		 */
		iCurrentRow += 1;
		HSSFRow confirmation = sheet.createRow(iCurrentRow);
		confirmation.createCell(1).setCellValue("TRƯỞNG BỘ MÔN");
		confirmation.getCell(1).setCellStyle(styleSubtitle);
		
		confirmation.createCell(4).setCellValue("TRƯỞNG KHOA/VIỆN/TT");
		confirmation.getCell(4).setCellStyle(styleSubtitle);
	}
	
	/**
	 * To generate all staff's papers summary
	 * @param model
	 * @param workbook
	 */
	public void buildExcelDocumentSummary(Map<String, Object> model, HSSFWorkbook workbook, Map<String, List<mPapers>> summaryAllStaffsList){
		HSSFSheet sheet = workbook.createSheet("03-BM");
		
		sheet.setDefaultColumnWidth(15);
		
		// preparing data
		String yearOfPaper = (String) model.get("yearOfPaper");
		String staffDepartementName  = (String) model.get("staffDepartementName");
		
		int iCurrentRow = 3;
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
		title.createCell(1).setCellValue("BẢNG TỔNG HỢP CHI TIẾT KHỐI LƯỢNG NCKH ĐƯỢC QUY ĐỔI TỪ CÁC BÀI BÁO KHOA HỌC");
		title.getCell(1).setCellStyle(style);
		sheet.setColumnWidth(0, 400);
		sheet.addMergedRegion(new CellRangeAddress(
				iCurrentRow, //first row (0-based)
				iCurrentRow, //last row  (0-based)
		            1, //first column (0-based)
		            12  //last column  (0-based)
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
		subTitle.createCell(1).setCellValue("ĐĂNG  TRONG TẠP CHÍ VÀ KỶ YẾU HỘI NGHỊ KHOA HỌC NĂM HỌC " + yearOfPaper);
		subTitle.getCell(1).setCellStyle(styleSubtitle);
		sheet.addMergedRegion(new CellRangeAddress(
				iCurrentRow, //first row (0-based)
				iCurrentRow, //last row  (0-based)
	            1, //first column (0-based)
	            12  //last column  (0-based)
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
		
		iCurrentRow += 3;
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
		
		authorUnitInfo.createCell(8).setCellValue("Khoa (Viện, Trung tâm): Viện Công nghệ thông tin và Truyền thông");
		authorUnitInfo.getCell(8).setCellStyle(styleSubtitle);
		sheet.addMergedRegion(new CellRangeAddress(
	            6, //first row (0-based)
	            6, //last row  (0-based)
	            8, //first column (0-based)
	            14  //last column  (0-based)
	    ));
		
		/**
		 * Style for header
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
	    
	    /**
		 * Style for content
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
	    
		iCurrentRow += 3;
		HSSFRow boxHeader = sheet.createRow(iCurrentRow);
		boxHeader.createCell(1).setCellValue("STT");
		boxHeader.getCell(1).setCellStyle(styleBox);
		sheet.addMergedRegion(new CellRangeAddress(
				iCurrentRow, //first row (0-based)
	            (iCurrentRow+1), //last row  (0-based)
	            1, //first column (0-based)
	            1  //last column  (0-based)
	    ));
		
		
		boxHeader.createCell(2).setCellValue("Họ và tên tác giả và các đồng tác giả");
		boxHeader.getCell(2).setCellStyle(styleBox);
		sheet.addMergedRegion(new CellRangeAddress(
				iCurrentRow, //first row (0-based)
				(iCurrentRow+1), //last row  (0-based)
	            2, //first column (0-based)
	            2  //last column  (0-based)
	    ));
		
		boxHeader.createCell(3).setCellValue("Tên bài báo");
		boxHeader.getCell(3).setCellStyle(styleBox);
		sheet.addMergedRegion(new CellRangeAddress(
				iCurrentRow, //first row (0-based)
				(iCurrentRow+1), //last row  (0-based)
	            3, //first column (0-based)
	            3  //last column  (0-based)
	    ));
		
		boxHeader.createCell(4).setCellValue("Tạp chí, Proceedings");
		boxHeader.getCell(4).setCellStyle(styleBox);
		sheet.addMergedRegion(new CellRangeAddress(
				iCurrentRow, //first row (0-based)
				iCurrentRow, //last row  (0-based)
	            4, //first column (0-based)
	            8  //last column  (0-based)
	    ));
		
		boxHeader.createCell(9).setCellValue("Số giờ quy đổi của người kê khai");
		boxHeader.getCell(9).setCellStyle(styleBox);
		sheet.addMergedRegion(new CellRangeAddress(
				iCurrentRow, //first row (0-based)
				(iCurrentRow+1), //last row  (0-based)
	            9, //first column (0-based)
	            9  //last column  (0-based)
	    ));
		
		boxHeader.createCell(10).setCellValue("Ghi chú");
		boxHeader.getCell(10).setCellStyle(styleBox);
		sheet.addMergedRegion(new CellRangeAddress(
				iCurrentRow, //first row (0-based)
				(iCurrentRow+1), //last row  (0-based)
	            10, //first column (0-based)
	            10  //last column  (0-based)
	    ));
		
		iCurrentRow++;
		HSSFRow boxHeader2 = sheet.createRow(iCurrentRow);
		boxHeader2.createCell(4).setCellValue("Tên tạp chí, Proceedings");
		boxHeader2.getCell(4).setCellStyle(styleBox);
		
		boxHeader2.createCell(5).setCellValue("Thời gian xuất bản");
		boxHeader2.getCell(5).setCellStyle(styleBox);
		
		boxHeader2.createCell(6).setCellValue("Chỉ số ISSN");
		boxHeader2.getCell(6).setCellStyle(styleBox);
		
		boxHeader2.createCell(7).setCellValue("Hệ số IF");
		boxHeader2.getCell(7).setCellStyle(styleBox);
		
		boxHeader2.createCell(8).setCellValue("Số giờ quy đổi của bài báo");
		boxHeader2.getCell(8).setCellStyle(styleBox);
		
		int iCount = 1;
		iCurrentRow++;
		int iTotalConvertedHours = 0;
		int iTotalAuthorConvertedHours = 0;
		int iTotalConvertedSummaryHours = 0;
		int iTotalAuthorConvertedSummaryHours = 0;
		for(Map.Entry<String, List<mPapers>> summaryStaffs : summaryAllStaffsList.entrySet()) { 
			String staffName = summaryStaffs.getKey();
            List<mPapers> listSummaryStaffsPapers = summaryStaffs.getValue();
			
            HSSFRow boxFisrtCatContent = sheet.createRow(iCurrentRow);
            if(listSummaryStaffsPapers.size() > 0){
				
				// Fill name and total converted hours
				boxFisrtCatContent.createCell(1).setCellValue("");
				boxFisrtCatContent.getCell(1).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(2).setCellValue(staffName);
				boxFisrtCatContent.getCell(2).setCellStyle(styleBox);
				
				boxFisrtCatContent.createCell(3).setCellValue("");
				boxFisrtCatContent.getCell(3).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(4).setCellValue("");
				boxFisrtCatContent.getCell(4).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(5).setCellValue("");
				boxFisrtCatContent.getCell(5).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(6).setCellValue("");
				boxFisrtCatContent.getCell(6).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(7).setCellValue("");
				boxFisrtCatContent.getCell(7).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(8).setCellValue("");
				boxFisrtCatContent.getCell(8).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(9).setCellValue("");
				boxFisrtCatContent.getCell(9).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(10).setCellValue("");
				boxFisrtCatContent.getCell(10).setCellStyle(styleContentBox);
				
				iCurrentRow++;
            }
			iCount = 1;
			iTotalConvertedHours = 0;
			iTotalAuthorConvertedHours = 0;
			if(listSummaryStaffsPapers.size() > 0){
				for(mPapers paperSummary : listSummaryStaffsPapers)
				{
					HSSFRow boxPaperContent = sheet.createRow(iCurrentRow);
					
					// Fill name and total converted hours
					boxPaperContent.createCell(1).setCellValue(iCount++);
					boxPaperContent.getCell(1).setCellStyle(styleContentBox);
					
					boxPaperContent.createCell(2).setCellValue(paperSummary.getPDECL_AuthorList());
					boxPaperContent.getCell(2).setCellStyle(styleContentBox);
					
					boxPaperContent.createCell(3).setCellValue(paperSummary.getPDECL_PublicationName());
					boxPaperContent.getCell(3).setCellStyle(styleContentBox);
					
					boxPaperContent.createCell(4).setCellValue(paperSummary.getPDECL_JournalConferenceName());
					boxPaperContent.getCell(4).setCellStyle(styleContentBox);
					
					boxPaperContent.createCell(5).setCellValue(paperSummary.getPDECL_Year());
					boxPaperContent.getCell(5).setCellStyle(styleContentBox);
					
					boxPaperContent.createCell(6).setCellValue(paperSummary.getPDECL_ISSN());
					boxPaperContent.getCell(6).setCellStyle(styleContentBox);
					
					boxPaperContent.createCell(7).setCellValue("");
					boxPaperContent.getCell(7).setCellStyle(styleContentBox);
					
					boxPaperContent.createCell(8).setCellValue(paperSummary.getPDECL_PublicationConvertedHours());
					boxPaperContent.getCell(8).setCellStyle(styleContentBox);
					
					boxPaperContent.createCell(9).setCellValue(paperSummary.getPDECL_AuthorConvertedHours());
					boxPaperContent.getCell(9).setCellStyle(styleContentBox);
					
					boxPaperContent.createCell(10).setCellValue("");
					boxPaperContent.getCell(10).setCellStyle(styleContentBox);
					
					// Calculate total hours for staff
					iTotalConvertedHours += paperSummary.getPDECL_PublicationConvertedHours();
					iTotalAuthorConvertedHours += paperSummary.getPDECL_AuthorConvertedHours();
					
					// Calculate total hours for all staffs
					iTotalConvertedSummaryHours += paperSummary.getPDECL_PublicationConvertedHours();
					iTotalAuthorConvertedSummaryHours += paperSummary.getPDECL_AuthorConvertedHours();
					
					iCurrentRow++;
					
					boxFisrtCatContent.createCell(8).setCellValue(iTotalConvertedHours);
					boxFisrtCatContent.getCell(8).setCellStyle(styleBox);
					
					boxFisrtCatContent.createCell(9).setCellValue(iTotalAuthorConvertedHours);
					boxFisrtCatContent.getCell(9).setCellStyle(styleBox);
				}
			}
		}
		
		/**
		 * Total line
		 */
		
		HSSFRow boxTotalLine = sheet.createRow(iCurrentRow);
		boxTotalLine.createCell(1).setCellValue("");
		boxTotalLine.getCell(1).setCellStyle(styleContentBox);
		
		boxTotalLine.createCell(2).setCellValue("Cộng cả Bộ môn");
		boxTotalLine.getCell(2).setCellStyle(styleBox);
		
		boxTotalLine.createCell(3).setCellValue("");
		boxTotalLine.getCell(3).setCellStyle(styleContentBox);
		
		boxTotalLine.createCell(4).setCellValue("");
		boxTotalLine.getCell(4).setCellStyle(styleContentBox);
		
		boxTotalLine.createCell(5).setCellValue("");
		boxTotalLine.getCell(5).setCellStyle(styleContentBox);
		
		boxTotalLine.createCell(6).setCellValue("");
		boxTotalLine.getCell(6).setCellStyle(styleContentBox);
		
		boxTotalLine.createCell(7).setCellValue("");
		boxTotalLine.getCell(7).setCellStyle(styleContentBox);
		
		boxTotalLine.createCell(8).setCellValue(iTotalConvertedSummaryHours);
		boxTotalLine.getCell(8).setCellStyle(styleBox);
		
		boxTotalLine.createCell(9).setCellValue(iTotalAuthorConvertedSummaryHours);
		boxTotalLine.getCell(9).setCellStyle(styleBox);
		
		boxTotalLine.createCell(10).setCellValue("");
		boxTotalLine.getCell(10).setCellStyle(styleContentBox);
		
		/**
		 * Note
		 */
		iCurrentRow += 3;
		HSSFRow note = sheet.createRow(iCurrentRow);
		note.createCell(1).setCellValue("Ghi chú:");
		note.getCell(1).setCellStyle(styleContent);
		
		iCurrentRow++;
		HSSFRow note1 = sheet.createRow(iCurrentRow);
		note1.createCell(1).setCellValue("(*): Cộng tổng giờ bài báo khoa học cho từng cá nhân");
		note1.getCell(1).setCellStyle(styleContent);
		
		iCurrentRow++;
		HSSFRow note2 = sheet.createRow(iCurrentRow);
		note2.createCell(1).setCellValue("(**): Cộng tổng giờ bài báo khoa học của Bộ môn");
		note2.getCell(1).setCellStyle(styleContent);
		
		/**
		 * Date
		 */
		iCurrentRow += 4;
		HSSFRow dateConfirmation = sheet.createRow(iCurrentRow);
		dateConfirmation.createCell(4).setCellValue("Hà Nội, Ngày ....... tháng ....... năm .........");
		dateConfirmation.getCell(4).setCellStyle(styleContent);
		
		/**
		 * Confirmations
		 */
		iCurrentRow += 1;
		HSSFRow confirmation = sheet.createRow(iCurrentRow);
		confirmation.createCell(1).setCellValue("TRƯỞNG BỘ MÔN");
		confirmation.getCell(1).setCellStyle(styleSubtitle);
		
		confirmation.createCell(4).setCellValue("TRƯỞNG KHOA/VIỆN/TT");
		confirmation.getCell(4).setCellStyle(styleSubtitle);
	}

}
