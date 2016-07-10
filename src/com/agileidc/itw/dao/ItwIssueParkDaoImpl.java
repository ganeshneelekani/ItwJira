package com.agileidc.itw.dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.agileidc.itw.model.ItwIssuePark;


@Repository("itwIssueParkDao")
public class ItwIssueParkDaoImpl implements ItwIssueParkDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addItwIssuePark(ItwIssuePark itwIssuePark) {
		sessionFactory.getCurrentSession().saveOrUpdate(itwIssuePark);
	}

	@SuppressWarnings("unchecked")
	public List<ItwIssuePark> listItwIssueParks() {
		
		return (List<ItwIssuePark>) sessionFactory.getCurrentSession().createCriteria(ItwIssuePark.class).list();
	}

	public ItwIssuePark getItwIssuePark(int id) {
		return (ItwIssuePark) sessionFactory.getCurrentSession().get(ItwIssuePark.class, id);
	}

	public void deleteItwIssuePark(ItwIssuePark itwIssuePark) {
		sessionFactory.getCurrentSession().createQuery("DELETE FROM ItwIssuePark WHERE id = "+itwIssuePark.getId()).executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<ItwIssuePark> listItwIssuePark(int taskId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ItwIssuePark.class)
				.add(Restrictions.eq("status", 1))
			    .add(Restrictions.eq("taskId", taskId));
		
		 List<ItwIssuePark> messageList = criteria.list();
	
	 return messageList;
	}
	
	

}
