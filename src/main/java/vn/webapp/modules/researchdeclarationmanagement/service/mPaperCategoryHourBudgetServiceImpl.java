package vn.webapp.modules.researchdeclarationmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.webapp.modules.researchdeclarationmanagement.dao.mPaperCategoryHourBudgetDAO;
import vn.webapp.modules.researchdeclarationmanagement.model.mPapersCategoryHourBudget;

@Service("mPaperCategoryHourBudgetService")
public class mPaperCategoryHourBudgetServiceImpl implements mPaperCategoryHourBudgetService{
	@Autowired
    private mPaperCategoryHourBudgetDAO paperCategoryHourBudgetDAO;
 
	
	/**
     * 
     */
    @Override
    public List<mPapersCategoryHourBudget> loadPaperCategoryHourBudgets(){
    	return paperCategoryHourBudgetDAO.loadPaperCategoryHourBudgets();
    }
    
    /**
     * 
     */
    @Override
    public List<mPapersCategoryHourBudget> loadPaperCategoryHourBudgetByYear(String reportingrYear){
    	if(!"".equals(reportingrYear)){
    		return paperCategoryHourBudgetDAO.loadPaperCategoryHourBudgetByYear(reportingrYear);
    	}
    	return null;
    }
	
    /**
     * 
     */
    @Override
	public mPapersCategoryHourBudget loadPaperCategoryHourBudgetByCategoryAndYear(String paperCategoryCode, String reportingrYear){
    	if( !("".equals(paperCategoryCode)) || !("".equals(reportingrYear)) ){
    		return paperCategoryHourBudgetDAO.loadPaperCategoryHourBudgetByCategoryAndYear(paperCategoryCode, reportingrYear);
    	}
    	return null;
    }
    
    /**
     * 
     */
    @Override
    public mPapersCategoryHourBudget loadPaperCategoryHourBudgetByCode(String code){
    	if(!"".equals(code)){
    		return paperCategoryHourBudgetDAO.loadPaperCategoryHourBudgetByCode(code);
    	}
    	return null;
    }
    
    /**
     * 
     */
    @Override
    public mPapersCategoryHourBudget loadPaperCategoryHourBudgetById(int id){
    	if(id > 0){
    		return paperCategoryHourBudgetDAO.loadPaperCategoryHourBudgetById(id);
    	}
    	return null;
    }
}
