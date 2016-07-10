package com.agileidc.itw.dao;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJBTransactionRolledbackException;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agileidc.itw.model.ItwUserIcon;

@Repository("itwUserIconDao")
public class ItwUserIconDaoImpl implements ItwUserIconDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addItwUserIcon(ItwUserIcon itwUserIcon) throws HibernateException {
		
		sessionFactory.getCurrentSession().saveOrUpdate(itwUserIcon);
		
	}

	@SuppressWarnings("unchecked")
	public List<ItwUserIcon> listItwUserIcons() {
		System.out.println("entered ItwUserIcon Impl ");
		return (List<ItwUserIcon>) sessionFactory.getCurrentSession()
				.createCriteria(ItwUserIcon.class).list();
	}

	public ItwUserIcon getItwUserIcon(int id) {
		return (ItwUserIcon) sessionFactory.getCurrentSession().get(ItwUserIcon.class,
				id);
	}

	public void deleteItwUserIcon(ItwUserIcon itwUserIcon) {
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM ItwUserIcon WHERE id = " + itwUserIcon.getId())
				.executeUpdate();
	}

}
