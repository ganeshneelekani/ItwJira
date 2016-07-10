package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agileidc.itw.model.ItwLangTypes;

@Repository("itwLangTypesDao")
public class ItwLangTypesDaoImpl implements ItwLangTypesDao {
	
	int id;

	@Autowired
	private SessionFactory sessionFactory;

	public void addItwLangTypes(ItwLangTypes itwLangTypes) {
		sessionFactory.getCurrentSession().saveOrUpdate(itwLangTypes);
	}

	@SuppressWarnings("unchecked")
	public List<ItwLangTypes> listItwLangTypes() {

		List<ItwLangTypes> iterator = sessionFactory.getCurrentSession()
				.createCriteria(ItwLangTypes.class).list();
		for (ItwLangTypes itwLangTypes : iterator) {

			
		}
		return (List<ItwLangTypes>) sessionFactory.getCurrentSession()
				.createCriteria(ItwLangTypes.class).list();

	}

	public ItwLangTypes getItwLangTypes(int empid) {
		return (ItwLangTypes) sessionFactory.getCurrentSession().get(
				ItwLangTypes.class, empid);
	}

	public void deleteItwLangTypes(ItwLangTypes itwLangTypes) throws HibernateException{

		sessionFactory.getCurrentSession().createQuery("DELETE FROM ItwLangTypes WHERE id = "+ itwLangTypes.getId()).executeUpdate();
	}

	public int getItwLangTypesId(String langDesc){
		  Integer tempi = null;
		
		  Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ItwLangTypes.class)
		      .add(Restrictions.eq("langDesc", langDesc));
		  
			
		     
		   List<ItwLangTypes> messageList = criteria.list();
		   for (ItwLangTypes itwLangType : messageList) {

		   System.out.println(itwLangType.getLangDesc()+"******************");
		       tempi  =  itwLangType.getId();
		      
		   
		 }
		   return tempi.intValue() ;

		}

	
}
