package com.agileidc.itw.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agileidc.itw.bean.ItwProjectBean;
import com.agileidc.itw.model.ItwCompany;
import com.agileidc.itw.model.ItwProject;
import com.agileidc.itw.model.ItwRoles;

@Repository("itwProjectDao")
public class ItwProjectDaoImpl implements ItwProjectDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addItwProject(ItwProject itwProject) {
		
		System.out.println(itwProject.getModulename()+" -- Dao Implemntation");
		sessionFactory.getCurrentSession().saveOrUpdate(itwProject);
	}

	@SuppressWarnings("unchecked")
	public List<ItwProject> listItwProjects(int langId) {

		
		System.out.println("  Inside Project IMPLIMENTATION");
		
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwProject.class)
				.addOrder(Order.desc("id"))
				.add(Restrictions.eq("langId", langId));
		List<ItwProject> messageList = criteria.list();

		Iterator<ItwProject> iterator=messageList.iterator();
		while (iterator.hasNext()) {
			ItwProject itwModuleBean = (ItwProject) iterator.next();
	
		System.out.println(itwModuleBean.getShortname()+"&&&&&&&&&&&&7");
		
		}
		return messageList;
	}

	public ItwProject getItwProject(int id) {
		return (ItwProject) sessionFactory.getCurrentSession().get(
				ItwProject.class, id);
	}

	public void deleteItwProject(ItwProject itwProject)
			throws HibernateException {

		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM ItwProject WHERE id = "
								+ itwProject.getId()).executeUpdate();
	}
	
	
	@Override
	public List<ItwProject> getItwProjectByShortName(String shortname) {
		
		
		System.out.println(shortname+"   %%%%%%%");
		@SuppressWarnings("unchecked")
		List<ItwProject> listItwRoles1 = (List<ItwProject>) sessionFactory
				.getCurrentSession().createCriteria(ItwProject.class)
				.add(Restrictions.like("shortname", shortname)).list();

		
		Iterator<ItwProject> iterator=listItwRoles1.iterator();
		while (iterator.hasNext()) {
			ItwProject itwModuleBean = (ItwProject) iterator.next();
	
		System.out.println(itwModuleBean.getShortname()+"$$$$$&&7");
		System.out.println(itwModuleBean.getId());
		}
		
		
		return listItwRoles1;
	}


}