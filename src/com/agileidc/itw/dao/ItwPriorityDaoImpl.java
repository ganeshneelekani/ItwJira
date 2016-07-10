package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agileidc.itw.model.ItwPriority;
import com.agileidc.itw.model.ItwPriority;
import com.agileidc.itw.model.ItwStagesTypes;
import com.agileidc.itw.model.ItwTweets;

@Repository("itwPriorityDao")
public class ItwPriorityDaoImpl implements ItwPriorityDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addItwPriority(ItwPriority itwPriority) {
		
		String SQL_QUERY = "select max(id)from ItwUserIcon ";
		  Query query = sessionFactory.getCurrentSession().createQuery(SQL_QUERY);
		  List list = query.list();
		  System.out.println("Max  Id in table ItwUserIcon: " + list.get(0));
		  Integer tempiconid = (Integer)list.get(0);
		 
		
		sessionFactory.getCurrentSession().saveOrUpdate(itwPriority);
	}

	@SuppressWarnings("unchecked")
	public List<ItwPriority> listItwPrioritys(int langId) {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwPriority.class)
				.add(Restrictions.eq("langId", langId))
				.addOrder(Order.desc("id"));
		List<ItwPriority> itwPriority = criteria.list();
		return itwPriority;
	}

	public ItwPriority getItwPriority(int id) {
		return (ItwPriority) sessionFactory.getCurrentSession().get(ItwPriority.class, id);
	}

	public void deleteItwPriority(ItwPriority itwPriority) throws HibernateException{
		sessionFactory.getCurrentSession().createQuery("DELETE FROM ItwPriority WHERE id = "+itwPriority.getId()).executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<ItwPriority> listItwPriorityPreced(int langId,String deflt) {
		
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwPriority.class)
				.add(Restrictions.eq("langId", langId))
				.add(Restrictions.eq("defaultvalue", deflt))
				.addOrder(Order.desc("id"));
		List<ItwPriority> PriorityList = (List<ItwPriority>) criteria.list();
		return PriorityList;
		
	}
}
