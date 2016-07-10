package com.agileidc.itw.dao;

import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agileidc.itw.model.ItwModule;

@Repository("itwModuleDao")
public class ItwModuleDaoImpl implements ItwModuleDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void addItwModule(ItwModule itwModuleType) {
		sessionFactory.getCurrentSession().saveOrUpdate(itwModuleType);
	}

	public List<ItwModule> getItwModuleByShortName(String shortName) {

		@SuppressWarnings("unchecked")
		List<ItwModule> listItwModules = (List<ItwModule>) sessionFactory
				.getCurrentSession().createCriteria(ItwModule.class)
				.add(Restrictions.like("shortname", shortName)).list();
		
		return listItwModules;

	}

	@SuppressWarnings("unchecked")
	public List<ItwModule> listItwModules(int langid) {

		ItwModule value = new ItwModule();

		List<ItwModule> listItwModules = (List<ItwModule>) sessionFactory
				.getCurrentSession().createCriteria(ItwModule.class)
				.addOrder(Order.desc("id"))
				.add(Restrictions.like("langid", langid)).list();

		Iterator<ItwModule> iterator=listItwModules.iterator();
		while (iterator.hasNext()) {

			System.out.println("++++++++++++++++++++++++++++++++");
			ItwModule itwModule = (ItwModule) iterator.next();
			System.out.println(itwModule.getLangid());
			System.out.println(itwModule.getShortname());
		}
				
		
		
		
		return listItwModules;

	}

	public ItwModule getItwModule(int id) {

		System.out.println("Inside Module Implimentation------------" + id);
		ItwModule itwModule = (ItwModule) sessionFactory.getCurrentSession()
				.get(ItwModule.class, id);

		System.out.println("Inside Module Implimentation------2222------");

		/*
		 * return (ItwModule) sessionFactory.getCurrentSession().get(
		 * ItwModule.class, empid);
		 */
		return itwModule;

	}

	public void deleteItwModule(ItwModule itwModuleType)
			throws HibernateException {

		System.out.println(itwModuleType.getId() + "UUUU");
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM ItwModule WHERE id = "
								+ itwModuleType.getId()).executeUpdate();
	}

	@Override
	public JRDataSource getDataSource() {
List<ItwModule> itwModuleList = listItwModules(1);
		
		JRDataSource ds = new JRBeanCollectionDataSource(itwModuleList); 
		return ds;
	}

	
}
