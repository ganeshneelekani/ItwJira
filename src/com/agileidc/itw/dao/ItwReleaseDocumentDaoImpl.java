package com.agileidc.itw.dao;

import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agileidc.itw.bean.ItwReleaseDocumentBean;
import com.agileidc.itw.model.ItwCompany;
import com.agileidc.itw.model.ItwReleaseDocument;
import com.agileidc.itw.model.ItwRoles;

@Repository("itwReleaseDocumentDao")
public class ItwReleaseDocumentDaoImpl implements ItwReleaseDocumentDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addItwReleaseDocument(ItwReleaseDocument itwReleaseDocument) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(itwReleaseDocument);
	}

	@SuppressWarnings("unchecked")
	public List<ItwReleaseDocument> listItwReleaseDocuments(int langId) {

		
		System.out.println("  Inside ReleaseDocument IMPLIMENTATION");
		
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwReleaseDocument.class)
				.addOrder(Order.desc("id"))
				.add(Restrictions.eq("langId", langId));
		List<ItwReleaseDocument> messageList = criteria.list();

		
		return messageList;
	}

	public ItwReleaseDocument getItwReleaseDocument(int id) {
		return (ItwReleaseDocument) sessionFactory.getCurrentSession().get(
				ItwReleaseDocument.class, id);
	}

	
	@SuppressWarnings("unchecked")
	public List<ItwReleaseDocument> getItwReleaseDocumentPdf(int id) {
		
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwReleaseDocument.class)
				.add(Restrictions.eq("id", id));
		
		List<ItwReleaseDocument> messageList = criteria.list();
		
		
		return messageList;
	}
	public void deleteItwReleaseDocument(ItwReleaseDocument itwReleaseDocument)
			throws HibernateException {

		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM ItwReleaseDocument WHERE id = "
								+ itwReleaseDocument.getId()).executeUpdate();
	}
	
	
	@Override
	public List<ItwReleaseDocument> getItwReleaseDocumentByShortName(String shortname) {
		
		
		@SuppressWarnings("unchecked")
		List<ItwReleaseDocument> listItwRoles1 = (List<ItwReleaseDocument>) sessionFactory
				.getCurrentSession().createCriteria(ItwReleaseDocument.class)
				.add(Restrictions.like("shortname", shortname)).list();

		
		
		
		return listItwRoles1;
	}
	
	@Override
	public JRDataSource getDataSource(Integer id) {
		
		
		
		List<ItwReleaseDocument> itwReleaseDocumentList = getItwReleaseDocumentPdf(id);
		
				
		JRDataSource ds = new JRBeanCollectionDataSource(itwReleaseDocumentList); 
		return ds;
	}


}