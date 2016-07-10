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

import com.agileidc.itw.model.ItwSeverity;
import com.agileidc.itw.model.ItwStagesTypes;
import com.agileidc.itw.model.ItwUser;

@Repository("itwSeverityDao")
public class ItwSeverityDaoImpl implements ItwSeverityDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addItwSeverity(ItwSeverity itwSeverity) {
		
		String SQL_QUERY = "select max(id)from ItwUserIcon ";
		  Query query = sessionFactory.getCurrentSession().createQuery(SQL_QUERY);
		  List list = query.list();
		  System.out.println("Max  Id in table ItwUserIcon: " + list.get(0));
		  Integer tempiconid = (Integer)list.get(0);
		  itwSeverity.setIconId(tempiconid);
		
		sessionFactory.getCurrentSession().saveOrUpdate(itwSeverity);
	}

	@SuppressWarnings("unchecked")
	public List<ItwSeverity> listItwSeveritys() {
		return (List<ItwSeverity>) sessionFactory.getCurrentSession().createCriteria(ItwSeverity.class).list();
	}

	public ItwSeverity getItwSeverity(int id) {
		return (ItwSeverity) sessionFactory.getCurrentSession().get(ItwSeverity.class, id);
	}

	public void deleteItwSeverity(ItwSeverity itwSeverity) throws HibernateException{
		sessionFactory.getCurrentSession().createQuery("DELETE FROM ItwSeverity WHERE id = "+itwSeverity.getId()).executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<ItwSeverity> listItwSeverityPreced(int langId,String deflt) {
		
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwSeverity.class)
				.add(Restrictions.eq("langId", langId))
				.add(Restrictions.eq("defaultvalue", deflt))
				.addOrder(Order.desc("id"));
		List<ItwSeverity> SeverityList = (List<ItwSeverity>) criteria.list();
		return SeverityList;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<ItwSeverity> listItwSeveritys(int langId) {
		
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwSeverity.class)
				.add(Restrictions.eq("langId", langId))
				
				.addOrder(Order.desc("id"));
		List<ItwSeverity> SeverityList = (List<ItwSeverity>) criteria.list();
		return SeverityList;
		
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<ItwSeverity> getItwSeverityByShortName(String shortname) {
		List<ItwSeverity> listItwSeverity = (List<ItwSeverity>) sessionFactory
				.getCurrentSession().createCriteria(ItwSeverity.class)
				.add(Restrictions.like("shortname", shortname).ignoreCase()).list();

		return listItwSeverity;
	}

}
