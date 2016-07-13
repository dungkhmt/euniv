package vn.webapp.modules.researchmanagement.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.webapp.dao.BaseDao;
import vn.webapp.modules.researchmanagement.model.mProducts;

@Repository("mProductDAO")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class mProductDAOImpl extends BaseDao implements mProductDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Get product list by user
	 * 
	 * @param null
	 * @return List
	 */
	@Override
	public List<mProducts> loadProductsListByStaff(String userRole, String userCode) {
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mProducts.class, "products");
			if (!userRole.equals("ROLE_ADMIN")) {
				criteria.add(Restrictions.eq("products.PROD_User_Code", userCode));
			}
			criteria.addOrder(Order.desc("products.PROD_ID"));
			List<mProducts> products = criteria.list();
			commit();
			return products;
		} catch (HibernateException e) {
			e.printStackTrace();
			rollback();
			close();
			return null;
		} finally {
			flush();
			close();
		}
	}
	
	/**
	 * Get product list by user
	 * 
	 * @return List
	 */
	@Override
	public List<mProducts> filerProductsList(String userRole, String userCode, Integer iStartItem, Integer iNumberOfItems, String sProductStatus, String sProductCategory) {
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mProducts.class, "products");
			if (!userRole.equals("ROLE_ADMIN")) {
				criteria.add(Restrictions.eq("products.PROD_User_Code", userCode));
			}
			if(null != sProductStatus && !sProductStatus.equals("")){
				criteria.add(Restrictions.eq("products.PROD_Status_Code", sProductStatus));
			}
			if(null != sProductCategory && !sProductCategory.equals("")){
				criteria.add(Restrictions.eq("products.PROD_ProjCode", sProductCategory));
			}
			criteria.setFirstResult(iStartItem);
			criteria.setMaxResults(iNumberOfItems);
			criteria.addOrder(Order.desc("products.PROD_ID"));
			List<mProducts> products = criteria.list();
			commit();
			return products;
		} catch (HibernateException e) {
			e.printStackTrace();
			rollback();
			close();
			return null;
		} finally {
			flush();
			close();
		}
	}
	
	/**
	 * Count Items
	 */
	public int countItems(String userRole, String userCode, String sProductStatus, String sProductCategory){
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mProducts.class, "products");
			if (!userRole.equals("ROLE_ADMIN")) {
				criteria.add(Restrictions.eq("products.PROD_User_Code", userCode));
			}
			if(null != sProductStatus && !sProductStatus.equals("")){
				criteria.add(Restrictions.eq("products.PROD_Status_Code", sProductStatus));
			}
			if(null != sProductCategory && !sProductCategory.equals("")){
				criteria.add(Restrictions.eq("products.PROD_ProjCode", sProductCategory));
			}
			criteria.addOrder(Order.desc("products.PROD_ID"));
			List<mProducts> products = criteria.list();
			commit();
			return products.size();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollback();
			close();
			return 0;
		} finally {
			flush();
			close();
		}
	}

	/**
	 * Save a topic
	 * 
	 * @param object
	 * @return int
	 */
	@Override
	public int saveAProduct(mProducts product) {
		try {
			begin();
			int id = 0;
			id = (int) getSession().save(product);
			commit();
			return id;
		} catch (HibernateException e) {
			e.printStackTrace();
			rollback();
			close();
			return 0;
		} finally {
			flush();
			close();
		}
	}

	/**
	 * Load A Product by id and User code
	 * 
	 * @param object
	 * @return int
	 */
	@Override
	public mProducts loadAProductByIdAndUserCode(String userRole, String userCode, int productId) {
		try {
			begin();
			Criteria criteria = getSession().createCriteria(mProducts.class);
			criteria.add(Restrictions.eq("PROD_ID", productId));
			if (!userRole.equals("ROLE_ADMIN")) {
				criteria.add(Restrictions.eq("PROD_User_Code", userCode));
			}
			mProducts product = (mProducts) criteria.uniqueResult();
			commit();
			return product;
		} catch (HibernateException e) {
			e.printStackTrace();
			rollback();
			close();
			return null;
		} finally {
			flush();
			close();
		}
	}

	/**
	 * Edit a product
	 * 
	 * @param object
	 * @return int
	 */
	@Override
	public void editAProduct(mProducts product) {
		try {
			begin();
			getSession().update(product);
			commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollback();
			close();
		} finally {
			flush();
			close();
		}
	}

	/**
	 * Remove a product
	 * 
	 * @param productId
	 * @return
	 */
	@Override
	public int removeAProduct(int productId) {
		mProducts product = new mProducts();
		product.setPROD_ID(productId);
		try {
			begin();
			getSession().delete(product);
			commit();
			return 1;
		} catch (HibernateException e) {
			e.printStackTrace();
			rollback();
			close();
			return 0;
		} finally {
			flush();
			close();
		}
	}
}
