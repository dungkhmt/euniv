package vn.webapp.modules.researchmanagement.controller.cp;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.jsoup.Jsoup;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import vn.webapp.libraries.Money2StringConvertor;
import vn.webapp.modules.researchmanagement.model.ProjectTasks;

public class mExcelThreadsBuilder extends AbstractExcelView{
	
	/**
	 * 
	 */
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get data model which is passed by the Spring container
		List<List<String>> summaryThreadList = (List<List<String>>) model.get("summaryThreadList");
		HashSet<String> listMembers = (HashSet<String>) model.get("listMembers");
		
		// create a new Excel sheet
		HSSFSheet sheet = workbook.createSheet("Thong ke de tai");
		sheet.setDefaultColumnWidth(20);
		
		// preparing data
		String yearOfPaper = (String) model.get("yearOfPaper");
		
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
		title.createCell(1).setCellValue("DANH MỤC ĐĂNG KÝ ĐỀ TÀI KHOA HỌC VÀ CÔNG NGHỆ CẤP TRƯỜNG - NĂM " + yearOfPaper);
		title.getCell(1).setCellStyle(style);
		sheet.setColumnWidth(0, 400);
		sheet.addMergedRegion(new CellRangeAddress(
		            1, //first row (0-based)
		            1, //last row  (0-based)
		            1, //first column (0-based)
		            8  //last column  (0-based)
		    ));
		iCurrentRow++;
		HSSFRow title2 = sheet.createRow(iCurrentRow);
		title2.createCell(2).setCellValue("TÊN ĐƠN VỊ");
		title2.getCell(2).setCellStyle(style);
		
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
		
		/**
		 * Create the box
		 */
		// Style the cell with borders all around.
	    CellStyle styleBox = workbook.createCellStyle();
	    styleBox.setWrapText(true);
	    styleBox.setBorderBottom(CellStyle.BORDER_THIN);
	    styleBox.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    styleBox.setBorderLeft(CellStyle.BORDER_THIN);
	    styleBox.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    styleBox.setBorderRight(CellStyle.BORDER_THIN);
	    styleBox.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    styleBox.setBorderTop(CellStyle.BORDER_THIN);
	    styleBox.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    styleBox.setFont(fontSubTitle);
	    
	    iCurrentRow = 4;
		HSSFRow boxHeader = sheet.createRow(iCurrentRow);
		boxHeader.createCell(1).setCellValue("STT");
		boxHeader.getCell(1).setCellStyle(styleBox);
		
		boxHeader.createCell(2).setCellValue("Tên đề tài");
		boxHeader.getCell(2).setCellStyle(styleBox);
		
		boxHeader.createCell(3).setCellValue("Tên chủ nhiệm");
		boxHeader.getCell(3).setCellStyle(styleBox);
		
		boxHeader.createCell(4).setCellValue("Nội dung nghiên cứu");
		boxHeader.getCell(4).setCellStyle(styleBox);
		
		boxHeader.createCell(5).setCellValue("Kết quả đạt được");
		boxHeader.getCell(5).setCellStyle(styleBox);
		
		boxHeader.createCell(6).setCellValue("Kinh phí");
		boxHeader.getCell(6).setCellStyle(styleBox);
		
		boxHeader.createCell(7).setCellValue("Điểm đánh giá trung bìn HĐ, nhận xét và kiến nghị");
		boxHeader.getCell(7).setCellStyle(styleBox);
		
		/**
		 * Show numbers of column
		 * iCurrentRow++;
			HSSFRow boxNo = sheet.createRow(iCurrentRow);
			for(int iNo = 1; iNo <=6; iNo++){
				boxNo.createCell(iNo).setCellValue(iNo);
				boxNo.getCell(iNo).setCellStyle(styleBox);
			}
		 */

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
	    styleContentBox.setWrapText(true);
	    styleContentBox.setBorderBottom(CellStyle.BORDER_THIN);
	    styleContentBox.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    styleContentBox.setBorderLeft(CellStyle.BORDER_THIN);
	    styleContentBox.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    styleContentBox.setBorderRight(CellStyle.BORDER_THIN);
	    styleContentBox.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    styleContentBox.setBorderTop(CellStyle.BORDER_THIN);
	    styleContentBox.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    styleContentBox.setFont(fontContent);
	    styleContentBox.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		int iCount = 1;
		iCurrentRow++;
		int iTotalBudget = 0;
		//for (List<String> oThread : summaryThreadList) { 
		//	iTotalBudget += Integer.parseInt(oThread.get(4));
		//}
		
		String sContent = "";
		for (List<String> oThread : summaryThreadList) { 
			
				HSSFRow boxFisrtCatContent = sheet.createRow(iCurrentRow);
				boxFisrtCatContent.createCell(1).setCellValue(iCount++);
				boxFisrtCatContent.getCell(1).setCellStyle(styleContentBox);
				
				// Name project
				boxFisrtCatContent.createCell(2).setCellValue(oThread.get(3));
				boxFisrtCatContent.getCell(2).setCellStyle(styleContentBox);
				
				// Project leader 
				boxFisrtCatContent.createCell(3).setCellValue(oThread.get(0));
				boxFisrtCatContent.getCell(3).setCellStyle(styleContentBox);
				
				// Project content
				sContent = (!"".equals(oThread.get(5)) && oThread.get(5) != null) ? Jsoup.parse(oThread.get(5)).text() : "";
				boxFisrtCatContent.createCell(4).setCellValue(sContent);
				boxFisrtCatContent.getCell(4).setCellStyle(styleContentBox);
				
				// Project result
				sContent = (!"".equals(oThread.get(6)) && oThread.get(6) != null) ? Jsoup.parse(oThread.get(6)).text() : "";
				boxFisrtCatContent.createCell(5).setCellValue(sContent);
				boxFisrtCatContent.getCell(5).setCellStyle(styleContentBox);
				
				// Budget
				String stdBudget = Money2StringConvertor.addDot2Moyney(oThread.get(4));
				//boxFisrtCatContent.createCell(6).setCellValue(oThread.get(4));
				boxFisrtCatContent.createCell(6).setCellValue(stdBudget);
				boxFisrtCatContent.getCell(6).setCellStyle(styleContentBox);
				
				// Empty column
				boxFisrtCatContent.createCell(7).setCellValue("");
				boxFisrtCatContent.getCell(7).setCellStyle(styleContentBox);
				
				iTotalBudget += Integer.parseInt(oThread.get(4));
				iCurrentRow++;
			
		}
		
		/**
		 * Papers - total line
		 */
		HSSFRow boxNoTotal = sheet.createRow(iCurrentRow);
		for(int iNo = 1; iNo <=7; iNo++){
			
			if(iNo == 1){
				boxNoTotal.createCell(iNo).setCellValue("Tổng cộng");
				boxNoTotal.getCell(iNo).setCellStyle(styleBox);
			}else if (iNo == 6){
				String stdBudget = Money2StringConvertor.addDot2Moyney(iTotalBudget + ""); 
				//boxNoTotal.createCell(iNo).setCellValue(iTotalBudget);
				boxNoTotal.createCell(iNo).setCellValue(stdBudget);
				boxNoTotal.getCell(iNo).setCellStyle(styleBox);
			}else{
				boxNoTotal.createCell(iNo).setCellValue("");
				boxNoTotal.getCell(iNo).setCellStyle(styleBox);
			}
		}
		
		iCurrentRow +=2 ;
		HSSFRow listJoiningMembers = sheet.createRow(iCurrentRow);
		listJoiningMembers.createCell(1).setCellValue("Danh sách thành viên tham gia");
		listJoiningMembers.getCell(1).setCellStyle(styleSubtitle);
		
		iCurrentRow += 2;
		HSSFRow boxAttandeeHeader = sheet.createRow(iCurrentRow);
		boxAttandeeHeader.createCell(1).setCellValue("STT");
		boxAttandeeHeader.getCell(1).setCellStyle(styleBox);
		
		boxAttandeeHeader.createCell(2).setCellValue("Họ và tên");
		boxAttandeeHeader.getCell(2).setCellStyle(styleBox);
		
		iCurrentRow++;
		iCount = 1;
		if(listMembers.size() > 0)
		{
			for (String member : listMembers) {
				HSSFRow boxFisrtCatContent = sheet.createRow(iCurrentRow);
				boxFisrtCatContent.createCell(1).setCellValue(iCount++);
				boxFisrtCatContent.getCell(1).setCellStyle(styleContentBox);
				
				// Staff project
				boxFisrtCatContent.createCell(2).setCellValue(member);
				boxFisrtCatContent.getCell(2).setCellStyle(styleContentBox);
				
				iCurrentRow++;
			}
		}
		
		/**
		 * Confirmation and Signature
		 */
		iCurrentRow +=3 ;
		HSSFRow dateEnd = sheet.createRow(iCurrentRow);
		dateEnd.createCell(6).setCellValue("Hà Nội, Ngày.........tháng.........năm.........");
		dateEnd.getCell(6).setCellStyle(styleSubtitle);
		
		iCurrentRow +=2 ;
		HSSFRow leadSign = sheet.createRow(iCurrentRow);
		leadSign.createCell(6).setCellValue("Thủ trưởng đơn vị");
		leadSign.getCell(6).setCellStyle(styleSubtitle);
		
		iCurrentRow +=3 ;
		HSSFRow noteCont = sheet.createRow(iCurrentRow);
		noteCont.createCell(1).setCellValue("Ghi chú: “Bảng tổng hợp” tổng hợp cả thuyết minh không đạt,  và phải được gửi cho các cá nhân đề xuất đề tài");
		noteCont.getCell(1).setCellStyle(styleSubtitle);
		
	}
}
