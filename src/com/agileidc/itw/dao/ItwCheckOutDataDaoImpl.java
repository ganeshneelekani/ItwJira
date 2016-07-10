package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agileidc.itw.model.ItwCheckOutData;

@Repository("itwCheckOutDataDao")
public class ItwCheckOutDataDaoImpl implements ItwCheckOutDataDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addItwCheckOutData(ItwCheckOutData itwCheckOutData) {
				
		sessionFactory.getCurrentSession().saveOrUpdate(itwCheckOutData);
	}

	@SuppressWarnings("unchecked")
	public List<ItwCheckOutData> listItwCheckOutData() {
		return (List<ItwCheckOutData>) sessionFactory.getCurrentSession().createCriteria(ItwCheckOutData.class).list();
	}

	public ItwCheckOutData getItwCheckOutData(int id) {
		return (ItwCheckOutData) sessionFactory.getCurrentSession().get(ItwCheckOutData.class, id);
	}
public List<ItwCheckOutData> getItwCheckOutDataOnIssueId(int id) {
		
		System.out.println("--------------------------------------------------"+id);

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwCheckOutData.class)
				.add(Restrictions.eq("issueId", id)).addOrder(Order.desc("id"));
		List<ItwCheckOutData> itwCheckOutData = (List<ItwCheckOutData>) criteria.list();
		
		
		
		return itwCheckOutData;
	}
	public void deleteItwCheckOutData(ItwCheckOutData itwCheckOutData) throws HibernateException{
		sessionFactory.getCurrentSession().createQuery("DELETE FROM ItwCheckOutData WHERE id = "+itwCheckOutData.getId()).executeUpdate();
	}

}
