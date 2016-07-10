package com.agileidc.itw.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.agileidc.itw.helper.ChatDateTime;
import com.agileidc.itw.model.ItwMessage;


/**
 * @author : Rajeev Yagati
 * @version : 1.0 20-1-2014
 * @since : 1.0
 */

@Repository("itwMessageDao")
public class ItwMessageDaoImpl implements ItwMessageDao {

	@Autowired
	private SessionFactory sessionFactory;

	/*@Autowired
	private HibernateTemplate hibernateTemplate;*/

	public void sendMessageToThisUser(String receiverId, String message,
			String senderId, Integer issueId) {
		ItwMessage message2 = new ItwMessage();
		message2.setMessage(message);
		message2.setReceiverId(receiverId);
		message2.setSenderId(senderId);
		message2.setIssueId(issueId);
		message2.setMsgRead("N");
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		
		message2.setTime(date.toString());
		sessionFactory.getCurrentSession().save(message2);
		ItwMessage message1 = new ItwMessage();
		message1.setMessage(message);
		message1.setReceiverId(senderId);
		message1.setSenderId(senderId);
		message1.setIssueId(issueId);
		message1.setMsgRead("N");
		message1.setTime(date.toString());
		sessionFactory.getCurrentSession().save(message1);
	}
	
	public void sendMessageToThisUserOnly(String receiverId, String message,
			String senderId, Integer issueId) {
		ItwMessage message2 = new ItwMessage();
		message2.setMessage(message);
		message2.setReceiverId(receiverId);
		message2.setSenderId(senderId);
		message2.setIssueId(issueId);
		message2.setMsgRead("N");
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();

		message2.setTime(date.toString());
		sessionFactory.getCurrentSession().save(message2);
	
	}


	public List<ItwMessage> getMyLatestMessages(String loggedUserId, Integer issueId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ItwMessage.class)
				.add(Restrictions.like("receiverId", loggedUserId))
				.add(Restrictions.like("issueId", issueId))
				.add(Restrictions.like("msgRead", "N"))
				.addOrder(Order.asc("id"))/*.setMaxResults(5)*/;
		@SuppressWarnings("unchecked")
		List<ItwMessage> messageList = criteria.list();
		for (ItwMessage itwMessage :messageList ){
			itwMessage.setMsgRead("Y");
			sessionFactory.getCurrentSession().update(itwMessage);
			
		}
		
		/*criteria = sessionFactory.getCurrentSession().createCriteria(ItwMessage.class)
				.add(Restrictions.like("receiverId", loggedUserId))
				.add(Restrictions.like("issueId", issueId))
				.addOrder(Order.asc("id"));
		
		messageList = criteria.list();*/
		return messageList;
	}
	
	
	public List<ItwMessage> getAllMessagesForId(String loggedUserId, Integer issueId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ItwMessage.class)
				.add(Restrictions.neProperty("receiverId", "senderId"))
				.add(Restrictions.like("issueId", issueId))
				.addOrder(Order.asc("id"));
		@SuppressWarnings("unchecked")
		List<ItwMessage> messageList = criteria.list();
		
		return messageList;
	}

	
	@SuppressWarnings("unchecked")
	public List<ItwMessage> getMyPrevMessages(String loggedUserId, String minVal) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ItwMessage.class)
				.add(Restrictions.like("receiverId", loggedUserId))
				.add(Restrictions.lt("id", Integer.parseInt(minVal)))
				.addOrder(Order.desc("id")).setMaxResults(5);
		List<ItwMessage> messageList = criteria.list();
		return messageList;
	}

	
	@SuppressWarnings("unchecked")
	public List<ItwMessage> getMyNextMessages(String loggedUserId, String maxVal) {
		System.out.println("maxVal " + maxVal);
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ItwMessage.class)
				.add(Restrictions.like("receiverId", loggedUserId))
				.add(Restrictions.gt("id", Integer.parseInt(maxVal)))
				.addOrder(Order.desc("id")).setMaxResults(5);
		List<ItwMessage> messageList = criteria.list();
		return messageList;
	}

}