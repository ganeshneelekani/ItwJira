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

import com.agileidc.itw.model.ItwUserGroup;

@Repository("itwUserGroupDao")
public class ItwUserGroupDaoImpl implements ItwUserGroupDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addItwUserGroup(ItwUserGroup itwUserGroup) {
		System.out.println("inside dao implimenyation");
		sessionFactory.getCurrentSession().saveOrUpdate(itwUserGroup);
	}

	@SuppressWarnings("unchecked")
	public List<ItwUserGroup> listItwUserGroups(int langId) {

		
		System.out.println("  Inside UserGroup IMPLIMENTATION");
		
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwUserGroup.class)
				.addOrder(Order.desc("id"))
				.add(Restrictions.eq("langId", langId));
		List<ItwUserGroup> messageList = criteria.list();
/*
		Iterator<ItwUserGroup> iterator=messageList.iterator();
		while (iterator.hasNext()) {
			ItwUserGroup itwUserBean = (ItwUserGroup) iterator.next();
	
		System.out.println(itwUserBean.getGroupname()+"&&&&&&&&&&&&7");
		System.out.println(itwUserBean.getUsernames());
		
		}*/
		return messageList;
	}

	public ItwUserGroup getItwUserGroup(int id) {
		return (ItwUserGroup) sessionFactory.getCurrentSession().get(
				ItwUserGroup.class, id);
	}

	public void deleteItwUserGroup(ItwUserGroup itwUserGroup)
			throws HibernateException {

		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM ItwUserGroup WHERE id = "
								+ itwUserGroup.getId()).executeUpdate();
	}
	
	
	@Override
	public List<ItwUserGroup> getItwUserGroupByShortName(String shortname) {
		
		
		System.out.println(shortname+"   %%%%%%%");
		@SuppressWarnings("unchecked")
		List<ItwUserGroup> listItwRoles1 = (List<ItwUserGroup>) sessionFactory
				.getCurrentSession().createCriteria(ItwUserGroup.class)
				.add(Restrictions.like("groupname", shortname)).list();

		
		Iterator<ItwUserGroup> iterator=listItwRoles1.iterator();
		while (iterator.hasNext()) {
			ItwUserGroup itwModuleBean = (ItwUserGroup) iterator.next();
	
		System.out.println(itwModuleBean.getGroupname()+"$$$$$&&7");
		System.out.println(itwModuleBean.getId());
		}
		
		
		return listItwRoles1;
	}


}