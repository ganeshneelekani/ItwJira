package com.agileidc.itw.dao;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agileidc.itw.model.Employee;
import com.agileidc.itw.model.ItwRoles;
import com.agileidc.itw.model.ItwRoles;
import com.agileidc.itw.model.ItwRoles;
import com.agileidc.itw.model.ItwRoles;

@Repository("itwRolesDao")
public class ItwRolesDaoImpl implements ItwRolesDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addItwRoles(ItwRoles itwStatusType) {
		sessionFactory.getCurrentSession().saveOrUpdate(itwStatusType);
	}

	@SuppressWarnings("unchecked")
	public List<ItwRoles> listItwRoles(int langid) {

		ItwRoles value = new ItwRoles();

		List<ItwRoles> iterator = (List<ItwRoles>) sessionFactory
				.getCurrentSession().createCriteria(ItwRoles.class)
				.addOrder(Order.desc("id"))
				.add(Restrictions.like("langid", langid)).list();

		/*
		 * List<ItwRoles> iterator = (List<ItwRoles>)
		 * sessionFactory.getCurrentSession()
		 * .createCriteria(ItwRoles.class).list();
		 */

		
		return iterator;

	}

	public ItwRoles getItwRoles(int id) {

		ItwRoles itwRoles = (ItwRoles) sessionFactory.getCurrentSession()
				.get(ItwRoles.class, id);

	
		/*
		 * return (ItwRoles) sessionFactory.getCurrentSession().get(
		 * ItwRoles.class, empid);
		 */
		return itwRoles;
		
	}

	public void deleteItwRoles(ItwRoles itwStatusType)
			throws HibernateException {

		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM ItwRoles WHERE id = "
								+ itwStatusType.getId()).executeUpdate();
	}

	@Override
	public List<ItwRoles> getItwRoleByShortName(String rolename, int langId) {
		@SuppressWarnings("unchecked")
		List<ItwRoles> listItwRoles1 = (List<ItwRoles>) sessionFactory
				.getCurrentSession().createCriteria(ItwRoles.class)
				.add(Restrictions.like("langid", langId))
				.add(Restrictions.like("rolename", rolename)).list();

		return listItwRoles1;
	}

	@Override
	public JRDataSource getDataSource() {
		List<ItwRoles> itwRolesList = listItwRoles(1);
		
		JRDataSource ds = new JRBeanCollectionDataSource(itwRolesList); 
		return ds;
	}

	

	
}
