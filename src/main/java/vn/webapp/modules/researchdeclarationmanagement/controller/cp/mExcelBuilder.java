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

public class mExcelBuilder extends AbstractExcelView {
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// get data model which is passed by the Spring container
		List<mPapers> listPapers = (List<mPapers>) model.get("papersList");
		
		// create a new Excel sheet
		HSSFSheet sheet = workbook.createSheet("Báo cáo khoa học");
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
		subTitle.createCell(1).setCellValue("(CÁC BÀI BÁO KHOA HỌC)");
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
		subTitle2.createCell(1).setCellValue("CÁC BÀI BÁO KHOA HỌC :");
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
	    
	    CellStyle styleBox2 = workbook.createCellStyle();
	    styleBox2.setBorderBottom(CellStyle.BORDER_THIN);
	    styleBox2.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    styleBox2.setBorderLeft(CellStyle.BORDER_THIN);
	    styleBox2.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    styleBox2.setBorderRight(CellStyle.BORDER_THIN);
	    styleBox2.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    styleBox2.setBorderTop(CellStyle.BORDER_THIN);
	    styleBox2.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    styleBox2.setFont(fontSubTitle);
	    
	    iCurrentRow = 11;
		HSSFRow boxHeader = sheet.createRow(iCurrentRow);
		boxHeader.createCell(1).setCellValue("STT");
		boxHeader.getCell(1).setCellStyle(styleBox);
		sheet.addMergedRegion(new CellRangeAddress(
	            11, //first row (0-based)
	            12, //last row  (0-based)
	            1, //first column (0-based)
	            1  //last column  (0-based)
	    ));
		
		
		boxHeader.createCell(2).setCellValue("Họ và tên các tác giả, đơn vị (ghi chi tiết)");
		
		boxHeader.getCell(2).setCellStyle(styleBox);
		sheet.addMergedRegion(new CellRangeAddress(
	            11, //first row (0-based)
	            12, //last row  (0-based)
	            2, //first column (0-based)
	            2  //last column  (0-based)
	    ));
		
		boxHeader.createCell(3).setCellValue("Tên bài báo");
		boxHeader.getCell(3).setCellStyle(styleBox);
		sheet.addMergedRegion(new CellRangeAddress(
	            11, //first row (0-based)
	            12, //last row  (0-based)
	            3, //first column (0-based)
	            3  //last column  (0-based)
	    ));
		
		boxHeader.createCell(4).setCellValue("Tạp chí, Proceedings");
		boxHeader.getCell(4).setCellStyle(styleBox);
		sheet.addMergedRegion(new CellRangeAddress(
	            11, //first row (0-based)
	            11, //last row  (0-based)
	            4, //first column (0-based)
	            7  //last column  (0-based)
	    ));
		
		boxHeader.createCell(8).setCellValue("Số giờ quy đổi  của bài báo");
		boxHeader.getCell(8).setCellStyle(styleBox);
		sheet.addMergedRegion(new CellRangeAddress(
	            11, //first row (0-based)
	            12, //last row  (0-based)
	            8, //first column (0-based)
	            8  //last column  (0-based)
	    ));
		
		boxHeader.createCell(9).setCellValue("Số giờ  quy đổi của người kê khai");
		boxHeader.getCell(9).setCellStyle(styleBox);
		sheet.addMergedRegion(new CellRangeAddress(
	            11, //first row (0-based)
	            12, //last row  (0-based)
	            9, //first column (0-based)
	            9  //last column  (0-based)
	    ));
		
		iCurrentRow++;
		HSSFRow boxHeader2 = sheet.createRow(12);
		boxHeader2.createCell(4).setCellValue("Tên tạp chí, Proceedings");
		boxHeader2.getCell(4).setCellStyle(styleBox);
		
		boxHeader2.createCell(5).setCellValue("Số và thời gian xuất bản");
		boxHeader2.getCell(5).setCellStyle(styleBox);
		
		boxHeader2.createCell(6).setCellValue("Chỉ số ISSN");
		boxHeader2.getCell(6).setCellStyle(styleBox);
		
		boxHeader2.createCell(7).setCellValue("Thuộc danh mục SCI và SCIE  (*)");
		boxHeader2.getCell(7).setCellStyle(styleBox);
		
		/**
		 * Show numbers of column
		 */
		iCurrentRow++;
		HSSFRow boxNo = sheet.createRow(iCurrentRow);
		for(int iNo = 1; iNo <=9; iNo++){
			boxNo.createCell(iNo).setCellValue(iNo);
			boxNo.getCell(iNo).setCellStyle(styleBox);
		}
		
		/**
		 * Show content's first category 
		 */
		iCurrentRow++;
		HSSFRow boxFisrtCat = sheet.createRow(iCurrentRow);
		boxFisrtCat.createCell(1).setCellValue("I");
		boxFisrtCat.getCell(1).setCellStyle(styleBox);
		
		boxFisrtCat.createCell(2).setCellValue("Các bài báo đăng trong tạp chí trong và ngoài nước");
		boxFisrtCat.getCell(2).setCellStyle(styleBox2);
		sheet.addMergedRegion(new CellRangeAddress(
	            14, //first row (0-based)
	            14, //last row  (0-based)
	            2, //first column (0-based)
	            9  //last column  (0-based)
	    ));
		
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
		for (mPapers aPaper : listPapers) { 
			if(aPaper.getPDECL_PaperCategory_Code().equals("JDOM_Other") 
					|| aPaper.getPDECL_PaperCategory_Code().equals("JINT_SCI")
					|| aPaper.getPDECL_PaperCategory_Code().equals("JINT_SCIE")
					|| aPaper.getPDECL_PaperCategory_Code().equals("JINT_Other")){
				HSSFRow boxFisrtCatContent = sheet.createRow(iCurrentRow);
				
				boxFisrtCatContent.createCell(1).setCellValue(iCount++);
				boxFisrtCatContent.getCell(1).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(2).setCellValue(aPaper.getPDECL_AuthorList());
				boxFisrtCatContent.getCell(2).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(3).setCellValue(aPaper.getPDECL_PublicationName());
				boxFisrtCatContent.getCell(3).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(4).setCellValue(aPaper.getPDECL_JournalConferenceName());
				boxFisrtCatContent.getCell(4).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(5).setCellValue(aPaper.getPDECL_Year());
				boxFisrtCatContent.getCell(5).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(6).setCellValue(aPaper.getPDECL_ISSN());
				boxFisrtCatContent.getCell(6).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(7).setCellValue(aPaper.getPDECL_IndexCode());
				boxFisrtCatContent.getCell(7).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(8).setCellValue(aPaper.getPDECL_PublicationConvertedHours());
				boxFisrtCatContent.getCell(8).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(9).setCellValue(aPaper.getPDECL_AuthorConvertedHours());
				boxFisrtCatContent.getCell(9).setCellStyle(styleContentBox);
				
				iTotalConvertedHours += aPaper.getPDECL_AuthorConvertedHours();
				iCurrentRow++;
			}
		}
		
		/**
		 * Show content's second category 
		 */
		HSSFRow boxSecondCat = sheet.createRow(iCurrentRow);
		boxSecondCat.createCell(1).setCellValue("II");
		boxSecondCat.getCell(1).setCellStyle(styleBox);
		
		boxSecondCat.createCell(2).setCellValue("Các bài báo đăng trong kỷ yếu Hội nghị  khoa học trong và ngoài nước");
		boxSecondCat.getCell(2).setCellStyle(styleBox2);
		sheet.addMergedRegion(new CellRangeAddress(
				iCurrentRow, //first row (0-based)
	            iCurrentRow, //last row  (0-based)
	            2, //first column (0-based)
	            9  //last column  (0-based)
	    ));
		
		/**
		 * Show all contents for the second category
		 */
		iCount = 1;
		iCurrentRow++;
		for (mPapers aPaper : listPapers) {
			if(aPaper.getPDECL_PaperCategory_Code().equals("CINT_Other") || aPaper.getPDECL_PaperCategory_Code().equals("CDOM_Other")){
				HSSFRow boxSecondCatContent = sheet.createRow(iCurrentRow);
				
				boxSecondCatContent.createCell(1).setCellValue(iCount++);
				boxSecondCatContent.getCell(1).setCellStyle(styleContentBox);
				
				boxSecondCatContent.createCell(2).setCellValue(aPaper.getPDECL_AuthorList());
				boxSecondCatContent.getCell(2).setCellStyle(styleContentBox);
				
				boxSecondCatContent.createCell(3).setCellValue(aPaper.getPDECL_PublicationName());
				boxSecondCatContent.getCell(3).setCellStyle(styleContentBox);
				
				boxSecondCatContent.createCell(4).setCellValue(aPaper.getPDECL_JournalConferenceName());
				boxSecondCatContent.getCell(4).setCellStyle(styleContentBox);
				
				boxSecondCatContent.createCell(5).setCellValue(aPaper.getPDECL_Year());
				boxSecondCatContent.getCell(5).setCellStyle(styleContentBox);
				
				boxSecondCatContent.createCell(6).setCellValue(aPaper.getPDECL_ISSN());
				boxSecondCatContent.getCell(6).setCellStyle(styleContentBox);
				
				boxSecondCatContent.createCell(7).setCellValue(aPaper.getPDECL_IndexCode());
				boxSecondCatContent.getCell(7).setCellStyle(styleContentBox);
				
				boxSecondCatContent.createCell(8).setCellValue(aPaper.getPDECL_PublicationConvertedHours());
				boxSecondCatContent.getCell(8).setCellStyle(styleContentBox);
				
				boxSecondCatContent.createCell(9).setCellValue(aPaper.getPDECL_AuthorConvertedHours());
				boxSecondCatContent.getCell(9).setCellStyle(styleContentBox);
				
				iTotalConvertedHours += aPaper.getPDECL_AuthorConvertedHours();
				iCurrentRow++;
			}
		}
		
		/**
		 * Confirmation and Signature
		 */
		iCurrentRow++;
		HSSFRow totalConvertedTime = sheet.createRow(iCurrentRow);
		totalConvertedTime.createCell(4).setCellValue("Tổng số giờ qui đổi của người kê khai = "+iTotalConvertedHours);
		totalConvertedTime.getCell(4).setCellStyle(styleSubtitle);
		
		iCurrentRow += 3;
		HSSFRow confirmation = sheet.createRow(iCurrentRow);
		confirmation.createCell(2).setCellValue("Xác nhận của Khoa (Viện , TT)");
		confirmation.getCell(2).setCellStyle(styleSubtitle);
		
		confirmation.createCell(5).setCellValue("Xác nhận của Bộ môn");
		confirmation.getCell(5).setCellStyle(styleSubtitle);
		
		confirmation.createCell(7).setCellValue("Người kê khai");
		confirmation.getCell(7).setCellStyle(styleSubtitle);
		
		/**
		 * Note
		 */
		iCurrentRow += 7;
		HSSFRow note = sheet.createRow(iCurrentRow);
		note.createCell(1).setCellValue("Ghi chú:  (*) Các bài báo được đăng trên tạp chí trong danh mục SCI và SCIE do ISI thống kê thì đánh dấu (x) vào cột 7");
		note.getCell(1).setCellStyle(styleContent);
	}
}
