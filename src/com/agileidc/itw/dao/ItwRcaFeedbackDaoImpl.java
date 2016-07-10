package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agileidc.itw.model.ItwRcaFeedback;

@Repository("itwRcaFeedbackDao")
public class ItwRcaFeedbackDaoImpl implements ItwRcaFeedbackDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addItwRcaFeedback(ItwRcaFeedback itwRcaFeedback) throws HibernateException{
		
		sessionFactory.getCurrentSession().save(itwRcaFeedback);
	}

	@SuppressWarnings("unchecked")
	public List<ItwRcaFeedback> listItwRcaFeedbacks() {

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwRcaFeedback.class);
				
		List<ItwRcaFeedback> ItwRcaFeedbackList = (List<ItwRcaFeedback>) criteria.list();
		return ItwRcaFeedbackList;
		
		

	}
	
	@SuppressWarnings("unchecked")
	public List<ItwRcaFeedback> listItwRcaFeedbacksByTaskId(int taskId) {

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwRcaFeedback.class)     
				.add(Restrictions.eq("taskId", taskId));
				
		List<ItwRcaFeedback> ItwRcaFeedbackList = (List<ItwRcaFeedback>) criteria.list();
		return ItwRcaFeedbackList;
		
		

	}

	public ItwRcaFeedback getItwRcaFeedback(int id) {
		return (ItwRcaFeedback) sessionFactory.getCurrentSession().get(
				ItwRcaFeedback.class, id);
	}

	public void deleteItwRcaFeedback(ItwRcaFeedback itwRcaFeedback) throws HibernateException {

		
		sessionFactory.getCurrentSession().createQuery("DELETE FROM ItwRcaFeedback WHERE id = "+ itwRcaFeedback.getId()).executeUpdate();
	}

}
