package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwTweets;

public interface ItwTweetsService {
	
	public void addItwTweets(ItwTweets itwTweets);

	public List<ItwTweets> listItwTweets();
	
	public List<ItwTweets> listItwTweetsByProjectId(int projectId);
	
	public ItwTweets getItwTweets(int id);
	
	public void deleteItwTweets(ItwTweets itwTweets) throws HibernateException;
}
