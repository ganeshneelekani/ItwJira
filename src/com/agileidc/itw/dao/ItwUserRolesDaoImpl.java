package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agileidc.itw.model.ItwUserRoles;

@Repository("itwUserRolesDao")
public class ItwUserRolesDaoImpl implements ItwUserRolesDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addItwUserRoles(ItwUserRoles itwUserRoles) {
		sessionFactory.getCurrentSession().saveOrUpdate(itwUserRoles);
	}

	@SuppressWarnings("unchecked")
	public List<ItwUserRoles> listItwUserRoles(int langId) {
		
		List<ItwUserRoles> iterator = sessionFactory.getCurrentSession()
				.createCriteria(ItwUserRoles.class)
				.addOrder(Order.desc("id"))
				.add(Restrictions.eq("langId", langId)).list();
		
		return (List<ItwUserRoles>) sessionFactory.getCurrentSession().createCriteria(ItwUserRoles.class).list();
	}

	public ItwUserRoles getItwUserRoles(int empid) {
		return (ItwUserRoles) sessionFactory.getCurrentSession().get(ItwUserRoles.class, empid);
	}

	public void deleteItwUserRoles(ItwUserRoles itwUserRoles) {
		sessionFactory.getCurrentSession().createQuery("DELETE FROM ItwUserRoles WHERE id = "+itwUserRoles.getId()).executeUpdate();
	}

}
