package vn.webapp.modules.researchdeclarationmanagement.dao;

import java.util.List;

import vn.webapp.modules.researchdeclarationmanagement.model.mPapersCategoryHourBudget;

public interface mPaperCategoryHourBudgetDAO {
	
	public List<mPapersCategoryHourBudget> loadPaperCategoryHourBudgets();
	
	public List<mPapersCategoryHourBudget> loadPaperCategoryHourBudgetByYear(String reportingrYear);
	
	public mPapersCategoryHourBudget loadPaperCategoryHourBudgetByCategoryAndYear(String paperCategoryCode, String reportingrYear);
    
    public mPapersCategoryHourBudget loadPaperCategoryHourBudgetByCode(String code);
    
    public mPapersCategoryHourBudget loadPaperCategoryHourBudgetById(int id);
}
