package vn.webapp.modules.researchdeclarationmanagement.controller.cp;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

import vn.webapp.modules.researchdeclarationmanagement.model.SetPaperStaffs;
import vn.webapp.modules.researchdeclarationmanagement.model.SetPapers;
import vn.webapp.modules.researchdeclarationmanagement.model.mPapers;
import vn.webapp.modules.researchdeclarationmanagement.model.mPapersCategoryHourBudget;

public class mExcelKV04SummaryBuilder extends AbstractExcelView {
	
	public String name(){
		return "mExcelKV04SummaryBuilder";
	}
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// get data model which is passed by the Spring container
		List<mPapers> listPapers = (List<mPapers>) model.get("papersList");
		
		List<mPapers> papersListJINT = (List<mPapers>) model.get("papersListJINT");
		List<mPapers> papersListJDOM_Other = (List<mPapers>) model.get("papersListJDOM_Other");
		List<mPapers> papersListCINT_Other = (List<mPapers>) model.get("papersListCINT_Other");
		List<mPapers> papersListCDOM_Other = (List<mPapers>) model.get("papersListCDOM_Other");
		
		List<SetPapers> SetPapersListJINT = (List<SetPapers>) model.get("SetPapersListJINT");
		List<SetPapers> SetPapersListJDOM_Other = (List<SetPapers>) model.get("SetPapersListJDOM_Other");
		List<SetPapers> SetPapersListCINT_Other = (List<SetPapers>) model.get("SetPapersListCINT_Other");
		List<SetPapers> SetPapersListCDOM_Other = (List<SetPapers>) model.get("SetPapersListCDOM_Other");
		
		int i_JDOMOther_Budget = (int) model.get("i_JDOMOther_Budget");
		int i_CINTOther_Budget = (int) model.get("i_CINTOther_Budget");
		int i_CDOMOther_Budget = (int) model.get("i_CDOMOther_Budget");
		int i_JINT_Budget = (int) model.get("i_JINT_Budget");

		// create a new Excel sheet
		HSSFSheet sheet = workbook.createSheet("04-KV");
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
		
		title.createCell(1).setCellValue(" BẢNG KÊ DANH MỤC BÀI BÁO TÍNH KINH PHÍ HỖ TRỢ NĂM HỌC " + yearOfPaper);
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
		
		/**
		 * Create a cell for normal text
		 */
		// create style for sub title cells
		CellStyle styleNormal = workbook.createCellStyle();
		Font fontNormal = workbook.createFont();
		fontNormal.setFontHeightInPoints((short)10);
		fontNormal.setFontName("Times New Roman");
		fontNormal.setColor(HSSFColor.BLACK.index);
		styleNormal.setFont(fontNormal);
		
		iCurrentRow += 3;
		// create sub title of the paper
		HSSFRow subTitle = sheet.createRow(iCurrentRow);
		subTitle.createCell(1).setCellValue("Khoa/Viện : ..............................");
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
	    styleBox2.setWrapText(true);
	    styleBox2.setFillForegroundColor(HSSFColor.LIME.index);
	    styleBox2.setFillPattern(CellStyle.SOLID_FOREGROUND);
	    
	    iCurrentRow = 7;
	    HSSFRow bHeader = sheet.createRow(iCurrentRow);
	    bHeader.createCell(1).setCellValue("TT");
	    bHeader.createCell(2).setCellValue("Họ và tên các tác giả, đơn vị (ghi chi tiết)");
	    bHeader.createCell(3).setCellValue("Tên bài báo");
	    bHeader.createCell(4).setCellValue("Tạp chí, Proceedings");
	    bHeader.createCell(5).setCellValue(" ");
	    bHeader.createCell(6).setCellValue(" ");
	    bHeader.createCell(7).setCellValue("Mức hỗ trợ/bài báo");
	    bHeader.createCell(8).setCellValue("Số đồng tác giả/bài báo");
	    bHeader.createCell(9).setCellValue("Số người thuộc đơn vị/bài báo");
	    bHeader.createCell(10).setCellValue("Kính phí được hỗ trợ/bài báo/tổng số cán bộ của đơn vị");
	    bHeader.createCell(11).setCellValue("Tên người được nhận");
	    bHeader.createCell(12).setCellValue("Số tiền nhận/người/bài báo");
	    
	    iCurrentRow++;
	    bHeader = sheet.createRow(iCurrentRow);
	    bHeader.createCell(1).setCellValue(" ");
	    bHeader.createCell(2).setCellValue(" ");
	    bHeader.createCell(3).setCellValue(" ");
	    bHeader.createCell(4).setCellValue("Tạp chí, Proceedings");
	    bHeader.createCell(5).setCellValue("Số tạp chí, Thời gian xuất bản");
	    bHeader.createCell(6).setCellValue("Chỉ số ISSN hoặc ISBN");
	    bHeader.createCell(7).setCellValue(" ");
	    bHeader.createCell(8).setCellValue(" ");
	    bHeader.createCell(9).setCellValue(" ");
	    bHeader.createCell(10).setCellValue(" ");
	    bHeader.createCell(11).setCellValue(" ");
	    bHeader.createCell(12).setCellValue(" ");
	    
	    sheet.setColumnWidth(1, 1500);
		sheet.setColumnWidth(2, 10000);
		sheet.setColumnWidth(3, 10000);
		sheet.setColumnWidth(4, 5000);
		sheet.setColumnWidth(5, 5000);
		sheet.setColumnWidth(6, 5000);
		
	    for(int i = iCurrentRow-1; i <= iCurrentRow; i++){
	    	HSSFRow r = sheet.getRow(i);
	    	for(int j = 1; j <= 12; j++){
	    		HSSFCell cell = r.getCell(j);
	    		cell.setCellStyle(styleBox);
	    	}
	    }
	    
	    sheet.addMergedRegion(new CellRangeAddress(iCurrentRow-1,iCurrentRow, 1,1));
		sheet.addMergedRegion(new CellRangeAddress(iCurrentRow-1,iCurrentRow, 2,2));
		sheet.addMergedRegion(new CellRangeAddress(iCurrentRow-1,iCurrentRow, 3,3));
		sheet.addMergedRegion(new CellRangeAddress(iCurrentRow-1,iCurrentRow-1, 4,6));
		sheet.addMergedRegion(new CellRangeAddress(iCurrentRow-1,iCurrentRow, 7,7));
		sheet.addMergedRegion(new CellRangeAddress(iCurrentRow-1,iCurrentRow, 8,8));
		sheet.addMergedRegion(new CellRangeAddress(iCurrentRow-1,iCurrentRow, 9,9));
		sheet.addMergedRegion(new CellRangeAddress(iCurrentRow-1,iCurrentRow, 10,10));
		sheet.addMergedRegion(new CellRangeAddress(iCurrentRow-1,iCurrentRow, 11,11));
		sheet.addMergedRegion(new CellRangeAddress(iCurrentRow-1,iCurrentRow, 12,12));

		iCurrentRow += 1;
	    bHeader = sheet.createRow(iCurrentRow);
	    HSSFRow r1 = sheet.getRow(iCurrentRow);
	    for(int i = 1; i <= 12; i++)
	    {	
	    	bHeader.createCell(i).setCellValue(i);
	    	HSSFCell cell = r1.getCell(i);
    		cell.setCellStyle(styleBox);
	    }
	    
	    
		/**
		 * Show content's first category
		 */
		iCurrentRow++;
		HSSFRow boxFisrtCat = sheet.createRow(iCurrentRow);
		boxFisrtCat.createCell(1).setCellValue("I");
		boxFisrtCat.getCell(1).setCellStyle(styleBox);
		
		boxFisrtCat.createCell(2).setCellValue("Bài báo đăng trong tạp chí nước ngoài (Không tính các bài báo trong danh mục SCI, SCIE)");
		boxFisrtCat.getCell(2).setCellStyle(styleBox2);
		boxFisrtCat.createCell(7).setCellValue(Integer.toString(i_JINT_Budget));
		boxFisrtCat.getCell(7).setCellStyle(styleBox2);
		boxFisrtCat.createCell(11).setCellValue("Tên");
		boxFisrtCat.getCell(11).setCellStyle(styleBox2);
		boxFisrtCat.createCell(12).setCellValue("Tiền");
		boxFisrtCat.getCell(12).setCellStyle(styleBox2);
		
		for(int iIterator = 1; iIterator <= 12; iIterator++){
			if(iIterator != 2 && iIterator != 7 && iIterator != 11 && iIterator != 12 ){
				boxFisrtCat.createCell(iIterator).setCellValue("");
				boxFisrtCat.getCell(iIterator).setCellStyle(styleBox2);
			}
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
		iCurrentRow++;
		int iTotalConvertedHours = 0;
		
		/*
		if(SetPapersListJINT != null)
		{
			int iRowMerged = 0;
			for (SetPapers ASetPaper : SetPapersListJINT) {
				iRowMerged = ASetPaper.getPaperStaffs().size();
				
				HSSFRow boxFisrtCatContent = sheet.createRow(iCurrentRow);
				
				boxFisrtCatContent.createCell(1).setCellValue(iCount++); // STT
				boxFisrtCatContent.getCell(1).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(2).setCellValue(ASetPaper.getPaperAuthorsName()); // Ho ten tac gia
				boxFisrtCatContent.getCell(2).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(3).setCellValue(ASetPaper.getPaperPublicName()); // Ten bai bao
				boxFisrtCatContent.getCell(3).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(4).setCellValue(ASetPaper.getPaperJournalConferencecName()); // Ten tap chi
				boxFisrtCatContent.getCell(4).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(5).setCellValue(ASetPaper.getPaperVolumn()); // So tap chi
				boxFisrtCatContent.getCell(5).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(6).setCellValue(ASetPaper.getPaperISSN()); // Chi so ISSN
				boxFisrtCatContent.getCell(6).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(7).setCellValue(ASetPaper.getPaperGeneralBudget()); // Muc ho tro bai bao
				boxFisrtCatContent.getCell(7).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(8).setCellValue(ASetPaper.getPaperTotalNumberOfAuthors()); // So dong tac gia bai bao
				boxFisrtCatContent.getCell(8).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(9).setCellValue(ASetPaper.getPaperTotalNumberOfInternalAuthors()); // So nguoi thuoc don vi bai bao
				boxFisrtCatContent.getCell(9).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(10).setCellValue(ASetPaper.getPaperBudgetOfInternalAuthors()); // Kinh phi duoc ho tro bai bao
				boxFisrtCatContent.getCell(10).setCellStyle(styleContentBox);
				
				//iTotalConvertedHours += aPaper.getPDECL_AuthorConvertedHours();
				
				if(ASetPaper.getPaperStaffs().size() > 0)
				{
					int iCounter = 0;
					int iMergeCurrentRow = iCurrentRow;
					for (SetPaperStaffs ASetPaperStaff : ASetPaper.getPaperStaffs()) {
						System.out.println(name() + "::buildExcelDocument, paper " + ASetPaper.getPaperPublicName() + " staff " + ASetPaperStaff.getPaperStaffName());
						boxFisrtCatContent.createCell(11).setCellValue(ASetPaperStaff.getPaperStaffName()); // Ten nguoi duoc nhan
						boxFisrtCatContent.getCell(11).setCellStyle(styleContentBox);
						
						boxFisrtCatContent.createCell(12).setCellValue(ASetPaper.getPaperBudgetForOneAuthor()); // So tien nhan/nguoi/bai bao
						boxFisrtCatContent.getCell(12).setCellStyle(styleContentBox);
						if(iCounter > 0){
							iMergeCurrentRow = iCurrentRow + iCounter;
							boxFisrtCatContent = sheet.createRow(iMergeCurrentRow);
							boxFisrtCatContent.createCell(11).setCellValue(ASetPaperStaff.getPaperStaffName()); // Ten nguoi duoc nhan
							boxFisrtCatContent.getCell(11).setCellStyle(styleContentBox);
							
							boxFisrtCatContent.createCell(12).setCellValue(ASetPaper.getPaperBudgetForOneAuthor()); // So tien nhan/nguoi/bai bao
							boxFisrtCatContent.getCell(12).setCellStyle(styleContentBox);
						}
						iCounter++;
					}
					iCurrentRow = iMergeCurrentRow;
				}else{
					boxFisrtCatContent.createCell(11).setCellValue("N/A"); // Ten nguoi duoc nhan
					boxFisrtCatContent.getCell(11).setCellStyle(styleContentBox);
					
					boxFisrtCatContent.createCell(12).setCellValue("N/A"); // So tien nhan/nguoi/bai bao
					boxFisrtCatContent.getCell(12).setCellStyle(styleContentBox);
				}
				if(iRowMerged > 0){
					for(int i = 1; i<=10 ; i++)
					{
						sheet.addMergedRegion(new CellRangeAddress(
					            iCurrentRow - iRowMerged + 1, //first row (0-based)
					            iCurrentRow, //last row  (0-based)
					            i, //first column (0-based)
					            i //last column  (0-based)
					    ));
					}
				}
				iCurrentRow++;
			}
		}
		*/

		if(SetPapersListJINT != null)
		{
			int iRowMerged = 0;
			for (SetPapers ASetPaper : SetPapersListJINT) {
				iRowMerged = iCurrentRow;//ASetPaper.getPaperStaffs().size();
				
				HSSFRow boxFisrtCatContent = sheet.createRow(iCurrentRow);
				
				boxFisrtCatContent.createCell(1).setCellValue(iCount++); // STT
				boxFisrtCatContent.getCell(1).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(2).setCellValue(ASetPaper.getPaperAuthorsName()); // Ho ten tac gia
				boxFisrtCatContent.getCell(2).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(3).setCellValue(ASetPaper.getPaperPublicName()); // Ten bai bao
				boxFisrtCatContent.getCell(3).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(4).setCellValue(ASetPaper.getPaperJournalConferencecName()); // Ten tap chi
				boxFisrtCatContent.getCell(4).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(5).setCellValue(ASetPaper.getPaperVolumn()); // So tap chi
				boxFisrtCatContent.getCell(5).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(6).setCellValue(ASetPaper.getPaperISSN()); // Chi so ISSN
				boxFisrtCatContent.getCell(6).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(7).setCellValue(ASetPaper.getPaperGeneralBudget()); // Muc ho tro bai bao
				boxFisrtCatContent.getCell(7).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(8).setCellValue(ASetPaper.getPaperTotalNumberOfAuthors()); // So dong tac gia bai bao
				boxFisrtCatContent.getCell(8).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(9).setCellValue(ASetPaper.getPaperTotalNumberOfInternalAuthors()); // So nguoi thuoc don vi bai bao
				boxFisrtCatContent.getCell(9).setCellStyle(styleContentBox);
				
				boxFisrtCatContent.createCell(10).setCellValue(ASetPaper.getPaperBudgetOfInternalAuthors()); // Kinh phi duoc ho tro bai bao
				boxFisrtCatContent.getCell(10).setCellStyle(styleContentBox);
				
				//iTotalConvertedHours += aPaper.getPDECL_AuthorConvertedHours();
				
				if(ASetPaper.getPaperStaffs().size() > 0)
				{
					int iCounter = 0;
					//int iMergeCurrentRow = iCurrentRow;
					for (SetPaperStaffs ASetPaperStaff : ASetPaper.getPaperStaffs()) {
						System.out.println(name() + "::buildExcelDocument, paper " + ASetPaper.getPaperPublicName() + " staff " + ASetPaperStaff.getPaperStaffName());
						if(iCounter > 0){
							iCurrentRow++;
							boxFisrtCatContent = sheet.createRow(iCurrentRow);
						}
						boxFisrtCatContent.createCell(11).setCellValue(ASetPaperStaff.getPaperStaffName()); // Ten nguoi duoc nhan
						boxFisrtCatContent.getCell(11).setCellStyle(styleContentBox);
						
						boxFisrtCatContent.createCell(12).setCellValue(ASetPaper.getPaperBudgetForOneAuthor()); // So tien nhan/nguoi/bai bao
						boxFisrtCatContent.getCell(12).setCellStyle(styleContentBox);
						/*
						if(iCounter > 0){
							iMergeCurrentRow = iCurrentRow + iCounter;
							boxFisrtCatContent = sheet.createRow(iMergeCurrentRow);
							boxFisrtCatContent.createCell(11).setCellValue(ASetPaperStaff.getPaperStaffName()); // Ten nguoi duoc nhan
							boxFisrtCatContent.getCell(11).setCellStyle(styleContentBox);
							
							boxFisrtCatContent.createCell(12).setCellValue(ASetPaper.getPaperBudgetForOneAuthor()); // So tien nhan/nguoi/bai bao
							boxFisrtCatContent.getCell(12).setCellStyle(styleContentBox);
						}
						*/
						iCounter++;
						//iCurrentRow++;
					}
					//iCurrentRow = iMergeCurrentRow;
					//iCurrentRow++;
				}else{
					boxFisrtCatContent.createCell(11).setCellValue("N/A"); // Ten nguoi duoc nhan
					boxFisrtCatContent.getCell(11).setCellStyle(styleContentBox);
					
					boxFisrtCatContent.createCell(12).setCellValue("N/A"); // So tien nhan/nguoi/bai bao
					boxFisrtCatContent.getCell(12).setCellStyle(styleContentBox);
				}
				if(iRowMerged > 0){
					for(int i = 1; i<=10 ; i++)
					{
						sheet.addMergedRegion(new CellRangeAddress(
					            //iCurrentRow - iRowMerged + 1, //first row (0-based)
					            iRowMerged,
								iCurrentRow, //last row  (0-based)
					            i, //first column (0-based)
					            i //last column  (0-based)
					    ));
					}
				}
				iCurrentRow++;
			}
		}
		
		
		
		/**
		 * Show content's second category 
		 */
		HSSFRow boxSecondCat = sheet.createRow(iCurrentRow);
		boxSecondCat.createCell(1).setCellValue("II");
		boxSecondCat.getCell(1).setCellStyle(styleBox);
		
		boxSecondCat.createCell(2).setCellValue("Bài báo đăng trong tạp chí trong nước");
		boxSecondCat.getCell(2).setCellStyle(styleBox2);
		boxSecondCat.createCell(7).setCellValue(Integer.toString(i_JDOMOther_Budget));
		boxSecondCat.getCell(7).setCellStyle(styleBox2);
		for(int iIterator = 1; iIterator <= 12; iIterator++){
			if(iIterator != 2 && iIterator != 7){
				boxSecondCat.createCell(iIterator).setCellValue("");
				boxSecondCat.getCell(iIterator).setCellStyle(styleBox2);
			}
		}

		iCount = 1;
		iCurrentRow++;
		/*
		if(SetPapersListJDOM_Other != null)
		{
			int iRowMerged = 0;
			for (SetPapers ASetPaper : SetPapersListJDOM_Other) {
				iRowMerged = ASetPaper.getPaperStaffs().size();
				
				HSSFRow boxSecondCatContent = sheet.createRow(iCurrentRow);
				
				boxSecondCatContent.createCell(1).setCellValue(iCount++); // STT
				boxSecondCatContent.getCell(1).setCellStyle(styleContentBox);
				
				boxSecondCatContent.createCell(2).setCellValue(ASetPaper.getPaperAuthorsName()); // Ho ten tac gia
				boxSecondCatContent.getCell(2).setCellStyle(styleContentBox);
				
				boxSecondCatContent.createCell(3).setCellValue(ASetPaper.getPaperPublicName()); // Ten bai bao
				boxSecondCatContent.getCell(3).setCellStyle(styleContentBox);
				
				boxSecondCatContent.createCell(4).setCellValue(ASetPaper.getPaperJournalConferencecName()); // Ten tap chi
				boxSecondCatContent.getCell(4).setCellStyle(styleContentBox);
				
				boxSecondCatContent.createCell(5).setCellValue(ASetPaper.getPaperVolumn()); // So tap chi
				boxSecondCatContent.getCell(5).setCellStyle(styleContentBox);
				
				boxSecondCatContent.createCell(6).setCellValue(ASetPaper.getPaperISSN()); // Chi so ISSN
				boxSecondCatContent.getCell(6).setCellStyle(styleContentBox);
				
				boxSecondCatContent.createCell(7).setCellValue(ASetPaper.getPaperGeneralBudget()); // Muc ho tro bai bao
				boxSecondCatContent.getCell(7).setCellStyle(styleContentBox);
				
				boxSecondCatContent.createCell(8).setCellValue(ASetPaper.getPaperTotalNumberOfAuthors()); // So dong tac gia bai bao
				boxSecondCatContent.getCell(8).setCellStyle(styleContentBox);
				
				boxSecondCatContent.createCell(9).setCellValue(ASetPaper.getPaperTotalNumberOfInternalAuthors()); // So nguoi thuoc don vi bai bao
				boxSecondCatContent.getCell(9).setCellStyle(styleContentBox);
				
				boxSecondCatContent.createCell(10).setCellValue(ASetPaper.getPaperBudgetOfInternalAuthors()); // Kinh phi duoc ho tro bai bao
				boxSecondCatContent.getCell(10).setCellStyle(styleContentBox);
				
				//iTotalConvertedHours += aPaper.getPDECL_AuthorConvertedHours();
				
				if(ASetPaper.getPaperStaffs().size() > 0)
				{
					int iCounter = 0;
					int iMergeCurrentRow = iCurrentRow;
					for (SetPaperStaffs ASetPaperStaff : ASetPaper.getPaperStaffs()) {
						boxSecondCatContent.createCell(11).setCellValue(ASetPaperStaff.getPaperStaffName()); // Ten nguoi duoc nhan
						boxSecondCatContent.getCell(11).setCellStyle(styleContentBox);
						
						boxSecondCatContent.createCell(12).setCellValue(ASetPaper.getPaperBudgetForOneAuthor()); // So tien nhan/nguoi/bai bao
						boxSecondCatContent.getCell(12).setCellStyle(styleContentBox);
						if(iCounter > 0){
							iMergeCurrentRow = iCurrentRow + iCounter;
							boxSecondCatContent = sheet.createRow(iMergeCurrentRow);
							boxSecondCatContent.createCell(11).setCellValue(ASetPaperStaff.getPaperStaffName()); // Ten nguoi duoc nhan
							boxSecondCatContent.getCell(11).setCellStyle(styleContentBox);
							
							boxSecondCatContent.createCell(12).setCellValue(ASetPaper.getPaperBudgetForOneAuthor()); // So tien nhan/nguoi/bai bao
							boxSecondCatContent.getCell(12).setCellStyle(styleContentBox);
						}
						iCounter++;
					}
					iCurrentRow = iMergeCurrentRow;
				}else{
					boxSecondCatContent.createCell(11).setCellValue("N/A"); // Ten nguoi duoc nhan
					boxSecondCatContent.getCell(11).setCellStyle(styleContentBox);
					
					boxSecondCatContent.createCell(12).setCellValue("N/A"); // So tien nhan/nguoi/bai bao
					boxSecondCatContent.getCell(12).setCellStyle(styleContentBox);
				}
				if(iRowMerged > 0){
					for(int i = 1; i<=10 ; i++)
					{
						sheet.addMergedRegion(new CellRangeAddress(
					            iCurrentRow - iRowMerged + 1, //first row (0-based)
					            iCurrentRow, //last row  (0-based)
					            i, //first column (0-based)
					            i //last column  (0-based)
					    ));
					}
				}
				iCurrentRow++;
			}
		}
		*/
		
		if(SetPapersListJDOM_Other != null)
		{
			int iRowMerged = 0;
			for (SetPapers ASetPaper : SetPapersListJDOM_Other) {
				iRowMerged = iCurrentRow;//ASetPaper.getPaperStaffs().size();
				
				HSSFRow boxSecondCatContent = sheet.createRow(iCurrentRow);
				
				boxSecondCatContent.createCell(1).setCellValue(iCount++); // STT
				boxSecondCatContent.getCell(1).setCellStyle(styleContentBox);
				
				boxSecondCatContent.createCell(2).setCellValue(ASetPaper.getPaperAuthorsName()); // Ho ten tac gia
				boxSecondCatContent.getCell(2).setCellStyle(styleContentBox);
				
				boxSecondCatContent.createCell(3).setCellValue(ASetPaper.getPaperPublicName()); // Ten bai bao
				boxSecondCatContent.getCell(3).setCellStyle(styleContentBox);
				
				boxSecondCatContent.createCell(4).setCellValue(ASetPaper.getPaperJournalConferencecName()); // Ten tap chi
				boxSecondCatContent.getCell(4).setCellStyle(styleContentBox);
				
				boxSecondCatContent.createCell(5).setCellValue(ASetPaper.getPaperVolumn()); // So tap chi
				boxSecondCatContent.getCell(5).setCellStyle(styleContentBox);
				
				boxSecondCatContent.createCell(6).setCellValue(ASetPaper.getPaperISSN()); // Chi so ISSN
				boxSecondCatContent.getCell(6).setCellStyle(styleContentBox);
				
				boxSecondCatContent.createCell(7).setCellValue(ASetPaper.getPaperGeneralBudget()); // Muc ho tro bai bao
				boxSecondCatContent.getCell(7).setCellStyle(styleContentBox);
				
				boxSecondCatContent.createCell(8).setCellValue(ASetPaper.getPaperTotalNumberOfAuthors()); // So dong tac gia bai bao
				boxSecondCatContent.getCell(8).setCellStyle(styleContentBox);
				
				boxSecondCatContent.createCell(9).setCellValue(ASetPaper.getPaperTotalNumberOfInternalAuthors()); // So nguoi thuoc don vi bai bao
				boxSecondCatContent.getCell(9).setCellStyle(styleContentBox);
				
				boxSecondCatContent.createCell(10).setCellValue(ASetPaper.getPaperBudgetOfInternalAuthors()); // Kinh phi duoc ho tro bai bao
				boxSecondCatContent.getCell(10).setCellStyle(styleContentBox);
				
				//iTotalConvertedHours += aPaper.getPDECL_AuthorConvertedHours();
				
				if(ASetPaper.getPaperStaffs().size() > 0)
				{
					int iCounter = 0;
					int iMergeCurrentRow = iCurrentRow;
					for (SetPaperStaffs ASetPaperStaff : ASetPaper.getPaperStaffs()) {
						System.out.println(name() + "::buildExcelDocument, paper " + ASetPaper.getPaperPublicName() + " staff " + ASetPaperStaff.getPaperStaffName());
						if(iCounter > 0){
							iCurrentRow++;
							boxSecondCatContent = sheet.createRow(iCurrentRow);
						}
						boxSecondCatContent.createCell(11).setCellValue(ASetPaperStaff.getPaperStaffName()); // Ten nguoi duoc nhan
						boxSecondCatContent.getCell(11).setCellStyle(styleContentBox);
						
						boxSecondCatContent.createCell(12).setCellValue(ASetPaper.getPaperBudgetForOneAuthor()); // So tien nhan/nguoi/bai bao
						boxSecondCatContent.getCell(12).setCellStyle(styleContentBox);
						/*
						if(iCounter > 0){
							iMergeCurrentRow = iCurrentRow + iCounter;
							boxSecondCatContent = sheet.createRow(iMergeCurrentRow);
							boxSecondCatContent.createCell(11).setCellValue(ASetPaperStaff.getPaperStaffName()); // Ten nguoi duoc nhan
							boxSecondCatContent.getCell(11).setCellStyle(styleContentBox);
							
							boxSecondCatContent.createCell(12).setCellValue(ASetPaper.getPaperBudgetForOneAuthor()); // So tien nhan/nguoi/bai bao
							boxSecondCatContent.getCell(12).setCellStyle(styleContentBox);
						}
						*/
						iCounter++;
						//iCurrentRow++;
					}
					//iCurrentRow = iMergeCurrentRow;
				}else{
					boxSecondCatContent.createCell(11).setCellValue("N/A"); // Ten nguoi duoc nhan
					boxSecondCatContent.getCell(11).setCellStyle(styleContentBox);
					
					boxSecondCatContent.createCell(12).setCellValue("N/A"); // So tien nhan/nguoi/bai bao
					boxSecondCatContent.getCell(12).setCellStyle(styleContentBox);
				}
				if(iRowMerged > 0){
					for(int i = 1; i<=10 ; i++)
					{
						sheet.addMergedRegion(new CellRangeAddress(
					            //iCurrentRow - iRowMerged + 1, //first row (0-based)
					            iRowMerged,
					            iCurrentRow, //last row  (0-based)
					            i, //first column (0-based)
					            i //last column  (0-based)
					    ));
					}
				}
				iCurrentRow++;
			}
		}
		
		
		/**
		 * Show content's third category 
		 */
		HSSFRow boxThirdCat = sheet.createRow(iCurrentRow);
		boxThirdCat.createCell(1).setCellValue("III");
		boxThirdCat.getCell(1).setCellStyle(styleBox);
		
		boxThirdCat.createCell(2).setCellValue("Bài báo đăng trong kỷ yếu hội nghị tổ chức ngoài nước (Proceedings)");
		boxThirdCat.getCell(2).setCellStyle(styleBox2);
		boxThirdCat.createCell(7).setCellValue(Integer.toString(i_CINTOther_Budget));
		boxThirdCat.getCell(7).setCellStyle(styleBox2);
		for(int iIterator = 1; iIterator <= 12; iIterator++){
			if(iIterator != 2 && iIterator != 7){
				boxThirdCat.createCell(iIterator).setCellValue("");
				boxThirdCat.getCell(iIterator).setCellStyle(styleBox2);
			}
		}

		iCount = 1;
		iCurrentRow++;
		/*
		if(SetPapersListCINT_Other != null)
		{
			int iRowMerged = 0;
			for (SetPapers ASetPaper : SetPapersListCINT_Other) {
				iRowMerged = ASetPaper.getPaperStaffs().size();
				
				HSSFRow boxThirdCatContent = sheet.createRow(iCurrentRow);
				
				boxThirdCatContent.createCell(1).setCellValue(iCount++); // STT
				boxThirdCatContent.getCell(1).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(2).setCellValue(ASetPaper.getPaperAuthorsName()); // Ho ten tac gia
				boxThirdCatContent.getCell(2).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(3).setCellValue(ASetPaper.getPaperPublicName()); // Ten bai bao
				boxThirdCatContent.getCell(3).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(4).setCellValue(ASetPaper.getPaperJournalConferencecName()); // Ten tap chi
				boxThirdCatContent.getCell(4).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(5).setCellValue(ASetPaper.getPaperVolumn()); // So tap chi
				boxThirdCatContent.getCell(5).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(6).setCellValue(ASetPaper.getPaperISSN()); // Chi so ISSN
				boxThirdCatContent.getCell(6).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(7).setCellValue(ASetPaper.getPaperGeneralBudget()); // Muc ho tro bai bao
				boxThirdCatContent.getCell(7).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(8).setCellValue(ASetPaper.getPaperTotalNumberOfAuthors()); // So dong tac gia bai bao
				boxThirdCatContent.getCell(8).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(9).setCellValue(ASetPaper.getPaperTotalNumberOfInternalAuthors()); // So nguoi thuoc don vi bai bao
				boxThirdCatContent.getCell(9).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(10).setCellValue(ASetPaper.getPaperBudgetOfInternalAuthors()); // Kinh phi duoc ho tro bai bao
				boxThirdCatContent.getCell(10).setCellStyle(styleContentBox);
				
				//iTotalConvertedHours += aPaper.getPDECL_AuthorConvertedHours();
				
				if(ASetPaper.getPaperStaffs().size() > 0)
				{
					int iCounter = 0;
					int iMergeCurrentRow = iCurrentRow;
					for (SetPaperStaffs ASetPaperStaff : ASetPaper.getPaperStaffs()) {
						boxThirdCatContent.createCell(11).setCellValue(ASetPaperStaff.getPaperStaffName()); // Ten nguoi duoc nhan
						boxThirdCatContent.getCell(11).setCellStyle(styleContentBox);
						
						boxThirdCatContent.createCell(12).setCellValue(ASetPaper.getPaperBudgetForOneAuthor()); // So tien nhan/nguoi/bai bao
						boxThirdCatContent.getCell(12).setCellStyle(styleContentBox);
						if(iCounter > 0){
							iMergeCurrentRow = iCurrentRow + iCounter;
							boxThirdCatContent = sheet.createRow(iMergeCurrentRow);
							boxThirdCatContent.createCell(11).setCellValue(ASetPaperStaff.getPaperStaffName()); // Ten nguoi duoc nhan
							boxThirdCatContent.getCell(11).setCellStyle(styleContentBox);
							
							boxThirdCatContent.createCell(12).setCellValue(ASetPaper.getPaperBudgetForOneAuthor()); // So tien nhan/nguoi/bai bao
							boxThirdCatContent.getCell(12).setCellStyle(styleContentBox);
						}
						iCounter++;
					}
					iCurrentRow = iMergeCurrentRow;
				}else{
					boxThirdCatContent.createCell(11).setCellValue("N/A"); // Ten nguoi duoc nhan
					boxThirdCatContent.getCell(11).setCellStyle(styleContentBox);
					
					boxThirdCatContent.createCell(12).setCellValue("N/A"); // So tien nhan/nguoi/bai bao
					boxThirdCatContent.getCell(12).setCellStyle(styleContentBox);
				}
				if(iRowMerged > 0){
					for(int i = 1; i<=10 ; i++)
					{
						sheet.addMergedRegion(new CellRangeAddress(
					            iCurrentRow - iRowMerged + 1, //first row (0-based)
					            iCurrentRow, //last row  (0-based)
					            i, //first column (0-based)
					            i //last column  (0-based)
					    ));
					}
				}
				iCurrentRow++;
			}
		}
		*/
		
		
		if(SetPapersListCINT_Other != null)
		{
			int iRowMerged = 0;
			for (SetPapers ASetPaper : SetPapersListCINT_Other) {
				iRowMerged = iCurrentRow;//ASetPaper.getPaperStaffs().size();
				
				HSSFRow boxThirdCatContent = sheet.createRow(iCurrentRow);
				
				boxThirdCatContent.createCell(1).setCellValue(iCount++); // STT
				boxThirdCatContent.getCell(1).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(2).setCellValue(ASetPaper.getPaperAuthorsName()); // Ho ten tac gia
				boxThirdCatContent.getCell(2).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(3).setCellValue(ASetPaper.getPaperPublicName()); // Ten bai bao
				boxThirdCatContent.getCell(3).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(4).setCellValue(ASetPaper.getPaperJournalConferencecName()); // Ten tap chi
				boxThirdCatContent.getCell(4).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(5).setCellValue(ASetPaper.getPaperVolumn()); // So tap chi
				boxThirdCatContent.getCell(5).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(6).setCellValue(ASetPaper.getPaperISSN()); // Chi so ISSN
				boxThirdCatContent.getCell(6).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(7).setCellValue(ASetPaper.getPaperGeneralBudget()); // Muc ho tro bai bao
				boxThirdCatContent.getCell(7).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(8).setCellValue(ASetPaper.getPaperTotalNumberOfAuthors()); // So dong tac gia bai bao
				boxThirdCatContent.getCell(8).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(9).setCellValue(ASetPaper.getPaperTotalNumberOfInternalAuthors()); // So nguoi thuoc don vi bai bao
				boxThirdCatContent.getCell(9).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(10).setCellValue(ASetPaper.getPaperBudgetOfInternalAuthors()); // Kinh phi duoc ho tro bai bao
				boxThirdCatContent.getCell(10).setCellStyle(styleContentBox);
				
				//iTotalConvertedHours += aPaper.getPDECL_AuthorConvertedHours();
				
				if(ASetPaper.getPaperStaffs().size() > 0)
				{
					int iCounter = 0;
					int iMergeCurrentRow = iCurrentRow;
					for (SetPaperStaffs ASetPaperStaff : ASetPaper.getPaperStaffs()) {
						System.out.println(name() + "::buildExcelDocument, paper " + ASetPaper.getPaperPublicName() + " staff " + ASetPaperStaff.getPaperStaffName());
						if(iCounter > 0){
							iCurrentRow++;
							boxThirdCatContent = sheet.createRow(iCurrentRow);
						}
						boxThirdCatContent.createCell(11).setCellValue(ASetPaperStaff.getPaperStaffName()); // Ten nguoi duoc nhan
						boxThirdCatContent.getCell(11).setCellStyle(styleContentBox);
						
						boxThirdCatContent.createCell(12).setCellValue(ASetPaper.getPaperBudgetForOneAuthor()); // So tien nhan/nguoi/bai bao
						boxThirdCatContent.getCell(12).setCellStyle(styleContentBox);
						/*
						if(iCounter > 0){
							iMergeCurrentRow = iCurrentRow + iCounter;
							boxThirdCatContent = sheet.createRow(iMergeCurrentRow);
							boxThirdCatContent.createCell(11).setCellValue(ASetPaperStaff.getPaperStaffName()); // Ten nguoi duoc nhan
							boxThirdCatContent.getCell(11).setCellStyle(styleContentBox);
							
							boxThirdCatContent.createCell(12).setCellValue(ASetPaper.getPaperBudgetForOneAuthor()); // So tien nhan/nguoi/bai bao
							boxThirdCatContent.getCell(12).setCellStyle(styleContentBox);
						}
						*/
						iCounter++;
					}
					//iCurrentRow = iMergeCurrentRow;
				}else{
					boxThirdCatContent.createCell(11).setCellValue("N/A"); // Ten nguoi duoc nhan
					boxThirdCatContent.getCell(11).setCellStyle(styleContentBox);
					
					boxThirdCatContent.createCell(12).setCellValue("N/A"); // So tien nhan/nguoi/bai bao
					boxThirdCatContent.getCell(12).setCellStyle(styleContentBox);
				}
				if(iRowMerged > 0){
					for(int i = 1; i<=10 ; i++)
					{
						sheet.addMergedRegion(new CellRangeAddress(
					            //iCurrentRow - iRowMerged + 1, //first row (0-based)
					            iRowMerged,
								iCurrentRow, //last row  (0-based)
					            i, //first column (0-based)
					            i //last column  (0-based)
					    ));
					}
				}
				iCurrentRow++;
			}
		}
		
		/**
		 * Show content's fourth category 
		 */
		HSSFRow boxFourthCat = sheet.createRow(iCurrentRow);
		boxFourthCat.createCell(1).setCellValue("IV");
		boxFourthCat.getCell(1).setCellStyle(styleBox);
		
		boxFourthCat.createCell(2).setCellValue("Bài báo đăng trong kỷ yếu hội nghị tổ chức trong nước (Proceedings)");
		boxFourthCat.getCell(2).setCellStyle(styleBox2);
		boxFourthCat.createCell(7).setCellValue(Integer.toString(i_CDOMOther_Budget));
		boxFourthCat.getCell(7).setCellStyle(styleBox2);
		for(int iIterator = 1; iIterator <= 12; iIterator++){
			if(iIterator != 2 && iIterator != 7){
				boxFourthCat.createCell(iIterator).setCellValue("");
				boxFourthCat.getCell(iIterator).setCellStyle(styleBox2);
			}
		}
		
		/**
		 * Show all contents for the fourth category
		 */
		iCount = 1;
		iCurrentRow++;
		/*
		if(SetPapersListCDOM_Other != null)
		{
			int iRowMerged = 0;
			//for (SetPapers ASetPaper : SetPapersListCINT_Other) { BUG 
			for (SetPapers ASetPaper : SetPapersListCDOM_Other) {// fixed DungPQ
				iRowMerged = ASetPaper.getPaperStaffs().size();
				
				HSSFRow boxFourthCatContent = sheet.createRow(iCurrentRow);
				
				boxFourthCatContent.createCell(1).setCellValue(iCount++); // STT
				boxFourthCatContent.getCell(1).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(2).setCellValue(ASetPaper.getPaperAuthorsName()); // Ho ten tac gia
				boxFourthCatContent.getCell(2).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(3).setCellValue(ASetPaper.getPaperPublicName()); // Ten bai bao
				boxFourthCatContent.getCell(3).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(4).setCellValue(ASetPaper.getPaperJournalConferencecName()); // Ten tap chi
				boxFourthCatContent.getCell(4).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(5).setCellValue(ASetPaper.getPaperVolumn()); // So tap chi
				boxFourthCatContent.getCell(5).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(6).setCellValue(ASetPaper.getPaperISSN()); // Chi so ISSN
				boxFourthCatContent.getCell(6).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(7).setCellValue(ASetPaper.getPaperGeneralBudget()); // Muc ho tro bai bao
				boxFourthCatContent.getCell(7).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(8).setCellValue(ASetPaper.getPaperTotalNumberOfAuthors()); // So dong tac gia bai bao
				boxFourthCatContent.getCell(8).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(9).setCellValue(ASetPaper.getPaperTotalNumberOfInternalAuthors()); // So nguoi thuoc don vi bai bao
				boxFourthCatContent.getCell(9).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(10).setCellValue(ASetPaper.getPaperBudgetOfInternalAuthors()); // Kinh phi duoc ho tro bai bao
				boxFourthCatContent.getCell(10).setCellStyle(styleContentBox);
				
				//iTotalConvertedHours += aPaper.getPDECL_AuthorConvertedHours();
				
				if(ASetPaper.getPaperStaffs().size() > 0)
				{
					int iCounter = 0;
					int iMergeCurrentRow = iCurrentRow;
					for (SetPaperStaffs ASetPaperStaff : ASetPaper.getPaperStaffs()) {
						boxFourthCatContent.createCell(11).setCellValue(ASetPaperStaff.getPaperStaffName()); // Ten nguoi duoc nhan
						boxFourthCatContent.getCell(11).setCellStyle(styleContentBox);
						
						boxFourthCatContent.createCell(12).setCellValue(ASetPaper.getPaperBudgetForOneAuthor()); // So tien nhan/nguoi/bai bao
						boxFourthCatContent.getCell(12).setCellStyle(styleContentBox);
						if(iCounter > 0){
							iMergeCurrentRow = iCurrentRow + iCounter;
							boxFourthCatContent = sheet.createRow(iMergeCurrentRow);
							boxFourthCatContent.createCell(11).setCellValue(ASetPaperStaff.getPaperStaffName()); // Ten nguoi duoc nhan
							boxFourthCatContent.getCell(11).setCellStyle(styleContentBox);
							
							boxFourthCatContent.createCell(12).setCellValue(ASetPaper.getPaperBudgetForOneAuthor()); // So tien nhan/nguoi/bai bao
							boxFourthCatContent.getCell(12).setCellStyle(styleContentBox);
						}
						iCounter++;
					}
					iCurrentRow = iMergeCurrentRow;
				}else{
					boxFourthCatContent.createCell(11).setCellValue("N/A"); // Ten nguoi duoc nhan
					boxFourthCatContent.getCell(11).setCellStyle(styleContentBox);
					
					boxFourthCatContent.createCell(12).setCellValue("N/A"); // So tien nhan/nguoi/bai bao
					boxFourthCatContent.getCell(12).setCellStyle(styleContentBox);
				}
				if(iRowMerged > 0){
					for(int i = 1; i<=10 ; i++)
					{
						sheet.addMergedRegion(new CellRangeAddress(
					            iCurrentRow - iRowMerged + 1, //first row (0-based)
					            iCurrentRow, //last row  (0-based)
					            i, //first column (0-based)
					            i //last column  (0-based)
					    ));
					}
				}
				iCurrentRow++;
			}
		}
		*/
		
		if(SetPapersListCDOM_Other != null)
		{
			int iRowMerged = 0;
			//for (SetPapers ASetPaper : SetPapersListCINT_Other) { BUG 
			for (SetPapers ASetPaper : SetPapersListCDOM_Other) {// fixed DungPQ
				iRowMerged = iCurrentRow;//ASetPaper.getPaperStaffs().size();
				
				HSSFRow boxFourthCatContent = sheet.createRow(iCurrentRow);
				
				boxFourthCatContent.createCell(1).setCellValue(iCount++); // STT
				boxFourthCatContent.getCell(1).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(2).setCellValue(ASetPaper.getPaperAuthorsName()); // Ho ten tac gia
				boxFourthCatContent.getCell(2).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(3).setCellValue(ASetPaper.getPaperPublicName()); // Ten bai bao
				boxFourthCatContent.getCell(3).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(4).setCellValue(ASetPaper.getPaperJournalConferencecName()); // Ten tap chi
				boxFourthCatContent.getCell(4).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(5).setCellValue(ASetPaper.getPaperVolumn()); // So tap chi
				boxFourthCatContent.getCell(5).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(6).setCellValue(ASetPaper.getPaperISSN()); // Chi so ISSN
				boxFourthCatContent.getCell(6).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(7).setCellValue(ASetPaper.getPaperGeneralBudget()); // Muc ho tro bai bao
				boxFourthCatContent.getCell(7).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(8).setCellValue(ASetPaper.getPaperTotalNumberOfAuthors()); // So dong tac gia bai bao
				boxFourthCatContent.getCell(8).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(9).setCellValue(ASetPaper.getPaperTotalNumberOfInternalAuthors()); // So nguoi thuoc don vi bai bao
				boxFourthCatContent.getCell(9).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(10).setCellValue(ASetPaper.getPaperBudgetOfInternalAuthors()); // Kinh phi duoc ho tro bai bao
				boxFourthCatContent.getCell(10).setCellStyle(styleContentBox);
				
				//iTotalConvertedHours += aPaper.getPDECL_AuthorConvertedHours();
				
				if(ASetPaper.getPaperStaffs().size() > 0)
				{
					int iCounter = 0;
					int iMergeCurrentRow = iCurrentRow;
					for (SetPaperStaffs ASetPaperStaff : ASetPaper.getPaperStaffs()) {
						System.out.println(name() + "::buildExcelDocument, paper " + ASetPaper.getPaperPublicName() + " staff " + ASetPaperStaff.getPaperStaffName());
						if(iCounter > 0){
							iCurrentRow++;
							boxFourthCatContent = sheet.createRow(iCurrentRow);
						}
						boxFourthCatContent.createCell(11).setCellValue(ASetPaperStaff.getPaperStaffName()); // Ten nguoi duoc nhan
						boxFourthCatContent.getCell(11).setCellStyle(styleContentBox);
						
						boxFourthCatContent.createCell(12).setCellValue(ASetPaper.getPaperBudgetForOneAuthor()); // So tien nhan/nguoi/bai bao
						boxFourthCatContent.getCell(12).setCellStyle(styleContentBox);
						/*
						if(iCounter > 0){
							iMergeCurrentRow = iCurrentRow + iCounter;
							boxFourthCatContent = sheet.createRow(iMergeCurrentRow);
							boxFourthCatContent.createCell(11).setCellValue(ASetPaperStaff.getPaperStaffName()); // Ten nguoi duoc nhan
							boxFourthCatContent.getCell(11).setCellStyle(styleContentBox);
							
							boxFourthCatContent.createCell(12).setCellValue(ASetPaper.getPaperBudgetForOneAuthor()); // So tien nhan/nguoi/bai bao
							boxFourthCatContent.getCell(12).setCellStyle(styleContentBox);
						}
						*/
						iCounter++;
					}
					//iCurrentRow = iMergeCurrentRow;
				}else{
					boxFourthCatContent.createCell(11).setCellValue("N/A"); // Ten nguoi duoc nhan
					boxFourthCatContent.getCell(11).setCellStyle(styleContentBox);
					
					boxFourthCatContent.createCell(12).setCellValue("N/A"); // So tien nhan/nguoi/bai bao
					boxFourthCatContent.getCell(12).setCellStyle(styleContentBox);
				}
				if(iRowMerged > 0){
					for(int i = 1; i<=10 ; i++)
					{
						sheet.addMergedRegion(new CellRangeAddress(
					            //iCurrentRow - iRowMerged + 1, //first row (0-based)
					            iRowMerged,
								iCurrentRow, //last row  (0-based)
					            i, //first column (0-based)
					            i //last column  (0-based)
					    ));
					}
				}
				iCurrentRow++;
			}
		}
		
		/**
		 * Confirmation and Signature
		 */

		iCurrentRow += 3;
		HSSFRow dateYear = sheet.createRow(iCurrentRow);
		dateYear.createCell(5).setCellValue("Ngày.... Tháng.....Năm 2015");
		dateYear.getCell(5).setCellStyle(styleSubtitle);
		
		iCurrentRow += 2;
		HSSFRow confirmation = sheet.createRow(iCurrentRow);
		confirmation.createCell(5).setCellValue("LÃNH ĐẠO KHOA/VIỆN/TTNC");
		confirmation.getCell(5).setCellStyle(styleSubtitle);
		
		iCurrentRow += 4;
		HSSFRow Note = sheet.createRow(iCurrentRow);
		Note.createCell(1).setCellValue("Ghi chú: ");
		Note.getCell(1).setCellStyle(styleNormal);
		
		iCurrentRow += 1;
		HSSFRow Note1 = sheet.createRow(iCurrentRow);
		Note1.createCell(1).setCellValue("- Cột 10 = cột 7/ cột 8 * cột 9");
		Note1.getCell(1).setCellStyle(styleNormal);
		
		iCurrentRow += 1;
		HSSFRow Note2 = sheet.createRow(iCurrentRow);
		Note2.createCell(1).setCellValue("- Cột số 5: cần ghi rõ số Vol và thời gian số báo được đăng chính thức bản in. ");
		Note2.getCell(1).setCellStyle(styleNormal);
		
		iCurrentRow += 1;
		HSSFRow Note3 = sheet.createRow(iCurrentRow);
		Note3.createCell(1).setCellValue("- Cột 9, 10, 11: chỉ tính đối với cán bộ thuộc đơn vị, không tính các cán bộ thuộc đơn vị khác trong trường.");
		Note3.getCell(1).setCellStyle(styleNormal);		

		response.setHeader("Content-Disposition", "attachement; filename=\"" + "Mau-03-KV-tong-hop-bai-bao-toan-don-vi.xls\"");
	}
}
