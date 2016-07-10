package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agileidc.itw.model.ItwClient;
import com.agileidc.itw.model.ItwReleases;

@Repository("itwReleasesDao")
public class ItwReleasesDaoImpl implements ItwReleasesDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addItwReleases(ItwReleases itwReleases) {
		sessionFactory.getCurrentSession().saveOrUpdate(itwReleases);
	}

	@SuppressWarnings("unchecked")
	public List<ItwReleases> listItwReleases(int langId) {
		
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwReleases.class)
				.add(Restrictions.eq("langId", langId))
				.addOrder(Order.desc("id"));
		List<ItwReleases> StagesTypeList = criteria.list();
		return StagesTypeList;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<ItwReleases> listItwReleasesonlyYes(int langId) {
		
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwReleases.class)
				.add(Restrictions.eq("langId", langId))
				.add(Restrictions.eq("active", "Yes"))
				.addOrder(Order.desc("id"));
		List<ItwReleases> StagesTypeList = criteria.list();
		return StagesTypeList;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<ItwReleases> listItwReleasesPrecedTypes(int langId,int predId) {
		
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwReleases.class)
				.add(Restrictions.eq("langId", langId))
				
				.addOrder(Order.desc("id"));
		List<ItwReleases> StagesTypeList = criteria.list();
		return StagesTypeList;
		
	}

	public ItwReleases getItwReleases(int id) {
		return (ItwReleases) sessionFactory.getCurrentSession().get(
				ItwReleases.class, id);
	}

	public void deleteItwReleases(ItwReleases itwReleases) throws HibernateException{
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM ItwReleases WHERE id = "
								+ itwReleases.getId()).executeUpdate();
	}
	@SuppressWarnings("unchecked")
	public List<ItwReleases> listItwReleasesDeftTypes(int langId,String deftId) {
		
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwReleases.class)
				.add(Restrictions.eq("langId", langId))
				.add(Restrictions.eq("defaultvalue", deftId))
				.addOrder(Order.desc("id"));
		List<ItwReleases> StagesTypeList = criteria.list();
		return StagesTypeList;
		
	}


	public List<ItwReleases> getItwReleaseByShortName(String shortName) {

		System.out.println("Inside Releases Implimentation---------");
		
		List<ItwReleases> listItwReleaseDocument= (List<ItwReleases>) sessionFactory
				.getCurrentSession().createCriteria(ItwReleases.class)
				.add(Restrictions.like("shortname", shortName)).list();

		return listItwReleaseDocument;

	}

}
