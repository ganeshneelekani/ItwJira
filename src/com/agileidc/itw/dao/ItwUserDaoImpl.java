package com.agileidc.itw.dao;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJBTransactionRolledbackException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agileidc.itw.model.ItwModule;
import com.agileidc.itw.model.ItwOnlineUsers;
import com.agileidc.itw.model.ItwUser;
import com.agileidc.itw.model.ItwUserLogs;

@Repository("itwUserDao")
public class ItwUserDaoImpl implements ItwUserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addItwUser(ItwUser itwUser) throws HibernateException {
		String SQL_QUERY = "select max(id)from ItwUserIcon ";
		Query query = sessionFactory.getCurrentSession().createQuery(SQL_QUERY);
		List list = query.list();
		System.out.println("Max  Id in table ItwUserIcon: " + list.get(0));
		Integer tempiconid = (Integer) list.get(0);

		System.out.println("Max  Id in table ItwUserIcon: " + tempiconid);
		itwUser.setIconid(tempiconid);
		sessionFactory.getCurrentSession().saveOrUpdate(itwUser);
		ItwOnlineUsers itwOnlineUsers = new ItwOnlineUsers();
		itwOnlineUsers.setLiveStatus(0);
		itwOnlineUsers.setUserId(itwUser.getUserid());
		sessionFactory.getCurrentSession().save(itwOnlineUsers);

	}

	public void updateItwUser(ItwUser itwUser) throws HibernateException {
            System.out.println("Inside Update ===============");
		sessionFactory.getCurrentSession().saveOrUpdate(itwUser);

	}
	
	
	public void updateItwUserCount(ItwUser itwUser) throws HibernateException {
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE ItwUser set failedcount = :failedcount,enabled=:enabled"
								+ " where userid = :userid ");
		query.setParameter("failedcount", itwUser.getFailedcount());
		query.setParameter("enabled", itwUser.getEnabled());
		query.setParameter("userid", itwUser.getUserid());

		query.executeUpdate();

}
	
	public void updateItwUserPass(String uid,String pass,int onetimepass) throws HibernateException {
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE ItwUser set password = :password,onetimepassword=:onetimepassword"
								+ " where userid = :userid ");
		query.setParameter("password", pass);
		query.setParameter("onetimepassword", onetimepass);
		query.setParameter("userid", uid);

		query.executeUpdate();

}
	

	@SuppressWarnings("unchecked")
	public List<ItwUser> listItwUsers(Integer langId) {
		/*System.out.println("entered ItwUserDao Impl ");
		return (List<ItwUser>) sessionFactory.getCurrentSession()
				.createCriteria(ItwUser.class).list();*/
		
		List<ItwUser> listItwUsers = (List<ItwUser>) sessionFactory
				.getCurrentSession().createCriteria(ItwUser.class)
				.addOrder(Order.desc("id"))
				.add(Restrictions.like("langid", langId)).list();

		return listItwUsers;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ItwUser> getUsers(String uid,String pass) {
		/*System.out.println("entered ItwUserDao Impl ");
		return (List<ItwUser>) sessionFactory.getCurrentSession()
				.createCriteria(ItwUser.class).list();*/
		
		List<ItwUser> itwUsers = (List<ItwUser>) sessionFactory
				.getCurrentSession().createCriteria(ItwUser.class)
				.add(Restrictions.eq("userid", uid))
				.add(Restrictions.eq("password", pass)).list();

		return itwUsers;
	}

	public ItwUser getItwUser(int id) {
		return (ItwUser) sessionFactory.getCurrentSession().get(ItwUser.class,
				id);
	}

	public void deleteItwUser(ItwUser itwUser) {
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM ItwUser WHERE id = " + itwUser.getId())
				.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<ItwUser> getAllUsers(String loggedUserId) {
		
		Criteria criteria = (sessionFactory.getCurrentSession().createCriteria(
				ItwUser.class).add(Restrictions.ne("userid", loggedUserId)));
		List<ItwUser> itwUserList = criteria.list();
		return itwUserList;

	}

	@SuppressWarnings("unchecked")
	public List<ItwUserLogs> getOnlineUsers(String loggedUserId) {

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwUserLogs.class)
				.add(Restrictions.eq("onlinestatus", "Y"))
				.add(Restrictions.ne("userid", loggedUserId));
		return criteria.list();

	}

	@SuppressWarnings("unchecked")
	public List<ItwOnlineUsers> getOfflineUsers(String loggedUserId) {

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwOnlineUsers.class)
				.add(Restrictions.eq("liveStatus", 0))
				.add(Restrictions.ne("userId", loggedUserId));
		return criteria.list();

	}

	/**
	 * This is the method to be used to change users live status as user is
	 * logged in or not.
	 */
	public void changeMyOnlineStatus(String loggedUserId, int status) {
				
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwOnlineUsers.class)
				.add(Restrictions.eq("userId", loggedUserId));
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ItwUser> getItwUserByShortName(String userid) {
		List<ItwUser> listItwUser = (List<ItwUser>) sessionFactory
				.getCurrentSession().createCriteria(ItwUser.class)
				.add(Restrictions.like("userid", userid).ignoreCase()).list();

		return listItwUser;
	}
}
