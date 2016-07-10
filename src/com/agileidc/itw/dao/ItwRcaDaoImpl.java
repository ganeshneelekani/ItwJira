package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agileidc.itw.model.ItwBrowserTypes;
import com.agileidc.itw.model.ItwRca;
import com.agileidc.itw.model.ItwSeverity;

@Repository("itwRcaDao")
public class ItwRcaDaoImpl implements ItwRcaDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addItwRca(ItwRca itwRca) throws HibernateException{
		
		sessionFactory.getCurrentSession().save(itwRca);
	}

	@SuppressWarnings("unchecked")
	public List<ItwRca> listItwRcas() {

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwRca.class);
				
		List<ItwRca> ItwRcaList = (List<ItwRca>) criteria.list();
		return ItwRcaList;
		
		

	}
	
	@SuppressWarnings("unchecked")
	public List<ItwRca> listItwRcasByTaskId(int taskId) {

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwRca.class)     
				.add(Restrictions.eq("taskId", taskId));
				
		List<ItwRca> ItwRcaList = (List<ItwRca>) criteria.list();
		return ItwRcaList;
		
		

	}

	public ItwRca getItwRca(int id) {
		return (ItwRca) sessionFactory.getCurrentSession().get(
				ItwRca.class, id);
	}

	public void deleteItwRca(ItwRca itwRca) throws HibernateException {

		
		sessionFactory.getCurrentSession().createQuery("DELETE FROM ItwRca WHERE id = "+ itwRca.getId()).executeUpdate();
	}

}
