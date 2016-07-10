package com.agileidc.itw.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agileidc.itw.model.ItwProjectModule;

@Repository("itwProjectModuleDao")
public class ItwProjectModuleDaoImpl implements ItwProjectModuleDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addItwProjectModule(ItwProjectModule itwProjectModule) {
		sessionFactory.getCurrentSession().saveOrUpdate(itwProjectModule);
	}

	@SuppressWarnings("unchecked")
	public List<ItwProjectModule> listItwProjectModule(int langId) {
		
		System.out.println("inside IMPLEMEntation");
		
		List<ItwProjectModule> iterator = sessionFactory.getCurrentSession()
				.createCriteria(ItwProjectModule.class)
				.add(Restrictions.eq("langId", langId)).list();
		
		
		Iterator<ItwProjectModule>  iterator2=iterator.iterator();
		while (iterator2.hasNext()) {
			ItwProjectModule itwProjectModule = (ItwProjectModule) iterator2
					.next();
			
			System.out.println(itwProjectModule.getId());
			System.out.println(itwProjectModule.getModuleid());
			System.out.println(itwProjectModule.getProjectid());
			
		}
		
		return iterator;
	}

	public ItwProjectModule getItwProjectModule(int empid) {
		return (ItwProjectModule) sessionFactory.getCurrentSession().get(ItwProjectModule.class, empid);
	}

	public void deleteItwProjectModule(ItwProjectModule itwProjectModule) {
		sessionFactory.getCurrentSession().createQuery("DELETE FROM ItwProjectModule WHERE id = "+itwProjectModule.getId()).executeUpdate();
	}

}
