package vn.webapp.modules.researchdeclarationmanagement.controller.cp;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.itextpdf.tool.xml.html.head.Style;

import vn.webapp.modules.researchdeclarationmanagement.model.mBooks;

public class mExcelSummaryView02KVBook extends AbstractExcelView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest arg2,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HSSFSheet sheet = workbook.createSheet("Danh muc sach giao trinh");
		int currentRow = 0; // dong hien tai
		sheet.createRow(currentRow).createCell(4).setCellValue("Mẫu 02-KV");
		
		currentRow++;
		HSSFRow titleSummary= sheet.createRow(currentRow);
		
		titleSummary.createCell(1).setCellValue("DANH MỤC GIÁO TRÌNH CỦA CÁN BỘ TRƯỜNG ĐHBKHN NĂM"+model.get("reportingAcademicYear"));
		Font fontTitle = workbook.createFont();
		fontTitle.setBold(true);
		fontTitle.setFontHeightInPoints((short)14);
		fontTitle.setFontName("Times New Roman");
		CellStyle style= workbook.createCellStyle();
		style.setFont(fontTitle);
		titleSummary.getCell(1).setCellStyle(style);
		currentRow++;
		titleSummary.setHeightInPoints((short)30);
		sheet.addMergedRegion(new CellRangeAddress(1, // first row (0-based)
				1, // last row (0-based)
				1, // first column (0-based)
				6 // last column (0-based)
		));
		CellStyle styleBox = workbook.createCellStyle();
		styleBox.setBorderBottom(CellStyle.BORDER_THIN);
		styleBox.setBorderLeft(CellStyle.BORDER_THIN);
		styleBox.setBorderRight(CellStyle.BORDER_THIN);
		styleBox.setBorderTop(CellStyle.BORDER_THIN);
		
		HSSFRow faculty = sheet.createRow(currentRow);
		faculty.createCell(1).setCellValue("Khoa/Viện....................................");
		currentRow+=2;
		sheet.setColumnWidth(0, 1500);
		sheet.setColumnWidth(1, 10000);
		sheet.setColumnWidth(2, 10000);
		sheet.setColumnWidth(3, 5500);
		sheet.setColumnWidth(4, 5500);
		sheet.setColumnWidth(5, 5500);
		HSSFRow boxHeader = sheet.createRow(currentRow);
		boxHeader.setRowStyle(styleBox);
		boxHeader.createCell(0).setCellValue("STT");
		boxHeader.createCell(1).setCellValue("Họ tên tác giả");
		boxHeader.createCell(2).setCellValue("Tên giáo trình");
		boxHeader.createCell(3).setCellValue("Nhà xuất bản");
		boxHeader.createCell(4).setCellValue("Thời gian xuất bản");
		boxHeader.createCell(5).setCellValue("Chỉ số ISBN");
		
		int stt=0;
		List<mBooks> books= (List<mBooks>) model.get("listBooks");
		if(books!=null)
			for(int i=0;i<books.size();i++){
				mBooks b= books.get(i);
				currentRow++;
				stt++;
				HSSFRow r = sheet.createRow(currentRow);
				r.createCell(0).setCellValue(stt);
				r.createCell(1).setCellValue(b.getBOK_Authors());
				r.createCell(2).setCellValue(b.getBOK_BookName());
				r.createCell(3).setCellValue(b.getBOK_Publisher());
				r.createCell(4).setCellValue(b.getBOK_PublishedMonth()+"-"+b.getBOK_PublishedYear());
				r.createCell(5).setCellValue(b.getBOK_ISBN());
				for(int j=0;j<5;j++)
					r.getCell(j).setCellStyle(styleBox);
			}
		currentRow += 4;
		HSSFRow date = sheet.createRow(currentRow);
		
		date.createCell(4).setCellValue("Ngày      tháng      năm 2016");
		currentRow += 1;
		HSSFRow signed = sheet.createRow(currentRow);
		signed.createCell(4).setCellValue("LÃNH ĐẠO KHOA/VIỆN");
		CellStyle styleS= workbook.createCellStyle();
		Font f= workbook.createFont();
		f.setBold(true);
		styleS.setFont(f);
		signed.getCell(4).setCellStyle(styleS);
		date.getCell(4).setCellStyle(styleS);
		response.setHeader("Content-Disposition", "attachement; filename=\"" + "Mau-02-tong-hop-giao-trinh.xls\"");
	}

}
