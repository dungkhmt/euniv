package vn.webapp.modules.researchdeclarationmanagement.controller.cp;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
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

public class mExcelISI02SummaryBuilder extends AbstractExcelView {
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook,
										HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HSSFSheet sheet = workbook.createSheet("Danh muc bai bao");

		int iCurrentRow = 0;
		// preparing data
		String yearOfPaper = (String) model.get("year");

		// create style for title cells
		CellStyle style = workbook.createCellStyle();
		Font fontTitle = workbook.createFont();
		fontTitle.setFontHeightInPoints((short) 12);
		fontTitle.setFontName("Times New Roman");
		fontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fontTitle.setColor(HSSFColor.BLACK.index);
		style.setFont(fontTitle);
		// style.setWrapText(true);

		sheet.createRow(iCurrentRow).createCell(4).setCellValue("Mẫu 02-KV");
		
		// create title of the paper
		iCurrentRow++;
		HSSFRow title = sheet.createRow(iCurrentRow);
		title.createCell(1).setCellValue(
				"DANH MỤC BÀI BÁO CỦA CÁN BỘ TRƯỜNG ĐHBKHN");
		title.getCell(1).setCellStyle(style);

		iCurrentRow++;
		title = sheet.createRow(iCurrentRow);
		title.createCell(1).setCellValue(
				"ĐĂNG TRONG TẠP CHÍ QUỐC TẾ TRONG DANH MỤC SCI VÀ SCIE NĂM HỌC "
						+ yearOfPaper);

		iCurrentRow += 4;
		HSSFRow faculty = sheet.createRow(iCurrentRow);
		faculty.createCell(1).setCellValue("Khoa/Viện....................................");
		faculty.getCell(1).setCellStyle(style);

		
		title.getCell(1).setCellStyle(style);
		sheet.setColumnWidth(0, 400);
		sheet.addMergedRegion(new CellRangeAddress(1, // first row (0-based)
				1, // last row (0-based)
				1, // first column (0-based)
				8 // last column (0-based)
		));

		Font fontContentHeader = workbook.createFont();
		fontContentHeader.setFontHeightInPoints((short) 10);
		fontContentHeader.setFontName("Times New Roman");
		fontContentHeader.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fontContentHeader.setColor(HSSFColor.BLACK.index);
		
		// Style the cell with borders all around.
		CellStyle styleHeaderBox = workbook.createCellStyle();
		styleHeaderBox.setBorderBottom(CellStyle.BORDER_THIN);
		styleHeaderBox.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		styleHeaderBox.setBorderLeft(CellStyle.BORDER_THIN);
		styleHeaderBox.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		styleHeaderBox.setBorderRight(CellStyle.BORDER_THIN);
		styleHeaderBox.setRightBorderColor(IndexedColors.BLACK.getIndex());
		styleHeaderBox.setBorderTop(CellStyle.BORDER_THIN);
		styleHeaderBox.setTopBorderColor(IndexedColors.BLACK.getIndex());
		styleHeaderBox.setFont(fontContentHeader);
		styleHeaderBox.setWrapText(true);
		styleHeaderBox.setAlignment(CellStyle.ALIGN_CENTER);

		iCurrentRow = 10;
		HSSFRow boxHeader = sheet.createRow(iCurrentRow);
		boxHeader.createCell(0).setCellValue("STT");
		boxHeader.getCell(0).setCellStyle(styleHeaderBox);
		boxHeader.createCell(1).setCellValue("Họ và Tên tác giả");
		boxHeader.getCell(1).setCellStyle(styleHeaderBox);
		boxHeader.createCell(2).setCellValue("Tên bài báo");
		boxHeader.getCell(2).setCellStyle(styleHeaderBox);
		boxHeader.createCell(3).setCellValue("Tạp chí/Proceedings");
		boxHeader.getCell(3).setCellStyle(styleHeaderBox);
		boxHeader.createCell(4).setCellValue(" ");
		boxHeader.createCell(5).setCellValue(" ");
		
		
		iCurrentRow++;
		HSSFRow boxHeader1 = sheet.createRow(iCurrentRow);
		boxHeader1.createCell(0).setCellValue(" ");
		boxHeader1.createCell(1).setCellValue(" ");
		boxHeader1.createCell(2).setCellValue(" ");
		
		boxHeader1.createCell(3).setCellValue("Tên tạp chí");
		boxHeader1.getCell(3).setCellStyle(styleHeaderBox);
		boxHeader1.createCell(4).setCellValue("Số và thời gian xuất bản");
		boxHeader1.getCell(4).setCellStyle(styleHeaderBox);
		boxHeader1.createCell(5).setCellValue("Chỉ số ISSN");
		boxHeader1.getCell(5).setCellStyle(styleHeaderBox);
		
		sheet.setColumnWidth(0, 1500);
		sheet.setColumnWidth(1, 10000);
		sheet.setColumnWidth(2, 10000);
		sheet.setColumnWidth(3, 5000);
		sheet.setColumnWidth(4, 5000);
		sheet.setColumnWidth(5, 5000);
		
		for(int i = iCurrentRow-1; i <= iCurrentRow; i++){
			HSSFRow r = sheet.getRow(i);
			for(int j = 0; j <= 5; j++){
				HSSFCell cell = r.getCell(j);
				cell.setCellStyle(styleHeaderBox);
			}
		}
		sheet.addMergedRegion(new CellRangeAddress(iCurrentRow-1,iCurrentRow, 0,0));
		sheet.addMergedRegion(new CellRangeAddress(iCurrentRow-1,iCurrentRow, 1,1));
		sheet.addMergedRegion(new CellRangeAddress(iCurrentRow-1,iCurrentRow, 2,2));
		sheet.addMergedRegion(new CellRangeAddress(iCurrentRow-1,iCurrentRow-1, 3,5));
		

		// style.setWrapText(true);

		Font fontContent = workbook.createFont();
		fontContent.setFontHeightInPoints((short) 10);
		fontContent.setFontName("Times New Roman");
		fontContent.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		fontContent.setColor(HSSFColor.BLACK.index);

		CellStyle styleContent = workbook.createCellStyle();
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
		styleContentBox.setWrapText(true);

		int idx = 0;
		List<mPapers> listPapers = (List<mPapers>) model.get("listPapers");
		if(listPapers != null)for (int i = 0; i < listPapers.size(); i++) {
			mPapers p = listPapers.get(i);
			if (p.getPDECL_PaperCategory_Code().equals("JINT_SCI")
					|| p.getPDECL_PaperCategory_Code().equals("JINT_SCIE")) {
				iCurrentRow++;
				idx++;
				HSSFRow r = sheet.createRow(iCurrentRow);
				r.createCell(0).setCellValue(idx);
				r.createCell(1).setCellValue(p.getPDECL_AuthorList());
				r.createCell(2).setCellValue(p.getPDECL_PublicationName());
				r.createCell(3)
						.setCellValue(p.getPDECL_JournalConferenceName());
				r.createCell(4).setCellValue(
						"Vol. " + p.getPDECL_Volumn() + ", "
								+ p.getPDECL_Year());
				r.createCell(5).setCellValue(p.getPDECL_ISSN());

				for (int j = 0; j <= 5; j++) {
					r.getCell(j).setCellStyle(styleContentBox);
				}
			}
		}

		
		iCurrentRow += 4;
		HSSFRow date = sheet.createRow(iCurrentRow);
		date.createCell(4).setCellValue("Ngày      tháng      năm 2015");
		date.getCell(4).setCellStyle(style);

		iCurrentRow += 1;
		HSSFRow signed = sheet.createRow(iCurrentRow);
		signed.createCell(4).setCellValue("LÃNH ĐẠO KHOA/VIỆN");
		signed.getCell(4).setCellStyle(style);
		
		response.setHeader("Content-Disposition", "attachement; filename=\"" + "Mau-02-tong-hop-bai-bao-ISI.xls\"");
	}
}
