package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agileidc.itw.model.ItwModule;
import com.agileidc.itw.model.ItwOnlineUsers;
import com.agileidc.itw.model.ItwUserLogs;

@Repository("itwUserLogsDao")
public class ItwUserLogsDaoImpl implements ItwUserLogsDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addItwUserLogs(ItwUserLogs itwUserLogs)
			throws HibernateException {

		sessionFactory.getCurrentSession().saveOrUpdate(itwUserLogs);

	}

	public void updateItwUserLogs(ItwUserLogs itwUserLogs)
			throws HibernateException {

		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE ItwUserLogs set logouttime = :logouttime,onlinestatus=:onlinestatus,lastupdatedtime=:lastupdatedtime, logoutreason=:logoutreason "
								+ " where userid = :userid and onlinestatus='Y' ");
		query.setParameter("logouttime", itwUserLogs.getLogouttime());
		query.setParameter("lastupdatedtime", itwUserLogs.getLogouttime());
		query.setParameter("logoutreason", itwUserLogs.getLogoutreason());
		query.setParameter("onlinestatus", itwUserLogs.getOnlinestatus());
		query.setParameter("userid", itwUserLogs.getUserid());

		query.executeUpdate();

		
	}

	@SuppressWarnings("unchecked")
	public List<ItwUserLogs> listItwUserLogs() {
		System.out.println("entered ItwUserLogsDao Impl ");
		return (List<ItwUserLogs>) sessionFactory.getCurrentSession()
				.createCriteria(ItwUserLogs.class).list();
	}
	
	public List<ItwUserLogs> getItwUserLogsByUserId(String userId) {
			
		@SuppressWarnings("unchecked")
		List<ItwUserLogs> listItwUserLogs = (List<ItwUserLogs>) sessionFactory
				.getCurrentSession().createCriteria(ItwUserLogs.class)
				.add(Restrictions.like("onlinestatus", "Y"))
				.add(Restrictions.like("userid", userId)).list();
		
		return listItwUserLogs;
	}
	
	
	public ItwUserLogs getItwUserLogs(int id) {
		return (ItwUserLogs) sessionFactory.getCurrentSession().get(
				ItwUserLogs.class, id);
	}

	public void deleteItwUserLogs(ItwUserLogs itwUserLogs) {
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM ItwUserLogs WHERE id = "
								+ itwUserLogs.getId()).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<ItwUserLogs> getAllUserLogs(String loggedUserLogsId) {
		Criteria criteria = (sessionFactory.getCurrentSession().createCriteria(
				ItwUserLogs.class).add(Restrictions.ne("userid",
				loggedUserLogsId)));
		List<ItwUserLogs> itwUserLogsList = criteria.list();
		return itwUserLogsList;

	}
	@SuppressWarnings("unchecked")
	public List<ItwUserLogs> getAllOnlineUserLogs(String loggedUserLogsId) {
		Criteria criteria = (sessionFactory.getCurrentSession().createCriteria(
				ItwUserLogs.class).add(Restrictions.ne("userid",
				loggedUserLogsId)).add(Restrictions.eq("onlinestatus",
						"Y")));
		List<ItwUserLogs> itwUserLogsList = criteria.list();
		return itwUserLogsList;

	}
	
	@SuppressWarnings("unchecked")
	public List<ItwOnlineUsers> getOnlineUsers(String loggedUserId) {

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwOnlineUsers.class)
				.add(Restrictions.eq("liveStatus", 1))
				.add(Restrictions.ne("UserLogsId", loggedUserId));
		return criteria.list();

	}

	@SuppressWarnings("unchecked")
	public List<ItwOnlineUsers> getOfflineUsers(String loggedUserId) {

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwOnlineUsers.class)
				.add(Restrictions.eq("liveStatus", 0))
				.add(Restrictions.ne("UserLogsId", loggedUserId));
		return criteria.list();

	}

	/**
	 * This is the method to be used to change UserLogss live status as UserLogs
	 * is logged in or not.
	 */
	public void changeMyOnlineStatus(String loggedUserId, int status) {

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwOnlineUsers.class)
				.add(Restrictions.eq("UserLogsId", loggedUserId));
		if (criteria.list().size() == 0) {

			ItwOnlineUsers itwOnlineUsers = new ItwOnlineUsers();
			itwOnlineUsers.setLiveStatus(status);
			itwOnlineUsers.setUserId(loggedUserId);
			sessionFactory.getCurrentSession().save(itwOnlineUsers);
		} else {

			ItwOnlineUsers itwOnlineUsers = (ItwOnlineUsers) criteria.list()
					.get(0);
			itwOnlineUsers.setLiveStatus(status);
			sessionFactory.getCurrentSession().update(itwOnlineUsers);

		}
	}

}
