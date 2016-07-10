
package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwTweetsDao;
import com.agileidc.itw.model.ItwTweets;

@Service("itwTweetsService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwTweetsServiceImpl implements ItwTweetsService {

	@Autowired
	private ItwTweetsDao itwTweetsDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwTweets(ItwTweets itwTweets) {
		itwTweetsDao.addItwTweets(itwTweets);
	}
	
	public List<ItwTweets> listItwTweets() {
		return itwTweetsDao.listItwTweets();
	}

	public List<ItwTweets> listItwTweetsByProjectId(int projectId){
		return itwTweetsDao.listItwTweetsByProjectId(projectId);
	}
	
	public ItwTweets getItwTweets(int id) {
		return itwTweetsDao.getItwTweets(id);
	}
	
	public void deleteItwTweets(ItwTweets itwTweets) throws HibernateException {
		itwTweetsDao.deleteItwTweets(itwTweets);
	}

}
