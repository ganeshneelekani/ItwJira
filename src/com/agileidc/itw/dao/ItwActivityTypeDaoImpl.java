package com.agileidc.itw.dao;


import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agileidc.itw.model.ItwActivityType;


@Repository("itwActivityTypeDao")
public class ItwActivityTypeDaoImpl implements ItwActivityTypeDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addItwActivityType(ItwActivityType itwActivityType) {
		sessionFactory.getCurrentSession().saveOrUpdate(itwActivityType);
	}

	@SuppressWarnings("unchecked")
	public List<ItwActivityType> listItwActivityTypes() {
		
		return (List<ItwActivityType>) sessionFactory.getCurrentSession().createCriteria(ItwActivityType.class).list();
	}

	public ItwActivityType getItwActivityType(int id) {
		return (ItwActivityType) sessionFactory.getCurrentSession().get(ItwActivityType.class, id);
	}

	public void deleteItwActivityType(ItwActivityType itwActivityType) {
		sessionFactory.getCurrentSession().createQuery("DELETE FROM ItwActivityType WHERE id = "+itwActivityType.getId()).executeUpdate();
	}

}
