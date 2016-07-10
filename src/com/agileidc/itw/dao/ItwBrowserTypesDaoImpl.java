package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agileidc.itw.model.ItwBrowserTypes;

@Repository("itwBrowserTypeDao")
public class ItwBrowserTypesDaoImpl implements ItwBrowserTypesDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addItwBrowserTypes(ItwBrowserTypes itwBrowserType) {
		sessionFactory.getCurrentSession().saveOrUpdate(itwBrowserType);
	}

	@SuppressWarnings("unchecked")
	public List<ItwBrowserTypes> listItwBrowserTypess() {

		List<ItwBrowserTypes> iterator = sessionFactory.getCurrentSession()
				.createCriteria(ItwBrowserTypes.class).list();
		for (ItwBrowserTypes itwBrowserType : iterator) {

			System.out.println(itwBrowserType.getBrowsername());
		}
		return (List<ItwBrowserTypes>) sessionFactory.getCurrentSession()
				.createCriteria(ItwBrowserTypes.class).list();

	}

	public ItwBrowserTypes getItwBrowserTypes(int empid) {
		return (ItwBrowserTypes) sessionFactory.getCurrentSession().get(
				ItwBrowserTypes.class, empid);
	}

	public void deleteItwBrowserTypes(ItwBrowserTypes itwBrowserType) throws HibernateException {

		System.out.println("Deleting 8888888888888888888888888888");
		sessionFactory.getCurrentSession().createQuery("DELETE FROM ItwBrowserTypes WHERE id = "+ itwBrowserType.getId()).executeUpdate();
	}

}
