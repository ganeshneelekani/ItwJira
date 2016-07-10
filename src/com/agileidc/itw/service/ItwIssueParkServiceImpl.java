package com.agileidc.itw.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwIssueParkDao;
import com.agileidc.itw.model.ItwIssuePark;

@Service("itwIssueParkService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwIssueParkServiceImpl implements ItwIssueParkService {

	@Autowired
	private ItwIssueParkDao itwIssueParkDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwIssuePark(ItwIssuePark itwIssuePark) {
		itwIssueParkDao.addItwIssuePark(itwIssuePark);
	}
	
	public List<ItwIssuePark> listItwIssueParks() {
		return itwIssueParkDao.listItwIssueParks();
	}

	public ItwIssuePark getItwIssuePark(int id) {
		return itwIssueParkDao.getItwIssuePark(id);
	}
	
	public void deleteItwIssuePark(ItwIssuePark itwIssuePark) {
		itwIssueParkDao.deleteItwIssuePark(itwIssuePark);
	}
	
	public List<ItwIssuePark> listItwIssuePark(int taskId){
		
		return itwIssueParkDao.listItwIssuePark(taskId);
	}
	

}
