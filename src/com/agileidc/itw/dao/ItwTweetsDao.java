
package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwTweets;

public interface ItwTweetsDao {
	
	public void addItwTweets(ItwTweets itwTweets);
	public void addItwTweetsWithIssueId(ItwTweets itwTweets, Integer issueId);

	public List<ItwTweets> listItwTweets();
	
	public List<ItwTweets> listItwTweetsByProjectId(int projectId);
	
	public ItwTweets getItwTweets(int id);
	
	public void deleteItwTweets(ItwTweets itwTweets)throws HibernateException;
}
