package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agileidc.itw.model.ItwUserTypes;

@Repository("itwUserTypesDao")
public class ItwUserTypesDaoImpl implements ItwUserTypesDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addItwUserTypes(ItwUserTypes itwUserTypes) {
		sessionFactory.getCurrentSession().saveOrUpdate(itwUserTypes);
	}

	@SuppressWarnings("unchecked")
	public List<ItwUserTypes> listItwUserTypes() {

		List<ItwUserTypes> iterator = sessionFactory.getCurrentSession()
				.createCriteria(ItwUserTypes.class).list();
		for (ItwUserTypes itwUserTypes : iterator) {
			
			System.out.println(itwUserTypes.getShortName());
		}
		return (List<ItwUserTypes>) sessionFactory.getCurrentSession()
				.createCriteria(ItwUserTypes.class).list();

	}

	public ItwUserTypes getItwUserTypes(int empid) {
		return (ItwUserTypes) sessionFactory.getCurrentSession().get(
				ItwUserTypes.class, empid);
	}

	public void deleteItwUserTypes(ItwUserTypes itwUserTypes) {
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM ItwUserTypes WHERE id = "
								+ itwUserTypes.getId()).executeUpdate();
	}

	

	

}
