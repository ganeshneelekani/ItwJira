package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agileidc.itw.model.ItwChatGroupIssue;
import com.agileidc.itw.model.ItwUserLogs;

@Repository("iItwChatGroupIssueDao")
public class ItwChatGroupIssueDaoImpl implements ItwChatGroupIssueDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addItwChatGroupIssue(ItwChatGroupIssue itwChatGroupIssue) {
		sessionFactory.getCurrentSession().saveOrUpdate(itwChatGroupIssue);
	}

	@SuppressWarnings("unchecked")
	public List<ItwChatGroupIssue> listItwChatGroupIssue() {

		
		return (List<ItwChatGroupIssue>) sessionFactory.getCurrentSession()
				.createCriteria(ItwChatGroupIssue.class).list();

	}
public List<ItwChatGroupIssue> getItwChatGroupIssueBySessionId(String sessionId, Integer issueId) {
		
		@SuppressWarnings("unchecked")
		List<ItwChatGroupIssue> listItwChatGroupIssue = (List<ItwChatGroupIssue>) sessionFactory
				.getCurrentSession().createCriteria(ItwChatGroupIssue.class)
				.add(Restrictions.like("issueId", issueId))
				.add(Restrictions.like("sessionId", sessionId)).list();
		
		return listItwChatGroupIssue;
	}

	public ItwChatGroupIssue getItwChatGroupIssue(int id) {
		return (ItwChatGroupIssue) sessionFactory.getCurrentSession().get(
				ItwChatGroupIssue.class, id);
	}

	public void deleteItwChatGroupIssue(ItwChatGroupIssue itwChatGroupIssue) throws HibernateException {

		sessionFactory.getCurrentSession().createQuery("DELETE FROM ItwChatGroupIssue WHERE id = "+ itwChatGroupIssue.getId()).executeUpdate();
	}

}
