package vn.webapp.modules.researchdeclarationmanagement.controller.cp;

import java.util.ArrayList;
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

public class nExcelKV03SummaryBuilder extends AbstractExcelView {
	private String getFullPaperDescription(mPapers aPaper){
		String paper = aPaper.getPDECL_AuthorList() + ". " +
				aPaper.getPDECL_PublicationName() + ". " +
				aPaper.getPDECL_JournalConferenceName() + ". " + 
				aPaper.getPDECL_Volumn() + ", " +
				aPaper.getPDECL_Year();
		return paper;
	}

	public String name(){
		return "nExcelKV03SammaryBuilder";
	}
	private void buildSheetListPaper(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// get data model which is passed by the Spring container
		List<mPapers> listPapers = (List<mPapers>) model.get("papersList");
		
		List<mPapers> papersListJINT2 = (List<mPapers>) model.get("papersListJINT2");
		List<mPapers> papersListJDOM_Other2 = (List<mPapers>) model.get("papersListJDOM_Other2");
		List<mPapers> papersListCINT_Other2 = (List<mPapers>) model.get("papersListCINT_Other2");
		List<mPapers> papersListCDOM_Other2 = (List<mPapers>) model.get("papersListCDOM_Other2");

		List<mPapers> papersListJINT1 = (List<mPapers>) model.get("papersListJINT1");
		List<mPapers> papersListJDOM_Other1 = (List<mPapers>) model.get("papersListJDOM_Other1");
		List<mPapers> papersListCINT_Other1 = (List<mPapers>) model.get("papersListCINT_Other1");
		List<mPapers> papersListCDOM_Other1 = (List<mPapers>) model.get("papersListCDOM_Other1");
		
		List<mPapers> papersListJINT = new ArrayList<mPapers>();
		List<mPapers> papersListJDOM_Other = new ArrayList<mPapers>();
		List<mPapers> papersListCINT_Other = new ArrayList<mPapers>();
		List<mPapers> papersListCDOM_Other = new ArrayList<mPapers>();

		for(mPapers p: papersListJINT1) papersListJINT.add(p);
		for(mPapers p: papersListJINT2) papersListJINT.add(p);
		for(mPapers p: papersListJDOM_Other1) papersListJDOM_Other.add(p);
		for(mPapers p: papersListJDOM_Other2) papersListJDOM_Other.add(p);
		for(mPapers p: papersListCINT_Other1) papersListCINT_Other.add(p);
		for(mPapers p: papersListCINT_Other2) papersListCINT_Other.add(p);
		for(mPapers p: papersListCDOM_Other1) papersListCDOM_Other.add(p);
		for(mPapers p: papersListCDOM_Other2) papersListCDOM_Other.add(p);
		
		
		// create a new Excel sheet
		HSSFSheet sheet = workbook.createSheet("List-Papers");
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
		
		title.createCell(1).setCellValue("DANH MỤC BÀI BÁO ĐĂNG  TRONG TẠP CHÍ VÀ KỶ YẾU HỘI NGHỊ KHOA HỌC CỦA CÁN BỘ TRƯỜNG ĐH BÁCH KHOA HÀ NỘI NĂM HỌC " + yearOfPaper);
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
	    
	    iCurrentRow = 11;
	    HSSFRow bHeader = sheet.createRow(iCurrentRow);
	    bHeader.createCell(1).setCellValue("STT");
	    bHeader.createCell(2).setCellValue("Papers");
	    //bHeader.createCell(3).setCellValue("Tên bài báo");
	    //bHeader.createCell(4).setCellValue("Tạp chí, Proceedings");
	    //bHeader.createCell(5).setCellValue(" ");
	    //bHeader.createCell(6).setCellValue(" ");
	    
	    /*
	    iCurrentRow++;
	    bHeader = sheet.createRow(iCurrentRow);
	    bHeader.createCell(1).setCellValue(" ");
	    bHeader.createCell(2).setCellValue(" ");
	    bHeader.createCell(3).setCellValue(" ");
	    bHeader.createCell(4).setCellValue("Tạp chí, Proceedings");
	    bHeader.createCell(5).setCellValue("Số tạp chí, Thời gian xuất bản");
	    bHeader.createCell(6).setCellValue("Chỉ số ISSN");
	    */
	    
	    sheet.setColumnWidth(1, 1500);
		sheet.setColumnWidth(2, 30000);
		//sheet.setColumnWidth(3, 10000);
		//sheet.setColumnWidth(4, 5000);
		//sheet.setColumnWidth(5, 5000);
		//sheet.setColumnWidth(6, 5000);
		
	    //for(int i = iCurrentRow-1; i <= iCurrentRow; i++){
	    	HSSFRow r = sheet.getRow(iCurrentRow);
	    	for(int j = 1; j <= 2; j++){
	    		HSSFCell cell = r.getCell(j);
	    		cell.setCellStyle(styleBox);
	    	}
	    //}
	    //sheet.addMergedRegion(new CellRangeAddress(iCurrentRow-1,iCurrentRow, 1,1));
		//sheet.addMergedRegion(new CellRangeAddress(iCurrentRow-1,iCurrentRow, 2,2));
		//sheet.addMergedRegion(new CellRangeAddress(iCurrentRow-1,iCurrentRow, 3,3));
		//sheet.addMergedRegion(new CellRangeAddress(iCurrentRow-1,iCurrentRow-1, 4,6));
		
	    /*
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
	            6  //last column  (0-based)
	    ));
		
		
		iCurrentRow++;
		HSSFRow boxHeader2 = sheet.createRow(12);
		boxHeader2.createCell(4).setCellValue("Tên tạp chí, Proceedings");
		boxHeader2.getCell(4).setCellStyle(styleBox);
		
		boxHeader2.createCell(5).setCellValue("Số và thời gian xuất bản");
		boxHeader2.getCell(5).setCellStyle(styleBox);
		
		boxHeader2.createCell(6).setCellValue("Chỉ số ISSN");
		boxHeader2.getCell(6).setCellStyle(styleBox);
		*/
	    
		
		/**
		 * Show content's first category 
		 */
		iCurrentRow++;
		HSSFRow boxFisrtCat = sheet.createRow(iCurrentRow);
		boxFisrtCat.createCell(1).setCellValue("I");
		boxFisrtCat.getCell(1).setCellStyle(styleBox);
		
		boxFisrtCat.createCell(2).setCellValue("Bài báo đăng trong tạp chí nước ngoài");
		boxFisrtCat.getCell(2).setCellStyle(styleBox2);
		sheet.addMergedRegion(new CellRangeAddress(
	            iCurrentRow, //first row (0-based)
	            iCurrentRow, //last row  (0-based)
	            2, //first column (0-based)
	            2 //last column  (0-based)
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
		List<mPapers> SCI = new ArrayList<mPapers>();
		List<mPapers> SCIE = new ArrayList<mPapers>();
		List<mPapers> IJ = new ArrayList<mPapers>();
		for (mPapers aPaper : papersListJINT) {
			//System.out.println(name() + "::buildSheetListPaper aPaper.category = " + aPaper.getPDECL_PaperCategory_Code());
			if(aPaper.getPaperCategory().getPCAT_Code().equals("JINT_SCI"))
				SCI.add(aPaper);
		}
		for (mPapers aPaper : papersListJINT) {
			if(aPaper.getPaperCategory().getPCAT_Code().equals("JINT_SCIE"))
				SCIE.add(aPaper);
		}
		for (mPapers aPaper : papersListJINT) {
			if(aPaper.getPaperCategory().getPCAT_Code().equals("JINT_Other"))
				IJ.add(aPaper);
		}
		
		if(papersListJINT != null)
		{
			//for (mPapers aPaper : papersListJINT) {
		
			// list of SCI
			HSSFRow row = sheet.createRow(iCurrentRow);
			row.createCell(1).setCellValue("");
			row.getCell(1).setCellStyle(styleContentBox);
			row.createCell(2).setCellValue("SCI");
			row.getCell(2).setCellStyle(styleBox2);
			
			iCurrentRow++;
			
			for(mPapers aPaper: SCI){
				row = sheet.createRow(iCurrentRow);
				
				row.createCell(1).setCellValue(iCount++);
				row.getCell(1).setCellStyle(styleContentBox);
				
				String paper = getFullPaperDescription(aPaper);
				
				row.createCell(2).setCellValue(paper);
				row.getCell(2).setCellStyle(styleContentBox);
				
				iTotalConvertedHours += aPaper.getPDECL_AuthorConvertedHours();
				iCurrentRow++;
			}
			
		
			// list of SCIE
			row = sheet.createRow(iCurrentRow);
			row.createCell(1).setCellValue("");
			row.getCell(1).setCellStyle(styleContentBox);
			row.createCell(2).setCellValue("SCIE");
			row.getCell(2).setCellStyle(styleBox2);
			
			iCurrentRow++;
			
			for(mPapers aPaper: SCIE){
				row = sheet.createRow(iCurrentRow);
				
				row.createCell(1).setCellValue(iCount++);
				row.getCell(1).setCellStyle(styleContentBox);
				
				String paper = getFullPaperDescription(aPaper);
				
				row.createCell(2).setCellValue(paper);
				row.getCell(2).setCellStyle(styleContentBox);
				
				iTotalConvertedHours += aPaper.getPDECL_AuthorConvertedHours();
				iCurrentRow++;
			}
		
			
			// list of other internaltional journals
			row = sheet.createRow(iCurrentRow);
			row.createCell(1).setCellValue("");
			row.getCell(1).setCellStyle(styleContentBox);
			row.createCell(2).setCellValue("Other International Journal");
			row.getCell(2).setCellStyle(styleBox2);
			
			iCurrentRow++;
			
			for(mPapers aPaper: IJ){
				row = sheet.createRow(iCurrentRow);
				
				row.createCell(1).setCellValue(iCount++);
				row.getCell(1).setCellStyle(styleContentBox);
				
				String paper = getFullPaperDescription(aPaper);
				
				row.createCell(2).setCellValue(paper);
				row.getCell(2).setCellStyle(styleContentBox);
				
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
		
		boxSecondCat.createCell(2).setCellValue("Bài báo đăng trong tạp chí trong nước");
		boxSecondCat.getCell(2).setCellStyle(styleBox2);
		sheet.addMergedRegion(new CellRangeAddress(
				iCurrentRow, //first row (0-based)
	            iCurrentRow, //last row  (0-based)
	            2, //first column (0-based)
	            2  //last column  (0-based)
	    ));
		
		/**
		 * Show all contents for the second category
		 */
		iCount = 1;
		iCurrentRow++;
		if(papersListJDOM_Other != null)
		{
			for (mPapers aPaper : papersListJDOM_Other) {
				HSSFRow boxSecondCatContent = sheet.createRow(iCurrentRow);

				boxSecondCatContent.createCell(1).setCellValue(iCount++);
				boxSecondCatContent.getCell(1).setCellStyle(styleContentBox);
				
				String paper = getFullPaperDescription(aPaper);
				
				boxSecondCatContent.createCell(2).setCellValue(paper);
				boxSecondCatContent.getCell(2).setCellStyle(styleContentBox);

				/*
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
				*/
				
				iTotalConvertedHours += aPaper.getPDECL_AuthorConvertedHours();
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
		sheet.addMergedRegion(new CellRangeAddress(
				iCurrentRow, //first row (0-based)
	            iCurrentRow, //last row  (0-based)
	            2, //first column (0-based)
	            2  //last column  (0-based)
	    ));
		
		/**
		 * Show all contents for the second category
		 */
		iCount = 1;
		iCurrentRow++;
		if(papersListCINT_Other != null)
		{
			for (mPapers aPaper : papersListCINT_Other) {
				HSSFRow boxThirdCatContent = sheet.createRow(iCurrentRow);
				
				boxThirdCatContent.createCell(1).setCellValue(iCount++);
				boxThirdCatContent.getCell(1).setCellStyle(styleContentBox);
				
				String paper = getFullPaperDescription(aPaper);
				
				boxThirdCatContent.createCell(2).setCellValue(paper);
				boxThirdCatContent.getCell(2).setCellStyle(styleContentBox);

				/*
				boxThirdCatContent.createCell(1).setCellValue(iCount++);
				boxThirdCatContent.getCell(1).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(2).setCellValue(aPaper.getPDECL_AuthorList());
				boxThirdCatContent.getCell(2).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(3).setCellValue(aPaper.getPDECL_PublicationName());
				boxThirdCatContent.getCell(3).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(4).setCellValue(aPaper.getPDECL_JournalConferenceName());
				boxThirdCatContent.getCell(4).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(5).setCellValue(aPaper.getPDECL_Year());
				boxThirdCatContent.getCell(5).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(6).setCellValue(aPaper.getPDECL_ISSN());
				boxThirdCatContent.getCell(6).setCellStyle(styleContentBox);
				
				System.out.println("ExcelKV03SummaryBuilder::buildExcelDocument, paper " + iCount + " : " + 
				aPaper.getPDECL_AuthorList() + 
						" : " + aPaper.getPDECL_PublicationName() + " : " + aPaper.getPDECL_JournalConferenceName() + 
						" : " + aPaper.getPDECL_Year() + " : " + aPaper.getPDECL_ISSN());
				*/
				iTotalConvertedHours += aPaper.getPDECL_AuthorConvertedHours();
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
		sheet.addMergedRegion(new CellRangeAddress(
				iCurrentRow, //first row (0-based)
	            iCurrentRow, //last row  (0-based)
	            2, //first column (0-based)
	            2  //last column  (0-based)
	    ));
		
		/**
		 * Show all contents for the fourth category
		 */
		iCount = 1;
		iCurrentRow++;
		if(papersListCINT_Other != null)
		{
			for (mPapers aPaper : papersListCDOM_Other) {
				HSSFRow boxFourthCatContent = sheet.createRow(iCurrentRow);
				
				boxFourthCatContent.createCell(1).setCellValue(iCount++);
				boxFourthCatContent.getCell(1).setCellStyle(styleContentBox);
				
				String paper = getFullPaperDescription(aPaper);
				
				boxFourthCatContent.createCell(2).setCellValue(paper);
				boxFourthCatContent.getCell(2).setCellStyle(styleContentBox);

				/*
				boxFourthCatContent.createCell(1).setCellValue(iCount++);
				boxFourthCatContent.getCell(1).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(2).setCellValue(aPaper.getPDECL_AuthorList());
				boxFourthCatContent.getCell(2).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(3).setCellValue(aPaper.getPDECL_PublicationName());
				boxFourthCatContent.getCell(3).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(4).setCellValue(aPaper.getPDECL_JournalConferenceName());
				boxFourthCatContent.getCell(4).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(5).setCellValue(aPaper.getPDECL_Year());
				boxFourthCatContent.getCell(5).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(6).setCellValue(aPaper.getPDECL_ISSN());
				boxFourthCatContent.getCell(6).setCellStyle(styleContentBox);
				*/
				
				iTotalConvertedHours += aPaper.getPDECL_AuthorConvertedHours();
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
		confirmation.createCell(5).setCellValue("LÃNH ĐẠO KHOA/VIỆN");
		confirmation.getCell(5).setCellStyle(styleSubtitle);

		response.setHeader("Content-Disposition", "attachement; filename=\"" + "Mau-03-KV-tong-hop-bai-bao-toan-don-vi.xls\"");
		
	}

	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// get data model which is passed by the Spring container
		List<mPapers> listPapers = (List<mPapers>) model.get("papersList");
		
		List<mPapers> papersListJINT = (List<mPapers>) model.get("papersListJINT1");
		List<mPapers> papersListJDOM_Other = (List<mPapers>) model.get("papersListJDOM_Other1");
		List<mPapers> papersListCINT_Other = (List<mPapers>) model.get("papersListCINT_Other1");
		List<mPapers> papersListCDOM_Other = (List<mPapers>) model.get("papersListCDOM_Other1");
		
		List<mPapers> papersListJINT2 = (List<mPapers>) model.get("papersListJINT2");
		List<mPapers> papersListJDOM_Other2 = (List<mPapers>) model.get("papersListJDOM_Other2");
		List<mPapers> papersListCINT_Other2 = (List<mPapers>) model.get("papersListCINT_Other2");
		List<mPapers> papersListCDOM_Other2 = (List<mPapers>) model.get("papersListCDOM_Other2");

		// create a new Excel sheet
		HSSFSheet sheet = workbook.createSheet("03-KV");
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
		
		title.createCell(1).setCellValue("DANH MỤC BÀI BÁO ĐĂNG  TRONG TẠP CHÍ VÀ KỶ YẾU HỘI NGHỊ KHOA HỌC CỦA CÁN BỘ TRƯỜNG ĐH BÁCH KHOA HÀ NỘI NĂM HỌC " + yearOfPaper);
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
	    
	    iCurrentRow = 11;
	    HSSFRow bHeader = sheet.createRow(iCurrentRow);
	    bHeader.createCell(1).setCellValue("STT");
	    bHeader.createCell(2).setCellValue("Họ và tên các tác giả, đơn vị (ghi chi tiết)");
	    bHeader.createCell(3).setCellValue("Tên bài báo");
	    bHeader.createCell(4).setCellValue("Tạp chí, Proceedings");
	    bHeader.createCell(5).setCellValue(" ");
	    bHeader.createCell(6).setCellValue(" ");
	    
	    iCurrentRow++;
	    bHeader = sheet.createRow(iCurrentRow);
	    bHeader.createCell(1).setCellValue(" ");
	    bHeader.createCell(2).setCellValue(" ");
	    bHeader.createCell(3).setCellValue(" ");
	    bHeader.createCell(4).setCellValue("Tạp chí, Proceedings");
	    bHeader.createCell(5).setCellValue("Số tạp chí, Thời gian xuất bản");
	    bHeader.createCell(6).setCellValue("Chỉ số ISSN");
	    
	    sheet.setColumnWidth(1, 1500);
		sheet.setColumnWidth(2, 10000);
		sheet.setColumnWidth(3, 10000);
		sheet.setColumnWidth(4, 5000);
		sheet.setColumnWidth(5, 5000);
		sheet.setColumnWidth(6, 5000);
		
	    for(int i = iCurrentRow-1; i <= iCurrentRow; i++){
	    	HSSFRow r = sheet.getRow(i);
	    	for(int j = 1; j <= 6; j++){
	    		HSSFCell cell = r.getCell(j);
	    		cell.setCellStyle(styleBox);
	    	}
	    }
	    sheet.addMergedRegion(new CellRangeAddress(iCurrentRow-1,iCurrentRow, 1,1));
		sheet.addMergedRegion(new CellRangeAddress(iCurrentRow-1,iCurrentRow, 2,2));
		sheet.addMergedRegion(new CellRangeAddress(iCurrentRow-1,iCurrentRow, 3,3));
		sheet.addMergedRegion(new CellRangeAddress(iCurrentRow-1,iCurrentRow-1, 4,6));
		
		/**
		 * Show content's first category 
		 */
		iCurrentRow++;
		HSSFRow boxFisrtCatJulyToDec = sheet.createRow(iCurrentRow);
		boxFisrtCatJulyToDec.createCell(1).setCellValue("I");
		boxFisrtCatJulyToDec.getCell(1).setCellStyle(styleBox);
		
		boxFisrtCatJulyToDec.createCell(2).setCellValue("CÁC BÀI BÁO KHOA HỌC ĐƯỢC ĐĂNG CHÍNH THỨC TRONG KHOẢNG THỜI GIAN TỪ 1/7/2015 - 31/12/2015");
		boxFisrtCatJulyToDec.getCell(2).setCellStyle(styleBox2);
		sheet.addMergedRegion(new CellRangeAddress(
	            iCurrentRow, //first row (0-based)
	            iCurrentRow, //last row  (0-based)
	            2, //first column (0-based)
	            96 //last column  (0-based)
	    ));
		
		/**
		 * Show content's first category 
		 */
		iCurrentRow++;
		HSSFRow boxFisrtCat = sheet.createRow(iCurrentRow);	
		boxFisrtCat.createCell(3).setCellValue("Bài báo đăng trong tạp chí nước ngoài");
		boxFisrtCat.getCell(3).setCellStyle(styleBox2);
		
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
		if(papersListJINT != null)
		{
			for (mPapers aPaper : papersListJINT) { 
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
				
				iTotalConvertedHours += aPaper.getPDECL_AuthorConvertedHours();
				iCurrentRow++;
			}
		}
		
		/**
		 * Show content's second category 
		 */
		HSSFRow boxSecondCat = sheet.createRow(iCurrentRow);
		
		boxSecondCat.createCell(3).setCellValue("Bài báo đăng trong tạp chí trong nước");
		boxSecondCat.getCell(3).setCellStyle(styleBox2);
		
		/**
		 * Show all contents for the second category
		 */
		iCount = 1;
		iCurrentRow++;
		if(papersListJDOM_Other != null)
		{
			for (mPapers aPaper : papersListJDOM_Other) {
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
				
				iTotalConvertedHours += aPaper.getPDECL_AuthorConvertedHours();
				iCurrentRow++;
			}
		}
		/**
		 * Show content's third category 
		 */
		HSSFRow boxThirdCat = sheet.createRow(iCurrentRow);
		
		boxThirdCat.createCell(3).setCellValue("Bài báo đăng trong kỷ yếu hội nghị tổ chức ngoài nước (Proceedings)");
		boxThirdCat.getCell(3).setCellStyle(styleBox2);
		
		/**
		 * Show all contents for the second category
		 */
		iCount = 1;
		iCurrentRow++;
		if(papersListCINT_Other != null)
		{
			for (mPapers aPaper : papersListCINT_Other) {
				HSSFRow boxThirdCatContent = sheet.createRow(iCurrentRow);
				
				boxThirdCatContent.createCell(1).setCellValue(iCount++);
				boxThirdCatContent.getCell(1).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(2).setCellValue(aPaper.getPDECL_AuthorList());
				boxThirdCatContent.getCell(2).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(3).setCellValue(aPaper.getPDECL_PublicationName());
				boxThirdCatContent.getCell(3).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(4).setCellValue(aPaper.getPDECL_JournalConferenceName());
				boxThirdCatContent.getCell(4).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(5).setCellValue(aPaper.getPDECL_Year());
				boxThirdCatContent.getCell(5).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(6).setCellValue(aPaper.getPDECL_ISSN());
				boxThirdCatContent.getCell(6).setCellStyle(styleContentBox);
				
				iTotalConvertedHours += aPaper.getPDECL_AuthorConvertedHours();
				iCurrentRow++;
			}
		}
		/**
		 * Show content's fourth category 
		 */
		HSSFRow boxFourthCat = sheet.createRow(iCurrentRow);
		
		boxFourthCat.createCell(3).setCellValue("Bài báo đăng trong kỷ yếu hội nghị tổ chức trong nước (Proceedings)");
		boxFourthCat.getCell(3).setCellStyle(styleBox2);
		
		/**
		 * Show all contents for the fourth category
		 */
		iCount = 1;
		iCurrentRow++;
		if(papersListCDOM_Other != null)
		{
			for (mPapers aPaper : papersListCDOM_Other) {
				HSSFRow boxFourthCatContent = sheet.createRow(iCurrentRow);
				
				boxFourthCatContent.createCell(1).setCellValue(iCount++);
				boxFourthCatContent.getCell(1).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(2).setCellValue(aPaper.getPDECL_AuthorList());
				boxFourthCatContent.getCell(2).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(3).setCellValue(aPaper.getPDECL_PublicationName());
				boxFourthCatContent.getCell(3).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(4).setCellValue(aPaper.getPDECL_JournalConferenceName());
				boxFourthCatContent.getCell(4).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(5).setCellValue(aPaper.getPDECL_Year());
				boxFourthCatContent.getCell(5).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(6).setCellValue(aPaper.getPDECL_ISSN());
				boxFourthCatContent.getCell(6).setCellStyle(styleContentBox);
				
				iTotalConvertedHours += aPaper.getPDECL_AuthorConvertedHours();
				iCurrentRow++;
			}
		}

		HSSFRow boxFisrtCatJanToJune = sheet.createRow(iCurrentRow);
		boxFisrtCatJanToJune.createCell(1).setCellValue("II");
		boxFisrtCatJanToJune.getCell(1).setCellStyle(styleBox);
		
		boxFisrtCatJanToJune.createCell(2).setCellValue("CÁC BÀI BÁO KHOA HỌC ĐƯỢC ĐĂNG CHÍNH THỨC TRONG KHOẢNG THỜI GIAN TỪ 1/1/2016 - 30/6/2016");
		boxFisrtCatJanToJune.getCell(2).setCellStyle(styleBox2);
		sheet.addMergedRegion(new CellRangeAddress(
	            iCurrentRow, //first row (0-based)
	            iCurrentRow, //last row  (0-based)
	            2, //first column (0-based)
	            96 //last column  (0-based)
	    ));

		/**
		 * Show content's first category 
		 */
		iCurrentRow++;
		HSSFRow boxFisrtCat2 = sheet.createRow(iCurrentRow);	
		boxFisrtCat2.createCell(3).setCellValue("Bài báo đăng trong tạp chí nước ngoài");
		boxFisrtCat2.getCell(3).setCellStyle(styleBox2);
		
		int iCount2 = 1;
		iCurrentRow++;
		int iTotalConvertedHours2 = 0;
		if(papersListJINT2 != null)
		{
			for (mPapers aPaper : papersListJINT2) { 
				HSSFRow boxFisrtCatContent = sheet.createRow(iCurrentRow);
				
				boxFisrtCatContent.createCell(1).setCellValue(iCount2++);
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
				
				iTotalConvertedHours2 += aPaper.getPDECL_AuthorConvertedHours();
				iCurrentRow++;
			}
		}
		
		/**
		 * Show content's second category 
		 */
		HSSFRow boxSecondCat2 = sheet.createRow(iCurrentRow);
		
		boxSecondCat2.createCell(3).setCellValue("Bài báo đăng trong tạp chí trong nước");
		boxSecondCat2.getCell(3).setCellStyle(styleBox2);
		
		/**
		 * Show all contents for the second category
		 */
		iCount2 = 1;
		iCurrentRow++;
		if(papersListJDOM_Other2 != null)
		{
			for (mPapers aPaper : papersListJDOM_Other2) {
				HSSFRow boxSecondCatContent = sheet.createRow(iCurrentRow);
				
				boxSecondCatContent.createCell(1).setCellValue(iCount2++);
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
				
				iTotalConvertedHours2 += aPaper.getPDECL_AuthorConvertedHours();
				iCurrentRow++;
			}
		}
		/**
		 * Show content's third category 
		 */
		HSSFRow boxThirdCat2 = sheet.createRow(iCurrentRow);
		
		boxThirdCat2.createCell(3).setCellValue("Bài báo đăng trong kỷ yếu hội nghị tổ chức ngoài nước (Proceedings)");
		boxThirdCat2.getCell(3).setCellStyle(styleBox2);
		
		/**
		 * Show all contents for the second category
		 */
		iCount2 = 1;
		iCurrentRow++;
		if(papersListCINT_Other2 != null)
		{
			for (mPapers aPaper : papersListCINT_Other2) {
				HSSFRow boxThirdCatContent = sheet.createRow(iCurrentRow);
				
				boxThirdCatContent.createCell(1).setCellValue(iCount2++);
				boxThirdCatContent.getCell(1).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(2).setCellValue(aPaper.getPDECL_AuthorList());
				boxThirdCatContent.getCell(2).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(3).setCellValue(aPaper.getPDECL_PublicationName());
				boxThirdCatContent.getCell(3).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(4).setCellValue(aPaper.getPDECL_JournalConferenceName());
				boxThirdCatContent.getCell(4).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(5).setCellValue(aPaper.getPDECL_Year());
				boxThirdCatContent.getCell(5).setCellStyle(styleContentBox);
				
				boxThirdCatContent.createCell(6).setCellValue(aPaper.getPDECL_ISSN());
				boxThirdCatContent.getCell(6).setCellStyle(styleContentBox);
				
				iTotalConvertedHours2 += aPaper.getPDECL_AuthorConvertedHours();
				iCurrentRow++;
			}
		}
		/**
		 * Show content's fourth category 
		 */
		HSSFRow boxFourthCat2 = sheet.createRow(iCurrentRow);
		
		boxFourthCat2.createCell(3).setCellValue("Bài báo đăng trong kỷ yếu hội nghị tổ chức trong nước (Proceedings)");
		boxFourthCat2.getCell(3).setCellStyle(styleBox2);
		
		/**
		 * Show all contents for the fourth category
		 */
		iCount2 = 1;
		iCurrentRow++;
		if(papersListCDOM_Other2 != null)
		{
			for (mPapers aPaper : papersListCDOM_Other2) {
				HSSFRow boxFourthCatContent = sheet.createRow(iCurrentRow);
				
				boxFourthCatContent.createCell(1).setCellValue(iCount2++);
				boxFourthCatContent.getCell(1).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(2).setCellValue(aPaper.getPDECL_AuthorList());
				boxFourthCatContent.getCell(2).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(3).setCellValue(aPaper.getPDECL_PublicationName());
				boxFourthCatContent.getCell(3).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(4).setCellValue(aPaper.getPDECL_JournalConferenceName());
				boxFourthCatContent.getCell(4).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(5).setCellValue(aPaper.getPDECL_Year());
				boxFourthCatContent.getCell(5).setCellStyle(styleContentBox);
				
				boxFourthCatContent.createCell(6).setCellValue(aPaper.getPDECL_ISSN());
				boxFourthCatContent.getCell(6).setCellStyle(styleContentBox);
				
				iTotalConvertedHours2 += aPaper.getPDECL_AuthorConvertedHours();
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
		confirmation.createCell(5).setCellValue("LÃNH ĐẠO KHOA/VIỆN");
		confirmation.getCell(5).setCellStyle(styleSubtitle);

		
		
		response.setHeader("Content-Disposition", "attachement; filename=\"" + "Mau-03-KV-tong-hop-bai-bao-toan-don-vi.xls\"");
	
		buildSheetListPaper(model, workbook, request, response);
	}
}
