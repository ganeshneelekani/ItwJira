package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agileidc.itw.model.ItwSeverityColour;

@Repository("itwSeverityColourDao")
public class ItwSeverityColourDaoImpl implements ItwSeverityColourDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void addItwSeverityColour(ItwSeverityColour itwSeverityColour) {
		sessionFactory.getCurrentSession().saveOrUpdate(itwSeverityColour);
	}

	
    @SuppressWarnings("unchecked")
	public List<ItwSeverityColour> listItwSeverityColours(int langId) {

		
		List<ItwSeverityColour> listItwSeverityColours = (List<ItwSeverityColour>) sessionFactory
				.getCurrentSession().createCriteria(ItwSeverityColour.class)
				.addOrder(Order.desc("id"))
				.add(Restrictions.like("langid", langId)).list();

		return listItwSeverityColours;

	}

	public ItwSeverityColour getItwSeverityColour(int id) {

		
		ItwSeverityColour itwSeverityColour = (ItwSeverityColour) sessionFactory.getCurrentSession()
				.get(ItwSeverityColour.class, id);

		return itwSeverityColour;

	}

	public void deleteItwSeverityColour(ItwSeverityColour itwSeverityColour)
			throws HibernateException {

	
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM ItwClient WHERE id = "
								+ itwSeverityColour.getId()).executeUpdate();
	}

	
}
