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

import com.agileidc.itw.model.ItwPlatForms;

@Repository("itwPlatFormsDao")
public class ItwPlatFormsDaoImpl implements ItwPlatFormsDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addItwPlatForms(ItwPlatForms itwPlatForms) {
		
		String SQL_QUERY = "select max(id)from ItwUserIcon ";
		  Query query = sessionFactory.getCurrentSession().createQuery(SQL_QUERY);
		  List list = query.list();
		  System.out.println("Max  Id in table ItwUserIcon: " + list.get(0));
		  Integer tempiconid = (Integer)list.get(0);
		 
		
		sessionFactory.getCurrentSession().saveOrUpdate(itwPlatForms);
	}

	@SuppressWarnings("unchecked")
	public List<ItwPlatForms> listItwPlatForms(int langId) {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwPlatForms.class)
				.add(Restrictions.eq("langid", langId))
				.addOrder(Order.desc("id"));
		List<ItwPlatForms> itwPlatForms = criteria.list();
		return itwPlatForms;
	}

	public ItwPlatForms getItwPlatForms(int id) {
		return (ItwPlatForms) sessionFactory.getCurrentSession().get(ItwPlatForms.class, id);
	}

	public void deleteItwPlatForms(ItwPlatForms itwPlatForms) throws HibernateException{
		sessionFactory.getCurrentSession().createQuery("DELETE FROM ItwPlatForms WHERE id = "+itwPlatForms.getId()).executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<ItwPlatForms> listItwPlatFormsPreced(int langId,String deflt) {
		
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwPlatForms.class)
				.add(Restrictions.eq("langid", langId))
				.add(Restrictions.eq("defaultvalue", deflt))
				.addOrder(Order.desc("id"));
		List<ItwPlatForms> PlatFormsList = (List<ItwPlatForms>) criteria.list();
		return PlatFormsList;
		
	}
}
