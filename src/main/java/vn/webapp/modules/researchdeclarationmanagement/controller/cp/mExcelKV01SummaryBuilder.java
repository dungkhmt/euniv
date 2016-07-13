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
import vn.webapp.validation.StringUtils;

public class mExcelKV01SummaryBuilder extends AbstractExcelView {
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// get data model which is passed by the Spring container
		List<mPapers> listPapers = (List<mPapers>) model.get("papersList");
		int totalStaffPaperConvertedHours = (int) model.get("iTotalPaperConvertedHours");
		//int iTotalStaffPatentConvertedHours = (int) model.get("iTotalPatentConvertedHours");
		int iTotalStaffProjectConvertedHours = (int) model.get("iTotalProjectConvertedHours");
		int iTotalConvertedHours = (int) model.get("iTotalConvertedHours");
		
		Map<String, Map<String, List<Integer>>> summaryAllDepartmentStaffList =  (Map<String, Map<String, List<Integer>>>) model.get("summaryAllDepartmentStaffList");
		
		// create a new Excel sheet
		HSSFSheet sheet = workbook.createSheet("01-KV");
		sheet.setDefaultColumnWidth(15);
		
		// preparing data
		String yearOfPaper = (String) model.get("yearOfPaper");
		
		int iCurrentRow = 1;
		/**
		 * 1. Create a cell for the title
		 */
		// create style for title cells
		CellStyle style = workbook.createCellStyle();
		style.setWrapText(true);
		Font fontTitle = workbook.createFont();
		fontTitle.setFontHeightInPoints((short)12);
		fontTitle.setFontName("Times New Roman");
		fontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fontTitle.setColor(HSSFColor.BLACK.index);
		style.setFont(fontTitle);
		style.setWrapText(true);
		
		// create title of the paper
		HSSFRow title = sheet.createRow(iCurrentRow);
		
		title.createCell(1).setCellValue("Bảng tổng hợp tính giờ chuẩn NCKH năm học " + yearOfPaper +" của cán bộ trường ĐH Bách Khoa Hà Nội");
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
		
		iCurrentRow += 3;
		// create sub title of the paper
		HSSFRow subTitle = sheet.createRow(iCurrentRow);
		subTitle.createCell(1).setCellValue("Khoa/Viện : Viện Công nghệ Thông tin và Truyền thông");
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
	    styleBox.setWrapText(true);
	    
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
	    
	    iCurrentRow += 5;
		HSSFRow boxHeader = sheet.createRow(iCurrentRow);
		boxHeader.createCell(1).setCellValue("STT");
		boxHeader.getCell(1).setCellStyle(styleBox);

		boxHeader.createCell(2).setCellValue("Họ và tên");
		boxHeader.getCell(2).setCellStyle(styleBox);

		
		boxHeader.createCell(3).setCellValue("Tổng số giờ quy đổi từ bài báo");
		boxHeader.getCell(3).setCellStyle(styleBox);
		
		boxHeader.createCell(4).setCellValue("Tổng số giờ quy đổi từ đề tài NCKH");
		boxHeader.getCell(4).setCellStyle(styleBox);
		
		boxHeader.createCell(5).setCellValue("Tổng cộng giờ quy đổi");
		boxHeader.getCell(5).setCellStyle(styleBox);
		
		
		/**
		 * Show content's first category 
		 */
		iCurrentRow++;
		HSSFRow boxFisrt = sheet.createRow(iCurrentRow);
		boxFisrt.createCell(2).setCellValue("Viện CNTT & Truyền Thông");
		boxFisrt.getCell(2).setCellStyle(styleBox);
		boxFisrt.createCell(1).setCellValue("");
		boxFisrt.getCell(1).setCellStyle(styleBox);
		boxFisrt.createCell(3).setCellValue(totalStaffPaperConvertedHours);
		boxFisrt.getCell(3).setCellStyle(styleBox);
		//boxFisrt.createCell(4).setCellValue(iTotalStaffPatentConvertedHours);
		boxFisrt.createCell(4).setCellValue(iTotalStaffProjectConvertedHours);
		boxFisrt.getCell(4).setCellStyle(styleBox);
		boxFisrt.createCell(5).setCellValue(iTotalConvertedHours);
		boxFisrt.getCell(5).setCellStyle(styleBox);
		
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
		styleContent.setWrapText(true);
		
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
		styleContentBox.setWrapText(true);
		
		
		int iCount = 1;
		int iCountDepartment = 1;
		iCurrentRow++;
		int iTotalPaperConvertedHours = 0;
		//int iTotalPatentConvertedHours = 0;
		int iTotalProjectConvertedHours = 0;
		int iTotalStaffConvertedHours = 0;
		StringUtils oStringUtils = new StringUtils();
		for(Map.Entry<String, Map<String, List<Integer>>> department : summaryAllDepartmentStaffList.entrySet()) { 
			Map<String, List<Integer>> staffList = department.getValue();
			
			HSSFRow boxFisrtCat = sheet.createRow(iCurrentRow);
			boxFisrtCat.createCell(1).setCellValue(oStringUtils.RomanNumerals(iCountDepartment));
			boxFisrtCat.getCell(1).setCellStyle(styleBox);
			
			boxFisrtCat.createCell(2).setCellValue(department.getKey());
			boxFisrtCat.getCell(2).setCellStyle(styleBox2);
			
			iCurrentRow++;
			
			// Reset value for each department
			iCount = 1;
			iTotalPaperConvertedHours = 0;
			//iTotalPatentConvertedHours = 0;
			iTotalProjectConvertedHours = 0;
			iTotalStaffConvertedHours = 0;
			for(Map.Entry<String, List<Integer>> staff : staffList.entrySet()){
				String staffName = staff.getKey();
				List<Integer> paperConvertedStaffHour = staff.getValue();
				HSSFRow boxFisrtCatContent = sheet.createRow(iCurrentRow);
				
				boxFisrtCatContent.createCell(1).setCellValue(iCount++);
				boxFisrtCatContent.getCell(1).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(2).setCellValue(staffName);
				boxFisrtCatContent.getCell(2).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(3).setCellValue(paperConvertedStaffHour.get(0));
				boxFisrtCatContent.getCell(3).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(4).setCellValue(paperConvertedStaffHour.get(1));
				boxFisrtCatContent.getCell(4).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(5).setCellValue(paperConvertedStaffHour.get(2));
				boxFisrtCatContent.getCell(5).setCellStyle(styleContentBox);
				
				iTotalPaperConvertedHours += paperConvertedStaffHour.get(0);
				//iTotalPatentConvertedHours += paperConvertedStaffHour.get(1);
				iTotalProjectConvertedHours += paperConvertedStaffHour.get(1);
				iTotalStaffConvertedHours += paperConvertedStaffHour.get(2);
				iCurrentRow++;
			}
			
			// Fill converted hours for each department
			boxFisrtCat.createCell(3).setCellValue(iTotalPaperConvertedHours);
			boxFisrtCat.getCell(3).setCellStyle(styleBox);
			//boxFisrtCat.createCell(4).setCellValue(iTotalPatentConvertedHours);
			boxFisrtCat.createCell(4).setCellValue(iTotalProjectConvertedHours);
			boxFisrtCat.getCell(4).setCellStyle(styleBox);
			boxFisrtCat.createCell(5).setCellValue(iTotalStaffConvertedHours);
			boxFisrtCat.getCell(5).setCellStyle(styleBox);
			
			iCountDepartment++;
		}
		
		/**
		 * Confirmation and Signature
		 */

		iCurrentRow += 3;
		HSSFRow dateYear = sheet.createRow(iCurrentRow);
		dateYear.createCell(5).setCellValue("Ngày........Tháng.........Năm.........");
		dateYear.getCell(5).setCellStyle(styleSubtitle);
		
		iCurrentRow += 2;
		HSSFRow confirmation = sheet.createRow(iCurrentRow);
		confirmation.createCell(5).setCellValue("LÃNH ĐẠO KHOA/VIỆN");
		confirmation.getCell(5).setCellStyle(styleSubtitle);

		
		response.setHeader("Content-Disposition", "attachement; filename=\"" + "Mau-01-KV-tong-hop-khoi-luong-NCKH.xls\"");
	}
}
