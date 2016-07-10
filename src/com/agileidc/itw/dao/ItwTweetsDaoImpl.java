package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agileidc.itw.model.ItwMessage;
import com.agileidc.itw.model.ItwTweets;

@Repository("itwTweetsDao")
public class ItwTweetsDaoImpl implements ItwTweetsDao {

	@Autowired
	private SessionFactory sessionFactory;
	public void addItwTweetsWithIssueId(ItwTweets itwTweets, Integer issueId) {
		
		  String tweetmsgtemp = null;
		  if (itwTweets.getTweetmsg().contains("update")) {
			  //tempissueid = itwTweets.getIssueId();
			  tweetmsgtemp = "Issue ID " +"<a href=\"viewItwTaskBug1.html?id="+issueId  +"\">" + issueId + "</a>" + " for project  " +itwTweets.getTweetmsg();
			    
		  }
		  else
		  {
		   tweetmsgtemp = "Issue ID " +"<a href=\"viewItwTaskBug1.html?id="+issueId  +"\">" + issueId + "</a>" + " for project  " +itwTweets.getTweetmsg();
		  
		  }
		  System.out.println("Max  Id in table ItwTASKBUGS: " + issueId);
		  itwTweets.setIssueId(issueId);
		  itwTweets.setTweetmsg(tweetmsgtemp);
		
		sessionFactory.getCurrentSession().saveOrUpdate(itwTweets);
	}
	public void addItwTweets(ItwTweets itwTweets) {
		String SQL_QUERY = "select max(id)from ItwTaskBug where createdby = " + itwTweets.getUserid();
		  Query query = sessionFactory.getCurrentSession().createQuery(SQL_QUERY);
		  List list = query.list();
		  System.out.println("Max  Id in table ItwUserIcon: " + list.get(0));
		  Integer tempissueid = (Integer)list.get(0);
		  String tweetmsgtemp = null;
		  if (itwTweets.getTweetmsg().contains("update")) {
			  tempissueid = itwTweets.getIssueId();
			  tweetmsgtemp = "Issue ID " +"<a href=\"viewItwTaskBug1.html?id="+tempissueid  +"\">" + tempissueid + "</a>" + " for project  " +itwTweets.getTweetmsg();
			    
		  }
		  else
		  {
		   tweetmsgtemp = "Issue ID " +"<a href=\"viewItwTaskBug1.html?id="+tempissueid  +"\">" + tempissueid + "</a>" + " for project  " +itwTweets.getTweetmsg();
		  
		  }
		  System.out.println("Max  Id in table ItwTASKBUGS: " + tempissueid);
		  itwTweets.setIssueId(tempissueid);
		  itwTweets.setTweetmsg(tweetmsgtemp);
		
		sessionFactory.getCurrentSession().saveOrUpdate(itwTweets);
	}

	@SuppressWarnings("unchecked")
	public List<ItwTweets> listItwTweets() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ItwTweets.class)
							.addOrder(Order.desc("id"));
		List<ItwTweets> messageList = criteria.list();
		return messageList;
	}
	
	@SuppressWarnings("unchecked")
	public List<ItwTweets> listItwTweetsByProjectId(int projectId) {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ItwTweets.class)
				.add(Restrictions.like("projectId", projectId))
				.addOrder(Order.desc("id"));
		List<ItwTweets> messageList = criteria.list();
		return messageList;
		
	}

	public ItwTweets getItwTweets(int id) {
		return (ItwTweets) sessionFactory.getCurrentSession().get(ItwTweets.class, id);
	}

	public void deleteItwTweets(ItwTweets itwTweets) throws HibernateException {
		
		sessionFactory.getCurrentSession().createQuery("DELETE FROM ItwTweets WHERE id = "+itwTweets.getId()).executeUpdate();
	}

}
