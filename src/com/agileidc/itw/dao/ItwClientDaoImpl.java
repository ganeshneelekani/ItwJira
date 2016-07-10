package com.agileidc.itw.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agileidc.itw.model.ItwClient;
import com.agileidc.itw.model.ItwClient;

@Repository("itwClientDao")
public class ItwClientDaoImpl implements ItwClientDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void addItwClient(ItwClient itwClientType) {
		sessionFactory.getCurrentSession().saveOrUpdate(itwClientType);
	}

	public List<ItwClient> getItwClientByShortName(String shortName) {

		System.out.println("Inside Client Implimentation---------");
		
		List<ItwClient> listItwClients = (List<ItwClient>) sessionFactory
				.getCurrentSession().createCriteria(ItwClient.class)
				.add(Restrictions.like("clientname", shortName)).list();

		return listItwClients;

	}

	@SuppressWarnings("unchecked")
	public List<ItwClient> listItwClients(int langId) {

		ItwClient value = new ItwClient();

		List<ItwClient> listItwClients = (List<ItwClient>) sessionFactory
				.getCurrentSession().createCriteria(ItwClient.class)
				.addOrder(Order.desc("id"))
				.add(Restrictions.like("langid", langId)).list();

		return listItwClients;

	}

	public ItwClient getItwClient(int id) {

		System.out.println("Inside Client Implimentation------------" + id);
		ItwClient itwClient = (ItwClient) sessionFactory.getCurrentSession()
				.get(ItwClient.class, id);

	

		/*
		 * return (ItwClient) sessionFactory.getCurrentSession().get(
		 * ItwClient.class, empid);
		 */
		return itwClient;

	}

	public void deleteItwClient(ItwClient itwClientType)
			throws HibernateException {

		System.out.println(itwClientType.getId() + "UUUU");
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM ItwClient WHERE id = "
								+ itwClientType.getId()).executeUpdate();
	}

	
}
