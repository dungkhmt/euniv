package vn.webapp.modules.researchdeclarationmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.webapp.modules.researchdeclarationmanagement.dao.mPatentCategoryDAO;
import vn.webapp.modules.researchdeclarationmanagement.model.mPatentCategory;

@Service("mPatentCategoryService")
public class mPatentCategoryServiceImpl implements mPatentCategoryService {
	@Autowired
    private mPatentCategoryDAO patentCategoryDAO;

    /**
     * @return object
     */
    @Override
    public List<mPatentCategory> loadPatentCategoryList(){
        try {
        	return patentCategoryDAO.loadPatentCategoryList();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * @param String
     * @return object
     */
    @Override
    public mPatentCategory loadPatentCategoryListByCode(String patentCateCode){
    	try {
    		if(patentCateCode != null){
    			return patentCategoryDAO.loadPatentCategoryListByCode(patentCateCode);
    		}
    		return null;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * @param String
     * @return object
     * @throws UsernameNotFoundException
     */
    @Override
    public mPatentCategory loadAPatentCategoryById(int patentCateId){
    	try {
    		if(patentCateId > 0){
    			return patentCategoryDAO.loadAPatentCategoryById(patentCateId);
    		}
    		return null;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }

}
