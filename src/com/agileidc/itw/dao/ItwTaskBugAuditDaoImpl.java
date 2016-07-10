package com.agileidc.itw.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



import com.agileidc.itw.model.ItwTaskBugAudit;

@Repository("itwTaskBugAuditDao")
public class ItwTaskBugAuditDaoImpl implements ItwTaskBugAuditDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addItwTaskBugAudit(ItwTaskBugAudit itwTaskBugAudit) {
		sessionFactory.getCurrentSession().saveOrUpdate(itwTaskBugAudit);
	}

	@SuppressWarnings("unchecked")
	public List<ItwTaskBugAudit> listItwTaskBugAudits(int id) {

		
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwTaskBugAudit.class)
				.addOrder(Order.desc("id"))
				.add(Restrictions.eq("taskId", id));
		List<ItwTaskBugAudit> messageList = criteria.list();

		Iterator<ItwTaskBugAudit> iterator=messageList.iterator();
		
		return messageList;

	}

	public ItwTaskBugAudit getItwTaskBugAudit(int id) {
		return (ItwTaskBugAudit) sessionFactory.getCurrentSession().get(
				ItwTaskBugAudit.class, id);
	}

	public void deleteItwTaskBugAudit(ItwTaskBugAudit itwTaskBugAudit) {
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM ItwTaskBugAudit WHERE id = "
								+ itwTaskBugAudit.getId()).executeUpdate();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ItwTaskBugAudit> listItwTaskBugAuditsAscend(int id) {

		
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwTaskBugAudit.class)
				.addOrder(Order.asc("id"))
				.add(Restrictions.isNotNull("lastupdatedtime"))
				.add(Restrictions.eq("taskId", id));
		List<ItwTaskBugAudit> messageList = criteria.list();

		Iterator<ItwTaskBugAudit> iterator=messageList.iterator();
		
		return messageList;

	}
	
public List<ItwTaskBugAudit> listItwTaskBugAuditsFirstRecord(int id) {

		
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwTaskBugAudit.class)
				.addOrder(Order.asc("id"))
				.add(Restrictions.isNull("lastupdatedtime"))
				.add(Restrictions.eq("taskId", id));
		List<ItwTaskBugAudit> messageList = criteria.list();

		Iterator<ItwTaskBugAudit> iterator=messageList.iterator();
		
		return  messageList;

	}

}
